package com.tecser.autopartes.adapter.out.persistence.repository;

import com.tecser.autopartes.domain.model.Inventario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InventarioJpaRepository extends JpaRepository<Inventario, Long> {
}
