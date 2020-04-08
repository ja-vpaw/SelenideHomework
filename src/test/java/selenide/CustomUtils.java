package selenide;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Attachment;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static com.codeborne.selenide.Selenide.actions;
import static com.codeborne.selenide.Selenide.screenshot;

public class CustomUtils {

    @Attachment
    public static byte[] getScreen(){
        screenshot("screen");
        try {
            return Files.readAllBytes(Paths.get("build/reports/tests","screen.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new byte[0];
    }

    @Attachment
    public static byte[] getScreen(SelenideElement element){
        actions().moveToElement(element).perform();

        screenshot("screen");
        try {
            return Files.readAllBytes(Paths.get("build/reports/tests","screen.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new byte[0];
    }


    public static void stopSec(int s) {
        try {
            Thread.sleep(s*1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
