package selenide.PO;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Condition.matchText;
import static com.codeborne.selenide.Condition.matchesText;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverRunner.url;

public class YandexResultPage {

    public ElementsCollection results(){
        return $$(By.xpath("//li[@class='serp-item']"));
    }


    public OpenMainPage goLink(String nameLink){

        SelenideElement resultLink = $$(By.xpath("//li[@class='serp-item']")).find(matchText(nameLink));

        System.out.println(resultLink.getText());

        resultLink.$(By.xpath(".//a[@href]")).click();

        switchTo().window(1);
        System.out.println(title());
        Assertions.assertTrue(title().contains(nameLink),"Не удалось переключится на вкладку: "+nameLink);

        return page(OpenMainPage.class);
    }

    public OpenMainPage goByLink(String link){

        SelenideElement openLink = results().stream()
                .filter(x ->x.$(By.xpath(".//a")).attr("href").contains(link)).findFirst().get();

        //System.out.println(openLink.getText());

        openLink.$(By.tagName("a")).click();

        switchTo().window(1);
        System.out.println(url());
        Assertions.assertTrue(url().contains(link),"Не удалось переключится на вкладку: "+link);

        return page(OpenMainPage.class);
    }

}
