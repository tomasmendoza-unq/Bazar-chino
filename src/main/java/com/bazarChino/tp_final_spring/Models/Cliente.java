package com.bazarChino.tp_final_spring.Models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter @Getter @Entity
public class Cliente {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id_cliente;

    private String nombre;

    private String apellido;


    @OneToMany(mappedBy = "unCliente", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Venta> ventas;

    private String dni;

    public Cliente(String nombre, String apellido, String dni) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.dni = dni;
    }

    public Cliente() {
    }
}
