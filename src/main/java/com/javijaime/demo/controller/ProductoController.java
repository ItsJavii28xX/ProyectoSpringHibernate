package com.javijaime.demo.controller;

import com.javijaime.demo.model.Producto;
import com.javijaime.demo.repository.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/productos")
public class ProductoController {

    @Autowired
    private ProductoRepository productoRepository;

    @PreAuthorize("hasRole('USER')")
    @GetMapping
    public List<Producto> getAllProductos() {
        return productoRepository.findAll();
    }

    @PreAuthorize("hasRole('USER')")
    @GetMapping("/{id}")
    public Producto getProductoById(@PathVariable Long id) {
        return productoRepository.findById(id).orElse(null);
    }

    @PreAuthorize("hasRole('USER')")
    @GetMapping("/nombre/{nombre}")
    public List<Producto> getProductosByName(@PathVariable String nombre) {
        return productoRepository.findByNombre(nombre);
    }

    @PreAuthorize("hasRole('USER')")
    @GetMapping("/precio/{precio}")
    public List<Producto> getProductosByPrecioGreaterThan(@PathVariable Double precio) {
        return productoRepository.findByPrecioGreaterThan(precio);
    }

    @PreAuthorize("hasRole('USER')")
    @GetMapping("/ordenados")
    public List<Producto> getProductosOrdenadosPorNombre() {
        return productoRepository.findAllByOrderByNombreAsc();
    }

    @PreAuthorize("hasRole('USER')")
    @PostMapping
    public Producto createProducto(@RequestBody Producto producto) {
        return productoRepository.save(producto);
    }

    @PreAuthorize("hasRole('USER')")
    @PutMapping("/{id}")
    public Producto updateProducto(@PathVariable Long id, @RequestBody Producto producto) {
        producto.setId(id);
        return productoRepository.save(producto);
    }

    @PreAuthorize("hasRole('USER')")
    @DeleteMapping("/{id}")
    public void deleteProducto(@PathVariable Long id) {
        productoRepository.deleteById(id);
    }
}
