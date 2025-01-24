package com.bears.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
@Entity
@Table(name = "pedidos")
public class Pedido {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "usuario_id", nullable = false)
    private Usuario usuario;

    @Temporal(TemporalType.DATE)
    private Date fechaPedido;

    private String estado;

    @OneToMany(mappedBy = "pedido", cascade = CascadeType.ALL)
    private List<ProductoPedido> productos;
}
