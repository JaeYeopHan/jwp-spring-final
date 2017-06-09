package next.controller;

import io.restassured.RestAssured;
import org.junit.Before;
import org.junit.Test;

public class QuestionController {

    @Before
    public void setUp() {
        RestAssured.port = 8080;
    }

    @Test
    public void deleteQuestion() {

    }
}
