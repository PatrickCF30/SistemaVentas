package com.bears.service.impl;

import com.bears.dto.ProductoDTO;
import com.bears.model.Producto;
import com.bears.repository.ProductoRepository;
import com.bears.service.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
public class ProductoServiceImpl implements ProductoService {

    private final ProductoRepository productoRepository;

    @Autowired
    public ProductoServiceImpl(ProductoRepository productoRepository) {
        this.productoRepository = productoRepository;
    }

    @Override
    public ProductoDTO saveProducto(ProductoDTO productoDTO) {
        Producto producto = new Producto();
        producto.setNombre(productoDTO.getNombre());
        producto.setDescripcion(productoDTO.getDescripcion());
        producto.setStock(productoDTO.getStock());
        producto.setPrecio(BigDecimal.valueOf(productoDTO.getPrecio()));

        // Establecemos el estado como ACTIVO por defecto
        producto.setEstado(Producto.Estado.ACTIVO);

        Producto savedProducto = productoRepository.save(producto);

        return convertToDTO(savedProducto);
    }

    @Override
    public ProductoDTO updateProducto(Long id, ProductoDTO productoDTO) {
        Optional<Producto> productoOptional = productoRepository.findById(id);
        if (productoOptional.isPresent()) {
            Producto producto = productoOptional.get();
            producto.setNombre(productoDTO.getNombre());
            producto.setDescripcion(productoDTO.getDescripcion());
            producto.setStock(productoDTO.getStock());
            producto.setPrecio(BigDecimal.valueOf(productoDTO.getPrecio()));

            Producto updatedProducto = productoRepository.save(producto);
            return convertToDTO(updatedProducto);
        }
        return null; // O lanzar excepción si no se encuentra
    }

    @Override
    public ProductoDTO findProductoById(Long id) {
        Optional<Producto> productoOptional = productoRepository.findById(id);
        return productoOptional.map(this::convertToDTO).orElse(null);
    }

    @Override
    public List<ProductoDTO> findAllProductos() {
        List<Producto> productos = productoRepository.findAll();
        return productos.stream().map(this::convertToDTO).toList();
    }

    @Override
    public void deleteProductoLogic(Long id) {
        Optional<Producto> productoOptional = productoRepository.findById(id);
        if (productoOptional.isPresent()) {
            Producto producto = productoOptional.get();
            producto.setEstado(Producto.Estado.INACTIVO);  // Cambiamos el estado a INACTIVO
            productoRepository.save(producto);  // Guardamos el cambio
        }
    }

    @Override
    public void deleteProductoPhysically(Long id) {
        productoRepository.deleteById(id);  // Eliminación física del producto
    }

    private ProductoDTO convertToDTO(Producto producto) {
        ProductoDTO productoDTO = new ProductoDTO();
        productoDTO.setId(producto.getId());
        productoDTO.setNombre(producto.getNombre());
        productoDTO.setDescripcion(producto.getDescripcion());
        productoDTO.setStock(producto.getStock());
        productoDTO.setPrecio(producto.getPrecio().doubleValue());
        productoDTO.setEstado(producto.getEstado().name());  // Convertimos el Enum a String
        return productoDTO;
    }
}

