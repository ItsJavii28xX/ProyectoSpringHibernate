package com.javijaime.demo.controller;

import com.javijaime.demo.model.Producto;
import com.javijaime.demo.repository.ProductoRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/productos")
@Tag(name = "Productos", description = "Operaciones relacionadas con los productos")
public class ProductoController {

    @Autowired
    private ProductoRepository productoRepository;

    @Operation(summary = "Obtener todos los productos")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de productos obtenida")
    })
    @PreAuthorize("hasRole('USER')")
    @GetMapping
    public List<Producto> getAllProductos() {
        return productoRepository.findAll();
    }

    @Operation(summary = "Obtener producto por ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Producto encontrado"),
            @ApiResponse(responseCode = "404", description = "Producto no encontrado")
    })
    @PreAuthorize("hasRole('USER')")
    @GetMapping("/{id}")
    public Producto getProductoById(@PathVariable Long id) {
        return productoRepository.findById(id).orElse(null);
    }

    @Operation(summary = "Obtener productos por nombre")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de productos encontrada")
    })
    @PreAuthorize("hasRole('USER')")
    @GetMapping("/nombre/{nombre}")
    public List<Producto> getProductosByName(@PathVariable String nombre) {
        return productoRepository.findByNombre(nombre);
    }

    @Operation(summary = "Obtener productos por precio mayor que")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de productos encontrada")
    })
    @PreAuthorize("hasRole('USER')")
    @GetMapping("/precio/{precio}")
    public List<Producto> getProductosByPrecioGreaterThan(@PathVariable Double precio) {
        return productoRepository.findByPrecioGreaterThan(precio);
    }

    @Operation(summary = "Obtener productos ordenados por nombre")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de productos ordenada obtenida")
    })
    @PreAuthorize("hasRole('USER')")
    @GetMapping("/ordenados")
    public List<Producto> getProductosOrdenadosPorNombre() {
        return productoRepository.findAllByOrderByNombreAsc();
    }

    @Operation(summary = "Crear un nuevo producto")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Producto creado")
    })
    @PreAuthorize("hasRole('USER')")
    @PostMapping
    public Producto createProducto(@RequestBody Producto producto) {
        return productoRepository.save(producto);
    }

    @Operation(summary = "Actualizar un producto existente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Producto actualizado"),
            @ApiResponse(responseCode = "404", description = "Producto no encontrado")
    })
    @PreAuthorize("hasRole('USER')")
    @PutMapping("/{id}")
    public Producto updateProducto(@PathVariable Long id, @RequestBody Producto producto) {
        producto.setId(id);
        return productoRepository.save(producto);
    }

    @Operation(summary = "Eliminar un producto")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Producto eliminado")
    })
    @PreAuthorize("hasRole('USER')")
    @DeleteMapping("/{id}")
    public void deleteProducto(@PathVariable Long id) {
        productoRepository.deleteById(id);
    }
}