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

    // поле "Email"
    private final By INPUT_EMAIL = By.xpath(".//label[text()='Email']/following-sibling::input");

    // поле "Пароль"
    private final By INPUT_PASSWORD = By.xpath(".//label[text()='Пароль']/following-sibling::input");

    // кнопка "Зарегистрироваться"
    public static final By REGISTER_BUTTON = By.
            xpath(".//a[contains(@href, '/register')]");

    // ссылка "Восстановить пароль"
    public static final By RECOVER_PASS_BUTTON = By.
            xpath(".//a[contains(@href, '/forgot-password')]");

    // кнопка "Войти"
    public static final By ENTER_BUTTON = By.
            xpath(".//button[text()='Войти']");


    @Step("Ожидание загрузки страницы")
    public LoginPage waitLoadingPage() {
        new WebDriverWait(driver, Duration.ofSeconds(EnvConfig.DEFAULT_TIMEOUT))
                .until(ExpectedConditions.visibilityOfElementLocated(RECOVER_PASS_BUTTON));
        return this;
    }

    @Step("Клик по кнопке 'Зарегистрироваться'")
    public void clickRegisterLink() {
        driver.findElement(REGISTER_BUTTON).click();
    }

    @Step("Ввод email")
    public LoginPage inputEmail(String email) {
        driver.findElement(INPUT_EMAIL).sendKeys(email);
        return this;
    }

    @Step("Ввод пароля")
    public LoginPage inputPassword(String pass) {
        driver.findElement(INPUT_PASSWORD).sendKeys(pass);
        return this;
    }

    @Step("Клик по кнопке 'Войти'")
    public void clickEnterButton() {
        driver.findElement(ENTER_BUTTON).click();
    }
}
