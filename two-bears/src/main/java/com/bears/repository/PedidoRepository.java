package com.bears.repository;

import com.bears.model.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Long> {

    // Método para obtener pedidos por estado
    List<Pedido> findByEstado(String estado);

    // Método para obtener pedidos por usuario (suponiendo que se quiere filtrar por usuario)
    List<Pedido> findByUsuarioId(Long usuarioId);

    // Método para obtener pedidos realizados en una fecha específica
    List<Pedido> findByFechaPedido(Date fechaPedido);
}
