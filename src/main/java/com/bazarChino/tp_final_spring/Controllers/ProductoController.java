package com.bazarChino.tp_final_spring.Controllers;

import com.bazarChino.tp_final_spring.DTO.ProductoDTO;
import com.bazarChino.tp_final_spring.Models.Producto;
import com.bazarChino.tp_final_spring.Services.IProductoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProductoController {

    @Autowired
    IProductoService productService;

    @GetMapping("/productos")
    public List<Producto> getProductos(){
        return productService.getProductos();
    }

    @GetMapping("/productos/{codigo_producto}")
    public ResponseEntity<?> getProductoById(@PathVariable Long codigo_producto){
        try {
            Producto produc = productService.getProductoById(codigo_producto);
            return new ResponseEntity<>(produc, HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/productos/crear")
    public String saveProducto(@Valid @RequestBody ProductoDTO product){
        productService.saveProducto(product);
        return "El producto fue creado exitosamente";
    }

    @DeleteMapping("/productos/eliminar/{codigo_producto}")
    public String deleteProductoById(@PathVariable Long codigo_producto){
        productService.deleteProductoById(codigo_producto);
        return "El producto fue eliminado exitosamente";
    }

    @PutMapping("/productos/editar/{codigo_producto}")
    public String editProductoById(@PathVariable Long codigo_producto, @Valid @RequestBody ProductoDTO product){
        productService.editProductoById(codigo_producto, product);
        return "El producto fue editado correctamente";
    }
}
