package com.javijaime.demo.controller;

import com.javijaime.demo.model.Usuario;
import com.javijaime.demo.repository.UsuarioRepository;
import com.javijaime.demo.service.ServicioAutenticacion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private ServicioAutenticacion servicioAutenticacion;

    @Autowired
    private UsuarioRepository usuarioRepository;


    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody Usuario usuario) {
        // Obtener el usuario de la base de datos
        Usuario usuarioDB = usuarioRepository.findByNombreUsuario(usuario.getNombreUsuario());

        // Validar si el usuario existe y la contraseña es correcta
        if (usuarioDB!= null && usuario.getContrasena().equals(usuarioDB.getContrasena())) {

            String token = servicioAutenticacion.generarToken(usuario.getNombreUsuario());

            System.out.println(token);

            System.out.println(token);
            return new ResponseEntity<>(token, HttpStatus.OK);
        } else {

            return new ResponseEntity<>("Credenciales inválidas", HttpStatus.UNAUTHORIZED);
        }
    }
}