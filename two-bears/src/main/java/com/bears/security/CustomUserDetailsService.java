package com.bears.security;

import com.bears.model.Usuario;
import com.bears.repository.UsuarioRepository;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private UsuarioRepository usuarioRepository;

    public CustomUserDetailsService() {
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // Buscar al usuario en la base de datos
        Usuario usuario = usuarioRepository.findByCorreo(username)
                .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado con el correo: " + username));

        // Crear un objeto UserDetails, que contiene los detalles del usuario
        return User.builder()
                .username(usuario.getCorreo()) // Correo como username
                .password(usuario.getClave())  // Contraseña
                .roles(usuario.getRole().getNombre())
                .build();
    }
}
