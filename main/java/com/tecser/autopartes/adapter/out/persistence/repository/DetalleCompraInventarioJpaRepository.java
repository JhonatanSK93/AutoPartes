package com.tecser.autopartes.adapter.out.persistence.repository;

import com.tecser.autopartes.domain.model.DetalleCompraInventario;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface DetalleCompraInventarioJpaRepository extends JpaRepository<DetalleCompraInventario, Long> {
    List<DetalleCompraInventario> findByCompra_IdCompra(Long idCompra);

    void deleteByCompra_IdCompra(Long idCompra);
}
