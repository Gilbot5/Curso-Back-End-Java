package com.techlab.ecommerce.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

import com.techlab.ecommerce.exception.ProductoException;
import com.techlab.ecommerce.model.Cliente;
import com.techlab.ecommerce.model.Pedido;
import com.techlab.ecommerce.model.Producto;
import com.techlab.ecommerce.service.PedidoService;
import com.techlab.ecommerce.validator.Validadores;

public class PedidoServiceImpl implements PedidoService {

	Scanner scanner = new Scanner(System.in);
	String entrada = "";
	List <Pedido> pedidos = new ArrayList<>();
	ProductoServiceImpl producto;
	
	@Override
	public Pedido crearPedidos(List<Producto> productos) {	
		try {
			if (productos.isEmpty()) {
				System.out.println("No hay productos disponibles para realizar pedidos.");
				return null;
			}
			Cliente cliente = crearCliente();
			if(cliente == null) {
				throw new ProductoException("No se creo el cliente correctamente.");
			}
			HashMap<Producto, Integer> LineaPedido = new HashMap<>();
			boolean agregarMasProducto = true;
			while (agregarMasProducto && !productos.isEmpty()) {
				System.out.println("Productos disponibles:");
				for (Producto aux: productos) {
					System.out.println(aux);
				}
				
				System.out.println("Ingrese ID del producto a agregar (0 para terminar): ");
				entrada = scanner.next();
				int id = Validadores.validarId(entrada);
				if (id == 0) {
					agregarMasProducto = false;
				} else {
					Producto seleccionado = null;
					for (Producto aux : productos) {
						if (aux.getId() == id) {
							seleccionado = aux;
							break;
						}
					}
					if (seleccionado != null) {
						System.out.println("Ingrese cantidad: ");
						entrada = scanner.next();
						int cantidad = Validadores.validarCantidad(entrada);
						if (cantidad > seleccionado.getCantidadEnStock()) {
							throw new ProductoException("Stock insuficiente para " + seleccionado.getNombre());
						}
						LineaPedido.put(seleccionado, cantidad);
						seleccionado.setCantidadEnStock(seleccionado.getCantidadEnStock()-cantidad);
						System.out.println("¿Agregar otro producto? (s/n)");
						String continuar = scanner.next();
						if (!continuar.equalsIgnoreCase("s")) {
							agregarMasProducto = false;
						}
					} else {
						System.out.println("Producto no encontrado.");
					}
				}
			}
			Pedido pedido = new Pedido(LineaPedido, cliente);
			pedidos.add(pedido);
			System.out.println("Pedido creado:" + "\n" + pedido);
			
			return pedido;
		}catch (ProductoException e) {
			System.out.println("Error creando pedido: " + e.getMessage());
			scanner.nextLine();
			return null;
		}
	}

	@Override
	public void listarPedidos() {
		if(pedidos.isEmpty()) {
			System.out.println("No hay pedidos creados para mostrar.");
		}else {
			System.out.println("Lista de Pedidos:");
			for (Pedido aux: pedidos) {
				System.out.println(aux);
	        }
		}
	}
	
	private Cliente crearCliente() {
		try {
			System.out.println("Ingrese su nombre: ");
			String nombreCliente = scanner.next();
			Validadores.validarNombre(nombreCliente);
		
			System.out.println("Ingrese su email: ");
			String mailCliente = scanner.next();
			Validadores.validarEmail(mailCliente);
			
			return new Cliente(nombreCliente, mailCliente);
		}catch (ProductoException e) {
			System.out.println("Error: " + e.getMessage());
			scanner.nextLine();
			return null;
		}
	}

}