# Sistema de Gestión E-Commerce - TechLab

## Descripción del Proyecto

Este proyecto es una aplicación de consola en Java que simula un sistema básico de gestión para un e-commerce. Permite gestionar productos y pedidos de manera sencilla a través de un menú interactivo.

## Funcionalidades

### Gestión de Productos
- **Agregar Producto**: Permite crear nuevos productos especificando nombre, precio y cantidad en stock.
- **Listar Productos**: Muestra todos los productos registrados con sus detalles.
- **Buscar Producto**: Busca productos por ID o nombre, y opcionalmente permite actualizarlos.
- **Eliminar Producto**: Elimina productos existentes con confirmación.

### Gestión de Pedidos
- **Crear Pedido**: Permite crear pedidos asociando un cliente y seleccionando productos con cantidades.
- **Listar Pedidos**: Muestra todos los pedidos realizados con detalles completos.

## Estructura del Proyecto

```
src/
├── com/techlab/ecommerce/
│   ├── app/
│   │   ├── Main.java          # Punto de entrada de la aplicación
│   │   └── Menu.java          # Clase para mostrar el menú
│   ├── exception/
│   │   └── ProductoException.java  # Excepción personalizada
│   ├── model/
│   │   ├── Cliente.java       # Modelo de datos para clientes
│   │   ├── Pedido.java        # Modelo de datos para pedidos
│   │   └── Producto.java      # Modelo de datos para productos
│   ├── service/
│   │   ├── PedidoService.java     # Interfaz para servicios de pedidos
│   │   ├── ProductoService.java   # Interfaz para servicios de productos
│   │   └── impl/
│   │       ├── PedidoServiceImpl.java     # Implementación de servicios de pedidos
│   │       └── ProductoServiceImpl.java   # Implementación de servicios de productos
│   └── validator/
│       └── Validadores.java    # Clase para validaciones de entrada
bin/
└── com/techlab/ecommerce/       # Archivos compilados
```

## Modelos de Datos

### Producto
- `id`: Identificador único (auto-generado)
- `nombre`: Nombre del producto
- `precio`: Precio unitario
- `cantidadEnStock`: Cantidad disponible

### Cliente
- `nombre`: Nombre del cliente
- `email`: Correo electrónico

### Pedido
- `idPedido`: Identificador único del pedido
- `cliente`: Información del cliente
- `lineasPedido`: Mapa de productos y cantidades
- `total`: Total calculado del pedido

## Validaciones

El sistema incluye validaciones para:
- Nombres: Solo letras, espacios y caracteres especiales (mínimo 3 caracteres)
- Emails: Formato estándar de correo electrónico
- Precios: Números positivos
- Cantidades: Números enteros positivos
- IDs: Números enteros positivos

## Requisitos del Sistema

- Java 8 o superior
- Entorno de desarrollo con soporte para compilación de Java

## Compilación y Ejecución

### Compilación
Desde el directorio raíz del proyecto:

```bash
javac -d bin src/com/techlab/ecommerce/app/Main.java
```

### Ejecución
```bash
java -cp bin com.techlab.ecommerce.app.Main
```

## Uso de la Aplicación

1. **Inicio**: Al ejecutar, se muestra el menú principal.

2. **Agregar Producto**:
   - Seleccionar opción 1
   - Ingresar nombre, precio y cantidad
   - El producto se agrega automáticamente

3. **Listar Productos**:
   - Seleccionar opción 2
   - Se muestran todos los productos registrados

4. **Buscar/Actualizar Producto**:
   - Seleccionar opción 3
   - Elegir búsqueda por ID o nombre
   - Opcionalmente actualizar los datos

5. **Eliminar Producto**:
   - Seleccionar opción 4
   - Buscar el producto
   - Confirmar eliminación

6. **Crear Pedido**:
   - Seleccionar opción 5
   - Ingresar datos del cliente (nombre y email)
   - Seleccionar productos y cantidades
   - El pedido se crea y se actualiza el stock

7. **Listar Pedidos**:
   - Seleccionar opción 6
   - Se muestran todos los pedidos con detalles

8. **Salir**:
   - Seleccionar opción 7

## Manejo de Errores

- Validaciones de entrada con mensajes descriptivos
- Control de stock insuficiente al crear pedidos
- Confirmaciones para operaciones destructivas (eliminación)

## Notas Técnicas

- La aplicación utiliza memoria volátil (no persiste datos entre ejecuciones)
- Los IDs de productos y pedidos son auto-generados
- El stock se actualiza automáticamente al crear pedidos

## Mejoras Futuras

- Persistencia de datos (base de datos)
- Interfaz gráfica
- Autenticación de usuarios
- Reportes y estadísticas
- API REST para integración web