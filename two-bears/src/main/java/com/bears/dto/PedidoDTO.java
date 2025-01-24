package com.bears.dto;

import lombok.Data;

@Data
public class PedidoDTO {
    private Long id;
    private Long usuarioId;
    private String fecha;
    private String estado;
    private double total;
}
