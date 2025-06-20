package com.tecser.autopartes.adapter.out.persistence.repository;

import com.tecser.autopartes.domain.model.Parte;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ParteJpaRepository extends JpaRepository<Parte, String> {
    long countByCodigoParteStartingWith(String prefix);
}
