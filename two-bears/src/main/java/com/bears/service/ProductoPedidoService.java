package com.bears.service;

import com.bears.dto.ProductoPedidoDTO;

import java.util.List;

public interface ProductoPedidoService {
    ProductoPedidoDTO save(ProductoPedidoDTO productoPedidoDTO);
    ProductoPedidoDTO update(Long id, ProductoPedidoDTO productoPedidoDTO);
    void delete(Long id);
    List<ProductoPedidoDTO> getAll();
    ProductoPedidoDTO getById(Long id);
}