package com.tecser.autopartes.adapter.out.persistence.repository;

import com.tecser.autopartes.domain.model.CompraInventario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompraInventarioJpaRepository extends JpaRepository<CompraInventario, Long> {
}
