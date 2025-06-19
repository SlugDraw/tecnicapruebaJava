package com.mx.prueba.prueba_tecnica.models;

import com.mx.prueba.prueba_tecnica.documents.Direccion;
import com.mx.prueba.prueba_tecnica.documents.Pedidos;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;


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

    private Set<Direccion> direcciones;

    private Set<Pedidos> pedidos;

}
