package com.bears.service.impl;

import com.bears.dto.ProductoPedidoDTO;
import com.bears.model.ProductoPedido;
import com.bears.repository.ProductoPedidoRepository;
import com.bears.service.ProductoPedidoService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductoPedidoServiceImpl implements ProductoPedidoService {

    private final ProductoPedidoRepository productoPedidoRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public ProductoPedidoServiceImpl(ProductoPedidoRepository productoPedidoRepository, ModelMapper modelMapper) {
        this.productoPedidoRepository = productoPedidoRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public ProductoPedidoDTO save(ProductoPedidoDTO productoPedidoDTO) {
        ProductoPedido productoPedido = modelMapper.map(productoPedidoDTO, ProductoPedido.class);
        ProductoPedido savedProductoPedido = productoPedidoRepository.save(productoPedido);
        return modelMapper.map(savedProductoPedido, ProductoPedidoDTO.class);
    }

    @Override
    public ProductoPedidoDTO update(Long id, ProductoPedidoDTO productoPedidoDTO) {
        ProductoPedido existingProductoPedido = productoPedidoRepository.findById(id).orElseThrow(() -> new RuntimeException("ProductoPedido not found"));
        modelMapper.map(productoPedidoDTO, existingProductoPedido);
        ProductoPedido updatedProductoPedido = productoPedidoRepository.save(existingProductoPedido);
        return modelMapper.map(updatedProductoPedido, ProductoPedidoDTO.class);
    }

    @Override
    public void delete(Long id) {
        productoPedidoRepository.deleteById(id);
    }

    @Override
    public List<ProductoPedidoDTO> getAll() {
        List<ProductoPedido> productoPedidos = productoPedidoRepository.findAll();
        return productoPedidos.stream()
                .map(productoPedido -> modelMapper.map(productoPedido, ProductoPedidoDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public ProductoPedidoDTO getById(Long id) {
        ProductoPedido productoPedido = productoPedidoRepository.findById(id).orElseThrow(() -> new RuntimeException("ProductoPedido not found"));
        return modelMapper.map(productoPedido, ProductoPedidoDTO.class);
    }
}
