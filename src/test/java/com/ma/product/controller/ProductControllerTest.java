package com.ma.product.controller;


import com.ma.product.dto.ProductDTO;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.*;

import static io.restassured.RestAssured.given;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public
class ProductControllerTest {


    private ProductDTO productDTOMock;


    @Test
    @Order(1)
    public void createProductoRest() {

        productDTOMock = new ProductDTO("89efb206-2aa6-4e21-8a23-5765e3de1f312", "Especial", "Pizza de jamón y morrones", "Mozzarella, jamón, morrones y aceitunas verdes", 500.00F);
        given().
                header("Content-type", "application/json").
                contentType(ContentType.JSON).
                accept(ContentType.JSON).
                body(productDTOMock).
                when().
                post("http://localhost:8080/productos").
                then().statusCode(201).
                log().all();

        productDTOMock = new ProductDTO("e29ebd0c-39d2-4054-b0f4-ed2d0ea089a12", "Especial", "Pizza de jamón y morrones", "Mozzarella, jamón, morrones y aceitunas verdes", 500.00F);

        given().
                header("Content-type", "application/json").
                contentType(ContentType.JSON).
                accept(ContentType.JSON).
                body(productDTOMock).
                when().
                post("http://localhost:8080/productos").
                then().statusCode(201).
                log().all();
    }


    @Test
    @Order(2)
    public void updateProductRest() {

        productDTOMock = new ProductDTO("Especial", "Pizza de jamón y morrones", "Mozzarella, jamón, morrones y aceitunas verdes", 500.00F);

        given().
                header("Content-type", "application/json").
                contentType(ContentType.JSON).
                accept(ContentType.JSON).
                body(productDTOMock).
                when().
                put("http://localhost:8080/productos/89efb206-2aa6-4e21-8a23-5765e3de1f312").
                then().statusCode(204).
                log().all();
    }

    @Test
    @Order(3)
    void getProductIdRest() {
        given()
                .get("http://localhost:8080/productos/89efb206-2aa6-4e21-8a23-5765e3de1f312")
                .then().statusCode(200)
                .log().body();
    }

    @Test
    @Order(4)
    void getProductNotExists() {
        given().get("http://localhost:8080/productos/89efb206-2aa6-4e21-8a23-5765e3de1fXX")
                .then().statusCode(404)
                .log().body();
    }

    @Test
    @Order(5)
    void deleteProduct() {
        RestAssured.when()
                .delete("http://localhost:8080/productos/89efb206-2aa6-4e21-8a23-5765e3de1f312")
                .then().statusCode(204)
                .log().all();
    }
}