package com.bears.service.impl;

import com.bears.dto.PagoDTO;
import com.bears.model.Pago;
import com.bears.repository.PagoRepository;
import com.bears.service.PagoService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PagoServiceImpl implements PagoService {

    private final PagoRepository pagoRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public PagoServiceImpl(PagoRepository pagoRepository, ModelMapper modelMapper) {
        this.pagoRepository = pagoRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public PagoDTO save(PagoDTO pagoDTO) {
        Pago pago = modelMapper.map(pagoDTO, Pago.class);
        Pago savedPago = pagoRepository.save(pago);
        return modelMapper.map(savedPago, PagoDTO.class);
    }

    @Override
    public PagoDTO update(Long id, PagoDTO pagoDTO) {
        Pago existingPago = pagoRepository.findById(id).orElseThrow(() -> new RuntimeException("Pago not found"));
        modelMapper.map(pagoDTO, existingPago);
        Pago updatedPago = pagoRepository.save(existingPago);
        return modelMapper.map(updatedPago, PagoDTO.class);
    }

    @Override
    public void delete(Long id) {
        pagoRepository.deleteById(id);
    }

    @Override
    public List<PagoDTO> getAll() {
        List<Pago> pagos = pagoRepository.findAll();
        return pagos.stream()
                .map(pago -> modelMapper.map(pago, PagoDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public PagoDTO getById(Long id) {
        Pago pago = pagoRepository.findById(id).orElseThrow(() -> new RuntimeException("Pago not found"));
        return modelMapper.map(pago, PagoDTO.class);
    }

    @Override
    public List<PagoDTO> getByEstadoPago(String estadoPago) {
        List<Pago> pagos = pagoRepository.findByEstadoPago(Pago.EstadoPago.valueOf(estadoPago));
        return pagos.stream()
                .map(pago -> modelMapper.map(pago, PagoDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<PagoDTO> getByPedidoId(Long pedidoId) {
        List<Pago> pagos = pagoRepository.findByPedidoId(pedidoId);
        return pagos.stream()
                .map(pago -> modelMapper.map(pago, PagoDTO.class))
                .collect(Collectors.toList());
    }
}
