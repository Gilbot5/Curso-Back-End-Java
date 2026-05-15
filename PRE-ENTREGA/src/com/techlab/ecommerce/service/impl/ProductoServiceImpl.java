package com.techlab.ecommerce.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.techlab.ecommerce.exception.ProductoException;
import com.techlab.ecommerce.model.Producto;
import com.techlab.ecommerce.service.ProductoService;
import com.techlab.ecommerce.validator.Validadores;

public class ProductoServiceImpl implements ProductoService {
	
	List <Producto> productos = new ArrayList<>();
	PedidoServiceImpl pedido = new PedidoServiceImpl();
	Scanner scanner = new Scanner(System.in);
	String entrada = "";
	
	@Override
	public Producto agregarProducto() {
		try {
			System.out.println("Ingrese nombre: ");
			String nombre = scanner.next();
			Validadores.validarNombre(nombre);
			
			System.out.println("Ingrese precio: ");
			entrada = scanner.next();
			double precio = Validadores.validarPrecio(entrada);
		
			System.out.println("Ingrese cantidad: ");
			entrada = scanner.next();
			int cantidad = Validadores.validarCantidad(entrada);
			
			Producto producto = new Producto(nombre, precio, cantidad);
			productos.add(producto);
			System.out.println("Producto agregado: " + producto.toString());
			return producto;
			
		} catch (ProductoException e) {
			System.out.println("Error al ingresar datos: " + e.getMessage());
			scanner.nextLine();
			return null;
		}
	}

	@Override
	public void listarProductos() {
		if(productos.isEmpty()) {
			System.out.println("No hay productos creados para mostrar.");
		}else {
			System.out.println("Lista de Productos:");
			for (Producto aux: productos) {
	            System.out.println(aux);
	        }
		}
	}

	@Override
	public void buscarProducto() {
		try {
			Producto encontrado = buscandoProducto();
			if (encontrado != null) {
				System.out.println(encontrado);
				System.out.println("¿Desea actualizar este producto? (s/n)");
				String actualizar = scanner.next();
				if (actualizar.equalsIgnoreCase("s")) {
					actualizarProducto(encontrado);
				}
			}
		}catch (ProductoException e) {
			System.out.println("Error: " + e.getMessage());
			scanner.nextLine();
		}
	}
	
	@Override
	public void eliminarProducto() {
		try {
			Producto encontrado = buscandoProducto();
			if(encontrado != null) {
				for (Producto aux: productos) {
			        if (aux.getNombre().equalsIgnoreCase(encontrado.getNombre())) {
			        	System.out.println("¿Confirmar eliminación? (s/n)");
						String confirmar = scanner.next();
						if (confirmar.equalsIgnoreCase("s")) {
							productos.remove(aux);
							System.out.println("El producto " + encontrado.getNombre() + " fue eliminido con exito.");
							break;
						} else {
							System.out.println("Eliminación cancelada.");
						}
			        }
				}
			}
		}catch (ProductoException e) {
			System.out.println("Error: " + e.getMessage());
			scanner.nextLine();
		}
	}

	@Override
	public void actualizarProducto(Producto producto) {
		try {
			System.out.println("Ingrese nuevo nombre: ");
			String nombre = scanner.next();
			Validadores.validarNombre(nombre);
			producto.setNombre(nombre);
			
			System.out.println("Ingrese nuevo precio: ");
			entrada = scanner.next();
			double precio = Validadores.validarPrecio(entrada);
			producto.setPrecio(precio);
			
			System.out.println("Ingrese nueva cantidad: ");
			entrada = scanner.next();
			int cantidad = Validadores.validarCantidad(entrada);
			producto.setCantidadEnStock(cantidad);
			
			System.out.println("Producto actualizado: " + producto);
		}catch (ProductoException e) {
			System.out.println("Error: " + e.getMessage());
			scanner.nextLine();
		}
	}
	
	public Producto buscandoProducto() {
			String nombre = "";
			int id = 0;
			Producto encontrado = null;
			try {
				System.out.println("Buscar Producto: 1)Por Id, 2) Por Nombre");
				entrada = scanner.next();
				int opcion = Validadores.validarId(entrada);
				if (opcion == 1) {
					System.out.println("Ingrese el id del producto: ");
					entrada = scanner.next();
					id = Validadores.validarId(entrada);
					for (Producto aux: productos) {
						if (aux.getId() == id) {
							encontrado = aux;
							break;
						}else {
							System.out.println("Producto no encontrado.");
							break;
						}
					}
				}
				else if (opcion == 2) {
					System.out.println("Ingrese el nombre del producto: ");
					nombre = scanner.next();
					Validadores.validarNombre(nombre);
					for (Producto aux: productos) {
						if (aux.getNombre().equalsIgnoreCase(nombre)) {
							encontrado = aux;
							break;
						}else {
							System.out.println("Producto no encontrado.");
							break;
						}
					}
				}
				else if (opcion <= 0 || opcion > 2) {
					System.out.println("Opción inválida, Seleccione 1 o 2.");
				}
				return encontrado;
		}catch(ProductoException e) {
			System.out.println("Error: " + e.getMessage());
			scanner.nextLine();
			return null;
		}
	}
	
	public void crearPedido() {
		pedido.crearPedidos(productos);
	}
	
	public void listarPedidos() {
		pedido.listarPedidos();
	}
	
}