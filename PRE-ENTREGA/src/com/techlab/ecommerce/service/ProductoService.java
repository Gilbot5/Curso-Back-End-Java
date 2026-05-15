package com.techlab.ecommerce.service;

import com.techlab.ecommerce.model.Producto;

public interface ProductoService {
	
	Producto agregarProducto();
	
	void listarProductos();
	
	void buscarProducto();

	void eliminarProducto();
	
	void actualizarProducto(Producto p);
	
}
