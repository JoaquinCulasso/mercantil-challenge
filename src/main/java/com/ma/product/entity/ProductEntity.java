package com.ma.product.entity;

import javax.persistence.*;


@Entity
@Table(name = "productos")
public class ProductEntity {

    @Id
    private String id;

    @Column(name = "nombre", nullable = false, length = 100)
    private String name;

    @Column(name = "descripcion_corta", nullable = false, length = 100)
    private String short_description;

    @Column(name = "descripcion_larga", nullable = false, length = 250)
    private String long_description;

    @Column(name = "precio_unitario", nullable = false, length = 20)
    private float unit_price;

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
