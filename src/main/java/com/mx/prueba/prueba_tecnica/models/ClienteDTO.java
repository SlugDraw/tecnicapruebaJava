package com.mx.prueba.prueba_tecnica.models;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class ClienteDTO {

    @Size(max = 255)
    @ClienteIdValid
    private String id;

    @NotNull
    @Size(max = 255)
    private String nombre;

    @NotNull
    @Size(max = 255)
    private String apellidoMaterno;

    @NotNull
    @Size(max = 255)
    private String apellidoPaterno;

    @NotNull
    @Size(max = 255)
    private String correo;

}
