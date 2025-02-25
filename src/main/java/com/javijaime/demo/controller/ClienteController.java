package com.javijaime.demo.controller;

import com.javijaime.demo.model.Cliente;
import com.javijaime.demo.repository.ClienteRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/clientes")
@Tag(name = "Clientes", description = "Operaciones relacionadas con los clientes")
public class ClienteController {

    @Autowired
    private ClienteRepository clienteRepository;

    @Operation(summary = "Obtener todos los clientes")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de clientes obtenida")
    })
    @PreAuthorize("hasRole('USER')")
    @GetMapping
    public List<Cliente> getAllClientes() {
        return clienteRepository.findAll();
    }

    @Operation(summary = "Obtener cliente por ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Cliente encontrado"),
            @ApiResponse(responseCode = "404", description = "Cliente no encontrado")
    })
    @PreAuthorize("hasRole('USER')")
    @GetMapping("/{id}")
    public Cliente getClienteById(@NotNull @PathVariable Long id) {
        return clienteRepository.findById(id).orElse(null);
    }

    @Operation(summary = "Obtener clientes por nombre")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de clientes encontrada")
    })
    @PreAuthorize("hasRole('USER')")
    @GetMapping("/nombre/{nombre}")
    public List<Cliente> getClientesByName(@NotNull @PathVariable String nombre) {
        return clienteRepository.findByNombre(nombre);
    }

    @Operation(summary = "Crear un nuevo cliente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Cliente creado")
    })
    @PreAuthorize("hasRole('USER')")
    @PostMapping
    public Cliente createCliente(@NotNull @RequestBody Cliente cliente) {
        return clienteRepository.save(cliente);
    }

    @Operation(summary = "Actualizar un cliente existente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Cliente actualizado"),
            @ApiResponse(responseCode = "404", description = "Cliente no encontrado")
    })
    @PreAuthorize("hasRole('USER')")
    @PutMapping("/{id}")
    public Cliente updateCliente(@NotNull @PathVariable Long id, @NotNull @RequestBody Cliente cliente) {
        cliente.setId(id);
        return clienteRepository.save(cliente);
    }

    @Operation(summary = "Eliminar un cliente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Cliente eliminado")
    })
    @PreAuthorize("hasRole('USER')")
    @DeleteMapping("/{id}")
    public void deleteCliente(@NotNull @PathVariable Long id) {
        clienteRepository.deleteById(id);
    }
}