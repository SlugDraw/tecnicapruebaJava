package com.mx.prueba.prueba_tecnica.repository;

import com.mx.prueba.prueba_tecnica.documents.Cliente;
import com.mx.prueba.prueba_tecnica.documents.Direccion;
import org.springframework.data.mongodb.repository.MongoRepository;


public interface DireccionRepository extends MongoRepository<Direccion, String> {

    Direccion findFirstByCliente(Cliente cliente);

    boolean existsByIdIgnoreCase(String id);

}
