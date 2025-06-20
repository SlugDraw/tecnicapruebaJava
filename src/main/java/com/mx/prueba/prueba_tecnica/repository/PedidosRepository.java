package com.mx.prueba.prueba_tecnica.repository;

import com.mx.prueba.prueba_tecnica.documents.Cliente;
import com.mx.prueba.prueba_tecnica.documents.Pedidos;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PedidosRepository extends MongoRepository<Pedidos, String> {

    Pedidos findFirstByCliente(Cliente cliente);
    Pedidos findFirstByCliente_Id(String id);

    boolean existsByIdIgnoreCase(String id);

}
