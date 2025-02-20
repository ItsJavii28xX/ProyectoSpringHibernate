package com.javijaime.demo.repository;

import com.javijaime.demo.model.Cliente;
import com.javijaime.demo.model.DetallePedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DetallePedidoRepository extends JpaRepository<DetallePedido, Long> {

    @Query("SELECT d.cliente FROM DetallePedido d " +
            "JOIN d.producto p " +
            "WHERE p.nombre = :nombreProducto")
    List<Cliente> findClientesByNombreProducto(@Param("nombreProducto") String nombreProducto);
}