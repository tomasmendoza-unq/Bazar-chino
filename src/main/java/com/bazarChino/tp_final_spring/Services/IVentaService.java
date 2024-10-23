package com.bazarChino.tp_final_spring.Services;

import com.bazarChino.tp_final_spring.DTO.ProductoDTO;
import com.bazarChino.tp_final_spring.DTO.VentaClienteDTO;
import com.bazarChino.tp_final_spring.DTO.VentaDTO;
import com.bazarChino.tp_final_spring.DTO.VentaFechaDTO;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public interface IVentaService {
    public List<VentaDTO> getVentasDTO();

    public VentaDTO getVentaDTO(Long codigoVenta);

    public VentaDTO saveVenta(VentaDTO ventaDTO);

    public void editVenta(VentaDTO ventaDTO, Long codigoVenta);

    public void deleteVenta(Long codigoVenta);

    public List<ProductoDTO> getProductoDTOList(Long codigoVenta);

    public List<VentaFechaDTO> getByDate(LocalDate fecha);

    public VentaClienteDTO getMaxVenta();
}
