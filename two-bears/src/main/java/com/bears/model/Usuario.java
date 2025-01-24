package com.bears.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "usuarios")
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;

    @Column(unique = true, nullable = false)
    private String correo;

    @Column(nullable = false)
    private String clave;

    private String telefono;

    private String direccion;

    @ManyToOne
    @JoinColumn(name = "role_id", nullable = false)
    private Role role;
}

