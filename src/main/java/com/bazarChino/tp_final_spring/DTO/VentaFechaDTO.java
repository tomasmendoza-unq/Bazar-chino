package com.bazarChino.tp_final_spring.DTO;

import lombok.Data;

@Data
public class VentaFechaDTO {
    private Double total;

    private Long cantidad;

    public VentaFechaDTO(Double total, Long cantidad) {
        this.total = total;
        this.cantidad = cantidad;
    }
}
