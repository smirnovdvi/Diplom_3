import io.qameta.allure.Step;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import org.junit.After;
import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Test;
import org.junit.experimental.runners.Enclosed;
import org.junit.runner.RunWith;
import praktikum.*;
import praktikum.pages.LoginPage;
import praktikum.pages.MainPage;
import praktikum.pages.RegistrationPage;

@RunWith(Enclosed.class)
public class RegistrationTest {
    /**
     * Проверь:
     * Успешную регистрацию.
     * Ошибку для некорректного пароля. Минимальный пароль — шесть символов.
     */

    @DisplayName("Регистрация пользователя")
    public static class RegistrationTests {

        private User user;


        private static final Request respond = new Request();
        private static final Check check = new Check();
        //private static final Credentials creds = new Credentials();


        @ClassRule
        public static DriverRule driverRule = new DriverRule();

        @Before
        @Step("Генерация пользовательских данных, открытие главной страницы и открытие страницы логина")
        public void openPageAndNavigate() {
            user = User.random();
            new MainPage(driverRule.getDriver())
                    .openPage()
                    .waitPage()
                    .clickEnterAccountButton();
            new LoginPage(driverRule.getDriver())
                    .waitForLoadingPage()
                    .clickRegisterLink();
        }

        @After
        @Step("Удаление пользователя через API")
        public void deleteUserIfCreated() {
            String accessToken;
            var newLogin = Credentials.fromUser(user);
            ValidatableResponse loginUserResponse = respond.login(newLogin);
            accessToken = check.extractAccessToken(loginUserResponse);

            if (accessToken != null) {
                ValidatableResponse creationResponse = respond.delete(accessToken);
                check.code202andSuccess(creationResponse);
                check.userRemovedMessage(creationResponse);
            }
        }

        @Test
        @DisplayName("[+] Регистрация пользователя")
        public void registrationTest() {
            new RegistrationPage(driverRule.getDriver())
                    .waitForLoadingPage()
                    .inputName(user.getName())
                    .inputEmail(user.getEmail())
                    .inputPassword(user.getPassword())
                    .clickRegisterButton();
            new LoginPage(driverRule.getDriver())
                    .waitForLoadingPage();
        }

        @Test
        @DisplayName("[–] Регистрация с некорректным паролем. Пароль: 123")
        public void registrationWrongPasswordTest() {
            new RegistrationPage(driverRule.getDriver())
                    .waitForLoadingPage()
                    .inputName(user.getName())
                    .inputEmail(user.getEmail())
                    .inputPassword("123")
                    .clickRegisterButton()
                    .checkWrongPasswordWarning();
        }
    }
}
