package com.mx.prueba.prueba_tecnica.rest;

import com.mx.prueba.prueba_tecnica.models.PedidosDTO;
import com.mx.prueba.prueba_tecnica.service.PedidosService;
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
@RequestMapping(value = "/api/pedidoss", produces = MediaType.APPLICATION_JSON_VALUE)
public class PedidosResource {

    private final PedidosService pedidosService;

    public PedidosResource(final PedidosService pedidosService) {
        this.pedidosService = pedidosService;
    }

    @GetMapping
    public ResponseEntity<List<PedidosDTO>> getAllPedidoss() {
        return ResponseEntity.ok(pedidosService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<PedidosDTO> getPedidos(@PathVariable(name = "id") final String id) {
        return ResponseEntity.ok(pedidosService.get(id));
    }

    @PostMapping
    @ApiResponse(responseCode = "201")
    public ResponseEntity<String> createPedidos(@RequestBody @Valid final PedidosDTO pedidosDTO) {
        final String createdId = pedidosService.create(pedidosDTO);
        return new ResponseEntity<>('"' + createdId + '"', HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updatePedidos(@PathVariable(name = "id") final String id,
            @RequestBody @Valid final PedidosDTO pedidosDTO) {
        pedidosService.update(id, pedidosDTO);
        return ResponseEntity.ok('"' + id + '"');
    }

    @DeleteMapping("/{id}")
    @ApiResponse(responseCode = "204")
    public ResponseEntity<Void> deletePedidos(@PathVariable(name = "id") final String id) {
        pedidosService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
