package com.ma.pedidos.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;


@JsonSerialize
public class OrderProductDTO {

    @JsonProperty("producto")
    private String product;
    @JsonProperty(value = "cantidad", required = true)
    private int quantity;

    public OrderProductDTO() {
    }

    public OrderProductDTO(String product, int quantity) {
        this.product = product;
        this.quantity = quantity;
    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

}
