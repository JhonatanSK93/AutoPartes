package com.tecser.autopartes.adapter.out.persistence.repository;

import com.tecser.autopartes.domain.model.IngresoInventario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IngresoInventarioJpaRepository extends JpaRepository<IngresoInventario, Long> {
}
