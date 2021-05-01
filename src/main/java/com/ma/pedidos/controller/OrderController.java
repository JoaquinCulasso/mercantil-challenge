package com.ma.pedidos.controller;


import com.ma.pedidos.dto.OrderDTO;
import com.ma.pedidos.dto.OrderResponseDTO;
import com.ma.pedidos.entity.OrderHeaderEntity;
import com.ma.pedidos.service.OrderService;
import com.ma.pedidos.util.ValidOrderRequest;
import com.ma.pedidos.util.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/pedidos")
public class OrderController {

    private final OrderService orderService;
    private final ValidOrderRequest validOrderRequest = new ValidOrderRequest();


    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity register(@RequestBody OrderDTO orderDTO) {

        OrderResponseDTO orderResponseDTO = new OrderResponseDTO();

        try {
            List listError = validOrderRequest.isValidRequest(orderDTO);
            if ((!listError.isEmpty())) {
                Response resError = new Response(listError);
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(resError);
            } else {
                OrderHeaderEntity orderHeaderEntity = orderService.register(orderDTO);
                return ResponseEntity.status(HttpStatus.CREATED).body(orderResponseDTO.createResponse(orderResponseDTO, orderHeaderEntity));
            }

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(orderResponseDTO);
        }
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity listFromDate(@RequestParam(name = "fecha") String fecha) {


        List<OrderHeaderEntity> orderFromDate = new ArrayList<>();
        List<OrderResponseDTO> orderResponse = new ArrayList<>();
        LocalDate dateOrder = LocalDate.parse(fecha);
        try {
            orderFromDate.clear();
            orderFromDate = orderService.listFromDate(dateOrder);
            orderFromDate.forEach(order -> {
                OrderResponseDTO orderResponseDTO = new OrderResponseDTO();
                orderResponseDTO = orderResponseDTO.createResponse(orderResponseDTO, order);
                orderResponse.add(orderResponseDTO);
            });
            return ResponseEntity.status(HttpStatus.OK).body(orderResponse);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(orderResponse);
        }
    }


}
