package com.example.demo

import io.restassured.RestAssured
import org.hamcrest.CoreMatchers.notNullValue
import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.web.server.LocalServerPort
import org.springframework.http.HttpHeaders
import org.springframework.http.MediaType

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class AppBreakDownTests(@LocalServerPort val port: Int) {

    @Test
    fun `when get resources then status code is 200`() {
        RestAssured.given().port(port)
                .log().all()
                .get("/v1/resources")
                .then()
                .statusCode(200)
    }

    @Test
    fun `when requests resources then set content type AppJson`() {
        RestAssured.given().port(port)
                .log().all()
                .get("/v1/resources")
                .then()
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
    }

    @Test
    fun `when requests resources then set max age 10`() {
        RestAssured.given().port(port)
                .log().all()
                .get("/v1/resources")
                .then()
                .header(HttpHeaders.CACHE_CONTROL, "max-age=10")
    }

    @Test
    fun `when requests resources then ETAG is not empty`() {
        RestAssured.given().port(port)
                .log().all()
                .get("/v1/resources")
                .then()
                .header(HttpHeaders.ETAG, notNullValue())
    }

    @Test
    fun `when requests resources then body is not empty`() {
        RestAssured.given().port(port)
                .log().all()
                .get("/v1/resources")
                .then()
                .body("", notNullValue())
    }
}