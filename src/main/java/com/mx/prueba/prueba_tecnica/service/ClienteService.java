package com.mx.prueba.prueba_tecnica.service;


import com.mx.prueba.prueba_tecnica.documents.Cliente;
import com.mx.prueba.prueba_tecnica.models.ClienteDTO;
import com.mx.prueba.prueba_tecnica.util.ReferencedWarning;
import java.util.List;



public interface ClienteService {

    List<Cliente> findAll();
    ClienteDTO get(final String id);
    String create(final ClienteDTO clienteDTO);
    void update(final String id, final ClienteDTO clienteDTO);
    void delete(String id);
    boolean idExists(String id);
    ReferencedWarning getReferencedWarning(String id);

}
