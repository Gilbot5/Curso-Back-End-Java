package com.techlab.ecommerce.entrega.service;

import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;
import com.techlab.ecommerce.entrega.entity.Pedido;
import com.techlab.ecommerce.entrega.entity.PedidoProducto;
import com.techlab.ecommerce.entrega.entity.Producto;
import com.techlab.ecommerce.entrega.exception.PedidoNoEncontradoException;
import com.techlab.ecommerce.entrega.exception.ProductoNoEncontradoException;
import com.techlab.ecommerce.entrega.exception.StockInsuficienteException;
import com.techlab.ecommerce.entrega.repository.PedidoProductoRepository;
import com.techlab.ecommerce.entrega.repository.PedidoRepository;
import com.techlab.ecommerce.entrega.repository.ProductoRepository;

@Service
public class PedidoService {

	private final PedidoRepository pedidoRepository;
    private final ProductoService productoService;
    private final PedidoProductoRepository pedidoProductoRepository;

    public PedidoService(PedidoRepository pedidoRepository, PedidoProductoRepository pedidoProductoRepository, ProductoService productoService) {
        this.pedidoRepository = pedidoRepository;
        this.productoService = productoService;
        this.pedidoProductoRepository = pedidoProductoRepository;                 
    }

    public Pedido crear() {
        return pedidoRepository.save(new Pedido());
    }

    public Pedido obtenerPorId(Integer id) {
        return pedidoRepository.findById(id)
                .orElseThrow(() -> new PedidoNoEncontradoException(
                        "No se encontró un Pedido con id " + id));
    }

    public List<Pedido> listarTodos() {
        return pedidoRepository.findAll();
    }

    public Pedido agregarProducto(Integer pedidoId, Integer productoId) {
        Pedido pedido = obtenerPorId(pedidoId);
        Producto producto = productoService.obtenerPorId(productoId);

        if (producto.getCantidadEnStock() <= 0) {
            throw new StockInsuficienteException(
                    "El producto \"" + producto.getNombre() + "\" no tiene stock disponible.");
        }
        
        Optional<PedidoProducto> existente = pedidoProductoRepository.findByPedidoAndProducto(pedido, producto);

        if(existente.isPresent()){
        	
            PedidoProducto cp = existente.get();
            cp.setCantidad(cp.getCantidad() + 1);
            pedidoProductoRepository.save(cp);

        }else{
        	
            PedidoProducto nuevo = new PedidoProducto(null, pedido, producto, 1);
            pedidoProductoRepository.save(nuevo);
        }
        
        producto.setCantidadEnStock(producto.getCantidadEnStock() - 1);
        productoService.guardar(producto);

        return pedidoRepository.save(pedido);
        
    }

    public Pedido vaciar(Integer id) {
    	
        Pedido Pedido = obtenerPorId(id);
        Pedido.getProductos().clear();
        return pedidoRepository.save(Pedido);
        
    }

    public void eliminar(Integer id) {
    	
        Pedido pedido = obtenerPorId(id);
        pedidoRepository.delete(pedido);
        
    }
}
