package com.techlab.ecommerce.entrega.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.techlab.ecommerce.entrega.entity.Pedido;
import com.techlab.ecommerce.entrega.entity.PedidoProducto;
import com.techlab.ecommerce.entrega.entity.Producto;
import java.util.Optional;

public interface PedidoProductoRepository extends JpaRepository<PedidoProducto, Integer> {
    Optional<PedidoProducto> findByPedidoAndProducto(Pedido carrito, Producto producto);
}