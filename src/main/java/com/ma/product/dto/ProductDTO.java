package com.ma.product.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.sun.istack.NotNull;

@JsonSerialize
public class ProductDTO {

    @JsonProperty("id")
    @NotNull
    private String id;

    @JsonProperty("nombre")
    @NotNull
    private String name;

    @JsonProperty("descripcionCorta")
    private String short_description;

    @JsonProperty("descripcionLarga")
    private String long_description;

    @JsonProperty("precioUnitario")
    @NotNull
    private float unit_price;

    public ProductDTO() {
    }

    public ProductDTO(String id, String name, String short_description, String long_description, float unit_price) {
        this.id = id;
        this.name = name;
        this.short_description = short_description;
        this.long_description = long_description;
        this.unit_price = unit_price;
    }

    public ProductDTO(String name, String short_description, String long_description, float unit_price) {
        this.name = name;
        this.short_description = short_description;
        this.long_description = long_description;
        this.unit_price = unit_price;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getShort_description() {
        return short_description;
    }

    public void setShort_description(String short_description) {
        this.short_description = short_description;
    }

    public String getLong_description() {
        return long_description;
    }

    public void setLong_description(String long_description) {
        this.long_description = long_description;
    }

    public float getUnit_price() {
        return unit_price;
    }

    public void setUnit_price(float unit_price) {
        this.unit_price = unit_price;
    }
}
