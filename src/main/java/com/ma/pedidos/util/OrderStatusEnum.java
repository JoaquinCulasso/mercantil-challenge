package com.ma.pedidos.util;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonFormat
public enum OrderStatusEnum {

    @JsonProperty("Pendiente")
    PENDIENTE,
    @JsonProperty("Entregado")
    ENTREGADO
}
