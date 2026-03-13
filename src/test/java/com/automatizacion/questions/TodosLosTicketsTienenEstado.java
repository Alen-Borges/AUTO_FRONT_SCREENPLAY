package com.automatizacion.questions;

import com.automatizacion.ui.TicketsUI;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;

public class TodosLosTicketsTienenEstado implements Question<Boolean> {

    private final String estadoEsperado;

    private TodosLosTicketsTienenEstado(String estadoEsperado) {
        this.estadoEsperado = estadoEsperado;
    }

    public static TodosLosTicketsTienenEstado igualA(String estado) {
        return new TodosLosTicketsTienenEstado(estado);
    }

    @Override
    public Boolean answeredBy(Actor actor) {
        return TicketsUI.FILAS_TABLA.resolveAllFor(actor).stream()
            .allMatch(fila -> fila.findElement(
                org.openqa.selenium.By.cssSelector("td:nth-child(6) span"))
                .getText().equalsIgnoreCase(estadoEsperado));
    }
}
