package com.javijaime.demo.controller;

import com.javijaime.demo.model.Producto;
import com.javijaime.demo.repository.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/productos")
public class ProductoController {

    @Autowired
    private ProductoRepository productoRepository;

    @GetMapping
    public List<Producto> getAllProductos() {
        return productoRepository.findAll();
    }

    @GetMapping("/{id}")
    public Producto getProductoById(@PathVariable Long id) {
        return productoRepository.findById(id).orElse(null);
    }

    @GetMapping("/nombre/{nombre}")
    public List<Producto> getProductosByName(@PathVariable String nombre) {
        return productoRepository.findByNombre(nombre);
    }

    @GetMapping("/precio/{precio}")
    public List<Producto> getProductosByPrecioGreaterThan(@PathVariable Double precio) {
        return productoRepository.findByPrecioGreaterThan(precio);
    }

    @GetMapping("/ordenados")
    public List<Producto> getProductosOrdenadosPorNombre() {
        return productoRepository.findAllByOrderByNombreAsc();
    }

    @PostMapping
    public Producto createProducto(@RequestBody Producto producto) {
        return productoRepository.save(producto);
    }

    @PutMapping("/{id}")
    public Producto updateProducto(@PathVariable Long id, @RequestBody Producto producto) {
        producto.setId(id);
        return productoRepository.save(producto);
    }

    @DeleteMapping("/{id}")
    public void deleteProducto(@PathVariable Long id) {
        productoRepository.deleteById(id);
    }
}
