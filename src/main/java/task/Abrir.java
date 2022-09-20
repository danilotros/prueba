package task;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.Tasks;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.actions.Open;
import ui.UtestPagina;

import static ui.UtestPagina.BOTON_LOGIN;

public class Abrir implements Task {
    private UtestPagina ustesPagina;

    public static Abrir laPagina() {
        return Tasks.instrumented(Abrir.class);
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                Open.browserOn(ustesPagina),
                Click.on(BOTON_LOGIN)
        );

    }
}