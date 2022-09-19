package ui;

import net.serenitybdd.screenplay.targets.Target;
import org.openqa.selenium.By;

public class PrincipalPagina {
    public static final Target TEXTO_PRINCIAPAL=Target.the("texto del label").located(By.xpath("//dashboard-card-activity//h2[@class='dashboard-card__heading' ]"));
}