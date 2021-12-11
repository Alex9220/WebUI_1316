package Lesson3;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;


import java.time.Duration;


public class CreateCompany {
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

        //wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//div[@id='nav_menu']/div[5]/a/div"))));
        driver.findElement(By.xpath("//div[@id='nav_menu']/div[5]/a/div")).click();
        Thread.sleep(3000);
        driver.findElement(By.xpath("//div[@id='nav_menu']/div[5]/a/div")).click();
        //wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//a/span[2]"))));

        Thread.sleep(3000);
        driver.findElement(By.xpath("/html/body/div[6]/div/div/div/div[1]/div[3]/a/span[1]")).click();
        Thread.sleep(5000);
        //wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.id("person_n"))));
        driver.findElement(By.id("person_n")).sendKeys("Test4");
        driver.findElement(By.id("new_contact_n")).sendKeys("Mike Puls");
        driver.findElement(By.xpath("//*[@id=\"new_contact\"]/div[2]/div[1]/div[1]/div[1]/div[2]/div/div[1]/input")).sendKeys("987654321");
        driver.findElement(By.xpath("//*[@id=\"new_contact\"]/div[2]/div[1]/div[2]/div[1]/div[2]/div/div[1]/input")).sendKeys("email@email.em");
        driver.findElement(By.xpath("//*[@id=\"new_contact\"]/div[2]/div[1]/div[3]/div[2]/input")).sendKeys("Director");
        driver.findElement(By.xpath("/html/body/div[7]/div[1]/div[2]/button[1]")).click();
        Thread.sleep(5000);
        driver.quit();
    }

}
