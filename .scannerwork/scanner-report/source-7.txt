package ui;

import net.serenitybdd.core.annotations.findby.By;
import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.screenplay.targets.Target;
import net.thucydides.core.annotations.DefaultUrl;

@DefaultUrl("https://www.utest.com")
public class UtestPagina extends PageObject {
    public static final Target BOTON_LOGIN = Target.the("boton para ingresar al login de usuario").located(By.xpath("//ul[@class='nav navbar-nav']//a[text()='Log In']"));
}