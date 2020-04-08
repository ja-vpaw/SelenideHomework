package selenide.test;

import io.qameta.allure.Description;
import io.qameta.allure.Step;
import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import selenide.CustomUtils;
import selenide.PO.OpenMainPage;
import selenide.PO.YandexResultPage;

import java.util.HashMap;
import java.util.Map;

public class Steps {

    @Step
    @Description("Поиск должен содержать {url}")
    public static void checkContainsLink(YandexResultPage page, String url){
        Assert.assertTrue("URL not found",
                page.results().stream().anyMatch(x -> x.$(By.xpath(".//a")).attr("href").contains(url))
        );
        CustomUtils.getScreen();
    }

    @Step
    @Description("Переход по {url}")
    public static OpenMainPage goUrl(YandexResultPage page, String url){
        OpenMainPage openMainPage = new OpenMainPage();
        Assert.assertNotNull("did not go to the url: '"+url+"'",
                openMainPage = page.goByLink(url)
        );
        CustomUtils.getScreen();
        return openMainPage;
    }

    @Step
    @Description("Проверка курса валюты ")
    public static void checkOpenCourse(OpenMainPage page, String nameMoney){
        Map<String,Float> courseMoney = new HashMap<>();
        courseMoney.put(nameMoney +" Покупка", page.getCourse(nameMoney, "Покупка"));
        courseMoney.put(nameMoney +" Продажа", page.getCourse(nameMoney,"Продажа"));

        System.out.println(courseMoney);

        Assertions.assertTrue(courseMoney.get(nameMoney +" Покупка")<courseMoney.get(nameMoney +" Продажа"),
                                          "Курс покупки " + nameMoney + " не меньше курса продажи");
        CustomUtils.getScreen(page.getExchangeRates());
    }

}

