package selenide.PO;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;

public class OpenMainPage {
    private String selectorExchangeRates = "//*[@class='main-page-exchange main-page-info__card']";

    public SelenideElement getExchangeRates() {
        return $(By.xpath(selectorExchangeRates));
    }

    public float getCourse(String nameMoney, String typeOperation){

        ElementsCollection trHeaders = $$(By.xpath("//*[@class='main-page-exchange main-page-info__card']//tbody/tr[contains(@class,'header')]/td"));
        ElementsCollection trRows = $$(By.xpath("//*[@class='main-page-exchange main-page-info__card']//tbody/tr[contains(@class,'row')]"));
        SelenideElement elemPrice = null;

        for(int i=0;i<trHeaders.size();++i){
            if(trHeaders.get(i).getText().equals(typeOperation)){
                elemPrice=trRows.find(matchText(nameMoney)).$$(By.xpath("./td")).get(i);
                break;
            }
        }

        return Float.parseFloat(elemPrice.getText().replace(",","."));
    }

}
