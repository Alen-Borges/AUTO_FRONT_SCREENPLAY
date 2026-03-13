package com.automatizacion.steps;

import com.automatizacion.tasks.EnviarReporteDeIncidente;
import com.automatizacion.questions.MensajeExitoVisible;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.abilities.BrowseTheWeb;
import net.serenitybdd.screenplay.actors.OnStage;
import net.serenitybdd.screenplay.actors.OnlineCast;
import net.serenitybdd.annotations.Managed;
import net.serenitybdd.screenplay.actions.Open;
import org.openqa.selenium.WebDriver;
import static org.assertj.core.api.Assertions.assertThat;
import java.util.List;
import java.util.Map;

public class FormularioSteps {

    @Managed
    WebDriver driver;

    @Before
    public void prepararEscenario() {
        OnStage.setTheStage(new OnlineCast());
    }

    @Given("que el usuario está en la pantalla de reporte de incidentes")
    public void abrirFormulario() {
        OnStage.theActorCalled("Usuario").whoCan(BrowseTheWeb.with(driver));
        OnStage.theActorInTheSpotlight().attemptsTo(Open.url("http://localhost"));
    }

    @When("envía un reporte con los siguientes datos:")
    public void enviarReporte(DataTable dataTable) {
        List<Map<String, String>> data = dataTable.asMaps(String.class, String.class);
        Map<String, String> row = data.get(0);
        OnStage.theActorInTheSpotlight().attemptsTo(
            EnviarReporteDeIncidente.conDatos(
                row.get("email"),
                row.get("linea"),
                row.get("tipo"),
                row.get("descripcion")
            )
        );
    }

    @Then("el sistema debería confirmar que el reporte fue enviado exitosamente")
    public void verificarMensajeExito() {
        assertThat(
            OnStage.theActorInTheSpotlight().asksFor(MensajeExitoVisible.enPantalla())
        ).isTrue();
    }
}
