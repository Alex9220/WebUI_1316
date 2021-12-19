package lesson5;


import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;



public class DiaryTests {
    WebDriver driver;
    WebDriverWait webDriverWait;
    Actions actions;
    private final static String BASE_URL = "https://diary.ru/";
    String postTitle = "Новая запись1";

    @BeforeAll
    static void registerDriver() {
        WebDriverManager.chromedriver().setup();
    }

    @BeforeEach
    void setupDriver() {
        driver = new ChromeDriver();
        webDriverWait = new WebDriverWait(driver, 5);
        actions = new Actions(driver);
        driver.get(BASE_URL);
    }

    @Test
    void newEntryTest() throws InterruptedException {
        login();

        webDriverWait.until(d -> d.findElements(
                By.xpath("//*[@id=\"writeThisDiary\"]/a/span[1]")).size() > 0);
        driver.findElement(By.xpath("//a[@title='Новая запись']")).click();
        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"postTitle\"]")));
        driver.findElement(By.id("postTitle")).sendKeys(postTitle);
        driver.switchTo().frame(driver.findElement(By.id("message_ifr")));
        driver.findElement(By.id("tinymce")).sendKeys("Новая запись");
        driver.switchTo().parentFrame();
        driver.findElement(By.xpath("//*[@id=\"rewrite\"]")).click();
        Thread.sleep(3000);
    }

    @Test
    void likeEntryTest() throws InterruptedException{
        login();

        webDriverWait.until(d -> d.findElements(
                By.xpath("//a[@title='Мой дневник']")).size() > 0);
        driver.findElement(By.xpath("//a[@title='Мой дневник']")).click();
        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"postsArea\"]/div[3]")));
        driver.findElement(By.xpath("//*[@class='addLike  lastLink post_likes']")).click();
        Thread.sleep(3000);

    }

    @Test
    void deleteEntryTest() throws InterruptedException {
        login();

        webDriverWait.until(d -> d.findElements(
                By.xpath("//a[@title='Мой дневник']")).size() > 0);
        driver.findElement(By.xpath("//a[@title='Мой дневник']")).click();
        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"postsArea\"]/div[3]")));
        driver.findElement(By.xpath(String.format("//a[text()='%s']/following-sibling::span//span[@class='i-cross']", postTitle))).click();
        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@class='fade modal in']")));
        driver.findElement(By.xpath("//*[@class='btn btn-primary on confirm_delete_post']/ancestor::div[@class='fade modal in']")).click();
        Thread.sleep(3000);

    }
        @AfterEach
    void tearDown() {
        driver.quit();
    }




    void login() throws InterruptedException {
        driver.findElement(By.xpath("//*[@id=\"navbar_user-collapse\"]/ul[1]/li[1]/a")).click();
        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("loginform-username")));
        driver.findElement(By.id("loginform-username")).sendKeys("Alex9220");
        driver.findElement(By.id("loginform-password")).sendKeys("5jY5YixghR");
        driver.switchTo().frame(driver.findElement(By.xpath("//iframe[@title='reCAPTCHA']")));
        driver.findElement(By.xpath("//div[@class='recaptcha-checkbox-border']")).click();
        Thread.sleep(10000);
        driver.switchTo().parentFrame();
        driver.findElement(By.xpath("//*[@id=\"login_btn\"]")).click();
        //Thread.sleep(10000);
    }
}
