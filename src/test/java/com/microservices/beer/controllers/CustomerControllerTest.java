package com.microservices.beer.controllers;

import com.microservices.beer.model.dtos.CustomerDto;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;

import static io.restassured.RestAssured.when;
import static io.restassured.RestAssured.with;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;

//todo add profiles!
@SpringBootTest(webEnvironment= SpringBootTest.WebEnvironment.RANDOM_PORT)
class CustomerControllerTest {

    @LocalServerPort
    int port;

    @BeforeEach
    public void setup() {
        RestAssured.port = port;
    }

    @Test
    public void testGetOne(){
        when().request("GET", "/api/v1/customer/1").then().statusCode(200);
    }

    @Test
    public void testGetOneShouldGetNotFound(){
        when().request("GET", "/api/v1/customer/100").then().statusCode(404);
    }

    @Test
    public void testAddNewCustomer() {
        //given
        CustomerDto customerDto = new CustomerDto();
        customerDto.setName("name");

        with().body(customerDto)
                .contentType(ContentType.JSON)
                .when()
                .request("POST", "/api/v1/customer")
                .then()
                .statusCode(201)
                .body("id",  notNullValue());
    }

    @Test
    public void testDeleteCustomer() {
        when().request("DELETE", "/api/v1/customer/1").then().statusCode(204);
    }

    @Test
    public void testUpdateCustomer() {
        //given
        CustomerDto customerDto = new CustomerDto(2L, "new name");

        with().body(customerDto)
                .contentType(ContentType.JSON)
                .when()
                .request("PUT", "/api/v1/customer")
                .then()
                .statusCode(200)
                .body("id",  equalTo(2))
                .body("name",  equalTo("new name"));
    }
}