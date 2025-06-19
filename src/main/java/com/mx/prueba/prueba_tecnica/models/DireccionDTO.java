package com.mx.prueba.prueba_tecnica.models;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class DireccionDTO {

    @Size(max = 255)
    @DireccionIdValid
    private String id;

    @NotNull
    @Size(max = 255)
    private String calle;

    @NotNull
    @Size(max = 255)
    private String numero;

    @NotNull
    @Size(max = 255)
    private String colonia;

    @Size(max = 255)
    private String municipio;

    @Size(max = 255)
    private String ciudad;

    @Size(max = 255)
    private String cliente;

}
