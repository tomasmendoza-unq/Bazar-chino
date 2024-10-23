package com.bazarChino.tp_final_spring.DTO;

import lombok.Data;

@Data
public class VentaClienteDTO {

    private Long codigo_venta;

    private Double total;

    private Long cantidad_prod;

    private String nom_cli;

    private String apellido;

    public VentaClienteDTO(Long codigo_venta, Double total, Long cantidad_prod, String apellido, String nom_cli) {
        this.codigo_venta = codigo_venta;
        this.total = total;
        this.cantidad_prod = cantidad_prod;
        this.apellido = apellido;
        this.nom_cli = nom_cli;
    }

    public VentaClienteDTO() {
    }
}
