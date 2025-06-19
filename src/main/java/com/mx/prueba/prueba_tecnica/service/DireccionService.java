package com.mx.prueba.prueba_tecnica.service;

import com.mx.prueba.prueba_tecnica.documents.Cliente;
import com.mx.prueba.prueba_tecnica.documents.Direccion;
import com.mx.prueba.prueba_tecnica.models.DireccionDTO;
import com.mx.prueba.prueba_tecnica.repository.ClienteRepository;
import com.mx.prueba.prueba_tecnica.repository.DireccionRepository;
import com.mx.prueba.prueba_tecnica.util.NotFoundException;
import java.util.List;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;


public interface DireccionService {

    List<DireccionDTO> findAll();

    DireccionDTO get(String id);

    String create(DireccionDTO direccionDTO);

    void update(String id, DireccionDTO direccionDTO);

    void delete(String id);

    boolean idExists(String id);
}
