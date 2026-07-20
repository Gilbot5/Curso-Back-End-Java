# API REST de E-commerce

## Descripción

Esta aplicación es una API REST desarrollada con Spring Boot para gestionar un pequeño e-commerce. Permite administrar categorías, productos y pedidos, con validaciones básicas y persistencia en MySQL.

## Tecnologías

- Java 21
- Spring Boot 4
- Spring Web
- Spring Data JPA
- MySQL
- Validation

## Requisitos previos

- Java 21 instalado
- MySQL en ejecución
- Base de datos llamada `ecommerce`

## Configuración

Asegúrate de tener la base de datos configurada en [src/main/resources/application.properties](src/main/resources/application.properties):

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/ecommerce
spring.datasource.username=root
spring.datasource.password=
spring.jpa.hibernate.ddl-auto=update
```

## Ejecutar la aplicación

Desde la raíz del proyecto:

```bash
./mvnw.cmd spring-boot:run
```

En Linux o macOS:

```bash
./mvnw spring-boot:run
```

La API quedará disponible en:

```text
http://localhost:8080
```

## Modelos principales

### Categoría

```json
{
  "id": 1,
  "nombre": "Tecnología",
  "descripcion": "Productos electrónicos"
}
```

### Producto

```json
{
  "id": 1,
  "nombre": "Notebook",
  "precio": 10850.5,
  "cantidadEnStock": 5,
  "imagenUrl": "https://img.com/notebook.jpg",
  "categoria": {
    "id": 1,
    "nombre": "Tecnología",
    "descripcion": "Productos electrónicos"
  }
}
```

### Pedido

```json
{
  "id": 1,
  "productos": []
}
```

## Endpoints

### 1. Categorías

#### Listar todas las categorías
- Método: `GET`
- Ruta: `/categorias`

```bash
curl http://localhost:8080/categorias
```

#### Obtener una categoría por id
- Método: `GET`
- Ruta: `/categorias/{id}`

```bash
curl http://localhost:8080/categorias/1
```

#### Crear una categoría
- Método: `POST`
- Ruta: `/categorias`
- Body:

```json
{
  "nombre": "Tecnología",
  "descripcion": "Productos electrónicos"
}
```

```bash
curl -X POST http://localhost:8080/categorias \
  -H "Content-Type: application/json" \
  -d '{"nombre":"Tecnología","descripcion":"Productos electrónicos"}'
```

#### Actualizar una categoría
- Método: `PUT`
- Ruta: `/categorias/{id}`
- Body:

```json
{
  "nombre": "Tecnología",
  "descripcion": "Dispositivos y accesorios"
}
```

```bash
curl -X PUT http://localhost:8080/categorias/1 \
  -H "Content-Type: application/json" \
  -d '{"nombre":"Tecnología","descripcion":"Dispositivos y accesorios"}'
```

#### Eliminar una categoría
- Método: `DELETE`
- Ruta: `/categorias/{id}`

```bash
curl -X DELETE http://localhost:8080/categorias/1
```

### 2. Productos

#### Listar todos los productos
- Método: `GET`
- Ruta: `/productos`

```bash
curl http://localhost:8080/productos
```

#### Obtener un producto por id
- Método: `GET`
- Ruta: `/productos/{id}`

```bash
curl http://localhost:8080/productos/1
```

#### Crear un producto
- Método: `POST`
- Ruta: `/productos`
- Body:

```json
{
  "nombre": "Notebook",
  "precio": 10850.5,
  "cantidadEnStock": 5,
  "imagenUrl": "https://img.com/notebook.jpg",
  "categoria": {
    "id": 1
  }
}
```

```bash
curl -X POST http://localhost:8080/productos \
  -H "Content-Type: application/json" \
  -d '{"nombre":"Notebook","precio":10850.5,"cantidadEnStock":5,"imagenUrl":"https://img.com/notebook.jpg","categoria":{"id":1}}'
```

#### Actualizar un producto
- Método: `PUT`
- Ruta: `/productos/{id}`
- Body:

```json
{
  "nombre": "Notebook Gamer",
  "precio": 12500.0,
  "cantidadEnStock": 3,
  "imagenUrl": "https://img.com/notebook-gamer.jpg",
  "categoria": {
    "id": 1
  }
}
```

```bash
curl -X PUT http://localhost:8080/productos/1 \
  -H "Content-Type: application/json" \
  -d '{"nombre":"Notebook Gamer","precio":12500.0,"cantidadEnStock":3,"imagenUrl":"https://img.com/notebook-gamer.jpg","categoria":{"id":1}}'
```

#### Eliminar un producto
- Método: `DELETE`
- Ruta: `/productos/{id}`

```bash
curl -X DELETE http://localhost:8080/productos/1
```

#### Buscar productos por nombre
- Método: `GET`
- Ruta: `/productos/nombre/{nombre}`

```bash
curl http://localhost:8080/productos/nombre/notebook
```

#### Buscar productos por categoría
- Método: `GET`
- Ruta: `/productos/categoria/{categoria}`

```bash
curl http://localhost:8080/productos/categoria/tecnologia
```

### 3. Pedidos

#### Crear un pedido vacío
- Método: `POST`
- Ruta: `/pedidos`
- Body: no requiere body

```bash
curl -X POST http://localhost:8080/pedidos
```

#### Listar todos los pedidos
- Método: `GET`
- Ruta: `/pedidos`

```bash
curl http://localhost:8080/pedidos
```

#### Obtener un pedido por id
- Método: `GET`
- Ruta: `/pedidos/{id}`

```bash
curl http://localhost:8080/pedidos/1
```

#### Agregar un producto a un pedido
- Método: `POST`
- Ruta: `/pedidos/{pedidoId}/productos/{productoId}`
- Body: no requiere body

```bash
curl -X POST http://localhost:8080/pedidos/1/productos/2
```

#### Vaciar un pedido
- Método: `DELETE`
- Ruta: `/pedidos/{id}/vaciar`

```bash
curl -X DELETE http://localhost:8080/pedidos/1/vaciar
```

#### Eliminar un pedido
- Método: `DELETE`
- Ruta: `/pedidos/{id}`

```bash
curl -X DELETE http://localhost:8080/pedidos/1
```

## Notas importantes

- El stock se decrementa automáticamente cuando se agrega un producto a un pedido.
- Si no hay stock disponible, la API lanza un error de stock insuficiente.
- Las categorías se resuelven por id al crear o actualizar un producto.

## Ejemplo de flujo básico

1. Crear una categoría.
2. Crear un producto asociado a esa categoría.
3. Crear un pedido.
4. Agregar el producto al pedido.
5. Consultar el pedido para ver los productos incluidos.
