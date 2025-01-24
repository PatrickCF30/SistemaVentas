package com.bears.dto;

import lombok.Data;

@Data
public class ProductoPedidoDTO {
    private Long id;
    private Long productoId;
    private Long pedidoId;
    private int cantidad;
}
