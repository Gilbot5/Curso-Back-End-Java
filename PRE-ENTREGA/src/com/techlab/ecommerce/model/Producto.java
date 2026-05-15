package com.techlab.ecommerce.model;

public class Producto {
    
	private static int contadorId = 1;
	private int id;
	private String nombre;
	private double precio;
	private int cantidadEnStock;

	public Producto() {
	        this.id = contadorId++;
	}

    public Producto(String nombre, double precio, int cantidadEnStock) {
    	this.id = contadorId++;
        this.nombre = nombre;
        this.precio = precio;
        this.cantidadEnStock = cantidadEnStock;
    }

    public String getNombre() {
        return nombre;
    }

    public double getPrecio() {
        return precio;
    }

    public int getCantidadEnStock() {
        return cantidadEnStock;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public void setCantidadEnStock(int cantidadEnStock) {
        this.cantidadEnStock = cantidadEnStock;
    }
    
    public int getId() {
        return id;
    }

    public String toString() {
        return "ID: " + id + ", Producto: " + nombre + ", Precio: " + precio + ", Cantidad: " + cantidadEnStock;
    }

}