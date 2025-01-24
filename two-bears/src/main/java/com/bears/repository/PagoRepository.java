package com.bears.repository;

import com.bears.model.Pago;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PagoRepository extends JpaRepository<Pago, Long> {
    // Buscar pagos por estado
    List<Pago> findByEstadoPago(Pago.EstadoPago estadoPago);

    // Buscar pagos por pedido
    List<Pago> findByPedidoId(Long pedidoId);
}
