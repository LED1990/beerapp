package com.microservices.beer.controllers;

import com.microservices.beer.model.Beer;
import com.microservices.beer.model.dtos.BeerDto;
import com.microservices.beer.model.dtos.CustomerDto;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.context.annotation.Profile;

import static io.restassured.RestAssured.when;
import static io.restassured.RestAssured.with;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment= SpringBootTest.WebEnvironment.RANDOM_PORT)
class BeerControllerTest {

    @LocalServerPort
    int port;

    @BeforeEach
    public void setup() {
        RestAssured.port = port;
    }


    @Test
    public void testGetOne(){
        when().request("GET", "/api/v1/beer/1").then().statusCode(200);
    }

    @Test
    public void testGetOneShouldGetNotFound(){
        when().request("GET", "/api/v1/beer/100").then().statusCode(404);
    }

    @Test
    public void testAddNewBeer() {
        //given
        BeerDto beerDto = new BeerDto();
        beerDto.setBeerName("name");

        //when
        with().body(beerDto)
                .contentType(ContentType.JSON)
                .when()
                .request("POST", "/api/v1/beer")
                .then()
                .statusCode(201)
                .body("id",  notNullValue());
    }

    @Test
    public void testDeleteCustomer() {
        when().request("DELETE", "/api/v1/beer/1").then().statusCode(204);
    }

    @Test
    public void testUpdateCustomer() {
        //given
        BeerDto beerDto = new BeerDto();
        beerDto.setBeerName("new name");
        beerDto.setId(2L);

        //when
        with().body(beerDto)
                .contentType(ContentType.JSON)
                .when()
                .request("PUT", "/api/v1/beer")
                .then()
                .statusCode(200)
                .body("id",  equalTo(2))
                .body("beerName",  equalTo("new name"));
    }

}