package com.tecser.autopartes.adapter.out.persistence.repository;

import com.tecser.autopartes.domain.model.Usuario;
import com.tecser.autopartes.domain.model.Vehiculo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface VehiculoJpaRepository extends JpaRepository<Vehiculo, String> {
    List<Vehiculo> findByUsuario(Usuario usuario);
}

