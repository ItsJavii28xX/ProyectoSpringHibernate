package com.javijaime.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.javijaime.demo.model.Usuario;
import com.javijaime.demo.repository.UsuarioRepository;

@Service
public class ServicioUsuario implements UserDetailsService {

    @Autowired
    private UsuarioRepository usuarioRepositorio;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return (UserDetails) usuarioRepositorio.findByNombreUsuario(username);
    }
}
