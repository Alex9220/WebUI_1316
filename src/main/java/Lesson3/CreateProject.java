package Lesson3;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;

import java.time.Duration;

public class CreateProject {
    public static void main(String[] args) throws InterruptedException {
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        //заблокировать нотификации
        options.addArguments("--disable-notifications");

        WebDriver driver = new ChromeDriver(options);
        Wait<WebDriver> wait = new FluentWait<>(driver)
                .withTimeout(Duration.ofSeconds(10)) //тут максимальное время ожидания
                .pollingEvery(Duration.ofMillis(1000)) //тут как часто опрашиваем
                .ignoring(NoSuchElementException.class);

        driver.get("https://turtle1mailru.amocrm.ru/");

        driver.findElement(By.id("session_end_login")).sendKeys("turtle1@mail.ru");
        driver.findElement(By.id("password")).sendKeys("x2iy8Rs9");
        driver.findElement(By.id("auth_submit")).click();


        Thread.sleep(5000);

        driver.findElement(By.xpath("/html/body/div[1]/div[5]/div/div/div[2]/a/div[1]")).click();
        Thread.sleep(3000);
        driver.findElement(By.xpath("/html/body/div[1]/div[5]/div/div/div[2]/a/div[1]")).click();

        Thread.sleep(3000);
        driver.findElement(By.xpath("//*[@id=\"pipeline_items__list_44573464\"]/div[1]/div[1]")).click();
        Thread.sleep(5000);

        driver.findElement(By.id("fieldname")).sendKeys("Test3");
        driver.findElement(By.id("price")).sendKeys("25");
        driver.findElement(By.xpath("//input[@id='1_fieldname']")).sendKeys("Князь Игорь");
        driver.findElement(By.xpath("(//input[@name=''])[7]")).sendKeys("123456789");
        driver.findElement(By.xpath("//div[@id='1_49735']/div/input")).sendKeys("mylo@mylo.em");
        driver.findElement(By.xpath("/html/body/div[6]/div/div/div/div[2]/div[2]/div/div/div[2]/div[2]/div[1]/div[2]/div/div/div[4]/div[1]/div/input")).sendKeys("Test3");;
        driver.findElement(By.xpath("/html/body/div[6]/div/div/div/div[2]/div[2]/div/div/div[2]/div[2]/div[1]/div[2]/div/div/div[4]/div[2]/textarea")).sendKeys("Moscow");
        driver.findElement(By.id("quick_add_form_button")).click();

        Thread.sleep(5000);
        driver.quit();
    }
}

