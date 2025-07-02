package com.tecser.autopartes.adapter.out.persistence.repository;

import com.tecser.autopartes.domain.model.DetalleVenta;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DetalleVentaJpaRepository extends JpaRepository<DetalleVenta, Long> {
    
}
