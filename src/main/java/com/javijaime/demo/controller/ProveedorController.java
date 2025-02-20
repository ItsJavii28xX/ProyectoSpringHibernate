package com.javijaime.demo.controller;

import com.javijaime.demo.model.Proveedor;
import com.javijaime.demo.repository.ProveedorRepository;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/proveedores")
public class ProveedorController {

    @Autowired
    private ProveedorRepository proveedorRepository;

    @PreAuthorize("hasRole('USER')")
    @GetMapping
    public List<Proveedor> getAllProveedores() {
        return proveedorRepository.findAll();
    }

    @PreAuthorize("hasRole('USER')")
    @GetMapping("/{id}")
    public Proveedor getProveedorById(@NotNull @PathVariable Long id) {
        return proveedorRepository.findById(id).orElse(null);
    }

    @PreAuthorize("hasRole('USER')")
    @PostMapping
    public Proveedor createProveedor(@RequestBody Proveedor proveedor) {
        return proveedorRepository.save(proveedor);
    }

    @PreAuthorize("hasRole('USER')")
    @PutMapping("/{id}")
    public Proveedor updateProveedor(@PathVariable Long id, @RequestBody Proveedor proveedor) {
        proveedor.setId(id);
        return proveedorRepository.save(proveedor);
    }

    @PreAuthorize("hasRole('USER')")
    @DeleteMapping("/{id}")
    public void deleteProveedor(@PathVariable Long id) {
        proveedorRepository.deleteById(id);
    }

    @PreAuthorize("hasRole('USER')")
    @GetMapping("/producto/{nombreProducto}")
    public List<Proveedor> getProveedoresByProducto(@PathVariable String nombreProducto) {
        return proveedorRepository.findByProductoNombre(nombreProducto);
    }
}
