package com.techlab.ecommerce.validator;

import com.techlab.ecommerce.exception.ProductoException;

public class Validadores {
	
	private static final String EMAIL_REGEX = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";
	private static final String NOMBRE_REGEX = "^[\\p{L}ÁÉÍÓÚáéíóúñÑ ]{3,50}$";
	private static final String DOUBLE_REGEX = "^\\d+(\\.\\d+)?$";
	private static final String INT_REGEX = "^\\d+$";
	
	public static boolean validarNombre(String nombre) {
		if (nombre == null || !nombre.matches(NOMBRE_REGEX)) {
            throw new ProductoException("Debe ingresar un nombre válido.");
        }
		return true;
    }
	
	public static boolean validarEmail(String email) {
		 if (email == null || !email.matches(EMAIL_REGEX)) {
	            throw new ProductoException("Debe ingregar un email válido. Ej: xxxx@mail.com");
	        }
		return true;
   }
	
	public static double validarPrecio(String entrada) {
		double precio = 0;
		if (entrada == null || !entrada.matches(DOUBLE_REGEX)) {
			throw new ProductoException("El precio debe ser un número válido.");
		}
		precio = Double.parseDouble(entrada);
		if (precio <= 0) {
            throw new ProductoException("El precio debe ser mayor a cero.");
        }
		return precio;
    }
	
	public static int validarStock(String entrada) {
		int stock = 0;
		if (entrada == null || !entrada.matches(INT_REGEX)) {
			throw new ProductoException("El Stock debe ser un número válido.");
		}
		stock = Integer.parseInt(entrada);
		if (stock < 0) {
            throw new ProductoException("El Stock debe ser mayor a cero.");
        }
		return stock;
    }
	
	public static int validarId(String entrada) {
		int id = 0;
		if (entrada == null || !entrada.matches(INT_REGEX)) {
			throw new ProductoException("El Id debe ser un número válido.");
		}
		id = Integer.parseInt(entrada);
		if (id < 0) {
            throw new ProductoException("El Id debe ser mayor a cero.");
        }
		return id;
    }
	
	public static int validarOpcion(String entrada) {
		if (entrada == null || !entrada.matches(INT_REGEX)) {
			throw new ProductoException("La opción seleccionada debe ser un número válido.");
		}
		return Integer.parseInt(entrada);
    }
	
	public static int validarCantidad(String entrada) {
		int cantidad = 0;
		if (entrada == null || !entrada.matches(INT_REGEX)) {
			throw new ProductoException("La cantidad debe ser un número válido.");
		}
		cantidad = Integer.parseInt(entrada);
		if (cantidad < 0) {
            throw new ProductoException("La cantidad debe ser mayor a cero.");
        }
		return cantidad;
    }
	
}