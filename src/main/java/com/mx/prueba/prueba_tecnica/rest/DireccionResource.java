package com.mx.prueba.prueba_tecnica.rest;

import com.mx.prueba.prueba_tecnica.models.DireccionDTO;
import com.mx.prueba.prueba_tecnica.service.DireccionService;
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
@RequestMapping(value = "/api/direccions", produces = MediaType.APPLICATION_JSON_VALUE)
public class DireccionResource {

    private final DireccionService direccionService;

    public DireccionResource(final DireccionService direccionService) {
        this.direccionService = direccionService;
    }

    @GetMapping
    public ResponseEntity<List<DireccionDTO>> getAllDireccions() {
        return ResponseEntity.ok(direccionService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<DireccionDTO> getDireccion(@PathVariable(name = "id") final String id) {
        return ResponseEntity.ok(direccionService.get(id));
    }

    @PostMapping
    @ApiResponse(responseCode = "201")
    public ResponseEntity<String> createDireccion(
            @RequestBody @Valid final DireccionDTO direccionDTO) {
        final String createdId = direccionService.create(direccionDTO);
        return new ResponseEntity<>('"' + createdId + '"', HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateDireccion(@PathVariable(name = "id") final String id,
            @RequestBody @Valid final DireccionDTO direccionDTO) {
        direccionService.update(id, direccionDTO);
        return ResponseEntity.ok('"' + id + '"');
    }

    @DeleteMapping("/{id}")
    @ApiResponse(responseCode = "204")
    public ResponseEntity<Void> deleteDireccion(@PathVariable(name = "id") final String id) {
        direccionService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
