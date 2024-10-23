package com.bazarChino.tp_final_spring.DTO;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;


@Data
public class ProductoDTO {


    private Long codProduct;

    @NotNull(message = "Nombre no puede ser null")
    private String nombre;

    @NotNull(message = "Marca no puede ser null")
    private String marca;

    @NotNull(message = "Precio no puede ser null")
    @Min(value=0, message = "El costo no puede ser un numero negativo")
    private Double costo;

    @NotNull(message = "Cantidad no puede ser null")
    @Min(value=0, message = "El minimo disponible no puede ser menor a 0")
    private Double cantidad_disponible;

    public ProductoDTO(Long codProduct, String nombre, String marca, Double costo, Double cantidad_disponible) {
        this.codProduct = codProduct;
        this.nombre = nombre;
        this.marca = marca;
        this.costo = costo;
        this.cantidad_disponible = cantidad_disponible;
    }

    public ProductoDTO() {
    }
}
