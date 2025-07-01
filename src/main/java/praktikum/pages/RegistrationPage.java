package praktikum.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import praktikum.EnvConfig;

import java.time.Duration;

public class RegistrationPage {
    private final WebDriver driver;

    public RegistrationPage(WebDriver driver) {
        this.driver = driver;
    }

    public RegistrationPage openPage() {
        driver.get(EnvConfig.BASE_URL + "/register");
        return this;
    }


    // кнопка "Зарегистрироваться"
    public static final By REGISTER_BUTTON = By.
            xpath(".//button[text()='Зарегистрироваться']");

    // ссылка "Войти"
    public static final By ENTER_LINK = By.
            xpath(".//a[contains(@href, '/login')]");

    // поле "Имя"
    private static final By INPUT_NAME = By.xpath(".//label[text()='Имя']/following-sibling::input");

    // поле "Email"
    private static final By INPUT_EMAIL = By.xpath(".//label[text()='Email']/following-sibling::input");

    // поле "Пароль"
    private static final By INPUT_PASSWORD = By.xpath(".//label[text()='Пароль']/following-sibling::input");

    // текст "Некорректный пароль"
    private static final By WRONG_PASSWORD_TEXT = By.xpath(".//p[text()='Некорректный пароль']");


    @Step("Ожидание загрузки страницы")
    public RegistrationPage waitForLoadingPage() {
        new WebDriverWait(driver, Duration.ofSeconds(EnvConfig.DEFAULT_TIMEOUT))
                .until(ExpectedConditions.visibilityOfElementLocated(REGISTER_BUTTON));
        return this;
    }

    @Step("Ввод имени")
    public RegistrationPage inputName(String name) {
        driver.findElement(INPUT_NAME).sendKeys(name);
        return this;
    }

    @Step("Ввод email")
    public RegistrationPage inputEmail(String email) {
        driver.findElement(INPUT_EMAIL).sendKeys(email);
        return this;
    }

    @Step("Ввод пароля")
    public RegistrationPage inputPassword(String pass) {
        driver.findElement(INPUT_PASSWORD).sendKeys(pass);
        return this;
    }

    @Step("Клик по кнопке 'Зарегистрироваться'")
    public RegistrationPage clickRegisterButton() {
        driver.findElement(REGISTER_BUTTON).click();
        return this;
    }

    @Step("Проверка наличия текста 'Некорректный пароль'")
    public void checkWrongPasswordWarning() {
        driver.findElement(WRONG_PASSWORD_TEXT);
    }

    @Step("Клик по ссылке 'Войти'")
    public RegistrationPage clickEnterLink() {
        driver.findElement(ENTER_LINK).click();
        return this;
    }
}
