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

    // кнопка "Зарегистрироваться"
    public static final By registrationButton = By.
            xpath(".//button[text()='Зарегистрироваться']");

    // поле ввода "Имя"
    private static final By inputName = By.xpath(".//label[text()='Имя']/following-sibling::input");

    // поле ввода "Email"
    private static final By inputEmail = By.xpath(".//label[text()='Email']/following-sibling::input");

    // поле ввода "Пароль"
    private static final By inputPassword = By.xpath(".//label[text()='Пароль']/following-sibling::input");

    // Сообщение "Некорректный пароль"
    private static final By wrongPassword = By.xpath(".//p[text()='Некорректный пароль']");


    @Step("Ожидание загрузки страницы")
    public RegistrationPage waitLoadingPage() {
        new WebDriverWait(driver, Duration.ofSeconds(EnvConfig.DEFAULT_TIMEOUT))
                .until(ExpectedConditions.visibilityOfElementLocated(registrationButton));
        return this;
    }

    @Step("Ввод имени")
    public RegistrationPage inputName(String name) {
        driver.findElement(inputName).sendKeys(name);
        return this;
    }

    @Step("Ввод email")
    public RegistrationPage inputEmail(String email) {
        driver.findElement(inputEmail).sendKeys(email);
        return this;
    }

    @Step("Ввод пароля")
    public RegistrationPage inputPassword(String pass) {
        driver.findElement(inputPassword).sendKeys(pass);
        return this;
    }

    @Step("Нажатие по кнопке 'Зарегистрироваться'")
    public RegistrationPage clickRegistrationButton() {
        driver.findElement(registrationButton).click();
        return this;
    }

    @Step("Проверка наличия ошибки 'Некорректный пароль'")
    public void checkWrongPassword() {
        driver.findElement(wrongPassword);
    }
}
