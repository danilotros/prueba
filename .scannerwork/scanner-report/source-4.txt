package task;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.Tasks;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.actions.Enter;

import static ui.LoginPagina.*;

public class IngresarDatos implements Task {
    private String correo;
    private String contra;

    public IngresarDatos(String correo, String contra) {
        this.correo = correo;
        this.contra = contra;
    }

    public static IngresarDatos laPagina(String correo, String contra) {
        return Tasks.instrumented(IngresarDatos.class,correo,contra);
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                Click.on(CAMPO_CORREO),
                Enter.theValue(correo).into(CAMPO_CORREO),
                Click.on(CAMPO_CONTRA),
                Enter.theValue(contra).into(CAMPO_CONTRA),
                Click.on(BOTON_INGRESAR)
        );
    }
}