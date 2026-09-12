# AGENTS.md

Instrucciones para el agente de IA que abra este repositorio (Claude Code, Cursor, Codex, Copilot, Gemini). Se cargan solas: no hay que pegar nada en ningun chat.

## Que es este repositorio

Es el codigo base de un reto de aprendizaje de Pragma: **Implementación de persistencia local offline-first en Android**.

| | |
|---|---|
| Tema | Persistencia local offline-first |
| Nivel | senior-l2 |
| Chapter | Móvil |
| Especialidad | Android |
| Stack | Kotlin 2.1 / Android SDK 35 |
| Patron arquitectonico | clean_architecture |
| Tiempo estimado | 2 semanas |

## Tu tarea

Dejar este proyecto en estado **verificable**: que el comando de verificacion corra sin errores. Escribi los archivos en disco, en este repositorio. No generes ZIPs ni archivos adjuntos.

En orden:

1. Corre `./gradlew assembleDebug` y mira que falla.
2. Completa lo que falte de la lista de abajo: manifiesto de dependencias, punto de entrada, capa de interfaz y las capas del patron declarado.
3. Arregla SOLO los errores que impiden compilar o arrancar.
4. Volve a correr `./gradlew assembleDebug` hasta que pase.
5. Pará ahí.

## Regla dura: las fases son trabajo del humano

**PROHIBIDO implementar los entregables de las fases.** El valor del reto esta en que la persona los resuelva. Tu trabajo es que tenga un proyecto que arranca; el hueco pedagogico se queda como esta.

No resuelvas nada de esto:

- **Fase 1 — Definición de requisitos y diseño del modelo de datos**: Documento de diseño que describe el modelo de datos, las reglas de validación y los requisitos de rendimiento.
- **Fase 2 — Implementación de la capa de persistencia**: Capa de persistencia local implementada y verificada.
- **Fase 3 — Integración y sincronización con servicio en la nube**: Solución de persistencia local integrada con el servicio de sincronización en la nube y sincronización automática de datos implementada.

Distincion operativa:

- **Arreglar** (si): import faltante, tipo que no existe, dependencia sin declarar, error de sintaxis, archivo referenciado que no existe.
- **No tocar** (no): logica de negocio incompleta, validaciones ausentes, secretos hardcodeados, APIs deprecadas que funcionan, concurrencia insegura, patrones mejorables. Eso es lo que la persona tiene que encontrar.

## Lo que falta y tenes que completar

### 1. Archivos que la arquitectura declara (1 de 45)

La propuesta arquitectonica del reto los lista y no llegaron al repo. Crealos con implementacion real, respetando la capa en la que viven:

- [ ] `app/src/main/java/com/example/offlinefirst/data/remote/ApiClient.kt`

### 2. Referencias colgando (93)

Salieron de un analisis estatico del codigo que SI esta en el repo. Cada una rompe la compilacion:

- [ ] `app/src/main/java/com/example/offlinefirst/domain/usecase/SyncDataUseCase.kt` — `com.example.offlinefirst.domain.model.OperationType`
      El import com.example.offlinefirst.domain.model.OperationType usa un paquete propio del proyecto pero ningun archivo generado declara ese tipo. Falta generar la clase/interfaz, o el import esta mal escrito.
- [ ] `app/src/main/java/com/example/offlinefirst/MainActivity.kt` — `com.example.offlinefirst.presentation.ui.screens.UserScreen`
      El import com.example.offlinefirst.presentation.ui.screens.UserScreen usa un paquete propio del proyecto pero ningun archivo generado declara ese tipo. Falta generar la clase/interfaz, o el import esta mal escrito.
- [ ] `app/src/main/java/com/example/offlinefirst/MainActivity.kt` — `com.example.offlinefirst.presentation.ui.theme.OfflineFirstTheme`
      El import com.example.offlinefirst.presentation.ui.theme.OfflineFirstTheme usa un paquete propio del proyecto pero ningun archivo generado declara ese tipo. Falta generar la clase/interfaz, o el import esta mal escrito.
- [ ] `app/src/main/java/com/example/offlinefirst/data/local/entity/SyncOperationEntity.kt` — `com.example.offlinefirst.domain.model.OperationType`
      El import com.example.offlinefirst.domain.model.OperationType usa un paquete propio del proyecto pero ningun archivo generado declara ese tipo. Falta generar la clase/interfaz, o el import esta mal escrito.
- [ ] `app/src/main/java/com/example/offlinefirst/data/sync/SyncManager.kt` — `com.example.offlinefirst.domain.model.SyncResult`
      El import com.example.offlinefirst.domain.model.SyncResult usa un paquete propio del proyecto pero ningun archivo generado declara ese tipo. Falta generar la clase/interfaz, o el import esta mal escrito.
- [ ] `app/src/main/java/com/example/offlinefirst/presentation/ui/screens/UserScreen.kt` — `com.example.offlinefirst.presentation.ui.components.SyncStatusIndicator`
      El import com.example.offlinefirst.presentation.ui.components.SyncStatusIndicator usa un paquete propio del proyecto pero ningun archivo generado declara ese tipo. Falta generar la clase/interfaz, o el import esta mal escrito.
- [ ] `app/src/main/java/com/example/offlinefirst/domain/model/User.kt` — `Preferences`
      Preferences se usa en el cuerpo del archivo pero no esta importado. El proyecto lo declara en com.example.offlinefirst.util.Preferences.
- [ ] `app/src/main/java/com/example/offlinefirst/domain/model/DownloadedContent.kt` — `Content`
      Content se usa en el cuerpo del archivo pero no esta importado. El proyecto lo declara en com.example.offlinefirst.util.Content.
- [ ] `app/src/main/java/com/example/offlinefirst/domain/usecase/SaveUserUseCase.kt` — `Error`
      Error se usa en el cuerpo del archivo pero no esta importado. El proyecto lo declara en com.example.offlinefirst.util.Error (hay mas de un tipo con ese nombre en el proyecto).
- [ ] `app/src/main/java/com/example/offlinefirst/domain/usecase/SaveUserUseCase.kt` — `Success`
      Success se usa en el cuerpo del archivo pero no esta importado. El proyecto lo declara en com.example.offlinefirst.util.Success (hay mas de un tipo con ese nombre en el proyecto).
- [ ] `app/src/main/java/com/example/offlinefirst/domain/usecase/SaveUserUseCase.kt` — `Valid`
      Valid se usa en el cuerpo del archivo pero no esta importado. El proyecto lo declara en com.example.offlinefirst.domain.model.Valid (hay mas de un tipo con ese nombre en el proyecto).
- [ ] `app/src/main/java/com/example/offlinefirst/domain/usecase/SaveUserUseCase.kt` — `Invalid`
      Invalid se usa en el cuerpo del archivo pero no esta importado. El proyecto lo declara en com.example.offlinefirst.domain.model.Invalid (hay mas de un tipo con ese nombre en el proyecto).
- [ ] `app/src/main/java/com/example/offlinefirst/domain/usecase/SaveUserUseCase.kt` — `SyncOperation`
      SyncOperation se usa en el cuerpo del archivo pero no esta importado. El proyecto lo declara en com.example.offlinefirst.domain.model.SyncOperation.
- [ ] `app/src/main/java/com/example/offlinefirst/domain/usecase/SaveUserUseCase.kt` — `Loading`
      Loading se usa en el cuerpo del archivo pero no esta importado. El proyecto lo declara en com.example.offlinefirst.util.Loading (hay mas de un tipo con ese nombre en el proyecto).
- [ ] `app/src/main/java/com/example/offlinefirst/domain/usecase/SyncDataUseCase.kt` — `SyncOperationResult`
      SyncOperationResult se usa en el cuerpo del archivo pero no esta importado. El proyecto lo declara en com.example.offlinefirst.domain.repository.SyncOperationResult.
- [ ] `app/src/main/java/com/example/offlinefirst/domain/usecase/SyncDataUseCase.kt` — `Success`
      Success se usa en el cuerpo del archivo pero no esta importado. El proyecto lo declara en com.example.offlinefirst.util.Success (hay mas de un tipo con ese nombre en el proyecto).
- [ ] `app/src/main/java/com/example/offlinefirst/domain/usecase/SyncDataUseCase.kt` — `Error`
      Error se usa en el cuerpo del archivo pero no esta importado. El proyecto lo declara en com.example.offlinefirst.util.Error (hay mas de un tipo con ese nombre en el proyecto).
- [ ] `app/src/main/java/com/example/offlinefirst/data/local/entity/UserEntity.kt` — `Invalid`
      Invalid se usa en el cuerpo del archivo pero no esta importado. El proyecto lo declara en com.example.offlinefirst.domain.model.Invalid (hay mas de un tipo con ese nombre en el proyecto).
- [ ] `app/src/main/java/com/example/offlinefirst/data/local/entity/PreferencesEntity.kt` — `User`
      User se usa en el cuerpo del archivo pero no esta importado. El proyecto lo declara en com.example.offlinefirst.domain.model.User.
- [ ] `app/src/main/java/com/example/offlinefirst/data/local/entity/SyncOperationEntity.kt` — `SyncOperationStatus`
      SyncOperationStatus se usa en el cuerpo del archivo pero no esta importado. El proyecto lo declara en com.example.offlinefirst.domain.model.SyncOperationStatus.
- [ ] `app/src/main/java/com/example/offlinefirst/data/repository/PreferencesRepositoryImpl.kt` — `Resource`
      Resource se usa en el cuerpo del archivo pero no esta importado. El proyecto lo declara en com.example.offlinefirst.util.Resource.
- [ ] `app/src/main/java/com/example/offlinefirst/data/repository/PreferencesRepositoryImpl.kt` — `PreferenceValidationResult`
      PreferenceValidationResult se usa en el cuerpo del archivo pero no esta importado. El proyecto lo declara en com.example.offlinefirst.domain.model.PreferenceValidationResult.
- [ ] `app/src/main/java/com/example/offlinefirst/data/repository/PreferencesRepositoryImpl.kt` — `User`
      User se usa en el cuerpo del archivo pero no esta importado. El proyecto lo declara en com.example.offlinefirst.domain.model.User.
- [ ] `app/src/main/java/com/example/offlinefirst/data/repository/PreferencesRepositoryImpl.kt` — `Valid`
      Valid se usa en el cuerpo del archivo pero no esta importado. El proyecto lo declara en com.example.offlinefirst.domain.model.Valid (hay mas de un tipo con ese nombre en el proyecto).
- [ ] `app/src/main/java/com/example/offlinefirst/data/repository/PreferencesRepositoryImpl.kt` — `Invalid`
      Invalid se usa en el cuerpo del archivo pero no esta importado. El proyecto lo declara en com.example.offlinefirst.domain.model.Invalid (hay mas de un tipo con ese nombre en el proyecto).
- [ ] `app/src/main/java/com/example/offlinefirst/data/repository/ContentRepositoryImpl.kt` — `Loading`
      Loading se usa en el cuerpo del archivo pero no esta importado. El proyecto lo declara en com.example.offlinefirst.util.Loading (hay mas de un tipo con ese nombre en el proyecto).
- [ ] `app/src/main/java/com/example/offlinefirst/data/repository/ContentRepositoryImpl.kt` — `Success`
      Success se usa en el cuerpo del archivo pero no esta importado. El proyecto lo declara en com.example.offlinefirst.util.Success (hay mas de un tipo con ese nombre en el proyecto).
- [ ] `app/src/main/java/com/example/offlinefirst/data/repository/ContentRepositoryImpl.kt` — `Error`
      Error se usa en el cuerpo del archivo pero no esta importado. El proyecto lo declara en com.example.offlinefirst.util.Error (hay mas de un tipo con ese nombre en el proyecto).
- [ ] `app/src/main/java/com/example/offlinefirst/data/repository/ContentRepositoryImpl.kt` — `Content`
      Content se usa en el cuerpo del archivo pero no esta importado. El proyecto lo declara en com.example.offlinefirst.util.Content.
- [ ] `app/src/main/java/com/example/offlinefirst/data/repository/ContentRepositoryImpl.kt` — `Failed`
      Failed se usa en el cuerpo del archivo pero no esta importado. El proyecto lo declara en com.example.offlinefirst.data.sync.Failed (hay mas de un tipo con ese nombre en el proyecto).
- [ ] `app/src/main/java/com/example/offlinefirst/data/repository/SyncRepositoryImpl.kt` — `Loading`
      Loading se usa en el cuerpo del archivo pero no esta importado. El proyecto lo declara en com.example.offlinefirst.util.Loading (hay mas de un tipo con ese nombre en el proyecto).
- [ ] `app/src/main/java/com/example/offlinefirst/data/repository/SyncRepositoryImpl.kt` — `Success`
      Success se usa en el cuerpo del archivo pero no esta importado. El proyecto lo declara en com.example.offlinefirst.util.Success (hay mas de un tipo con ese nombre en el proyecto).
- [ ] `app/src/main/java/com/example/offlinefirst/data/repository/SyncRepositoryImpl.kt` — `Error`
      Error se usa en el cuerpo del archivo pero no esta importado. El proyecto lo declara en com.example.offlinefirst.util.Error (hay mas de un tipo con ese nombre en el proyecto).
- [ ] `app/src/main/java/com/example/offlinefirst/data/repository/SyncRepositoryImpl.kt` — `Sync`
      Sync se usa en el cuerpo del archivo pero no esta importado. El proyecto lo declara en com.example.offlinefirst.util.Sync (hay mas de un tipo con ese nombre en el proyecto).
- [ ] `app/src/main/java/com/example/offlinefirst/data/repository/SyncRepositoryImpl.kt` — `Failed`
      Failed se usa en el cuerpo del archivo pero no esta importado. El proyecto lo declara en com.example.offlinefirst.data.sync.Failed (hay mas de un tipo con ese nombre en el proyecto).
- [ ] `app/src/main/java/com/example/offlinefirst/data/remote/ApiService.kt` — `Content`
      Content se usa en el cuerpo del archivo pero no esta importado. El proyecto lo declara en com.example.offlinefirst.util.Content.
- [ ] `app/src/main/java/com/example/offlinefirst/data/remote/ApiService.kt` — `Invalid`
      Invalid se usa en el cuerpo del archivo pero no esta importado. El proyecto lo declara en com.example.offlinefirst.domain.model.Invalid (hay mas de un tipo con ese nombre en el proyecto).
- [ ] `app/src/main/java/com/example/offlinefirst/data/remote/ApiService.kt` — `Resource`
      Resource se usa en el cuerpo del archivo pero no esta importado. El proyecto lo declara en com.example.offlinefirst.util.Resource.
- [ ] `app/src/main/java/com/example/offlinefirst/data/queue/OperationQueueManager.kt` — `Queue`
      Queue se usa en el cuerpo del archivo pero no esta importado. El proyecto lo declara en com.example.offlinefirst.util.Queue.
- [ ] `app/src/main/java/com/example/offlinefirst/data/sync/SyncManager.kt` — `Sync`
      Sync se usa en el cuerpo del archivo pero no esta importado. El proyecto lo declara en com.example.offlinefirst.util.Sync (hay mas de un tipo con ese nombre en el proyecto).
- [ ] `app/src/main/java/com/example/offlinefirst/data/sync/SyncManager.kt` — `Preferences`
      Preferences se usa en el cuerpo del archivo pero no esta importado. El proyecto lo declara en com.example.offlinefirst.domain.model.Preferences (hay mas de un tipo con ese nombre en el proyecto).
- [ ] `app/src/main/java/com/example/offlinefirst/data/sync/SyncManager.kt` — `DownloadedContent`
      DownloadedContent se usa en el cuerpo del archivo pero no esta importado. El proyecto lo declara en com.example.offlinefirst.domain.model.DownloadedContent.
- [ ] `app/src/main/java/com/example/offlinefirst/di/NetworkModule.kt` — `Content`
      Content se usa en el cuerpo del archivo pero no esta importado. El proyecto lo declara en com.example.offlinefirst.util.Content.
- [ ] `app/src/main/java/com/example/offlinefirst/presentation/viewmodel/UserViewModel.kt` — `ErrorMessages`
      ErrorMessages se usa en el cuerpo del archivo pero no esta importado. El proyecto lo declara en com.example.offlinefirst.util.ErrorMessages.
- [ ] `app/src/main/java/com/example/offlinefirst/presentation/viewmodel/UserViewModel.kt` — `Error`
      Error se usa en el cuerpo del archivo pero no esta importado. El proyecto lo declara en com.example.offlinefirst.util.Error (hay mas de un tipo con ese nombre en el proyecto).
- [ ] `app/src/main/java/com/example/offlinefirst/presentation/ui/screens/UserScreen.kt` — `Sync`
      Sync se usa en el cuerpo del archivo pero no esta importado. El proyecto lo declara en com.example.offlinefirst.util.Sync (hay mas de un tipo con ese nombre en el proyecto).
- [ ] `app/src/main/java/com/example/offlinefirst/presentation/ui/screens/UserScreen.kt` — `Preferences`
      Preferences se usa en el cuerpo del archivo pero no esta importado. El proyecto lo declara en com.example.offlinefirst.domain.model.Preferences (hay mas de un tipo con ese nombre en el proyecto).
- [ ] `app/src/main/java/com/example/offlinefirst/presentation/ui/screens/UserScreen.kt` — `Content`
      Content se usa en el cuerpo del archivo pero no esta importado. El proyecto lo declara en com.example.offlinefirst.util.Content.
- [ ] `app/src/main/java/com/example/offlinefirst/presentation/ui/screens/UserScreen.kt` — `Error`
      Error se usa en el cuerpo del archivo pero no esta importado. El proyecto lo declara en com.example.offlinefirst.util.Error (hay mas de un tipo con ese nombre en el proyecto).
- [ ] `app/src/main/java/com/example/offlinefirst/data/sync/SyncWorker.kt` — `Error`
      Error se usa en el cuerpo del archivo pero no esta importado. El proyecto lo declara en com.example.offlinefirst.util.Error (hay mas de un tipo con ese nombre en el proyecto).
- [ ] `app/src/main/java/com/example/offlinefirst/data/sync/SyncManager.kt` — `ApiService`
      El import com.example.offlinefirst.data.remote.ApiService no se usa en ningun lado del cuerpo del archivo. Se puede eliminar.
- [ ] `app/src/main/java/com/example/offlinefirst/data/sync/SyncManager.kt` — `SyncStatus`
      El import com.example.offlinefirst.domain.model.SyncStatus no se usa en ningun lado del cuerpo del archivo. Se puede eliminar.
- [ ] `app/src/main/java/com/example/offlinefirst/data/sync/SyncManager.kt` — `SyncRepository`
      El import com.example.offlinefirst.domain.repository.SyncRepository no se usa en ningun lado del cuerpo del archivo. Se puede eliminar.
- [ ] `app/src/main/java/com/example/offlinefirst/di/NetworkModule.kt` — `DownloadedContent`
      El import com.example.offlinefirst.domain.model.DownloadedContent no se usa en ningun lado del cuerpo del archivo. Se puede eliminar.
- [ ] `app/src/main/java/com/example/offlinefirst/presentation/viewmodel/UserViewModel.kt` — `UserValidationResult`
      El import com.example.offlinefirst.domain.model.UserValidationResult no se usa en ningun lado del cuerpo del archivo. Se puede eliminar.
- [ ] `app/src/main/java/com/example/offlinefirst/domain/usecase/SyncDataUseCase.kt` — `UserRepository.getUsersBySyncStatus`
      Se invoca `getUsersBySyncStatus` sobre `UserRepository`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `app/src/main/java/com/example/offlinefirst/domain/usecase/SyncDataUseCase.kt` — `UserRepository.syncUserToRemote`
      Se invoca `syncUserToRemote` sobre `UserRepository`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `app/src/main/java/com/example/offlinefirst/domain/usecase/SyncDataUseCase.kt` — `PreferencesRepository.getPreferencesBySyncStatus`
      Se invoca `getPreferencesBySyncStatus` sobre `PreferencesRepository`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `app/src/main/java/com/example/offlinefirst/domain/usecase/SyncDataUseCase.kt` — `PreferencesRepository.syncPreferenceToRemote`
      Se invoca `syncPreferenceToRemote` sobre `PreferencesRepository`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `app/src/main/java/com/example/offlinefirst/domain/usecase/SyncDataUseCase.kt` — `ContentRepository.getContentBySyncStatus`
      Se invoca `getContentBySyncStatus` sobre `ContentRepository`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `app/src/main/java/com/example/offlinefirst/domain/usecase/SyncDataUseCase.kt` — `ContentRepository.syncContentToRemote`
      Se invoca `syncContentToRemote` sobre `ContentRepository`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `app/src/main/java/com/example/offlinefirst/domain/usecase/SyncDataUseCase.kt` — `UserRepository.deleteUserFromRemote`
      Se invoca `deleteUserFromRemote` sobre `UserRepository`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `app/src/main/java/com/example/offlinefirst/data/repository/UserRepositoryImpl.kt` — `User.validate`
      Se invoca `validate` sobre `User`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `app/src/main/java/com/example/offlinefirst/data/repository/UserRepositoryImpl.kt` — `User.toEntity`
      Se invoca `toEntity` sobre `User`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `app/src/main/java/com/example/offlinefirst/data/repository/PreferencesRepositoryImpl.kt` — `Preferences.validate`
      Se invoca `validate` sobre `Preferences`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `app/src/main/java/com/example/offlinefirst/data/repository/PreferencesRepositoryImpl.kt` — `Preferences.toEntity`
      Se invoca `toEntity` sobre `Preferences`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `app/src/main/java/com/example/offlinefirst/data/repository/ContentRepositoryImpl.kt` — `DownloadedContent.toEntity`
      Se invoca `toEntity` sobre `DownloadedContent`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `app/src/main/java/com/example/offlinefirst/data/repository/SyncRepositoryImpl.kt` — `SyncOperation.toEntity`
      Se invoca `toEntity` sobre `SyncOperation`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `app/src/main/java/com/example/offlinefirst/data/queue/OperationQueueManager.kt` — `SyncOperation.toEntity`
      Se invoca `toEntity` sobre `SyncOperation`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `app/src/main/java/com/example/offlinefirst/data/sync/SyncManager.kt` — `UserRepositoryImpl.getUserById`
      Se invoca `getUserById` sobre `UserRepositoryImpl`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `app/src/main/java/com/example/offlinefirst/data/sync/SyncManager.kt` — `SyncRepositoryImpl.recordSync`
      Se invoca `recordSync` sobre `SyncRepositoryImpl`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `app/src/main/java/com/example/offlinefirst/data/sync/ConflictResolver.kt` — `DownloadedContent.copy`
      Se invoca `copy` sobre `DownloadedContent`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `app/src/main/java/com/example/offlinefirst/presentation/viewmodel/UserViewModel.kt` — `GetUserUseCase.getAllUsers`
      Se invoca `getAllUsers` sobre `GetUserUseCase`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `app/src/main/java/com/example/offlinefirst/presentation/viewmodel/UserViewModel.kt` — `GetUserUseCase.getUserById`
      Se invoca `getUserById` sobre `GetUserUseCase`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `app/src/main/java/com/example/offlinefirst/presentation/viewmodel/UserViewModel.kt` — `User.validate`
      Se invoca `validate` sobre `User`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `app/src/main/java/com/example/offlinefirst/presentation/viewmodel/UserViewModel.kt` — `SaveUserUseCase.saveUser`
      Se invoca `saveUser` sobre `SaveUserUseCase`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `app/src/main/java/com/example/offlinefirst/presentation/viewmodel/UserViewModel.kt` — `SaveUserUseCase.deleteUser`
      Se invoca `deleteUser` sobre `SaveUserUseCase`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `app/src/main/java/com/example/offlinefirst/presentation/viewmodel/UserViewModel.kt` — `SyncDataUseCase.syncAll`
      Se invoca `syncAll` sobre `SyncDataUseCase`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `app/src/main/java/com/example/offlinefirst/data/sync/SyncWorker.kt` — `NetworkMonitor.isNetworkAvailable`
      Se invoca `isNetworkAvailable` sobre `NetworkMonitor`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `app/src/main/java/com/example/offlinefirst/data/sync/SyncWorker.kt` — `UserRepository.getPendingUsers`
      Se invoca `getPendingUsers` sobre `UserRepository`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `app/src/main/java/com/example/offlinefirst/data/sync/SyncWorker.kt` — `PreferencesRepository.getPendingPreferences`
      Se invoca `getPendingPreferences` sobre `PreferencesRepository`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `app/src/main/java/com/example/offlinefirst/data/sync/SyncWorker.kt` — `ContentRepository.getPendingContents`
      Se invoca `getPendingContents` sobre `ContentRepository`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `app/src/main/java/com/example/offlinefirst/data/sync/SyncWorker.kt` — `SyncRepository.updateSyncStatus`
      Se invoca `updateSyncStatus` sobre `SyncRepository`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `app/src/main/java/com/example/offlinefirst/data/sync/SyncWorker.kt` — `SyncManager.syncUser`
      Se invoca `syncUser` sobre `SyncManager`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `app/src/main/java/com/example/offlinefirst/data/sync/SyncWorker.kt` — `UserRepository.markAsSynced`
      Se invoca `markAsSynced` sobre `UserRepository`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `app/src/main/java/com/example/offlinefirst/data/sync/SyncWorker.kt` — `UserRepository.markAsError`
      Se invoca `markAsError` sobre `UserRepository`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `app/src/main/java/com/example/offlinefirst/data/sync/SyncWorker.kt` — `SyncManager.syncPreference`
      Se invoca `syncPreference` sobre `SyncManager`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `app/src/main/java/com/example/offlinefirst/data/sync/SyncWorker.kt` — `PreferencesRepository.markAsSynced`
      Se invoca `markAsSynced` sobre `PreferencesRepository`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `app/src/main/java/com/example/offlinefirst/data/sync/SyncWorker.kt` — `PreferencesRepository.markAsError`
      Se invoca `markAsError` sobre `PreferencesRepository`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `app/src/main/java/com/example/offlinefirst/data/sync/SyncWorker.kt` — `SyncManager.syncContent`
      Se invoca `syncContent` sobre `SyncManager`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `app/src/main/java/com/example/offlinefirst/data/sync/SyncWorker.kt` — `ContentRepository.markAsSynced`
      Se invoca `markAsSynced` sobre `ContentRepository`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `app/src/main/java/com/example/offlinefirst/data/sync/SyncWorker.kt` — `ContentRepository.markAsError`
      Se invoca `markAsError` sobre `ContentRepository`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `app/src/main/java/com/example/offlinefirst/data/sync/SyncWorker.kt` — `SyncRepository.recordSyncOperation`
      Se invoca `recordSyncOperation` sobre `SyncRepository`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.

### Presentes (57)

- `build.gradle.kts`
- `app/build.gradle.kts`
- `app/src/main/java/com/example/offlinefirst/OfflineFirstApplication.kt`
- `app/src/main/java/com/example/offlinefirst/domain/model/User.kt`
- `app/src/main/java/com/example/offlinefirst/domain/model/Preferences.kt`
- `app/src/main/java/com/example/offlinefirst/domain/model/DownloadedContent.kt`
- `app/src/main/java/com/example/offlinefirst/domain/model/SyncOperation.kt`
- `app/src/main/java/com/example/offlinefirst/domain/repository/UserRepository.kt`
- `app/src/main/java/com/example/offlinefirst/domain/repository/PreferencesRepository.kt`
- `app/src/main/java/com/example/offlinefirst/domain/repository/ContentRepository.kt`
- `app/src/main/java/com/example/offlinefirst/domain/repository/SyncRepository.kt`
- `app/src/main/java/com/example/offlinefirst/domain/usecase/GetUserUseCase.kt`
- `app/src/main/java/com/example/offlinefirst/domain/usecase/SaveUserUseCase.kt`
- `app/src/main/java/com/example/offlinefirst/domain/usecase/SyncDataUseCase.kt`
- `app/src/main/AndroidManifest.xml`
- `app/src/main/java/com/example/offlinefirst/MainActivity.kt`
- `app/src/main/java/com/example/offlinefirst/data/local/entity/UserEntity.kt`
- `app/src/main/java/com/example/offlinefirst/data/local/entity/PreferencesEntity.kt`
- `app/src/main/java/com/example/offlinefirst/data/local/entity/DownloadedContentEntity.kt`
- `app/src/main/java/com/example/offlinefirst/data/local/entity/SyncOperationEntity.kt`
- `app/src/main/java/com/example/offlinefirst/data/local/dao/UserDao.kt`
- `app/src/main/java/com/example/offlinefirst/data/local/dao/PreferencesDao.kt`
- `app/src/main/java/com/example/offlinefirst/data/local/dao/ContentDao.kt`
- `app/src/main/java/com/example/offlinefirst/data/local/dao/SyncOperationDao.kt`
- `app/src/main/java/com/example/offlinefirst/data/local/database/OfflineFirstDatabase.kt`
- `app/src/main/java/com/example/offlinefirst/data/repository/UserRepositoryImpl.kt`
- `app/src/main/java/com/example/offlinefirst/data/repository/PreferencesRepositoryImpl.kt`
- `app/src/main/java/com/example/offlinefirst/data/repository/ContentRepositoryImpl.kt`
- `app/src/main/java/com/example/offlinefirst/data/repository/SyncRepositoryImpl.kt`
- `app/src/main/java/com/example/offlinefirst/data/remote/ApiService.kt`
- `app/src/main/java/com/example/offlinefirst/data/queue/OperationQueueManager.kt`
- `app/src/main/java/com/example/offlinefirst/data/sync/SyncManager.kt`
- `app/src/main/java/com/example/offlinefirst/data/sync/ConflictResolver.kt`
- `app/src/main/java/com/example/offlinefirst/data/sync/NetworkMonitor.kt`
- `app/src/main/java/com/example/offlinefirst/di/DatabaseModule.kt`
- `app/src/main/java/com/example/offlinefirst/di/NetworkModule.kt`
- `app/src/main/java/com/example/offlinefirst/di/RepositoryModule.kt`
- `app/src/main/java/com/example/offlinefirst/util/Resource.kt`
- `app/src/main/java/com/example/offlinefirst/util/Constants.kt`
- `app/src/main/java/com/example/offlinefirst/presentation/viewmodel/UserViewModel.kt`
- `app/src/main/java/com/example/offlinefirst/presentation/ui/screens/UserScreen.kt`
- `app/src/main/java/com/example/offlinefirst/presentation/ui/components/SyncStatusIndicator.kt`
- `app/src/main/java/com/example/offlinefirst/data/sync/SyncWorker.kt`
- `settings.gradle.kts`
- `app/src/main/java/com/example/offlinefirst/presentation/ui/theme/OfflineFirstTheme.kt`
- `app/src/main/java/com/example/offlinefirst/presentation/ui/theme/Type.kt`
- `app/src/main/java/com/example/offlinefirst/domain/model/SyncOperationType.kt`
- `app/src/main/java/com/example/offlinefirst/domain/model/Success.kt`
- `app/src/main/java/com/example/offlinefirst/util/Loading.kt`
- `app/src/main/java/com/example/offlinefirst/util/Error.kt`
- `app/src/main/java/com/example/offlinefirst/util/Sync.kt`
- `app/src/main/java/com/example/offlinefirst/data/sync/Failed.kt`
- `app/src/main/java/com/example/offlinefirst/data/remote/dto/UserDto.kt`
- `app/src/main/java/com/example/offlinefirst/data/remote/dto/ContentDto.kt`
- `app/src/main/java/com/example/offlinefirst/data/remote/dto/PreferencesDto.kt`
- `app/src/main/java/com/example/offlinefirst/data/remote/dto/SyncRequestDto.kt`
- `app/src/main/java/com/example/offlinefirst/data/remote/dto/SyncResponseDto.kt`

### Capas del patron declarado

Cada una tiene que existir como directorio real con al menos un archivo. Codigo plano en la raiz no satisface el patron.

- `app/src/main/java/com/example/offlinefirst/data/local/dao`
- `app/src/main/java/com/example/offlinefirst/data/local/entity`
- `app/src/main/java/com/example/offlinefirst/data/local/database`
- `app/src/main/java/com/example/offlinefirst/data/remote`
- `app/src/main/java/com/example/offlinefirst/data/repository`
- `app/src/main/java/com/example/offlinefirst/data/sync`
- `app/src/main/java/com/example/offlinefirst/data/queue`
- `app/src/main/java/com/example/offlinefirst/domain/model`
- `app/src/main/java/com/example/offlinefirst/domain/repository`
- `app/src/main/java/com/example/offlinefirst/domain/usecase`
- `app/src/main/java/com/example/offlinefirst/presentation/ui/screens`
- `app/src/main/java/com/example/offlinefirst/presentation/ui/components`
- `app/src/main/java/com/example/offlinefirst/presentation/viewmodel`
- `app/src/main/java/com/example/offlinefirst/di`
- `app/src/main/java/com/example/offlinefirst/util`

## Verificacion

```bash
./gradlew assembleDebug
```

Ese comando pasando es la definicion de "terminado" para vos.

## Convenciones que tenes que respetar

- Un solo ecosistema: no declares librerias de otro lenguaje ni mezcles gestores de paquetes.
- Toda libreria que uses tiene que estar declarada en el manifiesto de dependencias.
- Todo import declarado tiene que usarse; todo tipo usado tiene que existir o venir de una dependencia declarada.
- El patron es **clean_architecture**: los contratos (interfaces, puertos) los define la capa interna y los implementa la externa, nunca al revés.
- Los archivos que crees llevan implementacion real, no stubs: sin `TODO`, sin cuerpos vacios, sin `// getters y setters`.

## Contexto del candidato

Sirve para calibrar el nivel del codigo, no para resolver las fases.

- Perfil: Chapter Movil, Especialidad Android, Tecnología Android, Senior
- Brecha que el reto ataca: Necesita fortalecer la practica de Android
- Mision: Liderar la iniciativa de persistencia local offline-first

---

*Generado por Challenge Generator — Pragma. `README.md` tiene el enunciado completo del reto para la persona. `PROMPT_MEJORA.md` es la variante para pegar en un chat, si se prefiere ese flujo.*
