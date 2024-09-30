package com.bazarChino.tp_final_spring.Models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Setter @Getter @Entity
public class Cliente {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id_cliente;

    private String nombre;

    private String apellido;

    private String dni;

}
