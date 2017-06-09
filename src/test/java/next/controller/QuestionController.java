package next.controller;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import next.model.Question;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;

import java.util.Date;

import static io.restassured.RestAssured.given;

public class QuestionController {
    private static final Logger log = LoggerFactory.getLogger(QuestionController.class);

    @Before
    public void setUp() {
        RestAssured.port = 8080;
    }

    @Test
    public void deleteQuestion() {
//        Question question = new Question(1, "admin", "test", "test content", new Date(), 1);
        given()
                .auth()
                .preemptive()
                .basic("admin", "password")
                .queryParam("title", "test title")
                .queryParam("contents", "test content")
                .contentType(ContentType.URLENC)
        .when()
                .post("/questions")
        .then()
                .statusCode(HttpStatus.FOUND.value());

        given()
                .auth()
                .preemptive()
                .basic("admin", "password")
                .contentType(ContentType.HTML)
        .when()
                .delete("/questions/9")
        .then()
                .statusCode(HttpStatus.FOUND.value());
    }
}
