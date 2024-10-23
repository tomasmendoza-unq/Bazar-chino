package com.bazarChino.tp_final_spring.Services;

import com.bazarChino.tp_final_spring.DTO.ProductoCantidadDTO;
import com.bazarChino.tp_final_spring.DTO.VentaDTO;
import com.bazarChino.tp_final_spring.Models.Venta;
import com.bazarChino.tp_final_spring.Models.VentaProducto;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public interface IVentaProductoService {

    Set<VentaProducto> getProductosByVenta(VentaDTO ventaDTO);

    List<ProductoCantidadDTO> getCantidadProductos(Venta venta);

    public void saveAll(Venta savedVenta, VentaDTO ventaDTO);

    public List<VentaProducto> findByCodVenta(Long codigoVenta);
}
