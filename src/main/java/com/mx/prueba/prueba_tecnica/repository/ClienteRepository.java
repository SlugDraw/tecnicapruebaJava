package com.mx.prueba.prueba_tecnica.repository;

import com.mx.prueba.prueba_tecnica.documents.Cliente;
import org.springframework.data.mongodb.repository.MongoRepository;


public interface ClienteRepository extends MongoRepository<Cliente, String> {

    boolean existsByIdIgnoreCase(String id);

}
