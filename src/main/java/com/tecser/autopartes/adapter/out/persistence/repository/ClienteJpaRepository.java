package com.tecser.autopartes.adapter.out.persistence.repository;

import com.tecser.autopartes.domain.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteJpaRepository extends JpaRepository<Cliente, String> {
}
