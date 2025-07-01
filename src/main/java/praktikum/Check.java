package praktikum;

import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;

import static org.hamcrest.CoreMatchers.equalTo;


public class Check {
    @Step("Код ответа: 200 OK")
    public void code201andSuccess(ValidatableResponse response) {
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

    @Step("Код ответа: 202 ACCEPTED")
    public void code202andSuccess(ValidatableResponse response) {
        response.assertThat().statusCode(202)
                .body("success", equalTo(true));
    }

    @Step("Текст ответа: пользователь успешно удалён")
    public void userRemovedMessage(ValidatableResponse response) {
        response.body("message", equalTo("User successfully removed"));
    }

}
