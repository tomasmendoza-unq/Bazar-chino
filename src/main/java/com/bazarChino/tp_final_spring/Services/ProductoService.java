package com.bazarChino.tp_final_spring.Services;

import com.bazarChino.tp_final_spring.DTO.ProductoCantidadDTO;
import com.bazarChino.tp_final_spring.DTO.ProductoDTO;
import com.bazarChino.tp_final_spring.Exception.ResourceNotFound;
import com.bazarChino.tp_final_spring.Exception.StockException;
import com.bazarChino.tp_final_spring.Models.Producto;
import com.bazarChino.tp_final_spring.Models.VentaProducto;
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
            listaProductoDTO.add(this.productoToDTO(producto));
        });
        return listaProductoDTO;
    }

    @Override
    public Producto getProductoById(Long codProduct){
        return prodRepository.findById(codProduct).orElseThrow(() ->
                new ResourceNotFound(codProduct, "No se encontró el producto con ID: " + codProduct)
        );
    }

    @Override
    public void actualizarStock(ProductoCantidadDTO pcDTO) {
        Producto producto = this.getProductoById(pcDTO.getProductoId());
        Double stock = producto.getCantidad_disponible() - pcDTO.getCantidad();

        this.validacionStock(stock, producto);

        producto.setCantidad_disponible(stock);

        this.saveProducto1(producto);

    }

    private void validacionStock(Double stock, Producto producto) {
        if(stock < 0){
            throw new StockException(stock, "No hay stock del producto: " + producto.toString());
        }

    }

    private ProductoDTO productoToDTO(Producto producto){
        return new ProductoDTO(
                producto.getCodigo_producto(),
                producto.getNombre(),
                producto.getMarca(),
                producto.getCosto(),
                producto.getCantidad_disponible()
        );
    }

    @Override
    public ProductoDTO getProductoDTOById(Long codProduct) {
        Producto producto = this.getProductoById(codProduct);


        return this.productoToDTO(producto);
    }

    @Override
    @Transactional
    public ProductoDTO saveProducto(@Valid ProductoDTO product) {
        Producto producto = new Producto(product.getNombre(), product.getMarca(), product.getCosto(), product.getCantidad_disponible());
        Producto savedProducto = this.saveProducto1(producto);

        return this.productoToDTO(savedProducto);
    }

    private Producto saveProducto1(Producto producto){
        return prodRepository.save(producto);
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

    @Override
    public List<ProductoDTO> getProductosDTOByVenta(List<VentaProducto> venta) {
        List<ProductoDTO> productoDTOS = new ArrayList<>();

       venta.forEach(ventaProducto -> {
            Long idProducto = ventaProducto.getId().getProductoId();
            productoDTOS.add(this.getProductoDTOById(idProducto));
        });

        return productoDTOS;
    }

    @Override
    public Double totalPrecio(List<ProductoCantidadDTO> productoDTOList) {
        Double total = 0.0;

        for (ProductoCantidadDTO productoCantidadDTO : productoDTOList){
            Producto producto = this.getProductoById(productoCantidadDTO.getProductoId());

            total += producto.getCosto()*productoCantidadDTO.getCantidad();
        };

        return total;
    }

    @Override
    public List<ProductoDTO> getProductosFaltaStock() {
        List<ProductoDTO> productosConFaltaStock = new ArrayList<>();

        this.getProductos().forEach(productoDTO -> {
            if(productoDTO.getCantidad_disponible()<5) productosConFaltaStock.add(productoDTO);
        });

        return productosConFaltaStock;
    }



}
