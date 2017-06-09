package next.controller.qna;

import io.restassured.RestAssured;
import org.junit.Before;
import org.junit.Test;
import org.springframework.http.HttpStatus;

import static io.restassured.RestAssured.when;

public class QnaControllerTest {



    @Before
    public void setUp() {
        RestAssured.port = 8080;
    }

    @Test
    public void test() {
        when()
                .get("/questions/1")
        .then()
                .statusCode(HttpStatus.OK.value());
    }
}
