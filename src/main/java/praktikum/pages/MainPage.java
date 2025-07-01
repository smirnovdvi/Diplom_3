package praktikum.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import praktikum.EnvConfig;

import java.time.Duration;

public class MainPage {

    private final WebDriver driver;

    public MainPage(WebDriver driver) {
        this.driver = driver;
    }

    @Step("Открытие главной страницы")
    public MainPage openPage() {
        driver.get(EnvConfig.BASE_URL);
        return this;
    }

    // кнопка "Войти в аккаунт"
    public static final By ENTER_ACCOUNT_BUTTON = By
            .xpath(".//button[text()='Войти в аккаунт']");

    public static final By CREATE_ORDER_BUTTON = By
            .xpath(".//button[text()='Оформить заказ']");

    // кнопка "Личный кабинет"
    public static final By ACCOUNT_BUTTON = By
            .xpath(".//a[contains(@href, '/account')]");

    // таб "Булки"
    public static final By TAB_BUNS = By
            .xpath(".//span[text()='Булки']/ancestor::div[contains(@class, 'tab_tab')]");

    // таб "Соусы"
    public static final By TAB_SAUCES = By
            .xpath(".//span[text()='Соусы']/ancestor::div[contains(@class, 'tab_tab')]");

    // таб "Начинки"
    public static final By TAB_INGREDIENTS = By
            .xpath(".//span[text()='Начинки']/ancestor::div[contains(@class, 'tab_tab')]");

    // текст "Булки" в конструкторе
    public static final By BUNS_TEXT = By
            .xpath(".//div[contains(@class, 'BurgerIngredients_ingredients__menuContainer')]" +
                    "/h2[text()='Булки']");

    // текст "Соусы" в конструкторе
    public static final By SAUCES_TEXT = By
            .xpath(".//div[contains(@class, 'BurgerIngredients_ingredients__menuContainer')]" +
                    "/h2[text()='Соусы']");

    // текст "Начинки" в конструкторе
    public static final By INGREDIENTS_TEXT = By
            .xpath(".//div[contains(@class, 'BurgerIngredients_ingredients__menuContainer')]" +
                    "/h2[text()='Начинки']");


    @Step("Ожидание загрузки страницы")
    public MainPage waitPage() {
        new WebDriverWait(driver, Duration.ofSeconds(EnvConfig.DEFAULT_TIMEOUT))
                .until(ExpectedConditions.visibilityOfElementLocated(ENTER_ACCOUNT_BUTTON));
        return this;
    }
//Проверить как работает с предыдущим методом
    @Step("Ожидание загрузки страницы (авторизованный пользователь)")
    public MainPage waitForLoadingPageAuthUser() {
        new WebDriverWait(driver, Duration.ofSeconds(EnvConfig.DEFAULT_TIMEOUT))
                .until(ExpectedConditions.visibilityOfElementLocated(CREATE_ORDER_BUTTON));
        return this;
    }

    @Step("Клик по кнопке 'Личный Кабинет'")
    public void clickAccountButton() {
        driver.findElement(ACCOUNT_BUTTON).click();
    }

    @Step("Клик по кнопке 'Войти в аккаунт'")
    public void clickEnterAccountButton() {
        driver.findElement(ENTER_ACCOUNT_BUTTON).click();
    }

    @Step("Запись в Local Storage токенов аутентификации")
    public MainPage setTokensToLocalStorage(String refreshToken, String accessToken) {
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        String setRefreshToken = "window.localStorage.setItem('refreshToken', '" + refreshToken + "');";
        String setAccessToken = "window.localStorage.setItem('accessToken', '" + accessToken + "');";
        jsExecutor.executeScript(setRefreshToken);
        jsExecutor.executeScript(setAccessToken);
        return this;
    }

    @Step("Получение из Local Storage токена аутентификации")
    public String getAccessTokenFromLocalStorage() {
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        String getToken = "return window.localStorage.getItem('accessToken');";
        return (String) jsExecutor.executeScript(getToken);
    }

    @Step("Перезагрузка страницы")
    public MainPage refresh() {
        driver.navigate().refresh();
        return this;
    }

    @Step("Клик по табу 'Булки'")
    public MainPage clickBunsTab() {
        driver.findElement(TAB_BUNS).click();
        return this;
    }

    @Step("Клик по табу 'Соусы'")
    public MainPage clickSaucesTab() {
        driver.findElement(TAB_SAUCES).click();
        return this;
    }

    @Step("Клик по табу 'Начинки'")
    public MainPage clickIngredientsTab() {
        driver.findElement(TAB_INGREDIENTS).click();
        return this;
    }

    @Step("Таб 'Булки' активный")
    public MainPage currentTabBuns() {
        new WebDriverWait(driver, Duration.ofSeconds(EnvConfig.DEFAULT_TIMEOUT))
                .until(ExpectedConditions.attributeContains(TAB_BUNS, "class", "current"));
        return this;
    }

    @Step("Таб 'Соусы' активный")
    public MainPage currentTabSauces() {
        new WebDriverWait(driver, Duration.ofSeconds(EnvConfig.DEFAULT_TIMEOUT))
                .until(ExpectedConditions.attributeContains(TAB_SAUCES, "class", "current"));
        return this;
    }

    @Step("Таб 'Начинки' активный")
    public MainPage currentTabIngredients() {
        new WebDriverWait(driver, Duration.ofSeconds(EnvConfig.DEFAULT_TIMEOUT))
                .until(ExpectedConditions.attributeContains(TAB_INGREDIENTS, "class", "current"));
        return this;
    }

    @Step("Скролл конструктора до Булок")
    public MainPage scrollToBuns() {
        new WebDriverWait(driver, Duration.ofSeconds(EnvConfig.DEFAULT_TIMEOUT))
                .until(driver -> driver.findElement(BUNS_TEXT).getRect().y < 300);
        return this;
    }

    @Step("Скролл конструктора до Соусов")
    public MainPage scrollToSauces() {
        new WebDriverWait(driver, Duration.ofSeconds(EnvConfig.DEFAULT_TIMEOUT))
                .until(driver -> driver.findElement(SAUCES_TEXT).getRect().y < 300);
        return this;
    }

    @Step("Скролл конструктора до Начинок")
    public MainPage scrollToIngredients() {
        new WebDriverWait(driver, Duration.ofSeconds(EnvConfig.DEFAULT_TIMEOUT))
                .until(driver -> driver.findElement(INGREDIENTS_TEXT).getRect().y < 300);
        return this;
    }
}
