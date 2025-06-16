package com.tecser.autopartes.adapter.out.persistence;

import com.tecser.autopartes.adapter.out.persistence.repository.ParteJpaRepository;
import com.tecser.autopartes.domain.model.Parte;
import com.tecser.autopartes.domain.port.out.ParteRepositoryPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class ParteRepositoryAdapter implements ParteRepositoryPort {

    private final ParteJpaRepository jpaRepository;

    @Override
    public List<Parte> findAll() {
        return jpaRepository.findAll();
    }

    @Override
    public Optional<Parte> findById(String id) {
        return jpaRepository.findById(id);
    }

    @Override
    public Parte save(Parte parte) {
        return jpaRepository.save(parte);
    }

    @Override
    public void deleteById(String id) {
        jpaRepository.deleteById(id);
    }
}
