package com.bears.service;

import com.bears.dto.ProductoDTO;
import java.util.List;

public interface ProductoService {

    ProductoDTO saveProducto(ProductoDTO productoDTO);

    ProductoDTO updateProducto(Long id, ProductoDTO productoDTO);

    ProductoDTO findProductoById(Long id);

    List<ProductoDTO> findAllProductos();

    void deleteProductoLogic(Long id);

    void deleteProductoPhysically(Long id);
}
