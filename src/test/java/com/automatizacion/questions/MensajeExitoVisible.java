package com.automatizacion.questions;

import com.automatizacion.ui.FormularioUI;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;

public class MensajeExitoVisible implements Question<Boolean> {

    public static MensajeExitoVisible enPantalla() {
        return new MensajeExitoVisible();
    }

    @Override
    public Boolean answeredBy(Actor actor) {
        return FormularioUI.MENSAJE_EXITO.resolveFor(actor).isDisplayed();
    }
}
