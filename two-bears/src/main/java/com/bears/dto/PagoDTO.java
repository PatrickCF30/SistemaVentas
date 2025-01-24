package com.bears.dto;

import lombok.Data;

@Data
public class PagoDTO {
    private Long id;
    private Long pedidoId;
    private String metodoPago;
    private double monto;
    private String estadoPago;
}
