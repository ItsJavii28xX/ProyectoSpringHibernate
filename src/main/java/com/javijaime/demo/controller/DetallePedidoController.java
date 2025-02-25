package com.javijaime.demo.controller;

import com.javijaime.demo.model.Cliente;
import com.javijaime.demo.model.DetallePedido;
import com.javijaime.demo.repository.DetallePedidoRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/detalles-pedido")
@Tag(name = "Detalles de Pedido", description = "Operaciones relacionadas con los detalles de pedido")
public class DetallePedidoController {

    @Autowired
    private DetallePedidoRepository detallePedidoRepository;

    @Operation(summary = "Obtener todos los detalles de pedido")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de detalles de pedido obtenida")
    })
    @PreAuthorize("hasRole('USER')")
    @GetMapping
    public List<DetallePedido> getAllDetallesPedido() {
        return detallePedidoRepository.findAll();
    }

    @Operation(summary = "Obtener detalle de pedido por ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Detalle de pedido encontrado"),
            @ApiResponse(responseCode = "404", description = "Detalle de pedido no encontrado")
    })
    @PreAuthorize("hasRole('USER')")
    @GetMapping("/{id}")
    public DetallePedido getDetallePedidoById(@PathVariable Long id) {
        return detallePedidoRepository.findById(id).orElse(null);
    }

    @Operation(summary = "Crear un nuevo detalle de pedido")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Detalle de pedido creado")
    })
    @PreAuthorize("hasRole('USER')")
    @PostMapping
    public DetallePedido createDetallePedido(@RequestBody DetallePedido detallePedido) {
        return detallePedidoRepository.save(detallePedido);
    }

    @Operation(summary = "Actualizar un detalle de pedido existente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Detalle de pedido actualizado"),
            @ApiResponse(responseCode = "404", description = "Detalle de pedido no encontrado")
    })
    @PreAuthorize("hasRole('USER')")
    @PutMapping("/{id}")
    public DetallePedido updateDetallePedido(@PathVariable Long id, @RequestBody DetallePedido detallePedido) {
        detallePedido.setId(id);
        return detallePedidoRepository.save(detallePedido);
    }

    @Operation(summary = "Eliminar un detalle de pedido")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Detalle de pedido eliminado")
    })
    @PreAuthorize("hasRole('USER')")
    @DeleteMapping("/{id}")
    public void deleteDetallePedido(@PathVariable Long id) {
        detallePedidoRepository.deleteById(id);
    }

    @Operation(summary = "Obtener clientes por nombre de producto")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de clientes obtenida")
    })
    @PreAuthorize("hasRole('USER')")
    @GetMapping("/clientes/{nombreProducto}")
    public List<Cliente> getClientesByNombreProducto(@PathVariable String nombreProducto) {
        return detallePedidoRepository.findClientesByNombreProducto(nombreProducto);
    }
}