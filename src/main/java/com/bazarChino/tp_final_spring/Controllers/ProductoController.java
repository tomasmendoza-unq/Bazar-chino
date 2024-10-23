package com.bazarChino.tp_final_spring.Controllers;

import com.bazarChino.tp_final_spring.DTO.ProductoDTO;
import com.bazarChino.tp_final_spring.Exception.ResourceNotFound;
import com.bazarChino.tp_final_spring.Models.Producto;
import com.bazarChino.tp_final_spring.Services.IProductoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/productos")
public class ProductoController {

    @Autowired
    IProductoService productService;

    @GetMapping
    public List<ProductoDTO> getProductos(){
        return productService.getProductos();
    }

    @GetMapping("/{codProduct}")
    public ResponseEntity<?> getProductoById(@PathVariable Long codProduct){
        ProductoDTO productoDTO = productService.getProductoDTOById(codProduct);

        return ResponseEntity.status(HttpStatus.FOUND).body(productoDTO);
    }

    @GetMapping("/falta_stock")
    public ResponseEntity<?> getProductosFaltaStock(){
        List<ProductoDTO> productoDTOList = productService.getProductosFaltaStock();
        if(productoDTOList.isEmpty()){
            return ResponseEntity.status(HttpStatus.OK).body("No hay productos con falta de stock");
        }
        return ResponseEntity.status(HttpStatus.FOUND).body(productoDTOList);
    }

    @PostMapping("/crear")
    public ResponseEntity<ProductoDTO> saveProducto(@Valid @RequestBody ProductoDTO product){
        ProductoDTO productoDTO = productService.saveProducto(product);
        return ResponseEntity.status(HttpStatus.CREATED).body(productoDTO);
    }

    @DeleteMapping("/eliminar/{codProduct}")
    public ResponseEntity<?> deleteProductoById(@PathVariable Long codProduct){
        productService.deleteProductoById(codProduct);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body("Se elimino el producto con el id: " + codProduct);
    }

    @PutMapping("/editar/{codProduct}")
    public ResponseEntity<?> editProductoById(@PathVariable Long codProduct, @Valid @RequestBody ProductoDTO product){
        productService.editProductoById(codProduct, product);
        return ResponseEntity.status(HttpStatus.OK).body(product);
    }

}
