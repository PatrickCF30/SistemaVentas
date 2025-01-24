package com.bears.controller;

import com.bears.dto.ProductoPedidoDTO;
import com.bears.service.ProductoPedidoService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/producto-pedidos")
public class ProductoPedidoController {

    private final ProductoPedidoService productoPedidoService;
    private final ModelMapper modelMapper;

    @Autowired
    public ProductoPedidoController(ProductoPedidoService productoPedidoService, ModelMapper modelMapper) {
        this.productoPedidoService = productoPedidoService;
        this.modelMapper = modelMapper;
    }

    @GetMapping
    public List<ProductoPedidoDTO> obtenerTodos() {
        return productoPedidoService.getAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductoPedidoDTO> obtenerPorId(@PathVariable Long id) {
        ProductoPedidoDTO productoPedidoDTO = productoPedidoService.getById(id);
        return productoPedidoDTO != null ? ResponseEntity.ok(productoPedidoDTO) : ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<ProductoPedidoDTO> agregarProductoPedido(@RequestBody ProductoPedidoDTO productoPedidoDTO) {
        ProductoPedidoDTO nuevoProductoPedido = productoPedidoService.save(productoPedidoDTO);
        return ResponseEntity.status(201).body(nuevoProductoPedido);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductoPedidoDTO> actualizarProductoPedido(@PathVariable Long id, @RequestBody ProductoPedidoDTO productoPedidoDTO) {
        ProductoPedidoDTO actualizado = productoPedidoService.update(id, productoPedidoDTO);
        return actualizado != null ? ResponseEntity.ok(actualizado) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarProductoPedido(@PathVariable Long id) {
        productoPedidoService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
