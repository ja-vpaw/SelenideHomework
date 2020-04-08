package selenide.test;

import com.codeborne.selenide.Configuration;
import io.qameta.allure.Description;
import org.aspectj.apache.bcel.classfile.Module;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import selenide.PO.OpenMainPage;
import selenide.PO.YandexMainPage;
import selenide.PO.YandexResultPage;

import java.util.HashMap;
import java.util.Map;

import static com.codeborne.selenide.Selenide.*;

public class TestsHome {

    @BeforeEach
    public void option(){
        Configuration.timeout = 6000;
        Configuration.browser="chrome";
        Configuration.startMaximized=true;

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-extensions");

        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability(ChromeOptions.CAPABILITY, options);
        Configuration.browserCapabilities = capabilities;

    }

    @Test
    @Description(value = "Тест Open.ru обмена валюты с шагами")
    public void TestExchangeOpen(){
        YandexMainPage yandexPage = open("https://www.yandex.ru/", YandexMainPage.class);
        YandexResultPage resultPage = yandexPage.search("Открытие");
        Steps.checkContainsLink(resultPage, "https://www.open.ru");

        OpenMainPage openPage = Steps.goUrl(resultPage,"https://www.open.ru");

        Steps.checkOpenCourse(openPage, "USD");
        Steps.checkOpenCourse(openPage, "EUR");
    }
}
