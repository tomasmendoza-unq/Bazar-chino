package com.bazarChino.tp_final_spring.Services;

import com.bazarChino.tp_final_spring.DTO.ProductoDTO;
import com.bazarChino.tp_final_spring.Models.Producto;
import jakarta.validation.Valid;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface IProductoService {
    public List<Producto> getProductos();

    public Producto getProductoById(Long codigoProducto);

    public void saveProducto(@Valid ProductoDTO product);

    public void deleteProductoById(Long codigoProducto);

    public void editProductoById(Long codigoProducto, ProductoDTO product);
}
