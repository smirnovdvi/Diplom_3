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


@DisplayName("Переходы по страницам")
public class AuthTest {
    private static final Request request = new Request();
    private static final Check check = new Check();

    public static String accessToken;
    public static String refreshToken;

    @ClassRule
    public static DriverRule driverRule = new DriverRule();

    @Before
    @Step("Создание пользователя и авторизация")
    public void createUserAndAuth() {
        User user = User.random();
        ValidatableResponse createUser = request.create(user);
        check.successRequest(createUser);
        refreshToken = check.extractRefreshToken(createUser);
        accessToken = check.extractAccessToken(createUser);

        new MainPage(driverRule.getDriver())
                .openPage()
                .waitMainPage()
                .saveTokensToLocalStorage(refreshToken, accessToken)
                .refresh()
                .waitMainPageAfterAuth();
    }

    @After
    @Step("Удаление пользователя")
    public void deleteUser() {
        ValidatableResponse creationResponse = Request.delete(accessToken);
        check.RequestSuccess(creationResponse);
        check.userRemoved(creationResponse);
        accessToken = null;
        refreshToken = null;
    }

    @Test
    @DisplayName("Переход в личный кабинет")
    public void toAccountPage() {
        new MainPage(driverRule.getDriver())
                .clickAccountButton();
        new AccountPage(driverRule.getDriver())
                .waitLoadingPage();
    }

    @Test
    @DisplayName("Переход из личного кабинета на главную страницу по кнопке 'Конструктор'")
    public void toMainPage() {
        toAccountPage();
        new AccountPage(driverRule.getDriver())
                .clickConstructorButton();
        new MainPage(driverRule.getDriver())
                .waitMainPageAfterAuth();
    }

    @Test
    @DisplayName("Выход из личного кабинета по кнопке 'Выход'")
    public void logoutAfterLogoutButton() {
        toAccountPage();
        new AccountPage(driverRule.getDriver())
                .clickLogoutLink();
        new LoginPage(driverRule.getDriver())
                .waitLoadingPage();
    }
    @Test
    @DisplayName("Выход на главную страницу по нажатию на логотип")
    public void toMainPageAfterClickLogo() {
        toAccountPage();;
        new AccountPage(driverRule.getDriver())
                .clickLogo();
        new MainPage(driverRule.getDriver())
                .waitMainPageAfterAuth();
    }


}