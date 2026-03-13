package com.automatizacion.questions;

import com.automatizacion.ui.TicketsUI;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;

public class TablaContieneTickets implements Question<Boolean> {

    public static TablaContieneTickets conDatos() {
        return new TablaContieneTickets();
    }

    @Override
    public Boolean answeredBy(Actor actor) {
        return TicketsUI.FILAS_TABLA.resolveAllFor(actor).size() > 0;
    }
}
