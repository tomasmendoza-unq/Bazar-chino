
# API Bazar

Esta es una API para la gestión de un bazar, desarrollada con el framework Spring Boot bajo una arquitectura MVC. La API permite la administración de **productos**, **clientes** y **ventas** mediante un CRUD para cada entidad y varios endpoints adicionales para consultas específicas.

## Tabla de Contenidos
1. [Descripción General](#descripción-general)
2. [Entidades Principales](#entidades-principales)
   - Producto
   - Cliente
   - Venta
   - VentaProducto (Clase intermedia)
3. [Rutas Permitidas](#rutas-permitidas)
4. [Requisitos del Proyecto](#requisitos-del-proyecto)
5. [Instalación y Configuración](#instalación-y-configuración)
6. [Ejemplos de Uso](#ejemplos-de-uso)

---

## Descripción General

La API Bazar permite gestionar un conjunto de productos y clientes, además de procesar ventas que asocian un cliente a una lista de productos. Las características principales incluyen:
- CRUD para las entidades **Producto**, **Cliente** y **Venta**.
- Métodos adicionales para realizar consultas sobre la cantidad de stock disponible, la suma del monto de ventas y estadísticas de ventas por día.
- Arquitectura MVC usando **Spring Boot** para la capa backend.

---

## Entidades Principales

### Producto
Un **Producto** representa un artículo disponible en el bazar. Cada producto cuenta con los siguientes atributos:
- **ID**: Identificador único.
- **Nombre**: Nombre del producto.
- **Costo**: Precio unitario del producto.
- **Cantidad disponible**: Cantidad disponible en stock.

### Cliente
Un **Cliente** representa a la persona que realiza una compra en el bazar. Sus atributos son:
- **ID**: Identificador único.
- **Nombre**: Nombre del cliente.
- **Apellido**: Apellido del cliente

### Venta
Una **Venta** es una transacción que asocia un cliente a una lista de productos. Contiene los siguientes atributos:
- **ID**: Identificador único de la venta.
- **Cliente**: Cliente asociado a la venta (una relación de uno a uno).
- **Lista de Productos**: Lista de productos comprados, donde cada producto tiene una cantidad específica.
- **Fecha de la Venta**: Fecha en que se realizó la transacción.
- **Total**: Monto total de una venta determinada

### VentaProducto (Clase intermedia)
La clase **VentaProducto** es una entidad intermedia que vincula una venta con uno o varios productos y especifica la cantidad de cada producto comprado. Atributos principales:
- **ID Venta**: Identificador de la venta.
- **ID Producto**: Identificador del producto.
- **Cantidad**: Cantidad de unidades compradas de ese producto en particular.

---

## Rutas Permitidas

A continuación se describen las rutas permitidas para cada entidad y los métodos adicionales.

### Producto
- **GET /productos**: Listar todos los productos.
- **GET /productos/{id}**: Obtener un producto por su ID.
- **POST /productos/crear**: Crear un nuevo producto.
- **PUT /productos/editar/{id}**: Actualizar un producto existente.
- **DELETE /productos/eliminar/{id}**: Eliminar un producto por su ID.
- **GET /productos/falta_stock**: Obtener la lista de productos con poco stock.

### Cliente
- **GET /clientes**: Listar todos los clientes.
- **GET /clientes/{id}**: Obtener un cliente por su ID.
- **POST /clientes/crear**: Crear un nuevo cliente.
- **PUT /clientes/editar/{id}**: Actualizar un cliente existente.
- **DELETE /clientes/eliminar/{id}**: Eliminar un cliente por su ID.

### Venta
- **GET /ventas**: Listar todas las ventas.
- **GET /ventas/{id}**: Obtener una venta por su ID.
- **POST /ventas/crear**: Crear una nueva venta.
- **PUT /ventas/editar/{id}**: Actualizar una venta existente.
- **DELETE /ventas/eliminar/{codigo_venta}**: Eliminar una venta por su ID.
- **GET /ventas/productos/{codigo_venta}**: Obtener los productos asociados a una venta determinada.
- **GET /ventas/fecha/{fecha}**: Obtener la sumatoria del monto y la cantidad total de ventas de un día determinado.
- **GET /ventas/mayor_venta**: Obtener la venta con el mayor monto.

---

## Requisitos del Proyecto

- **Java 17** o superior
- **Spring Boot 3.0** o superior
- **Maven** para la gestión de dependencias
- **Base de Datos MySQL** (o cualquier otra base de datos configurada en `application.properties`)

---

## Instalación y Configuración

1. Clona este repositorio.
   ```bash
   git clone https://github.com/tu-repositorio/api-bazar.git
   ```
2. Importa el proyecto en tu IDE preferido.
3. Configura las propiedades de la base de datos en `src/main/resources/application.properties`.
4. Ejecuta el proyecto con el siguiente comando:
   ```bash
   mvn spring-boot:run
   ```
5. La API estará disponible en `http://localhost:8080`.

---

## Ejemplos de Uso

### Crear un nuevo producto
```bash
POST /productos/crear
Body:
{    
    "nombre": "Jugo de naranja",
    "marca": "Tang",
    "costo": 900,
    "cantidad_disponible": 10
}
```

### Obtener la venta con el monto más alto
```bash
GET /ventas/mayor_venta
```

---

## Contribuciones

Las contribuciones son bienvenidas. Por favor, abre un issue o envía un pull request para cualquier mejora o corrección.

---

## Licencia

Este proyecto está licenciado bajo la Licencia MIT - consulta el archivo [LICENSE](LICENSE) para más detalles.
