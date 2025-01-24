package com.bears.controller;

import com.bears.dto.PedidoDTO;
import com.bears.service.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/pedidos")
public class PedidoController {

    @Autowired
    private PedidoService pedidoService;

    // Guardar un nuevo pedido
    @PostMapping
    public ResponseEntity<PedidoDTO> createPedido(@RequestBody PedidoDTO pedidoDTO) {
        PedidoDTO createdPedido = pedidoService.save(pedidoDTO);
        return ResponseEntity.ok(createdPedido);
    }

    // Actualizar un pedido existente
    @PutMapping("/{id}")
    public ResponseEntity<PedidoDTO> updatePedido(@RequestBody PedidoDTO pedidoDTO, @PathVariable Long id) {
        PedidoDTO updatedPedido = pedidoService.update(pedidoDTO, id);
        if (updatedPedido != null) {
            return ResponseEntity.ok(updatedPedido);
        }
        return ResponseEntity.notFound().build();
    }

    // Eliminar un pedido
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePedido(@PathVariable Long id) {
        pedidoService.delete(id);
        return ResponseEntity.noContent().build();
    }

    // Obtener un pedido por su ID
    @GetMapping("/{id}")
    public ResponseEntity<PedidoDTO> getPedidoById(@PathVariable Long id) {
        PedidoDTO pedidoDTO = pedidoService.findById(id);
        if (pedidoDTO != null) {
            return ResponseEntity.ok(pedidoDTO);
        }
        return ResponseEntity.notFound().build();
    }

    // Obtener todos los pedidos con un estado específico
    @GetMapping("/estado/{estado}")
    public ResponseEntity<List<PedidoDTO>> getPedidosByEstado(@PathVariable String estado) {
        List<PedidoDTO> pedidos = pedidoService.findByEstado(estado);
        return ResponseEntity.ok(pedidos);
    }

    // Obtener todos los pedidos de un usuario específico
    @GetMapping("/usuario/{usuarioId}")
    public ResponseEntity<List<PedidoDTO>> getPedidosByUsuarioId(@PathVariable Long usuarioId) {
        List<PedidoDTO> pedidos = pedidoService.findByUsuarioId(usuarioId);
        return ResponseEntity.ok(pedidos);
    }
}
