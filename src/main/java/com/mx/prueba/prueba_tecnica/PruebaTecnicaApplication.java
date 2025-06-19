package com.mx.prueba.prueba_tecnica;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;


@SpringBootApplication
@EnableMongoRepositories(basePackages = "com.mx.prueba.prueba_tecnica.repository")
public class PruebaTecnicaApplication {

    public static void main(final String[] args) {
        SpringApplication.run(PruebaTecnicaApplication.class, args);
    }

}
