package com.bears.service;

import com.bears.dto.PedidoDTO;
import com.bears.model.Pedido;

import java.util.List;

public interface PedidoService {

    PedidoDTO save(PedidoDTO pedidoDTO);

    PedidoDTO update(PedidoDTO pedidoDTO, Long id);

    void delete(Long id);

    PedidoDTO findById(Long id);

    List<PedidoDTO> findByEstado(String estado);

    List<PedidoDTO> findByUsuarioId(Long usuarioId);
}
