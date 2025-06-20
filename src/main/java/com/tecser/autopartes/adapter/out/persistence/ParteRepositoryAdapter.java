package com.tecser.autopartes.adapter.out.persistence;

import com.tecser.autopartes.domain.model.Parte;
import com.tecser.autopartes.domain.port.out.ParteRepositoryPort;
import com.tecser.autopartes.adapter.out.persistence.repository.ParteJpaRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class ParteRepositoryAdapter implements ParteRepositoryPort {

    private final ParteJpaRepository repository;

    public ParteRepositoryAdapter(ParteJpaRepository repository) {
        this.repository = repository;
    }

    @Override
    public Parte guardar(Parte parte) {
        return repository.save(parte);
    }

    @Override
    public List<Parte> listar() {
        return repository.findAll();
    }

    @Override
    public Optional<Parte> buscarPorId(String id) {
        return repository.findById(id);
    }

    @Override
    public Parte actualizar(Parte parte) {
        return repository.save(parte); // JPA lo actualiza si ya existe
    }

    @Override
    public void eliminar(String id) {
        repository.deleteById(id);
    }
}
