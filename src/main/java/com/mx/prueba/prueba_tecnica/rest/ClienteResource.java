package com.mx.prueba.prueba_tecnica.rest;

import com.mx.prueba.prueba_tecnica.documents.Cliente;
import com.mx.prueba.prueba_tecnica.models.ClienteDTO;
import com.mx.prueba.prueba_tecnica.service.ClienteService;
import com.mx.prueba.prueba_tecnica.util.ReferencedException;
import com.mx.prueba.prueba_tecnica.util.ReferencedWarning;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(value = "/api/clientes", produces = MediaType.APPLICATION_JSON_VALUE)
public class ClienteResource {

    private final ClienteService clienteService;

    public ClienteResource(final ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    @GetMapping
    public ResponseEntity<List<Cliente>> getAllClientes() {
        return ResponseEntity.ok(clienteService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Cliente> getCliente(@PathVariable(name = "id") final String id) {
        return ResponseEntity.ok(clienteService.get(id));
    }

    @PostMapping
    @ApiResponse(responseCode = "201")
    public ResponseEntity<String> createCliente(@RequestBody @Valid final ClienteDTO clienteDTO) {
        final String createdId = clienteService.create(clienteDTO);
        return new ResponseEntity<>('"' + createdId + '"', HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateCliente(@PathVariable(name = "id") final String id,
            @RequestBody @Valid final ClienteDTO clienteDTO) {
        clienteService.update(id, clienteDTO);
        return ResponseEntity.ok('"' + id + '"');
    }

    @DeleteMapping("/{id}")
    @ApiResponse(responseCode = "204")
    public ResponseEntity<Void> deleteCliente(@PathVariable(name = "id") final String id) {
        final ReferencedWarning referencedWarning = clienteService.getReferencedWarning(id);
        if (referencedWarning != null) {
            throw new ReferencedException(referencedWarning);
        }
        clienteService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
