package com.ma.pedidos.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.sun.istack.NotNull;

import java.time.LocalTime;
import java.util.List;

@JsonSerialize
public class OrderDTO {

    @JsonProperty(value = "direccion")
    @NotNull
    private String address;
    @JsonProperty("email")
    private String email;
    @JsonProperty("telefono")
    private String phone;
    @JsonProperty("horario")
    private LocalTime timetable;
    @JsonProperty("detalle")
    private List<OrderProductDTO> detail;

    public OrderDTO() {
    }

    public OrderDTO(String address, String email, String phone, LocalTime timetable, List<OrderProductDTO> detail) {
        this.address = address;
        this.email = email;
        this.phone = phone;
        this.timetable = timetable;
        this.detail = detail;
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

    public void setTimetable(LocalTime timetable) {
        this.timetable = timetable;
    }

    public List<OrderProductDTO> getDetail() {
        return detail;
    }

    public void setDetail(List<OrderProductDTO> detail) {
        this.detail = detail;
    }
}
