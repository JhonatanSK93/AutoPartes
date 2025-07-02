package com.tecser.autopartes.application.service;

import com.tecser.autopartes.application.dto.VehiculoDto;
import com.tecser.autopartes.domain.model.Cliente;
import com.tecser.autopartes.domain.model.Usuario;
import com.tecser.autopartes.domain.model.Vehiculo;
import com.tecser.autopartes.adapter.out.persistence.repository.ClienteJpaRepository;
import com.tecser.autopartes.adapter.out.persistence.repository.UsuarioJpaRepository;
import com.tecser.autopartes.adapter.out.persistence.repository.VehiculoJpaRepository;

import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class VehiculoService {

    private final VehiculoJpaRepository vehiculoRepository;
    private final UsuarioJpaRepository usuarioRepository;
    private final ClienteJpaRepository clienteRepository;

    public VehiculoService(VehiculoJpaRepository vehiculoRepository, UsuarioJpaRepository usuarioRepository, ClienteJpaRepository clienteRepository) {
        this.vehiculoRepository = vehiculoRepository;
        this.usuarioRepository = usuarioRepository;
        this.clienteRepository = clienteRepository;
    }

    // ✅ Guardar un nuevo vehículo asociado al usuario autenticado
    public Vehiculo guardar(VehiculoDto dto) {
        String correo = obtenerCorreoAutenticado();
        System.out.println("Correo autenticado: " + correo);

        Usuario usuario = usuarioRepository.findByCorreo(correo)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado con correo: " + correo));

        Vehiculo vehiculo = new Vehiculo();
        vehiculo.setUsuario(usuario);
        vehiculo.setPlaca(dto.getPlaca().toUpperCase());
        vehiculo.setMarca(dto.getMarca());
        vehiculo.setModelo(dto.getModelo());
        vehiculo.setAnio(dto.getAnio());
        vehiculo.setColor(dto.getColor());
        vehiculo.setFechaIngreso(dto.getFechaIngreso() != null ? dto.getFechaIngreso() : LocalDate.now());

        
        if (dto.getCedulaCliente() !=null){        
        Cliente cliente = clienteRepository.findById(dto.getCedulaCliente())
                .orElseThrow(() -> new RuntimeException("Cliente no encontrado con cedula" + dto.getCedulaCliente()));
            vehiculo.setCliente(cliente);
        }  

        return vehiculoRepository.save(vehiculo);
    }

    // ✅ Listar todos los vehículos
    public List<Vehiculo> listarTodos() {
        return vehiculoRepository.findAll();
    }

    //Asignar cliente a un vehiculo
    public Vehiculo asignarCliente(String placa, String cedulaCliente) {
        Vehiculo vehiculo = vehiculoRepository.findById(placa.toUpperCase())
                .orElseThrow(() -> new RuntimeException("Vehiculo no encontrado con placa" + placa));

        if (vehiculo.getCliente() !=null){
            throw new RuntimeException("Este vehiculo ya tiene un cliente asignado");
        }

        Cliente cliente = clienteRepository.findById(cedulaCliente)
                .orElseThrow(() -> new RuntimeException("Cliente no encontrado con cedula" + cedulaCliente));

        vehiculo.setCliente(cliente);
        return vehiculoRepository.save(vehiculo);
    }

    // ✅ Buscar un vehículo por su placa
    public Optional<Vehiculo> buscarPorId(String placa) {
        return vehiculoRepository.findById(placa.toUpperCase());
    }

    // ✅ Eliminar un vehículo por placa
    public void eliminar(String placa) {
        vehiculoRepository.deleteById(placa.toUpperCase());
    }

    // ✅ Actualizar los datos de un vehículo
    public Optional<Vehiculo> actualizar(String placa, VehiculoDto dto) {
        return vehiculoRepository.findById(placa.toUpperCase()).map(vehiculo -> {
            vehiculo.setMarca(dto.getMarca());
            vehiculo.setModelo(dto.getModelo());
            vehiculo.setAnio(dto.getAnio());
            vehiculo.setColor(dto.getColor());
            vehiculo.setFechaIngreso(dto.getFechaIngreso() != null ? dto.getFechaIngreso() : LocalDate.now());

            return vehiculoRepository.save(vehiculo);
        });
    }

    // ✅ Listar vehículos del usuario autenticado
    public List<Vehiculo> listarMisVehiculos() {
        String correo = obtenerCorreoAutenticado();

        Usuario usuario = usuarioRepository.findByCorreo(correo)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado con correo: " + correo));

        return vehiculoRepository.findByUsuario(usuario);
    }

    // 🔒 Método privado reutilizable para validar autenticación
    private String obtenerCorreoAutenticado() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null || !authentication.isAuthenticated() || authentication instanceof AnonymousAuthenticationToken) {
            throw new RuntimeException("No hay un usuario autenticado");
        }

        return authentication.getName();
    }
}
