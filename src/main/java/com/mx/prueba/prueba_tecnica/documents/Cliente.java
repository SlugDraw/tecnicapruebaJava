package com.mx.prueba.prueba_tecnica.documents;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.time.OffsetDateTime;
import java.util.Set;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.annotation.ReadOnlyProperty;
import org.springframework.data.annotation.Version;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;


@Document
@Getter
@Setter
public class Cliente {

    @NotNull
    @Id
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

    @DocumentReference(lazy = true, lookup = "{ 'cliente' : ?#{#self._id} }")
    @ReadOnlyProperty
    private Set<Direccion> direcciones;

    @DocumentReference(lazy = true, lookup = "{ 'cliente' : ?#{#self._id} }")
    @ReadOnlyProperty
    private Set<Pedidos> pedidos;

    @CreatedDate
    private OffsetDateTime dateCreated;

    @LastModifiedDate
    private OffsetDateTime lastUpdated;

    @Version
    private Integer version;

}
