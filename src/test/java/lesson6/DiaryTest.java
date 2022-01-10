package lesson6;


import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.Description;
import io.qameta.allure.Story;
import lesson6.Logger.CustomLogger;
import io.qameta.allure.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.logging.LogEntries;
import org.openqa.selenium.logging.LogEntry;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.support.events.EventFiringDecorator;
import org.openqa.selenium.support.events.EventFiringWebDriver;

import java.util.Iterator;


@Story("Дневники")
public class DiaryTest {

    WebDriver driver;
    EventFiringWebDriver eventFiringWebDriver;
    MainPage mainPage;
    LoginBlock loginBlock;
    EntryPage entryPage;
    MyDiaryPage myDiaryPage;
    private final static String BASE_URL = "https://diary.ru/";
    String postTitle = "Новая запись1";

    @BeforeAll
    static void registerDriver() {
        WebDriverManager.chromedriver().setup();
    }

    @BeforeEach
    void setupDriver() {
        driver = new EventFiringDecorator(new CustomLogger()).decorate(new ChromeDriver());
        //driver = new ChromeDriver();
        mainPage = new MainPage(driver);
        loginBlock = new LoginBlock(driver);
        entryPage = new EntryPage(driver);
        myDiaryPage = new MyDiaryPage(driver);
        driver.get(BASE_URL);
    }

    @Test
    @Description("Тест добавления записи")
    void newEntryTest() throws InterruptedException {
        new MainPage(driver).clickLoginButton();

        new LoginBlock(driver)
                .fillLoginInput("Alex9220")
                .fillPasswordInput("5jY5YixghR")
                .fillCaptchaFrame()
                .submitLogin();
        Thread.sleep(10000);

        new MainPage(driver).createNewEntry();

        new EntryPage(driver)
                .createPostTitle(postTitle)
                .switchToTextFrame()
                .inputText("Новый текст")
                .switchToParentFrame()
                .clickPublishButton();
        Thread.sleep(3000);
    }

    @Test
    @Description("Тест добавления лайка")
    void likeEntryTest() throws InterruptedException {
        new MainPage(driver).clickLoginButton();

        new LoginBlock(driver)
                .fillLoginInput("Alex9220")
                .fillPasswordInput("5jY5YixghR")
                .fillCaptchaFrame()
                .submitLogin();
        Thread.sleep(10000);

        new  MainPage(driver).clickOpenMyDiary();

        new MyDiaryPage(driver).
                pressLikePost();
        Thread.sleep(3000);
    }

    @Test
    @Description("Тест удаления записи")
    void deleteEntryTest() throws InterruptedException {
        new MainPage(driver).clickLoginButton();

        new LoginBlock(driver)
                .fillLoginInput("Alex9220")
                .fillPasswordInput("5jY5YixghR")
                .fillCaptchaFrame()
                .submitLogin();
        Thread.sleep(10000);

        new MainPage(driver)
                .clickOpenMyDiary()
                .clickDeletePost()
                .clickConfirmDelete();
            }

    @AfterEach
    void tearDown() {
        LogEntries logs = driver.manage().logs().get(LogType.BROWSER);
        Iterator<LogEntry> iterator = logs.iterator();

        while (iterator.hasNext()) {
            Allure.addAttachment("Элемент лога браузера", iterator.next().getMessage());
        }
        driver.quit();

    }
}
