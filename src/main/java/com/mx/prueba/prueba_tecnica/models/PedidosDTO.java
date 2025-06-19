package com.mx.prueba.prueba_tecnica.models;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class PedidosDTO {

    @Size(max = 255)
    @PedidosIdValid
    private String id;

    @NotNull
    @Size(max = 255)
    private String codigo;

    @NotNull
    private Integer cantidad;

    @NotNull
    private Double precio;

    @Size(max = 255)
    private String cliente;

}
