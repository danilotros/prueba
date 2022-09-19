package stepdefinitions;


import io.cucumber.datatable.DataTable;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.java.es.Cuando;
import io.cucumber.java.es.Dado;
import io.cucumber.java.es.Entonces;
import net.serenitybdd.screenplay.GivenWhenThen;
import net.serenitybdd.screenplay.actors.OnStage;
import net.serenitybdd.screenplay.actors.OnlineCast;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import questions.Validar;
import task.Abrir;
import task.IngresarDatos;
import util.ExcelReader;
import javax.swing.*;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import static net.serenitybdd.screenplay.actors.OnStage.theActor;
import static net.serenitybdd.screenplay.actors.OnStage.theActorInTheSpotlight;
public class LoginStepDefinition {

    private Scenario scenario;
    @Before
    public void definirEscenario(Scenario scenario) {
        OnStage.setTheStage(new OnlineCast());
        this.scenario=scenario;
    }
    @Dado("^Ya esta en la plataforma$")
    public void yaEstaEnLaPlataforma() {
        theActor("Daniel").wasAbleTo(Abrir.laPagina());
    }

    @Cuando("^El ingrese las credenciales$")
    public void elIngreseLasCredenciales(DataTable datos) throws IOException, InvalidFormatException {
        ExcelReader reader = new ExcelReader("C:/Users/daniel.perea/Downloads/prueba.xlsx", "H");
        List<Map<String,String>> testData = reader.getData();
        theActorInTheSpotlight().attemptsTo(IngresarDatos.laPagina(testData.get(reader.findRow("contra")).get("correo"),testData.get(reader.findRow("contra")).get("contra")));
    }
    @Entonces("^El vera la pantalla principal$")
    public void elVeraLaPantallaPrincipal(List<String> respuesta) {theActorInTheSpotlight().should(GivenWhenThen.seeThat(Validar.texto("MY ACTIVITY")));}


}