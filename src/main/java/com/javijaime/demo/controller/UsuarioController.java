package com.javijaime.demo.controller;

import com.javijaime.demo.model.Usuario;
import com.javijaime.demo.repository.UsuarioRepository;
import com.javijaime.demo.service.ServicioAutenticacion;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;

@RestController
@RequestMapping("/usuarios")
@Tag(name = "Usuarios", description = "Operaciones relacionadas con los usuarios")
public class UsuarioController {

    @Autowired
    private ServicioAutenticacion servicioAutenticacion;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Operation(summary = "Iniciar sesión")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Inicio de sesión exitoso"),
            @ApiResponse(responseCode = "401", description = "Credenciales inválidas")
    })
    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody Usuario usuario) {
        // Obtener el usuario de la base de datos
        Usuario usuarioDB = usuarioRepository.findByNombreUsuario(usuario.getNombreUsuario());

        // Validar si el usuario existe y la contraseña es correcta
        if (usuarioDB!= null && usuario.getContrasena().equals(usuarioDB.getContrasena())) {

            String token = servicioAutenticacion.generarToken(usuario.getNombreUsuario());

            System.out.println(token);
            System.out.println(Arrays.toString(servicioAutenticacion.getSecretKey().getEncoded()));

            return new ResponseEntity<>(token, HttpStatus.OK);
        } else {

            return new ResponseEntity<>("Credenciales inválidas", HttpStatus.UNAUTHORIZED);
        }
    }
}