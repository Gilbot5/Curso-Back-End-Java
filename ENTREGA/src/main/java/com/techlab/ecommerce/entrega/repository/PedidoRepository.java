package com.techlab.ecommerce.entrega.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.techlab.ecommerce.entrega.entity.Pedido;

public interface PedidoRepository extends JpaRepository<Pedido, Integer> {
}