package com.javijaime.demo;

import com.javijaime.demo.model.Usuario;
import com.javijaime.demo.repository.UsuarioRepository;
import com.javijaime.demo.service.ServicioUsuario;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ServicioUsuarioTest {

    @Mock
    private UsuarioRepository usuarioRepository;

    @InjectMocks
    private ServicioUsuario servicioUsuario;

    @Test
    public void loadUserByUsername_deberiaDevolverUsuarioValido() {
        Usuario usuario = new Usuario("testuser", "password");
        when(usuarioRepository.findByNombreUsuario("testuser")).thenReturn(usuario);

        UserDetails userDetails = servicioUsuario.loadUserByUsername("testuser");

        assertNotNull(userDetails);
        assertEquals("testuser", userDetails.getUsername());
    }

    @Test
    public void loadUserByUsername_deberiaLanzarExcepcionCuandoNoEncuentraUsuario() {
        when(usuarioRepository.findByNombreUsuario("testuser")).thenReturn(null);

        assertThrows(UsernameNotFoundException.class, () -> servicioUsuario.loadUserByUsername("testuser"));
    }
}
