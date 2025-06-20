package com.tecser.autopartes.application.service;

import com.tecser.autopartes.adapter.out.persistence.repository.InventarioJpaRepository;
import com.tecser.autopartes.adapter.out.persistence.repository.ParteJpaRepository;
import com.tecser.autopartes.application.dto.InventarioDto;
import com.tecser.autopartes.domain.model.Inventario;
import com.tecser.autopartes.domain.model.Parte;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class InventarioService {

    private final InventarioJpaRepository inventarioRepository;
    private final ParteJpaRepository parteRepository;

    public InventarioService(InventarioJpaRepository inventarioRepository, ParteJpaRepository parteRepository) {
        this.inventarioRepository = inventarioRepository;
        this.parteRepository = parteRepository;
    }

    public List<Inventario> listarTodos() {
        List<Inventario> lista = new ArrayList<>();
        inventarioRepository.findAll().forEach(lista::add);
        return lista;
    }

    public Optional<Inventario> buscarPorId(Long idInventario) {
        return inventarioRepository.findById(idInventario);
    }

    public Inventario guardar(InventarioDto dto) {
        Parte parte = parteRepository.findById(dto.getIdParte())
                .orElseThrow(() -> new RuntimeException("Parte no encontrada"));

        Inventario inventario = new Inventario(parte, dto.getCantidadDisponible());
        return inventarioRepository.save(inventario);
    }

    public void eliminar(Long idInventario) {
        inventarioRepository.deleteById(idInventario);
    }
}
