package com.javijaime.demo.repository;

import com.javijaime.demo.model.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductoRepository extends JpaRepository<Producto, Long> {

    List<Producto> findByNombre(String nombre);

    List<Producto> findAllByOrderByNombreAsc();

    List<Producto> findByPrecioGreaterThan(Double precio);

}
