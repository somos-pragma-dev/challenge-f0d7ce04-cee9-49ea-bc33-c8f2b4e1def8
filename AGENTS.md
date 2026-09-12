# AGENTS.md

Instrucciones para el agente de IA que abra este repositorio (Claude Code, Cursor, Codex, Copilot, Gemini). Se cargan solas: no hay que pegar nada en ningun chat.

## Que es este repositorio

Es el codigo base de un reto de aprendizaje de Pragma: **Implementación de persistencia local offline-first en una aplicación Android**.

| | |
|---|---|
| Tema | Persistencia local offline-first |
| Nivel | senior-l2 |
| Chapter | Móvil |
| Especialidad | Android |
| Stack | Kotlin 2.1 / Android SDK 35 |
| Patron arquitectonico | clean_architecture_hexagonal |
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

- **Fase 1 — Diseño del modelo de datos**: Esquema de la base de datos local con relaciones definidas.
- **Fase 2 — Implementación de la persistencia local**: Código fuente que implementa la persistencia local de los datos.
- **Fase 3 — Sincronización de datos**: Código fuente que implementa la sincronización automática de datos.

Distincion operativa:

- **Arreglar** (si): import faltante, tipo que no existe, dependencia sin declarar, error de sintaxis, archivo referenciado que no existe.
- **No tocar** (no): logica de negocio incompleta, validaciones ausentes, secretos hardcodeados, APIs deprecadas que funcionan, concurrencia insegura, patrones mejorables. Eso es lo que la persona tiene que encontrar.

## Lo que falta y tenes que completar

### 1. Boilerplate del stack (1)

Sin esto el proyecto no compila ni arranca. **Es tu trabajo crearlo**, y no toca nada de lo pedagogico: es andamiaje del stack.

- [ ] **Activity o Application (con @HiltAndroidApp / setContent)** — Sin una Activity o Application, no hay componente que Android pueda lanzar al abrir la app.

### 2. Archivos que la arquitectura declara (10 de 48)

La propuesta arquitectonica del reto los lista y no llegaron al repo. Crealos con implementacion real, respetando la capa en la que viven:

- [ ] `app/src/main/java/com/example/offlinefirst/data/sync/SyncManager.kt`
- [ ] `app/src/main/java/com/example/offlinefirst/presentation/ui/viewmodel/ProductViewModel.kt`
- [ ] `app/src/main/java/com/example/offlinefirst/presentation/ui/viewmodel/PurchaseViewModel.kt`
- [ ] `app/src/main/java/com/example/offlinefirst/presentation/ui/viewmodel/SettingsViewModel.kt`
- [ ] `app/src/main/java/com/example/offlinefirst/presentation/ui/screens/ProductListScreen.kt`
- [ ] `app/src/main/java/com/example/offlinefirst/presentation/ui/screens/PurchaseHistoryScreen.kt`
- [ ] `app/src/main/java/com/example/offlinefirst/presentation/ui/screens/SettingsScreen.kt`
- [ ] `app/src/main/java/com/example/offlinefirst/presentation/ui/components/SyncStatusBanner.kt`
- [ ] `app/src/main/java/com/example/offlinefirst/di/DatabaseModule.kt`
- [ ] `app/src/main/res/values/themes.xml`

### 3. Referencias colgando (50)

Salieron de un analisis estatico del codigo que SI esta en el repo. Cada una rompe la compilacion:

- [ ] `app/src/main/java/com/example/offlinefirst/di/NetworkModule.kt` — `com.example.offlinefirst.BuildConfig`
      El import com.example.offlinefirst.BuildConfig usa un paquete propio del proyecto pero ningun archivo generado declara ese tipo. Falta generar la clase/interfaz, o el import esta mal escrito.
- [ ] `app/src/main/java/com/example/offlinefirst/data/remote/dto/PurchaseDto.kt` — `PurchaseStatus`
      PurchaseStatus se usa en el cuerpo del archivo pero no esta importado. El proyecto lo declara en com.example.offlinefirst.domain.model.PurchaseStatus.
- [ ] `app/src/main/java/com/example/offlinefirst/domain/usecase/PurchaseUseCases.kt` — `Loading`
      Loading se usa en el cuerpo del archivo pero no esta importado. El proyecto lo declara en com.example.offlinefirst.util.Loading.
- [ ] `app/src/main/java/com/example/offlinefirst/domain/usecase/PurchaseUseCases.kt` — `Success`
      Success se usa en el cuerpo del archivo pero no esta importado. El proyecto lo declara en com.example.offlinefirst.util.Success.
- [ ] `app/src/main/java/com/example/offlinefirst/domain/usecase/PurchaseUseCases.kt` — `Error`
      Error se usa en el cuerpo del archivo pero no esta importado. El proyecto lo declara en com.example.offlinefirst.util.Error.
- [ ] `app/src/main/java/com/example/offlinefirst/domain/usecase/UserPreferencesUseCases.kt` — `Loading`
      Loading se usa en el cuerpo del archivo pero no esta importado. El proyecto lo declara en com.example.offlinefirst.util.Loading.
- [ ] `app/src/main/java/com/example/offlinefirst/domain/usecase/UserPreferencesUseCases.kt` — `Success`
      Success se usa en el cuerpo del archivo pero no esta importado. El proyecto lo declara en com.example.offlinefirst.util.Success.
- [ ] `app/src/main/java/com/example/offlinefirst/domain/usecase/UserPreferencesUseCases.kt` — `Error`
      Error se usa en el cuerpo del archivo pero no esta importado. El proyecto lo declara en com.example.offlinefirst.util.Error.
- [ ] `app/src/main/java/com/example/offlinefirst/domain/usecase/SyncDataUseCase.kt` — `Loading`
      Loading se usa en el cuerpo del archivo pero no esta importado. El proyecto lo declara en com.example.offlinefirst.util.Loading.
- [ ] `app/src/main/java/com/example/offlinefirst/domain/usecase/SyncDataUseCase.kt` — `Error`
      Error se usa en el cuerpo del archivo pero no esta importado. El proyecto lo declara en com.example.offlinefirst.util.Error.
- [ ] `app/src/main/java/com/example/offlinefirst/domain/usecase/SyncDataUseCase.kt` — `Success`
      Success se usa en el cuerpo del archivo pero no esta importado. El proyecto lo declara en com.example.offlinefirst.util.Success.
- [ ] `app/src/main/java/com/example/offlinefirst/data/repository/ProductRepositoryImpl.kt` — `Loading`
      Loading se usa en el cuerpo del archivo pero no esta importado. El proyecto lo declara en com.example.offlinefirst.util.Loading.
- [ ] `app/src/main/java/com/example/offlinefirst/data/repository/ProductRepositoryImpl.kt` — `Success`
      Success se usa en el cuerpo del archivo pero no esta importado. El proyecto lo declara en com.example.offlinefirst.util.Success.
- [ ] `app/src/main/java/com/example/offlinefirst/data/repository/ProductRepositoryImpl.kt` — `Error`
      Error se usa en el cuerpo del archivo pero no esta importado. El proyecto lo declara en com.example.offlinefirst.util.Error.
- [ ] `app/src/main/java/com/example/offlinefirst/data/repository/ProductRepositoryImpl.kt` — `CreateProductRequest`
      CreateProductRequest se usa en el cuerpo del archivo pero no esta importado. El proyecto lo declara en com.example.offlinefirst.data.remote.dto.CreateProductRequest.
- [ ] `app/src/main/java/com/example/offlinefirst/data/repository/ProductRepositoryImpl.kt` — `UpdateProductRequest`
      UpdateProductRequest se usa en el cuerpo del archivo pero no esta importado. El proyecto lo declara en com.example.offlinefirst.data.remote.dto.UpdateProductRequest.
- [ ] `app/src/main/java/com/example/offlinefirst/data/repository/ProductRepositoryImpl.kt` — `SyncStatus`
      SyncStatus se usa en el cuerpo del archivo pero no esta importado. El proyecto lo declara en com.example.offlinefirst.domain.model.SyncStatus.
- [ ] `app/src/main/java/com/example/offlinefirst/data/repository/PurchaseRepositoryImpl.kt` — `Loading`
      Loading se usa en el cuerpo del archivo pero no esta importado. El proyecto lo declara en com.example.offlinefirst.util.Loading.
- [ ] `app/src/main/java/com/example/offlinefirst/data/repository/PurchaseRepositoryImpl.kt` — `Success`
      Success se usa en el cuerpo del archivo pero no esta importado. El proyecto lo declara en com.example.offlinefirst.util.Success.
- [ ] `app/src/main/java/com/example/offlinefirst/data/repository/PurchaseRepositoryImpl.kt` — `Error`
      Error se usa en el cuerpo del archivo pero no esta importado. El proyecto lo declara en com.example.offlinefirst.util.Error.
- [ ] `app/src/main/java/com/example/offlinefirst/data/repository/UserPreferencesRepositoryImpl.kt` — `Loading`
      Loading se usa en el cuerpo del archivo pero no esta importado. El proyecto lo declara en com.example.offlinefirst.util.Loading.
- [ ] `app/src/main/java/com/example/offlinefirst/data/repository/UserPreferencesRepositoryImpl.kt` — `Success`
      Success se usa en el cuerpo del archivo pero no esta importado. El proyecto lo declara en com.example.offlinefirst.util.Success.
- [ ] `app/src/main/java/com/example/offlinefirst/data/repository/UserPreferencesRepositoryImpl.kt` — `Error`
      Error se usa en el cuerpo del archivo pero no esta importado. El proyecto lo declara en com.example.offlinefirst.util.Error.
- [ ] `app/src/main/java/com/example/offlinefirst/data/sync/SyncWorker.kt` — `Error`
      Error se usa en el cuerpo del archivo pero no esta importado. El proyecto lo declara en com.example.offlinefirst.util.Error.
- [ ] `app/src/main/java/com/example/offlinefirst/data/sync/SyncWorker.kt` — `Success`
      Success se usa en el cuerpo del archivo pero no esta importado. El proyecto lo declara en com.example.offlinefirst.util.Success.
- [ ] `app/src/main/java/com/example/offlinefirst/data/sync/SyncWorker.kt` — `Loading`
      Loading se usa en el cuerpo del archivo pero no esta importado. El proyecto lo declara en com.example.offlinefirst.util.Loading.
- [ ] `app/src/main/java/com/example/offlinefirst/data/local/entity/Product.kt` — `Product`
      Product se usa en el cuerpo del archivo pero no esta importado. El proyecto lo declara en com.example.offlinefirst.domain.model.Product.
- [ ] `app/src/main/java/com/example/offlinefirst/data/local/entity/Product.kt` — `SyncState`
      SyncState se usa en el cuerpo del archivo pero no esta importado. El proyecto lo declara en com.example.offlinefirst.domain.model.SyncState.
- [ ] `app/src/main/java/com/example/offlinefirst/data/repository/PurchaseRepositoryImpl.kt` — `SyncStatus`
      El import com.example.offlinefirst.domain.model.SyncStatus no se usa en ningun lado del cuerpo del archivo. Se puede eliminar.
- [ ] `app/src/main/java/com/example/offlinefirst/data/sync/ConflictResolver.kt` — `SyncStatus`
      El import com.example.offlinefirst.domain.model.SyncStatus no se usa en ningun lado del cuerpo del archivo. Se puede eliminar.
- [ ] `app/src/main/java/com/example/offlinefirst/data/sync/ConflictResolver.kt` — `SyncState`
      El import com.example.offlinefirst.domain.model.SyncState no se usa en ningun lado del cuerpo del archivo. Se puede eliminar.
- [ ] `app/src/main/java/com/example/offlinefirst/data/repository/ProductRepositoryImpl.kt` — `Product.toEntity`
      Se invoca `toEntity` sobre `Product`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `app/src/main/java/com/example/offlinefirst/data/repository/ProductRepositoryImpl.kt` — `Product.toDomain`
      Se invoca `toDomain` sobre `Product`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `app/src/main/java/com/example/offlinefirst/data/repository/ProductRepositoryImpl.kt` — `Product.toPendingEntity`
      Se invoca `toPendingEntity` sobre `Product`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `app/src/main/java/com/example/offlinefirst/data/repository/ProductRepositoryImpl.kt` — `ProductDao.insertPendingProduct`
      Se invoca `insertPendingProduct` sobre `ProductDao`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `app/src/main/java/com/example/offlinefirst/data/repository/ProductRepositoryImpl.kt` — `ProductDao.markProductForDeletion`
      Se invoca `markProductForDeletion` sobre `ProductDao`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `app/src/main/java/com/example/offlinefirst/data/repository/ProductRepositoryImpl.kt` — `ProductDao.getPendingProducts`
      Se invoca `getPendingProducts` sobre `ProductDao`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `app/src/main/java/com/example/offlinefirst/data/repository/ProductRepositoryImpl.kt` — `ProductDao.deletePendingProduct`
      Se invoca `deletePendingProduct` sobre `ProductDao`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `app/src/main/java/com/example/offlinefirst/data/repository/PurchaseRepositoryImpl.kt` — `PurchaseHistory.toEntity`
      Se invoca `toEntity` sobre `PurchaseHistory`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `app/src/main/java/com/example/offlinefirst/data/repository/PurchaseRepositoryImpl.kt` — `PurchaseHistory.toDomain`
      Se invoca `toDomain` sobre `PurchaseHistory`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `app/src/main/java/com/example/offlinefirst/data/repository/PurchaseRepositoryImpl.kt` — `PurchaseHistory.toPendingEntity`
      Se invoca `toPendingEntity` sobre `PurchaseHistory`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `app/src/main/java/com/example/offlinefirst/data/repository/UserPreferencesRepositoryImpl.kt` — `UserPreferences.toEntity`
      Se invoca `toEntity` sobre `UserPreferences`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `app/src/main/java/com/example/offlinefirst/data/sync/ConflictResolver.kt` — `Product.copy`
      Se invoca `copy` sobre `Product`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `app/src/main/java/com/example/offlinefirst/data/repository/ProductRepositoryImpl.kt (actualizado)` — `Product.toEntity`
      Se invoca `toEntity` sobre `Product`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `app/src/main/java/com/example/offlinefirst/data/repository/ProductRepositoryImpl.kt (actualizado)` — `Product.toDomain`
      Se invoca `toDomain` sobre `Product`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `app/src/main/java/com/example/offlinefirst/data/repository/ProductRepositoryImpl.kt (actualizado)` — `Product.toPendingEntity`
      Se invoca `toPendingEntity` sobre `Product`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `app/src/main/java/com/example/offlinefirst/data/repository/ProductRepositoryImpl.kt (actualizado)` — `ProductDao.insertPendingProduct`
      Se invoca `insertPendingProduct` sobre `ProductDao`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `app/src/main/java/com/example/offlinefirst/data/repository/ProductRepositoryImpl.kt (actualizado)` — `ProductDao.markProductForDeletion`
      Se invoca `markProductForDeletion` sobre `ProductDao`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `app/src/main/java/com/example/offlinefirst/data/repository/ProductRepositoryImpl.kt (actualizado)` — `ProductDao.getPendingProducts`
      Se invoca `getPendingProducts` sobre `ProductDao`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `app/src/main/java/com/example/offlinefirst/data/repository/ProductRepositoryImpl.kt (actualizado)` — `ProductDao.deletePendingProduct`
      Se invoca `deletePendingProduct` sobre `ProductDao`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.

### Presentes (44)

- `build.gradle.kts`
- `app/build.gradle.kts`
- `app/src/main/java/com/example/offlinefirst/data/remote/dto/ProductDto.kt`
- `app/src/main/java/com/example/offlinefirst/data/remote/dto/PurchaseDto.kt`
- `app/src/main/java/com/example/offlinefirst/domain/model/Product.kt`
- `app/src/main/java/com/example/offlinefirst/domain/model/PurchaseHistory.kt`
- `app/src/main/java/com/example/offlinefirst/domain/model/UserPreferences.kt`
- `app/src/main/java/com/example/offlinefirst/domain/model/SyncStatus.kt`
- `app/src/main/java/com/example/offlinefirst/domain/repository/ProductRepository.kt`
- `app/src/main/java/com/example/offlinefirst/domain/repository/PurchaseRepository.kt`
- `app/src/main/java/com/example/offlinefirst/domain/repository/UserPreferencesRepository.kt`
- `app/src/main/java/com/example/offlinefirst/domain/usecase/ProductUseCases.kt`
- `app/src/main/java/com/example/offlinefirst/domain/usecase/PurchaseUseCases.kt`
- `app/src/main/java/com/example/offlinefirst/domain/usecase/UserPreferencesUseCases.kt`
- `app/src/main/java/com/example/offlinefirst/domain/usecase/SyncDataUseCase.kt`
- `app/src/main/AndroidManifest.xml`
- `app/src/main/java/com/example/offlinefirst/OfflineFirstApp.kt`
- `app/src/main/java/com/example/offlinefirst/data/local/entity/ProductEntity.kt`
- `app/src/main/java/com/example/offlinefirst/data/local/entity/PurchaseHistoryEntity.kt`
- `app/src/main/java/com/example/offlinefirst/data/local/entity/UserPreferencesEntity.kt`
- `app/src/main/java/com/example/offlinefirst/data/local/entity/SyncMetadataEntity.kt`
- `app/src/main/java/com/example/offlinefirst/data/local/dao/ProductDao.kt`
- `app/src/main/java/com/example/offlinefirst/data/local/dao/PurchaseHistoryDao.kt`
- `app/src/main/java/com/example/offlinefirst/data/local/dao/UserPreferencesDao.kt`
- `app/src/main/java/com/example/offlinefirst/data/local/database/OfflineFirstDatabase.kt`
- `app/src/main/java/com/example/offlinefirst/data/remote/api/OfflineFirstApiService.kt`
- `app/src/main/java/com/example/offlinefirst/data/repository/ProductRepositoryImpl.kt`
- `app/src/main/java/com/example/offlinefirst/data/repository/PurchaseRepositoryImpl.kt`
- `app/src/main/java/com/example/offlinefirst/data/repository/UserPreferencesRepositoryImpl.kt`
- `app/src/main/java/com/example/offlinefirst/data/sync/NetworkConnectivityManager.kt`
- `app/src/main/java/com/example/offlinefirst/data/sync/SyncWorker.kt`
- `app/src/main/java/com/example/offlinefirst/di/NetworkModule.kt`
- `app/src/main/java/com/example/offlinefirst/di/RepositoryModule.kt`
- `app/src/main/java/com/example/offlinefirst/util/Resource.kt`
- `app/src/main/java/com/example/offlinefirst/util/NetworkMonitor.kt`
- `app/src/main/res/values/strings.xml`
- `app/src/main/res/values/colors.xml`
- `app/src/main/java/com/example/offlinefirst/data/sync/ConflictResolver.kt`
- `app/src/main/java/com/example/offlinefirst/data/local/entity/Product.kt`
- `app/src/main/java/com/example/offlinefirst/data/local/dao/ProductDao.kt (actualizado con métodos faltantes)`
- `app/src/main/java/com/example/offlinefirst/data/remote/dto/PurchaseDto.kt (actualizado)`
- `app/src/main/java/com/example/offlinefirst/data/repository/ProductRepositoryImpl.kt (actualizado)`
- `app/src/main/java/com/example/offlinefirst/util/Resource.kt (agregando Loading, Success, Error como standalone)`
- `app/src/main/java/com/example/offlinefirst/di/NetworkModule.kt (actualizado sin BuildConfig)`

### Capas del patron declarado

Cada una tiene que existir como directorio real con al menos un archivo. Codigo plano en la raiz no satisface el patron.

- `app/src/main/java/com/example/offlinefirst/data/local/dao`
- `app/src/main/java/com/example/offlinefirst/data/local/entity`
- `app/src/main/java/com/example/offlinefirst/data/local/database`
- `app/src/main/java/com/example/offlinefirst/data/remote/api`
- `app/src/main/java/com/example/offlinefirst/data/remote/dto`
- `app/src/main/java/com/example/offlinefirst/data/repository`
- `app/src/main/java/com/example/offlinefirst/data/sync`
- `app/src/main/java/com/example/offlinefirst/domain/model`
- `app/src/main/java/com/example/offlinefirst/domain/repository`
- `app/src/main/java/com/example/offlinefirst/domain/usecase`
- `app/src/main/java/com/example/offlinefirst/presentation/ui/screens`
- `app/src/main/java/com/example/offlinefirst/presentation/ui/viewmodel`
- `app/src/main/java/com/example/offlinefirst/presentation/ui/components`
- `app/src/main/java/com/example/offlinefirst/di`
- `app/src/main/java/com/example/offlinefirst/util`
- `app/src/main/res/values`
- `app/src/main/res/layout`

## Verificacion

```bash
./gradlew assembleDebug
```

Ese comando pasando es la definicion de "terminado" para vos.

## Convenciones que tenes que respetar

- Un solo ecosistema: no declares librerias de otro lenguaje ni mezcles gestores de paquetes.
- Toda libreria que uses tiene que estar declarada en el manifiesto de dependencias.
- Todo import declarado tiene que usarse; todo tipo usado tiene que existir o venir de una dependencia declarada.
- El patron es **clean_architecture_hexagonal**: los contratos (interfaces, puertos) los define la capa interna y los implementa la externa, nunca al revés.
- Los archivos que crees llevan implementacion real, no stubs: sin `TODO`, sin cuerpos vacios, sin `// getters y setters`.

## Contexto del candidato

Sirve para calibrar el nivel del codigo, no para resolver las fases.

- Perfil: Chapter Movil, Especialidad Android, Tecnología Android, Senior
- Brecha que el reto ataca: Necesita fortalecer la practica de Android
- Mision: Liderar la iniciativa de persistencia local offline-first

---

*Generado por Challenge Generator — Pragma. `README.md` tiene el enunciado completo del reto para la persona. `PROMPT_MEJORA.md` es la variante para pegar en un chat, si se prefiere ese flujo.*
