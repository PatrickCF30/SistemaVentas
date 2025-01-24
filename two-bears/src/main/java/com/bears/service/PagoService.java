package com.bears.service;

import com.bears.dto.PagoDTO;

import java.util.List;

public interface PagoService {
    PagoDTO save(PagoDTO pagoDTO);

    PagoDTO update(Long id, PagoDTO pagoDTO);

    void delete(Long id);

    List<PagoDTO> getAll();

    PagoDTO getById(Long id);

    List<PagoDTO> getByEstadoPago(String estadoPago);

    List<PagoDTO> getByPedidoId(Long pedidoId);
}
