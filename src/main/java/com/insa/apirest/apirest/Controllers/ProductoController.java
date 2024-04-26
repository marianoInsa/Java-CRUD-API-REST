package com.insa.apirest.apirest.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.insa.apirest.apirest.Repositories.ProductoRepository;
import com.insa.apirest.apirest.Entities.Producto;

// Para el enrutamiento, decimos que sucedera si el usuario accede a /productos
@RestController
@RequestMapping("/productos")
public class ProductoController {

    // Inyecta una instancia de un repositorio
    // Este repositorio es la conexion con la base de datos
    @Autowired
    private ProductoRepository productoRepository;

    // Metodo GET All -> Recuperar todos los recursos (productos)
    @GetMapping
    public List<Producto> obtenerTodosLosProductos() {
        // Trae todos los productos de la base de datos
        return productoRepository.findAll();
    }

    // Metodo GET individual
    @GetMapping("/{id}")
    public Producto obtenerProductoPorId(@PathVariable Long id) {
        // Preguntar si el producto esta en la BD
        return productoRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("No se encontro el producto con el ID: " + id));
    }

    // Metodo POST -> Agregar un nuevo recurso (producto)
    @PostMapping
    public Producto crearProducto(@RequestBody Producto producto) {
        // .save() -> lo guarda en la BD
        return productoRepository.save(producto);
    }

    // Metodo PUT -> Actualizacion de un recurso (producto)
    @PutMapping("/{id}")
    public Producto actualizarProducto(@PathVariable Long id, @RequestBody Producto detallesProducto) {
        // Preguntar si el producto esta en la BD
        Producto producto = productoRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("No se encontro el producto con el ID: " + id));

        // Actualizar los valores del producto
        producto.setNombre(detallesProducto.getNombre());
        producto.setPrecio(detallesProducto.getPrecio());

        // .save() -> lo guarda en la BD
        return productoRepository.save(producto);
    }

    // Metodo DELETE -> Eliminacion de un recurso (producto)
    @DeleteMapping("/{id}")
    public String borrarProducto(@PathVariable Long id) {
        // Preguntar si el producto esta en la BD
        Producto producto = productoRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("No se encontro el producto con el ID: " + id));

        // Esto porq el metodo devuelve un string
        productoRepository.delete(producto);
        return "El producto con el ID: " + id + " fue eliminado";

        // Otra forma puede ser devolver el producto, el metodo en lugar de "String" devuelve "Producto"
        // producto = productoRepository.delete(producto);
        // return producto;
    }
}
