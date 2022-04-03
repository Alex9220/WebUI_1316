package lesson6.Logger;


import io.qameta.allure.Allure;
import org.openqa.selenium.*;
import org.openqa.selenium.support.events.WebDriverListener;

import java.io.ByteArrayInputStream;

public class CustomLogger implements WebDriverListener {
    public void beforeClick(WebElement element) {
         Allure.step("Кликаем на элемент с текстом: " + element.getText());
    }

    public void afterFindElement(WebDriver driver, By locator, WebElement result) {
        Allure.addAttachment("Скриншот страницы ",
                new ByteArrayInputStream(((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES)));
    }
}


