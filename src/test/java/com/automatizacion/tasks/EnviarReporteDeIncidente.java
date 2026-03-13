package com.automatizacion.tasks;

import com.automatizacion.ui.FormularioUI;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.actions.Enter;

public class EnviarReporteDeIncidente implements Task {

    private final String email;
    private final String numeroLinea;
    private final String tipoIncidente;
    private final String descripcion;

    private EnviarReporteDeIncidente(String email, String numeroLinea,
                                      String tipoIncidente, String descripcion) {
        this.email = email;
        this.numeroLinea = numeroLinea;
        this.tipoIncidente = tipoIncidente;
        this.descripcion = descripcion;
    }

    public static EnviarReporteDeIncidente conDatos(String email, String numeroLinea,
                                                     String tipoIncidente, String descripcion) {
        return new EnviarReporteDeIncidente(email, numeroLinea, tipoIncidente, descripcion);
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
            Enter.theValue(email).into(FormularioUI.CAMPO_EMAIL),
            Enter.theValue(numeroLinea).into(FormularioUI.CAMPO_NUMERO_LINEA),
            net.serenitybdd.screenplay.actions.SelectFromOptions.byVisibleText(tipoIncidente).from(FormularioUI.CAMPO_TIPO_INCIDENTE),
            Enter.theValue(descripcion).into(FormularioUI.CAMPO_DESCRIPCION),
            Click.on(FormularioUI.BOTON_ENVIAR)
        );
    }
}
