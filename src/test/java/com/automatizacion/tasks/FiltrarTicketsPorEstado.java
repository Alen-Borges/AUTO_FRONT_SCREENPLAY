package com.automatizacion.tasks;

import com.automatizacion.ui.TicketsUI;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.SelectFromOptions;

public class FiltrarTicketsPorEstado implements Task {

    private final String estado;

    private FiltrarTicketsPorEstado(String estado) {
        this.estado = estado;
    }

    public static FiltrarTicketsPorEstado porEstado(String estado) {
        return new FiltrarTicketsPorEstado(estado);
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
            SelectFromOptions.byVisibleText(estado).from(TicketsUI.FILTRO_ESTADO)
        );
    }
}
