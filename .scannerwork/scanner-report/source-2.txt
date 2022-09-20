package questions;


import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;
import net.serenitybdd.screenplay.questions.Text;
import net.serenitybdd.screenplay.questions.targets.TheTarget;


import javax.swing.*;

import static ui.PrincipalPagina.TEXTO_PRINCIAPAL;


public class Validar implements Question<Boolean> {

    private String texto;

    public Validar(String texto) {
        this.texto = texto;
    }

    public static Validar texto(String texto) {
        return new Validar(texto);
    }

    @Override
    public Boolean answeredBy(Actor actor) {
        return TheTarget.textOf(TEXTO_PRINCIAPAL).toString().equals(texto);

    }
}
