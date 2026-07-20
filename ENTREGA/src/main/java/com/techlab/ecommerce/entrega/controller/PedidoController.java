package com.techlab.ecommerce.entrega.controller;

import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.techlab.ecommerce.entrega.entity.Pedido;
import com.techlab.ecommerce.entrega.service.PedidoService;

@RestController
@RequestMapping("/pedidos")
public class PedidoController {

	private final PedidoService service;

    public PedidoController(PedidoService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<Pedido>> listarTodos() {
        return ResponseEntity.ok(service.listarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Pedido> obtenerPedido(@PathVariable Integer id) {
        return ResponseEntity.ok(service.obtenerPorId(id));
    }

    @PostMapping
    public ResponseEntity<Pedido> crear() {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.crear());
    }

    @PostMapping("/{pedidoId}/productos/{productoId}")
    public ResponseEntity<Pedido> agregarProducto(
            @PathVariable Integer pedidoId,
            @PathVariable Integer productoId) {
        return ResponseEntity.ok(service.agregarProducto(pedidoId, productoId));
    }

    @DeleteMapping("/{id}/vaciar")
    public ResponseEntity<Pedido> vaciar(@PathVariable Integer id) {
        return ResponseEntity.ok(service.vaciar(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Integer id) {
        service.eliminar(id);
        return ResponseEntity.ok().build();
    }
}
