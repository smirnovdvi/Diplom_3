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
import praktikum.pages.LoginPage;
import praktikum.pages.MainPage;

@DisplayName("Авторизация пользователя")
public class LoginTest {

    private static User user;
    private static final Request request = new Request();
    private static final Check check = new Check();

//    private static CreateUserRequestJson newUser;
    private static String accessToken;

    @ClassRule
    public static DriverRule driverRule = new DriverRule();

    @Before
    @Step("Создание пользователя и открытие страницы авторизации")
    public void createUserAndOpenLoginPage() {
        user = User.random();
        ValidatableResponse createUserResponse = request.create(user);
        check.successRequest(createUserResponse);
        accessToken = check.extractAccessToken(createUserResponse);
        new LoginPage(driverRule.getDriver())
                .openPage()
                .waitLoadingPage();
    }

    @After
    @Step("Удаление пользователя")
    public void deleteUser() {
        ValidatableResponse creationResponse = request.delete(accessToken);
        check.RequestSuccess(creationResponse);
        check.userRemoved(creationResponse);
        accessToken = null;
    }

    @Test
    @DisplayName("Авторизация пользователя")
    public void loginUser() {
        new LoginPage(driverRule.getDriver())
                .inputEmail(user.getEmail())
                .inputPassword(user.getPassword())
                .clickEnterButton();
        accessToken = new MainPage(driverRule.getDriver())  // перезапись токена, сохранённого при создании юзера
                .waitMainPageAfterAuth()
                .getTokenFromLocalStorage();
    }
}
