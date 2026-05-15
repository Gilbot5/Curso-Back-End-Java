package com.techlab.ecommerce.model;

import java.util.HashMap;

public class Pedido {

	private static int contadorId = 1;
	private HashMap<Producto, Integer> lineasPedido = new HashMap<>();
	private Cliente cliente;
    private int idPedido;
    private double total;

    public Pedido() {}
    
	public Pedido(HashMap<Producto, Integer> lineasPedido, Cliente cliente) {
		this.lineasPedido = lineasPedido;
		this.cliente = cliente;
		this.idPedido = contadorId++;
		this.total = calcularTotal();
	}
	
	public HashMap<Producto, Integer> getLineasPedido() {
		return lineasPedido;
	}

	public void setLineasPedido(HashMap<Producto, Integer> lineasPedido) {
		this.lineasPedido = lineasPedido;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public int getIdPedido() {
		return idPedido;
	}

	public double getTotal() {
		return total;
	}

	public void setTotal(double total) {
		this.total = total;
	}

	private double calcularTotal() {
		double total = 0;
		for (HashMap.Entry<Producto, Integer> aux : lineasPedido.entrySet()) {
			total += aux.getKey().getPrecio() * aux.getValue();
		}
		return total;
	}
	
	@Override
	public String toString() {
	    StringBuilder sb = new StringBuilder();
	    sb.append("Pedido:\n");
	    sb.append("idPedido: ").append(idPedido).append("\n");
	    sb.append(cliente).append("\n");
	    sb.append("LineasPedido: {");
	    for (HashMap.Entry<Producto, Integer> entry : lineasPedido.entrySet()) {
	        Producto producto = entry.getKey();
	        int cantidadPedida = entry.getValue();
	        sb.append("ID: ").append(producto.getId())
	          .append(", Producto: ").append(producto.getNombre())
	          .append(", Precio: ").append(producto.getPrecio())
	          .append(", Cantidad: ").append(cantidadPedida);
	    }
	    sb.append("}\n");
	    sb.append("Total a pagar: ").append(total).append("\n");
	    return sb.toString();
	}
}