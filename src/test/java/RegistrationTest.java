import io.qameta.allure.Step;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import org.junit.After;
import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Test;
import praktikum.*;
import praktikum.pages.LoginPage;
import praktikum.pages.MainPage;
import praktikum.pages.RegistrationPage;

@DisplayName("Регистрация пользователя")
public class RegistrationTest {

    private User user;


    //private static final Request request = new Request();
    private static final Check check = new Check();
    //private static final Credentials creds = new Credentials();
    @ClassRule
    public static DriverRule driverRule = new DriverRule();

    @Before
    @Step("Создание и регистрация пользователя")
    public void openPageAndNavigate() {
        user = User.random();
        new MainPage(driverRule.getDriver())
                .openPage()
                .waitMainPage()
                .clickEnterAccountButton();
        new LoginPage(driverRule.getDriver())
                .waitLoadingPage()
                .clickRegisterLink();
    }

    @After
    @Step("Удаление пользователя")
    public void deleteUser() {
        String accessToken;
        var newLogin = Credentials.fromUser(user);
        ValidatableResponse loginUserResponse = Request.login(newLogin);
        accessToken = check.extractAccessToken(loginUserResponse);

        if (accessToken != null) {
            ValidatableResponse creationResponse = Request.delete(accessToken);
            check.RequestSuccess(creationResponse);
            check.userRemoved(creationResponse);
        }
    }

    @Test
    @DisplayName("Регистрация пользователя")
    public void registration() {
        var creds = Credentials.fromUser(user);
        new RegistrationPage(driverRule.getDriver())
                .waitForLoadingPage()
                .inputName(creds.getName())
                .inputEmail(creds.getEmail())
                .inputPassword(creds.getPassword())
                .clickRegisterButton();
        new LoginPage(driverRule.getDriver())
                .waitLoadingPage();
    }

    @Test
    @DisplayName("Регистрация с некорректным паролем")
    public void registrationWrongPassword() {
        var creds = Credentials.fromUser(user);
        new RegistrationPage(driverRule.getDriver())
                .waitForLoadingPage()
                .inputName(creds.getName())
                .inputEmail(creds.getEmail())
                .inputPassword("qwe")
                .clickRegisterButton()
                .checkWrongPasswordWarning();
    }
}
