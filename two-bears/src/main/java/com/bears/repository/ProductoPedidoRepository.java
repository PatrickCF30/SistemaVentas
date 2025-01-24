package com.bears.repository;

import com.bears.model.ProductoPedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductoPedidoRepository extends JpaRepository<ProductoPedido, Long> {

    List<ProductoPedido> findByPedidoId(Long pedidoId);

}

