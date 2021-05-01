package com.ma.pedidos.service;

import com.ma.pedidos.dto.OrderDTO;
import com.ma.pedidos.entity.OrderDetailEntity;
import com.ma.pedidos.entity.OrderHeaderEntity;
import com.ma.pedidos.repository.OrderHeaderRepository;
import com.ma.pedidos.util.Constant;
import com.ma.pedidos.util.OrderStatusEnum;
import com.ma.product.entity.ProductEntity;
import com.ma.product.repository.ProductRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class OrderServiceImpl implements OrderService {

    private final OrderHeaderRepository orderHeaderRepository;
    private final ProductRepository productRepository;


    @Autowired
    public OrderServiceImpl(OrderHeaderRepository orderHeaderRepository, ProductRepository productRepository) {
        this.orderHeaderRepository = orderHeaderRepository;
        this.productRepository = productRepository;
    }

    @Override
    @Transactional
    public OrderHeaderEntity register(OrderDTO orderDTO) {


        OrderHeaderEntity orderHeader = new OrderHeaderEntity();
        List<OrderDetailEntity> listOrder = new ArrayList<>();

        String date = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        String timetable = orderDTO.getTimetable().format(DateTimeFormatter.ofPattern("HH:mm"));

        orderHeader.setHigh_date(LocalDate.parse(date));
        orderHeader.setAddress(orderDTO.getAddress());
        orderHeader.setEmail(orderDTO.getEmail());
        orderHeader.setPhone(orderDTO.getPhone());
        orderHeader.setTimetable(LocalTime.parse(timetable));

        orderDTO.getDetail().forEach(detail -> {
            OrderDetailEntity orderDetail = new OrderDetailEntity();

            Optional<ProductEntity> optionalProduct = productRepository.findById(detail.getProduct());

            orderDetail.setOrder_header(orderHeader);
            orderDetail.setProductEntity(optionalProduct.get());
            orderDetail.setQuantity(detail.getQuantity());
            orderDetail.setUnit_price(detail.getQuantity() * optionalProduct.get().getUnit_price());
            listOrder.add(orderDetail);
        });

        orderHeader.setDetail(listOrder);

        float total = 0;
        int quantityProduct = 0;

        for (OrderDetailEntity orderDetail : listOrder) {
            total += orderDetail.getUnit_price();
            quantityProduct += orderDetail.getQuantity();
        }

        if (quantityProduct > Constant.QUANTITY_APPLY_DISCOUNT) {
            orderHeader.setTotal_amount(total - ((total * Constant.DISCOUNT_PERCENTAGE) / 100));
            orderHeader.setApply_discount(Constant.APPLY_DISCOUNT);
        } else {
            orderHeader.setTotal_amount(total);
            orderHeader.setApply_discount(Constant.NOT_APPLY_DISCOUNT);
        }

        orderHeader.setStatus(OrderStatusEnum.PENDIENTE);

        orderHeaderRepository.save(orderHeader);

        return orderHeader;

    }

    @Override
    @Transactional(readOnly = true)
    public List<OrderHeaderEntity> listFromDate(LocalDate date) {
        return orderHeaderRepository.listFromDate(date);
    }
}
