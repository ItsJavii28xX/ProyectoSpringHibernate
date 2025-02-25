package com.javijaime.demo.controller;

import com.javijaime.demo.model.Proveedor;
import com.javijaime.demo.repository.ProveedorRepository;
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
@RequestMapping("/proveedores")
@Tag(name = "Proveedores", description = "Operaciones relacionadas con los proveedores")
public class ProveedorController {

    @Autowired
    private ProveedorRepository proveedorRepository;

    @Operation(summary = "Obtener todos los proveedores")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de proveedores obtenida")
    })
    @PreAuthorize("hasRole('USER')")
    @GetMapping
    public List<Proveedor> getAllProveedores() {
        return proveedorRepository.findAll();
    }

    @Operation(summary = "Obtener proveedor por ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Proveedor encontrado"),
            @ApiResponse(responseCode = "404", description = "Proveedor no encontrado")
    })
    @PreAuthorize("hasRole('USER')")
    @GetMapping("/{id}")
    public Proveedor getProveedorById(@NotNull @PathVariable Long id) {
        return proveedorRepository.findById(id).orElse(null);
    }

    @Operation(summary = "Crear un nuevo proveedor")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Proveedor creado")
    })
    @PreAuthorize("hasRole('USER')")
    @PostMapping
    public Proveedor createProveedor(@RequestBody Proveedor proveedor) {
        return proveedorRepository.save(proveedor);
    }

    @Operation(summary = "Actualizar un proveedor existente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Proveedor actualizado"),
            @ApiResponse(responseCode = "404", description = "Proveedor no encontrado")
    })
    @PreAuthorize("hasRole('USER')")
    @PutMapping("/{id}")
    public Proveedor updateProveedor(@PathVariable Long id, @RequestBody Proveedor proveedor) {
        proveedor.setId(id);
        return proveedorRepository.save(proveedor);
    }

    @Operation(summary = "Eliminar un proveedor")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Proveedor eliminado")
    })
    @PreAuthorize("hasRole('USER')")
    @DeleteMapping("/{id}")
    public void deleteProveedor(@PathVariable Long id) {
        proveedorRepository.deleteById(id);
    }

    @Operation(summary = "Obtener proveedores por nombre de producto")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de proveedores obtenida")
    })
    @PreAuthorize("hasRole('USER')")
    @GetMapping("/producto/{nombreProducto}")
    public List<Proveedor> getProveedoresByProducto(@PathVariable String nombreProducto) {
        return proveedorRepository.findByProductoNombre(nombreProducto);
    }
}