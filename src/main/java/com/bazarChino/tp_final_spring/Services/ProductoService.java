package com.bazarChino.tp_final_spring.Services;

import com.bazarChino.tp_final_spring.DTO.ProductoDTO;
import com.bazarChino.tp_final_spring.Exception.ResourceNotFound;
import com.bazarChino.tp_final_spring.Models.Producto;
import com.bazarChino.tp_final_spring.Repository.IProductoRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductoService implements IProductoService{
    @Autowired
    IProductoRepository prodRepository;

    @Override
    public List<ProductoDTO> getProductos() {
        List<ProductoDTO> listaProductoDTO = new ArrayList<>();


        prodRepository.findAll().forEach(producto -> {
            ProductoDTO productoDTO = new ProductoDTO(
                    producto.getNombre(),producto.getMarca(),
                    producto.getCosto(), producto.getCantidad_disponible());
            listaProductoDTO.add(productoDTO);
        });
        return listaProductoDTO;
    }

    public Producto getProductoById(Long codProduct){
        return prodRepository.findById(codProduct).orElseThrow(() ->
                new ResourceNotFound(codProduct, "No se encontró el producto con ID: " + codProduct)
        );
    }

    @Override
    public ProductoDTO getProductoDTOById(Long codProduct) {
        Producto producto = this.getProductoById(codProduct);


        return new ProductoDTO(
                producto.getNombre(),
                producto.getMarca(),
                producto.getCosto(),
                producto.getCantidad_disponible()
        );
    }

    @Override
    @Transactional
    public ProductoDTO saveProducto(@Valid ProductoDTO product) {
        Producto producto = new Producto(product.getNombre(), product.getMarca(), product.getCosto(), product.getCantidad_disponible());
        Producto savedProducto = prodRepository.save(producto);

        return new ProductoDTO(
                savedProducto.getNombre(),
                savedProducto.getMarca(),
                savedProducto.getCosto(),
                savedProducto.getCantidad_disponible()
        );
    }

    @Override
    public void deleteProductoById(Long codProduct) {
        Producto producto = this.getProductoById(codProduct);
        prodRepository.delete(producto);
    }

    private void updateProductoFromDTO(Producto producto, ProductoDTO productDTO) {
        producto.setNombre(productDTO.getNombre());
        producto.setMarca(productDTO.getMarca());
        producto.setCosto(productDTO.getCosto());
        producto.setCantidad_disponible(productDTO.getCantidad_disponible());
    }

    @Override
    public void editProductoById(Long codProduct, ProductoDTO product) {
        Producto producto = this.getProductoById(codProduct);
        updateProductoFromDTO(producto, product);

        prodRepository.save(producto);
    }
}
