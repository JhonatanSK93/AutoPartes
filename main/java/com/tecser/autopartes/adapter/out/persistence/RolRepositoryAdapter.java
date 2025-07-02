package com.tecser.autopartes.adapter.out.persistence;

import com.tecser.autopartes.adapter.out.persistence.repository.RolJpaRepository;
import com.tecser.autopartes.domain.model.Rol;
import com.tecser.autopartes.domain.port.out.RolRepositoryPort;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class RolRepositoryAdapter implements RolRepositoryPort {

    private final RolJpaRepository rolJpaRepository;

    public RolRepositoryAdapter(RolJpaRepository rolJpaRepository) {
        this.rolJpaRepository = rolJpaRepository;
    }

    @Override
    public Rol guardar(Rol rol) {
        return rolJpaRepository.save(rol); // ✅ devuelve el Rol guardado
    }

    @Override
    public void guardar(List<Rol> roles) {
        rolJpaRepository.saveAll(roles);
    }

    @Override
    public long count() {
        return rolJpaRepository.count();
    }

    @Override
    public Optional<Rol> buscarPorNombre(String nombre) {
        return rolJpaRepository.findByNombre(nombre);
    }

    @Override
    public List<Rol> listarTodos() {
        return rolJpaRepository.findAll();
    }
}
