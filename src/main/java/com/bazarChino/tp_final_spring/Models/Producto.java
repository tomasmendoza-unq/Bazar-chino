package com.bazarChino.tp_final_spring.Models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter @Entity
public class Producto {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long codigo_producto;

    @Basic
    private String nombre;
    private String marca;
    private Double costo;
    private Double cantidad_disponible;
    @ManyToOne
    @JoinColumn(name = "cod_venta")
    private Venta compra;

    public Producto(String nombre, String marca, Double costo, Double cantidad_disponible) {
        this.nombre = nombre;
        this.marca = marca;
        this.costo = costo;
        this.cantidad_disponible = cantidad_disponible;
    }
}
