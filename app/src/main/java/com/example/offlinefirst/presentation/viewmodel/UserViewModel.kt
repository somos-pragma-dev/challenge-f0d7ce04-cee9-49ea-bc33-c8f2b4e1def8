package com.example.offlinefirst.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.offlinefirst.domain.model.User
import com.example.offlinefirst.domain.model.UserValidationResult
import com.example.offlinefirst.domain.usecase.GetUserUseCase
import com.example.offlinefirst.domain.usecase.SaveUserUseCase
import com.example.offlinefirst.domain.usecase.SyncDataUseCase
import com.example.offlinefirst.util.Resource
import com.example.offlinefirst.util.Constants
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Estado interno de la UI de usuarios.
 */
data class UserUiState(
    val isLoading: Boolean = false,
    val users: List<User> = emptyList(),
    val selectedUser: User? = null,
    val error: String? = null,
    val isSyncing: Boolean = false,
    val syncProgress: Float = 0f,
    val searchQuery: String = "",
    val filteredUsers: List<User> = emptyList(),
    val isOffline: Boolean = false,
    val lastSyncTimestamp: Long = 0L
)

/**
 * Eventos unidireccionales desde el ViewModel hacia la UI.
 */
sealed class UserEvent {
    data class ShowSnackbar(val message: String) : UserEvent()
    data class NavigateToUserDetail(val userId: String) : UserEvent()
    data object NavigateBack : UserEvent()
    data class ShowError(val message: String) : UserEvent()
    data object SyncCompleted : UserEvent()
    data class SyncFailed(val message: String) : UserEvent()
}

/**
 * ViewModel para la gestión de usuarios.
 * Maneja el estado de la UI y coordina las operaciones de dominio.
 */
@HiltViewModel
class UserViewModel @Inject constructor(
    private val getUserUseCase: GetUserUseCase,
    private val saveUserUseCase: SaveUserUseCase,
    private val syncDataUseCase: SyncDataUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(UserUiState())
    val uiState: StateFlow<UserUiState> = _uiState.asStateFlow()

    private val _events = MutableSharedFlow<UserEvent>()
    val events = _events.asSharedFlow()

    private val _userFlow = MutableStateFlow<Resource<User>>(Resource.loading())
    val userFlow: StateFlow<Resource<User>> = _userFlow.asStateFlow()

    init {
        loadUsers()
    }

    /**
     * Carga la lista de usuarios desde el repositorio.
     */
    fun loadUsers() {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true, error = null) }
            
            getUserUseCase.getAllUsers()
                .onEach { result ->
                    result.fold(
                        onSuccess = { users ->
                            _uiState.update { state ->
                                state.copy(
                                    isLoading = false,
                                    users = users,
                                    filteredUsers = filterUsers(users, state.searchQuery),
                                    error = null
                                )
                            }
                        },
                        onFailure = { throwable ->
                            _uiState.update { state ->
                                state.copy(
                                    isLoading = false,
                                    error = throwable.message ?: Constants.ErrorMessages.UNKNOWN_ERROR
                                )
                            }
                            _events.emit(UserEvent.ShowError(throwable.message ?: "Error al cargar usuarios"))
                        }
                    )
                }
                .catch { e ->
                    _uiState.update { it.copy(isLoading = false, error = e.message) }
                }
                .launchIn(viewModelScope)
        }
    }

    /**
     * Carga un usuario específico por su ID.
     */
    fun loadUser(userId: String) {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true, error = null) }
            _userFlow.value = Resource.loading()
            
            getUserUseCase.getUserById(userId)
                .onEach { result ->
                    result.fold(
                        onSuccess = { user ->
                            _userFlow.value = Resource.success(user)
                            _uiState.update { it.copy(isLoading = false, selectedUser = user) }
                        },
                        onFailure = { throwable ->
                            _userFlow.value = Resource.error(throwable.message ?: "Error", throwable)
                            _uiState.update { it.copy(isLoading = false, error = throwable.message) }
                        }
                    )
                }
                .catch { e ->
                    _userFlow.value = Resource.error(e.message ?: "Error", e)
                    _uiState.update { it.copy(isLoading = false, error = e.message) }
                }
                .launchIn(viewModelScope)
        }
    }

    /**
     * Guarda un usuario, ya sea creando uno nuevo o actualizando existente.
     */
    fun saveUser(user: User) {
        viewModelScope.launch {
            val validationResult = user.validate()
            if (!validationResult.isValid()) {
                val errors = validationResult.getErrors().joinToString(", ")
                _events.emit(UserEvent.ShowError(errors))
                return@launch
            }

            _uiState.update { it.copy(isLoading = true, error = null) }
            
            saveUserUseCase.saveUser(user)
                .onEach { result ->
                    result.fold(
                        onSuccess = { savedUser ->
                            _uiState.update { state ->
                                val updatedList = if (state.users.any { it.id == savedUser.id }) {
                                    state.users.map { if (it.id == savedUser.id) savedUser else it }
                                } else {
                                    state.users + savedUser
                                }
                                state.copy(
                                    isLoading = false,
                                    users = updatedList,
                                    filteredUsers = filterUsers(updatedList, state.searchQuery),
                                    selectedUser = savedUser
                                )
                            }
                            _events.emit(UserEvent.ShowSnackbar("Usuario guardado correctamente"))
                            _events.emit(UserEvent.NavigateBack)
                        },
                        onFailure = { throwable ->
                            _uiState.update { it.copy(isLoading = false, error = throwable.message) }
                            _events.emit(UserEvent.ShowError(throwable.message ?: "Error al guardar"))
                        }
                    )
                }
                .catch { e ->
                    _uiState.update { it.copy(isLoading = false, error = e.message) }
                }
                .launchIn(viewModelScope)
        }
    }

    /**
     * Elimina un usuario por su ID.
     */
    fun deleteUser(userId: String) {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true, error = null) }
            
            saveUserUseCase.deleteUser(userId)
                .onEach { result ->
                    result.fold(
                        onSuccess = {
                            _uiState.update { state ->
                                val updatedList = state.users.filter { it.id != userId }
                                state.copy(
                                    isLoading = false,
                                    users = updatedList,
                                    filteredUsers = filterUsers(updatedList, state.searchQuery),
                                    selectedUser = if (state.selectedUser?.id == userId) null else state.selectedUser
                                )
                            }
                            _events.emit(UserEvent.ShowSnackbar("Usuario eliminado"))
                        },
                        onFailure = { throwable ->
                            _uiState.update { it.copy(isLoading = false, error = throwable.message) }
                            _events.emit(UserEvent.ShowError(throwable.message ?: "Error al eliminar"))
                        }
                    )
                }
                .catch { e ->
                    _uiState.update { it.copy(isLoading = false, error = e.message) }
                }
                .launchIn(viewModelScope)
        }
    }

    /**
     * Inicia el proceso de sincronización de datos.
     */
    fun syncData() {
        viewModelScope.launch {
            if (_uiState.value.isSyncing) {
                _events.emit(UserEvent.ShowSnackbar("Sincronización en progreso"))
                return@launch
            }

            _uiState.update { it.copy(isSyncing = true, syncProgress = 0f, error = null) }
            
            syncDataUseCase.syncAll()
                .onEach { result ->
                    result.fold(
                        onSuccess = { _ ->
                            _uiState.update { it.copy(
                                isSyncing = false,
                                syncProgress = 1f,
                                lastSyncTimestamp = System.currentTimeMillis()
                            )}
                            loadUsers()
                            _events.emit(UserEvent.SyncCompleted)
                            _events.emit(UserEvent.ShowSnackbar("Sincronización completada"))
                        },
                        onFailure = { throwable ->
                            _uiState.update { it.copy(isSyncing = false, error = throwable.message) }
                            _events.emit(UserEvent.SyncFailed(throwable.message ?: "Error de sincronización"))
                        }
                    )
                }
                .catch { e ->
                    _uiState.update { it.copy(isSyncing = false, error = e.message) }
                    _events.emit(UserEvent.SyncFailed(e.message ?: "Error de sincronización"))
                }
                .launchIn(viewModelScope)
        }
    }

    /**
     * Actualiza el query de búsqueda y filtra la lista de usuarios.
     */
    fun onSearchQueryChanged(query: String) {
        _uiState.update { state ->
            state.copy(
                searchQuery = query,
                filteredUsers = filterUsers(state.users, query)
            )
        }
    }

    /**
     * Actualiza el estado de conexión a internet.
     */
    fun setOfflineStatus(isOffline: Boolean) {
        _uiState.update { it.copy(isOffline = isOffline) }
    }

    /**
     * Selecciona un usuario para ver o editar.
     */
    fun selectUser(user: User?) {
        _uiState.update { it.copy(selectedUser = user) }
        user?.let { _events.emit(UserEvent.NavigateToUserDetail(it.id)) }
    }

    /**
     * Limpia el error actual.
     */
    fun clearError() {
        _uiState.update { it.copy(error = null) }
    }

    /**
     * Filtra usuarios según el query de búsqueda.
     */
    private fun filterUsers(users: List<User>, query: String): List<User> {
        if (query.isBlank()) return users
        val lowerQuery = query.lowercase()
        return users.filter { user ->
            user.username.lowercase().contains(lowerQuery) ||
            user.email.lowercase().contains(lowerQuery) ||
            user.displayName.lowercase().contains(lowerQuery)
        }
    }

    /**
     * Actualiza el progreso de sincronización.
     */
    fun updateSyncProgress(progress: Float) {
        _uiState.update { it.copy(syncProgress = progress) }
    }

    /**
     * Obtiene usuarios que necesitan sincronización.
     */
    fun getPendingSyncUsers(): List<User> {
        return _uiState.value.users.filter { it.needsSync() }
    }

    override fun onCleared() {
        super.onCleared()
    }
}