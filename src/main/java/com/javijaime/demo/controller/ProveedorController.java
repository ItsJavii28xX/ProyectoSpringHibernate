package com.javijaime.demo.controller;

import com.javijaime.demo.model.Proveedor;
import com.javijaime.demo.repository.ProveedorRepository;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/proveedores")
public class ProveedorController {

    @Autowired
    private ProveedorRepository proveedorRepository;

    @GetMapping
    public List<Proveedor> getAllProveedores() {
        return proveedorRepository.findAll();
    }

    @GetMapping("/{id}")
    public Proveedor getProveedorById(@NotNull @PathVariable Long id) {
        return proveedorRepository.findById(id).orElse(null);
    }

    @PostMapping
    public Proveedor createProveedor(@RequestBody Proveedor proveedor) {
        return proveedorRepository.save(proveedor);
    }

    @PutMapping("/{id}")
    public Proveedor updateProveedor(@PathVariable Long id, @RequestBody Proveedor proveedor) {
        proveedor.setId(id);
        return proveedorRepository.save(proveedor);
    }

    @DeleteMapping("/{id}")
    public void deleteProveedor(@PathVariable Long id) {
        proveedorRepository.deleteById(id);
    }

    @GetMapping("/producto/{nombreProducto}")
    public List<Proveedor> getProveedoresByProducto(@PathVariable String nombreProducto) {
        return proveedorRepository.findByProductoNombre(nombreProducto);
    }
}
