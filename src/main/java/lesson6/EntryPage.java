package lesson6;

import io.qameta.allure.Step;
import io.qameta.allure.Story;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

@Story("Публикация записей")
public class EntryPage extends BaseView{
    public EntryPage(WebDriver driver) {
        super(driver);
    }
    private final static String postTitleLocatorById = "postTitle";
    @FindBy(id = postTitleLocatorById)
    private WebElement newPostTitle;

    public EntryPage createPostTitle(String newPost) {
        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.id(postTitleLocatorById)));
        newPostTitle.sendKeys(newPost);
        return this;
    }

    @FindBy(id = "message_ifr")
    private WebElement textFrame;

    @Step("Переключить в окно ввода текста")
    public EntryPage switchToTextFrame() {
        driver.switchTo().frame(textFrame);
        return this;
    }

    public EntryPage switchToParentFrame() {
        driver.switchTo().parentFrame();
        return this;
    }

    @FindBy(id = "tinymce")
    private WebElement insertText;

    @Step("Ввод текста")
    public EntryPage inputText (String text) {
        insertText.sendKeys(text);
        return this;
    }

    @FindBy(xpath = "//*[@id=\"rewrite\"]")
    WebElement publishButton;

    @Step("Клик на кнопку Опубликовать")
    public EntryPage clickPublishButton() {
        publishButton.click();
        return this;
    }


}
