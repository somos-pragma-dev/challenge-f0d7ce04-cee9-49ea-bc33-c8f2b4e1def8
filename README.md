# Implementación de persistencia local offline-first en una aplicación Android

La aplicación Android debe permitir a los usuarios acceder a sus datos sin conexión. Los datos se sincronizarán automáticamente cuando la conexión a Internet se restablezca. Los datos críticos incluyen la lista de productos, el historial de compras y las preferencias del usuario. La aplicación debe manejar adecuadamente los conflictos de datos que surjan durante la sincronización.

## Informacion General

| Campo | Valor |
|-------|-------|
| **Tema** | Persistencia local offline-first |
| **Nivel** | senior-l2 |
| **Tipo** | practical |
| **Tiempo estimado** | 2 semanas |

## Fases del Reto

### Fase 0: Configuración del Proyecto

**Objetivo:** Obtener el proyecto base funcional enviando el Código Base a un asistente de IA, que lo analizará, corregirá errores y generará un ZIP listo para usar.

**Tiempo estimado:** 15-30 minutos

**Instrucciones:**

- Asegúrate de tener instalado para ejecutar el proyecto: JDK 17+, Gradle 8+, IDE con soporte Java.
- Copia todo el contenido del campo **Código Base** de este reto — incluyendo el texto de instrucciones que aparece al inicio.
- Abre un asistente de IA (Claude en claude.ai, ChatGPT o Gemini — se recomienda Claude), pega el contenido copiado en el chat y envíalo.
- El asistente analizará los archivos, corregirá errores y generará un archivo ZIP descargable. Descárgalo y extráelo en la carpeta donde quieras trabajar.
- Ejecuta `gradle build` en la raíz. Si no hay errores, estás listo.

**Entregable:** El proyecto compila/arranca sin errores.

<details>
<summary>Pistas de conocimiento</summary>

- Copia el Código Base completo incluyendo el texto de instrucciones al inicio — esas instrucciones le indican al asistente exactamente qué hacer con los archivos.
- Si el asistente no genera el ZIP automáticamente al terminar el análisis, escríbele: "genera el ZIP ahora".
- Si el proyecto tiene errores al arrancar, comparte el mensaje de error con el mismo asistente para que lo corrija.

</details>

### Fase 1: Diseño del modelo de datos

**Objetivo:** Definir la estructura de los datos que se persistirán localmente.

**Tiempo estimado:** 3 días

**Instrucciones:**

- Identificar los datos críticos que deben estar disponibles sin conexión.
- Definir el esquema de la base de datos local.
- Establecer las relaciones entre las entidades.

**Entregable:** Esquema de la base de datos local con relaciones definidas.

<details>
<summary>Pistas de conocimiento</summary>

- Considera la normalización y la desnormalización de datos.
- Piensa en cómo manejarás las relaciones uno a muchos y muchos a muchos.

</details>

### Fase 2: Implementación de la persistencia local

**Objetivo:** Implementar la persistencia local de los datos identificados en la fase anterior.

**Tiempo estimado:** 5 días

**Instrucciones:**

- Crear las clases de modelo para los datos.
- Implementar las operaciones CRUD para los datos locales.
- Asegurar la consistencia de los datos al realizar operaciones.

**Entregable:** Código fuente que implementa la persistencia local de los datos.

<details>
<summary>Pistas de conocimiento</summary>

- Utiliza un ORM para simplificar las operaciones de base de datos.
- Considera el uso de transacciones para garantizar la consistencia de los datos.

</details>

### Fase 3: Sincronización de datos

**Objetivo:** Implementar la sincronización automática de datos cuando la conexión a Internet se restablezca.

**Tiempo estimado:** 4 días

**Instrucciones:**

- Detectar cambios en la conectividad de red.
- Sincronizar los datos locales con el servidor.
- Manejar conflictos de datos durante la sincronización.

**Entregable:** Código fuente que implementa la sincronización automática de datos.

<details>
<summary>Pistas de conocimiento</summary>

- Utiliza un servicio de trabajo para detectar cambios en la conectividad de red.
- Considera el uso de un algoritmo de resolución de conflictos para manejar conflictos de datos.

</details>

## Dimensiones Evaluadas

- **queEs**: ¿Qué es la persistencia local offline-first y por qué es importante para una aplicación Android?
- **paraQueSirve**: ¿Para qué sirve el modelo de datos en una aplicación Android con persistencia local?
- **comoSeUsa**: ¿Cómo se usa un ORM para simplificar las operaciones de base de datos en una aplicación Android?
- **erroresComunes**: ¿Cuáles son los errores comunes al implementar la persistencia local en una aplicación Android?
- **queDecisionesImplica**: ¿Qué decisiones implica la sincronización de datos en una aplicación Android con persistencia local?

## Criterios de Evaluacion

- Diseño del modelo de datos que permite la persistencia local offline-first.
- Implementación de las operaciones CRUD para los datos locales.
- Implementación de la sincronización automática de datos cuando la conexión a Internet se restablezca.
- Manejo adecuado de los conflictos de datos durante la sincronización.

## Como trabajar con un asistente de IA

Hay dos caminos, elegi uno:

- **AGENTS.md** (recomendado) — instrucciones nativas del repo. Abri esta carpeta con tu agente local (Claude Code, Cursor, Codex, Copilot, Gemini) y las carga solo. Sabe que archivos faltan y con que comando se verifica, y completa el scaffold escribiendo en disco.
- **PROMPT_MEJORA.md** — para copiar y pegar en un chat (claude.ai, ChatGPT). Devuelve un ZIP con el proyecto. Sirve si no tenes un agente en el IDE.

Ninguno de los dos resuelve las fases del reto: eso es tu trabajo.

## Verificacion

El proyecto esta listo para trabajar cuando este comando corre sin errores:

```bash
./gradlew assembleDebug
```

---

*Reto generado automaticamente por Challenge Generator - Pragma*
