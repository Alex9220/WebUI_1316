package lesson6;

import io.qameta.allure.Step;
import io.qameta.allure.Story;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;


@Story("Авторизация")
public class LoginBlock extends BaseView {

        public LoginBlock(WebDriver driver) {
            super(driver);
        }

        private final static String loginInputLocatorById = "loginform-username";
        @FindBy(id = loginInputLocatorById)
        private WebElement loginInput;

        @Step("Ввод логина")
        public LoginBlock fillLoginInput(String login) {
            webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.id(loginInputLocatorById)));
            loginInput.sendKeys(login);
            return this;
        }

        @FindBy(id = "loginform-password")
        private WebElement passwordInput;

        @Step("Ввод пароля")
        public LoginBlock fillPasswordInput(String password) {
            passwordInput.sendKeys(password);
            return this;
        }

        @FindBy(xpath = "//iframe[@title='reCAPTCHA']")
        private WebElement captchaFrame;

        @Step("Ввод капчи")
        public LoginBlock fillCaptchaFrame() {
            driver.switchTo().frame(driver.findElement(By.xpath("//iframe[@title='reCAPTCHA']")));
            driver.findElement(By.xpath("//div[@class='recaptcha-checkbox-border']")).click();
            driver.switchTo().parentFrame();
            return this;
        }

        @FindBy(xpath = "//*[@id=\"login_btn\"]")
        private WebElement sumbitLoginButton;

        @Step("Клик по кнопки авторизации")
        public MainPage submitLogin() {
//            webDriverWait.until(d -> d.findElement(By.id(loginInputLocatorById)).getAttribute("value").contains("@rambler.ru"));
            sumbitLoginButton.click();
            return new MainPage(driver);
        }
}


