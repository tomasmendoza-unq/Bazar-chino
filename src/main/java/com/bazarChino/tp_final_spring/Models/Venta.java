package com.bazarChino.tp_final_spring.Models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter @Setter @Entity
public class Venta {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long codigo_venta;

    private LocalDate fecha_venta;
    private Double total;

    // TODO: PONER CARDINALIDAD
    @OneToMany(mappedBy = "compra")
    private List<Producto> listaProductos;
    @OneToOne
    @JoinColumn(name = "id_cliente",
                referencedColumnName = "id_cliente")
    private Cliente unCliente;

}
