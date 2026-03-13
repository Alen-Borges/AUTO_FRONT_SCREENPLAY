package com.automatizacion.ui;

import net.serenitybdd.screenplay.targets.Target;

public class TicketsUI {

    public static final Target FILAS_TABLA =
        Target.the("filas de la tabla de tickets").located(org.openqa.selenium.By.cssSelector("table tbody tr"));

    public static final Target FILTRO_ESTADO =
        Target.the("filtro de estado").located(org.openqa.selenium.By.id("filter-status"));

    public static final Target FILTRO_PRIORIDAD =
        Target.the("filtro de prioridad").located(org.openqa.selenium.By.id("filter-priority"));

    public static final Target ESTADO_EN_FILA =
        Target.the("estado en fila").located(org.openqa.selenium.By.cssSelector("table tbody tr td:nth-child(6) span"));
}
