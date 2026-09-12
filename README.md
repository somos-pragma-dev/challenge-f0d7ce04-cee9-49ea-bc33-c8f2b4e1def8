# Implementación de persistencia local offline-first en Android

El equipo de desarrollo móvil necesita implementar una solución de persistencia local que opere en modo offline-first para asegurar que la aplicación Android pueda funcionar sin conexión y sincronizarse automáticamente cuando la conexión sea restablecida. La aplicación debe manejar la persistencia de datos de usuario, preferencias y contenido descargado. Los datos deben ser consistentes y estar disponibles sin importar el estado de la conexión. Los actores involucrados son el usuario final, el servicio de sincronización en la nube y el almacenamiento local del dispositivo. La aplicación debe soportar un throughput de 1 000 operaciones de lectura/escritura por segundo y una latencia máxima de 200 ms para operaciones locales. En caso de fallo de conexión, la aplicación debe queuear las operaciones y reintentar automáticamente la sincronización cada 5 minutos.

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

### Fase 1: Definición de requisitos y diseño del modelo de datos

**Objetivo:** Establecer los requisitos funcionales y no funcionales para la persistencia local y diseñar el modelo de datos.

**Tiempo estimado:** 3 días

**Instrucciones:**

- Identificar las entidades y relaciones que deben ser persistidas localmente.
- Definir las reglas de validación y consistencia para los datos persistidos.
- Establecer los umbrales de rendimiento y latencia para las operaciones de persistencia.

**Entregable:** Documento de diseño que describe el modelo de datos, las reglas de validación y los requisitos de rendimiento.

<details>
<summary>Pistas de conocimiento</summary>

- Considerar el uso de patrones de diseño para la gestión de datos locales.
- Evaluar la necesidad de mecanismos de versionado para los datos persistidos.

</details>

### Fase 2: Implementación de la capa de persistencia

**Objetivo:** Implementar la capa de persistencia local utilizando el modelo de datos diseñado.

**Tiempo estimado:** 5 días

**Instrucciones:**

- Implementar las operaciones CRUD para las entidades persistidas.
- Asegurar que las operaciones sean idempotentes y consistentes.
- Implementar mecanismos de queueing y reintento para operaciones en modo offline.

**Entregable:** Capa de persistencia local implementada y verificada.

<details>
<summary>Pistas de conocimiento</summary>

- Utilizar librerías de persistencia local adecuadas para Android.
- Implementar estrategias de queueing y reintento eficientes.

</details>

### Fase 3: Integración y sincronización con servicio en la nube

**Objetivo:** Integrar la persistencia local con el servicio de sincronización en la nube y asegurar la sincronización automática de datos.

**Tiempo estimado:** 4 días

**Instrucciones:**

- Implementar la lógica de sincronización entre la persistencia local y el servicio en la nube.
- Asegurar que los datos se sincronizan automáticamente cuando la conexión sea restablecida.
- Manejar conflictos de sincronización y asegurar la consistencia de los datos.

**Entregable:** Solución de persistencia local integrada con el servicio de sincronización en la nube y sincronización automática de datos implementada.

<details>
<summary>Pistas de conocimiento</summary>

- Utilizar estrategias de sincronización incremental para minimizar el consumo de datos.
- Implementar mecanismos de resolución de conflictos de sincronización.

</details>

## Dimensiones Evaluadas

- **queEs**: ¿Qué es la persistencia local offline-first y por qué es importante para una aplicación Android?
- **paraQueSirve**: ¿Para qué sirve la integración de la persistencia local con el servicio de sincronización en la nube?
- **comoSeUsa**: ¿Cómo se utiliza la persistencia local para asegurar la disponibilidad de datos sin conexión?
- **erroresComunes**: ¿Cuáles son los errores comunes al implementar una solución de persistencia local offline-first?
- **queDecisionesImplica**: ¿Qué decisiones implica la elección de estrategias de sincronización y resolución de conflictos?

## Criterios de Evaluacion

- Definición clara de requisitos y diseño del modelo de datos.
- Implementación correcta de la capa de persistencia local.
- Integración efectiva con el servicio de sincronización en la nube.
- Manejo adecuado de conflictos de sincronización y aseguramiento de la consistencia de datos.

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
