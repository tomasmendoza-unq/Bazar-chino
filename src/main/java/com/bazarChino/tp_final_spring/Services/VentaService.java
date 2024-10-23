package com.bazarChino.tp_final_spring.Services;

import com.bazarChino.tp_final_spring.DTO.*;
import com.bazarChino.tp_final_spring.Exception.ResourceNotFound;

import com.bazarChino.tp_final_spring.Models.Producto;
import com.bazarChino.tp_final_spring.Models.Venta;

import com.bazarChino.tp_final_spring.Models.VentaProducto;
import com.bazarChino.tp_final_spring.Repository.IVentaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class VentaService implements IVentaService{
    @Autowired
    IVentaRepository ventaRepository;

    @Autowired
    IClienteService clienteService;

    @Autowired
    IProductoService productoService;

    @Autowired
    IVentaProductoService ventaProductoService;

    @Override
    public List<VentaDTO> getVentasDTO() {
        List<VentaDTO> ventaDTOList = new ArrayList<>();

        ventaRepository.findAll().forEach(venta -> {
            ventaDTOList.add(this.ventaToDTO(venta));
        });

        return ventaDTOList;
    }

    @Override
    public VentaDTO getVentaDTO(Long codigoVenta) {
        Venta venta = this.getVenta(codigoVenta);

        return this.ventaToDTO(venta);
    }

    private VentaDTO ventaToDTO(Venta venta){
        List<ProductoCantidadDTO> productoCantidadDTOList = ventaProductoService.getCantidadProductos(venta);

        return new VentaDTO(
                venta.getCodigo_venta(),
                venta.getFecha_venta(),
                productoCantidadDTOList,
                clienteService.getClienteDTOByVenta(venta).getIdcli(),
                venta.getTotal()
        );
    }



    public Venta getVenta(Long codigoVenta){
        return ventaRepository.findById(codigoVenta).orElseThrow(() ->
                new ResourceNotFound(codigoVenta, "No se encontró la venta con el ID: " + codigoVenta)
        );
    }

    @Override
    public VentaDTO saveVenta(VentaDTO ventaDTO) {
        Venta venta = this.dtoToVenta(ventaDTO);

        Venta savedVenta = ventaRepository.save(venta);

        ventaProductoService.saveAll(savedVenta, ventaDTO);

        return this.ventaToDTO(savedVenta);

    }

    public Venta dtoToVenta(VentaDTO ventaDTO){

        return new Venta(
                ventaDTO.getCodVenta(),
                ventaDTO.getFecha_venta(),
                productoService.totalPrecio(ventaDTO.getListaProductos()),
                new HashSet<>(),
                clienteService.getClienteByVenta(ventaDTO)
        );
    }


    @Override
    public void editVenta(VentaDTO ventaDTO, Long codigoVenta)
    {
        Venta venta = this.getVenta(codigoVenta);
        this.updateVentaFromDTO(venta,ventaDTO);

        this.saveVenta(this.ventaToDTO(venta));

    }

    private void updateVentaFromDTO(Venta venta, VentaDTO ventaDTO){
        venta.setCodigo_venta(ventaDTO.getCodVenta());
        venta.setFecha_venta(ventaDTO.getFecha_venta());
        venta.setTotal(ventaDTO.getTotal());
        venta.setProductos(ventaProductoService.getProductosByVenta(ventaDTO));
        venta.setUnCliente(clienteService.getClienteByVenta(ventaDTO));
    }

    @Override
    public void deleteVenta(Long codigoVenta) {
        ventaRepository.delete(this.getVenta(codigoVenta));
    }

    @Override
    public List<ProductoDTO> getProductoDTOList(Long codigoVenta) {
        Venta venta = this.getVenta(codigoVenta);

        return productoService.getProductosDTOByVenta(ventaProductoService.findByCodVenta(codigoVenta));
    }

    @Override
    public List<VentaFechaDTO> getByDate(LocalDate fecha) {
        return ventaRepository.findByFecha(fecha);
    }

    @Override
    public VentaClienteDTO getMaxVenta() {
        List<VentaClienteDTO> list = ventaRepository.findMaxVenta();

        if(!list.isEmpty()) return list.getFirst();

        return null;
    }


}
