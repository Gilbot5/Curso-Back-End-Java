package com.techlab.ecommerce.entrega.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.techlab.ecommerce.entrega.entity.Categoria;

public interface CategoriaRepository extends JpaRepository<Categoria, Integer> {  
}