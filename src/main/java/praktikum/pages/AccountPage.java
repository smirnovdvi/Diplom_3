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
    public static final By saveButton = By
            .xpath(".//button[text()='Сохранить']");

    // кнопка "Конструктор"
    public static final By constructorButton = By
            .xpath(".//p[text()='Конструктор']");

    // кнопка "Выход"
    public static final By logoutButton = By
            .xpath(".//button[text()='Выход']");

    // логотип
    public static final By logo = By
            .xpath(".//div[contains(@class,'AppHeader')]");


    @Step("Ожидание загрузки страницы")
    public AccountPage waitLoadingPage() {
        new WebDriverWait(driver, Duration.ofSeconds(EnvConfig.DEFAULT_TIMEOUT))
                .until(ExpectedConditions.visibilityOfElementLocated(saveButton));
        return this;
    }

    @Step("Клик по кнопке 'Конструктор'")
    public void clickConstructorButton() {
        driver.findElement(constructorButton).click();
    }

    @Step("Клик по логотипу")
    public void clickLogo() {
        driver.findElement(logo).click();
    }

    @Step("Клик по кнопке 'Выйти'")
    public void clickLogoutLink() {
        driver.findElement(logoutButton).click();
    }
}
