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
    public static final By enterButton = By
            .xpath(".//button[text()='Войти в аккаунт']");

    //кнопка "Оформить заказ"
    public static final By orderButton = By
            .xpath(".//button[text()='Оформить заказ']");

    // кнопка "Личный кабинет"
    public static final By accountButton = By
            .xpath(".//p[text()='Личный Кабинет']");

    // вкладка "Булки"
    public static final By buns = By
            .xpath(".//span[text()='Булки']/ancestor::div[contains(@class, 'tab_tab')]");

    // вкладка "Соусы"
    public static final By sauces = By
            .xpath(".//span[text()='Соусы']/ancestor::div[contains(@class, 'tab_tab')]");

    // вкладка "Начинки"
    public static final By ingredients = By
            .xpath(".//span[text()='Начинки']/ancestor::div[contains(@class, 'tab_tab')]");

    // раздел "Булки" в списке ингредиентов
    public static final By bunsInList = By
            .xpath(".//h2[text()='Булки']");

    // раздел "Соусы" в списке ингредиентов
    public static final By saucesInList = By
            .xpath(".//h2[text()='Соусы']");

    // раздел "Начинки" в списке ингредиентов
    public static final By ingredientsInList = By
            .xpath(".//h2[text()='Начинки']");


    @Step("Ожидание загрузки главной страницы")
    public MainPage waitMainPage() {
        new WebDriverWait(driver, Duration.ofSeconds(EnvConfig.DEFAULT_TIMEOUT))
                .until(ExpectedConditions.visibilityOfElementLocated(enterButton));
        return this;
    }

    @Step("Ожидание загрузки страницы после авторизации пользователя")
    public MainPage waitMainPageAfterAuth() {
        new WebDriverWait(driver, Duration.ofSeconds(EnvConfig.DEFAULT_TIMEOUT))
                .until(ExpectedConditions.visibilityOfElementLocated(orderButton));
        return this;
    }

    @Step("Нажатие по кнопке 'Личный Кабинет'")
    public void clickAccountButton() {
        driver.findElement(accountButton).click();
    }

    @Step("Нажатие по кнопке 'Войти в аккаунт'")
    public void clickEnterAccountButton() {
        driver.findElement(enterButton).click();
    }

    @Step("Сохранение токенов в Local Storage")
    public MainPage saveTokensToLocalStorage(String refreshToken, String accessToken) {
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        String setRefreshToken = "window.localStorage.setItem('refreshToken', '" + refreshToken + "');";
        String setAccessToken = "window.localStorage.setItem('accessToken', '" + accessToken + "');";
        jsExecutor.executeScript(setRefreshToken);
        jsExecutor.executeScript(setAccessToken);
        return this;
    }

    @Step("Получение токенов из Local Storage")
    public String getTokenFromLocalStorage() {
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        String getToken = "return window.localStorage.getItem('accessToken');";
        return (String) jsExecutor.executeScript(getToken);
    }

    @Step("Перезагрузка страницы")
    public MainPage refresh() {
        driver.navigate().refresh();
        return this;
    }

    @Step("Нажатие по вкладке 'Булки'")
    public MainPage clickBunsTab() {
        driver.findElement(buns).click();
        return this;
    }

    @Step("Нажатие по вкладке 'Соусы'")
    public MainPage clickSaucesTab() {
        driver.findElement(sauces).click();
        return this;
    }

    @Step("Нажатие по вкладке 'Начинки'")
    public MainPage clickIngredientsTab() {
        driver.findElement(ingredients).click();
        return this;
    }

    @Step("Вкладка 'Булки' активна")
    public MainPage currentTabBuns() {
        new WebDriverWait(driver, Duration.ofSeconds(EnvConfig.DEFAULT_TIMEOUT))
                .until(ExpectedConditions.attributeContains(buns, "class", "current"));
        return this;
    }

    @Step("Вкладка 'Соусы' активна")
    public MainPage currentTabSauces() {
        new WebDriverWait(driver, Duration.ofSeconds(EnvConfig.DEFAULT_TIMEOUT))
                .until(ExpectedConditions.attributeContains(sauces, "class", "current"));
        return this;
    }

    @Step("Вкладка 'Начинки' активна")
    public MainPage currentTabIngredients() {
        new WebDriverWait(driver, Duration.ofSeconds(EnvConfig.DEFAULT_TIMEOUT))
                .until(ExpectedConditions.attributeContains(ingredients, "class", "current"));
        return this;
    }

    @Step("Прокрутка списка до Булок")
    public MainPage scrollToBuns() {
        new WebDriverWait(driver, Duration.ofSeconds(EnvConfig.DEFAULT_TIMEOUT))
                .until(driver -> driver.findElement(bunsInList).getRect().y < 300);
        return this;
    }

    @Step("Прокрутка списка до Соусов")
    public MainPage scrollToSauces() {
        new WebDriverWait(driver, Duration.ofSeconds(EnvConfig.DEFAULT_TIMEOUT))
                .until(driver -> driver.findElement(saucesInList).getRect().y < 300);
        return this;
    }

    @Step("Прокрутка списка до Начинок")
    public MainPage scrollToIngredients() {
        new WebDriverWait(driver, Duration.ofSeconds(EnvConfig.DEFAULT_TIMEOUT))
                .until(driver -> driver.findElement(ingredientsInList).getRect().y < 300);
        return this;
    }
}
