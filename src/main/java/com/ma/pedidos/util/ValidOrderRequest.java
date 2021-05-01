package com.ma.pedidos.util;

import com.ma.pedidos.dto.OrderDTO;

import java.util.*;

public class ValidOrderRequest {


    public List<Map> isValidRequest(OrderDTO orderDTO) {
        List<Map> listResponse = new ArrayList<>();
        Optional<String> isValid;

        isValid = Optional.ofNullable(orderDTO.getAddress());
        if (!isValid.isPresent() || isValid.get().isEmpty()) {
            Map<String, String> response = new HashMap<>();
            response.put("error", Constant.EMPTY_ADDRESS);
            listResponse.add(response);
        }
        orderDTO.getDetail().forEach(
                detail -> {

                    if (detail.getQuantity() <= 0) {
                        Map<String, String> response = new HashMap<>();
                        response.put("error", Constant.EMPTY_QUANTITY);
                        listResponse.add(response);

                    }
                }
        );

        return listResponse;
    }

}
