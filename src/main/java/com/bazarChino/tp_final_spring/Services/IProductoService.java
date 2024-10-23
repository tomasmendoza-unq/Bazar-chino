package com.bazarChino.tp_final_spring.Services;

import com.bazarChino.tp_final_spring.DTO.ProductoCantidadDTO;
import com.bazarChino.tp_final_spring.DTO.ProductoDTO;
import com.bazarChino.tp_final_spring.Models.Producto;
import com.bazarChino.tp_final_spring.Models.VentaProducto;
import jakarta.validation.Valid;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface IProductoService {
    public List<ProductoDTO> getProductos();

    public ProductoDTO getProductoDTOById(Long codigoProducto);

    public ProductoDTO saveProducto(@Valid ProductoDTO product);

    public void deleteProductoById(Long codigoProducto);

    public void editProductoById(Long codigoProducto, ProductoDTO product);

    public List<ProductoDTO> getProductosDTOByVenta(List<VentaProducto> productoDTOList);

    public Double totalPrecio(List<ProductoCantidadDTO> productoList);

    public List<ProductoDTO> getProductosFaltaStock();


    public Producto getProductoById(Long codProduct);

    public void actualizarStock(ProductoCantidadDTO pcDTO);
}
