package lesson6;


import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;


//@Story("Дневники")
public class DiaryTest {

    WebDriver driver;
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
        driver = new ChromeDriver();
        mainPage = new MainPage(driver);
        loginBlock = new LoginBlock(driver);
        entryPage = new EntryPage(driver);
        myDiaryPage = new MyDiaryPage(driver);
        driver.get(BASE_URL);
    }

    @Test
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
        driver.quit();
    }
}
