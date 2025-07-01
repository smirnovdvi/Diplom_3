import io.qameta.allure.Step;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import org.junit.After;
import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Test;
import praktikum.Check;
import praktikum.DriverRule;
import praktikum.Request;
import praktikum.User;
import praktikum.pages.AccountPage;
import praktikum.pages.LoginPage;
import praktikum.pages.MainPage;
import praktikum.pages.RegistrationPage;


@DisplayName("Переходы по страницам")
public class AuthTest {
    private static User user;
    private static final Request respon = new Request();
    private static final Check check = new Check();

    public static String accessToken;
    public static String refreshToken;

    @ClassRule
    public static DriverRule driverRule = new DriverRule();

    @Before
    @Step("Создание пользователя через API, логин и авторизация")
    public void createUserAndOpenLoginPage() {
        user = User.random();  // генерация json рандомного пользователя
        ValidatableResponse createUser = respon.create(user);  // создание пользователя через API,
        check.code201andSuccess(createUser);                  // чтобы не создавать через страницу регистрации
        refreshToken = check.extractRefreshToken(createUser);
        accessToken = check.extractAccessToken(createUser);

        new MainPage(driverRule.getDriver())
                .openPage()            // сначала открытие страницы без параметров, т.к. в Local Storage
                .waitPage()  // нельзя записывать, пока открыта страница data:,
                .setTokensToLocalStorage(refreshToken, accessToken)
                .refresh()
                .waitForLoadingPageAuthUser();
    }

    @After
    @Step("Удаление пользователя через API")
    public void deleteUser() {
        ValidatableResponse creationResponse = respon.delete(accessToken);
        check.code202andSuccess(creationResponse);
        check.userRemovedMessage(creationResponse);
        accessToken = null;
        refreshToken = null;
    }

    @Test
    @DisplayName("Переход в личный кабинет")
    public void toProfileFromMainPage() {
        new MainPage(driverRule.getDriver())
                .clickAccountButton();
        new RegistrationPage(driverRule.getDriver())
                .waitForLoadingPage();
    }

    @Test
    @DisplayName("Переход на главную страницу по кнопке 'Конструктор'")
    public void toMainPageFromProfileViaConstructorButton() {
        toProfileFromMainPage();
        new AccountPage(driverRule.getDriver())
                .clickConstructorButton();
        new MainPage(driverRule.getDriver())
                .waitForLoadingPageAuthUser();
    }

    @Test
    @DisplayName("Выход по кнопке 'Выход'")
    public void logoutViaLogoutButton() {
        toProfileFromMainPage();
        new AccountPage(driverRule.getDriver())
                .clickLogoutLink();
        new LoginPage(driverRule.getDriver())
                .waitForLoadingPage();
    }
    @Test
    @DisplayName("Переход на главную страницу по клику на логотип")
    public void toMainPageFromProfileViaLogo() {
        toProfileFromMainPage();
        new AccountPage(driverRule.getDriver())
                .clickLogo();
        new MainPage(driverRule.getDriver())
                .waitForLoadingPageAuthUser();
    }


}