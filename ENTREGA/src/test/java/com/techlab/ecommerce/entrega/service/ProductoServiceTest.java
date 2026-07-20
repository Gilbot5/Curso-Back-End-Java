package com.techlab.ecommerce.entrega.service;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.mockito.ArgumentMatchers.any;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.mockito.junit.jupiter.MockitoExtension;

import com.techlab.ecommerce.entrega.entity.Categoria;
import com.techlab.ecommerce.entrega.entity.Producto;
import com.techlab.ecommerce.entrega.repository.CategoriaRepository;
import com.techlab.ecommerce.entrega.repository.ProductoRepository;

@ExtendWith(MockitoExtension.class)
class ProductoServiceTest {

    @Mock
    private ProductoRepository productoRepository;

    @Mock
    private CategoriaRepository categoriaRepository;

    @InjectMocks
    private ProductoService productoService;

    @Test
    void guardarDebeResolverLaCategoriaDesdeLaBaseDeDatos() {
        Categoria categoriaPersistida = new Categoria(2, "Tecnología", "Productos electrónicos");
        Producto producto = new Producto();
        producto.setNombre("Notebook");
        producto.setPrecio(10850.5);
        producto.setCantidadEnStock(5);
        producto.setCategoria(new Categoria());
        producto.getCategoria().setId(2);

        when(categoriaRepository.findById(2)).thenReturn(Optional.of(categoriaPersistida));
        when(productoRepository.save(any(Producto.class))).thenAnswer(invocation -> invocation.getArgument(0));

        Producto resultado = productoService.guardar(producto);

        assertEquals("Tecnología", resultado.getCategoria().getNombre());
        assertEquals("Productos electrónicos", resultado.getCategoria().getDescripcion());
        verify(productoRepository).save(producto);
    }
}
