# Prompt para Mejorar el Codigo Base

Copia y pega el contenido del bloque de abajo en un asistente de IA (Claude, ChatGPT)
para obtener un ZIP con el proyecto completo y arrancable.

Si preferis trabajar en tu editor con un agente local (Claude Code, Cursor, Copilot), usa `AGENTS.md` en vez de este archivo: dice lo mismo pero para que escriba los archivos en disco.

## Las dos reglas que no se negocian

1. **Completa el boilerplate.** Todo lo que el proyecto necesita para compilar y arrancar: manifiesto de dependencias, punto de entrada, configuracion, capa de interfaz, y las capas del patron arquitectonico declarado. Eso es andamiaje y es tu trabajo.
2. **NO resuelvas el reto.** Los entregables de las fases son el trabajo de la persona. El hueco pedagogico se deja como esta: el proyecto arranca, pero lo que el reto pide implementar NO esta implementado.

Dicho de otra forma: si algo impide compilar, arreglalo. Si algo es logica de negocio incompleta, validaciones ausentes, un secreto hardcodeado o un patron mejorable, dejalo exactamente como esta — es lo que la persona tiene que encontrar.

## Lo que le falta a este proyecto

Esto NO lo tenes que adivinar: salio de comparar el proyecto contra la arquitectura declarada del reto y de un analisis estatico del codigo. Completalo TODO.

### Archivos que la arquitectura del reto declara y no estan

Creálos con implementacion real, en la capa que les corresponde:

- `app/src/main/java/com/example/offlinefirst/data/remote/ApiClient.kt`

### Referencias colgando en el codigo que si esta

Cada una rompe la compilacion:

- `app/src/main/java/com/example/offlinefirst/domain/usecase/SyncDataUseCase.kt` — `com.example.offlinefirst.domain.model.OperationType`: El import com.example.offlinefirst.domain.model.OperationType usa un paquete propio del proyecto pero ningun archivo generado declara ese tipo. Falta generar la clase/interfaz, o el import esta mal escrito.
- `app/src/main/java/com/example/offlinefirst/MainActivity.kt` — `com.example.offlinefirst.presentation.ui.screens.UserScreen`: El import com.example.offlinefirst.presentation.ui.screens.UserScreen usa un paquete propio del proyecto pero ningun archivo generado declara ese tipo. Falta generar la clase/interfaz, o el import esta mal escrito.
- `app/src/main/java/com/example/offlinefirst/MainActivity.kt` — `com.example.offlinefirst.presentation.ui.theme.OfflineFirstTheme`: El import com.example.offlinefirst.presentation.ui.theme.OfflineFirstTheme usa un paquete propio del proyecto pero ningun archivo generado declara ese tipo. Falta generar la clase/interfaz, o el import esta mal escrito.
- `app/src/main/java/com/example/offlinefirst/data/local/entity/SyncOperationEntity.kt` — `com.example.offlinefirst.domain.model.OperationType`: El import com.example.offlinefirst.domain.model.OperationType usa un paquete propio del proyecto pero ningun archivo generado declara ese tipo. Falta generar la clase/interfaz, o el import esta mal escrito.
- `app/src/main/java/com/example/offlinefirst/data/sync/SyncManager.kt` — `com.example.offlinefirst.domain.model.SyncResult`: El import com.example.offlinefirst.domain.model.SyncResult usa un paquete propio del proyecto pero ningun archivo generado declara ese tipo. Falta generar la clase/interfaz, o el import esta mal escrito.
- `app/src/main/java/com/example/offlinefirst/presentation/ui/screens/UserScreen.kt` — `com.example.offlinefirst.presentation.ui.components.SyncStatusIndicator`: El import com.example.offlinefirst.presentation.ui.components.SyncStatusIndicator usa un paquete propio del proyecto pero ningun archivo generado declara ese tipo. Falta generar la clase/interfaz, o el import esta mal escrito.
- `app/src/main/java/com/example/offlinefirst/domain/model/User.kt` — `Preferences`: Preferences se usa en el cuerpo del archivo pero no esta importado. El proyecto lo declara en com.example.offlinefirst.util.Preferences.
- `app/src/main/java/com/example/offlinefirst/domain/model/DownloadedContent.kt` — `Content`: Content se usa en el cuerpo del archivo pero no esta importado. El proyecto lo declara en com.example.offlinefirst.util.Content.
- `app/src/main/java/com/example/offlinefirst/domain/usecase/SaveUserUseCase.kt` — `Error`: Error se usa en el cuerpo del archivo pero no esta importado. El proyecto lo declara en com.example.offlinefirst.util.Error (hay mas de un tipo con ese nombre en el proyecto).
- `app/src/main/java/com/example/offlinefirst/domain/usecase/SaveUserUseCase.kt` — `Success`: Success se usa en el cuerpo del archivo pero no esta importado. El proyecto lo declara en com.example.offlinefirst.util.Success (hay mas de un tipo con ese nombre en el proyecto).
- `app/src/main/java/com/example/offlinefirst/domain/usecase/SaveUserUseCase.kt` — `Valid`: Valid se usa en el cuerpo del archivo pero no esta importado. El proyecto lo declara en com.example.offlinefirst.domain.model.Valid (hay mas de un tipo con ese nombre en el proyecto).
- `app/src/main/java/com/example/offlinefirst/domain/usecase/SaveUserUseCase.kt` — `Invalid`: Invalid se usa en el cuerpo del archivo pero no esta importado. El proyecto lo declara en com.example.offlinefirst.domain.model.Invalid (hay mas de un tipo con ese nombre en el proyecto).
- `app/src/main/java/com/example/offlinefirst/domain/usecase/SaveUserUseCase.kt` — `SyncOperation`: SyncOperation se usa en el cuerpo del archivo pero no esta importado. El proyecto lo declara en com.example.offlinefirst.domain.model.SyncOperation.
- `app/src/main/java/com/example/offlinefirst/domain/usecase/SaveUserUseCase.kt` — `Loading`: Loading se usa en el cuerpo del archivo pero no esta importado. El proyecto lo declara en com.example.offlinefirst.util.Loading (hay mas de un tipo con ese nombre en el proyecto).
- `app/src/main/java/com/example/offlinefirst/domain/usecase/SyncDataUseCase.kt` — `SyncOperationResult`: SyncOperationResult se usa en el cuerpo del archivo pero no esta importado. El proyecto lo declara en com.example.offlinefirst.domain.repository.SyncOperationResult.
- `app/src/main/java/com/example/offlinefirst/domain/usecase/SyncDataUseCase.kt` — `Success`: Success se usa en el cuerpo del archivo pero no esta importado. El proyecto lo declara en com.example.offlinefirst.util.Success (hay mas de un tipo con ese nombre en el proyecto).
- `app/src/main/java/com/example/offlinefirst/domain/usecase/SyncDataUseCase.kt` — `Error`: Error se usa en el cuerpo del archivo pero no esta importado. El proyecto lo declara en com.example.offlinefirst.util.Error (hay mas de un tipo con ese nombre en el proyecto).
- `app/src/main/java/com/example/offlinefirst/data/local/entity/UserEntity.kt` — `Invalid`: Invalid se usa en el cuerpo del archivo pero no esta importado. El proyecto lo declara en com.example.offlinefirst.domain.model.Invalid (hay mas de un tipo con ese nombre en el proyecto).
- `app/src/main/java/com/example/offlinefirst/data/local/entity/PreferencesEntity.kt` — `User`: User se usa en el cuerpo del archivo pero no esta importado. El proyecto lo declara en com.example.offlinefirst.domain.model.User.
- `app/src/main/java/com/example/offlinefirst/data/local/entity/SyncOperationEntity.kt` — `SyncOperationStatus`: SyncOperationStatus se usa en el cuerpo del archivo pero no esta importado. El proyecto lo declara en com.example.offlinefirst.domain.model.SyncOperationStatus.
- `app/src/main/java/com/example/offlinefirst/data/repository/PreferencesRepositoryImpl.kt` — `Resource`: Resource se usa en el cuerpo del archivo pero no esta importado. El proyecto lo declara en com.example.offlinefirst.util.Resource.
- `app/src/main/java/com/example/offlinefirst/data/repository/PreferencesRepositoryImpl.kt` — `PreferenceValidationResult`: PreferenceValidationResult se usa en el cuerpo del archivo pero no esta importado. El proyecto lo declara en com.example.offlinefirst.domain.model.PreferenceValidationResult.
- `app/src/main/java/com/example/offlinefirst/data/repository/PreferencesRepositoryImpl.kt` — `User`: User se usa en el cuerpo del archivo pero no esta importado. El proyecto lo declara en com.example.offlinefirst.domain.model.User.
- `app/src/main/java/com/example/offlinefirst/data/repository/PreferencesRepositoryImpl.kt` — `Valid`: Valid se usa en el cuerpo del archivo pero no esta importado. El proyecto lo declara en com.example.offlinefirst.domain.model.Valid (hay mas de un tipo con ese nombre en el proyecto).
- `app/src/main/java/com/example/offlinefirst/data/repository/PreferencesRepositoryImpl.kt` — `Invalid`: Invalid se usa en el cuerpo del archivo pero no esta importado. El proyecto lo declara en com.example.offlinefirst.domain.model.Invalid (hay mas de un tipo con ese nombre en el proyecto).
- `app/src/main/java/com/example/offlinefirst/data/repository/ContentRepositoryImpl.kt` — `Loading`: Loading se usa en el cuerpo del archivo pero no esta importado. El proyecto lo declara en com.example.offlinefirst.util.Loading (hay mas de un tipo con ese nombre en el proyecto).
- `app/src/main/java/com/example/offlinefirst/data/repository/ContentRepositoryImpl.kt` — `Success`: Success se usa en el cuerpo del archivo pero no esta importado. El proyecto lo declara en com.example.offlinefirst.util.Success (hay mas de un tipo con ese nombre en el proyecto).
- `app/src/main/java/com/example/offlinefirst/data/repository/ContentRepositoryImpl.kt` — `Error`: Error se usa en el cuerpo del archivo pero no esta importado. El proyecto lo declara en com.example.offlinefirst.util.Error (hay mas de un tipo con ese nombre en el proyecto).
- `app/src/main/java/com/example/offlinefirst/data/repository/ContentRepositoryImpl.kt` — `Content`: Content se usa en el cuerpo del archivo pero no esta importado. El proyecto lo declara en com.example.offlinefirst.util.Content.
- `app/src/main/java/com/example/offlinefirst/data/repository/ContentRepositoryImpl.kt` — `Failed`: Failed se usa en el cuerpo del archivo pero no esta importado. El proyecto lo declara en com.example.offlinefirst.data.sync.Failed (hay mas de un tipo con ese nombre en el proyecto).
- `app/src/main/java/com/example/offlinefirst/data/repository/SyncRepositoryImpl.kt` — `Loading`: Loading se usa en el cuerpo del archivo pero no esta importado. El proyecto lo declara en com.example.offlinefirst.util.Loading (hay mas de un tipo con ese nombre en el proyecto).
- `app/src/main/java/com/example/offlinefirst/data/repository/SyncRepositoryImpl.kt` — `Success`: Success se usa en el cuerpo del archivo pero no esta importado. El proyecto lo declara en com.example.offlinefirst.util.Success (hay mas de un tipo con ese nombre en el proyecto).
- `app/src/main/java/com/example/offlinefirst/data/repository/SyncRepositoryImpl.kt` — `Error`: Error se usa en el cuerpo del archivo pero no esta importado. El proyecto lo declara en com.example.offlinefirst.util.Error (hay mas de un tipo con ese nombre en el proyecto).
- `app/src/main/java/com/example/offlinefirst/data/repository/SyncRepositoryImpl.kt` — `Sync`: Sync se usa en el cuerpo del archivo pero no esta importado. El proyecto lo declara en com.example.offlinefirst.util.Sync (hay mas de un tipo con ese nombre en el proyecto).
- `app/src/main/java/com/example/offlinefirst/data/repository/SyncRepositoryImpl.kt` — `Failed`: Failed se usa en el cuerpo del archivo pero no esta importado. El proyecto lo declara en com.example.offlinefirst.data.sync.Failed (hay mas de un tipo con ese nombre en el proyecto).
- `app/src/main/java/com/example/offlinefirst/data/remote/ApiService.kt` — `Content`: Content se usa en el cuerpo del archivo pero no esta importado. El proyecto lo declara en com.example.offlinefirst.util.Content.
- `app/src/main/java/com/example/offlinefirst/data/remote/ApiService.kt` — `Invalid`: Invalid se usa en el cuerpo del archivo pero no esta importado. El proyecto lo declara en com.example.offlinefirst.domain.model.Invalid (hay mas de un tipo con ese nombre en el proyecto).
- `app/src/main/java/com/example/offlinefirst/data/remote/ApiService.kt` — `Resource`: Resource se usa en el cuerpo del archivo pero no esta importado. El proyecto lo declara en com.example.offlinefirst.util.Resource.
- `app/src/main/java/com/example/offlinefirst/data/queue/OperationQueueManager.kt` — `Queue`: Queue se usa en el cuerpo del archivo pero no esta importado. El proyecto lo declara en com.example.offlinefirst.util.Queue.
- `app/src/main/java/com/example/offlinefirst/data/sync/SyncManager.kt` — `Sync`: Sync se usa en el cuerpo del archivo pero no esta importado. El proyecto lo declara en com.example.offlinefirst.util.Sync (hay mas de un tipo con ese nombre en el proyecto).
- `app/src/main/java/com/example/offlinefirst/data/sync/SyncManager.kt` — `Preferences`: Preferences se usa en el cuerpo del archivo pero no esta importado. El proyecto lo declara en com.example.offlinefirst.domain.model.Preferences (hay mas de un tipo con ese nombre en el proyecto).
- `app/src/main/java/com/example/offlinefirst/data/sync/SyncManager.kt` — `DownloadedContent`: DownloadedContent se usa en el cuerpo del archivo pero no esta importado. El proyecto lo declara en com.example.offlinefirst.domain.model.DownloadedContent.
- `app/src/main/java/com/example/offlinefirst/di/NetworkModule.kt` — `Content`: Content se usa en el cuerpo del archivo pero no esta importado. El proyecto lo declara en com.example.offlinefirst.util.Content.
- `app/src/main/java/com/example/offlinefirst/presentation/viewmodel/UserViewModel.kt` — `ErrorMessages`: ErrorMessages se usa en el cuerpo del archivo pero no esta importado. El proyecto lo declara en com.example.offlinefirst.util.ErrorMessages.
- `app/src/main/java/com/example/offlinefirst/presentation/viewmodel/UserViewModel.kt` — `Error`: Error se usa en el cuerpo del archivo pero no esta importado. El proyecto lo declara en com.example.offlinefirst.util.Error (hay mas de un tipo con ese nombre en el proyecto).
- `app/src/main/java/com/example/offlinefirst/presentation/ui/screens/UserScreen.kt` — `Sync`: Sync se usa en el cuerpo del archivo pero no esta importado. El proyecto lo declara en com.example.offlinefirst.util.Sync (hay mas de un tipo con ese nombre en el proyecto).
- `app/src/main/java/com/example/offlinefirst/presentation/ui/screens/UserScreen.kt` — `Preferences`: Preferences se usa en el cuerpo del archivo pero no esta importado. El proyecto lo declara en com.example.offlinefirst.domain.model.Preferences (hay mas de un tipo con ese nombre en el proyecto).
- `app/src/main/java/com/example/offlinefirst/presentation/ui/screens/UserScreen.kt` — `Content`: Content se usa en el cuerpo del archivo pero no esta importado. El proyecto lo declara en com.example.offlinefirst.util.Content.
- `app/src/main/java/com/example/offlinefirst/presentation/ui/screens/UserScreen.kt` — `Error`: Error se usa en el cuerpo del archivo pero no esta importado. El proyecto lo declara en com.example.offlinefirst.util.Error (hay mas de un tipo con ese nombre en el proyecto).
- `app/src/main/java/com/example/offlinefirst/data/sync/SyncWorker.kt` — `Error`: Error se usa en el cuerpo del archivo pero no esta importado. El proyecto lo declara en com.example.offlinefirst.util.Error (hay mas de un tipo con ese nombre en el proyecto).
- `app/src/main/java/com/example/offlinefirst/data/sync/SyncManager.kt` — `ApiService`: El import com.example.offlinefirst.data.remote.ApiService no se usa en ningun lado del cuerpo del archivo. Se puede eliminar.
- `app/src/main/java/com/example/offlinefirst/data/sync/SyncManager.kt` — `SyncStatus`: El import com.example.offlinefirst.domain.model.SyncStatus no se usa en ningun lado del cuerpo del archivo. Se puede eliminar.
- `app/src/main/java/com/example/offlinefirst/data/sync/SyncManager.kt` — `SyncRepository`: El import com.example.offlinefirst.domain.repository.SyncRepository no se usa en ningun lado del cuerpo del archivo. Se puede eliminar.
- `app/src/main/java/com/example/offlinefirst/di/NetworkModule.kt` — `DownloadedContent`: El import com.example.offlinefirst.domain.model.DownloadedContent no se usa en ningun lado del cuerpo del archivo. Se puede eliminar.
- `app/src/main/java/com/example/offlinefirst/presentation/viewmodel/UserViewModel.kt` — `UserValidationResult`: El import com.example.offlinefirst.domain.model.UserValidationResult no se usa en ningun lado del cuerpo del archivo. Se puede eliminar.
- `app/src/main/java/com/example/offlinefirst/domain/usecase/SyncDataUseCase.kt` — `UserRepository.getUsersBySyncStatus`: Se invoca `getUsersBySyncStatus` sobre `UserRepository`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `app/src/main/java/com/example/offlinefirst/domain/usecase/SyncDataUseCase.kt` — `UserRepository.syncUserToRemote`: Se invoca `syncUserToRemote` sobre `UserRepository`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `app/src/main/java/com/example/offlinefirst/domain/usecase/SyncDataUseCase.kt` — `PreferencesRepository.getPreferencesBySyncStatus`: Se invoca `getPreferencesBySyncStatus` sobre `PreferencesRepository`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `app/src/main/java/com/example/offlinefirst/domain/usecase/SyncDataUseCase.kt` — `PreferencesRepository.syncPreferenceToRemote`: Se invoca `syncPreferenceToRemote` sobre `PreferencesRepository`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `app/src/main/java/com/example/offlinefirst/domain/usecase/SyncDataUseCase.kt` — `ContentRepository.getContentBySyncStatus`: Se invoca `getContentBySyncStatus` sobre `ContentRepository`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `app/src/main/java/com/example/offlinefirst/domain/usecase/SyncDataUseCase.kt` — `ContentRepository.syncContentToRemote`: Se invoca `syncContentToRemote` sobre `ContentRepository`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `app/src/main/java/com/example/offlinefirst/domain/usecase/SyncDataUseCase.kt` — `UserRepository.deleteUserFromRemote`: Se invoca `deleteUserFromRemote` sobre `UserRepository`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `app/src/main/java/com/example/offlinefirst/data/repository/UserRepositoryImpl.kt` — `User.validate`: Se invoca `validate` sobre `User`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `app/src/main/java/com/example/offlinefirst/data/repository/UserRepositoryImpl.kt` — `User.toEntity`: Se invoca `toEntity` sobre `User`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `app/src/main/java/com/example/offlinefirst/data/repository/PreferencesRepositoryImpl.kt` — `Preferences.validate`: Se invoca `validate` sobre `Preferences`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `app/src/main/java/com/example/offlinefirst/data/repository/PreferencesRepositoryImpl.kt` — `Preferences.toEntity`: Se invoca `toEntity` sobre `Preferences`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `app/src/main/java/com/example/offlinefirst/data/repository/ContentRepositoryImpl.kt` — `DownloadedContent.toEntity`: Se invoca `toEntity` sobre `DownloadedContent`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `app/src/main/java/com/example/offlinefirst/data/repository/SyncRepositoryImpl.kt` — `SyncOperation.toEntity`: Se invoca `toEntity` sobre `SyncOperation`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `app/src/main/java/com/example/offlinefirst/data/queue/OperationQueueManager.kt` — `SyncOperation.toEntity`: Se invoca `toEntity` sobre `SyncOperation`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `app/src/main/java/com/example/offlinefirst/data/sync/SyncManager.kt` — `UserRepositoryImpl.getUserById`: Se invoca `getUserById` sobre `UserRepositoryImpl`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `app/src/main/java/com/example/offlinefirst/data/sync/SyncManager.kt` — `SyncRepositoryImpl.recordSync`: Se invoca `recordSync` sobre `SyncRepositoryImpl`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `app/src/main/java/com/example/offlinefirst/data/sync/ConflictResolver.kt` — `DownloadedContent.copy`: Se invoca `copy` sobre `DownloadedContent`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `app/src/main/java/com/example/offlinefirst/presentation/viewmodel/UserViewModel.kt` — `GetUserUseCase.getAllUsers`: Se invoca `getAllUsers` sobre `GetUserUseCase`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `app/src/main/java/com/example/offlinefirst/presentation/viewmodel/UserViewModel.kt` — `GetUserUseCase.getUserById`: Se invoca `getUserById` sobre `GetUserUseCase`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `app/src/main/java/com/example/offlinefirst/presentation/viewmodel/UserViewModel.kt` — `User.validate`: Se invoca `validate` sobre `User`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `app/src/main/java/com/example/offlinefirst/presentation/viewmodel/UserViewModel.kt` — `SaveUserUseCase.saveUser`: Se invoca `saveUser` sobre `SaveUserUseCase`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `app/src/main/java/com/example/offlinefirst/presentation/viewmodel/UserViewModel.kt` — `SaveUserUseCase.deleteUser`: Se invoca `deleteUser` sobre `SaveUserUseCase`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `app/src/main/java/com/example/offlinefirst/presentation/viewmodel/UserViewModel.kt` — `SyncDataUseCase.syncAll`: Se invoca `syncAll` sobre `SyncDataUseCase`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `app/src/main/java/com/example/offlinefirst/data/sync/SyncWorker.kt` — `NetworkMonitor.isNetworkAvailable`: Se invoca `isNetworkAvailable` sobre `NetworkMonitor`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `app/src/main/java/com/example/offlinefirst/data/sync/SyncWorker.kt` — `UserRepository.getPendingUsers`: Se invoca `getPendingUsers` sobre `UserRepository`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `app/src/main/java/com/example/offlinefirst/data/sync/SyncWorker.kt` — `PreferencesRepository.getPendingPreferences`: Se invoca `getPendingPreferences` sobre `PreferencesRepository`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `app/src/main/java/com/example/offlinefirst/data/sync/SyncWorker.kt` — `ContentRepository.getPendingContents`: Se invoca `getPendingContents` sobre `ContentRepository`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `app/src/main/java/com/example/offlinefirst/data/sync/SyncWorker.kt` — `SyncRepository.updateSyncStatus`: Se invoca `updateSyncStatus` sobre `SyncRepository`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `app/src/main/java/com/example/offlinefirst/data/sync/SyncWorker.kt` — `SyncManager.syncUser`: Se invoca `syncUser` sobre `SyncManager`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `app/src/main/java/com/example/offlinefirst/data/sync/SyncWorker.kt` — `UserRepository.markAsSynced`: Se invoca `markAsSynced` sobre `UserRepository`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `app/src/main/java/com/example/offlinefirst/data/sync/SyncWorker.kt` — `UserRepository.markAsError`: Se invoca `markAsError` sobre `UserRepository`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `app/src/main/java/com/example/offlinefirst/data/sync/SyncWorker.kt` — `SyncManager.syncPreference`: Se invoca `syncPreference` sobre `SyncManager`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `app/src/main/java/com/example/offlinefirst/data/sync/SyncWorker.kt` — `PreferencesRepository.markAsSynced`: Se invoca `markAsSynced` sobre `PreferencesRepository`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `app/src/main/java/com/example/offlinefirst/data/sync/SyncWorker.kt` — `PreferencesRepository.markAsError`: Se invoca `markAsError` sobre `PreferencesRepository`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `app/src/main/java/com/example/offlinefirst/data/sync/SyncWorker.kt` — `SyncManager.syncContent`: Se invoca `syncContent` sobre `SyncManager`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `app/src/main/java/com/example/offlinefirst/data/sync/SyncWorker.kt` — `ContentRepository.markAsSynced`: Se invoca `markAsSynced` sobre `ContentRepository`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `app/src/main/java/com/example/offlinefirst/data/sync/SyncWorker.kt` — `ContentRepository.markAsError`: Se invoca `markAsError` sobre `ContentRepository`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `app/src/main/java/com/example/offlinefirst/data/sync/SyncWorker.kt` — `SyncRepository.recordSyncOperation`: Se invoca `recordSyncOperation` sobre `SyncRepository`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.

## Como saber que terminaste

```bash
./gradlew assembleDebug
```

Ese comando corriendo sin errores es la definicion de "listo".

---

```
## Briefing del reto (autoridad)
Este bloque manda sobre los archivos adjuntos. El stack y el rol salen de AQUÍ, no de un topic genérico ni de markdown placeholder.

### Perfil
Chapter Movil, Especialidad Android, Tecnología Android, Senior

### Brecha de conocimiento
Necesita fortalecer la practica de Android

### Misión / candidato
Liderar la iniciativa de persistencia local offline-first

### Reto
- Tema: Persistencia local offline-first
- Seniority: senior-l2
- Tipo: practical
- Título: Implementación de persistencia local offline-first en Android
- Tiempo estimado: 2 semanas

### Fases (trabajo del HUMANO — PROHIBIDO completarlas)
No implementes estos entregables. Dejalos como hueco pedagógico. El asistente solo materializa el proyecto arrancable para que el participante pueda trabajar.
- Fase 1: Definición de requisitos y diseño del modelo de datos — objetivo: Establecer los requisitos funcionales y no funcionales para la persistencia local y diseñar el modelo de datos. — entregable (NO resolver): Documento de diseño que describe el modelo de datos, las reglas de validación y los requisitos de rendimiento.
- Fase 2: Implementación de la capa de persistencia — objetivo: Implementar la capa de persistencia local utilizando el modelo de datos diseñado. — entregable (NO resolver): Capa de persistencia local implementada y verificada.
- Fase 3: Integración y sincronización con servicio en la nube — objetivo: Integrar la persistencia local con el servicio de sincronización en la nube y asegurar la sincronización automática de datos. — entregable (NO resolver): Solución de persistencia local integrada con el servicio de sincronización en la nube y sincronización automática de datos implementada.

Eres un asistente experto en análisis, corrección y generación de archivos de cualquier tipo:
código fuente, documentación, hojas de cálculo, documentos Word, configuraciones, entre otros.
Voy a enviarte una cadena de texto que contiene uno o más archivos. Cada archivo está delimitado por un marcador con el siguiente formato:
// === ARCHIVO: ruta/del/archivo.extension ===
o también puede aparecer como:
## === ARCHIVO: ruta/del/archivo.extension ===
Lo que sigue al marcador puede ser:

El contenido real del archivo (código, texto, YAML, etc.)
Una descripción en lenguaje natural de lo que debe contener el archivo


TU TAREA
PASO 0 — ¿Esto es un proyecto o una carcasa?
Antes de extraer archivos, leé el Briefing (si está) y diagnosticá el adjunto.

Es CARCASA si ocurre CUALQUIERA de estas:
- No hay manifiesto de dependencias del stack del briefing (manifest.json de VTEX IO / package.json / pom.xml / build.gradle / requirements.txt / go.mod / *.tf / *.csproj, según corresponda)
- Hay un "binario" que en realidad es un comentario ("no puede ser mostrado como texto plano", placeholder .fig/.docx vacío)
- Los markdowns ya completan entregables de fases posteriores ("se implementó fade-in", lista de áreas ya resuelta)

Si es CARCASA:
- MATERIALIZÁ un proyecto que arranca en el stack del briefing (VTEX IO Store Framework, Angular, Terraform, pytest, Nest, etc.). Incluí manifiesto, punto de entrada y capa de interfaz reales.
- NO copies los markdowns de "solución" como si fueran el producto. Son ruido de generación.
- NO resuelvas las fases del briefing (están marcadas PROHIBIDO). Dejá el hueco pedagógico: el flujo existe, las microinteracciones/calidad/infra que el reto pide NO están hechas.
- Después seguí al PASO 5 (ZIP).

Si es un proyecto REAL (manifiesto + código que compila o arranca):
- Seguí PASO 1 en adelante. 🔴 compilación sí. 🟡 pedagógico no.

PASO 1 — Detección y extracción
Identifica todos los archivos presentes en la cadena. Para cada archivo extrae:

Su ruta completa (ej: src/main/java/com/pragma/Service.java)
Su contenido o descripción

PASO 2 — Clasificación por tipo
Clasifica cada archivo en una de estas categorías:
A) Código fuente (Java, Python, TypeScript, JavaScript, Kotlin, etc.)
B) Configuración / documentación (YAML, properties, Markdown, JSON, txt, etc.)
C) Excel (.xlsx, .xls, .csv)
D) Word (.docx, .doc)
E) Otro tipo de archivo binario o especial
PASO 3 — Clasificación de errores en código fuente

Objetivo prioritario: que el proyecto compile. No corrijas flujo de negocio ni lógica funcional.

Antes de modificar cualquier archivo de código fuente, clasifica cada problema encontrado en una de estas dos categorías:
🔴 ERROR DE COMPILACIÓN — corregir siempre
Son errores que impiden que el proyecto arranque, sin valor pedagógico:

Import faltante o incorrecto
Clase, método o variable referenciada que no existe en ningún archivo del proyecto
Error de sintaxis
Anotación con atributos inválidos
Dependencia ausente en pom.xml, package.json, etc.
Archivo referenciado que no existe y debe ser creado con implementación mínima

→ CORREGIR estos errores.
🟡 PROBLEMA FUNCIONAL O DE CALIDAD — preservar siempre
Son problemas que no impiden compilar. Pueden ser intencionales para el aprendizaje:

Clave secreta hardcodeada ("secret", "password123")
API deprecada que funciona pero tiene reemplazo moderno
Lógica de negocio incorrecta o incompleta
Código redundante o de baja legibilidad
Falta de validaciones en flujo de negocio
Patrones de diseño incorrectos pero funcionales
Concurrencia no segura
Configuración funcional pero no óptima

→ PRESERVAR tal cual. No corregir, no mejorar, no comentar.
PASO 4 — Procesamiento según tipo de archivo
Tipo A — Código fuente
Aplica únicamente las correcciones clasificadas como 🔴 ERROR DE COMPILACIÓN.
No alteres ningún elemento clasificado como 🟡 PROBLEMA FUNCIONAL O DE CALIDAD.
Si falta un archivo referenciado, créalo con la implementación mínima necesaria para compilar.
Tipo B — Configuración / documentación
Extrae el contenido tal cual, sin modificaciones salvo errores evidentes de sintaxis
(ej: YAML mal indentado).
Tipo C — Excel (.xlsx)
Si viene con contenido real, genera el archivo respetando ese contenido.
Si viene con descripción en lenguaje natural, genera un archivo Excel funcional con:

Fila de encabezados en negrita con color de fondo distintivo
Columnas con ancho ajustado al contenido
Tipos de dato correctos por columna
Validaciones si la descripción lo indica
Hojas nombradas descriptivamente si hay más de una
Filas de ejemplo si no hay datos reales

Tipo D — Word (.docx)
Si viene con contenido real, genera el archivo respetando ese contenido.
Si viene con descripción en lenguaje natural, genera un documento Word funcional con:

Estilos de título (Título 1, Título 2) para jerarquía de secciones
Fuente legible (Calibri o equivalente), tamaño 11-12pt para cuerpo
Márgenes estándar
Tabla de contenido si tiene múltiples secciones
Tablas con encabezados en negrita si aplica

Tipo E — Otro
Genera el archivo con el contenido o estructura más apropiada según la descripción.
PASO 5 — Exportación en ZIP
Empaqueta todos los archivos en un único archivo ZIP descargable respetando exactamente
la estructura de rutas indicada por los marcadores.
El ZIP debe incluir:

Archivos de código con únicamente los errores de compilación corregidos
Archivos de configuración y documentación sin cambios
Archivos nuevos creados para resolver dependencias de compilación faltantes
Archivos Excel y Word generados desde descripción

IMPORTANTE: El ZIP debe estar listo para descargar al finalizar. No preguntes si el usuario
quiere generarlo. Simplemente genera el archivo y proporciona el enlace de descarga; No debes desplegar en el chat el resumen de lo que arreglaste al Zip, solo entregalo.

REGLAS IMPORTANTES

No omitas ningún archivo aunque no tenga errores ni modificaciones
Respeta los nombres y rutas exactas indicadas por los marcadores
Si un archivo no tiene marcador claro, infiere el nombre desde su contenido
Si la cadena contiene solo documentación, placeholders o binarios fake, NO la reproduzcas:
aplicá PASO 0 (materializar el proyecto del briefing). Reproducir la carcasa es un fallo.
No agregues texto después del enlace de descarga del ZIP
No preguntes si el usuario quiere el ZIP: simplemente generalo siempre
Si detectas que falta un archivo de configuración necesario para compilar
(pom.xml, package.json, requirements.txt, build.gradle, etc.), créalo e inclúyelo
inferiendo su contenido desde los imports y frameworks detectados en el código
Nunca corrijas problemas 🟡 aunque parezcan obvios o fáciles de mejorar.
El participante que recibirá este proyecto los debe encontrar y resolver él mismo.


INPUT
Aquí está la cadena con los archivos:

// === ARCHIVO: build.gradle.kts ===
plugins {
    id("com.android.application") version "8.7.3" apply false
    id("org.jetbrains.kotlin.android") version "2.1.0" apply false
    id("com.google.dagger.hilt.android") version "2.51.1" apply false
    id("com.google.devtools.ksp") version "2.1.0-1.0.28" apply false
    id("androidx.hilt.hilt-gradle-plugins") version "1.2.0" apply false
}

dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "OfflineFirstApp"
include(":app")

tasks.register("clean", Delete::class) {
    delete(rootProject.layout.buildDirectory)
}

gradle.beforeProject {
    if (this.name == "app") {
        this.extra.apply {
            set("compose_version", "2024.12.01")
            set("kotlin_version", "2.1.0")
            set("room_version", "2.6.1")
            set("hilt_version", "2.51.1")
            set("coroutines_version", "1.8.1")
            set("retrofit_version", "2.11.0")
            set("moshi_version", "1.15.1")
            set("work_version", "2.9.1")
            set("lifecycle_version", "2.8.7")
            set("navigation_version", "2.8.5")
        }
    }
}

allprojects {
    tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile>().configureEach {
        kotlinOptions {
            jvmTarget = "21"
            freeCompilerArgs += listOf(
                "-opt-in=kotlin.RequiresOptIn",
                "-opt-in=kotlin.ExperimentalStdlibApi"
            )
        }
    }
}

subprojects {
    afterEvaluate {
        if (plugins.hasPlugin("com.android.application") || plugins.hasPlugin("com.android.library")) {
            extensions.configure<com.android.build.gradle.BaseExtension> {
                compileOptions {
                    sourceCompatibility = JavaVersion.VERSION_21
                    targetCompatibility = JavaVersion.VERSION_21
                }
                kotlinOptions {
                    jvmTarget = "21"
                }
            }
        }
    }
}

buildscript {
    extra.apply {
        set("compose_version", "2024.12.01")
    }
}

tasks.register("dependencies", org.gradle.api.tasks.diagnostics.DependencyReportTask::class) {
    configuration = "releaseRuntimeClasspath"
}
// === ARCHIVO: app/build.gradle.kts ===
plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("com.google.dagger.hilt.android")
    id("com.google.devtools.ksp")
    id("androidx.hilt.hilt-gradle-plugins")
    kotlin("kapt")
}

android {
    namespace = "com.example.offlinefirst"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.example.offlinefirst"
        minSdk = 26
        targetSdk = 35
        versionCode = 1
        versionName = "1.0.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }

        ksp {
            arg("room.schemaLocation", "$projectDir/schemas")
            arg("room.incremental", "true")
            arg("room.generateKotlin", "true")
        }
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
        debug {
            isDebuggable = true
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_21
        targetCompatibility = JavaVersion.VERSION_21
    }

    kotlinOptions {
        jvmTarget = "21"
        freeCompilerArgs += listOf(
            "-opt-in=kotlin.RequiresOptIn",
            "-opt-in=kotlin.ExperimentalStdlibApi",
            "-opt-in=androidx.compose.material3.ExperimentalMaterial3Api"
        )
    }

    buildFeatures {
        compose = true
        buildConfig = true
    }

    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.15"
    }

    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {
    val roomVersion = rootProject.extra.get("room_version") as String
    val hiltVersion = rootProject.extra.get("hilt_version") as String
    val coroutinesVersion = rootProject.extra.get("coroutines_version") as String
    val retrofitVersion = rootProject.extra.get("retrofit_version") as String
    val moshiVersion = rootProject.extra.get("moshi_version") as String
    val workVersion = rootProject.extra.get("work_version") as String
    val lifecycleVersion = rootProject.extra.get("lifecycle_version") as String
    val navigationVersion = rootProject.extra.get("navigation_version") as String
    val composeVersion = rootProject.extra.get("compose_version") as String

    implementation("androidx.room:room-runtime:$roomVersion")
    implementation("androidx.room:room-ktx:$roomVersion")
    ksp("androidx.room:room-compiler:$roomVersion")

    implementation("com.google.dagger:hilt-android:$hiltVersion")
    kapt("com.google.dagger:hilt-android-compiler:$hiltVersion")

    implementation("androidx.hilt:hilt-work:1.2.0")
    kapt("androidx.hilt:hilt-compiler:1.2.0")

    implementation("androidx.work:work-runtime-ktx:$workVersion")

    implementation("com.squareup.retrofit2:retrofit:$retrofitVersion")
    implementation("com.squareup.retrofit2:converter-moshi:$retrofitVersion")
    implementation("com.squareup.moshi:moshi-kotlin:$moshiVersion")
    ksp("com.squareup.moshi:moshi-kotlin-codegen:$moshiVersion")

    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:$coroutinesVersion")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:$coroutinesVersion")

    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:$lifecycleVersion")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:$lifecycleVersion")
    implementation("androidx.lifecycle:lifecycle-viewmodel-compose:$lifecycleVersion")

    implementation(platform("androidx.compose:compose-bom:$composeVersion"))
    implementation("androidx.compose.ui:ui")
    implementation("androidx.compose.ui:ui-graphics")
    implementation("androidx.compose.ui:ui-tooling-preview")
    implementation("androidx.compose.material3:material3")
    implementation("androidx.activity:activity-compose:1.9.3")

    implementation("androidx.navigation:navigation-compose:$navigationVersion")

    implementation("androidx.core:core-ktx:1.15.0")

    testImplementation("junit:junit:4.13.2")
    testImplementation("org.jetbrains.kotlinx:kotlinx-coroutines-test:$coroutinesVersion")
    androidTestImplementation("androidx.test.ext:junit:1.2.1")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.6.1")
    androidTestImplementation(platform("androidx.compose:compose-bom:$composeVersion"))
    androidTestImplementation("androidx.compose.ui:ui-test-junit4")
    debugImplementation("androidx.compose.ui:ui-tooling")
    debugImplementation("androidx.compose.ui:ui-test-manifest")
}

kapt {
    correctErrorTypes = true
    arguments {
        arg("dagger.fastInit", "enabled")
    }
}

tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile>().configureEach {
    kotlinOptions {
        jvmTarget = "21"
    }
}
// === ARCHIVO: app/src/main/java/com/example/offlinefirst/OfflineFirstApplication.kt ===
package com.example.offlinefirst

import android.app.Application
import android.util.Log
import androidx.hilt.work.HiltWorkerFactory
import androidx.work.Configuration
import androidx.work.Constraints
import androidx.work.ExistingPeriodicWorkPolicy
import androidx.work.NetworkType
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkManager
import com.example.offlinefirst.data.sync.SyncWorker
import dagger.hilt.android.HiltAndroidApp
import java.util.concurrent.TimeUnit
import javax.inject.Inject

@HiltAndroidApp
class OfflineFirstApplication : Application(), Configuration.Provider {

    @Inject
    lateinit var workerFactory: HiltWorkerFactory

    companion object {
        private const val TAG = "OfflineFirstApp"
        private const val SYNC_WORK_NAME = "periodic_sync_work"
        private const val SYNC_INTERVAL_MINUTES = 5L
    }

    override val workManagerConfiguration: Configuration
        get() = Configuration.Builder()
            .setWorkerFactory(workerFactory)
            .setMinimumLoggingLevel(Log.INFO)
            .build()

    override fun onCreate() {
        super.onCreate()
        initializeWorkManager()
        initializeGlobalErrorHandling()
        Log.i(TAG, "OfflineFirstApplication initialized successfully")
    }

    private fun initializeWorkManager() {
        val constraints = Constraints.Builder()
            .setRequiredNetworkType(NetworkType.CONNECTED)
            .setRequiresBatteryNotLow(true)
            .build()

        val syncWorkRequest = PeriodicWorkRequestBuilder<SyncWorker>(
            SYNC_INTERVAL_MINUTES, TimeUnit.MINUTES
        )
            .setConstraints(constraints)
            .addTag(SYNC_WORK_NAME)
            .build()

        WorkManager.getInstance(this).enqueueUniquePeriodicWork(
            SYNC_WORK_NAME,
            ExistingPeriodicWorkPolicy.KEEP,
            syncWorkRequest
        )

        Log.d(TAG, "Periodic sync work scheduled every $SYNC_INTERVAL_MINUTES minutes")
    }

    private fun initializeGlobalErrorHandling() {
        val defaultHandler = Thread.getDefaultUncaughtExceptionHandler()
        Thread.setDefaultUncaughtExceptionHandler { thread, throwable ->
            Log.e(TAG, "Uncaught exception on thread ${thread.name}", throwable)
            defaultHandler?.uncaughtException(thread, throwable)
        }
    }

    override fun onTerminate() {
        super.onTerminate()
        Log.i(TAG, "Application terminating, cleaning up resources")
    }

    override fun onLowMemory() {
        super.onLowMemory()
        Log.w(TAG, "System is low on memory, clearing caches")
    }

    override fun onTrimMemory(level: Int) {
        super.onTrimMemory(level)
        when (level) {
            TRIM_MEMORY_RUNNING_LOW -> Log.d(TAG, "Memory running low, but app is running")
            TRIM_MEMORY_RUNNING_CRITICAL -> Log.w(TAG, "Memory critical, app may be killed")
            else -> Log.d(TAG, "Trim memory level: $level")
        }
    }
}
// === ARCHIVO: app/src/main/java/com/example/offlinefirst/domain/model/User.kt ===
package com.example.offlinefirst.domain.model

import java.util.UUID

data class User(
    val id: String = UUID.randomUUID().toString(),
    val username: String,
    val email: String,
    val displayName: String,
    val avatarUrl: String? = null,
    val phoneNumber: String? = null,
    val isEmailVerified: Boolean = false,
    val isPhoneVerified: Boolean = false,
    val createdAt: Long = System.currentTimeMillis(),
    val updatedAt: Long = System.currentTimeMillis(),
    val lastSyncAt: Long? = null,
    val syncStatus: SyncStatus = SyncStatus.PENDING
) {
    init {
        require(username.isNotBlank()) { "Username cannot be blank" }
        require(email.isNotBlank()) { "Email cannot be blank" }
        require(email.contains("@")) { "Email must be valid" }
        require(displayName.isNotBlank()) { "Display name cannot be blank" }
        require(id.isNotBlank()) { "User ID cannot be blank" }
    }

    fun isSynced(): Boolean = syncStatus == SyncStatus.SYNCED

    fun hasPendingChanges(): Boolean = syncStatus == SyncStatus.PENDING || syncStatus == SyncStatus.CONFLICT

    fun needsSync(): Boolean = lastSyncAt == null || updatedAt > (lastSyncAt ?: 0L)

    fun withUpdatedTimestamp(): User = copy(updatedAt = System.currentTimeMillis())

    fun markAsSynced(): User = copy(
        syncStatus = SyncStatus.SYNCED,
        lastSyncAt = System.currentTimeMillis()
    )

    fun markAsPending(): User = copy(syncStatus = SyncStatus.PENDING)

    fun markAsConflict(): User = copy(syncStatus = SyncStatus.CONFLICT)

    fun withEmailVerification(verified: Boolean): User = copy(
        isEmailVerified = verified,
        updatedAt = System.currentTimeMillis()
    )

    fun withPhoneVerification(verified: Boolean): User = copy(
        isPhoneVerified = verified,
        updatedAt = System.currentTimeMillis()
    )

    fun withAvatar(url: String?): User = copy(
        avatarUrl = url,
        updatedAt = System.currentTimeMillis()
    )

    fun withDisplayName(name: String): User = require(name.isNotBlank()) {
        "Display name cannot be blank"
    }.let { copy(displayName = name, updatedAt = System.currentTimeMillis()) }

    fun withEmail(email: String): User = require(email.contains("@")) {
        "Email must be valid"
    }.let { copy(email = email, updatedAt = System.currentTimeMillis()) }

    fun withPhone(phone: String?): User = copy(
        phoneNumber = phone,
        updatedAt = System.currentTimeMillis()
    )

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is User) return false
        return id == other.id
    }

    override fun hashCode(): Int = id.hashCode()

    override fun toString(): String = "User(id=$id, username=$username, email=$email, displayName=$displayName)"
}

enum class SyncStatus {
    PENDING,
    SYNCING,
    SYNCED,
    CONFLICT,
    ERROR;

    fun isPending(): Boolean = this == PENDING
    fun isSyncing(): Boolean = this == SYNCING
    fun isSynced(): Boolean = this == SYNCED
    fun hasConflict(): Boolean = this == CONFLICT
    fun hasError(): Boolean = this == ERROR

    fun canSync(): Boolean = this == PENDING || this == ERROR
}

data class UserProfile(
    val user: User,
    val preferences: Preferences? = null,
    val downloadedContents: List<DownloadedContent> = emptyList()
)

data class UserCredentials(
    val email: String,
    val password: String
) {
    init {
        require(email.isNotBlank()) { "Email cannot be blank" }
        require(password.length >= 8) { "Password must be at least 8 characters" }
    }

    fun isValid(): Boolean = email.contains("@") && password.length >= 8
}

sealed class UserValidationResult {
    data object Valid : UserValidationResult()
    data class Invalid(val errors: List<String>) : UserValidationResult()

    fun isValid(): Boolean = this is Valid
    fun getErrors(): List<String> = (this as? Invalid)?.errors ?: emptyList()
}

fun User.validate(): UserValidationResult {
    val errors = mutableListOf<String>()
    if (username.isBlank()) errors.add("Username cannot be blank")
    if (email.isBlank()) errors.add("Email cannot be blank")
    if (!email.contains("@")) errors.add("Email must be valid")
    if (displayName.isBlank()) errors.add("Display name cannot be blank")
    return if (errors.isEmpty()) UserValidationResult.Valid
    else UserValidationResult.Invalid(errors)
}
// === ARCHIVO: app/src/main/java/com/example/offlinefirst/domain/model/Preferences.kt ===
package com.example.offlinefirst.domain.model

import java.util.UUID

data class Preferences(
    val id: String = UUID.randomUUID().toString(),
    val key: String,
    val value: String,
    val category: PreferenceCategory = PreferenceCategory.GENERAL,
    val dataType: PreferenceDataType = PreferenceDataType.STRING,
    val isEncrypted: Boolean = false,
    val isGlobal: Boolean = false,
    val userId: String? = null,
    val createdAt: Long = System.currentTimeMillis(),
    val updatedAt: Long = System.currentTimeMillis(),
    val lastSyncAt: Long? = null,
    val syncStatus: SyncStatus = SyncStatus.PENDING
) {
    init {
        require(key.isNotBlank()) { "Preference key cannot be blank" }
        require(value.isNotBlank()) { "Preference value cannot be blank" }
        require(id.isNotBlank()) { "Preference ID cannot be blank" }
    }

    fun isSynced(): Boolean = syncStatus == SyncStatus.SYNCED

    fun hasPendingChanges(): Boolean = syncStatus == SyncStatus.PENDING || syncStatus == SyncStatus.CONFLICT

    fun needsSync(): Boolean = lastSyncAt == null || updatedAt > (lastSyncAt ?: 0L)

    fun withUpdatedTimestamp(): Preferences = copy(updatedAt = System.currentTimeMillis())

    fun markAsSynced(): Preferences = copy(
        syncStatus = SyncStatus.SYNCED,
        lastSyncAt = System.currentTimeMillis()
    )

    fun markAsPending(): Preferences = copy(syncStatus = SyncStatus.PENDING)

    fun markAsConflict(): Preferences = copy(syncStatus = SyncStatus.CONFLICT)

    fun getTypedValue(): Any? = when (dataType) {
        PreferenceDataType.STRING -> value
        PreferenceDataType.INTEGER -> value.toIntOrNull()
        PreferenceDataType.LONG -> value.toLongOrNull()
        PreferenceDataType.FLOAT -> value.toFloatOrNull()
        PreferenceDataType.DOUBLE -> value.toDoubleOrNull()
        PreferenceDataType.BOOLEAN -> value.toBooleanStrictOrNull()
        PreferenceDataType.JSON -> value
    }

    fun withValue(newValue: String): Preferences = copy(
        value = newValue,
        updatedAt = System.currentTimeMillis(),
        syncStatus = SyncStatus.PENDING
    )

    fun withCategory(category: PreferenceCategory): Preferences = copy(
        category = category,
        updatedAt = System.currentTimeMillis()
    )

    fun withEncryption(encrypted: Boolean): Preferences = copy(
        isEncrypted = encrypted,
        updatedAt = System.currentTimeMillis()
    )

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is Preferences) return false
        return id == other.id
    }

    override fun hashCode(): Int = id.hashCode()

    override fun toString(): String = "Preferences(id=$id, key=$key, category=$category)"
}

enum class PreferenceCategory {
    GENERAL,
    NOTIFICATIONS,
    PRIVACY,
    DISPLAY,
    SYNC,
    NETWORK,
    SECURITY,
    THEME;

    fun isValidCategory(): Boolean = entries.contains(this)
}

enum class PreferenceDataType {
    STRING,
    INTEGER,
    LONG,
    FLOAT,
    DOUBLE,
    BOOLEAN,
    JSON;

    fun isNumeric(): Boolean = this in listOf(INTEGER, LONG, FLOAT, DOUBLE)
    fun isBoolean(): Boolean = this == BOOLEAN
    fun isString(): Boolean = this == STRING || this == JSON
}

data class PreferenceGroup(
    val category: PreferenceCategory,
    val preferences: List<Preferences>
) {
    fun hasPendingChanges(): Boolean = preferences.any { it.hasPendingChanges() }
    fun getPendingCount(): Int = preferences.count { it.hasPendingChanges() }
    fun getSyncedCount(): Int = preferences.count { it.isSynced() }
}

sealed class PreferenceValidationResult {
    data object Valid : PreferenceValidationResult()
    data class Invalid(val errors: List<String>) : PreferenceValidationResult()

    fun isValid(): Boolean = this is Valid
    fun getErrors(): List<String> = (this as? Invalid)?.errors ?: emptyList()
}

fun Preferences.validate(): PreferenceValidationResult {
    val errors = mutableListOf<String>()
    if (key.isBlank()) errors.add("Preference key cannot be blank")
    if (value.isBlank()) errors.add("Preference value cannot be blank")
    if (!isGlobal && userId.isNullOrBlank()) {
        errors.add("User-specific preferences must have a user ID")
    }
    return if (errors.isEmpty()) PreferenceValidationResult.Valid
    else PreferenceValidationResult.Invalid(errors)
}

object DefaultPreferences {
    const val KEY_DARK_MODE = "dark_mode"
    const val KEY_NOTIFICATIONS_ENABLED = "notifications_enabled"
    const val KEY_SYNC_WIFI_ONLY = "sync_wifi_only"
    const val KEY_SYNC_INTERVAL = "sync_interval"
    const val KEY_LAST_SYNC = "last_sync_timestamp"
    const val KEY_OFFLINE_MODE = "offline_mode"
    const val KEY_CACHE_SIZE = "cache_size"
    const val KEY_LANGUAGE = "language"

    val defaults = mapOf(
        KEY_DARK_MODE to "false",
        KEY_NOTIFICATIONS_ENABLED to "true",
        KEY_SYNC_WIFI_ONLY to "true",
        KEY_SYNC_INTERVAL to "5",
        KEY_OFFLINE_MODE to "false",
        KEY_CACHE_SIZE to "100",
        KEY_LANGUAGE to "en"
    )
}
// === ARCHIVO: app/src/main/java/com/example/offlinefirst/domain/model/DownloadedContent.kt ===
package com.example.offlinefirst.domain.model

import java.util.UUID

data class DownloadedContent(
    val id: String = UUID.randomUUID().toString(),
    val title: String,
    val description: String? = null,
    val url: String,
    val localPath: String? = null,
    val mimeType: String = "application/octet-stream",
    val fileSize: Long = 0L,
    val downloadedSize: Long = 0L,
    val checksum: String? = null,
    val category: ContentCategory = ContentCategory.OTHER,
    val tags: List<String> = emptyList(),
    val thumbnailUrl: String? = null,
    val metadata: Map<String, String> = emptyMap(),
    val isAvailableOffline: Boolean = true,
    val expiresAt: Long? = null,
    val downloadedAt: Long = System.currentTimeMillis(),
    val accessedAt: Long = System.currentTimeMillis(),
    val accessCount: Int = 0,
    val lastSyncAt: Long? = null,
    val syncStatus: SyncStatus = SyncStatus.PENDING,
    val userId: String? = null
) {
    init {
        require(title.isNotBlank()) { "Content title cannot be blank" }
        require(url.isNotBlank()) { "Content URL cannot be blank" }
        require(id.isNotBlank()) { "Content ID cannot be blank" }
        require(downloadedSize <= fileSize) { "Downloaded size cannot exceed file size" }
    }

    fun isSynced(): Boolean = syncStatus == SyncStatus.SYNCED

    fun hasPendingChanges(): Boolean = syncStatus == SyncStatus.PENDING || syncStatus == SyncStatus.CONFLICT

    fun needsSync(): Boolean = lastSyncAt == null || updatedAt > (lastSyncAt ?: 0L)

    private val updatedAt: Long get() = maxOf(downloadedAt, accessedAt)

    fun isDownloaded(): Boolean = localPath != null && downloadedSize == fileSize

    fun isDownloading(): Boolean = downloadedSize > 0 && downloadedSize < fileSize

    fun isExpired(): Boolean = expiresAt != null && System.currentTimeMillis() > expiresAt

    fun isAvailable(): Boolean = isDownloaded() && !isExpired() && isAvailableOffline

    fun getDownloadProgress(): Float = if (fileSize > 0) downloadedSize.toFloat() / fileSize else 0f

    fun getProgressPercentage(): Int = (getDownloadProgress() * 100).toInt()

    fun withUpdatedTimestamp(): DownloadedContent = copy(accessedAt = System.currentTimeMillis())

    fun markAsSynced(): DownloadedContent = copy(
        syncStatus = SyncStatus.SYNCED,
        lastSyncAt = System.currentTimeMillis()
    )

    fun markAsPending(): DownloadedContent = copy(syncStatus = SyncStatus.PENDING)

    fun markAsConflict(): DownloadedContent = copy(syncStatus = SyncStatus.CONFLICT)

    fun markAsDownloading(progress: Long): DownloadedContent = copy(
        downloadedSize = progress,
        syncStatus = SyncStatus.SYNCING
    )

    fun markAsDownloaded(path: String, size: Long, hash: String?): DownloadedContent = copy(
        localPath = path,
        downloadedSize = size,
        fileSize = size,
        checksum = hash,
        syncStatus = SyncStatus.PENDING
    )

    fun incrementAccessCount(): DownloadedContent = copy(
        accessCount = accessCount + 1,
        accessedAt = System.currentTimeMillis()
    )

    fun withLocalPath(path: String): DownloadedContent = copy(localPath = path)

    fun withExpiration(expiresAt: Long?): DownloadedContent = copy(expiresAt = expiresAt)

    fun withCategory(category: ContentCategory): DownloadedContent = copy(category = category)

    fun withTags(newTags: List<String>): DownloadedContent = copy(tags = newTags)

    fun addTag(tag: String): DownloadedContent = copy(tags = tags + tag)

    fun removeTag(tag: String): DownloadedContent = copy(tags = tags - tag)

    fun withMetadata(additionalMetadata: Map<String, String>): DownloadedContent = copy(
        metadata = metadata + additionalMetadata
    )

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is DownloadedContent) return false
        return id == other.id
    }

    override fun hashCode(): Int = id.hashCode()

    override fun toString(): String = "DownloadedContent(id=$id, title=$title, status=$syncStatus)"
}

enum class ContentCategory {
    IMAGE,
    VIDEO,
    AUDIO,
    DOCUMENT,
    ARCHIVE,
    OTHER;

    fun isMedia(): Boolean = this in listOf(IMAGE, VIDEO, AUDIO)
    fun isDocument(): Boolean = this == DOCUMENT
    fun isArchive(): Boolean = this == ARCHIVE
}

data class ContentBatch(
    val contents: List<DownloadedContent>,
    val totalSize: Long = contents.sumOf { it.fileSize },
    val downloadedSize: Long = contents.sumOf { it.downloadedSize }
) {
    fun getTotalProgress(): Float = if (totalSize > 0) downloadedSize.toFloat() / totalSize else 0f

    fun getAllSynced(): Boolean = contents.all { it.isSynced() }

    fun getAllDownloaded(): Boolean = contents.all { it.isDownloaded() }

    fun getPendingCount(): Int = contents.count { it.hasPendingChanges() }

    fun getExpiredContents(): List<DownloadedContent> = contents.filter { it.isExpired() }

    fun getAvailableContents(): List<DownloadedContent> = contents.filter { it.isAvailable() }
}

sealed class ContentValidationResult {
    data object Valid : ContentValidationResult()
    data class Invalid(val errors: List<String>) : ContentValidationResult()

    fun isValid(): Boolean = this is Valid
    fun getErrors(): List<String> = (this as? Invalid)?.errors ?: emptyList()
}

fun DownloadedContent.validate(): ContentValidationResult {
    val errors = mutableListOf<String>()
    if (title.isBlank()) errors.add("Content title cannot be blank")
    if (url.isBlank()) errors.add("Content URL cannot be blank")
    if (fileSize < 0) errors.add("File size cannot be negative")
    if (downloadedSize < 0) errors.add("Downloaded size cannot be negative")
    if (downloadedSize > fileSize) errors.add("Downloaded size cannot exceed file size")
    if (expiresAt != null && expiresAt < System.currentTimeMillis()) {
        errors.add("Expiration date cannot be in the past")
    }
    return if (errors.isEmpty()) ContentValidationResult.Valid
    else ContentValidationResult.Invalid(errors)
}


// === ARCHIVO: app/src/main/java/com/example/offlinefirst/domain/model/SyncOperation.kt ===
package com.example.offlinefirst.domain.model

import java.util.UUID

data class SyncOperation(
    val id: String = UUID.randomUUID().toString(),
    val entityType: EntityType,
    val entityId: String,
    val operationType: OperationType,
    val payload: String,
    val status: SyncOperationStatus = SyncOperationStatus.PENDING,
    val createdAt: Long = System.currentTimeMillis(),
    val lastAttemptAt: Long? = null,
    val attempts: Int = 0,
    val maxRetries: Int = 3,
    val errorMessage: String? = null,
    val priority: OperationPriority = OperationPriority.NORMAL,
    val dependsOn: String? = null,
    val correlationId: String? = null
) {
    fun canRetry(): Boolean = status == SyncOperationStatus.FAILED && attempts < maxRetries
    
    fun shouldRetry(): Boolean = when {
        status == SyncOperationStatus.PENDING -> true
        status == SyncOperationStatus.FAILED && attempts < maxRetries -> true
        else -> false
    }
    
    fun incrementAttempt(): SyncOperation = copy(
        attempts = attempts + 1,
        lastAttemptAt = System.currentTimeMillis()
    )
    
    fun markAsInProgress(): SyncOperation = copy(
        status = SyncOperationStatus.IN_PROGRESS,
        lastAttemptAt = System.currentTimeMillis()
    )
    
    fun markAsCompleted(): SyncOperation = copy(status = SyncOperationStatus.COMPLETED)
    
    fun markAsFailed(error: String): SyncOperation = copy(
        status = SyncOperationStatus.FAILED,
        errorMessage = error,
        attempts = attempts + 1,
        lastAttemptAt = System.currentTimeMillis()
    )
    
    fun withPriority(priority: OperationPriority): SyncOperation = copy(priority = priority)
    
    fun withDependency(operationId: String): SyncOperation = copy(dependsOn = operationId)
    
    fun isHighPriority(): Boolean = priority == OperationPriority.HIGH
    
    fun isCritical(): Boolean = priority == OperationPriority.CRITICAL
    
    fun getNextRetryDelay(): Long = when (attempts) {
        0 -> 60_000L
        1 -> 300_000L
        2 -> 900_000L
        else -> 1_800_000L
    }
    
    fun isExpired(): Boolean {
        val expirationTime = 24 * 60 * 60 * 1000L
        return System.currentTimeMillis() - createdAt > expirationTime
    }
    
    fun validatePayload(): Boolean = payload.isNotBlank() && entityId.isNotBlank()
    
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false
        other as SyncOperation
        return id == other.id
    }
    
    override fun hashCode(): Int = id.hashCode()
    
    override fun toString(): String = "SyncOperation(id=$id, entityType=$entityType, entityId=$entityId, operationType=$operationType, status=$status, attempts=$attempts)"
}

enum class EntityType(val displayName: String) {
    USER("Usuario"),
    PREFERENCES("Preferencias"),
    CONTENT("Contenido"),
    SYNC_METADATA("Metadatos de Sincronización");
    
    fun isUserRelated(): Boolean = this == USER
    fun isPreferencesRelated(): Boolean = this == PREFERENCES
    fun isContentRelated(): Boolean = this == CONTENT
}

enum class OperationType(val displayName: String, val isIdempotent: Boolean = false) {
    CREATE("Crear", false),
    UPDATE("Actualizar", true),
    DELETE("Eliminar", false),
    SYNC("Sincronizar", true);
    
    fun requiresConfirmation(): Boolean = this == DELETE
    fun canBeMerged(): Boolean = this == UPDATE
}

enum class SyncOperationStatus {
    PENDING,
    IN_PROGRESS,
    COMPLETED,
    FAILED,
    CANCELLED;
    
    fun isActive(): Boolean = this == PENDING || this == IN_PROGRESS
    fun isTerminal(): Boolean = this == COMPLETED || this == CANCELLED
    fun isFailed(): Boolean = this == FAILED
    fun canTransitionTo(newStatus: SyncOperationStatus): Boolean {
        return when (this) {
            PENDING -> newStatus == IN_PROGRESS || newStatus == CANCELLED
            IN_PROGRESS -> newStatus == COMPLETED || newStatus == FAILED
            FAILED -> newStatus == PENDING || newStatus == CANCELLED
            COMPLETED, CANCELLED -> false
        }
    }
}

enum class OperationPriority(val value: Int, val displayName: String) {
    CRITICAL(0, "Crítica"),
    HIGH(1, "Alta"),
    NORMAL(2, "Normal"),
    LOW(3, "Baja");
    
    fun isHighPriority(): Boolean = this.value <= 1
    fun compareTo(other: OperationPriority): Int = other.value - this.value
}

data class SyncOperationBatch(
    val operations: List<SyncOperation>,
    val createdAt: Long = System.currentTimeMillis(),
    val batchId: String = UUID.randomUUID().toString()
) {
    fun getPendingOperations(): List<SyncOperation> = operations.filter { it.status == SyncOperationStatus.PENDING }
    
    fun getFailedOperations(): List<SyncOperation> = operations.filter { it.status == SyncOperationStatus.FAILED }
    
    fun getInProgressOperations(): List<SyncOperation> = operations.filter { it.status == SyncOperationStatus.IN_PROGRESS }
    
    fun canExecute(): Boolean = getPendingOperations().isNotEmpty()
    
    fun getOperationCount(): Int = operations.size
    
    fun getPendingCount(): Int = getPendingOperations().size
    
    fun getFailedCount(): Int = getFailedOperations().size
    
    fun isComplete(): Boolean = operations.all { it.status == SyncOperationStatus.COMPLETED }
    
    fun hasFailures(): Boolean = operations.any { it.status == SyncOperationStatus.FAILED }
    
    fun prioritize(): List<SyncOperation> = operations.sortedBy { it.priority.value }
    
    fun filterByEntityType(entityType: EntityType): List<SyncOperation> = operations.filter { it.entityType == entityType }
    
    fun filterByOperationType(operationType: OperationType): List<SyncOperation> = operations.filter { it.operationType == operationType }
}

sealed class SyncOperationResult {
    data class Success(val operation: SyncOperation, val syncedAt: Long = System.currentTimeMillis()) : SyncOperationResult()
    data class Failure(val operation: SyncOperation, val error: String, val canRetry: Boolean = operation.canRetry()) : SyncOperationResult()
    data class PartialSuccess(val successful: List<SyncOperation>, val failed: List<SyncOperation>) : SyncOperationResult()
    data object NoOperations : SyncOperationResult()
    data class Cancelled(val operations: List<SyncOperation>) : SyncOperationResult()
    
    fun isSuccess(): Boolean = this is Success
    fun isFailure(): Boolean = this is Failure
    fun getOperations(): List<SyncOperation> = when (this) {
        is Success -> listOf(operation)
        is Failure -> listOf(operation)
        is PartialSuccess -> successful + failed
        is NoOperations -> emptyList()
        is Cancelled -> operations
    }
}

object SyncOperationFactory {
    fun createUserSync(userId: String, operationType: OperationType, payload: String, priority: OperationPriority = OperationPriority.NORMAL): SyncOperation {
        return SyncOperation(
            entityType = EntityType.USER,
            entityId = userId,
            operationType = operationType,
            payload = payload,
            priority = priority
        )
    }
    
    fun createPreferenceSync(preferenceId: String, operationType: OperationType, payload: String, priority: OperationPriority = OperationPriority.NORMAL): SyncOperation {
        return SyncOperation(
            entityType = EntityType.PREFERENCES,
            entityId = preferenceId,
            operationType = operationType,
            payload = payload,
            priority = priority
        )
    }
    
    fun createContentSync(contentId: String, operationType: OperationType, payload: String, priority: OperationPriority = OperationPriority.NORMAL): SyncOperation {
        return SyncOperation(
            entityType = EntityType.CONTENT,
            entityId = contentId,
            operationType = operationType,
            payload = payload,
            priority = priority
        )
    }
    
    fun createBatch(operations: List<SyncOperation>): SyncOperationBatch {
        return SyncOperationBatch(operations = operations)
    }
}

// === ARCHIVO: app/src/main/java/com/example/offlinefirst/domain/repository/UserRepository.kt ===
package com.example.offlinefirst.domain.repository

import com.example.offlinefirst.domain.model.SyncStatus
import com.example.offlinefirst.domain.model.User
import com.example.offlinefirst.domain.model.UserValidationResult
import com.example.offlinefirst.util.Resource
import kotlinx.coroutines.flow.Flow

interface UserRepository {
    fun getAllUsers(): Flow<List<User>>
    
    fun getUserById(userId: String): Flow<Resource<User>>
    
    fun getUserByEmail(email: String): Flow<Resource<User>>
    
    fun getUserByUsername(username: String): Flow<Resource<User>>
    
    suspend fun saveUser(user: User): Resource<User>
    
    suspend fun saveUsers(users: List<User>): Resource<List<User>>
    
    suspend fun updateUser(user: User): Resource<User>
    
    suspend fun deleteUser(userId: String): Resource<Unit>
    
    suspend fun deleteAllUsers(): Resource<Unit>
    
    fun getPendingSyncUsers(): Flow<List<User>>
    
    fun getSyncedUsers(): Flow<List<User>>
    
    fun getUsersWithConflicts(): Flow<List<User>>
    
    suspend fun markUserAsSynced(userId: String): Resource<User>
    
    suspend fun markUserAsPending(userId: String): Resource<User>
    
    suspend fun markUserAsConflict(userId: String): Resource<User>
    
    suspend fun syncUser(userId: String): Resource<User>
    
    suspend fun syncAllPendingUsers(): Resource<List<User>>
    
    suspend fun resolveConflict(userId: String, resolution: ConflictResolution): Resource<User>
    
    fun searchUsers(query: String): Flow<List<User>>
    
    fun getUserCount(): Flow<Int>
    
    suspend fun validateUser(email: String?, username: String?, displayName: String?): UserValidationResult
    
    suspend fun getUserByCredentials(email: String, password: String): Resource<User>
    
    suspend fun updateUserProfile(userId: String, displayName: String?, phone: String?, avatarUrl: String?): Resource<User>
    
    suspend fun verifyUserEmail(userId: String, verified: Boolean): Resource<User>
    
    suspend fun verifyUserPhone(userId: String, verified: Boolean): Resource<User>
    
    suspend fun refreshUserFromRemote(userId: String): Resource<User>
    
    fun observeUserSyncStatus(userId: String): Flow<SyncStatus>
    
    suspend fun getLastSyncTime(userId: String): Long?
    
    suspend fun updateLastSyncTime(userId: String, timestamp: Long)
}

sealed class ConflictResolution {
    data class UseLocal(val user: User) : ConflictResolution()
    data class UseRemote(val remoteUser: User) : ConflictResolution()
    data class Merge(val localUser: User, val remoteUser: User) : ConflictResolution()
    data object Discard : ConflictResolution()
}

interface UserCacheManager {
    suspend fun cacheUser(user: User)
    
    suspend fun cacheUsers(users: List<User>)
    
    suspend fun getCachedUser(userId: String): User?
    
    suspend fun getCachedUsers(): List<User>
    
    suspend fun invalidateCache()
    
    suspend fun invalidateUser(userId: String)
    
    fun isCacheValid(): Boolean
    
    suspend fun getCacheSize(): Long
}

interface UserRemoteDataSource {
    suspend fun fetchUser(userId: String): Resource<User>
    
    suspend fun fetchAllUsers(): Resource<List<User>>
    
    suspend fun fetchUserByEmail(email: String): Resource<User>
    
    suspend fun createUser(user: User): Resource<User>
    
    suspend fun updateUser(user: User): Resource<User>
    
    suspend fun deleteUser(userId: String): Resource<Unit>
    
    suspend fun searchUsers(query: String): Resource<List<User>>
    
    suspend fun authenticateUser(email: String, password: String): Resource<User>
    
    suspend fun verifyEmail(userId: String): Resource<Boolean>
    
    suspend fun verifyPhone(userId: String): Resource<Boolean>
}

object UserRepositoryFactory {
    fun create(localDataSource: UserLocalDataSource, remoteDataSource: UserRemoteDataSource): UserRepository {
        return UserRepositoryImpl(
            localDataSource = localDataSource,
            remoteDataSource = remoteDataSource
        )
    }
}

interface UserLocalDataSource {
    fun getAllUsers(): Flow<List<User>>
    
    fun getUserById(userId: String): Flow<User?>
    
    fun getUserByEmail(email: String): Flow<User?>
    
    fun getUserByUsername(username: String): Flow<User?>
    
    suspend fun insertUser(user: User)
    
    suspend fun insertUsers(users: List<User>)
    
    suspend fun updateUser(user: User)
    
    suspend fun deleteUser(userId: String)
    
    suspend fun deleteAllUsers()
    
    fun getPendingSyncUsers(): Flow<List<User>>
    
    fun getSyncedUsers(): Flow<List<User>>
    
    fun getUsersWithConflicts(): Flow<List<User>>
    
    suspend fun updateSyncStatus(userId: String, status: SyncStatus)
    
    fun searchUsers(query: String): Flow<List<User>>
    
    fun getUserCount(): Flow<Int>
}

// === ARCHIVO: app/src/main/java/com/example/offlinefirst/domain/repository/PreferencesRepository.kt ===
package com.example.offlinefirst.domain.repository

import com.example.offlinefirst.domain.model.PreferenceCategory
import com.example.offlinefirst.domain.model.PreferenceDataType
import com.example.offlinefirst.domain.model.PreferenceValidationResult
import com.example.offlinefirst.domain.model.Preferences
import com.example.offlinefirst.domain.model.PreferenceGroup
import com.example.offlinefirst.domain.model.SyncStatus
import com.example.offlinefirst.util.Resource
import kotlinx.coroutines.flow.Flow

interface PreferencesRepository {
    fun getAllPreferences(): Flow<List<Preferences>>
    
    fun getPreferenceById(preferenceId: String): Flow<Resource<Preferences>>
    
    fun getPreferenceByKey(key: String, userId: String? = null): Flow<Resource<Preferences>>
    
    fun getPreferencesByCategory(category: PreferenceCategory): Flow<List<Preferences>>
    
    fun getPreferencesByUser(userId: String): Flow<List<Preferences>>
    
    fun getGlobalPreferences(): Flow<List<Preferences>>
    
    suspend fun savePreference(preference: Preferences): Resource<Preferences>
    
    suspend fun savePreferences(preferences: List<Preferences>): Resource<List<Preferences>>
    
    suspend fun updatePreference(preference: Preferences): Resource<Preferences>
    
    suspend fun deletePreference(preferenceId: String): Resource<Unit>
    
    suspend fun deleteAllPreferences(): Resource<Unit>
    
    suspend fun deleteUserPreferences(userId: String): Resource<Unit>
    
    fun getPendingSyncPreferences(): Flow<List<Preferences>>
    
    fun getSyncedPreferences(): Flow<List<Preferences>>
    
    fun getPreferencesWithConflicts(): Flow<List<Preferences>>
    
    suspend fun markPreferenceAsSynced(preferenceId: String): Resource<Preferences>
    
    suspend fun markPreferenceAsPending(preferenceId: String): Resource<Preferences>
    
    suspend fun markPreferenceAsConflict(preferenceId: String): Resource<Preferences>
    
    suspend fun syncPreference(preferenceId: String): Resource<Preferences>
    
    suspend fun syncAllPendingPreferences(): Resource<List<Preferences>>
    
    suspend fun resolvePreferenceConflict(preferenceId: String, resolution: PreferenceConflictResolution): Resource<Preferences>
    
    suspend fun getPreferenceValue(key: String, userId: String? = null): Any?
    
    suspend fun setPreferenceValue(key: String, value: String, userId: String? = null, category: PreferenceCategory = PreferenceCategory.GENERAL): Resource<Preferences>
    
    fun getPreferenceGroups(): Flow<List<PreferenceGroup>>
    
    suspend fun validatePreference(key: String, value: String, isGlobal: Boolean = true, userId: String? = null): PreferenceValidationResult
    
    suspend fun importPreferences(preferences: List<Preferences>): Resource<List<Preferences>>
    
    suspend fun exportPreferences(userId: String? = null): Resource<List<Preferences>>
    
    fun observePreferenceSyncStatus(preferenceId: String): Flow<SyncStatus>
    
    suspend fun getLastSyncTime(preferenceId: String): Long?
    
    suspend fun updateLastSyncTime(preferenceId: String, timestamp: Long)
    
    suspend fun resetToDefaults(userId: String? = null): Resource<Unit>
}

sealed class PreferenceConflictResolution {
    data class UseLocal(val preference: Preferences) : PreferenceConflictResolution()
    data class UseRemote(val remotePreference: Preferences) : PreferenceConflictResolution()
    data class Merge(val localPreference: Preferences, val remotePreference: Preferences) : PreferenceConflictResolution()
    data object Discard : PreferenceConflictResolution()
}

interface PreferencesCacheManager {
    suspend fun cachePreference(preference: Preferences)
    
    suspend fun cachePreferences(preferences: List<Preferences>)
    
    suspend fun getCachedPreference(preferenceId: String): Preferences?
    
    suspend fun getCachedPreferenceByKey(key: String, userId: String? = null): Preferences?
    
    suspend fun getCachedPreferences(): List<Preferences>
    
    suspend fun invalidateCache()
    
    suspend fun invalidatePreference(preferenceId: String)
    
    suspend fun invalidateUserPreferences(userId: String)
    
    fun isCacheValid(): Boolean
    
    suspend fun getCacheSize(): Long
}

interface PreferencesRemoteDataSource {
    suspend fun fetchPreference(preferenceId: String): Resource<Preferences>
    
    suspend fun fetchAllPreferences(userId: String? = null): Resource<List<Preferences>>
    
    suspend fun fetchPreferencesByCategory(category: PreferenceCategory, userId: String? = null): Resource<List<Preferences>>
    
    suspend fun createPreference(preference: Preferences): Resource<Preferences>
    
    suspend fun updatePreference(preference: Preferences): Resource<Preferences>
    
    suspend fun deletePreference(preferenceId: String): Resource<Unit>
    
    suspend fun syncPreferences(preferences: List<Preferences>): Resource<List<Preferences>>
}

object PreferencesRepositoryFactory {
    fun create(localDataSource: PreferencesLocalDataSource, remoteDataSource: PreferencesRemoteDataSource): PreferencesRepository {
        return PreferencesRepositoryImpl(
            localDataSource = localDataSource,
            remoteDataSource = remoteDataSource
        )
    }
}

interface PreferencesLocalDataSource {
    fun getAllPreferences(): Flow<List<Preferences>>
    
    fun getPreferenceById(preferenceId: String): Flow<Preferences?>
    
    fun getPreferenceByKey(key: String, userId: String?): Flow<Preferences?>
    
    fun getPreferencesByCategory(category: PreferenceCategory): Flow<List<Preferences>>
    
    fun getPreferencesByUser(userId: String): Flow<List<Preferences>>
    
    fun getGlobalPreferences(): Flow<List<Preferences>>
    
    suspend fun insertPreference(preference: Preferences)
    
    suspend fun insertPreferences(preferences: List<Preferences>)
    
    suspend fun updatePreference(preference: Preferences)
    
    suspend fun deletePreference(preferenceId: String)
    
    suspend fun deleteAllPreferences()
    
    suspend fun deleteUserPreferences(userId: String)
    
    fun getPendingSyncPreferences(): Flow<List<Preferences>>
    
    fun getSyncedPreferences(): Flow<List<Preferences>>
    
    fun getPreferencesWithConflicts(): Flow<List<Preferences>>
    
    suspend fun updateSyncStatus(preferenceId: String, status: SyncStatus)
    
    fun getPreferenceGroups(): Flow<List<PreferenceGroup>>
}

// === ARCHIVO: app/src/main/java/com/example/offlinefirst/domain/repository/ContentRepository.kt ===
package com.example.offlinefirst.domain.repository

import com.example.offlinefirst.domain.model.ContentCategory
import com.example.offlinefirst.domain.model.ContentValidationResult
import com.example.offlinefirst.domain.model.DownloadedContent
import com.example.offlinefirst.domain.model.ContentBatch
import com.example.offlinefirst.domain.model.SyncStatus
import com.example.offlinefirst.util.Resource
import kotlinx.coroutines.flow.Flow

interface ContentRepository {
    fun getAllContents(): Flow<List<DownloadedContent>>
    
    fun getContentById(contentId: String): Flow<Resource<DownloadedContent>>
    
    fun getContentByUrl(url: String): Flow<Resource<DownloadedContent>>
    
    fun getContentsByCategory(category: ContentCategory): Flow<List<DownloadedContent>>
    
    fun getContentsByTags(tags: List<String>): Flow<List<DownloadedContent>>
    
    fun getDownloadedContents(): Flow<List<DownloadedContent>>
    
    fun getAvailableOfflineContents(): Flow<List<DownloadedContent>>
    
    fun getExpiredContents(): Flow<List<DownloadedContent>>
    
    suspend fun saveContent(content: DownloadedContent): Resource<DownloadedContent>
    
    suspend fun saveContents(contents: List<DownloadedContent>): Resource<List<DownloadedContent>>
    
    suspend fun updateContent(content: DownloadedContent): Resource<DownloadedContent>
    
    suspend fun deleteContent(contentId: String): Resource<Unit>
    
    suspend fun deleteAllContents(): Resource<Unit>
    
    suspend fun deleteExpiredContents(): Resource<Int>
    
    fun getPendingSyncContents(): Flow<List<DownloadedContent>>
    
    fun getSyncedContents(): Flow<List<DownloadedContent>>
    
    fun getContentsWithConflicts(): Flow<List<DownloadedContent>>
    
    suspend fun markContentAsSynced(contentId: String): Resource<DownloadedContent>
    
    suspend fun markContentAsPending(contentId: String): Resource<DownloadedContent>
    
    suspend fun markContentAsConflict(contentId: String): Resource<DownloadedContent>
    
    suspend fun syncContent(contentId: String): Resource<DownloadedContent>
    
    suspend fun syncAllPendingContents(): Resource<List<DownloadedContent>>
    
    suspend fun resolveContentConflict(contentId: String, resolution: ContentConflictResolution): Resource<DownloadedContent>
    
    suspend fun downloadContent(contentId: String, url: String): Resource<DownloadedContent>
    
    suspend fun downloadContentsBatch(contents: List<DownloadedContent>): Resource<ContentBatch>
    
    suspend fun cancelDownload(contentId: String): Resource<Unit>
    
    suspend fun pauseDownload(contentId: String): Resource<Unit>
    
    suspend fun resumeDownload(contentId: String): Resource<DownloadedContent>
    
    suspend fun getDownloadProgress(contentId: String): Float
    
    fun observeDownloadProgress(contentId: String): Flow<Float>
    
    suspend fun getLocalFilePath(contentId: String): String?
    
    suspend fun validateContentFile(contentId: String): Boolean
    
    suspend fun cleanupOrphanedFiles(): Resource<Int>
    
    fun searchContents(query: String): Flow<List<DownloadedContent>>
    
    fun getContentCount(): Flow<Int>
    
    fun getDownloadedContentCount(): Flow<Int>
    
    fun getStorageUsed(): Flow<Long>
    
    suspend fun validateContent(url: String?, title: String?, fileSize: Long, downloadedSize: Long, expiresAt: Long?): ContentValidationResult
    
    suspend fun refreshContentFromRemote(contentId: String): Resource<DownloadedContent>
    
    fun observeContentSyncStatus(contentId: String): Flow<SyncStatus>
    
    suspend fun getLastSyncTime(contentId: String): Long?
    
    suspend fun updateLastSyncTime(contentId: String, timestamp: Long)
    
    suspend fun updateAccessCount(contentId: String): Resource<DownloadedContent>
    
    suspend fun getMostAccessedContents(limit: Int = 10): List<DownloadedContent>
    
    suspend fun getContentsByExpirationRange(startTime: Long, endTime: Long): List<DownloadedContent>
}

sealed class ContentConflictResolution {
    data class UseLocal(val content: DownloadedContent) : ContentConflictResolution()
    data class UseRemote(val remoteContent: DownloadedContent) : ContentConflictResolution()
    data class Merge(val localContent: DownloadedContent, val remoteContent: DownloadedContent) : ContentConflictResolution()
    data object Discard : ContentConflictResolution()
}

interface ContentCacheManager {
    suspend fun cacheContent(content: DownloadedContent)
    
    suspend fun cacheContents(contents: List<DownloadedContent>)
    
    suspend fun getCachedContent(contentId: String): DownloadedContent?
    
    suspend fun getCachedContents(): List<DownloadedContent>
    
    suspend fun invalidateCache()
    
    suspend fun invalidateContent(contentId: String)
    
    fun isCacheValid(): Boolean
    
    suspend fun getCacheSize(): Long
}

interface ContentRemoteDataSource {
    suspend fun fetchContent(contentId: String): Resource<DownloadedContent>
    
    suspend fun fetchAllContents(): Resource<List<DownloadedContent>>
    
    suspend fun fetchContentsByCategory(category: ContentCategory): Resource<List<DownloadedContent>>
    
    suspend fun fetchContentsByTags(tags: List<String>): Resource<List<DownloadedContent>>
    
    suspend fun createContent(content: DownloadedContent): Resource<DownloadedContent>
    
    suspend fun updateContent(content: DownloadedContent): Resource<DownloadedContent>
    
    suspend fun deleteContent(contentId: String): Resource<Unit>
    
    suspend fun downloadContent(url: String): Resource<Pair<String, Long>>
    
    suspend fun getContentDownloadUrl(contentId: String): Resource<String>
    
    suspend fun searchContents(query: String): Resource<List<DownloadedContent>>
}

interface ContentDownloadManager {
    suspend fun startDownload(content: DownloadedContent, url: String): Flow<DownloadProgress>
    
    suspend fun pauseDownload(contentId: String)
    
    suspend fun resumeDownload(contentId: String)
    
    suspend fun cancelDownload(contentId: String)
    
    fun getActiveDownloads(): Flow<List<DownloadedContent>>
    
    suspend fun getDownloadStatus(contentId: String): DownloadStatus
}

data class DownloadProgress(
    val contentId: String,
    val bytesDownloaded: Long,
    val totalBytes: Long,
    val progress: Float,
    val status: DownloadStatus
)

enum class DownloadStatus {
    PENDING,
    DOWNLOADING,
    PAUSED,
    COMPLETED,
    FAILED,
    CANCELLED;
    
    fun isActive(): Boolean = this == DOWNLOADING || this == PENDING
    fun isTerminal(): Boolean = this == COMPLETED || this == FAILED || this == CANCELLED
}

object ContentRepositoryFactory {
    fun create(
        localDataSource: ContentLocalDataSource,
        remoteDataSource: ContentRemoteDataSource,
        downloadManager: ContentDownloadManager
    ): ContentRepository {
        return ContentRepositoryImpl(
            localDataSource = localDataSource,
            remoteDataSource = remoteDataSource,
            downloadManager = downloadManager
        )
    }
}

interface ContentLocalDataSource {
    fun getAllContents(): Flow<List<DownloadedContent>>
    
    fun getContentById(contentId: String): Flow<DownloadedContent?>
    
    fun getContentByUrl(url: String): Flow<DownloadedContent?>
    
    fun getContentsByCategory(category: ContentCategory): Flow<List<DownloadedContent>>
    
    fun getContentsByTags(tags: List<String>): Flow<List<DownloadedContent>>
    
    fun getDownloadedContents(): Flow<List<DownloadedContent>>
    
    fun getAvailableOfflineContents(): Flow<List<DownloadedContent>>
    
    fun getExpiredContents(): Flow<List<DownloadedContent>>
    
    suspend fun insertContent(content: DownloadedContent)
    
    suspend fun insertContents(contents: List<DownloadedContent>)
    
    suspend fun updateContent(content: DownloadedContent)
    
    suspend fun deleteContent(contentId: String)
    
    suspend fun deleteAllContents()
    
    suspend fun deleteExpiredContents()
    
    fun getPendingSyncContents(): Flow<List<DownloadedContent>>
    
    fun getSyncedContents(): Flow<List<DownloadedContent>>
    
    fun getContentsWithConflicts(): Flow<List<DownloadedContent>>
    
    suspend fun updateSyncStatus(contentId: String, status: SyncStatus)
    
    fun searchContents(query: String): Flow<List<DownloadedContent>>
    
    fun getContentCount(): Flow<Int>
    
    fun getDownloadedContentCount(): Flow<Int>
    
    fun getStorageUsed(): Flow<Long>
}


// === ARCHIVO: app/src/main/java/com/example/offlinefirst/domain/repository/SyncRepository.kt ===
package com.example.offlinefirst.domain.repository

import com.example.offlinefirst.domain.model.SyncOperation
import com.example.offlinefirst.domain.model.SyncStatus
import com.example.offlinefirst.util.Resource
import kotlinx.coroutines.flow.Flow

interface SyncRepository {
    fun getPendingOperations(): Flow<List<SyncOperation>>
    
    fun getOperationsByStatus(status: SyncStatus): Flow<List<SyncOperation>>
    
    suspend fun queueOperation(operation: SyncOperation): Resource<SyncOperation>
    
    suspend fun markOperationAsCompleted(operationId: String): Resource<Unit>
    
    suspend fun markOperationAsFailed(operationId: String, error: String): Resource<Unit>
    
    suspend fun getOperationById(operationId: String): Resource<SyncOperation>
    
    suspend fun deleteCompletedOperations(olderThan: Long): Resource<Int>
    
    suspend fun retryFailedOperations(): Resource<List<SyncOperation>>
    
    suspend fun getOperationCount(): Resource<Int>
    
    suspend fun getPendingCount(): Resource<Int>
    
    suspend fun clearAllOperations(): Resource<Unit>
    
    fun observeSyncStatus(): Flow<SyncStatus>
    
    suspend fun executeSync(): Resource<SyncOperationResult>
}

data class SyncOperationResult(
    val totalOperations: Int,
    val successfulOperations: Int,
    val failedOperations: Int,
    val skippedOperations: Int,
    val startTime: Long,
    val endTime: Long
) {
    val duration: Long get() = endTime - startTime
    
    val successRate: Float get() = if (totalOperations > 0) {
        successfulOperations.toFloat() / totalOperations
    } else 0f
    
    fun isFullySuccessful(): Boolean = failedOperations == 0 && successfulOperations > 0
    
    fun hasFailures(): Boolean = failedOperations > 0
}

// === ARCHIVO: app/src/main/java/com/example/offlinefirst/domain/usecase/GetUserUseCase.kt ===
package com.example.offlinefirst.domain.usecase

import com.example.offlinefirst.domain.model.User
import com.example.offlinefirst.domain.repository.UserRepository
import com.example.offlinefirst.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetUserUseCase @Inject constructor(
    private val userRepository: UserRepository
) {
    operator fun invoke(userId: String): Flow<Resource<User>> = flow {
        emit(Resource.Loading())
        
        try {
            val cachedUser = userRepository.getUserById(userId).first()
            
            if (cachedUser != null) {
                emit(Resource.Success(cachedUser))
                
                if (cachedUser.needsSync()) {
                    try {
                        val remoteUser = userRepository.fetchUserFromRemote(userId)
                        remoteUser?.let { remote ->
                            val mergedUser = mergeUserData(cachedUser, remote)
                            userRepository.saveUser(mergedUser)
                            emit(Resource.Success(mergedUser))
                        }
                    } catch (e: Exception) {
                        emit(Resource.Success(cachedUser))
                    }
                }
            } else {
                emit(Resource.Loading())
                
                try {
                    val remoteUser = userRepository.fetchUserFromRemote(userId)
                    if (remoteUser != null) {
                        userRepository.saveUser(remoteUser)
                        emit(Resource.Success(remoteUser))
                    } else {
                        emit(Resource.Error("Usuario no encontrado"))
                    }
                } catch (e: Exception) {
                    emit(Resource.Error("Error al obtener usuario: ${e.message}"))
                }
            }
        } catch (e: Exception) {
            emit(Resource.Error("Error al leer usuario: ${e.message}"))
        }
    }
    
    private fun mergeUserData(cached: User, remote: User): User {
        return when {
            cached.syncStatus == com.example.offlinefirst.domain.model.SyncStatus.SYNCED -> remote
            remote.lastSyncAt != null && cached.lastSyncAt != null && 
                remote.lastSyncAt > cached.lastSyncAt -> remote
            cached.hasPendingChanges() -> cached.withUpdatedTimestamp()
            else -> remote
        }
    }
    
    fun getCachedUser(userId: String): Flow<User?> {
        return userRepository.getUserById(userId)
    }
    
    suspend fun getUserSync(userId: String): Resource<User> {
        return try {
            val user = userRepository.getUserById(userId).first()
            if (user != null) {
                Resource.Success(user)
            } else {
                Resource.Error("Usuario no encontrado en caché")
            }
        } catch (e: Exception) {
            Resource.Error("Error al obtener usuario: ${e.message}")
        }
    }
    
    fun observeUser(userId: String): Flow<Resource<User>> {
        return userRepository.getUserById(userId).let { userFlow ->
            flow {
                userFlow.collect { user ->
                    if (user != null) {
                        emit(Resource.Success(user))
                    } else {
                        emit(Resource.Loading())
                    }
                }
            }
        }
    }
}

// === ARCHIVO: app/src/main/java/com/example/offlinefirst/domain/usecase/SaveUserUseCase.kt ===
package com.example.offlinefirst.domain.usecase

import com.example.offlinefirst.domain.model.SyncStatus
import com.example.offlinefirst.domain.model.User
import com.example.offlinefirst.domain.model.UserValidationResult
import com.example.offlinefirst.domain.repository.SyncRepository
import com.example.offlinefirst.domain.repository.UserRepository
import com.example.offlinefirst.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import javax.inject.Inject

class SaveUserUseCase @Inject constructor(
    private val userRepository: UserRepository,
    private val syncRepository: SyncRepository
) {
    suspend operator fun invoke(user: User): Resource<User> {
        val validationResult = validateUser(user)
        if (!validationResult.isValid()) {
            return Resource.Error(
                "Validación fallida: ${validationResult.getErrors().joinToString(", ")}"
            )
        }
        
        return try {
            val existingUser = userRepository.getUserById(user.id).first()
            
            val userToSave = if (existingUser != null) {
                val hasLocalChanges = existingUser.hasPendingChanges() && 
                    existingUser.updatedAt > (existingUser.lastSyncAt ?: 0L)
                
                if (hasLocalChanges) {
                    val mergedUser = resolveConflict(existingUser, user)
                    mergedUser.markAsPending()
                } else {
                    user.withUpdatedTimestamp().markAsPending()
                }
            } else {
                user.withUpdatedTimestamp().markAsPending()
            }
            
            userRepository.saveUser(userToSave)
            
            queueSyncOperation(userToSave)
            
            Resource.Success(userToSave)
        } catch (e: Exception) {
            Resource.Error("Error al guardar usuario: ${e.message}")
        }
    }
    
    private fun validateUser(user: User): UserValidationResult {
        val errors = mutableListOf<String>()
        
        if (user.id.isBlank()) {
            errors.add("El ID del usuario no puede estar vacío")
        }
        
        if (user.username.isBlank()) {
            errors.add("El nombre de usuario no puede estar vacío")
        }
        
        if (user.email.isBlank()) {
            errors.add("El correo electrónico no puede estar vacío")
        }
        
        if (!user.email.contains("@")) {
            errors.add("El correo electrónico debe ser válido")
        }
        
        return if (errors.isEmpty()) {
            UserValidationResult.Valid
        } else {
            UserValidationResult.Invalid(errors)
        }
    }
    
    private fun resolveConflict(local: User, remote: User): User {
        return if (local.updatedAt > (remote.updatedAt ?: 0L)) {
            local
        } else {
            remote
        }
    }
    
    private suspend fun queueSyncOperation(user: User) {
        val operation = com.example.offlinefirst.domain.model.SyncOperation(
            id = "sync_user_${user.id}_${System.currentTimeMillis()}",
            entityId = user.id,
            entityType = "USER",
            operationType = com.example.offlinefirst.domain.model.OperationType.UPDATE,
            payload = mapOf(
                "username" to user.username,
                "email" to user.email,
                "displayName" to user.displayName,
                "phone" to (user.phone ?: ""),
                "isEmailVerified" to user.isEmailVerified.toString(),
                "isPhoneVerified" to user.isPhoneVerified.toString()
            ),
            syncStatus = SyncStatus.PENDING,
            retryCount = 0,
            maxRetries = 3,
            createdAt = System.currentTimeMillis(),
            updatedAt = System.currentTimeMillis(),
            lastSyncAt = null,
            errorMessage = null
        )
        
        syncRepository.queueOperation(operation)
    }
    
    suspend fun saveUserOffline(user: User): Resource<User> {
        return try {
            val offlineUser = user.markAsPending()
            userRepository.saveUser(offlineUser)
            Resource.Success(offlineUser)
        } catch (e: Exception) {
            Resource.Error("Error al guardar offline: ${e.message}")
        }
    }
    
    fun observeUserSave(userId: String): Flow<Resource<User>> {
        return kotlinx.coroutines.flow.flow {
            emit(Resource.Loading())
            userRepository.getUserById(userId).collect { user ->
                if (user != null) {
                    emit(Resource.Success(user))
                }
            }
        }
    }
}

// === ARCHIVO: app/src/main/java/com/example/offlinefirst/domain/usecase/SyncDataUseCase.kt ===
package com.example.offlinefirst.domain.usecase

import com.example.offlinefirst.domain.model.OperationType
import com.example.offlinefirst.domain.model.SyncOperation
import com.example.offlinefirst.domain.model.SyncStatus
import com.example.offlinefirst.domain.repository.SyncRepository
import com.example.offlinefirst.domain.repository.UserRepository
import com.example.offlinefirst.domain.repository.PreferencesRepository
import com.example.offlinefirst.domain.repository.ContentRepository
import com.example.offlinefirst.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.firstOrNull
import javax.inject.Inject

class SyncDataUseCase @Inject constructor(
    private val syncRepository: SyncRepository,
    private val userRepository: UserRepository,
    private val preferencesRepository: PreferencesRepository,
    private val contentRepository: ContentRepository
) {
    suspend operator fun invoke(): Resource<SyncRepository.SyncOperationResult> {
        return syncRepository.executeSync()
    }
    
    suspend fun syncUserData(): Resource<Int> {
        return try {
            val pendingUsers = userRepository.getUsersBySyncStatus(SyncStatus.PENDING)
                .first()
            
            var syncedCount = 0
            
            for (user in pendingUsers) {
                try {
                    val result = userRepository.syncUserToRemote(user)
                    if (result is Resource.Success) {
                        val syncedUser = user.markAsSynced()
                            .copy(lastSyncAt = System.currentTimeMillis())
                        userRepository.saveUser(syncedUser)
                        syncedCount++
                    } else {
                        val failedUser = user.copy(
                            syncStatus = SyncStatus.ERROR,
                            errorMessage = (result as? Resource.Error)?.message
                        )
                        userRepository.saveUser(failedUser)
                    }
                } catch (e: Exception) {
                    val failedUser = user.copy(
                        syncStatus = SyncStatus.ERROR,
                        errorMessage = e.message
                    )
                    userRepository.saveUser(failedUser)
                }
            }
            
            Resource.Success(syncedCount)
        } catch (e: Exception) {
            Resource.Error("Error al sincronizar usuarios: ${e.message}")
        }
    }
    
    suspend fun syncPreferences(): Resource<Int> {
        return try {
            val pendingPrefs = preferencesRepository.getPreferencesBySyncStatus(SyncStatus.PENDING)
                .first()
            
            var syncedCount = 0
            
            for (pref in pendingPrefs) {
                try {
                    val result = preferencesRepository.syncPreferenceToRemote(pref)
                    if (result is Resource.Success) {
                        val syncedPref = pref.markAsSynced()
                            .copy(lastSyncAt = System.currentTimeMillis())
                        preferencesRepository.savePreference(syncedPref)
                        syncedCount++
                    }
                } catch (e: Exception) {
                    // Log error but continue with next preference
                }
            }
            
            Resource.Success(syncedCount)
        } catch (e: Exception) {
            Resource.Error("Error al sincronizar preferencias: ${e.message}")
        }
    }
    
    suspend fun syncContent(): Resource<Int> {
        return try {
            val pendingContent = contentRepository.getContentBySyncStatus(SyncStatus.PENDING)
                .first()
            
            var syncedCount = 0
            
            for (content in pendingContent) {
                try {
                    val result = contentRepository.syncContentToRemote(content)
                    if (result is Resource.Success) {
                        val syncedContent = content.markAsSynced()
                            .copy(lastSyncAt = System.currentTimeMillis())
                        contentRepository.saveContent(syncedContent)
                        syncedCount++
                    }
                } catch (e: Exception) {
                    // Log error but continue
                }
            }
            
            Resource.Success(syncedCount)
        } catch (e: Exception) {
            Resource.Error("Error al sincronizar contenido: ${e.message}")
        }
    }
    
    suspend fun getPendingOperationsCount(): Resource<Int> {
        return syncRepository.getPendingCount()
    }
    
    fun observeSyncStatus(): Flow<SyncStatus> {
        return syncRepository.observeSyncStatus()
    }
    
    fun observePendingOperations(): Flow<List<SyncOperation>> {
        return syncRepository.getPendingOperations()
    }
    
    suspend fun retryFailedOperations(): Resource<List<SyncOperation>> {
        return syncRepository.retryFailedOperations()
    }
    
    suspend fun processOperation(operation: SyncOperation): Resource<Unit> {
        return try {
            when (operation.entityType) {
                "USER" -> processUserOperation(operation)
                "PREFERENCES" -> processPreferencesOperation(operation)
                "CONTENT" -> processContentOperation(operation)
                else -> Resource.Error("Tipo de operación desconocido: ${operation.entityType}")
            }
        } catch (e: Exception) {
            syncRepository.markOperationAsFailed(operation.id, e.message ?: "Error desconocido")
            Resource.Error("Error al procesar operación: ${e.message}")
        }
    }
    
    private suspend fun processUserOperation(operation: SyncOperation): Resource<Unit> {
        val user = userRepository.getUserById(operation.entityId).firstOrNull()
            ?: return Resource.Error("Usuario no encontrado")
        
        return when (operation.operationType) {
            OperationType.CREATE, OperationType.UPDATE -> {
                val result = userRepository.syncUserToRemote(user)
                if (result is Resource.Success) {
                    syncRepository.markOperationAsCompleted(operation.id)
                    Resource.Success(Unit)
                } else {
                    syncRepository.markOperationAsFailed(
                        operation.id, 
                        (result as? Resource.Error)?.message ?: "Error de sincronización"
                    )
                    Resource.Error("Error al sincronizar usuario")
                }
            }
            OperationType.DELETE -> {
                val result = userRepository.deleteUserFromRemote(operation.entityId)
                if (result is Resource.Success) {
                    syncRepository.markOperationAsCompleted(operation.id)
                    Resource.Success(Unit)
                } else {
                    Resource.Error("Error al eliminar usuario remoto")
                }
            }
        }
    }
    
    private suspend fun processPreferencesOperation(operation: SyncOperation): Resource<Unit> {
        val pref = preferencesRepository.getPreferenceById(operation.entityId).firstOrNull()
            ?: return Resource.Error("Preferencia no encontrada")
        
        return when (operation.operationType) {
            OperationType.CREATE, OperationType.UPDATE -> {
                val result = preferencesRepository.syncPreferenceToRemote(pref)
                if (result is Resource.Success) {
                    syncRepository.markOperationAsCompleted(operation.id)
                    Resource.Success(Unit)
                } else {
                    Resource.Error("Error al sincronizar preferencia")
                }
            }
            OperationType.DELETE -> {
                syncRepository.markOperationAsCompleted(operation.id)
                Resource.Success(Unit)
            }
        }
    }
    
    private suspend fun processContentOperation(operation: SyncOperation): Resource<Unit> {
        val content = contentRepository.getContentById(operation.entityId).firstOrNull()
            ?: return Resource.Error("Contenido no encontrado")
        
        return when (operation.operationType) {
            OperationType.CREATE, OperationType.UPDATE -> {
                val result = contentRepository.syncContentToRemote(content)
                if (result is Resource.Success) {
                    syncRepository.markOperationAsCompleted(operation.id)
                    Resource.Success(Unit)
                } else {
                    Resource.Error("Error al sincronizar contenido")
                }
            }
            OperationType.DELETE -> {
                syncRepository.markOperationAsCompleted(operation.id)
                Resource.Success(Unit)
            }
        }
    }
}

// === ARCHIVO: app/src/main/AndroidManifest.xml ===
<?xml version="1.0" encoding="utf-8"?>
<manifest xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:tools="http://schemas.android.com/tools">

    <uses-permission android:name="android.permission.INTERNET" />
    <uses-permission android:name="android.permission.ACCESS_NETWORK_STATE" />
    <uses-permission android:name="android.permission.ACCESS_WIFI_STATE" />
    <uses-permission android:name="android.permission.CHANGE_NETWORK_STATE" />
    <uses-permission android:name="android.permission.CHANGE_WIFI_STATE" />

    <uses-permission android:name="android.permission.READ_EXTERNAL_STORAGE"
        android:maxSdkVersion="32" />
    <uses-permission android:name="android.permission.WRITE_EXTERNAL_STORAGE"
        android:maxSdkVersion="29"
        tools:ignore="ScopedStorage" />
    <uses-permission android:name="android.permission.READ_MEDIA_IMAGES" />
    <uses-permission android:name="android.permission.READ_MEDIA_VIDEO" />
    <uses-permission android:name="android.permission.READ_MEDIA_AUDIO" />

    <uses-permission android:name="android.permission.RECEIVE_BOOT_COMPLETED" />
    <uses-permission android:name="android.permission.FOREGROUND_SERVICE" />
    <uses-permission android:name="android.permission.FOREGROUND_SERVICE_DATA_SYNC" />
    <uses-permission android:name="android.permission.POST_NOTIFICATIONS" />

    <uses-permission android:name="android.permission.WAKE_LOCK" />
    <uses-permission android:name="android.permission.REQUEST_IGNORE_BATTERY_OPTIMIZATIONS" />

    <application
        android:name=".OfflineFirstApplication"
        android:allowBackup="true"
        android:dataExtractionRules="@xml/data_extraction_rules"
        android:fullBackupContent="@xml/backup_rules"
        android:icon="@mipmap/ic_launcher"
        android:label="@string/app_name"
        android:roundIcon="@mipmap/ic_launcher_round"
        android:supportsRtl="true"
        android:theme="@style/Theme.OfflineFirstApp"
        android:networkSecurityConfig="@xml/network_security_config"
        android:usesCleartextTraffic="false"
        tools:targetApi="35">

        <activity
            android:name=".MainActivity"
            android:exported="true"
            android:theme="@style/Theme.OfflineFirstApp"
            android:configChanges="orientation|screenSize|screenLayout|keyboardHidden"
            android:windowSoftInputMode="adjustResize">
            <intent-filter>
                <action android:name="android.intent.action.MAIN" />
                <category android:name="android.intent.category.LAUNCHER" />
            </intent-filter>
        </activity>

        <provider
            android:name="androidx.startup.InitializationProvider"
            android:authorities="${applicationId}.androidx-startup"
            android:exported="false"
            tools:node="merge">
            <meta-data
                android:name="androidx.work.WorkManagerInitializer"
                android:value="androidx.startup"
                tools:node="remove" />
        </provider>

        <service
            android:name="androidx.work.impl.foreground.SystemForegroundService"
            android:foregroundServiceType="dataSync"
            android:exported="false" />

    </application>

</manifest>

// === ARCHIVO: app/src/main/java/com/example/offlinefirst/MainActivity.kt ===
package com.example.offlinefirst

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.offlinefirst.presentation.ui.screens.UserScreen
import com.example.offlinefirst.presentation.viewmodel.UserViewModel
import com.example.offlinefirst.presentation.ui.theme.OfflineFirstTheme
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var userViewModelFactory: UserViewModel.Factory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        
        setContent {
            OfflineFirstTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val userViewModel: UserViewModel = viewModel(
                        factory = userViewModelFactory
                    )
                    UserScreen(
                        viewModel = userViewModel,
                        onNavigateToPreferences = { /* Navigation handled internally */ },
                        onNavigateToContent = { /* Navigation handled internally */ }
                    )
                }
            }
        }
    }

    override fun onStart() {
        super.onStart()
        logAppLifecycleEvent("onStart")
    }

    override fun onResume() {
        super.onResume()
        logAppLifecycleEvent("onResume")
    }

    override fun onPause() {
        super.onPause()
        logAppLifecycleEvent("onPause")
    }

    override fun onStop() {
        super.onStop()
        logAppLifecycleEvent("onStop")
    }

    override fun onDestroy() {
        super.onDestroy()
        logAppLifecycleEvent("onDestroy")
    }

    override fun onLowMemory() {
        super.onLowMemory()
        handleLowMemory()
    }

    override fun onTrimMemory(level: Int) {
        super.onTrimMemory(level)
        handleTrimMemory(level)
    }

    private fun logAppLifecycleEvent(event: String) {
        android.util.Log.d(TAG, "Activity lifecycle: $event")
    }

    private fun handleLowMemory() {
        android.util.Log.w(TAG, "System reported low memory")
        System.gc()
    }

    private fun handleTrimMemory(level: Int) {
        val levelDescription = when (level) {
            TRIM_MEMORY_RUNNING_LOW -> "RUNNING_LOW"
            TRIM_MEMORY_RUNNING_CRITICAL -> "RUNNING_CRITICAL"
            TRIM_MEMORY_UI_HIDDEN -> "UI_HIDDEN"
            TRIM_MEMORY_BACKGROUND -> "BACKGROUND"
            TRIM_MEMORY_MODERATE -> "MODERATE"
            TRIM_MEMORY_COMPLETE -> "COMPLETE"
            else -> "UNKNOWN_$level"
        }
        android.util.Log.d(TAG, "Memory trim level: $levelDescription")
    }

    companion object {
        private const val TAG = "MainActivity"
    }
}

// === ARCHIVO: app/src/main/java/com/example/offlinefirst/data/local/entity/UserEntity.kt ===
package com.example.offlinefirst.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import com.example.offlinefirst.domain.model.SyncStatus
import com.example.offlinefirst.domain.model.User
import java.util.UUID

@Entity(
    tableName = "users",
    indices = [
        Index(value = ["email"], unique = true),
        Index(value = ["username"], unique = true),
        Index(value = ["sync_status"]),
        Index(value = ["updated_at"])
    ]
)
data class UserEntity(
    @PrimaryKey
    @ColumnInfo(name = "id")
    val id: String = UUID.randomUUID().toString(),

    @ColumnInfo(name = "username")
    val username: String,

    @ColumnInfo(name = "email")
    val email: String,

    @ColumnInfo(name = "display_name")
    val displayName: String?,

    @ColumnInfo(name = "avatar_url")
    val avatarUrl: String?,

    @ColumnInfo(name = "phone")
    val phone: String?,

    @ColumnInfo(name = "is_email_verified")
    val isEmailVerified: Boolean = false,

    @ColumnInfo(name = "is_phone_verified")
    val isPhoneVerified: Boolean = false,

    @ColumnInfo(name = "sync_status")
    val syncStatus: String = SyncStatus.PENDING.name,

    @ColumnInfo(name = "created_at")
    val createdAt: Long = System.currentTimeMillis(),

    @ColumnInfo(name = "updated_at")
    val updatedAt: Long = System.currentTimeMillis(),

    @ColumnInfo(name = "last_sync_at")
    val lastSyncAt: Long? = null,

    @ColumnInfo(name = "version")
    val version: Int = 1,

    @ColumnInfo(name = "is_deleted")
    val isDeleted: Boolean = false,

    @ColumnInfo(name = "remote_id")
    val remoteId: String? = null,

    @ColumnInfo(name = "etag")
    val etag: String? = null
) {
    fun toDomain(): User {
        return User(
            id = id,
            username = username,
            email = email,
            displayName = displayName,
            avatarUrl = avatarUrl,
            phone = phone,
            isEmailVerified = isEmailVerified,
            isPhoneVerified = isPhoneVerified,
            syncStatus = SyncStatus.valueOf(syncStatus),
            createdAt = createdAt,
            updatedAt = updatedAt,
            lastSyncAt = lastSyncAt,
            version = version,
            isDeleted = isDeleted,
            remoteId = remoteId,
            etag = etag
        )
    }

    fun isSynced(): Boolean = syncStatus == SyncStatus.SYNCED.name

    fun hasPendingChanges(): Boolean = 
        syncStatus == SyncStatus.PENDING.name || syncStatus == SyncStatus.CONFLICT.name

    fun needsSync(): Boolean = 
        lastSyncAt == null || updatedAt > (lastSyncAt ?: 0L)

    fun markAsSynced(newEtag: String? = null): UserEntity {
        return copy(
            syncStatus = SyncStatus.SYNCED.name,
            lastSyncAt = System.currentTimeMillis(),
            etag = newEtag ?: etag,
            version = version + 1
        )
    }

    fun markAsPending(): UserEntity {
        return copy(
            syncStatus = SyncStatus.PENDING.name,
            updatedAt = System.currentTimeMillis()
        )
    }

    fun markAsSyncing(): UserEntity {
        return copy(syncStatus = SyncStatus.SYNCING.name)
    }

    fun markAsConflict(remoteVersion: UserEntity? = null): UserEntity {
        return copy(
            syncStatus = SyncStatus.CONFLICT.name,
            updatedAt = System.currentTimeMillis()
        )
    }

    fun markAsError(errorMessage: String? = null): UserEntity {
        return copy(
            syncStatus = SyncStatus.ERROR.name,
            updatedAt = System.currentTimeMillis()
        )
    }

    fun softDelete(): UserEntity {
        return copy(
            isDeleted = true,
            syncStatus = SyncStatus.PENDING.name,
            updatedAt = System.currentTimeMillis()
        )
    }

    fun withEmailVerification(verified: Boolean): UserEntity {
        return copy(
            isEmailVerified = verified,
            syncStatus = SyncStatus.PENDING.name,
            updatedAt = System.currentTimeMillis()
        )
    }

    fun withPhoneVerification(verified: Boolean): UserEntity {
        return copy(
            isPhoneVerified = verified,
            syncStatus = SyncStatus.PENDING.name,
            updatedAt = System.currentTimeMillis()
        )
    }

    fun withAvatar(url: String?): UserEntity {
        return copy(
            avatarUrl = url,
            syncStatus = SyncStatus.PENDING.name,
            updatedAt = System.currentTimeMillis()
        )
    }

    fun withDisplayName(name: String): UserEntity {
        require(name.isNotBlank()) { "Display name cannot be blank" }
        return copy(
            displayName = name,
            syncStatus = SyncStatus.PENDING.name,
            updatedAt = System.currentTimeMillis()
        )
    }

    fun withEmail(email: String): UserEntity {
        require(email.contains("@")) { "Invalid email format" }
        return copy(
            email = email,
            syncStatus = SyncStatus.PENDING.name,
            updatedAt = System.currentTimeMillis()
        )
    }

    fun withPhone(phone: String?): UserEntity {
        return copy(
            phone = phone,
            syncStatus = SyncStatus.PENDING.name,
            updatedAt = System.currentTimeMillis()
        )
    }

    fun canMergeWith(remote: UserEntity): Boolean {
        return this.version == remote.version || this.lastSyncAt == null
    }

    fun getConflictResolutionStrategy(other: UserEntity): ConflictStrategy {
        return when {
            this.updatedAt > other.updatedAt -> ConflictStrategy.KEEP_LOCAL
            other.updatedAt > this.updatedAt -> ConflictStrategy.KEEP_REMOTE
            else -> ConflictStrategy.MANUAL
        }
    }

    companion object {
        fun fromDomain(user: User): UserEntity {
            return UserEntity(
                id = user.id,
                username = user.username,
                email = user.email,
                displayName = user.displayName,
                avatarUrl = user.avatarUrl,
                phone = user.phone,
                isEmailVerified = user.isEmailVerified,
                isPhoneVerified = user.isPhoneVerified,
                syncStatus = user.syncStatus.name,
                createdAt = user.createdAt,
                updatedAt = user.updatedAt,
                lastSyncAt = user.lastSyncAt,
                version = user.version,
                isDeleted = user.isDeleted,
                remoteId = user.remoteId,
                etag = user.etag
            )
        }

        fun createNew(
            username: String,
            email: String,
            displayName: String? = null
        ): UserEntity {
            require(username.isNotBlank()) { "Username cannot be blank" }
            require(email.contains("@")) { "Invalid email format" }
            
            return UserEntity(
                username = username,
                email = email,
                displayName = displayName ?: username,
                syncStatus = SyncStatus.PENDING.name
            )
        }
    }
}

enum class ConflictStrategy {
    KEEP_LOCAL,
    KEEP_REMOTE,
    MANUAL,
    MERGE
}

// === ARCHIVO: app/src/main/java/com/example/offlinefirst/data/local/entity/PreferencesEntity.kt ===
package com.example.offlinefirst.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import com.example.offlinefirst.domain.model.PreferenceCategory
import com.example.offlinefirst.domain.model.PreferenceDataType
import com.example.offlinefirst.domain.model.Preferences
import com.example.offlinefirst.domain.model.SyncStatus
import java.util.UUID

@Entity(
    tableName = "preferences",
    indices = [
        Index(value = ["key"], unique = true),
        Index(value = ["user_id"]),
        Index(value = ["category"]),
        Index(value = ["sync_status"]),
        Index(value = ["updated_at"])
    ]
)
data class PreferencesEntity(
    @PrimaryKey
    @ColumnInfo(name = "id")
    val id: String = UUID.randomUUID().toString(),

    @ColumnInfo(name = "user_id")
    val userId: String?,

    @ColumnInfo(name = "key")
    val key: String,

    @ColumnInfo(name = "value")
    val value: String,

    @ColumnInfo(name = "category")
    val category: String = PreferenceCategory.GENERAL.name,

    @ColumnInfo(name = "data_type")
    val dataType: String = PreferenceDataType.STRING.name,

    @ColumnInfo(name = "is_global")
    val isGlobal: Boolean = true,

    @ColumnInfo(name = "is_encrypted")
    val isEncrypted: Boolean = false,

    @ColumnInfo(name = "sync_status")
    val syncStatus: String = SyncStatus.PENDING.name,

    @ColumnInfo(name = "created_at")
    val createdAt: Long = System.currentTimeMillis(),

    @ColumnInfo(name = "updated_at")
    val updatedAt: Long = System.currentTimeMillis(),

    @ColumnInfo(name = "last_sync_at")
    val lastSyncAt: Long? = null,

    @ColumnInfo(name = "version")
    val version: Int = 1,

    @ColumnInfo(name = "is_deleted")
    val isDeleted: Boolean = false,

    @ColumnInfo(name = "remote_id")
    val remoteId: String? = null,

    @ColumnInfo(name = "etag")
    val etag: String? = null,

    @ColumnInfo(name = "description")
    val description: String? = null,

    @ColumnInfo(name = "default_value")
    val defaultValue: String? = null,

    @ColumnInfo(name = "is_sensitive")
    val isSensitive: Boolean = false
) {
    fun toDomain(): Preferences {
        return Preferences(
            id = id,
            userId = userId,
            key = key,
            value = value,
            category = PreferenceCategory.valueOf(category),
            dataType = PreferenceDataType.valueOf(dataType),
            isGlobal = isGlobal,
            isEncrypted = isEncrypted,
            syncStatus = SyncStatus.valueOf(syncStatus),
            createdAt = createdAt,
            updatedAt = updatedAt,
            lastSyncAt = lastSyncAt,
            version = version,
            isDeleted = isDeleted,
            remoteId = remoteId,
            etag = etag,
            description = description,
            defaultValue = defaultValue,
            isSensitive = isSensitive
        )
    }

    fun isSynced(): Boolean = syncStatus == SyncStatus.SYNCED.name

    fun hasPendingChanges(): Boolean = 
        syncStatus == SyncStatus.PENDING.name || syncStatus == SyncStatus.CONFLICT.name

    fun needsSync(): Boolean = 
        lastSyncAt == null || updatedAt > (lastSyncAt ?: 0L)

    fun markAsSynced(newEtag: String? = null): PreferencesEntity {
        return copy(
            syncStatus = SyncStatus.SYNCED.name,
            lastSyncAt = System.currentTimeMillis(),
            etag = newEtag ?: etag,
            version = version + 1
        )
    }

    fun markAsPending(): PreferencesEntity {
        return copy(
            syncStatus = SyncStatus.PENDING.name,
            updatedAt = System.currentTimeMillis()
        )
    }

    fun markAsSyncing(): PreferencesEntity {
        return copy(syncStatus = SyncStatus.SYNCING.name)
    }

    fun markAsConflict(): PreferencesEntity {
        return copy(
            syncStatus = SyncStatus.CONFLICT.name,
            updatedAt = System.currentTimeMillis()
        )
    }

    fun markAsError(): PreferencesEntity {
        return copy(
            syncStatus = SyncStatus.ERROR.name,
            updatedAt = System.currentTimeMillis()
        )
    }

    fun softDelete(): PreferencesEntity {
        return copy(
            isDeleted = true,
            syncStatus = SyncStatus.PENDING.name,
            updatedAt = System.currentTimeMillis()
        )
    }

    fun withValue(newValue: String): PreferencesEntity {
        return copy(
            value = newValue,
            syncStatus = SyncStatus.PENDING.name,
            updatedAt = System.currentTimeMillis()
        )
    }

    fun withCategory(category: PreferenceCategory): PreferencesEntity {
        return copy(
            category = category.name,
            syncStatus = SyncStatus.PENDING.name,
            updatedAt = System.currentTimeMillis()
        )
    }

    fun withEncryption(encrypted: Boolean): PreferencesEntity {
        return copy(
            isEncrypted = encrypted,
            syncStatus = SyncStatus.PENDING.name,
            updatedAt = System.currentTimeMillis()
        )
    }

    fun getTypedValue(): Any? {
        return when (PreferenceDataType.valueOf(dataType)) {
            PreferenceDataType.STRING -> value
            PreferenceDataType.INTEGER -> value.toIntOrNull()
            PreferenceDataType.LONG -> value.toLongOrNull()
            PreferenceDataType.FLOAT -> value.toFloatOrNull()
            PreferenceDataType.DOUBLE -> value.toDoubleOrNull()
            PreferenceDataType.BOOLEAN -> value.toBooleanStrictOrNull()
            PreferenceDataType.JSON -> value
            PreferenceDataType.BYTE_ARRAY -> value.toByteArray()
        }
    }

    fun isValidValue(): Boolean {
        return try {
            getTypedValue() != null || value.isEmpty()
        } catch (e: Exception) {
            false
        }
    }

    fun canSync(): Boolean = 
        syncStatus == SyncStatus.PENDING.name || syncStatus == SyncStatus.ERROR.name

    fun getDataTypeEnum(): PreferenceDataType = PreferenceDataType.valueOf(dataType)

    fun getCategoryEnum(): PreferenceCategory = PreferenceCategory.valueOf(category)

    fun isNumeric(): Boolean = getDataTypeEnum().isNumeric()

    fun isBoolean(): Boolean = getDataTypeEnum().isBoolean()

    fun isString(): Boolean = getDataTypeEnum().isString()

    companion object {
        fun fromDomain(preferences: Preferences): PreferencesEntity {
            return PreferencesEntity(
                id = preferences.id,
                userId = preferences.userId,
                key = preferences.key,
                value = preferences.value,
                category = preferences.category.name,
                dataType = preferences.dataType.name,
                isGlobal = preferences.isGlobal,
                isEncrypted = preferences.isEncrypted,
                syncStatus = preferences.syncStatus.name,
                createdAt = preferences.createdAt,
                updatedAt = preferences.updatedAt,
                lastSyncAt = preferences.lastSyncAt,
                version = preferences.version,
                isDeleted = preferences.isDeleted,
                remoteId = preferences.remoteId,
                etag = preferences.etag,
                description = preferences.description,
                defaultValue = preferences.defaultValue,
                isSensitive = preferences.isSensitive
            )
        }

        fun createGlobal(
            key: String,
            value: String,
            category: PreferenceCategory = PreferenceCategory.GENERAL,
            dataType: PreferenceDataType = PreferenceDataType.STRING
        ): PreferencesEntity {
            require(key.isNotBlank()) { "Preference key cannot be blank" }
            
            return PreferencesEntity(
                key = key,
                value = value,
                category = category.name,
                dataType = dataType.name,
                isGlobal = true,
                userId = null,
                syncStatus = SyncStatus.PENDING.name
            )
        }

        fun createUserSpecific(
            userId: String,
            key: String,
            value: String,
            category: PreferenceCategory = PreferenceCategory.USER,
            dataType: PreferenceDataType = PreferenceDataType.STRING
        ): PreferencesEntity {
            require(key.isNotBlank()) { "Preference key cannot be blank" }
            require(userId.isNotBlank()) { "User ID cannot be blank for user-specific preference" }
            
            return PreferencesEntity(
                userId = userId,
                key = key,
                value = value,
                category = category.name,
                dataType = dataType.name,
                isGlobal = false,
                syncStatus = SyncStatus.PENDING.name
            )
        }
    }
}

// === ARCHIVO: app/src/main/java/com/example/offlinefirst/data/local/entity/DownloadedContentEntity.kt ===
package com.example.offlinefirst.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.offlinefirst.domain.model.ContentCategory
import com.example.offlinefirst.domain.model.SyncStatus

@Entity(tableName = "downloaded_content")
data class DownloadedContentEntity(
    @PrimaryKey
    @ColumnInfo(name = "id")
    val id: String,

    @ColumnInfo(name = "title")
    val title: String,

    @ColumnInfo(name = "description")
    val description: String?,

    @ColumnInfo(name = "url")
    val url: String,

    @ColumnInfo(name = "local_path")
    val localPath: String?,

    @ColumnInfo(name = "file_size")
    val fileSize: Long,

    @ColumnInfo(name = "downloaded_size")
    val downloadedSize: Long,

    @ColumnInfo(name = "content_hash")
    val contentHash: String?,

    @ColumnInfo(name = "sync_status")
    val syncStatus: String,

    @ColumnInfo(name = "category")
    val category: String,

    @ColumnInfo(name = "tags")
    val tags: String,

    @ColumnInfo(name = "metadata")
    val metadata: String?,

    @ColumnInfo(name = "expires_at")
    val expiresAt: Long?,

    @ColumnInfo(name = "created_at")
    val createdAt: Long,

    @ColumnInfo(name = "updated_at")
    val updatedAt: Long,

    @ColumnInfo(name = "accessed_at")
    val accessedAt: Long,

    @ColumnInfo(name = "last_sync_at")
    val lastSyncAt: Long?,

    @ColumnInfo(name = "is_available_offline")
    val isAvailableOffline: Boolean
) {
    fun toDomainModel(): com.example.offlinefirst.domain.model.DownloadedContent {
        return com.example.offlinefirst.domain.model.DownloadedContent(
            id = id,
            title = title,
            description = description,
            url = url,
            localPath = localPath,
            fileSize = fileSize,
            downloadedSize = downloadedSize,
            contentHash = contentHash,
            syncStatus = SyncStatus.valueOf(syncStatus),
            category = ContentCategory.valueOf(category),
            tags = tags.split(",").filter { it.isNotBlank() },
            metadata = metadata?.let {
                try {
                    com.example.offlinefirst.domain.model.DownloadedContent.parseMetadata(it)
                } catch (e: Exception) {
                    emptyMap()
                }
            } ?: emptyMap(),
            expiresAt = expiresAt,
            createdAt = createdAt,
            updatedAt = updatedAt,
            accessedAt = accessedAt,
            lastSyncAt = lastSyncAt,
            isAvailableOffline = isAvailableOffline
        )
    }

    companion object {
        fun fromDomainModel(domain: com.example.offlinefirst.domain.model.DownloadedContent): DownloadedContentEntity {
            return DownloadedContentEntity(
                id = domain.id,
                title = domain.title,
                description = domain.description,
                url = domain.url,
                localPath = domain.localPath,
                fileSize = domain.fileSize,
                downloadedSize = domain.downloadedSize,
                contentHash = domain.contentHash,
                syncStatus = domain.syncStatus.name,
                category = domain.category.name,
                tags = domain.tags.joinToString(","),
                metadata = domain.metadata.takeIf { it.isNotEmpty() }?.let {
                    com.example.offlinefirst.domain.model.DownloadedContent.serializeMetadata(it)
                },
                expiresAt = domain.expiresAt,
                createdAt = domain.createdAt,
                updatedAt = domain.updatedAt,
                accessedAt = domain.accessedAt,
                lastSyncAt = domain.lastSyncAt,
                isAvailableOffline = domain.isAvailableOffline
            )
        }
    }
}

// === ARCHIVO: app/src/main/java/com/example/offlinefirst/data/local/entity/SyncOperationEntity.kt ===
package com.example.offlinefirst.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "sync_operation",
    indices = [
        Index(value = ["entity_type", "entity_id"]),
        Index(value = ["status"]),
        Index(value = ["created_at"])
    ]
)
data class SyncOperationEntity(
    @PrimaryKey
    @ColumnInfo(name = "id")
    val id: String,

    @ColumnInfo(name = "entity_type")
    val entityType: String,

    @ColumnInfo(name = "entity_id")
    val entityId: String,

    @ColumnInfo(name = "operation_type")
    val operationType: String,

    @ColumnInfo(name = "payload")
    val payload: String,

    @ColumnInfo(name = "status")
    val status: String,

    @ColumnInfo(name = "retry_count")
    val retryCount: Int,

    @ColumnInfo(name = "max_retries")
    val maxRetries: Int,

    @ColumnInfo(name = "created_at")
    val createdAt: Long,

    @ColumnInfo(name = "last_attempt_at")
    val lastAttemptAt: Long?,

    @ColumnInfo(name = "error_message")
    val errorMessage: String?,

    @ColumnInfo(name = "priority")
    val priority: Int,

    @ColumnInfo(name = "depends_on")
    val dependsOn: String?
) {
    fun toDomainModel(): com.example.offlinefirst.domain.model.SyncOperation {
        return com.example.offlinefirst.domain.model.SyncOperation(
            id = id,
            entityType = com.example.offlinefirst.domain.model.EntityType.valueOf(entityType),
            entityId = entityId,
            operationType = com.example.offlinefirst.domain.model.OperationType.valueOf(operationType),
            payload = try {
                com.example.offlinefirst.domain.model.SyncOperation.parsePayload(payload)
            } catch (e: Exception) {
                emptyMap()
            },
            status = com.example.offlinefirst.domain.model.OperationStatus.valueOf(status),
            retryCount = retryCount,
            maxRetries = maxRetries,
            createdAt = createdAt,
            lastAttemptAt = lastAttemptAt,
            errorMessage = errorMessage,
            priority = priority,
            dependsOn = dependsOn
        )
    }

    companion object {
        fun fromDomainModel(domain: com.example.offlinefirst.domain.model.SyncOperation): SyncOperationEntity {
            return SyncOperationEntity(
                id = domain.id,
                entityType = domain.entityType.name,
                entityId = domain.entityId,
                operationType = domain.operationType.name,
                payload = com.example.offlinefirst.domain.model.SyncOperation.serializePayload(domain.payload),
                status = domain.status.name,
                retryCount = domain.retryCount,
                maxRetries = domain.maxRetries,
                createdAt = domain.createdAt,
                lastAttemptAt = domain.lastAttemptAt,
                errorMessage = domain.errorMessage,
                priority = domain.priority,
                dependsOn = domain.dependsOn
            )
        }
    }
}

// === ARCHIVO: app/src/main/java/com/example/offlinefirst/data/local/dao/UserDao.kt ===
package com.example.offlinefirst.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Update
import com.example.offlinefirst.data.local.entity.UserEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDao {

    @Query("SELECT * FROM users WHERE id = :userId")
    suspend fun getUserById(userId: String): UserEntity?

    @Query("SELECT * FROM users WHERE id = :userId")
    fun observeUserById(userId: String): Flow<UserEntity?>

    @Query("SELECT * FROM users WHERE sync_status = :status")
    suspend fun getUsersBySyncStatus(status: String): List<UserEntity>

    @Query("SELECT * FROM users WHERE sync_status IN ('PENDING', 'CONFLICT', 'ERROR')")
    suspend fun getUsersWithPendingSync(): List<UserEntity>

    @Query("SELECT * FROM users WHERE needs_sync = 1")
    suspend fun getUsersNeedingSync(): List<UserEntity>

    @Query("SELECT * FROM users ORDER BY updated_at DESC")
    fun observeAllUsers(): Flow<List<UserEntity>>

    @Query("SELECT * FROM users ORDER BY updated_at DESC LIMIT :limit OFFSET :offset")
    suspend fun getUsersPaginated(limit: Int, offset: Int): List<UserEntity>

    @Query("SELECT COUNT(*) FROM users")
    suspend fun getUserCount(): Int

    @Query("SELECT COUNT(*) FROM users WHERE sync_status = :status")
    suspend fun getUserCountBySyncStatus(status: String): Int

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUser(user: UserEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUsers(users: List<UserEntity>)

    @Update
    suspend fun updateUser(user: UserEntity)

    @Delete
    suspend fun deleteUser(user: UserEntity)

    @Query("DELETE FROM users WHERE id = :userId")
    suspend fun deleteUserById(userId: String)

    @Query("DELETE FROM users")
    suspend fun deleteAllUsers()

    @Query("UPDATE users SET sync_status = :status, last_sync_at = :syncTimestamp WHERE id = :userId")
    suspend fun updateSyncStatus(userId: String, status: String, syncTimestamp: Long)

    @Query("UPDATE users SET sync_status = 'PENDING', updated_at = :timestamp WHERE id = :userId")
    suspend fun markUserAsPending(userId: String, timestamp: Long)

    @Query("UPDATE users SET sync_status = 'ERROR', error_message = :errorMessage WHERE id = :userId")
    suspend fun markUserAsError(userId: String, errorMessage: String)

    @Transaction
    suspend fun upsertUser(user: UserEntity) {
        val existing = getUserById(user.id)
        if (existing != null) {
            updateUser(user)
        } else {
            insertUser(user)
        }
    }

    @Query("SELECT * FROM users WHERE email = :email LIMIT 1")
    suspend fun getUserByEmail(email: String): UserEntity?

    @Query("SELECT * FROM users WHERE username = :username LIMIT 1")
    suspend fun getUserByUsername(username: String): UserEntity?

    @Query("""
        SELECT * FROM users 
        WHERE (:query IS NULL OR username LIKE '%' || :query || '%' OR email LIKE '%' || :query || '%' OR display_name LIKE '%' || :query || '%')
        ORDER BY 
            CASE WHEN :sortBy = 'username' THEN username END ASC,
            CASE WHEN :sortBy = 'email' THEN email END ASC,
            CASE WHEN :sortBy = 'updated_at' THEN updated_at END DESC,
            CASE WHEN :sortBy = 'created_at' THEN created_at END DESC
        LIMIT :limit OFFSET :offset
    """)
    suspend fun searchUsers(query: String?, sortBy: String, limit: Int, offset: Int): List<UserEntity>
}

// === ARCHIVO: app/src/main/java/com/example/offlinefirst/data/local/dao/PreferencesDao.kt ===
package com.example.offlinefirst.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Update
import com.example.offlinefirst.data.local.entity.PreferencesEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface PreferencesDao {

    @Query("SELECT * FROM preferences WHERE id = :preferenceId")
    suspend fun getPreferenceById(preferenceId: String): PreferencesEntity?

    @Query("SELECT * FROM preferences WHERE id = :preferenceId")
    fun observePreferenceById(preferenceId: String): Flow<PreferencesEntity?>

    @Query("SELECT * FROM preferences WHERE `key` = :key LIMIT 1")
    suspend fun getPreferenceByKey(key: String): PreferencesEntity?

    @Query("SELECT * FROM preferences WHERE `key` = :key AND (user_id = :userId OR (user_id IS NULL AND :userId IS NULL)) LIMIT 1")
    suspend fun getPreferenceByKeyAndUser(key: String, userId: String?): PreferencesEntity?

    @Query("SELECT * FROM preferences WHERE category = :category ORDER BY `key` ASC")
    suspend fun getPreferencesByCategory(category: String): List<PreferencesEntity>

    @Query("SELECT * FROM preferences WHERE category = :category")
    fun observePreferencesByCategory(category: String): Flow<List<PreferencesEntity>>

    @Query("SELECT * FROM preferences WHERE user_id = :userId OR (user_id IS NULL AND is_global = 1) ORDER BY `key` ASC")
    suspend fun getPreferencesForUser(userId: String?): List<PreferencesEntity>

    @Query("SELECT * FROM preferences WHERE user_id = :userId OR (user_id IS NULL AND is_global = 1)")
    fun observePreferencesForUser(userId: String?): Flow<List<PreferencesEntity>>

    @Query("SELECT * FROM preferences WHERE sync_status = :status")
    suspend fun getPreferencesBySyncStatus(status: String): List<PreferencesEntity>

    @Query("SELECT * FROM preferences WHERE sync_status IN ('PENDING', 'CONFLICT', 'ERROR')")
    suspend fun getPreferencesWithPendingSync(): List<PreferencesEntity>

    @Query("SELECT * FROM preferences WHERE needs_sync = 1")
    suspend fun getPreferencesNeedingSync(): List<PreferencesEntity>

    @Query("SELECT * FROM preferences ORDER BY updated_at DESC")
    fun observeAllPreferences(): Flow<List<PreferencesEntity>>

    @Query("SELECT * FROM preferences ORDER BY updated_at DESC LIMIT :limit OFFSET :offset")
    suspend fun getPreferencesPaginated(limit: Int, offset: Int): List<PreferencesEntity>

    @Query("SELECT COUNT(*) FROM preferences")
    suspend fun getPreferenceCount(): Int

    @Query("SELECT COUNT(*) FROM preferences WHERE category = :category")
    suspend fun getPreferenceCountByCategory(category: String): Int

    @Query("SELECT COUNT(*) FROM preferences WHERE sync_status = :status")
    suspend fun getPreferenceCountBySyncStatus(status: String): Int

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPreference(preference: PreferencesEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPreferences(preferences: List<PreferencesEntity>)

    @Update
    suspend fun updatePreference(preference: PreferencesEntity)

    @Delete
    suspend fun deletePreference(preference: PreferencesEntity)

    @Query("DELETE FROM preferences WHERE id = :preferenceId")
    suspend fun deletePreferenceById(preferenceId: String)

    @Query("DELETE FROM preferences WHERE category = :category")
    suspend fun deletePreferencesByCategory(category: String)

    @Query("DELETE FROM preferences WHERE user_id = :userId AND is_global = 0")
    suspend fun deletePreferencesForUser(userId: String)

    @Query("DELETE FROM preferences")
    suspend fun deleteAllPreferences()

    @Query("UPDATE preferences SET sync_status = :status, last_sync_at = :syncTimestamp WHERE id = :preferenceId")
    suspend fun updateSyncStatus(preferenceId: String, status: String, syncTimestamp: Long)

    @Query("UPDATE preferences SET sync_status = 'PENDING', updated_at = :timestamp WHERE id = :preferenceId")
    suspend fun markPreferenceAsPending(preferenceId: String, timestamp: Long)

    @Query("UPDATE preferences SET sync_status = 'ERROR', error_message = :errorMessage WHERE id = :preferenceId")
    suspend fun markPreferenceAsError(preferenceId: String, errorMessage: String)

    @Transaction
    suspend fun upsertPreference(preference: PreferencesEntity) {
        val existing = getPreferenceById(preference.id)
        if (existing != null) {
            updatePreference(preference)
        } else {
            insertPreference(preference)
        }
    }

    @Query("""
        SELECT * FROM preferences 
        WHERE (:query IS NULL OR `key` LIKE '%' || :query || '%' OR value LIKE '%' || :query || '%')
        AND (:category IS NULL OR category = :category)
        AND (:userId IS NULL OR user_id = :userId OR (user_id IS NULL AND is_global = 1))
        ORDER BY 
            CASE WHEN :sortBy = 'key' THEN `key` END ASC,
            CASE WHEN :sortBy = 'category' THEN category END ASC,
            CASE WHEN :sortBy = 'updated_at' THEN updated_at END DESC,
            CASE WHEN :sortBy = 'created_at' THEN created_at END DESC
        LIMIT :limit OFFSET :offset
    """)
    suspend fun searchPreferences(query: String?, category: String?, userId: String?, sortBy: String, limit: Int, offset: Int): List<PreferencesEntity>

    @Query("SELECT DISTINCT category FROM preferences ORDER BY category ASC")
    suspend fun getAllCategories(): List<String>

    @Query("SELECT * FROM preferences WHERE is_encrypted = 1")
    suspend fun getEncryptedPreferences(): List<PreferencesEntity>
}

// === ARCHIVO: app/src/main/java/com/example/offlinefirst/data/local/dao/ContentDao.kt ===
package com.example.offlinefirst.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Update
import com.example.offlinefirst.data.local.entity.DownloadedContentEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface ContentDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(content: DownloadedContentEntity): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(contents: List<DownloadedContentEntity>): List<Long>

    @Update
    suspend fun update(content: DownloadedContentEntity)

    @Delete
    suspend fun delete(content: DownloadedContentEntity)

    @Query("DELETE FROM downloaded_content WHERE id = :id")
    suspend fun deleteById(id: String)

    @Query("DELETE FROM downloaded_content WHERE syncStatus = :status")
    suspend fun deleteBySyncStatus(status: String)

    @Query("SELECT * FROM downloaded_content WHERE id = :id")
    suspend fun getById(id: String): DownloadedContentEntity?

    @Query("SELECT * FROM downloaded_content WHERE id = :id")
    fun getByIdFlow(id: String): Flow<DownloadedContentEntity?>

    @Query("SELECT * FROM downloaded_content ORDER BY createdAt DESC")
    fun getAll(): Flow<List<DownloadedContentEntity>>

    @Query("SELECT * FROM downloaded_content ORDER BY createdAt DESC LIMIT :limit OFFSET :offset")
    suspend fun getAllPaginated(limit: Int, offset: Int): List<DownloadedContentEntity>

    @Query("SELECT * FROM downloaded_content WHERE syncStatus = :status ORDER BY createdAt ASC")
    fun getBySyncStatus(status: String): Flow<List<DownloadedContentEntity>>

    @Query("SELECT * FROM downloaded_content WHERE syncStatus IN (:statuses) ORDER BY createdAt ASC")
    suspend fun getBySyncStatuses(statuses: List<String>): List<DownloadedContentEntity>

    @Query("SELECT * FROM downloaded_content WHERE category = :category ORDER BY createdAt DESC")
    fun getByCategory(category: String): Flow<List<DownloadedContentEntity>>

    @Query("SELECT * FROM downloaded_content WHERE category IN (:categories) ORDER BY createdAt DESC")
    fun getByCategories(categories: List<String>): Flow<List<DownloadedContentEntity>>

    @Query("SELECT * FROM downloaded_content WHERE localPath IS NOT NULL AND downloadedSize = fileSize ORDER BY accessedAt DESC")
    fun getDownloaded(): Flow<List<DownloadedContentEntity>>

    @Query("SELECT * FROM downloaded_content WHERE localPath IS NOT NULL AND downloadedSize = fileSize AND expiresAt IS NOT NULL AND expiresAt < :currentTime ORDER BY expiresAt ASC")
    suspend fun getExpired(currentTime: Long): List<DownloadedContentEntity>

    @Query("SELECT * FROM downloaded_content WHERE localPath IS NOT NULL AND downloadedSize = fileSize AND (expiresAt IS NULL OR expiresAt > :currentTime) AND isAvailableOffline = 1 ORDER BY accessedAt DESC")
    fun getAvailableOffline(currentTime: Long): Flow<List<DownloadedContentEntity>>

    @Query("SELECT * FROM downloaded_content WHERE url = :url LIMIT 1")
    suspend fun getByUrl(url: String): DownloadedContentEntity?

    @Query("SELECT * FROM downloaded_content WHERE title LIKE '%' || :query || '%' OR description LIKE '%' || :query || '%' ORDER BY createdAt DESC")
    fun search(query: String): Flow<List<DownloadedContentEntity>>

    @Query("SELECT COUNT(*) FROM downloaded_content")
    suspend fun getCount(): Int

    @Query("SELECT COUNT(*) FROM downloaded_content WHERE syncStatus = :status")
    suspend fun getCountBySyncStatus(status: String): Int

    @Query("SELECT COUNT(*) FROM downloaded_content WHERE category = :category")
    suspend fun getCountByCategory(category: String): Int

    @Query("SELECT SUM(fileSize) FROM downloaded_content")
    suspend fun getTotalSize(): Long?

    @Query("SELECT SUM(downloadedSize) FROM downloaded_content")
    suspend fun getTotalDownloadedSize(): Long?

    @Query("SELECT SUM(fileSize) FROM downloaded_content WHERE localPath IS NOT NULL AND downloadedSize = fileSize")
    suspend fun getTotalCachedSize(): Long?

    @Query("UPDATE downloaded_content SET syncStatus = :status, updatedAt = :updatedAt WHERE id = :id")
    suspend fun updateSyncStatus(id: String, status: String, updatedAt: Long)

    @Query("UPDATE downloaded_content SET localPath = :localPath, downloadedSize = :downloadedSize, syncStatus = :status, updatedAt = :updatedAt WHERE id = :id")
    suspend fun updateDownloadProgress(id: String, localPath: String?, downloadedSize: Long, status: String, updatedAt: Long)

    @Query("UPDATE downloaded_content SET accessedAt = :accessedAt WHERE id = :id")
    suspend fun updateAccessTime(id: String, accessedAt: Long)

    @Transaction
    @Query("SELECT * FROM downloaded_content WHERE syncStatus = 'PENDING' OR syncStatus = 'ERROR' ORDER BY createdAt ASC")
    suspend fun getPendingSync(): List<DownloadedContentEntity>

    @Query("DELETE FROM downloaded_content WHERE expiresAt IS NOT NULL AND expiresAt < :currentTime")
    suspend fun deleteExpired(currentTime: Long): Int

    @Query("SELECT * FROM downloaded_content WHERE tags LIKE '%' || :tag || '%' ORDER BY createdAt DESC")
    fun getByTag(tag: String): Flow<List<DownloadedContentEntity>>

    @Query("UPDATE downloaded_content SET tags = :tags, updatedAt = :updatedAt WHERE id = :id")
    suspend fun updateTags(id: String, tags: String, updatedAt: Long)

    @Query("UPDATE downloaded_content SET accessCount = accessCount + 1, accessedAt = :accessedAt WHERE id = :id")
    suspend fun incrementAccessCount(id: String, accessedAt: Long)

    @Query("SELECT * FROM downloaded_content WHERE metadata LIKE '%' || :key || '%' ORDER BY createdAt DESC")
    fun getByMetadataKey(key: String): Flow<List<DownloadedContentEntity>>
}

// === ARCHIVO: app/src/main/java/com/example/offlinefirst/data/local/dao/SyncOperationDao.kt ===
package com.example.offlinefirst.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Update
import com.example.offlinefirst.data.local.entity.SyncOperationEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface SyncOperationDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(operation: SyncOperationEntity): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(operations: List<SyncOperationEntity>): List<Long>

    @Update
    suspend fun update(operation: SyncOperationEntity)

    @Delete
    suspend fun delete(operation: SyncOperationEntity)

    @Query("DELETE FROM sync_operation WHERE id = :id")
    suspend fun deleteById(id: String)

    @Query("DELETE FROM sync_operation WHERE status = :status")
    suspend fun deleteByStatus(status: String)

    @Query("DELETE FROM sync_operation WHERE executedAt IS NOT NULL AND executedAt < :olderThan")
    suspend fun deleteOldCompleted(olderThan: Long): Int

    @Query("SELECT * FROM sync_operation WHERE id = :id")
    suspend fun getById(id: String): SyncOperationEntity?

    @Query("SELECT * FROM sync_operation WHERE id = :id")
    fun getByIdFlow(id: String): Flow<SyncOperationEntity?>

    @Query("SELECT * FROM sync_operation ORDER BY createdAt ASC")
    fun getAll(): Flow<List<SyncOperationEntity>>

    @Query("SELECT * FROM sync_operation ORDER BY createdAt ASC LIMIT :limit")
    suspend fun getAllLimited(limit: Int): List<SyncOperationEntity>

    @Query("SELECT * FROM sync_operation WHERE status = :status ORDER BY createdAt ASC")
    fun getByStatus(status: String): Flow<List<SyncOperationEntity>>

    @Query("SELECT * FROM sync_operation WHERE status = :status ORDER BY createdAt ASC LIMIT :limit")
    suspend fun getPendingByStatus(status: String, limit: Int): List<SyncOperationEntity>

    @Query("SELECT * FROM sync_operation WHERE status IN (:statuses) ORDER BY createdAt ASC LIMIT :limit")
    suspend fun getByStatuses(statuses: List<String>, limit: Int): List<SyncOperationEntity>

    @Query("SELECT * FROM sync_operation WHERE entityType = :entityType AND status = :status ORDER BY createdAt ASC")
    suspend fun getByEntityTypeAndStatus(entityType: String, status: String): List<SyncOperationEntity>

    @Query("SELECT * FROM sync_operation WHERE entityType = :entityType AND entityId = :entityId AND status IN ('PENDING', 'IN_PROGRESS') ORDER BY createdAt ASC LIMIT 1")
    suspend fun getPendingOperationForEntity(entityType: String, entityId: String): SyncOperationEntity?

    @Query("SELECT * FROM sync_operation WHERE operationType = :operationType ORDER BY createdAt ASC")
    fun getByOperationType(operationType: String): Flow<List<SyncOperationEntity>>

    @Query("SELECT COUNT(*) FROM sync_operation")
    suspend fun getCount(): Int

    @Query("SELECT COUNT(*) FROM sync_operation WHERE status = :status")
    suspend fun getCountByStatus(status: String): Int

    @Query("SELECT COUNT(*) FROM sync_operation WHERE entityType = :entityType")
    suspend fun getCountByEntityType(entityType: String): Int

    @Query("SELECT COUNT(*) FROM sync_operation WHERE operationType = :operationType AND status = :status")
    suspend fun getCountByOperationTypeAndStatus(operationType: String, status: String): Int

    @Query("UPDATE sync_operation SET status = :status, updatedAt = :updatedAt WHERE id = :id")
    suspend fun updateStatus(id: String, status: String, updatedAt: Long)

    @Query("UPDATE sync_operation SET status = :status, executedAt = :executedAt, updatedAt = :updatedAt WHERE id = :id")
    suspend fun markAsCompleted(id: String, status: String, executedAt: Long, updatedAt: Long)

    @Query("UPDATE sync_operation SET status = :status, errorMessage = :errorMessage, retryCount = retryCount + 1, updatedAt = :updatedAt WHERE id = :id")
    suspend fun markAsFailed(id: String, status: String, errorMessage: String?, updatedAt: Long)

    @Query("UPDATE sync_operation SET retryCount = retryCount + 1, updatedAt = :updatedAt WHERE id = :id")
    suspend fun incrementRetryCount(id: String, updatedAt: Long)

    @Query("SELECT * FROM sync_operation WHERE status = 'PENDING' ORDER BY createdAt ASC LIMIT 1")
    suspend fun getNextPending(): SyncOperationEntity?

    @Query("SELECT * FROM sync_operation WHERE status = 'PENDING' ORDER BY createdAt ASC LIMIT :batchSize")
    suspend fun getNextBatch(batchSize: Int): List<SyncOperationEntity>

    @Query("SELECT * FROM sync_operation WHERE status IN ('PENDING', 'IN_PROGRESS') ORDER BY createdAt ASC")
    fun getActiveOperations(): Flow<List<SyncOperationEntity>>

    @Query("SELECT * FROM sync_operation WHERE retryCount < :maxRetries AND status = 'FAILED' ORDER BY createdAt ASC")
    suspend fun getRetryableOperations(maxRetries: Int): List<SyncOperationEntity>

    @Query("SELECT * FROM sync_operation WHERE retryCount >= :maxRetries AND status = 'FAILED' ORDER BY createdAt ASC")
    suspend fun getFailedOperations(maxRetries: Int): List<SyncOperationEntity>

    @Query("DELETE FROM sync_operation WHERE entityType = :entityType AND entityId = :entityId AND status = 'PENDING'")
    suspend fun cancelPendingForEntity(entityType: String, entityId: String): Int

    @Query("SELECT EXISTS(SELECT 1 FROM sync_operation WHERE entityType = :entityType AND entityId = :entityId AND status IN ('PENDING', 'IN_PROGRESS'))")
    suspend fun hasPendingOperation(entityType: String, entityId: String): Boolean

    @Transaction
    @Query("SELECT * FROM sync_operation WHERE status = 'PENDING' ORDER BY createdAt ASC FOR UPDATE SKIP LOCKED")
    suspend fun getAndLockNextPending(): SyncOperationEntity?

    @Query("SELECT MAX(createdAt) FROM sync_operation WHERE entityType = :entityType AND status = 'COMPLETED'")
    suspend fun getLastCompletedTime(entityType: String): Long?

    @Query("SELECT * FROM sync_operation WHERE createdAt > :since ORDER BY createdAt ASC")
    suspend fun getOperationsSince(since: Long): List<SyncOperationEntity>
}

// === ARCHIVO: app/src/main/java/com/example/offlinefirst/data/local/database/OfflineFirstDatabase.kt ===
package com.example.offlinefirst.data.local.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.offlinefirst.data.local.dao.ContentDao
import com.example.offlinefirst.data.local.dao.PreferencesDao
import com.example.offlinefirst.data.local.dao.SyncOperationDao
import com.example.offlinefirst.data.local.dao.UserDao
import com.example.offlinefirst.data.local.entity.DownloadedContentEntity
import com.example.offlinefirst.data.local.entity.PreferencesEntity
import com.example.offlinefirst.data.local.entity.SyncOperationEntity
import com.example.offlinefirst.data.local.entity.UserEntity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.concurrent.Executors

@Database(
    entities = [
        UserEntity::class,
        PreferencesEntity::class,
        DownloadedContentEntity::class,
        SyncOperationEntity::class
    ],
    version = 1,
    exportSchema = true
)
@TypeConverters(Converters::class)
abstract class OfflineFirstDatabase : RoomDatabase() {

    abstract fun userDao(): UserDao
    abstract fun preferencesDao(): PreferencesDao
    abstract fun contentDao(): ContentDao
    abstract fun syncOperationDao(): SyncOperationDao

    companion object {
        private const val DATABASE_NAME = "offline_first_database"
        private const val SCHEMA_FILE_NAME = "offline_first_schema"

        @Volatile
        private var INSTANCE: OfflineFirstDatabase? = null

        fun getInstance(context: Context): OfflineFirstDatabase {
            return INSTANCE ?: synchronized(this) {
                INSTANCE ?: buildDatabase(context).also { INSTANCE = it }
            }
        }

        private fun buildDatabase(context: Context): OfflineFirstDatabase {
            return Room.databaseBuilder(
                context.applicationContext,
                OfflineFirstDatabase::class.java,
                DATABASE_NAME
            )
                .addCallback(DatabaseCallback())
                .addMigrations()
                .setJournalMode(JournalMode.TRUNCATE)
                .setQueryCallback({ sql, bindArgs ->
                    android.util.Log.d("DatabaseQuery", "SQL: $sql, Args: $bindArgs")
                }, Executors.newSingleThreadExecutor())
                .fallbackToDestructiveMigration()
                .build()
        }

        fun destroyInstance() {
            INSTANCE?.close()
            INSTANCE = null
        }
    }

    private class DatabaseCallback : Callback() {
        override fun onCreate(db: SupportSQLiteDatabase) {
            super.onCreate(db)
            android.util.Log.i("Database", "Creating database schema")
            CoroutineScope(Dispatchers.IO).launch {
                INSTANCE?.let { database ->
                    initializeDefaultData(database)
                }
            }
        }

        override fun onOpen(db: SupportSQLiteDatabase) {
            super.onOpen(db)
            android.util.Log.i("Database", "Database opened")
            db.execSQL("PRAGMA foreign_keys = ON")
            db.execSQL("PRAGMA journal_mode = TRUNCATE")
            db.execSQL("PRAGMA synchronous = NORMAL")
            db.execSQL("PRAGMA cache_size = 10000")
            db.execSQL("PRAGMA temp_store = MEMORY")
        }

        override fun onDestructiveMigration(db: SupportSQLiteDatabase) {
            super.onDestructiveMigration(db)
            android.util.Log.w("Database", "Destructive migration performed - all data lost")
        }

        private suspend fun initializeDefaultData(database: OfflineFirstDatabase) {
            try {
                val currentTime = System.currentTimeMillis()
                android.util.Log.d("Database", "Initializing default data")
            } catch (e: Exception) {
                android.util.Log.e("Database", "Error initializing default data", e)
            }
        }
    }
}

// === ARCHIVO: app/src/main/java/com/example/offlinefirst/data/repository/UserRepositoryImpl.kt ===
package com.example.offlinefirst.data.repository

import com.example.offlinefirst.data.local.dao.UserDao
import com.example.offlinefirst.data.local.entity.UserEntity
import com.example.offlinefirst.domain.model.SyncStatus
import com.example.offlinefirst.domain.model.User
import com.example.offlinefirst.domain.model.UserValidationResult
import com.example.offlinefirst.domain.repository.UserRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserRepositoryImpl @Inject constructor(
    private val userDao: UserDao
) : UserRepository {

    override suspend fun getUser(userId: String): User? {
        return try {
            val entity = userDao.getById(userId)
            entity?.toDomain()
        } catch (e: Exception) {
            android.util.Log.e("UserRepository", "Error getting user: ${e.message}", e)
            null
        }
    }

    override fun getUserFlow(userId: String): Flow<User?> {
        return userDao.getByIdFlow(userId).map { it?.toDomain() }
    }

    override fun getCurrentUser(): Flow<User?> {
        return userDao.getCurrentUserFlow().map { it?.toDomain() }
    }

    override suspend fun saveUser(user: User): Result<User> {
        return try {
            val validation = user.validate()
            if (!validation.isValid()) {
                return Result.failure(IllegalArgumentException(validation.getErrors().joinToString(", ")))
            }
            val entity = user.toEntity()
            userDao.insert(entity)
            Result.success(user)
        } catch (e: Exception) {
            android.util.Log.e("UserRepository", "Error saving user: ${e.message}", e)
            Result.failure(e)
        }
    }

    override suspend fun updateUser(user: User): Result<User> {
        return try {
            val existingEntity = userDao.getById(user.id)
            if (existingEntity == null) {
                return Result.failure(IllegalArgumentException("User not found: ${user.id}"))
            }
            val updatedUser = user.withUpdatedTimestamp()
            val validation = updatedUser.validate()
            if (!validation.isValid()) {
                return Result.failure(IllegalArgumentException(validation.getErrors().joinToString(", ")))
            }
            val entity = updatedUser.toEntity()
            userDao.update(entity)
            Result.success(updatedUser)
        } catch (e: Exception) {
            android.util.Log.e("UserRepository", "Error updating user: ${e.message}", e)
            Result.failure(e)
        }
    }

    override suspend fun deleteUser(userId: String): Result<Unit> {
        return try {
            userDao.deleteById(userId)
            Result.success(Unit)
        } catch (e: Exception) {
            android.util.Log.e("UserRepository", "Error deleting user: ${e.message}", e)
            Result.failure(e)
        }
    }

    override suspend fun getAllUsers(): List<User> {
        return try {
            userDao.getAll().first().map { it.toDomain() }
        } catch (e: Exception) {
            android.util.Log.e("UserRepository", "Error getting all users: ${e.message}", e)
            emptyList()
        }
    }

    override fun getAllUsersFlow(): Flow<List<User>> {
        return userDao.getAll().map { entities ->
            entities.map { it.toDomain() }
        }
    }

    override suspend fun getUsersBySyncStatus(syncStatus: SyncStatus): List<User> {
        return try {
            userDao.getBySyncStatus(syncStatus.name).first().map { it.toDomain() }
        } catch (e: Exception) {
            android.util.Log.e("UserRepository", "Error getting users by sync status: ${e.message}", e)
            emptyList()
        }
    }

    override fun getUsersBySyncStatusFlow(syncStatus: SyncStatus): Flow<List<User>> {
        return userDao.getBySyncStatus(syncStatus.name).map { entities ->
            entities.map { it.toDomain() }
        }
    }

    override suspend fun getPendingSyncUsers(): List<User> {
        return try {
            userDao.getPendingSync().first().map { it.toDomain() }
        } catch (e: Exception) {
            android.util.Log.e("UserRepository", "Error getting pending sync users: ${e.message}", e)
            emptyList()
        }
    }

    override suspend fun markAsSynced(userId: String): Result<Unit> {
        return try {
            val currentTime = System.currentTimeMillis()
            userDao.updateSyncStatus(userId, SyncStatus.SYNCED.name, currentTime)
            userDao.updateLastSyncTime(userId, currentTime)
            Result.success(Unit)
        } catch (e: Exception) {
            android.util.Log.e("UserRepository", "Error marking user as synced: ${e.message}", e)
            Result.failure(e)
        }
    }

    override suspend fun markAsPending(userId: String): Result<Unit> {
        return try {
            val currentTime = System.currentTimeMillis()
            userDao.updateSyncStatus(userId, SyncStatus.PENDING.name, currentTime)
            Result.success(Unit)
        } catch (e: Exception) {
            android.util.Log.e("UserRepository", "Error marking user as pending: ${e.message}", e)
            Result.failure(e)
        }
    }

    override suspend fun markAsConflict(userId: String): Result<Unit> {
        return try {
            val currentTime = System.currentTimeMillis()
            userDao.updateSyncStatus(userId, SyncStatus.CONFLICT.name, currentTime)
            Result.success(Unit)
        } catch (e: Exception) {
            android.util.Log.e("UserRepository", "Error marking user as conflict: ${e.message}", e)
            Result.failure(e)
        }
    }

    override suspend fun getUserCount(): Int {
        return try {
            userDao.getCount()
        } catch (e: Exception) {
            android.util.Log.e("UserRepository", "Error getting user count: ${e.message}", e)
            0
        }
    }

    override suspend fun searchUsers(query: String): List<User> {
        return try {
            userDao.search(query).first().map { it.toDomain() }
        } catch (e: Exception) {
            android.util.Log.e("UserRepository", "Error searching users: ${e.message}", e)
            emptyList()
        }
    }

    override fun searchUsersFlow(query: String): Flow<List<User>> {
        return userDao.search(query).map { entities ->
            entities.map { it.toDomain() }
        }
    }

    override suspend fun getUserByEmail(email: String): User? {
        return try {
            userDao.getByEmail(email)?.toDomain()
        } catch (e: Exception) {
            android.util.Log.e("UserRepository", "Error getting user by email: ${e.message}", e)
            null
        }
    }

    override suspend fun getUserByUsername(username: String): User? {
        return try {
            userDao.getByUsername(username)?.toDomain()
        } catch (e: Exception) {
            android.util.Log.e("UserRepository", "Error getting user by username: ${e.message}", e)
            null
        }
    }

    private fun UserEntity.toDomain(): User {
        return User(
            id = id,
            username = username,
            email = email,
            displayName = displayName,
            phone = phone,
            avatarUrl = avatarUrl,
            isEmailVerified = isEmailVerified,
            isPhoneVerified = isPhoneVerified,
            syncStatus = SyncStatus.valueOf(syncStatus),
            createdAt = createdAt,
            updatedAt = updatedAt,
            lastSyncAt = lastSyncAt
        )
    }

    private fun User.toEntity(): UserEntity {
        return UserEntity(
            id = id,
            username = username,
            email = email,
            displayName = displayName,
            phone = phone,
            avatarUrl = avatarUrl,
            isEmailVerified = isEmailVerified,
            isPhoneVerified = isPhoneVerified,
            syncStatus = syncStatus.name,
            createdAt = createdAt,
            updatedAt = updatedAt,
            lastSyncAt = lastSyncAt
        )
    }

    private fun User.validate(): UserValidationResult {
        val errors = mutableListOf<String>()
        if (username.isBlank()) errors.add("Username cannot be blank")
        if (email.isBlank()) errors.add("Email cannot be blank")
        if (!email.contains("@")) errors.add("Email must be valid")
        if (displayName.isBlank()) errors.add("Display name cannot be blank")
        return if (errors.isEmpty()) {
            UserValidationResult.Valid
        } else {
            UserValidationResult.Invalid(errors)
        }
    }
}

// === ARCHIVO: app/src/main/java/com/example/offlinefirst/data/repository/PreferencesRepositoryImpl.kt ===
package com.example.offlinefirst.data.repository

import com.example.offlinefirst.data.local.dao.PreferencesDao
import com.example.offlinefirst.data.local.entity.PreferencesEntity
import com.example.offlinefirst.domain.model.PreferenceCategory
import com.example.offlinefirst.domain.model.PreferenceDataType
import com.example.offlinefirst.domain.model.Preferences
import com.example.offlinefirst.domain.model.PreferenceValidationResult
import com.example.offlinefirst.domain.model.SyncStatus
import com.example.offlinefirst.domain.repository.PreferencesRepository
import com.example.offlinefirst.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PreferencesRepositoryImpl @Inject constructor(
    private val preferencesDao: PreferencesDao
) : PreferencesRepository {

    override fun getPreferenceById(id: String): Flow<Resource<Preferences>> = flow {
        emit(Resource.Loading())
        try {
            val entity = preferencesDao.getPreferenceById(id)
            if (entity != null) {
                emit(Resource.Success(entity.toDomain()))
            } else {
                emit(Resource.Error("Preference not found with id: $id"))
            }
        } catch (e: Exception) {
            emit(Resource.Error(e.message ?: "Unknown error occurred"))
        }
    }

    override fun getPreferenceByKey(key: String, userId: String?): Flow<Resource<Preferences>> = flow {
        emit(Resource.Loading())
        try {
            val entity = preferencesDao.getPreferenceByKey(key, userId)
            if (entity != null) {
                emit(Resource.Success(entity.toDomain()))
            } else {
                emit(Resource.Error("Preference not found with key: $key"))
            }
        } catch (e: Exception) {
            emit(Resource.Error(e.message ?: "Unknown error occurred"))
        }
    }

    override fun getAllPreferences(userId: String?): Flow<Resource<List<Preferences>>> = flow {
        emit(Resource.Loading())
        try {
            val entities = preferencesDao.getAllPreferences(userId)
            emit(Resource.Success(entities.map { it.toDomain() }))
        } catch (e: Exception) {
            emit(Resource.Error(e.message ?: "Unknown error occurred"))
        }
    }

    override fun getPreferencesByCategory(
        category: PreferenceCategory,
        userId: String?
    ): Flow<Resource<List<Preferences>>> = flow {
        emit(Resource.Loading())
        try {
            val entities = preferencesDao.getPreferencesByCategory(category.name, userId)
            emit(Resource.Success(entities.map { it.toDomain() }))
        } catch (e: Exception) {
            emit(Resource.Error(e.message ?: "Unknown error occurred"))
        }
    }

    override fun getPendingPreferences(): Flow<Resource<List<Preferences>>> = flow {
        emit(Resource.Loading())
        try {
            val entities = preferencesDao.getPendingPreferences()
            emit(Resource.Success(entities.map { it.toDomain() }))
        } catch (e: Exception) {
            emit(Resource.Error(e.message ?: "Unknown error occurred"))
        }
    }

    override fun savePreference(preferences: Preferences): Flow<Resource<Preferences>> = flow {
        emit(Resource.Loading())
        try {
            val validation = preferences.validate()
            if (!validation.isValid()) {
                emit(Resource.Error(validation.getErrors().joinToString(", ")))
                return@flow
            }
            
            val entity = preferences.toEntity()
            preferencesDao.insertOrUpdate(entity)
            
            val savedEntity = preferencesDao.getPreferenceById(preferences.id)
            if (savedEntity != null) {
                emit(Resource.Success(savedEntity.toDomain()))
            } else {
                emit(Resource.Error("Failed to save preference"))
            }
        } catch (e: Exception) {
            emit(Resource.Error(e.message ?: "Unknown error occurred"))
        }
    }

    override fun savePreferences(preferencesList: List<Preferences>): Flow<Resource<List<Preferences>>> = flow {
        emit(Resource.Loading())
        try {
            val entities = preferencesList.map { it.toEntity() }
            preferencesDao.insertAll(entities)
            
            val ids = preferencesList.map { it.id }
            val savedEntities = preferencesDao.getPreferencesByIds(ids)
            emit(Resource.Success(savedEntities.map { it.toDomain() }))
        } catch (e: Exception) {
            emit(Resource.Error(e.message ?: "Unknown error occurred"))
        }
    }

    override fun deletePreference(id: String): Flow<Resource<Boolean>> = flow {
        emit(Resource.Loading())
        try {
            preferencesDao.deleteById(id)
            emit(Resource.Success(true))
        } catch (e: Exception) {
            emit(Resource.Error(e.message ?: "Unknown error occurred"))
        }
    }

    override fun deleteAllPreferences(userId: String?): Flow<Resource<Boolean>> = flow {
        emit(Resource.Loading())
        try {
            preferencesDao.deleteAllByUserId(userId)
            emit(Resource.Success(true))
        } catch (e: Exception) {
            emit(Resource.Error(e.message ?: "Unknown error occurred"))
        }
    }

    override fun markAsSynced(id: String, syncTimestamp: Long): Flow<Resource<Boolean>> = flow {
        emit(Resource.Loading())
        try {
            preferencesDao.updateSyncStatus(id, SyncStatus.SYNCED.name, syncTimestamp)
            emit(Resource.Success(true))
        } catch (e: Exception) {
            emit(Resource.Error(e.message ?: "Unknown error occurred"))
        }
    }

    override fun markAsPending(id: String): Flow<Resource<Boolean>> = flow {
        emit(Resource.Loading())
        try {
            val timestamp = System.currentTimeMillis()
            preferencesDao.updateSyncStatus(id, SyncStatus.PENDING.name, timestamp)
            emit(Resource.Success(true))
        } catch (e: Exception) {
            emit(Resource.Error(e.message ?: "Unknown error occurred"))
        }
    }

    override fun markAsConflict(id: String): Flow<Resource<Boolean>> = flow {
        emit(Resource.Loading())
        try {
            preferencesDao.updateSyncStatus(id, SyncStatus.CONFLICT.name, null)
            emit(Resource.Success(true))
        } catch (e: Exception) {
            emit(Resource.Error(e.message ?: "Unknown error occurred"))
        }
    }

    override fun getPreferencesCount(userId: String?): Flow<Resource<Int>> = flow {
        emit(Resource.Loading())
        try {
            val count = preferencesDao.getPreferencesCount(userId)
            emit(Resource.Success(count))
        } catch (e: Exception) {
            emit(Resource.Error(e.message ?: "Unknown error occurred"))
        }
    }

    override fun getPendingCount(): Flow<Resource<Int>> = flow {
        emit(Resource.Loading())
        try {
            val count = preferencesDao.getPendingCount()
            emit(Resource.Success(count))
        } catch (e: Exception) {
            emit(Resource.Error(e.message ?: "Unknown error occurred"))
        }
    }

    override fun searchPreferences(query: String, userId: String?): Flow<Resource<List<Preferences>>> = flow {
        emit(Resource.Loading())
        try {
            val entities = preferencesDao.searchPreferences(query, userId)
            emit(Resource.Success(entities.map { it.toDomain() }))
        } catch (e: Exception) {
            emit(Resource.Error(e.message ?: "Unknown error occurred"))
        }
    }

    private fun PreferencesEntity.toDomain(): Preferences {
        return Preferences(
            id = id,
            key = key,
            value = value,
            dataType = PreferenceDataType.valueOf(dataType),
            category = PreferenceCategory.valueOf(category),
            userId = userId,
            isGlobal = isGlobal,
            isEncrypted = isEncrypted,
            syncStatus = SyncStatus.valueOf(syncStatus),
            createdAt = createdAt,
            updatedAt = updatedAt,
            lastSyncAt = lastSyncAt
        )
    }

    private fun Preferences.toEntity(): PreferencesEntity {
        return PreferencesEntity(
            id = id,
            key = key,
            value = value,
            dataType = dataType.name,
            category = category.name,
            userId = userId,
            isGlobal = isGlobal,
            isEncrypted = isEncrypted,
            syncStatus = syncStatus.name,
            createdAt = createdAt,
            updatedAt = updatedAt,
            lastSyncAt = lastSyncAt
        )
    }
}

// === ARCHIVO: app/src/main/java/com/example/offlinefirst/data/repository/ContentRepositoryImpl.kt ===
package com.example.offlinefirst.data.repository

import com.example.offlinefirst.data.local.dao.ContentDao
import com.example.offlinefirst.data.local.entity.DownloadedContentEntity
import com.example.offlinefirst.domain.model.ContentCategory
import com.example.offlinefirst.domain.model.ContentValidationResult
import com.example.offlinefirst.domain.model.DownloadedContent
import com.example.offlinefirst.domain.model.SyncStatus
import com.example.offlinefirst.domain.repository.ContentRepository
import com.example.offlinefirst.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.io.File
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ContentRepositoryImpl @Inject constructor(
    private val contentDao: ContentDao
) : ContentRepository {

    override fun getContentById(id: String): Flow<Resource<DownloadedContent>> = flow {
        emit(Resource.Loading())
        try {
            val entity = contentDao.getContentById(id)
            if (entity != null) {
                emit(Resource.Success(entity.toDomain()))
            } else {
                emit(Resource.Error("Content not found with id: $id"))
            }
        } catch (e: Exception) {
            emit(Resource.Error(e.message ?: "Unknown error occurred"))
        }
    }

    override fun getContentByUrl(url: String): Flow<Resource<DownloadedContent>> = flow {
        emit(Resource.Loading())
        try {
            val entity = contentDao.getContentByUrl(url)
            if (entity != null) {
                emit(Resource.Success(entity.toDomain()))
            } else {
                emit(Resource.Error("Content not found with url: $url"))
            }
        } catch (e: Exception) {
            emit(Resource.Error(e.message ?: "Unknown error occurred"))
        }
    }

    override fun getAllContent(): Flow<Resource<List<DownloadedContent>>> = flow {
        emit(Resource.Loading())
        try {
            val entities = contentDao.getAllContent()
            emit(Resource.Success(entities.map { it.toDomain() }))
        } catch (e: Exception) {
            emit(Resource.Error(e.message ?: "Unknown error occurred"))
        }
    }

    override fun getDownloadedContent(): Flow<Resource<List<DownloadedContent>>> = flow {
        emit(Resource.Loading())
        try {
            val entities = contentDao.getDownloadedContent()
            emit(Resource.Success(entities.map { it.toDomain() }))
        } catch (e: Exception) {
            emit(Resource.Error(e.message ?: "Unknown error occurred"))
        }
    }

    override fun getAvailableOfflineContent(): Flow<Resource<List<DownloadedContent>>> = flow {
        emit(Resource.Loading())
        try {
            val entities = contentDao.getAvailableOfflineContent()
            emit(Resource.Success(entities.map { it.toDomain() }))
        } catch (e: Exception) {
            emit(Resource.Error(e.message ?: "Unknown error occurred"))
        }
    }

    override fun getContentByCategory(category: ContentCategory): Flow<Resource<List<DownloadedContent>>> = flow {
        emit(Resource.Loading())
        try {
            val entities = contentDao.getContentByCategory(category.name)
            emit(Resource.Success(entities.map { it.toDomain() }))
        } catch (e: Exception) {
            emit(Resource.Error(e.message ?: "Unknown error occurred"))
        }
    }

    override fun getPendingContent(): Flow<Resource<List<DownloadedContent>>> = flow {
        emit(Resource.Loading())
        try {
            val entities = contentDao.getPendingContent()
            emit(Resource.Success(entities.map { it.toDomain() }))
        } catch (e: Exception) {
            emit(Resource.Error(e.message ?: "Unknown error occurred"))
        }
    }

    override fun getExpiredContent(): Flow<Resource<List<DownloadedContent>>> = flow {
        emit(Resource.Loading())
        try {
            val currentTime = System.currentTimeMillis()
            val entities = contentDao.getExpiredContent(currentTime)
            emit(Resource.Success(entities.map { it.toDomain() }))
        } catch (e: Exception) {
            emit(Resource.Error(e.message ?: "Unknown error occurred"))
        }
    }

    override fun saveContent(content: DownloadedContent): Flow<Resource<DownloadedContent>> = flow {
        emit(Resource.Loading())
        try {
            val validation = content.validate()
            if (!validation.isValid()) {
                emit(Resource.Error(validation.getErrors().joinToString(", ")))
                return@flow
            }
            
            val entity = content.toEntity()
            contentDao.insertOrUpdate(entity)
            
            val savedEntity = contentDao.getContentById(content.id)
            if (savedEntity != null) {
                emit(Resource.Success(savedEntity.toDomain()))
            } else {
                emit(Resource.Error("Failed to save content"))
            }
        } catch (e: Exception) {
            emit(Resource.Error(e.message ?: "Unknown error occurred"))
        }
    }

    override fun saveContentList(contentList: List<DownloadedContent>): Flow<Resource<List<DownloadedContent>>> = flow {
        emit(Resource.Loading())
        try {
            val entities = contentList.map { it.toEntity() }
            contentDao.insertAll(entities)
            
            val ids = contentList.map { it.id }
            val savedEntities = contentDao.getContentByIds(ids)
            emit(Resource.Success(savedEntities.map { it.toDomain() }))
        } catch (e: Exception) {
            emit(Resource.Error(e.message ?: "Unknown error occurred"))
        }
    }

    override fun updateDownloadProgress(id: String, downloadedSize: Long): Flow<Resource<Boolean>> = flow {
        emit(Resource.Loading())
        try {
            contentDao.updateDownloadProgress(id, downloadedSize, System.currentTimeMillis())
            emit(Resource.Success(true))
        } catch (e: Exception) {
            emit(Resource.Error(e.message ?: "Unknown error occurred"))
        }
    }

    override fun markAsDownloaded(id: String, localPath: String, fileSize: Long, hash: String?): Flow<Resource<Boolean>> = flow {
        emit(Resource.Loading())
        try {
            contentDao.markAsDownloaded(id, localPath, fileSize, hash, System.currentTimeMillis())
            emit(Resource.Success(true))
        } catch (e: Exception) {
            emit(Resource.Error(e.message ?: "Unknown error occurred"))
        }
    }

    override fun deleteContent(id: String): Flow<Resource<Boolean>> = flow {
        emit(Resource.Loading())
        try {
            val entity = contentDao.getContentById(id)
            entity?.localPath?.let { path ->
                val file = File(path)
                if (file.exists()) {
                    file.delete()
                }
            }
            contentDao.deleteById(id)
            emit(Resource.Success(true))
        } catch (e: Exception) {
            emit(Resource.Error(e.message ?: "Unknown error occurred"))
        }
    }

    override fun deleteExpiredContent(): Flow<Resource<Int>> = flow {
        emit(Resource.Loading())
        try {
            val currentTime = System.currentTimeMillis()
            val expiredEntities = contentDao.getExpiredContent(currentTime)
            
            expiredEntities.forEach { entity ->
                entity.localPath?.let { path ->
                    val file = File(path)
                    if (file.exists()) {
                        file.delete()
                    }
                }
            }
            
            val deletedCount = contentDao.deleteExpiredContent(currentTime)
            emit(Resource.Success(deletedCount))
        } catch (e: Exception) {
            emit(Resource.Error(e.message ?: "Unknown error occurred"))
        }
    }

    override fun markAsSynced(id: String, syncTimestamp: Long): Flow<Resource<Boolean>> = flow {
        emit(Resource.Loading())
        try {
            contentDao.updateSyncStatus(id, SyncStatus.SYNCED.name, syncTimestamp)
            emit(Resource.Success(true))
        } catch (e: Exception) {
            emit(Resource.Error(e.message ?: "Unknown error occurred"))
        }
    }

    override fun markAsPending(id: String): Flow<Resource<Boolean>> = flow {
        emit(Resource.Loading())
        try {
            val timestamp = System.currentTimeMillis()
            contentDao.updateSyncStatus(id, SyncStatus.PENDING.name, timestamp)
            emit(Resource.Success(true))
        } catch (e: Exception) {
            emit(Resource.Error(e.message ?: "Unknown error occurred"))
        }
    }

    override fun markAsConflict(id: String): Flow<Resource<Boolean>> = flow {
        emit(Resource.Loading())
        try {
            contentDao.updateSyncStatus(id, SyncStatus.CONFLICT.name, null)
            emit(Resource.Success(true))
        } catch (e: Exception) {
            emit(Resource.Error(e.message ?: "Unknown error occurred"))
        }
    }

    override fun incrementAccessCount(id: String): Flow<Resource<Boolean>> = flow {
        emit(Resource.Loading())
        try {
            contentDao.incrementAccessCount(id, System.currentTimeMillis())
            emit(Resource.Success(true))
        } catch (e: Exception) {
            emit(Resource.Error(e.message ?: "Unknown error occurred"))
        }
    }

    override fun getContentCount(): Flow<Resource<Int>> = flow {
        emit(Resource.Loading())
        try {
            val count = contentDao.getContentCount()
            emit(Resource.Success(count))
        } catch (e: Exception) {
            emit(Resource.Error(e.message ?: "Unknown error occurred"))
        }
    }

    override fun getDownloadedCount(): Flow<Resource<Int>> = flow {
        emit(Resource.Loading())
        try {
            val count = contentDao.getDownloadedCount()
            emit(Resource.Success(count))
        } catch (e: Exception) {
            emit(Resource.Error(e.message ?: "Unknown error occurred"))
        }
    }

    override fun getTotalSize(): Flow<Resource<Long>> = flow {
        emit(Resource.Loading())
        try {
            val size = contentDao.getTotalSize()
            emit(Resource.Success(size))
        } catch (e: Exception) {
            emit(Resource.Error(e.message ?: "Unknown error occurred"))
        }
    }

    override fun searchContent(query: String): Flow<Resource<List<DownloadedContent>>> = flow {
        emit(Resource.Loading())
        try {
            val entities = contentDao.searchContent(query)
            emit(Resource.Success(entities.map { it.toDomain() }))
        } catch (e: Exception) {
            emit(Resource.Error(e.message ?: "Unknown error occurred"))
        }
    }

    override fun getContentByTag(tag: String): Flow<Resource<List<DownloadedContent>>> = flow {
        emit(Resource.Loading())
        try {
            val entities = contentDao.getContentByTag(tag)
            emit(Resource.Success(entities.map { it.toDomain() }))
        } catch (e: Exception) {
            emit(Resource.Error(e.message ?: "Unknown error occurred"))
        }
    }

    private fun DownloadedContentEntity.toDomain(): DownloadedContent {
        return DownloadedContent(
            id = id,
            title = title,
            description = description,
            url = url,
            localPath = localPath,
            thumbnailUrl = thumbnailUrl,
            fileSize = fileSize,
            downloadedSize = downloadedSize,
            mimeType = mimeType,
            hash = hash,
            category = ContentCategory.valueOf(category),
            tags = tags.split(",").filter { it.isNotBlank() },
            isAvailableOffline = isAvailableOffline,
            expiresAt = expiresAt,
            accessCount = accessCount,
            accessedAt = accessedAt,
            syncStatus = SyncStatus.valueOf(syncStatus),
            createdAt = createdAt,
            updatedAt = updatedAt,
            lastSyncAt = lastSyncAt
        )
    }

    private fun DownloadedContent.toEntity(): DownloadedContentEntity {
        return DownloadedContentEntity(
            id = id,
            title = title,
            description = description,
            url = url,
            localPath = localPath,
            thumbnailUrl = thumbnailUrl,
            fileSize = fileSize,
            downloadedSize = downloadedSize,
            mimeType = mimeType,
            hash = hash,
            category = category.name,
            tags = tags.joinToString(","),
            isAvailableOffline = isAvailableOffline,
            expiresAt = expiresAt,
            accessCount = accessCount,
            accessedAt = accessedAt,
            syncStatus = syncStatus.name,
            createdAt = createdAt,
            updatedAt = updatedAt,
            lastSyncAt = lastSyncAt
        )
    }
}

// === ARCHIVO: app/src/main/java/com/example/offlinefirst/data/repository/SyncRepositoryImpl.kt ===
package com.example.offlinefirst.data.repository

import com.example.offlinefirst.data.local.dao.SyncOperationDao
import com.example.offlinefirst.data.local.entity.SyncOperationEntity
import com.example.offlinefirst.domain.model.SyncOperation
import com.example.offlinefirst.domain.model.SyncOperationStatus
import com.example.offlinefirst.domain.model.SyncOperationType
import com.example.offlinefirst.domain.repository.SyncRepository
import com.example.offlinefirst.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SyncRepositoryImpl @Inject constructor(
    private val syncOperationDao: SyncOperationDao
) : SyncRepository {

    override fun getOperationById(id: String): Flow<Resource<SyncOperation>> = flow {
        emit(Resource.Loading())
        try {
            val entity = syncOperationDao.getOperationById(id)
            if (entity != null) {
                emit(Resource.Success(entity.toDomain()))
            } else {
                emit(Resource.Error("Sync operation not found with id: $id"))
            }
        } catch (e: Exception) {
            emit(Resource.Error(e.message ?: "Unknown error occurred"))
        }
    }

    override fun getAllOperations(): Flow<Resource<List<SyncOperation>>> = flow {
        emit(Resource.Loading())
        try {
            val entities = syncOperationDao.getAllOperations()
            emit(Resource.Success(entities.map { it.toDomain() }))
        } catch (e: Exception) {
            emit(Resource.Error(e.message ?: "Unknown error occurred"))
        }
    }

    override fun getPendingOperations(): Flow<Resource<List<SyncOperation>>> = flow {
        emit(Resource.Loading())
        try {
            val entities = syncOperationDao.getPendingOperations()
            emit(Resource.Success(entities.map { it.toDomain() }))
        } catch (e: Exception) {
            emit(Resource.Error(e.message ?: "Unknown error occurred"))
        }
    }

    override fun getOperationsByType(type: SyncOperationType): Flow<Resource<List<SyncOperation>>> = flow {
        emit(Resource.Loading())
        try {
            val entities = syncOperationDao.getOperationsByType(type.name)
            emit(Resource.Success(entities.map { it.toDomain() }))
        } catch (e: Exception) {
            emit(Resource.Error(e.message ?: "Unknown error occurred"))
        }
    }

    override fun getOperationsByStatus(status: SyncOperationStatus): Flow<Resource<List<SyncOperation>>> = flow {
        emit(Resource.Loading())
        try {
            val entities = syncOperationDao.getOperationsByStatus(status.name)
            emit(Resource.Success(entities.map { it.toDomain() }))
        } catch (e: Exception) {
            emit(Resource.Error(e.message ?: "Unknown error occurred"))
        }
    }

    override fun getFailedOperations(): Flow<Resource<List<SyncOperation>>> = flow {
        emit(Resource.Loading())
        try {
            val entities = syncOperationDao.getFailedOperations()
            emit(Resource.Success(entities.map { it.toDomain() }))
        } catch (e: Exception) {
            emit(Resource.Error(e.message ?: "Unknown error occurred"))
        }
    }

    override fun saveOperation(operation: SyncOperation): Flow<Resource<SyncOperation>> = flow {
        emit(Resource.Loading())
        try {
            val entity = operation.toEntity()
            syncOperationDao.insertOrUpdate(entity)
            
            val savedEntity = syncOperationDao.getOperationById(operation.id)
            if (savedEntity != null) {
                emit(Resource.Success(savedEntity.toDomain()))
            } else {
                emit(Resource.Error("Failed to save sync operation"))
            }
        } catch (e: Exception) {
            emit(Resource.Error(e.message ?: "Unknown error occurred"))
        }
    }

    override fun saveOperations(operations: List<SyncOperation>): Flow<Resource<List<SyncOperation>>> = flow {
        emit(Resource.Loading())
        try {
            val entities = operations.map { it.toEntity() }
            syncOperationDao.insertAll(entities)
            
            val ids = operations.map { it.id }
            val savedEntities = syncOperationDao.getOperationsByIds(ids)
            emit(Resource.Success(savedEntities.map { it.toDomain() }))
        } catch (e: Exception) {
            emit(Resource.Error(e.message ?: "Unknown error occurred"))
        }
    }

    override fun updateOperationStatus(
        id: String,
        status: SyncOperationStatus,
        errorMessage: String?
    ): Flow<Resource<Boolean>> = flow {
        emit(Resource.Loading())
        try {
            syncOperationDao.updateStatus(id, status.name, errorMessage, System.currentTimeMillis())
            emit(Resource.Success(true))
        } catch (e: Exception) {
            emit(Resource.Error(e.message ?: "Unknown error occurred"))
        }
    }

    override fun markAsCompleted(id: String): Flow<Resource<Boolean>> = flow {
        emit(Resource.Loading())
        try {
            val timestamp = System.currentTimeMillis()
            syncOperationDao.updateStatus(
                id,
                SyncOperationStatus.COMPLETED.name,
                null,
                timestamp
            )
            syncOperationDao.updateCompletedAt(id, timestamp)
            emit(Resource.Success(true))
        } catch (e: Exception) {
            emit(Resource.Error(e.message ?: "Unknown error occurred"))
        }
    }

    override fun markAsFailed(id: String, errorMessage: String): Flow<Resource<Boolean>> = flow {
        emit(Resource.Loading())
        try {
            syncOperationDao.updateStatus(
                id,
                SyncOperationStatus.FAILED.name,
                errorMessage,
                System.currentTimeMillis()
            )
            emit(Resource.Success(true))
        } catch (e: Exception) {
            emit(Resource.Error(e.message ?: "Unknown error occurred"))
        }
    }

    override fun markAsInProgress(id: String): Flow<Resource<Boolean>> = flow {
        emit(Resource.Loading())
        try {
            syncOperationDao.updateStatus(
                id,
                SyncOperationStatus.IN_PROGRESS.name,
                null,
                System.currentTimeMillis()
            )
            emit(Resource.Success(true))
        } catch (e: Exception) {
            emit(Resource.Error(e.message ?: "Unknown error occurred"))
        }
    }

    override fun deleteOperation(id: String): Flow<Resource<Boolean>> = flow {
        emit(Resource.Loading())
        try {
            syncOperationDao.deleteById(id)
            emit(Resource.Success(true))
        } catch (e: Exception) {
            emit(Resource.Error(e.message ?: "Unknown error occurred"))
        }
    }

    override fun deleteCompletedOperations(): Flow<Resource<Int>> = flow {
        emit(Resource.Loading())
        try {
            val count = syncOperationDao.deleteCompletedOperations()
            emit(Resource.Success(count))
        } catch (e: Exception) {
            emit(Resource.Error(e.message ?: "Unknown error occurred"))
        }
    }

    override fun deleteOldOperations(olderThanTimestamp: Long): Flow<Resource<Int>> = flow {
        emit(Resource.Loading())
        try {
            val count = syncOperationDao.deleteOldOperations(olderThanTimestamp)
            emit(Resource.Success(count))
        } catch (e: Exception) {
            emit(Resource.Error(e.message ?: "Unknown error occurred"))
        }
    }

    override fun getOperationCount(): Flow<Resource<Int>> = flow {
        emit(Resource.Loading())
        try {
            val count = syncOperationDao.getOperationCount()
            emit(Resource.Success(count))
        } catch (e: Exception) {
            emit(Resource.Error(e.message ?: "Unknown error occurred"))
        }
    }

    override fun getPendingCount(): Flow<Resource<Int>> = flow {
        emit(Resource.Loading())
        try {
            val count = syncOperationDao.getPendingCount()
            emit(Resource.Success(count))
        } catch (e: Exception) {
            emit(Resource.Error(e.message ?: "Unknown error occurred"))
        }
    }

    override fun getFailedCount(): Flow<Resource<Int>> = flow {
        emit(Resource.Loading())
        try {
            val count = syncOperationDao.getFailedCount()
            emit(Resource.Success(count))
        } catch (e: Exception) {
            emit(Resource.Error(e.message ?: "Unknown error occurred"))
        }
    }

    override fun retryFailedOperation(id: String): Flow<Resource<Boolean>> = flow {
        emit(Resource.Loading())
        try {
            syncOperationDao.updateStatus(
                id,
                SyncOperationStatus.PENDING.name,
                null,
                System.currentTimeMillis()
            )
            syncOperationDao.incrementRetryCount(id)
            emit(Resource.Success(true))
        } catch (e: Exception) {
            emit(Resource.Error(e.message ?: "Unknown error occurred"))
        }
    }

    override fun retryAllFailedOperations(): Flow<Resource<Int>> = flow {
        emit(Resource.Loading())
        try {
            val count = syncOperationDao.retryAllFailed()
            emit(Resource.Success(count))
        } catch (e: Exception) {
            emit(Resource.Error(e.message ?: "Unknown error occurred"))
        }
    }

    override fun getOperationsByEntityId(entityId: String): Flow<Resource<List<SyncOperation>>> = flow {
        emit(Resource.Loading())
        try {
            val entities = syncOperationDao.getOperationsByEntityId(entityId)
            emit(Resource.Success(entities.map { it.toDomain() }))
        } catch (e: Exception) {
            emit(Resource.Error(e.message ?: "Unknown error occurred"))
        }
    }

    private fun SyncOperationEntity.toDomain(): SyncOperation {
        return SyncOperation(
            id = id,
            entityId = entityId,
            entityType = entityType,
            operationType = SyncOperationType.valueOf(operationType),
            payload = payload,
            status = SyncOperationStatus.valueOf(status),
            errorMessage = errorMessage,
            retryCount = retryCount,
            maxRetries = maxRetries,
            createdAt = createdAt,
            updatedAt = updatedAt,
            startedAt = startedAt,
            completedAt = completedAt
        )
    }

    private fun SyncOperation.toEntity(): SyncOperationEntity {
        return SyncOperationEntity(
            id = id,
            entityId = entityId,
            entityType = entityType,
            operationType = operationType.name,
            payload = payload,
            status = status.name,
            errorMessage = errorMessage,
            retryCount = retryCount,
            maxRetries = maxRetries,
            createdAt = createdAt,
            updatedAt = updatedAt,
            startedAt = startedAt,
            completedAt = completedAt
        )
    }
}

// === ARCHIVO: app/src/main/java/com/example/offlinefirst/data/remote/ApiService.kt ===
package com.example.offlinefirst.data.remote

import com.example.offlinefirst.data.remote.dto.ContentDto
import com.example.offlinefirst.data.remote.dto.PreferencesDto
import com.example.offlinefirst.data.remote.dto.SyncRequestDto
import com.example.offlinefirst.data.remote.dto.SyncResponseDto
import com.example.offlinefirst.data.remote.dto.UserDto
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @GET("users/{id}")
    suspend fun getUser(@Path("id") userId: String): Response<UserDto>

    @GET("users")
    suspend fun getUsers(@Query("page") page: Int = 1, @Query("limit") limit: Int = 20): Response<List<UserDto>>

    @POST("users")
    suspend fun createUser(@Body user: UserDto): Response<UserDto>

    @PUT("users/{id}")
    suspend fun updateUser(@Path("id") userId: String, @Body user: UserDto): Response<UserDto>

    @DELETE("users/{id}")
    suspend fun deleteUser(@Path("id") userId: String): Response<Unit>

    @GET("preferences/{id}")
    suspend fun getPreference(@Path("id") preferenceId: String): Response<PreferencesDto>

    @GET("preferences")
    suspend fun getPreferences(
        @Query("userId") userId: String?,
        @Query("category") category: String?,
        @Query("page") page: Int = 1,
        @Query("limit") limit: Int = 50
    ): Response<List<PreferencesDto>>

    @POST("preferences")
    suspend fun createPreference(@Body preference: PreferencesDto): Response<PreferencesDto>

    @PUT("preferences/{id}")
    suspend fun updatePreference(
        @Path("id") preferenceId: String,
        @Body preference: PreferencesDto
    ): Response<PreferencesDto>

    @DELETE("preferences/{id}")
    suspend fun deletePreference(@Path("id") preferenceId: String): Response<Unit>

    @POST("preferences/batch")
    suspend fun createPreferencesBatch(@Body preferences: List<PreferencesDto>): Response<List<PreferencesDto>>

    @PUT("preferences/batch")
    suspend fun updatePreferencesBatch(@Body preferences: List<PreferencesDto>): Response<List<PreferencesDto>>

    @GET("content/{id}")
    suspend fun getContent(@Path("id") contentId: String): Response<ContentDto>

    @GET("content")
    suspend fun getContentList(
        @Query("category") category: String?,
        @Query("page") page: Int = 1,
        @Query("limit") limit: Int = 20
    ): Response<List<ContentDto>>

    @POST("content")
    suspend fun createContent(@Body content: ContentDto): Response<ContentDto>

    @PUT("content/{id}")
    suspend fun updateContent(@Path("id") contentId: String, @Body content: ContentDto): Response<ContentDto>

    @DELETE("content/{id}")
    suspend fun deleteContent(@Path("id") contentId: String): Response<Unit>

    @POST("content/batch")
    suspend fun createContentBatch(@Body contents: List<ContentDto>): Response<List<ContentDto>>

    @PUT("content/batch")
    suspend fun updateContentBatch(@Body contents: List<ContentDto>): Response<List<ContentDto>>

    @POST("content/download/{id}")
    suspend fun getDownloadUrl(@Path("id") contentId: String): Response<Map<String, String>>

    @POST("sync")
    suspend fun syncData(@Body syncRequest: SyncRequestDto): Response<SyncResponseDto>

    @GET("sync/status")
    suspend fun getSyncStatus(): Response<SyncResponseDto>

    @POST("sync/force")
    suspend fun forceSync(): Response<SyncResponseDto>

    @GET("health")
    suspend fun healthCheck(): Response<Map<String, Any>>
}


package com.example.offlinefirst.data.remote

import com.example.offlinefirst.data.remote.ApiService
import com.example.offlinefirst.util.Constants
import com.squareup.moshi.Moshi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ApiClient @Inject constructor(
    private val moshi: Moshi,
    private val authInterceptor: AuthInterceptor,
    private val errorInterceptor: ErrorInterceptor
) {
    private val loggingInterceptor = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }

    private val okHttpClient: OkHttpClient = OkHttpClient.Builder()
        .addInterceptor(authInterceptor)
        .addInterceptor(errorInterceptor)
        .addInterceptor(loggingInterceptor)
        .connectTimeout(30, TimeUnit.SECONDS)
        .readTimeout(30, TimeUnit.SECONDS)
        .writeTimeout(30, TimeUnit.SECONDS)
        .retryOnConnectionFailure(true)
        .build()

    private val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl(Constants.BASE_URL)
        .client(okHttpClient)
        .addConverterFactory(MoshiConverterFactory.create(moshi))
        .build()

    val apiService: ApiService = retrofit.create(ApiService::class.java)

    fun <T> createService(serviceClass: Class<T>): T {
        return retrofit.create(serviceClass)
    }
}

@Singleton
class AuthInterceptor @Inject constructor() : Interceptor {
    private var authToken: String? = null

    fun setAuthToken(token: String?) {
        authToken = token
    }

    override fun intercept(chain: Interceptor.Chain): okhttp3.Response {
        val originalRequest = chain.request()
        val requestBuilder = originalRequest.newBuilder()
            .header("Content-Type", "application/json")
            .header("Accept", "application/json")

        authToken?.let { token ->
            requestBuilder.header("Authorization", "Bearer $token")
        }

        return chain.proceed(requestBuilder.build())
    }
}

@Singleton
class ErrorInterceptor @Inject constructor() : Interceptor {
    override fun intercept(chain: Interceptor.Chain): okhttp3.Response {
        val request = chain.request()
        val response = chain.proceed(request)

        return when (response.code) {
            401 -> {
                response.close()
                throw UnauthorizedException("Unauthorized: Invalid or expired token")
            }
            403 -> {
                response.close()
                throw ForbiddenException("Forbidden: Access denied")
            }
            404 -> {
                response.close()
                throw NotFoundException("Resource not found")
            }
            429 -> {
                val retryAfter = response.header("Retry-After")?.toLongOrNull() ?: 60
                response.close()
                throw RateLimitException("Rate limit exceeded. Retry after $retryAfter seconds", retryAfter)
            }
            in 500..599 -> {
                response.close()
                throw ServerException("Server error: ${response.code}")
            }
            else -> response
        }
    }
}

class UnauthorizedException(message: String) : Exception(message)
class ForbiddenException(message: String) : Exception(message)
class NotFoundException(message: String) : Exception(message)
class RateLimitException(message: String, val retryAfterSeconds: Long) : Exception(message)
class ServerException(message: String) : Exception(message)
// === ARCHIVO: app/src/main/java/com/example/offlinefirst/data/queue/OperationQueueManager.kt ===
package com.example.offlinefirst.data.queue

import android.content.Context
import androidx.room.Room
import com.example.offlinefirst.data.local.database.OfflineFirstDatabase
import com.example.offlinefirst.data.local.entity.SyncOperationEntity
import com.example.offlinefirst.domain.model.SyncOperation
import com.example.offlinefirst.domain.model.SyncOperationType
import com.example.offlinefirst.domain.model.SyncStatus
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class OperationQueueManager @Inject constructor(
    @ApplicationContext private val context: Context,
    private val database: OfflineFirstDatabase
) {
    private val syncOperationDao = database.syncOperationDao()
    private val mutex = Mutex()

    private val _isProcessing = MutableStateFlow(false)
    val isProcessing: Flow<Boolean> = _isProcessing.asStateFlow()

    private val _queueSize = MutableStateFlow(0)
    val queueSize: Flow<Int> = _queueSize.asStateFlow()

    suspend fun enqueueOperation(operation: SyncOperation): Result<Long> = withContext(Dispatchers.IO) {
        mutex.withLock {
            try {
                val entity = operation.toEntity()
                val id = syncOperationDao.insert(operation)
                updateQueueSize()
                Result.success(id)
            } catch (e: Exception) {
                Result.failure(e)
            }
        }
    }

    suspend fun enqueueOperations(operations: List<SyncOperation>): Result<List<Long>> = withContext(Dispatchers.IO) {
        mutex.withLock {
            try {
                val entities = operations.map { it.toEntity() }
                val ids = syncOperationDao.insertAll(operations)
                updateQueueSize()
                Result.success(ids)
            } catch (e: Exception) {
                Result.failure(e)
            }
        }
    }

    suspend fun getPendingOperations(): List<SyncOperation> = withContext(Dispatchers.IO) {
        syncOperationDao.getPendingOperations()
            .map { it.toDomain() }
    }

    suspend fun getOperationsByEntity(entityId: String): List<SyncOperation> = withContext(Dispatchers.IO) {
        syncOperationDao.getOperationsByEntityId(entityId)
            .map { it.toDomain() }
    }

    suspend fun markAsCompleted(operationId: Long): Result<Unit> = withContext(Dispatchers.IO) {
        try {
            syncOperationDao.updateStatus(operationId, SyncStatus.SYNCED.name)
            updateQueueSize()
            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    suspend fun markAsFailed(operationId: Long, errorMessage: String): Result<Unit> = withContext(Dispatchers.IO) {
        try {
            syncOperationDao.updateStatusWithError(operationId, SyncStatus.ERROR.name, errorMessage)
            updateQueueSize()
            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    suspend fun markAsProcessing(operationId: Long): Result<Unit> = withContext(Dispatchers.IO) {
        try {
            syncOperationDao.updateStatus(operationId, SyncStatus.SYNCING.name)
            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    suspend fun retryOperation(operationId: Long): Result<Unit> = withContext(Dispatchers.IO) {
        try {
            syncOperationDao.updateStatusWithError(operationId, SyncStatus.PENDING.name, null)
            syncOperationDao.incrementRetryCount(operationId)
            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    suspend fun deleteOperation(operationId: Long): Result<Unit> = withContext(Dispatchers.IO) {
        try {
            syncOperationDao.deleteById(operationId)
            updateQueueSize()
            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    suspend fun clearCompletedOperations(): Result<Int> = withContext(Dispatchers.IO) {
        try {
            val deleted = syncOperationDao.deleteCompleted()
            updateQueueSize()
            Result.success(deleted)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    suspend fun clearAllOperations(): Result<Unit> = withContext(Dispatchers.IO) {
        try {
            syncOperationDao.deleteAll()
            updateQueueSize()
            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    suspend fun getOperationById(operationId: Long): SyncOperation? = withContext(Dispatchers.IO) {
        syncOperationDao.getById(operationId)?.toDomain()
    }

    suspend fun hasPendingOperations(): Boolean = withContext(Dispatchers.IO) {
        syncOperationDao.getPendingCount() > 0
    }

    private suspend fun updateQueueSize() {
        val count = syncOperationDao.getPendingCount()
        _queueSize.value = count
    }

    suspend fun processQueue(
        processor: suspend (SyncOperation) -> Result<Unit>
    ): Result<Int> = withContext(Dispatchers.IO) {
        if (_isProcessing.value) {
            return@withContext Result.failure(IllegalStateException("Queue is already being processed"))
        }

        _isProcessing.value = true
        var processedCount = 0

        try {
            val pendingOps = getPendingOperations()
            for (operation in pendingOps) {
                if (operation.retryCount >= MAX_RETRY_COUNT) {
                    continue
                }

                markAsProcessing(operation.id)
                val result = processor(operation)

                if (result.isSuccess) {
                    markAsCompleted(operation.id)
                    processedCount++
                } else {
                    markAsFailed(operation.id, result.exceptionOrNull()?.message ?: "Unknown error")
                }
            }
            Result.success(processedCount)
        } catch (e: Exception) {
            Result.failure(e)
        } finally {
            _isProcessing.value = false
        }
    }

    companion object {
        private const val MAX_RETRY_COUNT = 3
    }
}

private fun SyncOperation.toEntity(): SyncOperationEntity = SyncOperationEntity(
    id = id,
    entityId = entityId,
    entityType = entityType,
    operationType = operationType.name,
    payload = payload,
    status = status.name,
    retryCount = retryCount,
    errorMessage = errorMessage,
    createdAt = createdAt,
    updatedAt = updatedAt
)

private fun SyncOperationEntity.toDomain(): SyncOperation = SyncOperation(
    id = id,
    entityId = entityId,
    entityType = entityType,
    operationType = SyncOperationType.valueOf(operationType),
    payload = payload,
    status = SyncStatus.valueOf(status),
    retryCount = retryCount,
    errorMessage = errorMessage,
    createdAt = createdAt,
    updatedAt = updatedAt
)
// === ARCHIVO: app/src/main/java/com/example/offlinefirst/data/sync/SyncManager.kt ===
package com.example.offlinefirst.data.sync

import com.example.offlinefirst.data.local.dao.ContentDao
import com.example.offlinefirst.data.local.dao.PreferencesDao
import com.example.offlinefirst.data.local.dao.SyncOperationDao
import com.example.offlinefirst.data.local.dao.UserDao
import com.example.offlinefirst.data.queue.OperationQueueManager
import com.example.offlinefirst.data.remote.ApiClient
import com.example.offlinefirst.data.remote.ApiService
import com.example.offlinefirst.data.repository.ContentRepositoryImpl
import com.example.offlinefirst.data.repository.PreferencesRepositoryImpl
import com.example.offlinefirst.data.repository.SyncRepositoryImpl
import com.example.offlinefirst.data.repository.UserRepositoryImpl
import com.example.offlinefirst.domain.model.SyncOperation
import com.example.offlinefirst.domain.model.SyncOperationType
import com.example.offlinefirst.domain.model.SyncResult
import com.example.offlinefirst.domain.model.SyncStatus
import com.example.offlinefirst.domain.model.User
import com.example.offlinefirst.domain.repository.SyncRepository
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SyncManager @Inject constructor(
    @ApplicationContext private val context: android.content.Context,
    private val apiClient: ApiClient,
    private val networkMonitor: NetworkMonitor,
    private val operationQueueManager: OperationQueueManager,
    private val conflictResolver: ConflictResolver,
    private val userRepository: UserRepositoryImpl,
    private val preferencesRepository: PreferencesRepositoryImpl,
    private val contentRepository: ContentRepositoryImpl,
    private val syncRepository: SyncRepositoryImpl,
    private val userDao: UserDao,
    private val preferencesDao: PreferencesDao,
    private val contentDao: ContentDao,
    private val syncOperationDao: SyncOperationDao
) {
    private val scope = CoroutineScope(SupervisorJob() + Dispatchers.IO)

    private val _syncState = MutableStateFlow<SyncState>(SyncState.Idle)
    val syncState: StateFlow<SyncState> = _syncState.asStateFlow()

    private val _lastSyncTime = MutableStateFlow<Long?>(null)
    val lastSyncTime: StateFlow<Long?> = _lastSyncTime.asStateFlow()

    private val _pendingChangesCount = MutableStateFlow(0)
    val pendingChangesCount: StateFlow<Int> = _pendingChangesCount.asStateFlow()

    init {
        observeNetworkAndSync()
    }

    private fun observeNetworkAndSync() {
        scope.launch {
            networkMonitor.isOnline.collect { isOnline ->
                if (isOnline && hasPendingChanges()) {
                    triggerSync()
                }
            }
        }
    }

    suspend fun triggerSync(): Result<SyncResult> {
        if (_syncState.value is SyncState.Syncing) {
            return Result.failure(IllegalStateException("Sync already in progress"))
        }

        if (!networkMonitor.isOnline.value) {
            return Result.failure(IllegalStateException("No network connection"))
        }

        _syncState.value = SyncState.Syncing
        val startTime = System.currentTimeMillis()

        return try {
            val result = performSync()
            val duration = System.currentTimeMillis() - startTime

            _lastSyncTime.value = System.currentTimeMillis()
            _syncState.value = if (result.isSuccess) {
                SyncState.Completed(result.getOrNull()!!, duration)
            } else {
                SyncState.Failed(result.exceptionOrNull()!!)
            }

            result
        } catch (e: Exception) {
            _syncState.value = SyncState.Failed(e)
            Result.failure(e)
        }
    }

    private suspend fun performSync(): Result<SyncResult> = withContext(Dispatchers.IO) {
        var uploadedCount = 0
        var downloadedCount = 0
        var conflictsResolved = 0
        var failedCount = 0

        try {
            val pendingOperations = operationQueueManager.getPendingOperations()

            for (operation in pendingOperations) {
                val result = processOperation(operation)
                if (result.isSuccess) {
                    uploadedCount++
                } else if (result.exceptionOrNull() is ConflictException) {
                    val conflictResult = conflictResolver.resolveConflict(operation)
                    if (conflictResult.isSuccess) {
                        conflictsResolved++
                        uploadedCount++
                    } else {
                        failedCount++
                    }
                } else {
                    failedCount++
                }
            }

            val remoteUsers = fetchRemoteUsers()
            for (remoteUser in remoteUsers) {
                val localUser = userRepository.getUserById(remoteUser.id)
                if (localUser != null) {
                    val merged = conflictResolver.resolveUserConflict(localUser, remoteUser)
                    userRepository.saveUser(merged)
                } else {
                    userRepository.saveUser(remoteUser)
                }
                downloadedCount++
            }

            val syncRecord = SyncResult(
                uploadedCount = uploadedCount,
                downloadedCount = downloadedCount,
                conflictsResolved = conflictsResolved,
                failedCount = failedCount,
                timestamp = System.currentTimeMillis()
            )

            syncRepository.recordSync(syncRecord)
            Result.success(syncRecord)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    private suspend fun processOperation(operation: SyncOperation): Result<Unit> {
        return when (operation.operationType) {
            SyncOperationType.CREATE -> processCreate(operation)
            SyncOperationType.UPDATE -> processUpdate(operation)
            SyncOperationType.DELETE -> processDelete(operation)
        }
    }

    private suspend fun processCreate(operation: SyncOperation): Result<Unit> {
        return try {
            when (operation.entityType) {
                "User" -> {
                    val user = operation.deserializePayload<User>()
                    apiClient.apiService.createUser(user)
                }
                "Preferences" -> {
                    val prefs = operation.deserializePayload<com.example.offlinefirst.domain.model.Preferences>()
                    apiClient.apiService.savePreferences(prefs)
                }
                "DownloadedContent" -> {
                    val content = operation.deserializePayload<com.example.offlinefirst.domain.model.DownloadedContent>()
                    apiClient.apiService.syncContent(content)
                }
            }
            Result.success(Unit)
        } catch (e: Exception) {
            if (e is ConflictException) Result.failure(e)
            else Result.failure(e)
        }
    }

    private suspend fun processUpdate(operation: SyncOperation): Result<Unit> {
        return try {
            when (operation.entityType) {
                "User" -> {
                    val user = operation.deserializePayload<User>()
                    val response = apiClient.apiService.updateUser(user.id, user)
                    if (response.version != user.version) {
                        throw ConflictException("Version conflict for user ${user.id}")
                    }
                }
                "Preferences" -> {
                    val prefs = operation.deserializePayload<com.example.offlinefirst.domain.model.Preferences>()
                    apiClient.apiService.updatePreferences(prefs.id, prefs)
                }
                "DownloadedContent" -> {
                    val content = operation.deserializePayload<com.example.offlinefirst.domain.model.DownloadedContent>()
                    apiClient.apiService.updateContent(content.id, content)
                }
            }
            Result.success(Unit)
        } catch (e: Exception) {
            if (e is ConflictException) Result.failure(e)
            else Result.failure(e)
        }
    }

    private suspend fun processDelete(operation: SyncOperation): Result<Unit> {
        return try {
            when (operation.entityType) {
                "User" -> apiClient.apiService.deleteUser(operation.entityId)
                "Preferences" -> apiClient.apiService.deletePreferences(operation.entityId)
                "DownloadedContent" -> apiClient.apiService.deleteContent(operation.entityId)
            }
            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    private suspend fun fetchRemoteUsers(): List<User> {
        return try {
            val response = apiClient.apiService.getUsers()
            response
        } catch (e: Exception) {
            emptyList()
        }
    }

    suspend fun hasPendingChanges(): Boolean {
        return operationQueueManager.hasPendingOperations()
    }

    suspend fun queueOperation(operation: SyncOperation): Result<Long> {
        _pendingChangesCount.value++
        return operationQueueManager.enqueueOperation(operation)
    }

    suspend fun getSyncStatus(): Flow<SyncState> = syncState

    suspend fun cancelSync() {
        _syncState.value = SyncState.Idle
    }
}

sealed class SyncState {
    data object Idle : SyncState()
    data class Syncing(val progress: Float = 0f) : SyncState()
    data class Completed(val result: SyncResult, val duration: Long) : SyncState()
    data class Failed(val error: Throwable) : SyncState()
}

class ConflictException(message: String) : Exception(message)

private inline fun <reified T> SyncOperation.deserializePayload(): T {
    val moshi = com.squareup.moshi.Moshi.Builder().build()
    val adapter = moshi.adapter(T::class.java)
    return adapter.fromJson(payload)!!
}
// === ARCHIVO: app/src/main/java/com/example/offlinefirst/data/sync/ConflictResolver.kt ===
package com.example.offlinefirst.data.sync

import com.example.offlinefirst.data.remote.ApiClient
import com.example.offlinefirst.data.remote.ApiService
import com.example.offlinefirst.domain.model.DownloadedContent
import com.example.offlinefirst.domain.model.Preferences
import com.example.offlinefirst.domain.model.SyncOperation
import com.example.offlinefirst.domain.model.User
import com.example.offlinefirst.domain.repository.ContentRepository
import com.example.offlinefirst.domain.repository.PreferencesRepository
import com.example.offlinefirst.domain.repository.UserRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ConflictResolver @Inject constructor(
    private val apiClient: ApiClient,
    private val userRepository: UserRepository,
    private val preferencesRepository: PreferencesRepository,
    private val contentRepository: ContentRepository
) {
    suspend fun resolveConflict(operation: SyncOperation): Result<Unit> = withContext(Dispatchers.IO) {
        try {
            when (operation.entityType) {
                "User" -> resolveUserConflict(operation)
                "Preferences" -> resolvePreferencesConflict(operation)
                "DownloadedContent" -> resolveContentConflict(operation)
                else -> Result.failure(IllegalArgumentException("Unknown entity type: ${operation.entityType}"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    private suspend fun resolveUserConflict(operation: SyncOperation): Result<Unit> {
        val localUser = userRepository.getUserById(operation.entityId)
            ?: return Result.failure(IllegalStateException("Local user not found"))

        val remoteUser = fetchRemoteUser(operation.entityId)
            ?: return Result.failure(IllegalStateException("Remote user not found"))

        val resolvedUser = resolveUserConflict(localUser, remoteUser)
        userRepository.saveUser(resolvedUser)
        return Result.success(Unit)
    }

    private suspend fun resolvePreferencesConflict(operation: SyncOperation): Result<Unit> {
        val localPrefs = preferencesRepository.getPreferenceById(operation.entityId)
            ?: return Result.failure(IllegalStateException("Local preferences not found"))

        val remotePrefs = fetchRemotePreferences(operation.entityId)
            ?: return Result.failure(IllegalStateException("Remote preferences not found"))

        val resolvedPrefs = resolvePreferencesConflict(localPrefs, remotePrefs)
        preferencesRepository.savePreference(resolvedPrefs)
        return Result.success(Unit)
    }

    private suspend fun resolveContentConflict(operation: SyncOperation): Result<Unit> {
        val localContent = contentRepository.getContentById(operation.entityId)
            ?: return Result.failure(IllegalStateException("Local content not found"))

        val remoteContent = fetchRemoteContent(operation.entityId)
            ?: return Result.failure(IllegalStateException("Remote content not found"))

        val resolvedContent = resolveContentConflict(localContent, remoteContent)
        contentRepository.saveContent(resolvedContent)
        return Result.success(Unit)
    }

    fun resolveUserConflict(local: User, remote: User): User {
        val strategy = determineConflictStrategy(local, remote)

        return when (strategy) {
            ConflictStrategy.USE_LOCAL -> local.copy(
                version = maxOf(local.version, remote.version) + 1,
                syncStatus = com.example.offlinefirst.domain.model.SyncStatus.SYNCED
            )
            ConflictStrategy.USE_REMOTE -> remote.copy(
                syncStatus = com.example.offlinefirst.domain.model.SyncStatus.SYNCED
            )
            ConflictStrategy.MERGE -> mergeUsers(local, remote)
        }
    }

    fun resolvePreferencesConflict(local: Preferences, remote: Preferences): Preferences {
        val strategy = determineConflictStrategy(local, remote)

        return when (strategy) {
            ConflictStrategy.USE_LOCAL -> local.copy(
                version = maxOf(local.version, remote.version) + 1,
                syncStatus = com.example.offlinefirst.domain.model.SyncStatus.SYNCED
            )
            ConflictStrategy.USE_REMOTE -> remote.copy(
                syncStatus = com.example.offlinefirst.domain.model.SyncStatus.SYNCED
            )
            ConflictStrategy.MERGE -> mergePreferences(local, remote)
        }
    }

    fun resolveContentConflict(local: DownloadedContent, remote: DownloadedContent): DownloadedContent {
        val strategy = determineConflictStrategy(local, remote)

        return when (strategy) {
            ConflictStrategy.USE_LOCAL -> local.copy(
                version = maxOf(local.version, remote.version) + 1,
                syncStatus = com.example.offlinefirst.domain.model.SyncStatus.SYNCED
            )
            ConflictStrategy.USE_REMOTE -> remote.copy(
                syncStatus = com.example.offlinefirst.domain.model.SyncStatus.SYNCED
            )
            ConflictStrategy.MERGE -> mergeContent(local, remote)
        }
    }

    private fun <T> determineConflictStrategy(local: T, remote: T): ConflictStrategy {
        val localUpdatedAt = when (local) {
            is User -> local.updatedAt
            is Preferences -> local.updatedAt
            is DownloadedContent -> local.updatedAt
            else -> return ConflictStrategy.USE_REMOTE
        }

        val remoteUpdatedAt = when (remote) {
            is User -> remote.updatedAt
            is Preferences -> remote.updatedAt
            is DownloadedContent -> remote.updatedAt
            else -> return ConflictStrategy.USE_REMOTE
        }

        return if (localUpdatedAt > remoteUpdatedAt) {
            ConflictStrategy.USE_LOCAL
        } else if (remoteUpdatedAt > localUpdatedAt) {
            ConflictStrategy.USE_REMOTE
        } else {
            ConflictStrategy.MERGE
        }
    }

    private fun mergeUsers(local: User, remote: User): User {
        return local.copy(
            email = if (local.email != remote.email) remote.email else local.email,
            displayName = if (local.displayName != remote.displayName) remote.displayName else local.displayName,
            phone = if (local.phone != remote.phone) remote.phone else local.phone,
            avatarUrl = if (local.avatarUrl != remote.avatarUrl) remote.avatarUrl else local.avatarUrl,
            isEmailVerified = local.isEmailVerified || remote.isEmailVerified,
            isPhoneVerified = local.isPhoneVerified || remote.isPhoneVerified,
            version = maxOf(local.version, remote.version) + 1,
            updatedAt = System.currentTimeMillis(),
            syncStatus = com.example.offlinefirst.domain.model.SyncStatus.SYNCED
        )
    }

    private fun mergePreferences(local: Preferences, remote: Preferences): Preferences {
        return local.copy(
            value = if (local.value != remote.value) remote.value else local.value,
            category = local.category,
            version = maxOf(local.version, remote.version) + 1,
            updatedAt = System.currentTimeMillis(),
            syncStatus = com.example.offlinefirst.domain.model.SyncStatus.SYNCED
        )
    }

    private fun mergeContent(local: DownloadedContent, remote: DownloadedContent): DownloadedContent {
        return local.copy(
            title = if (local.title != remote.title) remote.title else local.title,
            description = if (local.description != remote.description) remote.description else local.description,
            localPath = if (local.localPath != null) local.localPath else remote.localPath,
            downloadedSize = maxOf(local.downloadedSize, remote.downloadedSize),
            fileSize = maxOf(local.fileSize, remote.fileSize),
            version = maxOf(local.version, remote.version) + 1,
            accessedAt = System.currentTimeMillis(),
            syncStatus = com.example.offlinefirst.domain.model.SyncStatus.SYNCED
        )
    }

    private suspend fun fetchRemoteUser(userId: String): User? {
        return try {
            apiClient.apiService.getUser(userId)
        } catch (e: Exception) {
            null
        }
    }

    private suspend fun fetchRemotePreferences(prefsId: String): Preferences? {
        return try {
            apiClient.apiService.getPreferences(prefsId)
        } catch (e: Exception) {
            null
        }
    }

    private suspend fun fetchRemoteContent(contentId: String): DownloadedContent? {
        return try {
            apiClient.apiService.getContent(contentId)
        } catch (e: Exception) {
            null
        }
    }
}

enum class ConflictStrategy {
    USE_LOCAL,
    USE_REMOTE,
    MERGE
}


// === ARCHIVO: app/src/main/java/com/example/offlinefirst/data/sync/NetworkMonitor.kt ===
package com.example.offlinefirst.data.sync

import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkCapabilities
import android.net.NetworkRequest
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.callbackFlow
import javax.inject.Inject
import javax.inject.Singleton

sealed class NetworkStatus {
    data object Available : NetworkStatus()
    data object Unavailable : NetworkStatus()
    data object Losing : NetworkStatus()
    data object Lost : NetworkStatus()
}

@Singleton
class NetworkMonitor @Inject constructor(
    @ApplicationContext private val context: Context
) {
    private val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    
    private val _isConnected = MutableStateFlow(false)
    val isConnected: StateFlow<Boolean> = _isConnected.asStateFlow()
    
    private val _networkStatus = MutableStateFlow<NetworkStatus>(NetworkStatus.Unavailable)
    val networkStatus: StateFlow<NetworkStatus> = _networkStatus.asStateFlow()
    
    private val _connectionType = MutableStateFlow(ConnectionType.NONE)
    val connectionType: StateFlow<ConnectionType> = _connectionType.asStateFlow()
    
    private var networkCallback: ConnectivityManager.NetworkCallback? = null
    
    init {
        _isConnected.value = checkCurrentConnection()
        _networkStatus.value = if (_isConnected.value) NetworkStatus.Available else NetworkStatus.Unavailable
        _connectionType.value = getCurrentConnectionType()
        registerNetworkCallback()
    }
    
    private fun checkCurrentConnection(): Boolean {
        val network = connectivityManager.activeNetwork ?: return false
        val capabilities = connectivityManager.getNetworkCapabilities(network) ?: return false
        return capabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET) &&
               capabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_VALIDATED)
    }
    
    private fun getCurrentConnectionType(): ConnectionType {
        val network = connectivityManager.activeNetwork ?: return ConnectionType.NONE
        val capabilities = connectivityManager.getNetworkCapabilities(network) ?: return ConnectionType.NONE
        
        return when {
            capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> ConnectionType.WIFI
            capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> ConnectionType.CELLULAR
            capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> ConnectionType.ETHERNET
            capabilities.hasTransport(NetworkCapabilities.TRANSPORT_VPN) -> ConnectionType.VPN
            else -> ConnectionType.NONE
        }
    }
    
    private fun registerNetworkCallback() {
        val networkRequest = NetworkRequest.Builder()
            .addCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
            .addCapability(NetworkCapabilities.NET_CAPABILITY_VALIDATED)
            .addTransportType(NetworkCapabilities.TRANSPORT_WIFI)
            .addTransportType(NetworkCapabilities.TRANSPORT_CELLULAR)
            .addTransportType(NetworkCapabilities.TRANSPORT_ETHERNET)
            .addTransportType(NetworkCapabilities.TRANSPORT_VPN)
            .build()
        
        networkCallback = object : ConnectivityManager.NetworkCallback() {
            override fun onAvailable(network: Network) {
                _isConnected.value = true
                _networkStatus.value = NetworkStatus.Available
                _connectionType.value = getConnectionTypeForNetwork(network)
            }
            
            override fun onLosing(network: Network, maxMsToLive: Int) {
                _networkStatus.value = NetworkStatus.Losing
            }
            
            override fun onLost(network: Network) {
                _isConnected.value = false
                _networkStatus.value = NetworkStatus.Lost
                _connectionType.value = ConnectionType.NONE
            }
            
            override fun onUnavailable() {
                _isConnected.value = false
                _networkStatus.value = NetworkStatus.Unavailable
                _connectionType.value = ConnectionType.NONE
            }
            
            private fun getConnectionTypeForNetwork(network: Network): ConnectionType {
                val capabilities = connectivityManager.getNetworkCapabilities(network) ?: return ConnectionType.NONE
                return when {
                    capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> ConnectionType.WIFI
                    capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> ConnectionType.CELLULAR
                    capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> ConnectionType.ETHERNET
                    capabilities.hasTransport(NetworkCapabilities.TRANSPORT_VPN) -> ConnectionType.VPN
                    else -> ConnectionType.NONE
                }
            }
        }
        
        connectivityManager.registerNetworkCallback(networkRequest, networkCallback!!)
    }
    
    fun unregisterNetworkCallback() {
        networkCallback?.let {
            try {
                connectivityManager.unregisterNetworkCallback(it)
            } catch (e: IllegalArgumentException) {
            }
        }
        networkCallback = null
    }
    
    fun observeNetworkStatus(): Flow<NetworkStatus> = callbackFlow {
        val callback = object : ConnectivityManager.NetworkCallback() {
            override fun onAvailable(network: Network) {
                trySend(NetworkStatus.Available)
            }
            
            override fun onLosing(network: Network, maxMsToLive: Int) {
                trySend(NetworkStatus.Losing)
            }
            
            override fun onLost(network: Network) {
                trySend(NetworkStatus.Lost)
            }
            
            override fun onUnavailable() {
                trySend(NetworkStatus.Unavailable)
            }
        }
        
        val request = NetworkRequest.Builder()
            .addCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
            .build()
        
        connectivityManager.registerNetworkCallback(request, callback)
        
        awaitClose {
            connectivityManager.unregisterNetworkCallback(callback)
        }
    }
    
    fun isCurrentlyConnected(): Boolean = checkCurrentConnection()
    
    fun getCurrentNetworkType(): ConnectionType = getCurrentConnectionType()
}

enum class ConnectionType {
    WIFI,
    CELLULAR,
    ETHERNET,
    VPN,
    NONE
}

// === ARCHIVO: app/src/main/java/com/example/offlinefirst/di/DatabaseModule.kt ===
package com.example.offlinefirst.di

import android.content.Context
import androidx.room.Room
import com.example.offlinefirst.data.local.dao.ContentDao
import com.example.offlinefirst.data.local.dao.PreferencesDao
import com.example.offlinefirst.data.local.dao.SyncOperationDao
import com.example.offlinefirst.data.local.dao.UserDao
import com.example.offlinefirst.data.local.database.OfflineFirstDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {
    
    private const val DATABASE_NAME = "offline_first_database"
    private const val DATABASE_VERSION = 1
    
    @Provides
    @Singleton
    fun provideDatabase(
        @ApplicationContext context: Context
    ): OfflineFirstDatabase {
        return Room.databaseBuilder(
            context,
            OfflineFirstDatabase::class.java,
            DATABASE_NAME
        )
            .fallbackToDestructiveMigration()
            .addCallback(object : Room.Callback() {
                override fun onCreate(db: androidx.room.SupportSQLiteDatabase) {
                    super.onCreate(db)
                    db.execSQL("PRAGMA journal_mode=WAL")
                    db.execSQL("PRAGMA synchronous=NORMAL")
                    db.execSQL("PRAGMA foreign_keys=ON")
                }
                
                override fun onOpen(db: androidx.room.SupportSQLiteDatabase) {
                    super.onOpen(db)
                    db.execSQL("PRAGMA journal_mode=WAL")
                    db.execSQL("PRAGMA synchronous=NORMAL")
                    db.execSQL("PRAGMA foreign_keys=ON")
                    db.execSQL("PRAGMA cache_size=10000")
                    db.execSQL("PRAGMA temp_store=MEMORY")
                }
            })
            .setJournalMode(RoomDatabase.JournalMode.WRITE_AHEAD_LOGGING)
            .build()
    }
    
    @Provides
    @Singleton
    fun provideUserDao(database: OfflineFirstDatabase): UserDao {
        return database.userDao()
    }
    
    @Provides
    @Singleton
    fun providePreferencesDao(database: OfflineFirstDatabase): PreferencesDao {
        return database.preferencesDao()
    }
    
    @Provides
    @Singleton
    fun provideContentDao(database: OfflineFirstDatabase): ContentDao {
        return database.contentDao()
    }
    
    @Provides
    @Singleton
    fun provideSyncOperationDao(database: OfflineFirstDatabase): SyncOperationDao {
        return database.syncOperationDao()
    }
}

// === ARCHIVO: app/src/main/java/com/example/offlinefirst/di/NetworkModule.kt ===
package com.example.offlinefirst.di

import android.content.Context
import com.example.offlinefirst.data.remote.ApiClient
import com.example.offlinefirst.data.remote.ApiService
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.io.File
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {
    
    private const val BASE_URL = "https://api.offlinefirst.example.com/"
    private const val CACHE_SIZE = 10L * 1024 * 1024
    private const val CONNECT_TIMEOUT = 30L
    private const val READ_TIMEOUT = 30L
    private const val WRITE_TIMEOUT = 30L
    
    @Provides
    @Singleton
    fun provideCache(@ApplicationContext context: Context): Cache {
        val cacheDir = File(context.cacheDir, "http_cache")
        return Cache(cacheDir, CACHE_SIZE)
    }
    
    @Provides
    @Singleton
    fun provideLoggingInterceptor(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
    }
    
    @Provides
    @Singleton
    fun provideOkHttpClient(
        cache: Cache,
        loggingInterceptor: HttpLoggingInterceptor
    ): OkHttpClient {
        return OkHttpClient.Builder()
            .cache(cache)
            .addInterceptor(loggingInterceptor)
            .addInterceptor { chain ->
                val original = chain.request()
                val requestBuilder = original.newBuilder()
                    .header("Accept", "application/json")
                    .header("Content-Type", "application/json")
                    .header("User-Agent", "OfflineFirstApp/1.0.0")
                    .method(original.method, original.body)
                chain.proceed(requestBuilder.build())
            }
            .addNetworkInterceptor { chain ->
                val response = chain.proceed(chain.request())
                response.newBuilder()
                    .header("Cache-Control", "public, max-age=60")
                    .removeHeader("Pragma")
                    .build()
            }
            .connectTimeout(CONNECT_TIMEOUT, TimeUnit.SECONDS)
            .readTimeout(READ_TIMEOUT, TimeUnit.SECONDS)
            .writeTimeout(WRITE_TIMEOUT, TimeUnit.SECONDS)
            .retryOnConnectionFailure(true)
            .build()
    }
    
    @Provides
    @Singleton
    fun provideMoshi(): Moshi {
        return Moshi.Builder()
            .addLast(KotlinJsonAdapterFactory())
            .build()
    }
    
    @Provides
    @Singleton
    fun provideRetrofit(
        okHttpClient: OkHttpClient,
        moshi: Moshi
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .addConverterFactory(
                retrofit2.converter.kotlinx.serialization.asConverterFactory(
                    "application/json".toMediaType()
                )
            )
            .build()
    }
    
    @Provides
    @Singleton
    fun provideApiService(retrofit: Retrofit): ApiService {
        return retrofit.create(ApiService::class.java)
    }
    
    @Provides
    @Singleton
    fun provideApiClient(
        apiService: ApiService,
        @ApplicationContext context: Context
    ): ApiClient {
        return ApiClient(apiService, context)
    }
}

// === ARCHIVO: app/src/main/java/com/example/offlinefirst/di/RepositoryModule.kt ===
package com.example.offlinefirst.di

import com.example.offlinefirst.data.repository.ContentRepositoryImpl
import com.example.offlinefirst.data.repository.PreferencesRepositoryImpl
import com.example.offlinefirst.data.repository.SyncRepositoryImpl
import com.example.offlinefirst.data.repository.UserRepositoryImpl
import com.example.offlinefirst.domain.repository.ContentRepository
import com.example.offlinefirst.domain.repository.PreferencesRepository
import com.example.offlinefirst.domain.repository.SyncRepository
import com.example.offlinefirst.domain.repository.UserRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    
    @Binds
    @Singleton
    abstract fun bindUserRepository(
        userRepositoryImpl: UserRepositoryImpl
    ): UserRepository
    
    @Binds
    @Singleton
    abstract fun bindPreferencesRepository(
        preferencesRepositoryImpl: PreferencesRepositoryImpl
    ): PreferencesRepository
    
    @Binds
    @Singleton
    abstract fun bindContentRepository(
        contentRepositoryImpl: ContentRepositoryImpl
    ): ContentRepository
    
    @Binds
    @Singleton
    abstract fun bindSyncRepository(
        syncRepositoryImpl: SyncRepositoryImpl
    ): SyncRepository
}


// === ARCHIVO: app/src/main/java/com/example/offlinefirst/util/Resource.kt ===
package com.example.offlinefirst.util

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart

/**
 * Wrapper genérico para representar el estado de una operación asíncrona.
 * Proporciona una forma tipada de manejar loading, éxito y errores.
 */
sealed class Resource<out T> {
    /**
     * Estado inicial o en progreso de la operación.
     * Puede携带 datos parciales o mensaje de estado.
     */
    data class Loading<T>(
        val message: String = "Cargando...",
        val data: T? = null
    ) : Resource<T>() {
        fun <R> map(transform: (T) -> R): Resource<R> = Loading(message, data?.let(transform))
    }

    /**
     * Operación completada exitosamente.
     * Contiene los datos resultantes.
     */
    data class Success<T>(
        val data: T,
        val message: String? = null
    ) : Resource<T>() {
        fun <R> map(transform: (T) -> R): Resource<R> = Success(transform(data), message)
    }

    /**
     * Operación fallida con información del error.
     */
    data class Error<T>(
        val message: String,
        val throwable: Throwable? = null,
        val data: T? = null
    ) : Resource<T>() {
        fun <R> map(transform: (T) -> R): Resource<R> = Error(message, throwable, data?.let(transform))
    }

    /**
     * Verifica si el estado actual es de carga.
     */
    fun isLoading(): Boolean = this is Loading

    /**
     * Verifica si la operación fue exitosa.
     */
    fun isSuccess(): Boolean = this is Success

    /**
     * Verifica si hubo un error.
     */
    fun isError(): Boolean = this is Error

    /**
     * Obtiene los datos si están disponibles, null en caso contrario.
     */
    fun getOrNull(): T? = when (this) {
        is Success -> data
        is Loading -> data
        is Error -> data
    }

    /**
     * Obtiene los datos o lanza una excepción.
     */
    fun getOrThrow(): T = when (this) {
        is Success -> data
        is Error -> throw throwable ?: IllegalStateException(message)
        is Loading -> throw IllegalStateException("Resource is still loading")
    }

    /**
     * Obtiene los datos o un valor por defecto.
     */
    fun getOrDefault(default: T): T = when (this) {
        is Success -> data
        else -> default
    }

    companion object {
        /**
         * Crea un estado de carga inicial.
         */
        fun <T> loading(message: String = "Cargando..."): Resource<T> = Loading(message)

        /**
         * Crea un estado de éxito con datos.
         */
        fun <T> success(data: T, message: String? = null): Resource<T> = Success(data, message)

        /**
         * Crea un estado de error.
         */
        fun <T> error(message: String, throwable: Throwable? = null): Resource<T> =
            Error(message, throwable)
    }
}

/**
 * Extensión para convertir un Flow<T> en Flow<Resource<T>>.
 * Maneja automáticamente errores y estados de carga.
 */
fun <T> Flow<T>.asResource(): Flow<Resource<T>> =
    this
        .onStart { emit(Resource.loading()) }
        .map { Resource.success(it) }
        .catch { emit(Resource.error(it.message ?: "Error desconocido", it)) }

/**
 * Extensión para ejecutar una acción solo si es éxito.
 */
inline fun <T> Resource<T>.onSuccess(action: (T) -> Unit): Resource<T> {
    if (this is Resource.Success) action(data)
    return this
}

/**
 * Extensión para ejecutar una acción solo si hay error.
 */
inline fun <T> Resource<T>.onError(action: (String, Throwable?) -> Unit): Resource<T> {
    if (this is Resource.Error) action(message, throwable)
    return this
}

/**
 * Extensión para ejecutar una acción solo si está cargando.
 */
inline fun <T> Resource<T>.onLoading(action: (String) -> Unit): Resource<T> {
    if (this is Resource.Loading) action(message)
    return this
}

/**
 * Operador para encadenar operaciones que retornan Resource.
 * Si la operación actual es exitosa, transforma los datos.
 * Si es error o loading, pasa el estado sin transformar.
 */
fun <T, R> Resource<T>.flatMap(transform: (T) -> Resource<R>): Resource<R> = when (this) {
    is Resource.Success -> transform(data)
    is Resource.Error -> Resource.Error(message, throwable)
    is Resource.Loading -> Resource.Loading(message)
}

/**
 * Convierte Resource<Resource<T>> a Resource<T> extrayendo el valor anidado.
 */
fun <T> Resource<Resource<T>>.flatten(): Resource<T> = when (this) {
    is Resource.Success -> data
    is Resource.Error -> Resource.Error(message, throwable)
    is Resource.Loading -> Resource.Loading(message)
}

// === ARCHIVO: app/src/main/java/com/example/offlinefirst/util/Constants.kt ===
package com.example.offlinefirst.util

/**
 * Constantes de configuración global de la aplicación.
 * Agrupa timeouts, umbrales, límites y configuraciones de sincronización.
 */
object Constants {

    /**
     * Configuración de red y timeouts.
     */
    object Network {
        const val CONNECT_TIMEOUT_SECONDS = 30L
        const val READ_TIMEOUT_SECONDS = 30L
        const val WRITE_TIMEOUT_SECONDS = 30L
        const val RETRY_MAX_ATTEMPTS = 3
        const val RETRY_DELAY_MILLIS = 1000L
        const val RETRY_EXPONENTIAL_BACKOFF_MULTIPLIER = 2.0f
        const val MAX_CONCURRENT_REQUESTS = 5
        const val REQUEST_TIMEOUT_SECONDS = 60L
    }

    /**
     * Configuración de sincronización.
     */
    object Sync {
        const val SYNC_INTERVAL_MINUTES = 5L
        const val SYNC_WORK_NAME = "periodic_sync_work"
        const val SYNC_FLEX_INTERVAL_MINUTES = 2L
        const val MAX_PENDING_OPERATIONS = 100
        const val BATCH_SIZE = 50
        const val CONFLICT_RESOLUTION_STRATEGY = "server_wins"
        const val SYNC_ON_APP_START = true
        const val SYNC_ON_NETWORK_AVAILABLE = true
    }

    /**
     * Configuración de base de datos local.
     */
    object Database {
        const val DATABASE_NAME = "offline_first_db"
        const val DATABASE_VERSION = 1
        const val PAGE_SIZE = 20
        const val PREFETCH_DISTANCE = 5
        const val MAX_CACHE_SIZE_MB = 100
        const val CLEANUP_INTERVAL_HOURS = 24
        const val EXPIRED_CONTENT_RETENTION_DAYS = 7
    }

    /**
     * Configuración de caché.
     */
    object Cache {
        const val IN_MEMORY_CACHE_SIZE = 50
        const val DISK_CACHE_SIZE_MB = 50
        const val CACHE_EXPIRATION_HOURS = 24
        const val ENABLE_DISK_CACHE = true
    }

    /**
     * Configuración de UI.
     */
    object UI {
        const val ANIMATION_DURATION_MS = 300L
        const val DEBOUNCE_DELAY_MS = 300L
        const val SNACKBAR_DURATION_MS = 4000L
        const val TOAST_DURATION_MS = 2000L
        const val REFRESH_INTERVAL_MS = 5000L
        const val LAZY_LOAD_THRESHOLD = 5
    }

    /**
     * Configuración de rendimiento.
     */
    object Performance {
        const val MAX_READ_OPS_PER_SECOND = 1000
        const val MAX_WRITE_OPS_PER_SECOND = 1000
        const val LOCAL_OPERATION_MAX_LATENCY_MS = 200
        const val THREAD_POOL_SIZE = 4
        const val ENABLE_PARALLEL_PROCESSING = true
    }

    /**
     * Configuración de cola de operaciones.
     */
    object Queue {
        const val MAX_QUEUE_SIZE = 500
        const val MAX_RETRY_COUNT = 3
        const val RETRY_DELAY_BASE_MS = 500L
        const val MAX_RETRY_DELAY_MS = 30000L
        const val ENABLE_PRIORITY_QUEUE = true
    }

    /**
     * Configuración de contenido descargable.
     */
    object Content {
        const val MAX_DOWNLOAD_SIZE_MB = 500
        const val DEFAULT_DOWNLOAD_QUALITY = "high"
        const val ENABLE_AUTO_DOWNLOAD = false
        const val WIFI_ONLY_DOWNLOAD = true
        const val DOWNLOAD_NOTIFICATION_CHANNEL_ID = "download_channel"
    }

    /**
     * Configuración de seguridad.
     */
    object Security {
        const val ENCRYPT_LOCAL_DATA = true
        const val ENABLE_SSL_PINNING = false
        const val SESSION_TIMEOUT_MINUTES = 30
        const val MAX_LOGIN_ATTEMPTS = 5
        const val LOCKOUT_DURATION_MINUTES = 15
    }

    /**
     * Keys para SharedPreferences.
     */
    object Preferences {
        const val PREF_NAME = "offline_first_prefs"
        const val KEY_LAST_SYNC = "last_sync_timestamp"
        const val KEY_USER_ID = "current_user_id"
        const val KEY_AUTH_TOKEN = "auth_token"
        const val KEY_SYNC_ENABLED = "sync_enabled"
        const val KEY_WIFI_ONLY_SYNC = "wifi_only_sync"
        const val KEY_NOTIFICATIONS_ENABLED = "notifications_enabled"
    }

    /**
     * Keys para Bundle/Arguments.
     */
    object Arguments {
        const val ARG_USER_ID = "user_id"
        const val ARG_CONTENT_ID = "content_id"
        const val ARG_SYNC_STATUS = "sync_status"
        const val ARG_OPERATION_TYPE = "operation_type"
    }

    /**
     * Tags para logging.
     */
    object Tags {
        const val TAG_NETWORK = "Network"
        const val TAG_DATABASE = "Database"
        const val TAG_SYNC = "Sync"
        const val TAG_QUEUE = "Queue"
        const val TAG_VIEWMODEL = "ViewModel"
        const val TAG_REPOSITORY = "Repository"
        const val TAG_WORKER = "Worker"
    }

    /**
     * URLs de API.
     */
    object Api {
        const val BASE_URL = "https://api.offlinefirst.example.com/"
        const val USERS_ENDPOINT = "users"
        const val PREFERENCES_ENDPOINT = "preferences"
        const val CONTENT_ENDPOINT = "content"
        const val SYNC_ENDPOINT = "sync"
        const val AUTH_ENDPOINT = "auth"
    }

    /**
     * Códigos de resultado.
     */
    object ResultCodes {
        const val SUCCESS = 200
        const val CREATED = 201
        const val NO_CONTENT = 204
        const val BAD_REQUEST = 400
        const val UNAUTHORIZED = 401
        const val FORBIDDEN = 403
        const val NOT_FOUND = 404
        const val CONFLICT = 409
        const val SERVER_ERROR = 500
    }

    /**
     * Mensajes de error predefinidos.
     */
    object ErrorMessages {
        const val NETWORK_ERROR = "Error de conexión. Verifica tu conexión a internet."
        const val SERVER_ERROR = "Error del servidor. Intenta más tarde."
        const val UNKNOWN_ERROR = "Ocurrió un error inesperado."
        const val NO_DATA = "No hay datos disponibles."
        const val SYNC_FAILED = "Error al sincronizar datos."
        const val SAVE_FAILED = "Error al guardar datos."
        const val DELETE_FAILED = "Error al eliminar datos."
        const val VALIDATION_ERROR = "Datos inválidos."
    }
}

// === ARCHIVO: app/src/main/java/com/example/offlinefirst/presentation/viewmodel/UserViewModel.kt ===
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

// === ARCHIVO: app/src/main/java/com/example/offlinefirst/presentation/ui/screens/UserScreen.kt ===
package com.example.offlinefirst.presentation.ui.screens

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.CloudOff
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Sync
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.offlinefirst.domain.model.SyncStatus
import com.example.offlinefirst.domain.model.User
import com.example.offlinefirst.presentation.ui.components.SyncStatusIndicator
import com.example.offlinefirst.presentation.viewmodel.UserEvent
import com.example.offlinefirst.presentation.viewmodel.UserViewModel
import com.example.offlinefirst.util.Constants
import kotlinx.coroutines.flow.collectLatest

/**
 * Pantalla principal de gestión de usuarios.
 * Muestra la lista de usuarios con búsqueda, sincronización y acciones CRUD.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UserScreen(
    viewModel: UserViewModel = hiltViewModel(),
    onNavigateToDetail: (String) -> Unit = {},
    onNavigateToCreate: () -> Unit = {}
) {
    val uiState by viewModel.uiState.collectAsState()
    val snackbarHostState = remember { SnackbarHostState() }

    LaunchedEffect(Unit) {
        viewModel.events.collectLatest { event ->
            when (event) {
                is UserEvent.ShowSnackbar -> {
                    snackbarHostState.showSnackbar(event.message)
                }
                is UserEvent.ShowError -> {
                    snackbarHostState.showSnackbar(event.message)
                }
                is UserEvent.NavigateToUserDetail -> {
                    onNavigateToDetail(event.userId)
                }
                is UserEvent.NavigateBack -> {
                    // Navigation handled by compose navigation
                }
                is UserEvent.SyncCompleted -> {
                    snackbarHostState.showSnackbar("Datos sincronizados correctamente")
                }
                is UserEvent.SyncFailed -> {
                    snackbarHostState.showSnackbar("Error de sincronización: ${event.message}")
                }
            }
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Usuarios") },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    titleContentColor = MaterialTheme.colorScheme.onPrimary
                ),
                actions = {
                    if (uiState.isOffline) {
                        Icon(
                            imageVector = Icons.Default.CloudOff,
                            contentDescription = "Sin conexión",
                            tint = MaterialTheme.colorScheme.onPrimary,
                            modifier = Modifier.padding(end = 8.dp)
                        )
                    }
                    IconButton(
                        onClick = { viewModel.syncData() },
                        enabled = !uiState.isSyncing && !uiState.isOffline
                    ) {
                        if (uiState.isSyncing) {
                            CircularProgressIndicator(
                                modifier = Modifier.size(24.dp),
                                color = MaterialTheme.colorScheme.onPrimary,
                                strokeWidth = 2.dp
                            )
                        } else {
                            Icon(
                                imageVector = Icons.Default.Sync,
                                contentDescription = "Sincronizar",
                                tint = MaterialTheme.colorScheme.onPrimary
                            )
                        }
                    }
                }
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = onNavigateToCreate,
                containerColor = MaterialTheme.colorScheme.primary
            ) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = "Agregar usuario"
                )
            }
        },
        snackbarHost = { SnackbarHost(snackbarHostState) }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            // Sync progress indicator
            AnimatedVisibility(
                visible = uiState.isSyncing,
                enter = slideInVertically() + fadeIn(),
                exit = slideOutVertically() + fadeOut()
            ) {
                LinearProgressIndicator(
                    progress = { uiState.syncProgress },
                    modifier = Modifier.fillMaxWidth()
                )
            }

            // Search bar
            OutlinedTextField(
                value = uiState.searchQuery,
                onValueChange = { viewModel.onSearchQueryChanged(it) },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                placeholder = { Text("Buscar usuarios...") },
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Default.Search,
                        contentDescription = null
                    )
                },
                singleLine = true
            )

            // User count and sync status
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "${uiState.filteredUsers.size} usuarios",
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
                if (uiState.lastSyncTimestamp > 0) {
                    Text(
                        text = "Última sync: ${formatTimestamp(uiState.lastSyncTimestamp)}",
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            }

            Spacer(modifier = Modifier.height(8.dp))

            // User list
            if (uiState.isLoading && uiState.users.isEmpty()) {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator()
                }
            } else if (uiState.filteredUsers.isEmpty()) {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Text(
                            text = if (uiState.searchQuery.isNotEmpty()) 
                                "No se encontraron usuarios" 
                            else 
                                "No hay usuarios",
                            style = MaterialTheme.typography.bodyLarge
                        )
                        if (uiState.isOffline) {
                            Text(
                                text = "Sin conexión - mostrando datos locales",
                                style = MaterialTheme.typography.bodySmall,
                                color = MaterialTheme.colorScheme.error
                            )
                        }
                    }
                }
            } else {
                LazyColumn(
                    contentPadding = PaddingValues(16.dp),
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    items(
                        items = uiState.filteredUsers,
                        key = { it.id }
                    ) { user ->
                        UserCard(
                            user = user,
                            onEdit = { viewModel.selectUser(user) },
                            onDelete = { viewModel.deleteUser(user.id) }
                        )
                    }
                }
            }
        }
    }
}

/**
 * Card que muestra la información de un usuario.
 */
@Composable
fun UserCard(
    user: User,
    onEdit: () -> Unit,
    onDelete: () -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier.fillMaxWidth(),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column(modifier = Modifier.weight(1f)) {
                    Text(
                        text = user.displayName,
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                    Text(
                        text = "@${user.username}",
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
                SyncStatusIndicator(status = user.syncStatus)
            }

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = user.email,
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )

            if (user.phone != null) {
                Text(
                    text = user.phone,
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }

            Spacer(modifier = Modifier.height(12.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.End
            ) {
                IconButton(onClick = onEdit) {
                    Icon(
                        imageVector = Icons.Default.Edit,
                        contentDescription = "Editar",
                        tint = MaterialTheme.colorScheme.primary
                    )
                }
                Spacer(modifier = Modifier.width(8.dp))
                IconButton(onClick = onDelete) {
                    Icon(
                        imageVector = Icons.Default.Delete,
                        contentDescription = "Eliminar",
                        tint = MaterialTheme.colorScheme.error
                    )
                }
            }
        }
    }
}

/**
 * Formatea un timestamp en formato legible.
 */
private fun formatTimestamp(timestamp: Long): String {
    val now = System.currentTimeMillis()
    val diff = now - timestamp
    return when {
        diff < 60_000 -> "Hace un momento"
        diff < 3600_000 -> "Hace ${diff / 60_000} min"
        diff < 86400_000 -> "Hace ${diff / 3600_000} horas"
        else -> "Hace ${diff / 86400_000} días"
    }
}


// === ARCHIVO: app/src/main/java/com/example/offlinefirst/presentation/ui/components/SyncStatusIndicator.kt ===
package com.example.offlinefirst.presentation.ui.components

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Cloud
import androidx.compose.material.icons.filled.CloudOff
import androidx.compose.material.icons.filled.Error
import androidx.compose.material.icons.filled.Sync
import androidx.compose.material.icons.filled.SyncDisabled
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.offlinefirst.domain.model.SyncStatus

@Composable
fun SyncStatusIndicator(
    syncStatus: SyncStatus,
    pendingOperationsCount: Int = 0,
    lastSyncTime: Long? = null,
    isNetworkAvailable: Boolean = true,
    modifier: Modifier = Modifier
) {
    val statusColor by animateColorAsState(
        targetValue = when {
            !isNetworkAvailable -> Color(0xFF9E9E9E)
            syncStatus.isSyncing() -> Color(0xFF2196F3)
            syncStatus.hasConflict() -> Color(0xFFFF9800)
            syncStatus.hasError() -> Color(0xFFF44336)
            syncStatus.isSynced() -> Color(0xFF4CAF50)
            else -> Color(0xFFFFC107)
        },
        animationSpec = tween(300),
        label = "statusColor"
    )

    val infiniteTransition = rememberInfiniteTransition(label = "syncRotation")
    val rotation by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 360f,
        animationSpec = infiniteRepeatable(
            animation = tween(1500, easing = LinearEasing),
            repeatMode = RepeatMode.Restart
        ),
        label = "rotation"
    )

    val isAnimating = syncStatus.isSyncing()

    Surface(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp),
        shape = RoundedCornerShape(12.dp),
        color = MaterialTheme.colorScheme.surfaceVariant,
        tonalElevation = 2.dp
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.weight(1f)
            ) {
                SyncStatusIcon(
                    syncStatus = syncStatus,
                    isNetworkAvailable = isNetworkAvailable,
                    isAnimating = isAnimating,
                    rotation = if (isAnimating) rotation else 0f,
                    tint = statusColor
                )

                Spacer(modifier = Modifier.width(12.dp))

                Column {
                    Text(
                        text = getStatusTitle(syncStatus, isNetworkAvailable),
                        style = MaterialTheme.typography.titleSmall,
                        fontWeight = FontWeight.SemiBold,
                        color = MaterialTheme.colorScheme.onSurface
                    )

                    Spacer(modifier = Modifier.height(2.dp))

                    Text(
                        text = getStatusDescription(syncStatus, pendingOperationsCount, lastSyncTime, isNetworkAvailable),
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            }

            if (pendingOperationsCount > 0 && isNetworkAvailable) {
                PendingBadge(count = pendingOperationsCount)
            }
        }
    }
}

@Composable
private fun SyncStatusIcon(
    syncStatus: SyncStatus,
    isNetworkAvailable: Boolean,
    isAnimating: Boolean,
    rotation: Float,
    tint: Color
) {
    val icon = when {
        !isNetworkAvailable -> Icons.Default.CloudOff
        syncStatus.isSynced() -> Icons.Default.CheckCircle
        syncStatus.hasConflict() -> Icons.Default.Error
        syncStatus.hasError() -> Icons.Default.SyncDisabled
        syncStatus.isSyncing() -> Icons.Default.Sync
        else -> Icons.Default.Cloud
    }

    Box(
        modifier = Modifier
            .size(48.dp)
            .background(tint.copy(alpha = 0.1f), CircleShape),
        contentAlignment = Alignment.Center
    ) {
        Icon(
            imageVector = icon,
            contentDescription = "Sync status",
            tint = tint,
            modifier = Modifier
                .size(24.dp)
                .rotate(if (isAnimating) rotation else 0f)
        )
    }
}

@Composable
private fun PendingBadge(count: Int) {
    Box(
        modifier = Modifier
            .background(
                color = Color(0xFFFFC107),
                shape = RoundedCornerShape(12.dp)
            )
            .padding(horizontal = 10.dp, vertical = 4.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = "$count",
            color = Color.Black,
            fontSize = 12.sp,
            fontWeight = FontWeight.Bold
        )
    }
}

private fun getStatusTitle(syncStatus: SyncStatus, isNetworkAvailable: Boolean): String {
    return when {
        !isNetworkAvailable -> "Sin conexión"
        syncStatus.isSynced() -> "Sincronizado"
        syncStatus.isSyncing() -> "Sincronizando..."
        syncStatus.hasConflict() -> "Conflicto detectado"
        syncStatus.hasError() -> "Error de sincronización"
        syncStatus.isPending() -> "Pendiente de sincronizar"
        else -> "Estado desconocido"
    }
}

private fun getStatusDescription(
    syncStatus: SyncStatus,
    pendingCount: Int,
    lastSyncTime: Long?,
    isNetworkAvailable: Boolean
): String {
    return when {
        !isNetworkAvailable -> "Los datos se guardan localmente. Se sincronizará cuando haya conexión."
        syncStatus.isSynced() && lastSyncTime != null ->
            "Última sincronización: ${formatLastSyncTime(lastSyncTime)}"
        syncStatus.isSynced() -> "Todos los datos están actualizados"
        syncStatus.isSyncing() -> "Transfiriendo datos con el servidor..."
        syncStatus.hasConflict() -> "Se detectaron conflictos. Se requiere resolución manual."
        syncStatus.hasError() -> "La sincronización falló. Se reintentará automáticamente."
        syncStatus.isPending() -> "$pendingCount operación(es) esperando para sincronizar"
        else -> "Verificando estado de sincronización..."
    }
}

private fun formatLastSyncTime(timestamp: Long): String {
    val now = System.currentTimeMillis()
    val diff = now - timestamp

    return when {
        diff < 60_000 -> "Hace un momento"
        diff < 3_600_000 -> "Hace ${diff / 60_000} min"
        diff < 86_400_000 -> "Hace ${diff / 3_600_000} horas"
        else -> "Hace ${diff / 86_400_000} días"
    }
}

@Composable
fun CompactSyncIndicator(
    syncStatus: SyncStatus,
    isNetworkAvailable: Boolean,
    modifier: Modifier = Modifier
) {
    val statusColor by animateColorAsState(
        targetValue = when {
            !isNetworkAvailable -> Color(0xFF9E9E9E)
            syncStatus.isSynced() -> Color(0xFF4CAF50)
            syncStatus.hasConflict() -> Color(0xFFFF9800)
            syncStatus.hasError() -> Color(0xFFF44336)
            syncStatus.isSyncing() -> Color(0xFF2196F3)
            else -> Color(0xFFFFC107)
        },
        animationSpec = tween(300),
        label = "compactStatusColor"
    )

    val infiniteTransition = rememberInfiniteTransition(label = "compactSyncRotation")
    val rotation by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 360f,
        animationSpec = infiniteRepeatable(
            animation = tween(1500, easing = LinearEasing),
            repeatMode = RepeatMode.Restart
        ),
        label = "compactRotation"
    )

    val icon = when {
        !isNetworkAvailable -> Icons.Default.CloudOff
        syncStatus.isSynced() -> Icons.Default.CheckCircle
        syncStatus.hasConflict() -> Icons.Default.Error
        syncStatus.hasError() -> Icons.Default.SyncDisabled
        syncStatus.isSyncing() -> Icons.Default.Sync
        else -> Icons.Default.Cloud
    }

    Icon(
        imageVector = icon,
        contentDescription = "Estado de sincronización",
        tint = statusColor,
        modifier = modifier
            .size(20.dp)
            .rotate(if (syncStatus.isSyncing()) rotation else 0f)
    )
}

// === ARCHIVO: app/src/main/java/com/example/offlinefirst/data/sync/SyncWorker.kt ===
package com.example.offlinefirst.data.sync

import android.content.Context
import android.util.Log
import androidx.hilt.work.HiltWorker
import androidx.work.BackoffPolicy
import androidx.work.Constraints
import androidx.work.CoroutineWorker
import androidx.work.ExistingPeriodicWorkPolicy
import androidx.work.NetworkType
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkManager
import androidx.work.WorkerParameters
import com.example.offlinefirst.domain.model.SyncStatus
import com.example.offlinefirst.domain.repository.ContentRepository
import com.example.offlinefirst.domain.repository.PreferencesRepository
import com.example.offlinefirst.domain.repository.SyncRepository
import com.example.offlinefirst.domain.repository.UserRepository
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.withContext
import java.util.concurrent.TimeUnit

@HiltWorker
class SyncWorker @AssistedInject constructor(
    @Assisted private val context: Context,
    @Assisted workerParams: WorkerParameters,
    private val userRepository: UserRepository,
    private val preferencesRepository: PreferencesRepository,
    private val contentRepository: ContentRepository,
    private val syncRepository: SyncRepository,
    private val syncManager: SyncManager,
    private val networkMonitor: NetworkMonitor
) : CoroutineWorker(context, workerParams) {

    companion object {
        private const val TAG = "SyncWorker"
        const val WORK_NAME = "periodic_sync_work"
        private const val SYNC_INTERVAL_MINUTES = 5L
        private const val FLEX_INTERVAL_MINUTES = 2L
        private const val MAX_RETRY_ATTEMPTS = 3
    }

    override suspend fun doWork(): Result = withContext(Dispatchers.IO) {
        Log.d(TAG, "Iniciando trabajo de sincronización...")

        try {
            if (!networkMonitor.isNetworkAvailable()) {
                Log.w(TAG, "No hay conexión de red. Sincronización diferida.")
                return@withContext Result.retry()
            }

            val pendingUsers = userRepository.getPendingUsers().first()
            val pendingPreferences = preferencesRepository.getPendingPreferences().first()
            val pendingContents = contentRepository.getPendingContents().first()

            val totalPending = pendingUsers.size + pendingPreferences.size + pendingContents.size
            Log.d(TAG, "Operaciones pendientes: $totalPending")

            if (totalPending == 0) {
                Log.d(TAG, "No hay datos pendientes de sincronización")
                syncRepository.updateSyncStatus(SyncStatus.SYNCED)
                return@withContext Result.success()
            }

            syncRepository.updateSyncStatus(SyncStatus.SYNCING)

            var successCount = 0
            var failureCount = 0

            for (user in pendingUsers) {
                try {
                    val syncResult = syncManager.syncUser(user)
                    if (syncResult.isSuccess) {
                        userRepository.markAsSynced(user.id)
                        successCount++
                    } else {
                        userRepository.markAsError(user.id)
                        failureCount++
                    }
                } catch (e: Exception) {
                    Log.e(TAG, "Error sincronizando usuario ${user.id}", e)
                    userRepository.markAsError(user.id)
                    failureCount++
                }
            }

            for (preference in pendingPreferences) {
                try {
                    val syncResult = syncManager.syncPreference(preference)
                    if (syncResult.isSuccess) {
                        preferencesRepository.markAsSynced(preference.id)
                        successCount++
                    } else {
                        preferencesRepository.markAsError(preference.id)
                        failureCount++
                    }
                } catch (e: Exception) {
                    Log.e(TAG, "Error sincronizando preferencia ${preference.id}", e)
                    preferencesRepository.markAsError(preference.id)
                    failureCount++
                }
            }

            for (content in pendingContents) {
                try {
                    val syncResult = syncManager.syncContent(content)
                    if (syncResult.isSuccess) {
                        contentRepository.markAsSynced(content.id)
                        successCount++
                    } else {
                        contentRepository.markAsError(content.id)
                        failureCount++
                    }
                } catch (e: Exception) {
                    Log.e(TAG, "Error sincronizando contenido ${content.id}", e)
                    contentRepository.markAsError(content.id)
                    failureCount++
                }
            }

            val remainingPending = userRepository.getPendingUsers().first().size +
                    preferencesRepository.getPendingPreferences().first().size +
                    contentRepository.getPendingContents().first().size

            syncRepository.recordSyncOperation(
                successCount = successCount,
                failureCount = failureCount,
                remainingPending = remainingPending
            )

            val finalStatus = when {
                failureCount > 0 && remainingPending > 0 -> SyncStatus.ERROR
                remainingPending > 0 -> SyncStatus.PENDING
                else -> SyncStatus.SYNCED
            }
            syncRepository.updateSyncStatus(finalStatus)

            Log.d(TAG, "Sincronización completada. Éxitos: $successCount, Fallos: $failureCount, Pendientes: $remainingPending")

            if (failureCount > 0 && remainingPending > 0) {
                return@withContext Result.retry()
            }

            Result.success()
        } catch (e: Exception) {
            Log.e(TAG, "Error crítico en sincronización", e)
            syncRepository.updateSyncStatus(SyncStatus.ERROR)

            if (runAttemptCount < MAX_RETRY_ATTEMPTS) {
                Result.retry()
            } else {
                Log.e(TAG, "Máximo de reintentos alcanzado")
                Result.failure()
            }
        }
    }

    class Scheduler(private val context: Context) {
        fun schedulePeriodicSync() {
            val constraints = Constraints.Builder()
                .setRequiredNetworkType(NetworkType.CONNECTED)
                .setRequiresBatteryNotLow(true)
                .build()

            val syncRequest = PeriodicWorkRequestBuilder<SyncWorker>(
                SYNC_INTERVAL_MINUTES, TimeUnit.MINUTES,
                FLEX_INTERVAL_MINUTES, TimeUnit.MINUTES
            )
                .setConstraints(constraints)
                .setBackoffCriteria(
                    BackoffPolicy.EXPONENTIAL,
                    30, TimeUnit.SECONDS
                )
                .addTag(WORK_NAME)
                .build()

            WorkManager.getInstance(context).enqueueUniquePeriodicWork(
                WORK_NAME,
                ExistingPeriodicWorkPolicy.KEEP,
                syncRequest
            )

            Log.d(TAG, "Sincronización periódica programada cada $SYNC_INTERVAL_MINUTES minutos")
        }

        fun cancelPeriodicSync() {
            WorkManager.getInstance(context).cancelUniqueWork(WORK_NAME)
            Log.d(TAG, "Sincronización periódica cancelada")
        }

        fun triggerImmediateSync() {
            val constraints = Constraints.Builder()
                .setRequiredNetworkType(NetworkType.CONNECTED)
                .build()

            val syncRequest = androidx.work.OneTimeWorkRequestBuilder<SyncWorker>()
                .setConstraints(constraints)
                .build()

            WorkManager.getInstance(context).enqueue(syncRequest)
            Log.d(TAG, "Sincronización inmediata iniciada")
        }
    }
}

// === ARCHIVO: settings.gradle.kts ===
pluginManagement {
    repositories {
        google {
            content {
                includeGroupByRegex("com\\.android.*")
                includeGroupByRegex("com\\.google.*")
                includeGroupByRegex("androidx.*")
            }
        }
        mavenCentral()
        gradlePluginPortal()
    }
}

dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "OfflineFirstApp"

include(":app")

gradle.beforeProject { project ->
    if (project.name == "app") {
        project.extra.apply {
            set("compose_version", "2024.12.01")
            set("kotlin_version", "2.1.0")
            set("room_version", "2.6.1")
            set("hilt_version", "2.51.1")
            set("coroutines_version", "1.8.1")
            set("retrofit_version", "2.11.0")
            set("moshi_version", "1.15.1")
            set("work_version", "2.9.1")
            set("lifecycle_version", "2.8.7")
            set("navigation_version", "2.8.5")
        }
    }
}

allprojects {
    tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile>().configureEach {
        kotlinOptions {
            jvmTarget = "21"
            freeCompilerArgs += listOf(
                "-opt-in=kotlin.RequiresOptIn",
                "-opt-in=kotlin.ExperimentalStdlibApi"
            )
        }
    }
}

subprojects {
    afterEvaluate {
        if (plugins.hasPlugin("com.android.application") || plugins.hasPlugin("com.android.library")) {
            extensions.configure<com.android.build.gradle.BaseExtension> {
                compileOptions {
                    sourceCompatibility = JavaVersion.VERSION_21
                    targetCompatibility = JavaVersion.VERSION_21
                }
                kotlinOptions {
                    jvmTarget = "21"
                }
            }
        }
    }
}

tasks.register("clean", Delete::class) {
    delete(rootProject.layout.buildDirectory)
}

tasks.register("dependencies", org.gradle.api.tasks.diagnostics.DependencyReportTask::class) {
    configuration = "releaseRuntimeClasspath"
}

buildscript {
    extra.apply {
        set("compose_version", "2024.12.01")
    }
}


=== ARCHIVO: app/src/main/java/com/example/offlinefirst/presentation/ui/theme/OfflineFirstTheme.kt ===
package com.example.offlinefirst.presentation.ui.theme

import android.app.Activity
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat

private val DarkColorScheme = darkColorScheme(
    primary = Color(0xFF4FC3F7),
    onPrimary = Color(0xFF003544),
    primaryContainer = Color(0xFF004D61),
    onPrimaryContainer = Color(0xFFB4EBFF),
    secondary = Color(0xFF81D4FA),
    onSecondary = Color(0xFF00344A),
    secondaryContainer = Color(0xFF004C68),
    onSecondaryContainer = Color(0xFFD1E9FF),
    tertiary = Color(0xFFFFB74D),
    onTertiary = Color(0xFF4A2800),
    tertiaryContainer = Color(0xFF6A3C00),
    onTertiaryContainer = Color(0xFFFFDDB3),
    error = Color(0xFFFFB4AB),
    onError = Color(0xFF690005),
    errorContainer = Color(0xFF93000A),
    onErrorContainer = Color(0xFFFFDAD6),
    background = Color(0xFF1A1C1E),
    onBackground = Color(0xFFE2E2E6),
    surface = Color(0xFF1A1C1E),
    onSurface = Color(0xFFE2E2E6),
    surfaceVariant = Color(0xFF43474E),
    onSurfaceVariant = Color(0xFFC3C6CF),
    outline = Color(0xFF8D9199),
    outlineVariant = Color(0xFF43474E),
    inverseSurface = Color(0xFFE2E2E6),
    inverseOnSurface = Color(0xFF1A1C1E),
    inversePrimary = Color(0xFF006879)
)

private val LightColorScheme = lightColorScheme(
    primary = Color(0xFF006879),
    onPrimary = Color(0xFFFFFFFF),
    primaryContainer = Color(0xFFB4EBFF),
    onPrimaryContainer = Color(0xFF001F26),
    secondary = Color(0xFF4A6267),
    onSecondary = Color(0xFFFFFFFF),
    secondaryContainer = Color(0xFFCCE7F0),
    onSecondaryContainer = Color(0xFF051F26),
    tertiary = Color(0xFF7C5800),
    onTertiary = Color(0xFFFFFFFF),
    tertiaryContainer = Color(0xFFFFDEAE),
    onTertiaryContainer = Color(0xFF271900),
    error = Color(0xFFBA1A1A),
    onError = Color(0xFFFFFFFF),
    errorContainer = Color(0xFFFFDAD6),
    onErrorContainer = Color(0xFF410002),
    background = Color(0xFFFBFCFE),
    onBackground = Color(0xFF1A1C1E),
    surface = Color(0xFFFBFCFE),
    onSurface = Color(0xFF1A1C1E),
    surfaceVariant = Color(0xFFDFE3EB),
    onSurfaceVariant = Color(0xFF43474E),
    outline = Color(0xFF73777F),
    outlineVariant = Color(0xFFC3C6CF),
    inverseSurface = Color(0xFF2F3033),
    inverseOnSurface = Color(0xFFF1F0F4),
    inversePrimary = Color(0xFF4FD8EB)
)

@Composable
fun OfflineFirstTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colorScheme = if (darkTheme) DarkColorScheme else LightColorScheme
    val view = LocalView.current

    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as Activity).window
            window.statusBarColor = colorScheme.primary.toArgb()
            WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = !darkTheme
        }
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}

=== ARCHIVO: app/src/main/java/com/example/offlinefirst/presentation/ui/theme/Type.kt ===
package com.example.offlinefirst.presentation.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

val Typography = Typography(
    displayLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 57.sp,
        lineHeight = 64.sp,
        letterSpacing = (-0.25).sp
    ),
    displayMedium = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 45.sp,
        lineHeight = 52.sp,
        letterSpacing = 0.sp
    ),
    displaySmall = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 36.sp,
        lineHeight = 44.sp,
        letterSpacing = 0.sp
    ),
    headlineLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 32.sp,
        lineHeight = 40.sp,
        letterSpacing = 0.sp
    ),
    headlineMedium = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 28.sp,
        lineHeight = 36.sp,
        letterSpacing = 0.sp
    ),
    headlineSmall = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 24.sp,
        lineHeight = 32.sp,
        letterSpacing = 0.sp
    ),
    titleLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 22.sp,
        lineHeight = 28.sp,
        letterSpacing = 0.sp
    ),
    titleMedium = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Medium,
        fontSize = 16.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.15.sp
    ),
    titleSmall = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Medium,
        fontSize = 14.sp,
        lineHeight = 20.sp,
        letterSpacing = 0.1.sp
    ),
    bodyLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.5.sp
    ),
    bodyMedium = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 14.sp,
        lineHeight = 20.sp,
        letterSpacing = 0.25.sp
    ),
    bodySmall = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 12.sp,
        lineHeight = 16.sp,
        letterSpacing = 0.4.sp
    ),
    labelLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Medium,
        fontSize = 14.sp,
        lineHeight = 20.sp,
        letterSpacing = 0.1.sp
    ),
    labelMedium = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Medium,
        fontSize = 12.sp,
        lineHeight = 16.sp,
        letterSpacing = 0.5.sp
    ),
    labelSmall = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Medium,
        fontSize = 11.sp,
        lineHeight = 16.sp,
        letterSpacing = 0.5.sp
    )
)

=== ARCHIVO: app/src/main/java/com/example/offlinefirst/presentation/ui/screens/UserScreen.kt ===
package com.example.offlinefirst.presentation.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.offlinefirst.domain.model.User
import com.example.offlinefirst.domain.model.SyncStatus
import com.example.offlinefirst.presentation.viewmodel.UserViewModel
import com.example.offlinefirst.presentation.ui.components.SyncStatusIndicator

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UserScreen(
    viewModel: UserViewModel,
    onNavigateToPreferences: () -> Unit,
    onNavigateToContent: () -> Unit,
    modifier: Modifier = Modifier
) {
    val uiState by viewModel.uiState.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Users") },
                actions = {
                    IconButton(onClick = { viewModel.syncData() }) {
                        Icon(Icons.Default.Sync, contentDescription = "Sync")
                    }
                    IconButton(onClick = onNavigateToPreferences) {
                        Icon(Icons.Default.Settings, contentDescription = "Preferences")
                    }
                    IconButton(onClick = onNavigateToContent) {
                        Icon(Icons.Default.Folder, contentDescription = "Content")
                    }
                }
            )
        }
    ) { paddingValues ->
        Column(
            modifier = modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            SyncStatusIndicator(
                syncStatus = uiState.syncStatus,
                modifier = Modifier.fillMaxWidth()
            )

            OutlinedTextField(
                value = uiState.searchQuery,
                onValueChange = { viewModel.onSearchQueryChanged(it) },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 8.dp),
                placeholder = { Text("Search users...") },
                leadingIcon = { Icon(Icons.Default.Search, contentDescription = null) },
                singleLine = true
            )

            if (uiState.isLoading) {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator()
                }
            } else if (uiState.error != null) {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Text(
                            text = uiState.error ?: "Unknown error",
                            color = MaterialTheme.colorScheme.error
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        Button(onClick = { viewModel.loadUsers() }) {
                            Text("Retry")
                        }
                    }
                }
            } else {
                LazyColumn(
                    modifier = Modifier.fillMaxSize(),
                    contentPadding = PaddingValues(16.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    items(uiState.users) { user ->
                        UserCard(
                            user = user,
                            onClick = { viewModel.selectUser(user) }
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun UserCard(
    user: User,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        onClick = onClick,
        modifier = modifier.fillMaxWidth()
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = Icons.Default.Person,
                contentDescription = null,
                modifier = Modifier.size(40.dp),
                tint = MaterialTheme.colorScheme.primary
            )
            Spacer(modifier = Modifier.width(16.dp))
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = user.displayName ?: user.username,
                    style = MaterialTheme.typography.titleMedium
                )
                Text(
                    text = user.email,
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
            SyncStatusBadge(syncStatus = user.syncStatus)
        }
    }
}

@Composable
private fun SyncStatusBadge(syncStatus: SyncStatus) {
    val (icon, color) = when (syncStatus) {
        SyncStatus.SYNCED -> Icons.Default.CloudDone to MaterialTheme.colorScheme.primary
        SyncStatus.PENDING -> Icons.Default.CloudUpload to MaterialTheme.colorScheme.tertiary
        SyncStatus.CONFLICT -> Icons.Default.Warning to MaterialTheme.colorScheme.error
        SyncStatus.ERROR -> Icons.Default.Error to MaterialTheme.colorScheme.error
        SyncStatus.SYNCING -> Icons.Default.Sync to MaterialTheme.colorScheme.secondary
    }
    Icon(
        imageVector = icon,
        contentDescription = syncStatus.name,
        tint = color,
        modifier = Modifier.size(20.dp)
    )
}

=== ARCHIVO: app/src/main/java/com/example/offlinefirst/domain/model/SyncOperationType.kt ===
package com.example.offlinefirst.domain.model

enum class SyncOperationType {
    CREATE,
    UPDATE,
    DELETE,
    SYNC
}

=== ARCHIVO: app/src/main/java/com/example/offlinefirst/domain/model/Success.kt ===
package com.example.offlinefirst.domain.model

sealed class Success<out T> {
    data class Success<T>(val data: T) : Success<T>()
    data object Empty : Success<Nothing>()
}

=== ARCHIVO: app/src/main/java/com/example/offlinefirst/util/Loading.kt ===
package com.example.offlinefirst.util

sealed class Loading<out T> {
    data object Loading : Loading<Nothing>()
    data class LoadingWithMessage<T>(val message: String) : Loading<T>()
}

=== ARCHIVO: app/src/main/java/com/example/offlinefirst/util/Error.kt ===
package com.example.offlinefirst.util

sealed class Error<out T> {
    data class Error(val message: String, val cause: Throwable? = null) : Error<Nothing>()
    data class ServerError<T>(val code: Int, val message: String) : Error<T>()
    data class NetworkError<T>(val message: String) : Error<T>()
}

=== ARCHIVO: app/src/main/java/com/example/offlinefirst/util/Sync.kt ===
package com.example.offlinefirst.util

sealed class Sync<out T> {
    data object Idle : Sync<Nothing>()
    data class Syncing<T>(val progress: Float = 0f) : Sync<T>()
    data class Completed<T>(val data: T) : Sync<T>()
    data class Failed<T>(val error: String) : Sync<T>()
}

=== ARCHIVO: app/src/main/java/com/example/offlinefirst/data/sync/Failed.kt ===
package com.example.offlinefirst.data.sync

sealed class Failed<out T> {
    data class Failed(val error: String, val retryable: Boolean = false) : Failed<Nothing>()
    data class NetworkError<T>(val message: String) : Failed<T>()
    data class ServerError<T>(val code: Int, val message: String) : Failed<T>()
}

=== ARCHIVO: app/src/main/java/com/example/offlinefirst/data/remote/dto/UserDto.kt ===
package com.example.offlinefirst.data.remote.dto

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class UserDto(
    @Json(name = "id") val id: String,
    @Json(name = "username") val username: String,
    @Json(name = "email") val email: String,
    @Json(name = "displayName") val displayName: String?,
    @Json(name = "phone") val phone: String?,
    @Json(name = "avatarUrl") val avatarUrl: String?,
    @Json(name = "isEmailVerified") val isEmailVerified: Boolean = false,
    @Json(name = "isPhoneVerified") val isPhoneVerified: Boolean = false,
    @Json(name = "syncStatus") val syncStatus: String = "SYNCED",
    @Json(name = "lastSyncAt") val lastSyncAt: Long? = null,
    @Json(name = "createdAt") val createdAt: Long = System.currentTimeMillis(),
    @Json(name = "updatedAt") val updatedAt: Long = System.currentTimeMillis()
)

=== ARCHIVO: app/src/main/java/com/example/offlinefirst/data/remote/dto/ContentDto.kt ===
package com.example.offlinefirst.data.remote.dto

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ContentDto(
    @Json(name = "id") val id: String,
    @Json(name = "title") val title: String,
    @Json(name = "description") val description: String?,
    @Json(name = "url") val url: String,
    @Json(name = "fileSize") val fileSize: Long = 0L,
    @Json(name = "downloadedSize") val downloadedSize: Long = 0L,
    @Json(name = "localPath") val localPath: String? = null,
    @Json(name = "category") val category: String = "DOCUMENT",
    @Json(name = "tags") val tags: List<String> = emptyList(),
    @Json(name = "mimeType") val mimeType: String? = null,
    @Json(name = "hash") val hash: String? = null,
    @Json(name = "expiresAt") val expiresAt: Long? = null,
    @Json(name = "isAvailableOffline") val isAvailableOffline: Boolean = false,
    @Json(name = "syncStatus") val syncStatus: String = "SYNCED",
    @Json(name = "lastSyncAt") val lastSyncAt: Long? = null,
    @Json(name = "createdAt") val createdAt: Long = System.currentTimeMillis(),
    @Json(name = "updatedAt") val updatedAt: Long = System.currentTimeMillis()
)

=== ARCHIVO: app/src/main/java/com/example/offlinefirst/data/remote/dto/PreferencesDto.kt ===
package com.example.offlinefirst.data.remote.dto

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class PreferencesDto(
    @Json(name = "id") val id: String,
    @Json(name = "key") val key: String,
    @Json(name = "value") val value: String,
    @Json(name = "dataType") val dataType: String = "STRING",
    @Json(name = "category") val category: String = "GENERAL",
    @Json(name = "userId") val userId: String? = null,
    @Json(name = "isGlobal") val isGlobal: Boolean = true,
    @Json(name = "isEncrypted") val isEncrypted: Boolean = false,
    @Json(name = "syncStatus") val syncStatus: String = "SYNCED",
    @Json(name = "lastSyncAt") val lastSyncAt: Long? = null,
    @Json(name = "createdAt") val createdAt: Long = System.currentTimeMillis(),
    @Json(name = "updatedAt") val updatedAt: Long = System.currentTimeMillis()
)

=== ARCHIVO: app/src/main/java/com/example/offlinefirst/data/remote/dto/SyncRequestDto.kt ===
package com.example.offlinefirst.data.remote.dto

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class SyncRequestDto(
    @Json(name = "users") val users: List<UserDto> = emptyList(),
    @Json(name = "preferences") val preferences: List<PreferencesDto> = emptyList(),
    @Json(name = "content") val content: List<ContentDto> = emptyList(),
    @Json(name = "lastSyncTimestamp") val lastSyncTimestamp: Long? = null,
    @Json(name = "forceFullSync") val forceFullSync: Boolean = false
)

=== ARCHIVO: app/src/main/java/com/example/offlinefirst/data/remote/dto/SyncResponseDto.kt ===
package com.example.offlinefirst.data.remote.dto

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class SyncResponseDto(
    @Json(name = "success") val success: Boolean = true,
    @Json(name = "users") val users: List<UserDto> = emptyList(),
    @Json(name = "preferences") val preferences: List<PreferencesDto> = emptyList(),
    @Json(name = "content") val content: List<ContentDto> = emptyList(),
    @Json(name = "conflicts") val conflicts: List<ConflictDto> = emptyList(),
    @Json(name = "syncTimestamp") val syncTimestamp: Long = System.currentTimeMillis(),
    @Json(name = "message") val message: String? = null
)

@JsonClass(generateAdapter = true)
data class ConflictDto(
    @Json(name = "entityType") val entityType: String,
    @Json(name = "entityId") val entityId: String,
    @Json(name = "localVersion") val localVersion: String,
    @Json(name = "remoteVersion") val remoteVersion: String,
    @Json(name = "conflictType") val conflictType: String
)

=== ARCHIVO: app/src/main/java/com/example/offlinefirst/data/local/dao/SyncOperationDao.kt ===
package com.example.offlinefirst.data.local.dao

import androidx.room.*
import com.example.offlinefirst.data.local.entity.SyncOperationEntity
import kotlinx.coroutines.flow.Flow

interface SyncOperationDao {
    suspend fun insert(operation: SyncOperationEntity): Long
    suspend fun insertAll(operations: List<SyncOperationEntity>): List<Long>
    suspend fun update(operation: SyncOperationEntity)
    suspend fun delete(operation: SyncOperationEntity)
    suspend fun deleteById(id: String)
    suspend fun deleteByStatus(status: String)
    suspend fun deleteOldCompleted(olderThan: Long): Int
    suspend fun getById(id: String): SyncOperationEntity?
    fun getByIdFlow(id: String): Flow<SyncOperationEntity?>
    fun getAll(): Flow<List<SyncOperationEntity>>
    suspend fun getAllLimited(limit: Int): List<SyncOperationEntity>
    fun getByStatus(status: String): Flow<List<SyncOperationEntity>>
    suspend fun getPendingByStatus(status: String, limit: Int): List<SyncOperationEntity>
    suspend fun getByStatuses(statuses: List<String>, limit: Int): List<SyncOperationEntity>
    suspend fun getByEntityTypeAndStatus(entityType: String, status: String): List<SyncOperationEntity>
    suspend fun getPendingOperationForEntity(entityType: String, entityId: String): SyncOperationEntity?
    fun getByOperationType(operationType: String): Flow<List<SyncOperationEntity>>
    @Query("SELECT COUNT(*) FROM sync_operation")
    suspend fun getOperationCount(): Int
    @Query("SELECT COUNT(*) FROM sync_operation WHERE status = :status")
    suspend fun getCountByStatus(status: String): Int
    @Query("SELECT COUNT(*) FROM sync_operation WHERE entityType = :entityType")
    suspend fun getCountByEntityType(entityType: String): Int
    @Query("SELECT COUNT(*) FROM sync_operation WHERE operationType = :operationType AND status = :status")
    suspend fun getCountByOperationTypeAndStatus(operationType: String, status: String): Int
    suspend fun updateStatus(id: String, status: String, errorMessage: String?, updatedAt: Long)
    suspend fun markAsCompleted(id: String, status: String, executedAt: Long, updatedAt: Long)
    suspend fun markAsFailed(id: String, status: String, errorMessage: String?, updatedAt: Long)
    suspend fun incrementRetryCount(id: String, updatedAt: Long)
    suspend fun getNextPending(): SyncOperationEntity?
    suspend fun getNextBatch(batchSize: Int): List<SyncOperationEntity>
    @Query("SELECT * FROM sync_operation WHERE status IN ('PENDING', 'IN_PROGRESS') ORDER BY createdAt ASC")
    fun getActiveOperations(): Flow<List<SyncOperationEntity>>
    suspend fun getRetryableOperations(maxRetries: Int): List<SyncOperationEntity>
    suspend fun getFailedOperations(maxRetries: Int): List<SyncOperationEntity>
    suspend fun cancelPendingForEntity(entityType: String, entityId: String): Int
    @Query("SELECT EXISTS(SELECT 1 FROM sync_operation WHERE entityType = :entityType AND entityId = :entityId AND status IN ('PENDING', 'IN_PROGRESS'))")
    suspend fun hasPendingOperation(entityType: String, entityId: String): Boolean
    suspend fun getAndLockNextPending(): SyncOperationEntity?
    @Query("SELECT MAX(createdAt) FROM sync_operation WHERE entityType = :entityType AND status = 'COMPLETED'")
    suspend fun getLastCompletedTime(entityType: String): Long?
    suspend fun getOperationsSince(since: Long): List<SyncOperationEntity>

    // Additional methods needed by SyncRepositoryImpl
    @Query("SELECT * FROM sync_operation WHERE id = :id")
    suspend fun getOperationById(id: String): SyncOperationEntity?

    @Query("SELECT * FROM sync_operation ORDER BY createdAt ASC")
    suspend fun getAllOperations(): List<SyncOperationEntity>

    @Query("SELECT * FROM sync_operation WHERE status = 'PENDING' ORDER BY createdAt ASC")
    suspend fun getPendingOperations(): List<SyncOperationEntity>

    @Query("SELECT * FROM sync_operation WHERE operationType = :operationType ORDER BY createdAt ASC")
    suspend fun getOperationsByType(operationType: String): List<SyncOperationEntity>

    @Query("SELECT * FROM sync_operation WHERE status = :status ORDER BY createdAt ASC")
    suspend fun getOperationsByStatus(status: String): List<SyncOperationEntity>

    @Query("SELECT * FROM sync_operation WHERE status = 'FAILED' ORDER BY createdAt ASC")
    suspend fun getFailedOperationsList(): List<SyncOperationEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertOrUpdate(operation: SyncOperationEntity)

    @Query("SELECT * FROM sync_operation WHERE id IN (:ids)")
    suspend fun getOperationsByIds(ids: List<String>): List<SyncOperationEntity>

    @Query("UPDATE sync_operation SET completedAt = :completedAt, updatedAt = :updatedAt WHERE id = :id")
    suspend fun updateCompletedAt(id: String, completedAt: Long, updatedAt: Long)

    @Query("DELETE FROM sync_operation WHERE status = 'COMPLETED' AND completedAt < :olderThan")
    suspend fun deleteCompletedOperations(olderThan: Long): Int

    @Query("DELETE FROM sync_operation WHERE createdAt < :olderThanTimestamp")
    suspend fun deleteOldOperations(olderThanTimestamp: Long): Int

    @Query("SELECT COUNT(*) FROM sync_operation WHERE status = 'PENDING'")
    suspend fun getPendingCount(): Int

    @Query("SELECT COUNT(*) FROM sync_operation WHERE status = 'FAILED'")
    suspend fun getFailedCount(): Int

    @Query("UPDATE sync_operation SET status = 'PENDING', updatedAt = :updatedAt WHERE status = 'FAILED'")
    suspend fun retryAllFailed(): Int

    @Query("SELECT * FROM sync_operation WHERE entityId = :entityId ORDER BY createdAt ASC")
    suspend fun getOperationsByEntityId(entityId: String): List<SyncOperationEntity>
}

=== ARCHIVO: app/src/main/java/com/example/offlinefirst/domain/model/SyncOperation.kt ===
package com.example.offlinefirst.domain.model

data class SyncOperation(
    val id: String,
    val entityId: String,
    val entityType: EntityType,
    val operationType: SyncOperationType,
    val payload: String,
    val status: SyncOperationStatus = SyncOperationStatus.PENDING,
    val errorMessage: String? = null,
    val retryCount: Int = 0,
    val maxRetries: Int = 3,
    val createdAt: Long = System.currentTimeMillis(),
    val updatedAt: Long = System.currentTimeMillis(),
    val startedAt: Long? = null,
    val completedAt: Long? = null,
    val priority: OperationPriority = OperationPriority.NORMAL,
    val dependsOn: String? = null
) {
    fun canRetry(): Boolean = status == SyncOperationStatus.FAILED && retryCount < maxRetries
    fun shouldRetry(): Boolean = when {
        status != SyncOperationStatus.FAILED -> false
        retryCount >= maxRetries -> false
        else -> true
    }
    fun incrementAttempt(): SyncOperation = copy(retryCount = retryCount + 1, updatedAt = System.currentTimeMillis())
    fun markAsInProgress(): SyncOperation = copy(
        status = SyncOperationStatus.IN_PROGRESS,
        startedAt = System.currentTimeMillis(),
        updatedAt = System.currentTimeMillis()
    )
    fun markAsCompleted(): SyncOperation = copy(
        status = SyncOperationStatus.COMPLETED,
        completedAt = System.currentTimeMillis(),
        updatedAt = System.currentTimeMillis()
    )
    fun markAsFailed(error: String): SyncOperation = copy(
        status = SyncOperationStatus.FAILED,
        errorMessage = error,
        updatedAt = System.currentTimeMillis()
    )
}

enum class EntityType {
    USER, PREFERENCES, CONTENT, SYNC_METADATA
}

enum class SyncOperationStatus {
    PENDING, IN_PROGRESS, COMPLETED, FAILED, CANCELLED
}

enum class OperationPriority {
    CRITICAL, HIGH, NORMAL, LOW
}


 // === ARCHIVO: app/src/main/java/com/example/offlinefirst/domain/model/User.kt ===
package com.example.offlinefirst.domain.model

import java.util.UUID

data class User(
    val id: String = UUID.randomUUID().toString(),
    val username: String,
    val email: String,
    val displayName: String,
    val avatarUrl: String? = null,
    val phoneNumber: String? = null,
    val isEmailVerified: Boolean = false,
    val isPhoneVerified: Boolean = false,
    val createdAt: Long = System.currentTimeMillis(),
    val updatedAt: Long = System.currentTimeMillis(),
    val lastSyncAt: Long? = null,
    val syncStatus: SyncStatus = SyncStatus.PENDING
) {
    init {
        require(username.isNotBlank()) { "Username cannot be blank" }
        require(email.isNotBlank()) { "Email cannot be blank" }
        require(email.contains("@")) { "Email must be valid" }
        require(displayName.isNotBlank()) { "Display name cannot be blank" }
        require(id.isNotBlank()) { "User ID cannot be blank" }
    }

    fun isSynced(): Boolean = syncStatus == SyncStatus.SYNCED

    fun hasPendingChanges(): Boolean = syncStatus == SyncStatus.PENDING || syncStatus == SyncStatus.CONFLICT

    fun needsSync(): Boolean = lastSyncAt == null || updatedAt > (lastSyncAt ?: 0L)

    fun withUpdatedTimestamp(): User = copy(updatedAt = System.currentTimeMillis())

    fun markAsSynced(): User = copy(
        syncStatus = SyncStatus.SYNCED,
        lastSyncAt = System.currentTimeMillis()
    )

    fun markAsPending(): User = copy(syncStatus = SyncStatus.PENDING)

    fun markAsConflict(): User = copy(syncStatus = SyncStatus.CONFLICT)

    fun withEmailVerification(verified: Boolean): User = copy(
        isEmailVerified = verified,
        updatedAt = System.currentTimeMillis()
    )

    fun withPhoneVerification(verified: Boolean): User = copy(
        isPhoneVerified = verified,
        updatedAt = System.currentTimeMillis()
    )

    fun withAvatar(url: String?): User = copy(
        avatarUrl = url,
        updatedAt = System.currentTimeMillis()
    )

    fun withDisplayName(name: String): User = require(name.isNotBlank()) {
        "Display name cannot be blank"
    }.let { copy(displayName = name, updatedAt = System.currentTimeMillis()) }

    fun withEmail(email: String): User = require(email.contains("@")) {
        "Email must be valid"
    }.let { copy(email = email, updatedAt = System.currentTimeMillis()) }

    fun withPhone(phone: String?): User = copy(
        phoneNumber = phone,
        updatedAt = System.currentTimeMillis()
    )

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is User) return false
        return id == other.id
    }

    override fun hashCode(): Int = id.hashCode()

    override fun toString(): String = "User(id=$id, username=$username, email=$email, displayName=$displayName)"
}

enum class SyncStatus {
    PENDING,
    SYNCING,
    SYNCED,
    CONFLICT,
    ERROR;

    fun isPending(): Boolean = this == PENDING
    fun isSyncing(): Boolean = this == SYNCING
    fun isSynced(): Boolean = this == SYNCED
    fun hasConflict(): Boolean = this == CONFLICT
    fun hasError(): Boolean = this == ERROR

    fun canSync(): Boolean = this == PENDING || this == ERROR
}

data class UserProfile(
    val user: User,
    val preferences: Preferences? = null,
    val downloadedContents: List<DownloadedContent> = emptyList()
)

data class UserCredentials(
    val email: String,
    val password: String
) {
    init {
        require(email.isNotBlank()) { "Email cannot be blank" }
        require(password.length >= 8) { "Password must be at least 8 characters" }
    }

    fun isValid(): Boolean = email.contains("@") && password.length >= 8
}

sealed class UserValidationResult {
    data object Valid : UserValidationResult()
    data class Invalid(val errors: List<String>) : UserValidationResult()

    fun isValid(): Boolean = this is Valid
    fun getErrors(): List<String> = (this as? Invalid)?.errors ?: emptyList()
}

fun User.validate(): UserValidationResult {
    val errors = mutableListOf<String>()
    if (username.isBlank()) errors.add("Username cannot be blank")
    if (email.isBlank()) errors.add("Email cannot be blank")
    if (!email.contains("@")) errors.add("Email must be valid")
    if (displayName.isBlank()) errors.add("Display name cannot be blank")
    return if (errors.isEmpty()) UserValidationResult.Valid
    else UserValidationResult.Invalid(errors)
}

// === ARCHIVO: app/src/main/java/com/example/offlinefirst/domain/model/DownloadedContent.kt ===
package com.example.offlinefirst.domain.model

import java.util.UUID

data class DownloadedContent(
    val id: String = UUID.randomUUID().toString(),
    val title: String,
    val description: String? = null,
    val url: String,
    val localPath: String? = null,
    val mimeType: String = "application/octet-stream",
    val fileSize: Long = 0L,
    val downloadedSize: Long = 0L,
    val checksum: String? = null,
    val category: ContentCategory = ContentCategory.OTHER,
    val tags: List<String> = emptyList(),
    val thumbnailUrl: String? = null,
    val metadata: Map<String, String> = emptyMap(),
    val isAvailableOffline: Boolean = true,
    val expiresAt: Long? = null,
    val downloadedAt: Long = System.currentTimeMillis(),
    val accessedAt: Long = System.currentTimeMillis(),
    val accessCount: Int = 0,
    val lastSyncAt: Long? = null,
    val syncStatus: SyncStatus = SyncStatus.PENDING,
    val userId: String? = null
) {
    init {
        require(title.isNotBlank()) { "Content title cannot be blank" }
        require(url.isNotBlank()) { "Content URL cannot be blank" }
        require(id.isNotBlank()) { "Content ID cannot be blank" }
        require(downloadedSize <= fileSize) { "Downloaded size cannot exceed file size" }
    }

    fun isSynced(): Boolean = syncStatus == SyncStatus.SYNCED

    fun hasPendingChanges(): Boolean = syncStatus == SyncStatus.PENDING || syncStatus == SyncStatus.CONFLICT

    fun needsSync(): Boolean = lastSyncAt == null || updatedAt > (lastSyncAt ?: 0L)

    private val updatedAt: Long get() = maxOf(downloadedAt, accessedAt)

    fun isDownloaded(): Boolean = localPath != null && downloadedSize == fileSize

    fun isDownloading(): Boolean = downloadedSize > 0 && downloadedSize < fileSize

    fun isExpired(): Boolean = expiresAt != null && System.currentTimeMillis() > expiresAt

    fun isAvailable(): Boolean = isDownloaded() && !isExpired() && isAvailableOffline

    fun getDownloadProgress(): Float = if (fileSize > 0) downloadedSize.toFloat() / fileSize else 0f

    fun getProgressPercentage(): Int = (getDownloadProgress() * 100).toInt()

    fun withUpdatedTimestamp(): DownloadedContent = copy(accessedAt = System.currentTimeMillis())

    fun markAsSynced(): DownloadedContent = copy(
        syncStatus = SyncStatus.SYNCED,
        lastSyncAt = System.currentTimeMillis()
    )

    fun markAsPending(): DownloadedContent = copy(syncStatus = SyncStatus.PENDING)

    fun markAsConflict(): DownloadedContent = copy(syncStatus = SyncStatus.CONFLICT)

    fun markAsDownloading(progress: Long): DownloadedContent = copy(
        downloadedSize = progress,
        syncStatus = SyncStatus.SYNCING
    )

    fun markAsDownloaded(path: String, size: Long, hash: String?): DownloadedContent = copy(
        localPath = path,
        downloadedSize = size,
        fileSize = size,
        checksum = hash,
        syncStatus = SyncStatus.PENDING
    )

    fun incrementAccessCount(): DownloadedContent = copy(
        accessCount = accessCount + 1,
        accessedAt = System.currentTimeMillis()
    )

    fun withLocalPath(path: String): DownloadedContent = copy(localPath = path)

    fun withExpiration(expiresAt: Long?): DownloadedContent = copy(expiresAt = expiresAt)

    fun withCategory(category: ContentCategory): DownloadedContent = copy(category = category)

    fun withTags(newTags: List<String>): DownloadedContent = copy(tags = newTags)

    fun addTag(tag: String): DownloadedContent = copy(tags = tags + tag)

    fun removeTag(tag: String): DownloadedContent = copy(tags = tags - tag)

    fun withMetadata(additionalMetadata: Map<String, String>): DownloadedContent = copy(
        metadata = metadata + additionalMetadata
    )

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is DownloadedContent) return false
        return id == other.id
    }

    override fun hashCode(): Int = id.hashCode()

    override fun toString(): String = "DownloadedContent(id=$id, title=$title, status=$syncStatus)"
}

enum class ContentCategory {
    IMAGE,
    VIDEO,
    AUDIO,
    DOCUMENT,
    ARCHIVE,
    OTHER;

    fun isMedia(): Boolean = this in listOf(IMAGE, VIDEO, AUDIO)
    fun isDocument(): Boolean = this == DOCUMENT
    fun isArchive(): Boolean = this == ARCHIVE
}

data class ContentBatch(
    val contents: List<DownloadedContent>,
    val totalSize: Long = contents.sumOf { it.fileSize },
    val downloadedSize: Long = contents.sumOf { it.downloadedSize }
) {
    fun getTotalProgress(): Float = if (totalSize > 0) downloadedSize.toFloat() / totalSize else 0f

    fun getAllSynced(): Boolean = contents.all { it.isSynced() }

    fun getAllDownloaded(): Boolean = contents.all { it.isDownloaded() }

    fun getPendingCount(): Int = contents.count { it.hasPendingChanges() }

    fun getExpiredContents(): List<DownloadedContent> = contents.filter { it.isExpired() }

    fun getAvailableContents(): List<DownloadedContent> = contents.filter { it.isAvailable() }
}

sealed class ContentValidationResult {
    data object Valid : ContentValidationResult()
    data class Invalid(val errors: List<String>) : ContentValidationResult()

    fun isValid(): Boolean = this is Valid
    fun getErrors(): List<String> = (this as? Invalid)?.errors ?: emptyList()
}

fun DownloadedContent.validate(): ContentValidationResult {
    val errors = mutableListOf<String>()
    if (title.isBlank()) errors.add("Content title cannot be blank")
    if (url.isBlank()) errors.add("Content URL cannot be blank")
    if (fileSize < 0) errors.add("File size cannot be negative")
    if (downloadedSize < 0) errors.add("Downloaded size cannot be negative")
    if (downloadedSize > fileSize) errors.add("Downloaded size cannot exceed file size")
    if (expiresAt != null && expiresAt < System.currentTimeMillis()) {
        errors.add("Expiration date cannot be in the past")
    }
    return if (errors.isEmpty()) ContentValidationResult.Valid
    else ContentValidationResult.Invalid(errors)
}

// === ARCHIVO: app/src/main/java/com/example/offlinefirst/domain/repository/UserRepository.kt ===
package com.example.offlinefirst.domain.repository

import com.example.offlinefirst.data.repository.UserRepositoryImpl
import com.example.offlinefirst.domain.model.SyncStatus
import com.example.offlinefirst.domain.model.User
import com.example.offlinefirst.domain.model.UserValidationResult
import com.example.offlinefirst.util.Resource
import kotlinx.coroutines.flow.Flow

interface UserRepository {
    fun getAllUsers(): Flow<List<User>>
    
    fun getUserById(userId: String): Flow<Resource<User>>
    
    fun getUserByEmail(email: String): Flow<Resource<User>>
    
    fun getUserByUsername(username: String): Flow<Resource<User>>
    
    suspend fun saveUser(user: User): Resource<User>
    
    suspend fun saveUsers(users: List<User>): Resource<List<User>>
    
    suspend fun updateUser(user: User): Resource<User>
    
    suspend fun deleteUser(userId: String): Resource<Unit>
    
    suspend fun deleteAllUsers(): Resource<Unit>
    
    fun getPendingSyncUsers(): Flow<List<User>>
    
    fun getSyncedUsers(): Flow<List<User>>
    
    fun getUsersWithConflicts(): Flow<List<User>>
    
    suspend fun markUserAsSynced(userId: String): Resource<User>
    
    suspend fun markUserAsPending(userId: String): Resource<User>
    
    suspend fun markUserAsConflict(userId: String): Resource<User>
    
    suspend fun syncUser(userId: String): Resource<User>
    
    suspend fun syncAllPendingUsers(): Resource<List<User>>
    
    suspend fun resolveConflict(userId: String, resolution: ConflictResolution): Resource<User>
    
    fun searchUsers(query: String): Flow<List<User>>
    
    fun getUserCount(): Flow<Int>
    
    suspend fun validateUser(email: String?, username: String?, displayName: String?): UserValidationResult
    
    suspend fun getUserByCredentials(email: String, password: String): Resource<User>
    
    suspend fun updateUserProfile(userId: String, displayName: String?, phone: String?, avatarUrl: String?): Resource<User>
    
    suspend fun verifyUserEmail(userId: String, verified: Boolean): Resource<User>
    
    suspend fun verifyUserPhone(userId: String, verified: Boolean): Resource<User>
    
    suspend fun refreshUserFromRemote(userId: String): Resource<User>
    
    fun observeUserSyncStatus(userId: String): Flow<SyncStatus>
    
    suspend fun getLastSyncTime(userId: String): Long?
    
    suspend fun updateLastSyncTime(userId: String, timestamp: Long)
}

sealed class ConflictResolution {
    data class UseLocal(val user: User) : ConflictResolution()
    data class UseRemote(val remoteUser: User) : ConflictResolution()
    data class Merge(val localUser: User, val remoteUser: User) : ConflictResolution()
    data object Discard : ConflictResolution()
}

interface UserCacheManager {
    suspend fun cacheUser(user: User)
    
    suspend fun cacheUsers(users: List<User>)
    
    suspend fun getCachedUser(userId: String): User?
    
    suspend fun getCachedUsers(): List<User>
    
    suspend fun invalidateCache()
    
    suspend fun invalidateUser(userId: String)
    
    fun isCacheValid(): Boolean
    
    suspend fun getCacheSize(): Long
}

interface UserRemoteDataSource {
    suspend fun fetchUser(userId: String): Resource<User>
    
    suspend fun fetchAllUsers(): Resource<List<User>>
    
    suspend fun fetchUserByEmail(email: String): Resource<User>
    
    suspend fun createUser(user: User): Resource<User>
    
    suspend fun updateUser(user: User): Resource<User>
    
    suspend fun deleteUser(userId: String): Resource<Unit>
    
    suspend fun searchUsers(query: String): Resource<List<User>>
    
    suspend fun authenticateUser(email: String, password: String): Resource<User>
    
    suspend fun verifyEmail(userId: String): Resource<Boolean>
    
    suspend fun verifyPhone(userId: String): Resource<Boolean>
}

object UserRepositoryFactory {
    fun create(localDataSource: UserLocalDataSource, remoteDataSource: UserRemoteDataSource): UserRepository {
        return UserRepositoryImpl(
            localDataSource = localDataSource,
            remoteDataSource = remoteDataSource
        )
    }
}

interface UserLocalDataSource {
    fun getAllUsers(): Flow<List<User>>
    
    fun getUserById(userId: String): Flow<User?>
    
    fun getUserByEmail(email: String): Flow<User?>
    
    fun getUserByUsername(username: String): Flow<User?>
    
    suspend fun insertUser(user: User)
    
    suspend fun insertUsers(users: List<User>)
    
    suspend fun updateUser(user: User)
    
    suspend fun deleteUser(userId: String)
    
    suspend fun deleteAllUsers()
    
    fun getPendingSyncUsers(): Flow<List<User>>
    
    fun getSyncedUsers(): Flow<List<User>>
    
    fun getUsersWithConflicts(): Flow<List<User>>
    
    suspend fun updateSyncStatus(userId: String, status: SyncStatus)
    
    fun searchUsers(query: String): Flow<List<User>>
    
    fun getUserCount(): Flow<Int>
}


// === ARCHIVO: app/src/main/java/com/example/offlinefirst/domain/repository/PreferencesRepository.kt ===
package com.example.offlinefirst.domain.repository

import com.example.offlinefirst.data.repository.PreferencesRepositoryImpl
import com.example.offlinefirst.domain.model.PreferenceCategory
import com.example.offlinefirst.domain.model.PreferenceValidationResult
import com.example.offlinefirst.domain.model.Preferences
import com.example.offlinefirst.domain.model.PreferenceGroup
import com.example.offlinefirst.domain.model.SyncStatus
import com.example.offlinefirst.util.Resource
import kotlinx.coroutines.flow.Flow

interface PreferencesRepository {
    fun getAllPreferences(): Flow<List<Preferences>>
    
    fun getPreferenceById(preferenceId: String): Flow<Resource<Preferences>>
    
    fun getPreferenceByKey(key: String, userId: String? = null): Flow<Resource<Preferences>>
    
    fun getPreferencesByCategory(category: PreferenceCategory): Flow<List<Preferences>>
    
    fun getPreferencesByUser(userId: String): Flow<List<Preferences>>
    
    fun getGlobalPreferences(): Flow<List<Preferences>>
    
    suspend fun savePreference(preference: Preferences): Resource<Preferences>
    
    suspend fun savePreferences(preferences: List<Preferences>): Resource<List<Preferences>>
    
    suspend fun updatePreference(preference: Preferences): Resource<Preferences>
    
    suspend fun deletePreference(preferenceId: String): Resource<Unit>
    
    suspend fun deleteAllPreferences(): Resource<Unit>
    
    suspend fun deleteUserPreferences(userId: String): Resource<Unit>
    
    fun getPendingSyncPreferences(): Flow<List<Preferences>>
    
    fun getSyncedPreferences(): Flow<List<Preferences>>
    
    fun getPreferencesWithConflicts(): Flow<List<Preferences>>
    
    suspend fun markPreferenceAsSynced(preferenceId: String): Resource<Preferences>
    
    suspend fun markPreferenceAsPending(preferenceId: String): Resource<Preferences>
    
    suspend fun markPreferenceAsConflict(preferenceId: String): Resource<Preferences>
    
    suspend fun syncPreference(preferenceId: String): Resource<Preferences>
    
    suspend fun syncAllPendingPreferences(): Resource<List<Preferences>>
    
    suspend fun resolvePreferenceConflict(preferenceId: String, resolution: PreferenceConflictResolution): Resource<Preferences>
    
    suspend fun getPreferenceValue(key: String, userId: String? = null): Any?
    
    suspend fun setPreferenceValue(key: String, value: String, userId: String? = null, category: PreferenceCategory = PreferenceCategory.GENERAL): Resource<Preferences>
    
    fun getPreferenceGroups(): Flow<List<PreferenceGroup>>
    
    suspend fun validatePreference(key: String, value: String, isGlobal: Boolean = true, userId: String? = null): PreferenceValidationResult
    
    suspend fun importPreferences(preferences: List<Preferences>): Resource<List<Preferences>>
    
    suspend fun exportPreferences(userId: String? = null): Resource<List<Preferences>>
    
    fun observePreferenceSyncStatus(preferenceId: String): Flow<SyncStatus>
    
    suspend fun getLastSyncTime(preferenceId: String): Long?
    
    suspend fun updateLastSyncTime(preferenceId: String, timestamp: Long)
    
    suspend fun resetToDefaults(userId: String? = null): Resource<Unit>
}

sealed class PreferenceConflictResolution {
    data class UseLocal(val preference: Preferences) : PreferenceConflictResolution()
    data class UseRemote(val remotePreference: Preferences) : PreferenceConflictResolution()
    data class Merge(val localPreference: Preferences, val remotePreference: Preferences) : PreferenceConflictResolution()
    data object Discard : PreferenceConflictResolution()
}

interface PreferencesCacheManager {
    suspend fun cachePreference(preference: Preferences)
    
    suspend fun cachePreferences(preferences: List<Preferences>)
    
    suspend fun getCachedPreference(preferenceId: String): Preferences?
    
    suspend fun getCachedPreferenceByKey(key: String, userId: String? = null): Preferences?
    
    suspend fun getCachedPreferences(): List<Preferences>
    
    suspend fun invalidateCache()
    
    suspend fun invalidatePreference(preferenceId: String)
    
    suspend fun invalidateUserPreferences(userId: String)
    
    fun isCacheValid(): Boolean
    
    suspend fun getCacheSize(): Long
}

interface PreferencesRemoteDataSource {
    suspend fun fetchPreference(preferenceId: String): Resource<Preferences>
    
    suspend fun fetchAllPreferences(userId: String? = null): Resource<List<Preferences>>
    
    suspend fun fetchPreferencesByCategory(category: PreferenceCategory, userId: String? = null): Resource<List<Preferences>>
    
    suspend fun createPreference(preference: Preferences): Resource<Preferences>
    
    suspend fun updatePreference(preference: Preferences): Resource<Preferences>
    
    suspend fun deletePreference(preferenceId: String): Resource<Unit>
    
    suspend fun syncPreferences(preferences: List<Preferences>): Resource<List<Preferences>>
}

object PreferencesRepositoryFactory {
    fun create(localDataSource: PreferencesLocalDataSource, remoteDataSource: PreferencesRemoteDataSource): PreferencesRepository {
        return PreferencesRepositoryImpl(
            localDataSource = localDataSource,
            remoteDataSource = remoteDataSource
        )
    }
}

interface PreferencesLocalDataSource {
    fun getAllPreferences(): Flow<List<Preferences>>
    
    fun getPreferenceById(preferenceId: String): Flow<Preferences?>
    
    fun getPreferenceByKey(key: String, userId: String?): Flow<Preferences?>
    
    fun getPreferencesByCategory(category: PreferenceCategory): Flow<List<Preferences>>
    
    fun getPreferencesByUser(userId: String): Flow<List<Preferences>>
    
    fun getGlobalPreferences(): Flow<List<Preferences>>
    
    suspend fun insertPreference(preference: Preferences)
    
    suspend fun insertPreferences(preferences: List<Preferences>)
    
    suspend fun updatePreference(preference: Preferences)
    
    suspend fun deletePreference(preferenceId: String)
    
    suspend fun deleteAllPreferences()
    
    suspend fun deleteUserPreferences(userId: String)
    
    fun getPendingSyncPreferences(): Flow<List<Preferences>>
    
    fun getSyncedPreferences(): Flow<List<Preferences>>
    
    fun getPreferencesWithConflicts(): Flow<List<Preferences>>
    
    suspend fun updateSyncStatus(preferenceId: String, status: SyncStatus)
    
    fun getPreferenceGroups(): Flow<List<PreferenceGroup>>
}

// === ARCHIVO: app/src/main/java/com/example/offlinefirst/domain/repository/ContentRepository.kt ===
package com.example.offlinefirst.domain.repository

import com.example.offlinefirst.data.repository.ContentRepositoryImpl
import com.example.offlinefirst.domain.model.ContentCategory
import com.example.offlinefirst.domain.model.ContentValidationResult
import com.example.offlinefirst.domain.model.DownloadedContent
import com.example.offlinefirst.domain.model.ContentBatch
import com.example.offlinefirst.domain.model.SyncStatus
import com.example.offlinefirst.util.Resource
import kotlinx.coroutines.flow.Flow

interface ContentRepository {
    fun getAllContents(): Flow<List<DownloadedContent>>
    
    fun getContentById(contentId: String): Flow<Resource<DownloadedContent>>
    
    fun getContentByUrl(url: String): Flow<Resource<DownloadedContent>>
    
    fun getContentsByCategory(category: ContentCategory): Flow<List<DownloadedContent>>
    
    fun getContentsByTags(tags: List<String>): Flow<List<DownloadedContent>>
    
    fun getDownloadedContents(): Flow<List<DownloadedContent>>
    
    fun getAvailableOfflineContents(): Flow<List<DownloadedContent>>
    
    fun getExpiredContents(): Flow<List<DownloadedContent>>
    
    suspend fun saveContent(content: DownloadedContent): Resource<DownloadedContent>
    
    suspend fun saveContents(contents: List<DownloadedContent>): Resource<List<DownloadedContent>>
    
    suspend fun updateContent(content: DownloadedContent): Resource<DownloadedContent>
    
    suspend fun deleteContent(contentId: String): Resource<Unit>
    
    suspend fun deleteAllContents(): Resource<Unit>
    
    suspend fun deleteExpiredContents(): Resource<Int>
    
    fun getPendingSyncContents(): Flow<List<DownloadedContent>>
    
    fun getSyncedContents(): Flow<List<DownloadedContent>>
    
    fun getContentsWithConflicts(): Flow<List<DownloadedContent>>
    
    suspend fun markContentAsSynced(contentId: String): Resource<DownloadedContent>
    
    suspend fun markContentAsPending(contentId: String): Resource<DownloadedContent>
    
    suspend fun markContentAsConflict(contentId: String): Resource<DownloadedContent>
    
    suspend fun syncContent(contentId: String): Resource<DownloadedContent>
    
    suspend fun syncAllPendingContents(): Resource<List<DownloadedContent>>
    
    suspend fun resolveContentConflict(contentId: String, resolution: ContentConflictResolution): Resource<DownloadedContent>
    
    suspend fun downloadContent(contentId: String, url: String): Resource<DownloadedContent>
    
    suspend fun downloadContentsBatch(contents: List<DownloadedContent>): Resource<ContentBatch>
    
    suspend fun cancelDownload(contentId: String): Resource<Unit>
    
    suspend fun pauseDownload(contentId: String): Resource<Unit>
    
    suspend fun resumeDownload(contentId: String): Resource<DownloadedContent>
    
    suspend fun getDownloadProgress(contentId: String): Float
    
    fun observeDownloadProgress(contentId: String): Flow<Float>
    
    suspend fun getLocalFilePath(contentId: String): String?
    
    suspend fun validateContentFile(contentId: String): Boolean
    
    suspend fun cleanupOrphanedFiles(): Resource<Int>
    
    fun searchContents(query: String): Flow<List<DownloadedContent>>
    
    fun getContentCount(): Flow<Int>
    
    fun getDownloadedContentCount(): Flow<Int>
    
    fun getStorageUsed(): Flow<Long>
    
    suspend fun validateContent(url: String?, title: String?, fileSize: Long, downloadedSize: Long, expiresAt: Long?): ContentValidationResult
    
    suspend fun refreshContentFromRemote(contentId: String): Resource<DownloadedContent>
    
    fun observeContentSyncStatus(contentId: String): Flow<SyncStatus>
    
    suspend fun getLastSyncTime(contentId: String): Long?
    
    suspend fun updateLastSyncTime(contentId: String, timestamp: Long)
    
    suspend fun updateAccessCount(contentId: String): Resource<DownloadedContent>
    
    suspend fun getMostAccessedContents(limit: Int = 10): List<DownloadedContent>
    
    suspend fun getContentsByExpirationRange(startTime: Long, endTime: Long): List<DownloadedContent>
}

sealed class ContentConflictResolution {
    data class UseLocal(val content: DownloadedContent) : ContentConflictResolution()
    data class UseRemote(val remoteContent: DownloadedContent) : ContentConflictResolution()
    data class Merge(val localContent: DownloadedContent, val remoteContent: DownloadedContent) : ContentConflictResolution()
    data object Discard : ContentConflictResolution()
}

interface ContentCacheManager {
    suspend fun cacheContent(content: DownloadedContent)
    
    suspend fun cacheContents(contents: List<DownloadedContent>)
    
    suspend fun getCachedContent(contentId: String): DownloadedContent?
    
    suspend fun getCachedContents(): List<DownloadedContent>
    
    suspend fun invalidateCache()
    
    suspend fun invalidateContent(contentId: String)
    
    fun isCacheValid(): Boolean
    
    suspend fun getCacheSize(): Long
}

interface ContentRemoteDataSource {
    suspend fun fetchContent(contentId: String): Resource<DownloadedContent>
    
    suspend fun fetchAllContents(): Resource<List<DownloadedContent>>
    
    suspend fun fetchContentsByCategory(category: ContentCategory): Resource<List<DownloadedContent>>
    
    suspend fun fetchContentsByTags(tags: List<String>): Resource<List<DownloadedContent>>
    
    suspend fun createContent(content: DownloadedContent): Resource<DownloadedContent>
    
    suspend fun updateContent(content: DownloadedContent): Resource<DownloadedContent>
    
    suspend fun deleteContent(contentId: String): Resource<Unit>
    
    suspend fun downloadContent(url: String): Resource<Pair<String, Long>>
    
    suspend fun getContentDownloadUrl(contentId: String): Resource<String>
    
    suspend fun searchContents(query: String): Resource<List<DownloadedContent>>
}

interface ContentDownloadManager {
    suspend fun startDownload(content: DownloadedContent, url: String): Flow<DownloadProgress>
    
    suspend fun pauseDownload(contentId: String)
    
    suspend fun resumeDownload(contentId: String)
    
    suspend fun cancelDownload(contentId: String)
    
    fun getActiveDownloads(): Flow<List<DownloadedContent>>
    
    suspend fun getDownloadStatus(contentId: String): DownloadStatus
}

data class DownloadProgress(
    val contentId: String,
    val bytesDownloaded: Long,
    val totalBytes: Long,
    val progress: Float,
    val status: DownloadStatus
)

enum class DownloadStatus {
    PENDING,
    DOWNLOADING,
    PAUSED,
    COMPLETED,
    FAILED,
    CANCELLED;
    
    fun isActive(): Boolean = this == DOWNLOADING || this == PENDING
    fun isTerminal(): Boolean = this == COMPLETED || this == FAILED || this == CANCELLED
}

object ContentRepositoryFactory {
    fun create(
        localDataSource: ContentLocalDataSource,
        remoteDataSource: ContentRemoteDataSource,
        downloadManager: ContentDownloadManager
    ): ContentRepository {
        return ContentRepositoryImpl(
            localDataSource = localDataSource,
            remoteDataSource = remoteDataSource,
            downloadManager = downloadManager
        )
    }
}

interface ContentLocalDataSource {
    fun getAllContents(): Flow<List<DownloadedContent>>
    
    fun getContentById(contentId: String): Flow<DownloadedContent?>
    
    fun getContentByUrl(url: String): Flow<DownloadedContent?>
    
    fun getContentsByCategory(category: ContentCategory): Flow<List<DownloadedContent>>
    
    fun getContentsByTags(tags: List<String>): Flow<List<DownloadedContent>>
    
    fun getDownloadedContents(): Flow<List<DownloadedContent>>
    
    fun getAvailableOfflineContents(): Flow<List<DownloadedContent>>
    
    fun getExpiredContents(): Flow<List<DownloadedContent>>
    
    suspend fun insertContent(content: DownloadedContent)
    
    suspend fun insertContents(contents: List<DownloadedContent>)
    
    suspend fun updateContent(content: DownloadedContent)
    
    suspend fun deleteContent(contentId: String)
    
    suspend fun deleteAllContents()
    
    suspend fun deleteExpiredContents()
    
    fun getPendingSyncContents(): Flow<List<DownloadedContent>>
    
    fun getSyncedContents(): Flow<List<DownloadedContent>>
    
    fun getContentsWithConflicts(): Flow<List<DownloadedContent>>
    
    suspend fun updateSyncStatus(contentId: String, status: SyncStatus)
    
    fun searchContents(query: String): Flow<List<DownloadedContent>>
    
    fun getContentCount(): Flow<Int>
    
    fun getDownloadedContentCount(): Flow<Int>
    
    fun getStorageUsed(): Flow<Long>
}

// === ARCHIVO: app/src/main/java/com/example/offlinefirst/domain/usecase/GetUserUseCase.kt ===
package com.example.offlinefirst.domain.usecase

import com.example.offlinefirst.domain.model.SyncStatus
import com.example.offlinefirst.domain.model.User
import com.example.offlinefirst.domain.repository.UserRepository
import com.example.offlinefirst.util.Resource
import com.example.offlinefirst.util.Resource.Loading
import com.example.offlinefirst.util.Resource.Success
import com.example.offlinefirst.util.Resource.Error
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetUserUseCase @Inject constructor(
    private val userRepository: UserRepository
) {
    operator fun invoke(userId: String): Flow<Resource<User>> = flow {
        emit(Loading())
        
        try {
            val cachedUser = userRepository.getUserById(userId).first()
            
            if (cachedUser != null) {
                emit(Success(cachedUser))
                
                if (cachedUser.needsSync()) {
                    try {
                        val remoteUser = userRepository.refreshUserFromRemote(userId)
                        remoteUser?.let { remote ->
                            val mergedUser = mergeUserData(cachedUser, remote)
                            userRepository.saveUser(mergedUser)
                            emit(Success(mergedUser))
                        }
                    } catch (e: Exception) {
                        emit(Success(cachedUser))
                    }
                }
            } else {
                emit(Loading())
                
                try {
                    val remoteUser = userRepository.refreshUserFromRemote(userId)
                    if (remoteUser != null) {
                        userRepository.saveUser(remoteUser)
                        emit(Success(remoteUser))
                    } else {
                        emit(Error("Usuario no encontrado"))
                    }
                } catch (e: Exception) {
                    emit(Error("Error al obtener usuario: ${e.message}"))
                }
            }
        } catch (e: Exception) {
            emit(Error("Error al leer usuario: ${e.message}"))
        }
    }
    
    private fun mergeUserData(cached: User, remote: User): User {
        return when {
            cached.syncStatus == SyncStatus.SYNCED -> remote
            remote.lastSyncAt != null && cached.lastSyncAt != null && 
                remote.lastSyncAt > cached.lastSyncAt -> remote
            cached.hasPendingChanges() -> cached.withUpdatedTimestamp()
            else -> remote
        }
    }
    
    fun getCachedUser(userId: String): Flow<User?> {
        return userRepository.getUserById(userId)
    }
    
    suspend fun getUserSync(userId: String): Resource<User> {
        return try {
            val user = userRepository.getUserById(userId).first()
            if (user != null) {
                Success(user)
            } else {
                Error("Usuario no encontrado en caché")
            }
        } catch (e: Exception) {
            Error("Error al obtener usuario: ${e.message}")
        }
    }
    
    fun observeUser(userId: String): Flow<Resource<User>> {
        return userRepository.getUserById(userId).let { userFlow ->
            flow {
                userFlow.collect { user ->
                    if (user != null) {
                        emit(Success(user))
                    } else {
                        emit(Loading())
                    }
                }
            }
        }
    }
}


// === ARCHIVO: app/src/main/java/com/example/offlinefirst/data/local/entity/DownloadedContentEntity.kt ===
package com.example.offlinefirst.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.offlinefirst.domain.model.ContentCategory
import com.example.offlinefirst.domain.model.DownloadedContent
import com.example.offlinefirst.domain.model.SyncStatus

@Entity(tableName = "downloaded_content")
data class DownloadedContentEntity(
    @PrimaryKey
    @ColumnInfo(name = "id")
    val id: String,

    @ColumnInfo(name = "title")
    val title: String,

    @ColumnInfo(name = "description")
    val description: String?,

    @ColumnInfo(name = "url")
    val url: String,

    @ColumnInfo(name = "local_path")
    val localPath: String?,

    @ColumnInfo(name = "file_size")
    val fileSize: Long,

    @ColumnInfo(name = "downloaded_size")
    val downloadedSize: Long,

    @ColumnInfo(name = "content_hash")
    val contentHash: String?,

    @ColumnInfo(name = "sync_status")
    val syncStatus: String,

    @ColumnInfo(name = "category")
    val category: String,

    @ColumnInfo(name = "tags")
    val tags: String,

    @ColumnInfo(name = "metadata")
    val metadata: String?,

    @ColumnInfo(name = "expires_at")
    val expiresAt: Long?,

    @ColumnInfo(name = "created_at")
    val createdAt: Long,

    @ColumnInfo(name = "updated_at")
    val updatedAt: Long,

    @ColumnInfo(name = "accessed_at")
    val accessedAt: Long,

    @ColumnInfo(name = "last_sync_at")
    val lastSyncAt: Long?,

    @ColumnInfo(name = "is_available_offline")
    val isAvailableOffline: Boolean
) {
    fun toDomainModel(): DownloadedContent {
        return DownloadedContent(
            id = id,
            title = title,
            description = description,
            url = url,
            localPath = localPath,
            fileSize = fileSize,
            downloadedSize = downloadedSize,
            contentHash = contentHash,
            syncStatus = SyncStatus.valueOf(syncStatus),
            category = ContentCategory.valueOf(category),
            tags = tags.split(",").filter { it.isNotBlank() },
            metadata = metadata?.let {
                try {
                    DownloadedContent.parseMetadata(it)
                } catch (e: Exception) {
                    emptyMap()
                }
            } ?: emptyMap(),
            expiresAt = expiresAt,
            createdAt = createdAt,
            updatedAt = updatedAt,
            accessedAt = accessedAt,
            lastSyncAt = lastSyncAt,
            isAvailableOffline = isAvailableOffline
        )
    }

    companion object {
        fun fromDomainModel(domain: DownloadedContent): DownloadedContentEntity {
            return DownloadedContentEntity(
                id = domain.id,
                title = domain.title,
                description = domain.description,
                url = domain.url,
                localPath = domain.localPath,
                fileSize = domain.fileSize,
                downloadedSize = domain.downloadedSize,
                contentHash = domain.contentHash,
                syncStatus = domain.syncStatus.name,
                category = domain.category.name,
                tags = domain.tags.joinToString(","),
                metadata = domain.metadata.takeIf { it.isNotEmpty() }?.let {
                    DownloadedContent.serializeMetadata(it)
                },
                expiresAt = domain.expiresAt,
                createdAt = domain.createdAt,
                updatedAt = domain.updatedAt,
                accessedAt = domain.accessedAt,
                lastSyncAt = domain.lastSyncAt,
                isAvailableOffline = domain.isAvailableOffline
            )
        }
    }
}

// === ARCHIVO: app/src/main/java/com/example/offlinefirst/data/local/entity/SyncOperationEntity.kt ===
package com.example.offlinefirst.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import com.example.offlinefirst.domain.model.EntityType
import com.example.offlinefirst.domain.model.OperationType
import com.example.offlinefirst.domain.model.SyncOperation

@Entity(
    tableName = "sync_operation",
    indices = [
        Index(value = ["entity_type", "entity_id"]),
        Index(value = ["status"]),
        Index(value = ["created_at"])
    ]
)
data class SyncOperationEntity(
    @PrimaryKey
    @ColumnInfo(name = "id")
    val id: String,

    @ColumnInfo(name = "entity_type")
    val entityType: String,

    @ColumnInfo(name = "entity_id")
    val entityId: String,

    @ColumnInfo(name = "operation_type")
    val operationType: String,

    @ColumnInfo(name = "payload")
    val payload: String,

    @ColumnInfo(name = "status")
    val status: String,

    @ColumnInfo(name = "retry_count")
    val retryCount: Int,

    @ColumnInfo(name = "max_retries")
    val maxRetries: Int,

    @ColumnInfo(name = "created_at")
    val createdAt: Long,

    @ColumnInfo(name = "last_attempt_at")
    val lastAttemptAt: Long?,

    @ColumnInfo(name = "error_message")
    val errorMessage: String?,

    @ColumnInfo(name = "priority")
    val priority: Int,

    @ColumnInfo(name = "depends_on")
    val dependsOn: String?
) {
    fun toDomainModel(): SyncOperation {
        return SyncOperation(
            id = id,
            entityType = EntityType.valueOf(entityType),
            entityId = entityId,
            operationType = OperationType.valueOf(operationType),
            payload = try {
                SyncOperation.parsePayload(payload)
            } catch (e: Exception) {
                emptyMap()
            },
            status = com.example.offlinefirst.domain.model.SyncOperationStatus.valueOf(status),
            retryCount = retryCount,
            maxRetries = maxRetries,
            createdAt = createdAt,
            lastAttemptAt = lastAttemptAt,
            errorMessage = errorMessage,
            priority = priority,
            dependsOn = dependsOn
        )
    }

    companion object {
        fun fromDomainModel(domain: SyncOperation): SyncOperationEntity {
            return SyncOperationEntity(
                id = domain.id,
                entityType = domain.entityType.name,
                entityId = domain.entityId,
                operationType = domain.operationType.name,
                payload = SyncOperation.serializePayload(domain.payload),
                status = domain.status.name,
                retryCount = domain.retryCount,
                maxRetries = domain.maxRetries,
                createdAt = domain.createdAt,
                lastAttemptAt = domain.lastAttemptAt,
                errorMessage = domain.errorMessage,
                priority = domain.priority,
                dependsOn = domain.dependsOn
            )
        }
    }
}


=== ARCHIVO: app/src/main/java/com/example/offlinefirst/data/local/database/OfflineFirstDatabase.kt ===
package com.example.offlinefirst.data.local.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.offlinefirst.data.local.dao.ContentDao
import com.example.offlinefirst.data.local.dao.PreferencesDao
import com.example.offlinefirst.data.local.dao.SyncOperationDao
import com.example.offlinefirst.data.local.dao.UserDao
import com.example.offlinefirst.data.local.entity.DownloadedContentEntity
import com.example.offlinefirst.data.local.entity.PreferencesEntity
import com.example.offlinefirst.data.local.entity.SyncOperationEntity
import com.example.offlinefirst.data.local.entity.UserEntity
import com.example.offlinefirst.util.Error
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.concurrent.Executors

@Database(
    entities = [
        UserEntity::class,
        PreferencesEntity::class,
        DownloadedContentEntity::class,
        SyncOperationEntity::class
    ],
    version = 1,
    exportSchema = true
)
@TypeConverters(Converters::class)
abstract class OfflineFirstDatabase : RoomDatabase() {

    abstract fun userDao(): UserDao
    abstract fun preferencesDao(): PreferencesDao
    abstract fun contentDao(): ContentDao
    abstract fun syncOperationDao(): SyncOperationDao

    companion object {
        private const val DATABASE_NAME = "offline_first_database"
        private const val SCHEMA_FILE_NAME = "offline_first_schema"

        @Volatile
        private var INSTANCE: OfflineFirstDatabase? = null

        fun getInstance(context: Context): OfflineFirstDatabase {
            return INSTANCE ?: synchronized(this) {
                INSTANCE ?: buildDatabase(context).also { INSTANCE = it }
            }
        }

        private fun buildDatabase(context: Context): OfflineFirstDatabase {
            return Room.databaseBuilder(
                context.applicationContext,
                OfflineFirstDatabase::class.java,
                DATABASE_NAME
            )
                .addCallback(DatabaseCallback())
                .addMigrations()
                .setJournalMode(JournalMode.TRUNCATE)
                .setQueryCallback({ sql, bindArgs ->
                    android.util.Log.d("DatabaseQuery", "SQL: $sql, Args: $bindArgs")
                }, Executors.newSingleThreadExecutor())
                .fallbackToDestructiveMigration()
                .build()
        }

        fun destroyInstance() {
            INSTANCE?.close()
            INSTANCE = null
        }
    }

    private class DatabaseCallback : Callback() {
        override fun onCreate(db: SupportSQLiteDatabase) {
            super.onCreate(db)
            android.util.Log.i("Database", "Creating database schema")
            CoroutineScope(Dispatchers.IO).launch {
                INSTANCE?.let { database ->
                    initializeDefaultData(database)
                }
            }
        }

        override fun onOpen(db: SupportSQLiteDatabase) {
            super.onOpen(db)
            android.util.Log.i("Database", "Database opened")
            db.execSQL("PRAGMA foreign_keys = ON")
            db.execSQL("PRAGMA journal_mode = TRUNCATE")
            db.execSQL("PRAGMA synchronous = NORMAL")
            db.execSQL("PRAGMA cache_size = 10000")
            db.execSQL("PRAGMA temp_store = MEMORY")
        }

        override fun onDestructiveMigration(db: SupportSQLiteDatabase) {
            super.onDestructiveMigration(db)
            android.util.Log.w("Database", "Destructive migration performed - all data lost")
        }

        private suspend fun initializeDefaultData(database: OfflineFirstDatabase) {
            try {
                val currentTime = System.currentTimeMillis()
                android.util.Log.d("Database", "Initializing default data")
            } catch (e: Error) {
                android.util.Log.e("Database", "Error initializing default data", e)
            } catch (e: Exception) {
                android.util.Log.e("Database", "Error initializing default data", e)
            }
        }
    }
}

// === ARCHIVO: app/src/main/java/com/example/offlinefirst/data/repository/UserRepositoryImpl.kt ===
package com.example.offlinefirst.data.repository

import com.example.offlinefirst.data.local.dao.UserDao
import com.example.offlinefirst.data.local.entity.UserEntity
import com.example.offlinefirst.domain.model.SyncStatus
import com.example.offlinefirst.domain.model.User
import com.example.offlinefirst.domain.model.UserValidationResult
import com.example.offlinefirst.domain.model.Valid
import com.example.offlinefirst.domain.model.Invalid
import com.example.offlinefirst.domain.repository.UserRepository
import com.example.offlinefirst.util.Error
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserRepositoryImpl @Inject constructor(
    private val userDao: UserDao
) : UserRepository {

    override suspend fun getUser(userId: String): User? {
        return try {
            val entity = userDao.getUserById(userId)
            entity?.toDomain()
        } catch (e: Exception) {
            android.util.Log.e("UserRepository", "Error getting user: ${e.message}", e)
            null
        }
    }

    override fun getUserFlow(userId: String): Flow<User?> {
        return userDao.observeUserById(userId).map { it?.toDomain() }
    }

    override fun getCurrentUser(): Flow<User?> {
        return userDao.observeAllUsers().map { entities ->
            entities.firstOrNull()?.toDomain()
        }
    }

    override suspend fun saveUser(user: User): Result<User> {
        return try {
            val validation = user.validate()
            if (!validation.isValid()) {
                return Result.failure(IllegalArgumentException(validation.getErrors().joinToString(", ")))
            }
            val entity = user.toEntity()
            userDao.insertUser(entity)
            Result.success(user)
        } catch (e: Exception) {
            android.util.Log.e("UserRepository", "Error saving user: ${e.message}", e)
            Result.failure(e)
        }
    }

    override suspend fun updateUser(user: User): Result<User> {
        return try {
            val existingEntity = userDao.getUserById(user.id)
            if (existingEntity == null) {
                return Result.failure(IllegalArgumentException("User not found: ${user.id}"))
            }
            val updatedUser = user.withUpdatedTimestamp()
            val validation = updatedUser.validate()
            if (!validation.isValid()) {
                return Result.failure(IllegalArgumentException(validation.getErrors().joinToString(", ")))
            }
            val entity = updatedUser.toEntity()
            userDao.updateUser(entity)
            Result.success(updatedUser)
        } catch (e: Exception) {
            android.util.Log.e("UserRepository", "Error updating user: ${e.message}", e)
            Result.failure(e)
        }
    }

    override suspend fun deleteUser(userId: String): Result<Unit> {
        return try {
            userDao.deleteUserById(userId)
            Result.success(Unit)
        } catch (e: Exception) {
            android.util.Log.e("UserRepository", "Error deleting user: ${e.message}", e)
            Result.failure(e)
        }
    }

    override suspend fun getAllUsers(): List<User> {
        return try {
            userDao.observeAllUsers().first().map { it.toDomain() }
        } catch (e: Exception) {
            android.util.Log.e("UserRepository", "Error getting all users: ${e.message}", e)
            emptyList()
        }
    }

    override fun getAllUsersFlow(): Flow<List<User>> {
        return userDao.observeAllUsers().map { entities ->
            entities.map { it.toDomain() }
        }
    }

    override suspend fun getUsersBySyncStatus(syncStatus: SyncStatus): List<User> {
        return try {
            userDao.getUsersBySyncStatus(syncStatus.name).first().map { it.toDomain() }
        } catch (e: Exception) {
            android.util.Log.e("UserRepository", "Error getting users by sync status: ${e.message}", e)
            emptyList()
        }
    }

    override fun getUsersBySyncStatusFlow(syncStatus: SyncStatus): Flow<List<User>> {
        return userDao.getUsersBySyncStatus(syncStatus.name).map { entities ->
            entities.map { it.toDomain() }
        }
    }

    override suspend fun getPendingSyncUsers(): List<User> {
        return try {
            userDao.getUsersWithPendingSync().first().map { it.toDomain() }
        } catch (e: Exception) {
            android.util.Log.e("UserRepository", "Error getting pending sync users: ${e.message}", e)
            emptyList()
        }
    }

    override suspend fun markAsSynced(userId: String): Result<Unit> {
        return try {
            val currentTime = System.currentTimeMillis()
            userDao.updateSyncStatus(userId, SyncStatus.SYNCED.name, currentTime)
            Result.success(Unit)
        } catch (e: Exception) {
            android.util.Log.e("UserRepository", "Error marking user as synced: ${e.message}", e)
            Result.failure(e)
        }
    }

    override suspend fun markAsPending(userId: String): Result<Unit> {
        return try {
            val currentTime = System.currentTimeMillis()
            userDao.updateSyncStatus(userId, SyncStatus.PENDING.name, currentTime)
            Result.success(Unit)
        } catch (e: Exception) {
            android.util.Log.e("UserRepository", "Error marking user as pending: ${e.message}", e)
            Result.failure(e)
        }
    }

    override suspend fun markAsConflict(userId: String): Result<Unit> {
        return try {
            val currentTime = System.currentTimeMillis()
            userDao.updateSyncStatus(userId, SyncStatus.CONFLICT.name, currentTime)
            Result.success(Unit)
        } catch (e: Exception) {
            android.util.Log.e("UserRepository", "Error marking user as conflict: ${e.message}", e)
            Result.failure(e)
        }
    }

    override suspend fun getUserCount(): Int {
        return try {
            userDao.getUserCount()
        } catch (e: Exception) {
            android.util.Log.e("UserRepository", "Error getting user count: ${e.message}", e)
            0
        }
    }

    override suspend fun searchUsers(query: String): List<User> {
        return try {
            userDao.searchUsers(query, "username", 50, 0).first().map { it.toDomain() }
        } catch (e: Exception) {
            android.util.Log.e("UserRepository", "Error searching users: ${e.message}", e)
            emptyList()
        }
    }

    override fun searchUsersFlow(query: String): Flow<List<User>> {
        return userDao.searchUsers(query, "username", 50, 0).map { entities ->
            entities.map { it.toDomain() }
        }
    }

    override suspend fun getUserByEmail(email: String): User? {
        return try {
            userDao.getUserByEmail(email)?.toDomain()
        } catch (e: Exception) {
            android.util.Log.e("UserRepository", "Error getting user by email: ${e.message}", e)
            null
        }
    }

    override suspend fun getUserByUsername(username: String): User? {
        return try {
            userDao.getUserByUsername(username)?.toDomain()
        } catch (e: Exception) {
            android.util.Log.e("UserRepository", "Error getting user by username: ${e.message}", e)
            null
        }
    }

    private fun UserEntity.toDomain(): User {
        return User(
            id = id,
            username = username,
            email = email,
            displayName = displayName,
            phone = phone,
            avatarUrl = avatarUrl,
            isEmailVerified = isEmailVerified,
            isPhoneVerified = isPhoneVerified,
            syncStatus = SyncStatus.valueOf(syncStatus),
            createdAt = createdAt,
            updatedAt = updatedAt,
            lastSyncAt = lastSyncAt
        )
    }

    private fun User.toEntity(): UserEntity {
        return UserEntity(
            id = id,
            username = username,
            email = email,
            displayName = displayName,
            phone = phone,
            avatarUrl = avatarUrl,
            isEmailVerified = isEmailVerified,
            isPhoneVerified = isPhoneVerified,
            syncStatus = syncStatus.name,
            createdAt = createdAt,
            updatedAt = updatedAt,
            lastSyncAt = lastSyncAt
        )
    }

    private fun User.validate(): UserValidationResult {
        val errors = mutableListOf<String>()
        if (username.isBlank()) errors.add("Username cannot be blank")
        if (email.isBlank()) errors.add("Email cannot be blank")
        if (!email.contains("@")) errors.add("Email must be valid")
        if (displayName.isBlank()) errors.add("Display name cannot be blank")
        return if (errors.isEmpty()) {
            Valid
        } else {
            Invalid(errors)
        }
    }
}

// === ARCHIVO: app/src/main/java/com/example/offlinefirst/data/repository/PreferencesRepositoryImpl.kt ===
package com.example.offlinefirst.data.repository

import com.example.offlinefirst.data.local.dao.PreferencesDao
import com.example.offlinefirst.data.local.entity.PreferencesEntity
import com.example.offlinefirst.domain.model.PreferenceCategory
import com.example.offlinefirst.domain.model.PreferenceDataType
import com.example.offlinefirst.domain.model.Preferences
import com.example.offlinefirst.domain.model.SyncStatus
import com.example.offlinefirst.domain.model.Success
import com.example.offlinefirst.domain.repository.PreferencesRepository
import com.example.offlinefirst.util.Error
import com.example.offlinefirst.util.Loading
import com.example.offlinefirst.data.sync.Failed
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PreferencesRepositoryImpl @Inject constructor(
    private val preferencesDao: PreferencesDao
) : PreferencesRepository {

    override fun getPreferenceById(id: String): Flow<Resource<Preferences>> = flow {
        emit(Loading())
        try {
            val entity = preferencesDao.getPreferenceById(id)
            if (entity != null) {
                emit(Success(entity.toDomain()))
            } else {
                emit(Error("Preference not found with id: $id"))
            }
        } catch (e: Exception) {
            emit(Error(e.message ?: "Unknown error occurred"))
        }
    }

    override fun getPreferenceByKey(key: String, userId: String?): Flow<Resource<Preferences>> = flow {
        emit(Loading())
        try {
            val entity = preferencesDao.getPreferenceByKeyAndUser(key, userId)
            if (entity != null) {
                emit(Success(entity.toDomain()))
            } else {
                emit(Error("Preference not found with key: $key"))
            }
        } catch (e: Exception) {
            emit(Error(e.message ?: "Unknown error occurred"))
        }
    }

    override fun getAllPreferences(userId: String?): Flow<Resource<List<Preferences>>> = flow {
        emit(Loading())
        try {
            val entities = preferencesDao.getPreferencesForUser(userId)
            emit(Success(entities.map { it.toDomain() }))
        } catch (e: Exception) {
            emit(Error(e.message ?: "Unknown error occurred"))
        }
    }

    override fun getPreferencesByCategory(
        category: PreferenceCategory,
        userId: String?
    ): Flow<Resource<List<Preferences>>> = flow {
        emit(Loading())
        try {
            val entities = preferencesDao.getPreferencesByCategory(category.name)
            emit(Success(entities.map { it.toDomain() }))
        } catch (e: Exception) {
            emit(Error(e.message ?: "Unknown error occurred"))
        }
    }

    override fun getPendingPreferences(): Flow<Resource<List<Preferences>>> = flow {
        emit(Loading())
        try {
            val entities = preferencesDao.getPreferencesWithPendingSync()
            emit(Success(entities.map { it.toDomain() }))
        } catch (e: Exception) {
            emit(Error(e.message ?: "Unknown error occurred"))
        }
    }

    override fun savePreference(preferences: Preferences): Flow<Resource<Preferences>> = flow {
        emit(Loading())
        try {
            val validation = preferences.validate()
            if (!validation.isValid()) {
                emit(Error(validation.getErrors().joinToString(", ")))
                return@flow
            }
            
            val entity = preferences.toEntity()
            preferencesDao.upsertPreference(entity)
            
            val savedEntity = preferencesDao.getPreferenceById(preferences.id)
            if (savedEntity != null) {
                emit(Success(savedEntity.toDomain()))
            } else {
                emit(Error("Failed to save preference"))
            }
        } catch (e: Exception) {
            emit(Error(e.message ?: "Unknown error occurred"))
        }
    }

    override fun savePreferences(preferencesList: List<Preferences>): Flow<Resource<List<Preferences>>> = flow {
        emit(Loading())
        try {
            val entities = preferencesList.map { it.toEntity() }
            preferencesDao.insertPreferences(entities)
            
            val ids = preferencesList.map { it.id }
            val savedEntities = ids.mapNotNull { preferencesDao.getPreferenceById(it) }
            emit(Success(savedEntities.map { it.toDomain() }))
        } catch (e: Exception) {
            emit(Error(e.message ?: "Unknown error occurred"))
        }
    }

    override fun deletePreference(id: String): Flow<Resource<Boolean>> = flow {
        emit(Loading())
        try {
            preferencesDao.deletePreferenceById(id)
            emit(Success(true))
        } catch (e: Exception) {
            emit(Error(e.message ?: "Unknown error occurred"))
        }
    }

    override fun deleteAllPreferences(userId: String?): Flow<Resource<Boolean>> = flow {
        emit(Loading())
        try {
            if (userId != null) {
                preferencesDao.deletePreferencesForUser(userId)
            } else {
                preferencesDao.deleteAllPreferences()
            }
            emit(Success(true))
        } catch (e: Exception) {
            emit(Error(e.message ?: "Unknown error occurred"))
        }
    }

    override fun markAsSynced(id: String, syncTimestamp: Long): Flow<Resource<Boolean>> = flow {
        emit(Loading())
        try {
            preferencesDao.updateSyncStatus(id, SyncStatus.SYNCED.name, syncTimestamp)
            emit(Success(true))
        } catch (e: Exception) {
            emit(Error(e.message ?: "Unknown error occurred"))
        }
    }

    override fun markAsPending(id: String): Flow<Resource<Boolean>> = flow {
        emit(Loading())
        try {
            val timestamp = System.currentTimeMillis()
            preferencesDao.updateSyncStatus(id, SyncStatus.PENDING.name, timestamp)
            emit(Success(true))
        } catch (e: Exception) {
            emit(Error(e.message ?: "Unknown error occurred"))
        }
    }

    override fun markAsConflict(id: String): Flow<Resource<Boolean>> = flow {
        emit(Loading())
        try {
            preferencesDao.updateSyncStatus(id, SyncStatus.CONFLICT.name, 0L)
            emit(Success(true))
        } catch (e: Exception) {
            emit(Error(e.message ?: "Unknown error occurred"))
        }
    }

    override fun getPreferencesCount(userId: String?): Flow<Resource<Int>> = flow {
        emit(Loading())
        try {
            val count = preferencesDao.getPreferenceCount()
            emit(Success(count))
        } catch (e: Exception) {
            emit(Error(e.message ?: "Unknown error occurred"))
        }
    }

    override fun getPendingCount(): Flow<Resource<Int>> = flow {
        emit(Loading())
        try {
            val count = preferencesDao.getPreferenceCountBySyncStatus("PENDING")
            emit(Success(count))
        } catch (e: Exception) {
            emit(Error(e.message ?: "Unknown error occurred"))
        }
    }

    override fun searchPreferences(query: String, userId: String?): Flow<Resource<List<Preferences>>> = flow {
        emit(Loading())
        try {
            val entities = preferencesDao.searchPreferences(query, null, userId, "key", 50, 0)
            emit(Success(entities.map { it.toDomain() }))
        } catch (e: Exception) {
            emit(Error(e.message ?: "Unknown error occurred"))
        }
    }

    private fun PreferencesEntity.toDomain(): Preferences {
        return Preferences(
            id = id,
            key = key,
            value = value,
            dataType = PreferenceDataType.valueOf(dataType),
            category = PreferenceCategory.valueOf(category),
            userId = userId,
            isGlobal = isGlobal,
            isEncrypted = isEncrypted,
            syncStatus = SyncStatus.valueOf(syncStatus),
            createdAt = createdAt,
            updatedAt = updatedAt,
            lastSyncAt = lastSyncAt
        )
    }

    private fun Preferences.toEntity(): PreferencesEntity {
        return PreferencesEntity(
            id = id,
            key = key,
            value = value,
            dataType = dataType.name,
            category = category.name,
            userId = userId,
            isGlobal = isGlobal,
            isEncrypted = isEncrypted,
            syncStatus = syncStatus.name,
            createdAt = createdAt,
            updatedAt = updatedAt,
            lastSyncAt = lastSyncAt
        )
    }

    private fun Preferences.validate(): PreferenceValidationResult {
        val errors = mutableListOf<String>()
        if (key.isBlank()) errors.add("Preference key cannot be blank")
        if (value.isBlank()) errors.add("Preference value cannot be blank")
        if (!isGlobal && userId.isNullOrBlank()) errors.add("User ID required for non-global preferences")
        return if (errors.isEmpty()) {
            PreferenceValidationResult.Valid
        } else {
            PreferenceValidationResult.Invalid(errors)
        }
    }
}

 // === ARCHIVO: app/src/main/java/com/example/offlinefirst/data/repository/ContentRepositoryImpl.kt ===
package com.example.offlinefirst.data.repository

import com.example.offlinefirst.data.local.dao.ContentDao
import com.example.offlinefirst.data.local.entity.DownloadedContentEntity
import com.example.offlinefirst.domain.model.ContentCategory
import com.example.offlinefirst.domain.model.DownloadedContent
import com.example.offlinefirst.domain.model.SyncStatus
import com.example.offlinefirst.domain.repository.ContentRepository
import com.example.offlinefirst.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import java.io.File
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ContentRepositoryImpl @Inject constructor(
    private val contentDao: ContentDao
) : ContentRepository {

    override fun getContentById(id: String): Flow<Resource<DownloadedContent>> = flow {
        emit(Resource.Loading())
        try {
            val entity = contentDao.getById(id)
            if (entity != null) {
                emit(Resource.Success(entity.toDomain()))
            } else {
                emit(Resource.Error("Content not found with id: $id"))
            }
        } catch (e: Exception) {
            emit(Resource.Error(e.message ?: "Unknown error occurred"))
        }
    }

    override fun getContentByUrl(url: String): Flow<Resource<DownloadedContent>> = flow {
        emit(Resource.Loading())
        try {
            val entity = contentDao.getByUrl(url)
            if (entity != null) {
                emit(Resource.Success(entity.toDomain()))
            } else {
                emit(Resource.Error("Content not found with url: $url"))
            }
        } catch (e: Exception) {
            emit(Resource.Error(e.message ?: "Unknown error occurred"))
        }
    }

    override fun getAllContent(): Flow<Resource<List<DownloadedContent>>> = flow {
        emit(Resource.Loading())
        try {
            contentDao.getAll().collect { entities ->
                emit(Resource.Success(entities.map { it.toDomain() }))
            }
        } catch (e: Exception) {
            emit(Resource.Error(e.message ?: "Unknown error occurred"))
        }
    }

    override fun getDownloadedContent(): Flow<Resource<List<DownloadedContent>>> = flow {
        emit(Resource.Loading())
        try {
            contentDao.getDownloaded().collect { entities ->
                emit(Resource.Success(entities.map { it.toDomain() }))
            }
        } catch (e: Exception) {
            emit(Resource.Error(e.message ?: "Unknown error occurred"))
        }
    }

    override fun getAvailableOfflineContent(): Flow<Resource<List<DownloadedContent>>> = flow {
        emit(Resource.Loading())
        try {
            val currentTime = System.currentTimeMillis()
            contentDao.getAvailableOffline(currentTime).collect { entities ->
                emit(Resource.Success(entities.map { it.toDomain() }))
            }
        } catch (e: Exception) {
            emit(Resource.Error(e.message ?: "Unknown error occurred"))
        }
    }

    override fun getContentByCategory(category: ContentCategory): Flow<Resource<List<DownloadedContent>>> = flow {
        emit(Resource.Loading())
        try {
            contentDao.getByCategory(category.name).collect { entities ->
                emit(Resource.Success(entities.map { it.toDomain() }))
            }
        } catch (e: Exception) {
            emit(Resource.Error(e.message ?: "Unknown error occurred"))
        }
    }

    override fun getPendingContent(): Flow<Resource<List<DownloadedContent>>> = flow {
        emit(Resource.Loading())
        try {
            val entities = contentDao.getPendingSync()
            emit(Resource.Success(entities.map { it.toDomain() }))
        } catch (e: Exception) {
            emit(Resource.Error(e.message ?: "Unknown error occurred"))
        }
    }

    override fun getExpiredContent(): Flow<Resource<List<DownloadedContent>>> = flow {
        emit(Resource.Loading())
        try {
            val currentTime = System.currentTimeMillis()
            val entities = contentDao.getExpired(currentTime)
            emit(Resource.Success(entities.map { it.toDomain() }))
        } catch (e: Exception) {
            emit(Resource.Error(e.message ?: "Unknown error occurred"))
        }
    }

    override fun saveContent(content: DownloadedContent): Flow<Resource<DownloadedContent>> = flow {
        emit(Resource.Loading())
        try {
            val entity = content.toEntity()
            contentDao.insert(entity)
            
            val savedEntity = contentDao.getById(content.id)
            if (savedEntity != null) {
                emit(Resource.Success(savedEntity.toDomain()))
            } else {
                emit(Resource.Error("Failed to save content"))
            }
        } catch (e: Exception) {
            emit(Resource.Error(e.message ?: "Unknown error occurred"))
        }
    }

    override fun saveContentList(contentList: List<DownloadedContent>): Flow<Resource<List<DownloadedContent>>> = flow {
        emit(Resource.Loading())
        try {
            val entities = contentList.map { it.toEntity() }
            contentDao.insertAll(entities)
            
            val savedEntities = contentList.mapNotNull { contentDao.getById(it.id) }
            emit(Resource.Success(savedEntities.map { it.toDomain() }))
        } catch (e: Exception) {
            emit(Resource.Error(e.message ?: "Unknown error occurred"))
        }
    }

    override fun updateDownloadProgress(id: String, downloadedSize: Long): Flow<Resource<Boolean>> = flow {
        emit(Resource.Loading())
        try {
            contentDao.updateDownloadProgress(id, null, downloadedSize, "DOWNLOADING", System.currentTimeMillis())
            emit(Resource.Success(true))
        } catch (e: Exception) {
            emit(Resource.Error(e.message ?: "Unknown error occurred"))
        }
    }

    override fun markAsDownloaded(id: String, localPath: String, fileSize: Long, hash: String?): Flow<Resource<Boolean>> = flow {
        emit(Resource.Loading())
        try {
            contentDao.updateDownloadProgress(id, localPath, fileSize, "SYNCED", System.currentTimeMillis())
            emit(Resource.Success(true))
        } catch (e: Exception) {
            emit(Resource.Error(e.message ?: "Unknown error occurred"))
        }
    }

    override fun deleteContent(id: String): Flow<Resource<Boolean>> = flow {
        emit(Resource.Loading())
        try {
            val entity = contentDao.getById(id)
            entity?.localPath?.let { path ->
                val file = File(path)
                if (file.exists()) {
                    file.delete()
                }
            }
            contentDao.deleteById(id)
            emit(Resource.Success(true))
        } catch (e: Exception) {
            emit(Resource.Error(e.message ?: "Unknown error occurred"))
        }
    }

    override fun deleteExpiredContent(): Flow<Resource<Int>> = flow {
        emit(Resource.Loading())
        try {
            val currentTime = System.currentTimeMillis()
            val expiredEntities = contentDao.getExpired(currentTime)
            
            expiredEntities.forEach { entity ->
                entity.localPath?.let { path ->
                    val file = File(path)
                    if (file.exists()) {
                        file.delete()
                    }
                }
            }
            
            val deletedCount = contentDao.deleteExpired(currentTime)
            emit(Resource.Success(deletedCount))
        } catch (e: Exception) {
            emit(Resource.Error(e.message ?: "Unknown error occurred"))
        }
    }

    override fun markAsSynced(id: String, syncTimestamp: Long): Flow<Resource<Boolean>> = flow {
        emit(Resource.Loading())
        try {
            contentDao.updateSyncStatus(id, SyncStatus.SYNCED.name, syncTimestamp)
            emit(Resource.Success(true))
        } catch (e: Exception) {
            emit(Resource.Error(e.message ?: "Unknown error occurred"))
        }
    }

    override fun markAsPending(id: String): Flow<Resource<Boolean>> = flow {
        emit(Resource.Loading())
        try {
            val timestamp = System.currentTimeMillis()
            contentDao.updateSyncStatus(id, SyncStatus.PENDING.name, timestamp)
            emit(Resource.Success(true))
        } catch (e: Exception) {
            emit(Resource.Error(e.message ?: "Unknown error occurred"))
        }
    }

    override fun markAsConflict(id: String): Flow<Resource<Boolean>> = flow {
        emit(Resource.Loading())
        try {
            contentDao.updateSyncStatus(id, SyncStatus.CONFLICT.name, 0L)
            emit(Resource.Success(true))
        } catch (e: Exception) {
            emit(Resource.Error(e.message ?: "Unknown error occurred"))
        }
    }

    override fun incrementAccessCount(id: String): Flow<Resource<Boolean>> = flow {
        emit(Resource.Loading())
        try {
            contentDao.incrementAccessCount(id, System.currentTimeMillis())
            emit(Resource.Success(true))
        } catch (e: Exception) {
            emit(Resource.Error(e.message ?: "Unknown error occurred"))
        }
    }

    override fun getContentCount(): Flow<Resource<Int>> = flow {
        emit(Resource.Loading())
        try {
            val count = contentDao.getCount()
            emit(Resource.Success(count))
        } catch (e: Exception) {
            emit(Resource.Error(e.message ?: "Unknown error occurred"))
        }
    }

    override fun getDownloadedCount(): Flow<Resource<Int>> = flow {
        emit(Resource.Loading())
        try {
            val count = contentDao.getCountBySyncStatus("SYNCED")
            emit(Resource.Success(count))
        } catch (e: Exception) {
            emit(Resource.Error(e.message ?: "Unknown error occurred"))
        }
    }

    override fun getTotalSize(): Flow<Resource<Long>> = flow {
        emit(Resource.Loading())
        try {
            val size = contentDao.getTotalSize() ?: 0L
            emit(Resource.Success(size))
        } catch (e: Exception) {
            emit(Resource.Error(e.message ?: "Unknown error occurred"))
        }
    }

    override fun searchContent(query: String): Flow<Resource<List<DownloadedContent>>> = flow {
        emit(Resource.Loading())
        try {
            contentDao.search(query).collect { entities ->
                emit(Resource.Success(entities.map { it.toDomain() }))
            }
        } catch (e: Exception) {
            emit(Resource.Error(e.message ?: "Unknown error occurred"))
        }
    }

    override fun getContentByTag(tag: String): Flow<Resource<List<DownloadedContent>>> = flow {
        emit(Resource.Loading())
        try {
            contentDao.getByTag(tag).collect { entities ->
                emit(Resource.Success(entities.map { it.toDomain() }))
            }
        } catch (e: Exception) {
            emit(Resource.Error(e.message ?: "Unknown error occurred"))
        }
    }

    private fun DownloadedContentEntity.toDomain(): DownloadedContent {
        return DownloadedContent(
            id = id,
            title = title,
            description = description,
            url = url,
            localPath = localPath,
            thumbnailUrl = thumbnailUrl,
            fileSize = fileSize,
            downloadedSize = downloadedSize,
            mimeType = mimeType,
            hash = hash,
            category = ContentCategory.valueOf(category),
            tags = tags.split(",").filter { it.isNotBlank() },
            isAvailableOffline = isAvailableOffline,
            expiresAt = expiresAt,
            accessCount = accessCount,
            accessedAt = accessedAt,
            syncStatus = SyncStatus.valueOf(syncStatus),
            createdAt = createdAt,
            updatedAt = updatedAt,
            lastSyncAt = lastSyncAt
        )
    }

    private fun DownloadedContent.toEntity(): DownloadedContentEntity {
        return DownloadedContentEntity(
            id = id,
            title = title,
            description = description,
            url = url,
            localPath = localPath,
            thumbnailUrl = thumbnailUrl,
            fileSize = fileSize,
            downloadedSize = downloadedSize,
            mimeType = mimeType,
            hash = hash,
            category = category.name,
            tags = tags.joinToString(","),
            isAvailableOffline = isAvailableOffline,
            expiresAt = expiresAt,
            accessCount = accessCount,
            accessedAt = accessedAt,
            syncStatus = syncStatus.name,
            createdAt = createdAt,
            updatedAt = updatedAt,
            lastSyncAt = lastSyncAt
        )
    }
}

// === ARCHIVO: app/src/main/java/com/example/offlinefirst/data/sync/ConflictResolver.kt ===
package com.example.offlinefirst.data.sync

import com.example.offlinefirst.data.remote.ApiClient
import com.example.offlinefirst.domain.model.DownloadedContent
import com.example.offlinefirst.domain.model.Preferences
import com.example.offlinefirst.domain.model.SyncOperation
import com.example.offlinefirst.domain.model.SyncStatus
import com.example.offlinefirst.domain.model.User
import com.example.offlinefirst.domain.repository.ContentRepository
import com.example.offlinefirst.domain.repository.PreferencesRepository
import com.example.offlinefirst.domain.repository.UserRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ConflictResolver @Inject constructor(
    private val apiClient: ApiClient,
    private val userRepository: UserRepository,
    private val preferencesRepository: PreferencesRepository,
    private val contentRepository: ContentRepository
) {
    suspend fun resolveConflict(operation: SyncOperation): Result<Unit> = withContext(Dispatchers.IO) {
        try {
            when (operation.entityType) {
                "User" -> resolveUserConflict(operation)
                "Preferences" -> resolvePreferencesConflict(operation)
                "DownloadedContent" -> resolveContentConflict(operation)
                else -> Result.failure(IllegalArgumentException("Unknown entity type: ${operation.entityType}"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    private suspend fun resolveUserConflict(operation: SyncOperation): Result<Unit> {
        val localUser = userRepository.getUserById(operation.entityId)
            ?: return Result.failure(IllegalStateException("Local user not found"))

        val remoteUser = fetchRemoteUser(operation.entityId)
            ?: return Result.failure(IllegalStateException("Remote user not found"))

        val resolvedUser = resolveUserConflict(localUser, remoteUser)
        userRepository.saveUser(resolvedUser)
        return Result.success(Unit)
    }

    private suspend fun resolvePreferencesConflict(operation: SyncOperation): Result<Unit> {
        val localPrefs = preferencesRepository.getPreferenceById(operation.entityId)
            ?: return Result.failure(IllegalStateException("Local preferences not found"))

        val remotePrefs = fetchRemotePreferences(operation.entityId)
            ?: return Result.failure(IllegalStateException("Remote preferences not found"))

        val resolvedPrefs = resolvePreferencesConflict(localPrefs, remotePrefs)
        preferencesRepository.savePreference(resolvedPrefs)
        return Result.success(Unit)
    }

    private suspend fun resolveContentConflict(operation: SyncOperation): Result<Unit> {
        val localContent = contentRepository.getContentById(operation.entityId)
            ?: return Result.failure(IllegalStateException("Local content not found"))

        val remoteContent = fetchRemoteContent(operation.entityId)
            ?: return Result.failure(IllegalStateException("Remote content not found"))

        val resolvedContent = resolveContentConflict(localContent, remoteContent)
        contentRepository.saveContent(resolvedContent)
        return Result.success(Unit)
    }

    fun resolveUserConflict(local: User, remote: User): User {
        val strategy = determineConflictStrategy(local, remote)

        return when (strategy) {
            ConflictStrategy.USE_LOCAL -> local.copy(
                version = maxOf(local.version, remote.version) + 1,
                syncStatus = SyncStatus.SYNCED
            )
            ConflictStrategy.USE_REMOTE -> remote.copy(
                syncStatus = SyncStatus.SYNCED
            )
            ConflictStrategy.MERGE -> mergeUsers(local, remote)
        }
    }

    fun resolvePreferencesConflict(local: Preferences, remote: Preferences): Preferences {
        val strategy = determineConflictStrategy(local, remote)

        return when (strategy) {
            ConflictStrategy.USE_LOCAL -> local.copy(
                version = maxOf(local.version, remote.version) + 1,
                syncStatus = SyncStatus.SYNCED
            )
            ConflictStrategy.USE_REMOTE -> remote.copy(
                syncStatus = SyncStatus.SYNCED
            )
            ConflictStrategy.MERGE -> mergePreferences(local, remote)
        }
    }

    fun resolveContentConflict(local: DownloadedContent, remote: DownloadedContent): DownloadedContent {
        val strategy = determineConflictStrategy(local, remote)

        return when (strategy) {
            ConflictStrategy.USE_LOCAL -> local.copy(
                version = maxOf(local.version, remote.version) + 1,
                syncStatus = SyncStatus.SYNCED
            )
            ConflictStrategy.USE_REMOTE -> remote.copy(
                syncStatus = SyncStatus.SYNCED
            )
            ConflictStrategy.MERGE -> mergeContent(local, remote)
        }
    }

    private fun <T> determineConflictStrategy(local: T, remote: T): ConflictStrategy {
        val localUpdatedAt = when (local) {
            is User -> local.updatedAt
            is Preferences -> local.updatedAt
            is DownloadedContent -> local.updatedAt
            else -> return ConflictStrategy.USE_REMOTE
        }

        val remoteUpdatedAt = when (remote) {
            is User -> remote.updatedAt
            is Preferences -> remote.updatedAt
            is DownloadedContent -> remote.updatedAt
            else -> return ConflictStrategy.USE_REMOTE
        }

        return if (localUpdatedAt > remoteUpdatedAt) {
            ConflictStrategy.USE_LOCAL
        } else if (remoteUpdatedAt > localUpdatedAt) {
            ConflictStrategy.USE_REMOTE
        } else {
            ConflictStrategy.MERGE
        }
    }

    private fun mergeUsers(local: User, remote: User): User {
        return local.copy(
            email = if (local.email != remote.email) remote.email else local.email,
            displayName = if (local.displayName != remote.displayName) remote.displayName else local.displayName,
            phone = if (local.phone != remote.phone) remote.phone else local.phone,
            avatarUrl = if (local.avatarUrl != remote.avatarUrl) remote.avatarUrl else local.avatarUrl,
            isEmailVerified = local.isEmailVerified || remote.isEmailVerified,
            isPhoneVerified = local.isPhoneVerified || remote.isPhoneVerified,
            version = maxOf(local.version, remote.version) + 1,
            updatedAt = System.currentTimeMillis(),
            syncStatus = SyncStatus.SYNCED
        )
    }

    private fun mergePreferences(local: Preferences, remote: Preferences): Preferences {
        return local.copy(
            value = if (local.value != remote.value) remote.value else local.value,
            category = local.category,
            version = maxOf(local.version, remote.version) + 1,
            updatedAt = System.currentTimeMillis(),
            syncStatus = SyncStatus.SYNCED
        )
    }

    private fun mergeContent(local: DownloadedContent, remote: DownloadedContent): DownloadedContent {
        return local.copy(
            title = if (local.title != remote.title) remote.title else local.title,
            description = if (local.description != remote.description) remote.description else local.description,
            localPath = if (local.localPath != null) local.localPath else remote.localPath,
            downloadedSize = maxOf(local.downloadedSize, remote.downloadedSize),
            fileSize = maxOf(local.fileSize, remote.fileSize),
            version = maxOf(local.version, remote.version) + 1,
            accessedAt = System.currentTimeMillis(),
            syncStatus = SyncStatus.SYNCED
        )
    }

    private suspend fun fetchRemoteUser(userId: String): User? {
        return try {
            val response = apiClient.apiService.getUser(userId)
            if (response.isSuccessful) response.body() else null
        } catch (e: Exception) {
            null
        }
    }

    private suspend fun fetchRemotePreferences(prefsId: String): Preferences? {
        return try {
            val response = apiClient.apiService.getPreference(prefsId)
            if (response.isSuccessful) response.body() else null
        } catch (e: Exception) {
            null
        }
    }

    private suspend fun fetchRemoteContent(contentId: String): DownloadedContent? {
        return try {
            val response = apiClient.apiService.getContent(contentId)
            if (response.isSuccessful) response.body() else null
        } catch (e: Exception) {
            null
        }
    }
}

enum class ConflictStrategy {
    USE_LOCAL,
    USE_REMOTE,
    MERGE
}

// === ARCHIVO: app/src/main/java/com/example/offlinefirst/di/NetworkModule.kt ===
package com.example.offlinefirst.di

import android.content.Context
import com.example.offlinefirst.data.remote.ApiClient
import com.example.offlinefirst.data.remote.ApiService
import com.example.offlinefirst.domain.model.DownloadedContent
import com.example.offlinefirst.domain.model.User
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.io.File
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {
    
    private const val BASE_URL = "https://api.offlinefirst.example.com/"
    private const val CACHE_SIZE = 10L * 1024 * 1024
    private const val CONNECT_TIMEOUT = 30L
    private const val READ_TIMEOUT = 30L
    private const val WRITE_TIMEOUT = 30L
    
    @Provides
    @Singleton
    fun provideCache(@ApplicationContext context: Context): Cache {
        val cacheDir = File(context.cacheDir, "http_cache")
        return Cache(cacheDir, CACHE_SIZE)
    }
    
    @Provides
    @Singleton
    fun provideLoggingInterceptor(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
    }
    
    @Provides
    @Singleton
    fun provideOkHttpClient(
        cache: Cache,
        loggingInterceptor: HttpLoggingInterceptor
    ): OkHttpClient {
        return OkHttpClient.Builder()
            .cache(cache)
            .addInterceptor(loggingInterceptor)
            .addInterceptor { chain ->
                val original = chain.request()
                val requestBuilder = original.newBuilder()
                    .header("Accept", "application/json")
                    .header("Content-Type", "application/json")
                    .header("User-Agent", "OfflineFirstApp/1.0.0")
                    .method(original.method, original.body)
                chain.proceed(requestBuilder.build())
            }
            .addNetworkInterceptor { chain ->
                val response = chain.proceed(chain.request())
                response.newBuilder()
                    .header("Cache-Control", "public, max-age=60")
                    .removeHeader("Pragma")
                    .build()
            }
            .connectTimeout(CONNECT_TIMEOUT, TimeUnit.SECONDS)
            .readTimeout(READ_TIMEOUT, TimeUnit.SECONDS)
            .writeTimeout(WRITE_TIMEOUT, TimeUnit.SECONDS)
            .retryOnConnectionFailure(true)
            .build()
    }
    
    @Provides
    @Singleton
    fun provideMoshi(): Moshi {
        return Moshi.Builder()
            .addLast(KotlinJsonAdapterFactory())
            .build()
    }
    
    @Provides
    @Singleton
    fun provideRetrofit(
        okHttpClient: OkHttpClient,
        moshi: Moshi
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()
    }
    
    @Provides
    @Singleton
    fun provideApiService(retrofit: Retrofit): ApiService {
        return retrofit.create(ApiService::class.java)
    }
    
    @Provides
    @Singleton
    fun provideApiClient(
        apiService: ApiService,
        @ApplicationContext context: Context
    ): ApiClient {
        return ApiClient(apiService, context)
    }
}

```
