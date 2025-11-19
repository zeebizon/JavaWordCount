package nl.zeebizon.it;

import nl.zeebizon.infrastructure.in.rest.dto.FrequencyForWordRequest;
import nl.zeebizon.infrastructure.in.rest.dto.MostCommonWordsRequest;
import org.junit.jupiter.api.Test;

import io.quarkus.test.junit.QuarkusTest;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

@QuarkusTest
class AnalysisAPIIT {

    @Test
    void mostCommonWordFrequency() {
        var body = new MostCommonWordsRequest("The quick brown fox jumps over the lazy dog. The dog barks.");
        given()
          .header("Content-Type", "application/json")
          .body(body)
          .when().post("/words/most-common/frequency/")
          .then()
             .statusCode(200)
             .body(equalTo("{\"frequency\":3}"));
    }

    @Test
    void frequencyForWordRequest() {
        var body = new FrequencyForWordRequest("The quick brown fox jumps over the lazy dog. The dog barks.");
        given()
            .header("Content-Type", "application/json")
            .body(body)
            .when().post("/words/the/frequency/")
            .then()
            .statusCode(200)
            .body(equalTo("{\"word\":\"the\",\"frequency\":3}"));
    }

    @Test
    void mostCommonWords() {
        var body = new FrequencyForWordRequest("The quick brown fox jumps over the lazy dog. The dog barks.");
        given()
            .header("Content-Type", "application/json")
            .body(body)
            .when().post("words/most-common/3")
            .then()
            .statusCode(200)
            .body(equalTo("{\"wordFrequencies\":[{\"word\":\"the\",\"frequency\":3},{\"word\":\"dog\",\"frequency\":2},{\"word\":\"barks\",\"frequency\":1}]}"));
    }
}