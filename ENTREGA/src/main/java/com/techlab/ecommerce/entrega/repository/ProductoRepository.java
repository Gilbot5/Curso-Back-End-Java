package com.techlab.ecommerce.entrega.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.techlab.ecommerce.entrega.entity.Producto;

public interface ProductoRepository extends JpaRepository<Producto, Integer> {
    List<Producto> findByNombreContaining(String nombre);
    List<Producto> findByCategoriaNombreContainingIgnoreCase(String nombre);
}