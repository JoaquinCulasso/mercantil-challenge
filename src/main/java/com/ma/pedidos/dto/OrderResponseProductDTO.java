package com.ma.pedidos.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class OrderResponseProductDTO {

    @JsonProperty("producto")
    private String product;
    @JsonProperty("nombre")
    private String name;
    @JsonProperty("cantidad")
    private int quantity;
    @JsonProperty("importe")
    private float unit_price;


    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public float getUnit_price() {
        return unit_price;
    }

    public void setUnit_price(float unit_price) {
        this.unit_price = unit_price;
    }
}
