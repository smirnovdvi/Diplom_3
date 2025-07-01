package praktikum.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import praktikum.EnvConfig;

import java.time.Duration;

public class AccountPage {
    private final WebDriver driver;

    public AccountPage(WebDriver driver) {
        this.driver = driver;
    }

    // кнопка "Сохранить"
    public static final By SAVE_BUTTON = By
            .xpath(".//button[text()='Сохранить']");

    // кнопка "Конструктор"
    public static final By CONSTRUCTOR_BUTTON = By
            .xpath(".//p[text()='Конструктор']/ancestor::a[contains(@href, '/')]");

    // логотип
    public static final By LOGO = By
            .xpath(".//div[contains(@class,'AppHeader')]/a[contains(@href, '/')]");

    // ссылка "Выход"
    public static final By LOGOUT_LINK = By
            .xpath(".//button[text()='Выход']");


    @Step("Ожидание загрузки страницы")
    public AccountPage waitLoadingPage() {
        new WebDriverWait(driver, Duration.ofSeconds(EnvConfig.DEFAULT_TIMEOUT))
                .until(ExpectedConditions.visibilityOfElementLocated(SAVE_BUTTON));
        return this;
    }

    @Step("Клик по кнопке 'Конструктор'")
    public void clickConstructorButton() {
        driver.findElement(CONSTRUCTOR_BUTTON).click();
    }

    @Step("Клик по логотипу")
    public void clickLogo() {
        driver.findElement(LOGO).click();
    }

    @Step("Клик по кнопке 'Выйти'")
    public void clickLogoutLink() {
        driver.findElement(LOGOUT_LINK).click();
    }
}
