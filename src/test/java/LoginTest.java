import io.qameta.allure.Step;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import org.junit.After;
import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Test;
import praktikum.DriverRule;
import praktikum.pages.LoginPage;
import praktikum.pages.MainPage;

@DisplayName("Вход пользователя на странице авторизации")
public class LoginTest {

    private static final CreateUserJsonGenerator USER_JSON = new CreateUserJsonGenerator();
    private static final UserRests USER_REST = new UserRests();
    private static final Check CHECK = new Check();

    private static CreateUserRequestJson newUser;
    private static String accessToken;

    @ClassRule
    public static DriverRule driverRule = new DriverRule();

    @Before
    @Step("Создание пользователя через API и открытие страницы логина")
    public void createUserAndOpenLoginPage() {
        newUser = USER_JSON.random();  // генерация json рандомного пользователя
        ValidatableResponse createUserResponse = USER_REST.create(newUser);  // создание пользователя
        CHECK.code201andSuccess(createUserResponse);
        accessToken = CHECK.extractAccessToken(createUserResponse);  // сохранение токена авторизации на случай,
        // если логин через браузер не сработает
        new LoginPage(driverRule.getDriver())
                .openPage()
                .waitForLoadingPage();
    }

    @After
    @Step("Удаление пользователя через API")
    public void deleteUser() {
        ValidatableResponse creationResponse = USER_REST.delete(accessToken);
        CHECK.code202andSuccess(creationResponse);
        CHECK.userRemovedMessage(creationResponse);
        accessToken = null;
    }

    @Test
    @DisplayName("[+] Логин пользователя")
    public void loginUserTest() {
        new LoginPage(driverRule.getDriver())
                .inputEmail(newUser.getEmail())
                .inputPassword(newUser.getPassword())
                .clickEnterButton();
        accessToken = new MainPage(driverRule.getDriver())  // перезапись токена, сохранённого при создании юзера
                .waitForLoadingPageAuthUser()
                .getAccessTokenFromLocalStorage();
        System.out.println("\nAccess token from local storage:\n" + accessToken + "\n");
    }
}
