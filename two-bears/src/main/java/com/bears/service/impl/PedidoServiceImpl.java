package com.bears.service.impl;

import com.bears.dto.PedidoDTO;
import com.bears.model.Pedido;
import com.bears.repository.PedidoRepository;
import com.bears.service.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PedidoServiceImpl implements PedidoService {

    @Autowired
    private PedidoRepository pedidoRepository;

    @Override
    public PedidoDTO save(PedidoDTO pedidoDTO) {
        Pedido pedido = new Pedido();
        pedido.setEstado("PENDIENTE"); // Definir el estado por defecto al guardar
        // Mapear los otros atributos de pedidoDTO a pedido
        pedido.setFechaPedido(java.sql.Date.valueOf(pedidoDTO.getFecha()));
        // Se debe mapear el usuario y otros campos según DTO
        pedido = pedidoRepository.save(pedido);
        return mapToDTO(pedido);
    }

    @Override
    public PedidoDTO update(PedidoDTO pedidoDTO, Long id) {
        Optional<Pedido> pedidoOptional = pedidoRepository.findById(id);
        if (pedidoOptional.isPresent()) {
            Pedido pedido = pedidoOptional.get();
            // Mapear los valores actualizados desde el DTO
            pedido.setFechaPedido(java.sql.Date.valueOf(pedidoDTO.getFecha()));
            pedido.setEstado(pedidoDTO.getEstado());
            // Actualizar otros campos según lo necesario
            pedido = pedidoRepository.save(pedido);
            return mapToDTO(pedido);
        }
        return null;
    }

    @Override
    public void delete(Long id) {
        Optional<Pedido> pedidoOptional = pedidoRepository.findById(id);
        if (pedidoOptional.isPresent()) {
            pedidoRepository.delete(pedidoOptional.get());
        }
    }

    @Override
    public PedidoDTO findById(Long id) {
        Optional<Pedido> pedidoOptional = pedidoRepository.findById(id);
        return pedidoOptional.map(this::mapToDTO).orElse(null);
    }

    @Override
    public List<PedidoDTO> findByEstado(String estado) {
        List<Pedido> pedidos = pedidoRepository.findByEstado(estado);
        return pedidos.stream().map(this::mapToDTO).collect(Collectors.toList());
    }

    @Override
    public List<PedidoDTO> findByUsuarioId(Long usuarioId) {
        List<Pedido> pedidos = pedidoRepository.findByUsuarioId(usuarioId);
        return pedidos.stream().map(this::mapToDTO).collect(Collectors.toList());
    }

    private PedidoDTO mapToDTO(Pedido pedido) {
        PedidoDTO pedidoDTO = new PedidoDTO();
        pedidoDTO.setId(pedido.getId());
        pedidoDTO.setUsuarioId(pedido.getUsuario().getId());
        pedidoDTO.setFecha(pedido.getFechaPedido().toString());
        pedidoDTO.setEstado(pedido.getEstado());
        // Mapear otros campos si es necesario
        return pedidoDTO;
    }
}
