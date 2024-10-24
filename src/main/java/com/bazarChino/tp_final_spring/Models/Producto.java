package com.bazarChino.tp_final_spring.Models;

import jakarta.persistence.*;
import lombok.Data;


@Data
@Entity
public class Producto {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long codigo_producto;

    @Basic
    private String nombre;
    private String marca;
    private Double costo;
    private Double cantidad_disponible;

    public Producto(String nombre, String marca, Double costo, Double cantidad_disponible) {
        this.nombre = nombre;
        this.marca = marca;
        this.costo = costo;
        this.cantidad_disponible = cantidad_disponible;
    }

    public Producto() {
    }
}
