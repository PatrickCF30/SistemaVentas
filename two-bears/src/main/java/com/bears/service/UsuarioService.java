package com.bears.service;

import com.bears.model.Usuario;

import java.util.List;
import java.util.Optional;

public interface UsuarioService {
    List<Usuario> listarUsuarios();

    Optional<Usuario> obtenerUsuarioPorId(Long id);

    Usuario guardarUsuario(Usuario usuario);

    void eliminarUsuario(Long id);
}
