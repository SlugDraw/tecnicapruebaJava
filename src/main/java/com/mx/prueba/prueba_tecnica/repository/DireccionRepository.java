package com.mx.prueba.prueba_tecnica.repository;

import com.mx.prueba.prueba_tecnica.documents.Cliente;
import com.mx.prueba.prueba_tecnica.documents.Direccion;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DireccionRepository extends MongoRepository<Direccion, String> {

    Direccion findFirstByCliente(Cliente cliente);
    Direccion findFirstByClienteId(String clienteId);
    boolean existsByIdIgnoreCase(String id);

}
