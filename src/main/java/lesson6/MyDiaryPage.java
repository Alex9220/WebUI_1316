package lesson6;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

public class MyDiaryPage extends BaseView{
    public MyDiaryPage(WebDriver driver) {
        super(driver);
    }

    private final static String likeLocatorByXpath = "//*[@class='addLike  lastLink post_likes']";
    @FindBy(xpath = likeLocatorByXpath)
    private WebElement newLikePost;

    public MyDiaryPage pressLikePost() {
        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(likeLocatorByXpath)));
        newLikePost.click();
        return this;
    }

    private final static String postButtom = "//a[text()='Новая запись1']/following-sibling::span//span[@class='i-cross']";

    @FindBy(xpath = postButtom)
    private WebElement delitePost;

    public MyDiaryPage clickDeletePost() {
        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(postButtom)));
        delitePost.click();
        return this;
    }

    private final static String modalButtomXpath = "//*[@class='btn btn-primary on confirm_delete_post']/ancestor::div[@class='fade modal in']";
    @FindBy(xpath = modalButtomXpath)
    private WebElement confirmDelete;

    public MyDiaryPage clickConfirmDelete() {
        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@class='fade modal in']")));
        confirmDelete.click();
        return this;
    }


}

