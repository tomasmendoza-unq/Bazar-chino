package com.bazarChino.tp_final_spring.Services;

import com.bazarChino.tp_final_spring.DTO.ProductoCantidadDTO;
import com.bazarChino.tp_final_spring.DTO.ProductoDTO;
import com.bazarChino.tp_final_spring.DTO.VentaDTO;
import com.bazarChino.tp_final_spring.Exception.ResourceNotFound;
import com.bazarChino.tp_final_spring.Models.Venta;
import com.bazarChino.tp_final_spring.Models.VentaProducto;
import com.bazarChino.tp_final_spring.Repository.IVentaProductoRepository;
import com.bazarChino.tp_final_spring.embeddables.VentaProductoId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class VentaProductoService implements IVentaProductoService{
    @Autowired
    IVentaProductoRepository iVentaProductoRepository;

    @Autowired
    IProductoService productoService;

    public VentaProducto getVentaProducto(Long idVenta, Long idProducto){
        VentaProductoId id = new VentaProductoId(idVenta, idProducto);

        return iVentaProductoRepository.findById(id).orElseThrow(() ->
                new ResourceNotFound(idVenta, "No se encontro la venta y el producto con los ids" +
                        ": " + idVenta + " " + idProducto)
        );
    }

    @Override
    public Set<VentaProducto> getProductosByVenta(VentaDTO ventaDTO) {
        Set<VentaProducto> ventaProductos = new HashSet<>();

        for(ProductoCantidadDTO productoCantidadDTO : ventaDTO.getListaProductos()){
            ventaProductos.add(this.getVentaProducto(ventaDTO.getCodVenta(),productoCantidadDTO.getProductoId()));
        }
        return ventaProductos;
    }

    @Override
    public List<ProductoCantidadDTO> getCantidadProductos(Venta venta) {
        List<ProductoCantidadDTO> productoCantidadDTOS = new ArrayList<>();

        for (VentaProducto ventaProducto : this.findByCodVenta(venta.getCodigo_venta())) {
            ProductoCantidadDTO productoCantidadDTO = new ProductoCantidadDTO(
                    ventaProducto.getId().getProductoId(),
                    ventaProducto.getCantidad()
            );


            productoCantidadDTOS.add(productoCantidadDTO);

        }

        return productoCantidadDTOS;
    }

    @Override
    public void saveAll(Venta savedVenta, VentaDTO ventaDTO) {
        for (ProductoCantidadDTO productoCantidadDTO : ventaDTO.getListaProductos()) {
            this.saveVentaProducto(productoCantidadDTO, savedVenta);
        }
    }

    @Override
    public List<VentaProducto> findByCodVenta(Long codigoVenta) {
        return iVentaProductoRepository.findByVentaCodigo(codigoVenta);
    }

    private VentaProducto saveVentaProducto(ProductoCantidadDTO pcDTO, Venta savedVenta) {
        productoService.actualizarStock(pcDTO);
        return iVentaProductoRepository.save(this.createVentaProducto(pcDTO,savedVenta));
    }

    private VentaProducto createVentaProducto(ProductoCantidadDTO pcDTO, Venta savedVenta) {
        VentaProductoId id = new VentaProductoId(savedVenta.getCodigo_venta(), pcDTO.getProductoId());

        return new VentaProducto(
                savedVenta,
                productoService.getProductoById(pcDTO.getProductoId()),
                pcDTO.getCantidad()
        );

    }


}
