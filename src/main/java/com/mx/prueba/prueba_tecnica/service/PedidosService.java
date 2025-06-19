package com.mx.prueba.prueba_tecnica.service;

import com.mx.prueba.prueba_tecnica.documents.Cliente;
import com.mx.prueba.prueba_tecnica.documents.Pedidos;
import com.mx.prueba.prueba_tecnica.models.PedidosDTO;
import com.mx.prueba.prueba_tecnica.repository.ClienteRepository;
import com.mx.prueba.prueba_tecnica.repository.PedidosRepository;
import com.mx.prueba.prueba_tecnica.util.NotFoundException;
import java.util.List;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;


public interface PedidosService {
    List<PedidosDTO> findAll();

    PedidosDTO get(String id);

    String create(PedidosDTO pedidosDTO);

    void update(String id, PedidosDTO pedidosDTO);

    void delete(String id);

    boolean idExists(String id);
}
