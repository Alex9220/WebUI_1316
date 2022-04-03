package lesson6;

import io.qameta.allure.Step;
import io.qameta.allure.Story;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

@Story("Основная страница")
public class MainPage extends BaseView {

    @FindBy(xpath = "//*[@id=\"navbar_user-collapse\"]/ul[1]/li[1]/a")
    WebElement loginButton;

    @Step("Клик на кнопку Логин")
    public MainPage clickLoginButton() {
        loginButton.click();
        return this;
    }


    private final static String newEntry = "//a[@title='Новая запись']";
    @FindBy(xpath = newEntry)
    private WebElement createNewEntry;

    @Step("Клик на кнопку Новая запись")
    public EntryPage createNewEntry() {
        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(newEntry)));
        createNewEntry.click();
        return new EntryPage(driver);
    }


    private final static String myDiary = "//a[@title='Мой дневник']";
    @FindBy(xpath = myDiary)
    private WebElement clickOpenMyDiary;

    @Step("Клик на кнопку Мой дневник")
    public MyDiaryPage clickOpenMyDiary() {
        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(myDiary)));
        clickOpenMyDiary.click();
        return new MyDiaryPage(driver);
    }

    public MainPage(WebDriver driver) {
        super(driver);
    }
}
