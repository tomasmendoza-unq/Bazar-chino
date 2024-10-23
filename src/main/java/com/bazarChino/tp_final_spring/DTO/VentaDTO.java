package com.bazarChino.tp_final_spring.DTO;

import com.bazarChino.tp_final_spring.Models.Cliente;
import com.bazarChino.tp_final_spring.Models.Producto;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class VentaDTO {


    private Long codVenta;

    @NotNull
    @PastOrPresent(message = "La fecha tiene que ser igual o menor a la fecha actual")
    private LocalDate fecha_venta;

    private Double total;

    private List<ProductoCantidadDTO> listaProductos;

    private Long unCliente;

    public VentaDTO(Long codVenta, LocalDate fecha_venta, List<ProductoCantidadDTO> listaProductos, Long unCliente, Double total) {
        this.codVenta = codVenta;
        this.fecha_venta = fecha_venta;
        this.listaProductos = listaProductos;
        this.unCliente = unCliente;
        this.total = total;
    }

    public VentaDTO() {
    }
}
