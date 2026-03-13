# PROYECTO: AUTO_FRONT_SCREENPLAY 🚀

Este proyecto es una suite de automatización de pruebas para el front-end de Atlas Fiber, migrada exitosamente del patrón **Page Object Model (POM)** al patrón **Screenplay** utilizando **Serenity BDD** y **Cucumber**.

## 🏗️ Arquitectura: Screenplay Pattern

El proyecto sigue los principios de Screenplay, centrando la automatización en el **Actor** y sus capacidades:

- **Actor**: Representa al usuario que realiza las acciones.
- **Tasks**: Acciones de alto nivel (ej. `EnviarReporteDeIncidente`).
- **Actions**: Interacciones granulares (Click, Enter, Select).
- **Questions**: Consultas sobre el estado del sistema (ej. `MensajeExitoVisible`).
- **UI (Targets)**: Definición de localizadores de elementos web de forma desacoplada.

## 🛠️ Tecnologías Utilizadas

- **Java 21**
- **Gradle**
- **Serenity BDD 4.0.12**
- **Cucumber (Gherkin)**
- **Selenium WebDriver**
- **AssertJ** (Aserciones fluidas)

## 📋 Requisitos Previos

- Java 21 instalado.
- Chromium/Chrome instalado (configurado en `/usr/bin/chromium`).

## 🚀 Ejecución de Pruebas

Para ejecutar la suite de pruebas completa y generar el reporte agregado:

```bash
./gradlew clean test aggregate --info
```

### 🏷️ Ejecutar por Etiquetas (Tags)

```bash
./gradlew test -Dcucumber.filter.tags="@reporte"
```

## 📊 Reportes de Pruebas

Al finalizar la ejecución, Serenity genera un reporte detallado e interactivo con capturas de pantalla de cada paso.

- **Ubicación del reporte**: `target/site/serenity/index.html`

Para abrir el reporte en Linux:
```bash
xdg-open target/site/serenity/index.html
```

## 📂 Estructura del Proyecto

```text
src/test/java/com/automatizacion/
├── runners/      # Lanzadores de JUnit (TestRunner)
├── steps/        # Definición de pasos de Cucumber con Actores
├── tasks/        # Clases de Tareas (Lógica de negocio)
├── questions/    # Consultas para verificaciones
├── ui/           # Objetos con los Targets (Mapeo de elementos)
resources/
└── features/     # Archivos .feature (Gherkin)
```

## ⚙️ Configuración

Toda la configuración técnica del proyecto se encuentra en:
- `src/test/resources/serenity.conf`: Configuración de drivers, ambiente y reportes.
- `build.gradle`: Gestión de dependencias y tareas de automatización.
