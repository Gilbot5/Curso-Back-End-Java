package com.techlab.ecommerce.service;

import java.util.List;
import com.techlab.ecommerce.model.Pedido;
import com.techlab.ecommerce.model.Producto;

public interface PedidoService {
	
	Pedido crearPedidos(List<Producto> productos);
	
	void listarPedidos();

}