package com.bears.controller;

import com.bears.dto.PagoDTO;
import com.bears.service.PagoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/pagos")
public class PagoController {

    private final PagoService pagoService;

    @Autowired
    public PagoController(PagoService pagoService) {
        this.pagoService = pagoService;
    }

    // Crear un nuevo pago
    @PostMapping
    public ResponseEntity<PagoDTO> save(@RequestBody PagoDTO pagoDTO) {
        PagoDTO savedPago = pagoService.save(pagoDTO);
        return ResponseEntity.ok(savedPago);
    }

    // Actualizar un pago existente
    @PutMapping("/{id}")
    public ResponseEntity<PagoDTO> update(@PathVariable Long id, @RequestBody PagoDTO pagoDTO) {
        PagoDTO updatedPago = pagoService.update(id, pagoDTO);
        return ResponseEntity.ok(updatedPago);
    }

    // Eliminar un pago por ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        pagoService.delete(id);
        return ResponseEntity.noContent().build();
    }

    // Obtener todos los pagos
    @GetMapping
    public ResponseEntity<List<PagoDTO>> getAll() {
        List<PagoDTO> pagos = pagoService.getAll();
        return ResponseEntity.ok(pagos);
    }

    // Obtener un pago por ID
    @GetMapping("/{id}")
    public ResponseEntity<PagoDTO> getById(@PathVariable Long id) {
        PagoDTO pago = pagoService.getById(id);
        return ResponseEntity.ok(pago);
    }

    // Obtener pagos por estado
    @GetMapping("/estado/{estadoPago}")
    public ResponseEntity<List<PagoDTO>> getByEstadoPago(@PathVariable String estadoPago) {
        List<PagoDTO> pagos = pagoService.getByEstadoPago(estadoPago);
        return ResponseEntity.ok(pagos);
    }

    // Obtener pagos por ID de pedido
    @GetMapping("/pedido/{pedidoId}")
    public ResponseEntity<List<PagoDTO>> getByPedidoId(@PathVariable Long pedidoId) {
        List<PagoDTO> pagos = pagoService.getByPedidoId(pedidoId);
        return ResponseEntity.ok(pagos);
    }
}
