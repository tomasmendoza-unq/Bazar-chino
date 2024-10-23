package com.bazarChino.tp_final_spring.Models;

import com.bazarChino.tp_final_spring.embeddables.VentaProductoId;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Entity
@Data
public class VentaProducto {

    @EmbeddedId
    private VentaProductoId id = new VentaProductoId();

    @ManyToOne
    @MapsId("ventaId")
    private Venta venta;

    @ManyToOne
    @MapsId("productoId")
    private Producto producto;

    private int cantidad; // La cantidad de este producto en la venta

    public VentaProducto() {
    }

    public VentaProducto(Venta venta, Producto producto, int cantidad) {
        this.venta = venta;
        this.producto = producto;
        this.cantidad = cantidad;
        this.id = new VentaProductoId(venta.getCodigo_venta(), producto.getCodigo_producto());
    }

}
