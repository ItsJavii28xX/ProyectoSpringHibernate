package com.javijaime.demo.controller;

import com.javijaime.demo.model.Cliente;
import com.javijaime.demo.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.jetbrains.annotations.*;

import java.util.List;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

    @Autowired
    private ClienteRepository clienteRepository;

    @GetMapping
    public List<Cliente> getAllClientes() {
        return clienteRepository.findAll();
    }

    @GetMapping("/{id}")
    public Cliente getClienteById(@NotNull @PathVariable Long id) {
        return clienteRepository.findById(id).orElse(null);
    }

    @GetMapping("/nombre/{nombre}")
    public List<Cliente> getClientesByName(@NotNull @PathVariable String nombre) {
        return clienteRepository.findByNombre(nombre);
    }

    @PostMapping
    public Cliente createCliente(@NotNull @RequestBody Cliente cliente) {
        return clienteRepository.save(cliente);
    }

    @PutMapping("/{id}")
    public Cliente updateCliente(@NotNull @PathVariable Long id, @NotNull @RequestBody Cliente cliente) {
        cliente.setId(id);
        return clienteRepository.save(cliente);
    }

    @DeleteMapping("/{id}")
    public void deleteCliente(@NotNull @PathVariable Long id) {
        clienteRepository.deleteById(id);
    }
}