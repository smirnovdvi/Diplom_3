package praktikum;

import io.qameta.allure.Step;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;


import static io.restassured.RestAssured.given;
import static praktikum.EnvConfig.*;

public class Request {
    @Step("Отправка запроса на создание пользователя")
    public ValidatableResponse create(User user) {
        return given().log().all()
                .contentType(ContentType.JSON)
                .baseUri(BASE_URL)
                .body(user)
                .when()
                .post(REGISTER_PATH)
                .then().log().all();
    }

    @Step("Отправка запроса на удаление пользователя")
    public ValidatableResponse delete(String token) {
        return given().log().all()
                .contentType(ContentType.JSON)
                .baseUri(BASE_URL)
                .header("Authorization", token)
                .when()
                .delete(USER_PATH)
                .then().log().all();
    }

    @Step("Авторизация пользователя")
    public ValidatableResponse login(Credentials creds) {
        return given().log().all()
                .contentType(ContentType.JSON)
                .baseUri(BASE_URL)
                .body(creds)
                .when()
                .post(LOGIN_PATH)
                .then().log().all();
    }
}
