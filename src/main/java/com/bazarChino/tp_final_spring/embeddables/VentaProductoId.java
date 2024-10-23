package com.bazarChino.tp_final_spring.embeddables;

import jakarta.persistence.Embeddable;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Embeddable
@EqualsAndHashCode
@Data
public class VentaProductoId {
    private Long ventaId;
    private Long productoId;

    public VentaProductoId() {
    }

    public VentaProductoId(Long ventaId, Long productoId) {
        this.ventaId = ventaId;
        this.productoId = productoId;
    }

}
