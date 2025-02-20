package com.javijaime.demo.controller;

import com.javijaime.demo.model.Cliente;
import com.javijaime.demo.model.DetallePedido;
import com.javijaime.demo.repository.DetallePedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/detalles-pedido")
public class DetallePedidoController {

    @Autowired
    private DetallePedidoRepository detallePedidoRepository;

    @GetMapping
    public List<DetallePedido> getAllDetallesPedido() {
        return detallePedidoRepository.findAll();
    }

    @GetMapping("/{id}")
    public DetallePedido getDetallePedidoById(@PathVariable Long id) {
        return detallePedidoRepository.findById(id).orElse(null);
    }

    @PostMapping
    public DetallePedido createDetallePedido(@RequestBody DetallePedido detallePedido) {
        return detallePedidoRepository.save(detallePedido);
    }

    @PutMapping("/{id}")
    public DetallePedido updateDetallePedido(@PathVariable Long id, @RequestBody DetallePedido detallePedido) {
        detallePedido.setId(id);
        return detallePedidoRepository.save(detallePedido);
    }

    @DeleteMapping("/{id}")
    public void deleteDetallePedido(@PathVariable Long id) {
        detallePedidoRepository.deleteById(id);
    }

    @GetMapping("/clientes/{nombreProducto}")
    public List<Cliente> getClientesByNombreProducto(@PathVariable String nombreProducto) {
        return detallePedidoRepository.findClientesByNombreProducto(nombreProducto);
    }
}
