package com.techlab.ecommerce.entrega.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.techlab.ecommerce.entrega.entity.Categoria;
import com.techlab.ecommerce.entrega.entity.Producto;
import com.techlab.ecommerce.entrega.exception.CategoriaNoEncontradaException;
import com.techlab.ecommerce.entrega.exception.ProductoNoEncontradoException;
import com.techlab.ecommerce.entrega.repository.CategoriaRepository;
import com.techlab.ecommerce.entrega.repository.ProductoRepository;

@Service
public class ProductoService {

    private final ProductoRepository repository;
    private final CategoriaRepository categoriaRepository;

    public ProductoService(ProductoRepository repository) {
        this(repository, null);
    }

    @Autowired
    public ProductoService(ProductoRepository repository, CategoriaRepository categoriaRepository) {
        this.repository = repository;
        this.categoriaRepository = categoriaRepository;
    }

    public Producto guardar(Producto p) {
        resolverCategoria(p);
        return repository.save(p);
    }

    public List<Producto> listarTodos() {
        return repository.findAll();
    }

    public Producto obtenerPorId(Integer id) {

        return repository.findById(id)
                .orElseThrow(() -> new ProductoNoEncontradoException("No se encontró un producto con id " + id));
    }

    public Producto actualizar(Integer id, Producto datos) {
        Producto p = obtenerPorId(id);

        p.setNombre(datos.getNombre());
        p.setPrecio(datos.getPrecio());
        p.setCantidadEnStock(datos.getCantidadEnStock());
        if (datos.getCategoria() != null) {
            resolverCategoria(datos);
            p.setCategoria(datos.getCategoria());
        }
        if (datos.getImagenUrl() != null) {
            p.setImagenUrl(datos.getImagenUrl());
        };

        return repository.save(p);
    }

    public void eliminar(Integer id) {
        Producto p = obtenerPorId(id);
        repository.delete(p);
    }

    public List<Producto> buscarPorNombre(String nombre) {
        return repository.findByNombreContaining(nombre);
    }

    public List<Producto> buscarPorCategoria(String categoria) {
        return repository.findByCategoriaNombreContainingIgnoreCase(categoria);
    }

    private void resolverCategoria(Producto producto) {
        if (producto.getCategoria() == null || producto.getCategoria().getId() == null || categoriaRepository == null) {
            return;
        }

        Categoria categoriaPersistida = categoriaRepository.findById(producto.getCategoria().getId())
                .orElseThrow(() -> new CategoriaNoEncontradaException(
                        "No se encontró una categoría con id " + producto.getCategoria().getId()));

        producto.setCategoria(categoriaPersistida);
    }
}
