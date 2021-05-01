package com.ma.pedidos.entity;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.ma.pedidos.util.OrderStatusEnum;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Entity
@Table(name = "pedidos_cabecera")
public class OrderHeaderEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "direccion", nullable = false, length = 250)
    private String address;

    @Column(name = "email", nullable = false, length = 100)
    private String email;

    @Column(name = "telefono", nullable = false, length = 100)
    private String phone;

    @JsonSerialize(using = ToStringSerializer.class)
    @Column(name = "horario", nullable = false)
    private LocalTime timetable;

    @JsonSerialize(using = ToStringSerializer.class)
    @Column(name = "fecha_alta", nullable = false)
    private LocalDate high_date;

    @Column(name = "monto_total", nullable = false, length = 20)
    private float total_amount;

    @Column(name = "aplico_descuento", nullable = false)
    private boolean apply_discount;

    @Column(name = "estado", nullable = false)
    @Enumerated(EnumType.STRING)
    private OrderStatusEnum status;

    @OneToMany(mappedBy = "order_header", cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE}, fetch = FetchType.LAZY, orphanRemoval = true)
    private List<OrderDetailEntity> detail;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public LocalTime getTimetable() {
        return timetable;
    }

    public void setTimetable(LocalTime schedule) {
        this.timetable = schedule;
    }

    public LocalDate getHigh_date() {
        return high_date;
    }

    public void setHigh_date(LocalDate high_date) {
        this.high_date = high_date;
    }

    public float getTotal_amount() {
        return total_amount;
    }

    public void setTotal_amount(float total_amount) {
        this.total_amount = total_amount;
    }

    public boolean isApply_discount() {
        return apply_discount;
    }

    public void setApply_discount(boolean apply_discount) {
        this.apply_discount = apply_discount;
    }

    public OrderStatusEnum getStatus() {
        return status;
    }

    public void setStatus(OrderStatusEnum status) {
        this.status = status;
    }

    public List<OrderDetailEntity> getDetail() {
        return detail;
    }

    public void setDetail(List<OrderDetailEntity> detail) {
        this.detail = detail;
    }
}
