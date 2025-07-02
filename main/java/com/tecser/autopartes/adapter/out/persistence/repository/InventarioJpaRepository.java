package com.tecser.autopartes.adapter.out.persistence.repository;

import com.tecser.autopartes.domain.model.Inventario;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface InventarioJpaRepository extends JpaRepository<Inventario, String> {
    Optional<Inventario> findByNombreParte(String nombreParte);
}
