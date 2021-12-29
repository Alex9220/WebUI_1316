package lesson6;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;



public class LoginBlock extends BaseView {

        public LoginBlock(WebDriver driver) {
            super(driver);
        }

    /*    @FindAll({
                @FindBy(xpath = "//a"),
                @FindBy(xpath = "//div")
        })
        private List<WebElement> testElement;

        @FindBys({
                @FindBy(xpath = "//div[.='test']"),
                @FindBy(xpath = "//a")
        })
        private List<WebElement> testElement1;*/


/*
        @FindBy(xpath = "//iframe[contains(@src,'rambler.ru/login')]")
        private WebElement loginFrame;

        public LoginBlock switchToLoginFrame() {
            driver.switchTo().frame(loginFrame);
            return this;
        }
*/

        private final static String loginInputLocatorById = "loginform-username";
        @FindBy(id = loginInputLocatorById)
        private WebElement loginInput;

        public LoginBlock fillLoginInput(String login) {
            webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.id(loginInputLocatorById)));
            loginInput.sendKeys(login);
            return this;
        }

        @FindBy(id = "loginform-password")
        private WebElement passwordInput;

        public LoginBlock fillPasswordInput(String password) {
            passwordInput.sendKeys(password);
            return this;
        }

    @FindBy(xpath = "//iframe[@title='reCAPTCHA']")
    private WebElement captchaFrame;

    public LoginBlock fillCaptchaFrame() {
        driver.switchTo().frame(driver.findElement(By.xpath("//iframe[@title='reCAPTCHA']")));
        driver.findElement(By.xpath("//div[@class='recaptcha-checkbox-border']")).click();
        driver.switchTo().parentFrame();
        return this;
    }

        @FindBy(xpath = "//*[@id=\"login_btn\"]")
        private WebElement sumbitLoginButton;

        public MainPage submitLogin() {
//            webDriverWait.until(d -> d.findElement(By.id(loginInputLocatorById)).getAttribute("value").contains("@rambler.ru"));
            sumbitLoginButton.click();
            return new MainPage(driver);
        }
    }


