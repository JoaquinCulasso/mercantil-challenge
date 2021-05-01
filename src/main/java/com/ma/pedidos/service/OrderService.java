package com.ma.pedidos.service;

import com.ma.pedidos.dto.OrderDTO;
import com.ma.pedidos.entity.OrderHeaderEntity;

import java.time.LocalDate;
import java.util.List;

public interface OrderService {

    OrderHeaderEntity register(OrderDTO orderDTO);

    List<OrderHeaderEntity> listFromDate(LocalDate date);

}
