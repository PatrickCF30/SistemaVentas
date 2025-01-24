package com.bears.dto;

import lombok.Data;

@Data
public class UsuarioDTO {
    private Long id;
    private String nombre;
    private String correo;
    private String clave;
    private String telefono;
    private String direccion;
    private String role;
}
