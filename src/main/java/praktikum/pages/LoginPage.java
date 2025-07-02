package praktikum.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import praktikum.EnvConfig;

import java.time.Duration;

public class LoginPage {
    private final WebDriver driver;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    public LoginPage openPage() {
        driver.get(EnvConfig.BASE_URL + "/login");
        return this;
    }
    // кнопка "Войти"
    public static final By loginButton = By.
            xpath(".//button[text()='Войти']");

    // поле для ввода "Email"
    private final By inputEmail = By.xpath(".//label[text()='Email']/following-sibling::input");

    // поле для ввода "Пароль"
    private final By inputPassword = By.xpath(".//label[text()='Пароль']/following-sibling::input");

    // ссылка "Зарегистрироваться"
    public static final By registrationButton = By.
            xpath(".//a[text()='Зарегистрироваться']");

    // ссылка "Восстановить пароль"
    public static final By recoverPassButton = By.
            xpath(".//a[text()='Восстановить пароль']");



    @Step("Ожидание загрузки страницы")
    public LoginPage waitLoadingPage() {
        new WebDriverWait(driver, Duration.ofSeconds(EnvConfig.DEFAULT_TIMEOUT))
                .until(ExpectedConditions.visibilityOfElementLocated(recoverPassButton));
        return this;
    }

    @Step("Клик по кнопке 'Зарегистрироваться'")
    public void clickRegisterLink() {
        driver.findElement(registrationButton).click();
    }

    @Step("Ввод email")
    public LoginPage inputEmail(String email) {
        driver.findElement(inputEmail).sendKeys(email);
        return this;
    }

    @Step("Ввод пароля")
    public LoginPage inputPassword(String pass) {
        driver.findElement(inputPassword).sendKeys(pass);
        return this;
    }

    @Step("Клик по кнопке 'Войти'")
    public void clickEnterButton() {
        driver.findElement(loginButton).click();
    }
}
