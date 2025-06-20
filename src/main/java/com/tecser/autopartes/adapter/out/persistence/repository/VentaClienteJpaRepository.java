package com.tecser.autopartes.adapter.out.persistence.repository;

import com.tecser.autopartes.domain.model.VentaCliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VentaClienteJpaRepository extends JpaRepository<VentaCliente, String> {
    // Puedes agregar métodos personalizados si lo necesitas más adelante
}
