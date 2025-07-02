// src/main/java/com/tecser/autopartes/config/DataSeeder.java
package com.tecser.autopartes.config;

import com.tecser.autopartes.adapter.out.persistence.repository.RolJpaRepository;
import com.tecser.autopartes.domain.model.Rol;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DataSeeder {

    @Bean
    CommandLineRunner initRoles(RolJpaRepository rolRepo) {
        return args -> {
            // evita duplicados si vuelves a levantar la app
            if (rolRepo.count() == 0) {
                rolRepo.save(new Rol(null, "SUPER_ADMIN"));
                rolRepo.save(new Rol(null, "ADMIN"));
                rolRepo.save(new Rol(null, "USER"));
                System.out.println("🔑 Roles iniciales insertados");
            }
        };
    }
}
