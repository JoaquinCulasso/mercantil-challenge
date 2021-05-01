package com.ma.pedidos.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ma.pedidos.entity.OrderHeaderEntity;
import com.ma.pedidos.util.OrderStatusEnum;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;


public class OrderResponseDTO {

    @JsonProperty("fecha")
    private LocalDate date;
    @JsonProperty("direccion")
    private String address;
    @JsonProperty("email")
    private String email;
    @JsonProperty("telefono")
    private String phone;
    @JsonProperty("horario")
    private LocalTime timetable;
    @JsonProperty("detalle")
    private List<OrderResponseProductDTO> detail;
    @JsonProperty("total")
    private float total;
    @JsonProperty("descuento")
    private boolean apply_discount;
    @JsonProperty("estado")
    private OrderStatusEnum status;

    public OrderResponseDTO() {
    }

    public OrderResponseDTO createResponse(OrderResponseDTO orderResponseDTO, OrderHeaderEntity orderHeader) {

        orderResponseDTO.setDate((orderHeader.getHigh_date()));
        orderResponseDTO.setAddress(orderHeader.getAddress());
        orderResponseDTO.setEmail(orderHeader.getEmail());
        orderResponseDTO.setPhone(orderHeader.getPhone());
        orderResponseDTO.setTimetable(orderHeader.getTimetable());

        List<OrderResponseProductDTO> listResponseProd = new ArrayList<>();

        orderHeader.getDetail().forEach(detail -> {
            OrderResponseProductDTO productDTO = new OrderResponseProductDTO();

            productDTO.setProduct((detail.getProductEntity().getId()));
            productDTO.setName(detail.getProductEntity().getName());
            productDTO.setQuantity(detail.getQuantity());
            productDTO.setUnit_price(detail.getProductEntity().getUnit_price() * detail.getQuantity());
            listResponseProd.add(productDTO);
        });
        orderResponseDTO.setDetail((listResponseProd));
        orderResponseDTO.setTotal((orderHeader.getTotal_amount()));
        orderResponseDTO.setApply_discount(orderHeader.isApply_discount());
        orderResponseDTO.setStatus(orderHeader.getStatus());

        return orderResponseDTO;
    }


    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
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

    public List<OrderResponseProductDTO> getDetail() {
        return detail;
    }

    public void setDetail(List<OrderResponseProductDTO> detail) {
        this.detail = detail;
    }

    public float getTotal() {
        return total;
    }

    public void setTotal(float total) {
        this.total = total;
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
}
