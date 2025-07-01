package praktikum;

import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;

import static org.hamcrest.CoreMatchers.equalTo;


public class Check {
    @Step("Успешный запрос")
    public void successRequest(ValidatableResponse response) {
        response.assertThat().statusCode(200)
                .body("success", equalTo(true));
    }

    @Step("Сохранение токена авторизации")
    public String extractAccessToken(ValidatableResponse response) {
        return response.extract().path("accessToken");
    }

    @Step("Сохранение токена авторизации")
    public String extractRefreshToken(ValidatableResponse response) {
        return response.extract().path("refreshToken");
    }

    @Step("Запрос принят")
    public void RequestSuccess(ValidatableResponse response) {
        response.assertThat().statusCode(202)
                .body("success", equalTo(true));
    }

    @Step("Подтверждение удаления пользователя")
    public void userRemoved(ValidatableResponse response) {
        response.body("message", equalTo("User successfully removed"));
    }

}
