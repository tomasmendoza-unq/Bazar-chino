package com.bazarChino.tp_final_spring.Services;

import com.bazarChino.tp_final_spring.DTO.ProductoDTO;
import com.bazarChino.tp_final_spring.Models.Producto;
import com.bazarChino.tp_final_spring.Repository.IProductoRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class ProductoService implements IProductoService{
    @Autowired
    IProductoRepository prodRepository;

    @Override
    public List<Producto> getProductos() {
        return prodRepository.findAll();
    }

    @Override
    public Producto getProductoById(Long codigoProducto) {
        return prodRepository.findById(codigoProducto).orElseThrow();
    }

    @Override
    public void saveProducto(@Valid ProductoDTO product) {
        Producto producto = new Producto(product.getNombre(),
                                        product.getMarca(),
                                        product.getCosto(),
                                        product.getCantidad_disponible());
        prodRepository.save(producto);
    }

    @Override
    public void deleteProductoById(Long codigoProducto) {
        prodRepository.deleteById(codigoProducto);
    }

    @Override
    public void editProductoById(Long codigoProducto, ProductoDTO product) {
        try{
            Producto producto = this.getProductoById(codigoProducto);
        } catch(NoSuchElementException e){
            throw new NoSuchElementException(e.getMessage());
        }

    }
}
