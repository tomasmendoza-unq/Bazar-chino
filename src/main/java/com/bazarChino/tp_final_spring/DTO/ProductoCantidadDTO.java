package com.bazarChino.tp_final_spring.DTO;

import jakarta.validation.constraints.Min;
import lombok.Data;

@Data
public class ProductoCantidadDTO {

    private Long productoId;

    @Min(value = 1, message = "La cantidad debe ser al menos 1")
    private int cantidad;

    public ProductoCantidadDTO(Long productoId, int cantidad) {
        this.productoId = productoId;
        this.cantidad = cantidad;
    }

    public ProductoCantidadDTO() {
    }
}
