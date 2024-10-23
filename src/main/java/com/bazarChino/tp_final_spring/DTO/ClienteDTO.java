package com.bazarChino.tp_final_spring.DTO;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ClienteDTO {


    private Long idcli;

    @NotNull(message = "Nombre no puede ser null")
    @NotBlank(message = "NOMBRE REQUIRED")
    private String nombre;

    @NotNull(message = "Apellido no puede ser null")
    @NotBlank(message = "APELLIDO REQUIRED")
    private String apellido;

    @NotNull(message = "DNI no puede ser null")
    @NotBlank(message = "DNI REQUIRED")
    private String dni;

    public ClienteDTO(Long idcli, String dni, String apellido, String nombre) {
        this.idcli = idcli;
        this.dni = dni;
        this.apellido = apellido;
        this.nombre = nombre;
    }

    public ClienteDTO() {
    }
}
