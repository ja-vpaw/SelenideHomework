package selenide.PO;

import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.page;

public class YandexMainPage {

    public YandexResultPage search(String query){
        $(By.name("text")).setValue(query).pressEnter();
        return page(YandexResultPage.class);
    }

}
