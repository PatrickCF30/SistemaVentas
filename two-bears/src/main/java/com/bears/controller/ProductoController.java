package com.bears.controller;

import com.bears.dto.ProductoDTO;
import com.bears.service.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/productos")
public class ProductoController {

    private final ProductoService productoService;

    @Autowired
    public ProductoController(ProductoService productoService) {
        this.productoService = productoService;
    }

    // Endpoint para crear un nuevo producto
    @PostMapping
    public ResponseEntity<ProductoDTO> saveProducto(@RequestBody ProductoDTO productoDTO) {
        ProductoDTO savedProducto = productoService.saveProducto(productoDTO);
        return new ResponseEntity<>(savedProducto, HttpStatus.CREATED);
    }

    // Endpoint para actualizar un producto existente
    @PutMapping("/{id}")
    public ResponseEntity<ProductoDTO> updateProducto(@PathVariable Long id, @RequestBody ProductoDTO productoDTO) {
        ProductoDTO updatedProducto = productoService.updateProducto(id, productoDTO);
        return new ResponseEntity<>(updatedProducto, HttpStatus.OK);
    }

    // Endpoint para obtener un producto por ID
    @GetMapping("/{id}")
    public ResponseEntity<ProductoDTO> getProductoById(@PathVariable Long id) {
        ProductoDTO productoDTO = productoService.findProductoById(id);
        return new ResponseEntity<>(productoDTO, HttpStatus.OK);
    }

    // Endpoint para obtener todos los productos
    @GetMapping
    public ResponseEntity<List<ProductoDTO>> getAllProductos() {
        List<ProductoDTO> productos = productoService.findAllProductos();
        return new ResponseEntity<>(productos, HttpStatus.OK);
    }

    // Endpoint para eliminar un producto de manera lógica (cambia su estado a INACTIVO)
    @DeleteMapping("/logic/{id}")
    public ResponseEntity<Void> deleteProductoLogic(@PathVariable Long id) {
        productoService.deleteProductoLogic(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    // Endpoint para eliminar un producto de manera física
    @DeleteMapping("/physic/{id}")
    public ResponseEntity<Void> deleteProductoPhysically(@PathVariable Long id) {
        productoService.deleteProductoPhysically(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
