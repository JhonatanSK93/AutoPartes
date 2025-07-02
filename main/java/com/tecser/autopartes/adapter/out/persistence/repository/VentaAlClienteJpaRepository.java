package com.tecser.autopartes.adapter.out.persistence.repository;

import com.tecser.autopartes.domain.model.VentaAlCliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VentaAlClienteJpaRepository extends JpaRepository<VentaAlCliente, String> {
}
