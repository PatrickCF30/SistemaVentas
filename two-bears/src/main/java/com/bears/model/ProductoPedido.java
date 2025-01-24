package com.bears.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "producto_pedido")
public class ProductoPedido {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "producto_id", nullable = false)
    private Producto producto;

    @ManyToOne
    @JoinColumn(name = "pedido_id", nullable = false)
    private Pedido pedido;

    private Integer cantidad;
}

