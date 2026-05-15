package com.techlab.ecommerce.app;

import java.util.Scanner;

import com.techlab.ecommerce.exception.ProductoException;
import com.techlab.ecommerce.service.impl.ProductoServiceImpl;
import com.techlab.ecommerce.validator.Validadores;

public class Main {
	
	public static void main(String[] args) {

    	Scanner scanner = new Scanner(System.in);
        ProductoServiceImpl producto = new ProductoServiceImpl();
        boolean terminar = false;
	        while(!terminar) {
	        	try {
		        	Menu.getMenu();
		        	String entrada = scanner.next();
		     		int opcion = Validadores.validarOpcion(entrada);
		     		switch (opcion) {
				        case 1:
				        	producto.agregarProducto();
				        	Limpieza.limpiarPantalla();
				        	break;
				        case 2:
				        	producto.listarProductos();
				        	Limpieza.limpiarPantalla();
				        	break;
				        case 3:
				        	producto.buscarProducto();
				        	Limpieza.limpiarPantalla();
				        	break;
				        case 4:
				        	producto.eliminarProducto();
				        	Limpieza.limpiarPantalla();
				        	break;
				        case 5:
				        	producto.crearPedido();
				        	Limpieza.limpiarPantalla();
				        	break;
				        case 6:
				        	producto.listarPedidos();
				        	Limpieza.limpiarPantalla();
				        	break;
				        case 7:
				        	terminar = true;
				        	break;
				        default:
				        	 System.out.println("La opción elegida no esta incluida, por favor selecciona del 1 al 7.");
				        	break;
				        }
	        	}catch (ProductoException e) {
	        		System.out.println("Error: " + e.getMessage());
	        		scanner.nextLine();
				}
	        }
        scanner.close();
        System.out.println("Programa finalizado..."); 
    }
}