package com.mx.prueba.prueba_tecnica.documents;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.time.OffsetDateTime;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.annotation.Version;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;


@Document
@Getter
@Setter
public class Direccion {

    @Id
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

    @DocumentReference(lazy = true)
    @JsonIgnore
    private Cliente cliente;

    @JsonProperty("clienteId")
    public String getClienteId() {
        return cliente != null ? cliente.getId() : null;
    }

    @CreatedDate
    private OffsetDateTime dateCreated;

    @LastModifiedDate
    private OffsetDateTime lastUpdated;

    @Version
    private Integer version;

}
