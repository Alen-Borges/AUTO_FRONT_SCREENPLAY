# 🚀 Resumen del Proyecto: Gestión de Quejas ISP (Contexto para QA)

Este documento sirve como contexto técnico para agentes de IA encargados de realizar pruebas de calidad (QA), asegurando el entendimiento de la arquitectura, flujos de datos y responsabilidades del sistema.

---

## 🏗️ Arquitectura del Sistema
El sistema es una plataforma distribuida orientada a eventos, organizada en microservicios independientes que se comunican de forma asíncrona.

### 🧩 Componentes y Responsabilidades
1.  **Frontend (React + Vite)**: 
    *   Interfaz para usuarios finales (reporte de quejas).
    *   Dashboard administrativo para visualización de métricas en tiempo real.
2.  **Producer Service (Express API - Puerto 3000)**:
    *   Punto de entrada de las quejas.
    *   Realiza validaciones de esquema (linea, email, tipo).
    *   Publica mensajes en la cola de RabbitMQ.
3.  **RabbitMQ (Message Broker)**:
    *   Gestiona la cola `complaints_queue`.
    *   Actúa como buffer entre la recepción y el procesamiento.
4.  **Consumer Service (Worker Node.js - Puerto 3001)**:
    *   Procesa las quejas de forma asíncrona.
    *   **Lógica de Negocio**: Calcula la prioridad del ticket usando el patrón *Strategy*.
    *   **Resiliencia**: Maneja reintentos (3 intentos) y deriva fallos persistentes a una **Dead Letter Queue (DLQ)**.
5.  **Reports-Query Service (Express API - Puerto 4000)**:
    *   Especializado en lectura (Separación de responsabilidades de lectura/escritura).
    *   Expone endpoints para listado paginado, filtrado y métricas agregadas.
6.  **PostgreSQL (Base de Datos)**:
    *   Persistencia única para los tickets procesados.

---

## 🛠️ Stack Tecnológico
*   **Lenguajes**: TypeScript (Estricto).
*   **Frameworks**: Express.js (Backend), React (Frontend).
*   **Infraestructura**: Docker, Docker Compose, RabbitMQ, PostgreSQL.
*   **Testing**: Vitest.

---

## 🚦 Flujo de la Información
1.  **Ingreso**: Queja recibida en `POST http://localhost:3000/complaints`.
2.  **Validación**: Si los datos son válidos, se retorna `202 Accepted`.
3.  **Mensajería**: El mensaje viaja por RabbitMQ.
4.  **Procesamiento**: El `Consumer` asigna prioridad (Alta para `NO_SERVICE`, Media para `SLOW_CONNECTION`, etc.).
5.  **Persistencia**: Se guarda en la DB con estado `RECEIVED`.
6.  **Visualización**: El Dashboard consulta `GET http://localhost:4000/api/tickets`.

---

## 🎯 Guía para Pruebas (QA Focus)

### Escenarios Críticos a Validar:
*   **Integridad de Datos**: Asegurar que los campos `priority` y `status` se asignen correctamente según las reglas de negocio en el Consumer.
*   **Manejo de Errores**: 
    *   Inyectar payloads inválidos al Producer (debe responder `400`).
    *   Simular caída de RabbitMQ (el Producer debe responder `503`).
    *   Simular mensajes corruptos en la cola (deben terminar en la DLQ).
*   **Rendimiento**: Los endpoints del servicio de reportes deben responder en **< 500ms** (RNF-01).
*   **Paginación y Filtros**: Validar que el filtrado por fecha, prioridad y estado en el puerto `4000` sea preciso.
*   **Consistencia**: Confirmar que una queja "enviada" realmente llegue a la base de datos tras pasar por el worker.

### Contratos de API Principales:
*   `POST /complaints`: `{ "lineNumber": string, "email": string, "incidentType": string, "description": string }`
*   `GET /api/tickets`: Query params: `page`, `pageSize`, `status`, `priority`, `type`, `dateFrom`, `dateTo`, `sortBy`, `sortOrder`.
*   `GET /api/tickets/metrics`: Resumen de totales por estado y prioridad.

---

**Estado del Proyecto**: Fase de Implementación Final.  
**Cobertura Requerida**: 100% en servicios de backend.
