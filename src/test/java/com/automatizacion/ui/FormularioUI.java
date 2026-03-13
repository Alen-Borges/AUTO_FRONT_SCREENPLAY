package com.automatizacion.ui;

import net.serenitybdd.screenplay.targets.Target;

public class FormularioUI {

    public static final Target CAMPO_EMAIL =
        Target.the("campo email").located(org.openqa.selenium.By.id("email"));

    public static final Target CAMPO_NUMERO_LINEA =
        Target.the("campo número de línea").located(org.openqa.selenium.By.id("lineNumber"));

    public static final Target CAMPO_TIPO_INCIDENTE =
        Target.the("campo tipo de incidente").located(org.openqa.selenium.By.id("incidentType"));

    public static final Target CAMPO_DESCRIPCION =
        Target.the("campo descripción").located(org.openqa.selenium.By.id("description"));

    public static final Target BOTON_ENVIAR =
        Target.the("botón enviar reporte").located(org.openqa.selenium.By.cssSelector("button[type='submit']"));

    public static final Target MENSAJE_EXITO =
        Target.the("mensaje de éxito").located(org.openqa.selenium.By.xpath("//h2[contains(text(), '¡Reporte Enviado!')]"));
}
