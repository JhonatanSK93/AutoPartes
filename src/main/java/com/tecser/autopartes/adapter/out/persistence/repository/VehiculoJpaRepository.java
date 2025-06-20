package com.tecser.autopartes.adapter.out.persistence.repository;

import com.tecser.autopartes.domain.model.Vehiculo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VehiculoJpaRepository extends JpaRepository<Vehiculo, String> {
}
