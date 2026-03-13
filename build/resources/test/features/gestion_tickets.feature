Feature: Gestión de Tickets de Soporte

  @reporte
  Scenario: Reporte de incidente
    Given que el usuario está en la pantalla de reporte de incidentes
    When envía un reporte con los siguientes datos:
      | email             | linea     | tipo | descripcion                   |
      | usuario@email.com | 123456789 | Otro | El servicio de internet es lento |
    Then el sistema debería confirmar que el reporte fue enviado exitosamente

  @dashboard
  Scenario: Visualizar tickets en dashboard
    Given que el usuario está en el dashboard de tickets
    Then la tabla debería mostrar al menos un ticket

  @filtro
  Scenario: Filtrar tickets por estado
    Given que el usuario está en el dashboard de tickets
    When filtra los tickets por estado "Recibida"
    Then solo deberían mostrarse tickets con estado "Recibida"
