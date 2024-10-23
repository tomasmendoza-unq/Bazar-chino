package com.bazarChino.tp_final_spring.Models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Set;


@Data
@Entity
@EqualsAndHashCode
public class Venta {

    @Id
    @SequenceGenerator(name = "venta_seq", sequenceName = "venta_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long codigo_venta;

    private LocalDate fecha_venta;
    private Double total;

    @OneToMany(mappedBy = "venta", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<VentaProducto> productos;

    @ManyToOne
    @JoinColumn(name = "id_cliente", referencedColumnName = "id_cliente", nullable = false)
    private Cliente unCliente;

    public Venta(Long codigo_venta, LocalDate fecha_venta, Double total, Set<VentaProducto> productos, Cliente unCliente) {
        this.codigo_venta = codigo_venta;
        this.fecha_venta = fecha_venta;
        this.total = total;
        this.productos = productos;
        this.unCliente = unCliente;
    }

    public Venta() {
    }
}
