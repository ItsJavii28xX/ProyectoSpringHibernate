package com.javijaime.demo.repository;

import com.javijaime.demo.model.Proveedor;
import com.javijaime.demo.model.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProveedorRepository extends JpaRepository<Proveedor, Long> {

    List<Proveedor> findByDireccion(String direccion);

    @Query("SELECT p FROM Proveedor p " +
            "JOIN Producto prod ON prod.proveedor = p " +
            "WHERE prod.nombre = :nombreProducto")
    List<Proveedor> findByProductoNombre(@Param("nombreProducto") String nombreProducto);
}

