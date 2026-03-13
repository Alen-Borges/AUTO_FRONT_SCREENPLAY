package com.automatizacion.steps;

import com.automatizacion.tasks.FiltrarTicketsPorEstado;
import com.automatizacion.questions.TablaContieneTickets;
import com.automatizacion.questions.TodosLosTicketsTienenEstado;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import net.serenitybdd.screenplay.abilities.BrowseTheWeb;
import net.serenitybdd.screenplay.actors.OnStage;
import net.serenitybdd.screenplay.actors.OnlineCast;
import net.serenitybdd.annotations.Managed;
import net.serenitybdd.screenplay.actions.Open;
import org.openqa.selenium.WebDriver;
import static org.assertj.core.api.Assertions.assertThat;

public class TicketsSteps {

    @Managed
    WebDriver driver;

    @Before
    public void prepararEscenario() {
        OnStage.setTheStage(new OnlineCast());
    }

    @Given("que el usuario está en el dashboard de tickets")
    public void abrirDashboard() {
        OnStage.theActorCalled("Usuario").whoCan(BrowseTheWeb.with(driver));
        OnStage.theActorInTheSpotlight().attemptsTo(Open.url("http://localhost/dashboard"));
    }

    @Then("la tabla debería mostrar al menos un ticket")
    public void verificarTablaConDatos() {
        assertThat(
            OnStage.theActorInTheSpotlight().asksFor(TablaContieneTickets.conDatos())
        ).isTrue();
    }

    @When("filtra los tickets por estado {string}")
    public void filtrarPorEstado(String estado) {
        OnStage.theActorInTheSpotlight().attemptsTo(
            FiltrarTicketsPorEstado.porEstado(estado)
        );
    }

    @Then("solo deberían mostrarse tickets con estado {string}")
    public void verificarFiltroEstado(String estado) {
        assertThat(
            OnStage.theActorInTheSpotlight().asksFor(TodosLosTicketsTienenEstado.igualA(estado))
        ).isTrue();
    }
}
