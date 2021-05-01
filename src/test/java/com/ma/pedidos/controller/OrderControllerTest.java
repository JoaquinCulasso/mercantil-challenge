package com.ma.pedidos.controller;

import com.ma.pedidos.dto.OrderDTO;
import com.ma.pedidos.dto.OrderProductDTO;
import com.ma.product.dto.ProductDTO;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;


import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;

import static io.restassured.RestAssured.given;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public
class OrderControllerTest {

    @Test
    @Order(0)
    public void createProductoRest() {

        ProductDTO productDTOMock = new ProductDTO("89efb206-2aa6-4e21-8a23-5765e3de1f31", "Especial", "Pizza de jamón y morrones", "Mozzarella, jamón, morrones y aceitunas verdes", 500.00F);
        given().
                header("Content-type", "application/json").
                contentType(ContentType.JSON).
                accept(ContentType.JSON).
                body(productDTOMock).
                when().
                post("http://localhost:8080/productos").
                then().statusCode(201).
                log().all();

        productDTOMock = new ProductDTO("e29ebd0c-39d2-4054-b0f4-ed2d0ea089a1", "Especial", "Pizza de jamón y morrones", "Mozzarella, jamón, morrones y aceitunas verdes", 500.00F);

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
    @Order(1)
    void registerOrderSuccess() {

        List<OrderProductDTO> listOrderProductDTO = Arrays.asList(
                new OrderProductDTO("89efb206-2aa6-4e21-8a23-5765e3de1f31", 1),
                new OrderProductDTO("e29ebd0c-39d2-4054-b0f4-ed2d0ea089a1", 4));
        OrderDTO orderDTOMock = new OrderDTO("Dorton Road 80", "tsayb@opera.com", "(0351) 48158101", LocalTime.parse("21:00"), listOrderProductDTO);

        RestAssured.given().
                header("Content-type", "application/json").
                contentType(ContentType.JSON).
                accept(ContentType.JSON).
                body(orderDTOMock)
                .when()
                .post("http://localhost:8080/pedidos")
                .then().statusCode(201)
                .log().body();

    }

    @Test
    @Order(2)
    void registerOrderSuccessApplyDiscount() {

        List<OrderProductDTO> listOrderProductDTO = Arrays.asList(
                new OrderProductDTO("89efb206-2aa6-4e21-8a23-5765e3de1f31", 1),
                new OrderProductDTO("e29ebd0c-39d2-4054-b0f4-ed2d0ea089a1", 4));
        OrderDTO orderDTOMock = new OrderDTO("Dorton Road 80", "tsayb@opera.com", "(0351) 48158101", LocalTime.parse("21:00"), listOrderProductDTO);

        orderDTOMock.setDetail(listOrderProductDTO);

        RestAssured.given().
                header("Content-type", "application/json").
                contentType(ContentType.JSON).
                accept(ContentType.JSON).
                body(orderDTOMock)
                .when()
                .post("http://localhost:8080/pedidos")
                .then().statusCode(201)
                .log().body();
    }

    @Test
    @Order(3)
    void orderWithErrors() {

        List<OrderProductDTO> listOrderProductDTO = Arrays.asList(
                new OrderProductDTO("e29ebd0c-39d2-4054-b0f4-ed2d0ea089a1", 1),
                new OrderProductDTO("e29ebd0c-39d2-4054-b0f4-ed2d0ea089a1", 0));
        OrderDTO orderDTOMock = new OrderDTO(null, "tsayb@opera.com", "(0351) 48158101", LocalTime.parse("21:00"), listOrderProductDTO);

        RestAssured.given().
                header("Content-type", "application/json").
                contentType(ContentType.JSON).
                accept(ContentType.JSON).
                body(orderDTOMock)
                .when()
                .post("http://localhost:8080/pedidos")
                .then().statusCode(400)
                .log().body();
    }

    @Test
    @Order(4)
    void listFromDate() {
        RestAssured.given()
                .queryParam("fecha", LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")))
                .when()
                .get("http://localhost:8080/pedidos")
                .then().statusCode(200)
                .log().all();
    }
}