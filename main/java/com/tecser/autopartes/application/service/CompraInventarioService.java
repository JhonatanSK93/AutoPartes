package com.tecser.autopartes.application.service;

import com.tecser.autopartes.application.dto.CompraInventarioDto;
import com.tecser.autopartes.application.dto.DetalleCompraInventarioDto;
import com.tecser.autopartes.domain.model.CompraInventario;
import com.tecser.autopartes.domain.model.DetalleCompraInventario;
import com.tecser.autopartes.domain.model.Inventario;
import com.tecser.autopartes.domain.model.Usuario;
import com.tecser.autopartes.domain.port.in.CompraInventarioServicePort;
import com.tecser.autopartes.domain.port.out.CompraInventarioRepositoryPort;
import com.tecser.autopartes.domain.port.out.DetalleCompraInventarioRepositoryPort;
import com.tecser.autopartes.domain.port.out.InventarioRepositoryPort;
import com.tecser.autopartes.domain.port.out.UsuarioRepositoryPort;
import com.tecser.autopartes.security.SecurityUtils;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CompraInventarioService implements CompraInventarioServicePort {

    private final CompraInventarioRepositoryPort compraProveedorRepository;
    private final UsuarioRepositoryPort usuarioRepository;
    private final DetalleCompraInventarioRepositoryPort detalleCompraRepository;
    private final InventarioRepositoryPort inventarioRepository;

    public CompraInventarioService(
            CompraInventarioRepositoryPort compraProveedorRepository,
            UsuarioRepositoryPort usuarioRepository,
            DetalleCompraInventarioRepositoryPort detalleCompraRepository,
            InventarioRepositoryPort inventarioRepository
    ) {
        this.compraProveedorRepository = compraProveedorRepository;
        this.usuarioRepository = usuarioRepository;
        this.detalleCompraRepository = detalleCompraRepository;
        this.inventarioRepository = inventarioRepository;
    }

    @Override
    public CompraInventarioDto guardarCompra(CompraInventarioDto dto) {
        String correo = SecurityUtils.getCorreoUsuarioAutenticado();
        Usuario usuario = usuarioRepository.buscarPorCorreo(correo)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado con correo: " + correo));

        CompraInventario compra = dto.toEntity(usuario);
        CompraInventario guardado = compraProveedorRepository.guardar(compra);
        return CompraInventarioDto.fromEntity(guardado);
    }

    public CompraInventarioDto guardarCompraConDetalles(CompraInventarioDto dto) {
        String correo = SecurityUtils.getCorreoUsuarioAutenticado();
        Usuario usuario = usuarioRepository.buscarPorCorreo(correo)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado con correo: " + correo));

        CompraInventario compra = dto.toEntity(usuario);
        CompraInventario compraGuardada = compraProveedorRepository.guardar(compra);

        for (DetalleCompraInventarioDto detalleDto : dto.getDetalles()) {
            String nombreParte = detalleDto.getInventario().getNombreParte();

            Inventario inventario = inventarioRepository.buscarPorId(nombreParte)
                    .orElseGet(() -> new Inventario(
                            nombreParte,
                            detalleDto.getDescripcion(),
                            0,
                            null,
                            BigDecimal.ZERO
                    ));

            int anterior = inventario.getCantidadDisponible() != null ? inventario.getCantidadDisponible() : 0;
            BigDecimal precioAnterior = inventario.getPrecio() != null ? inventario.getPrecio() : BigDecimal.ZERO;

            int nuevaCantidad = detalleDto.getCantidad();
            BigDecimal precioNuevo = BigDecimal.valueOf(detalleDto.getPrecioUnitario());

            BigDecimal totalAnterior = precioAnterior.multiply(BigDecimal.valueOf(anterior));
            BigDecimal totalNuevo = precioNuevo.multiply(BigDecimal.valueOf(nuevaCantidad));
            BigDecimal nuevoPrecioPromedio = totalAnterior.add(totalNuevo)
                    .divide(BigDecimal.valueOf(anterior + nuevaCantidad), 2, RoundingMode.HALF_UP);

            inventario.setCantidadDisponible(anterior + nuevaCantidad);
            inventario.setPrecio(nuevoPrecioPromedio);

            if (inventario.getDescripcion() == null || inventario.getDescripcion().isBlank()) {
                inventario.setDescripcion(detalleDto.getDescripcion());
            }

            inventarioRepository.guardar(inventario);

            detalleDto.setInventario(inventario);
            DetalleCompraInventario detalle = detalleDto.toEntity(compraGuardada);
            detalleCompraRepository.guardar(detalle);
        }

        return CompraInventarioDto.fromEntity(compraGuardada);
    }

    @Override
    public List<CompraInventarioDto> listarCompras() {
        return compraProveedorRepository.listar().stream()
                .map(CompraInventarioDto::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public CompraInventarioDto buscarPorId(Long idCompra) {
        return compraProveedorRepository.buscarPorId(idCompra)
                .map(CompraInventarioDto::fromEntity)
                .orElseThrow(() -> new RuntimeException("Compra no encontrada con ID: " + idCompra));
    }
    @Override
    public CompraInventarioDto actualizarCompra(Long idCompra, CompraInventarioDto dto) {
    CompraInventario compraExistente = compraProveedorRepository.buscarPorId(idCompra)
            .orElseThrow(() -> new RuntimeException("Compra no encontrada con ID: " + idCompra));

    compraExistente.setFechaCompra(dto.getFechaCompra());

    CompraInventario actualizada = compraProveedorRepository.guardar(compraExistente);
    return CompraInventarioDto.fromEntity(actualizada);
}

    @Override
    public void eliminarCompra(Long idCompra) {
        compraProveedorRepository.eliminar(idCompra);
    }

    @Override
    public List<CompraInventarioDto> listarComprasPorUsuario() {
        String correo = SecurityUtils.getCorreoUsuarioAutenticado();
        Usuario usuario = usuarioRepository.buscarPorCorreo(correo)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado con correo: " + correo));

        return compraProveedorRepository.listar().stream()
                .filter(c -> c.getUsuario().getId().equals(usuario.getId()))
                .map(CompraInventarioDto::fromEntity)
                .collect(Collectors.toList());
    }

    public void eliminarCompraConDetalles(Long idCompra) {
    compraProveedorRepository.buscarPorId(idCompra)
        .orElseThrow(() -> new RuntimeException("CompraProveedor no encontrada con ID: " + idCompra));

    var detalles = detalleCompraRepository.buscarPorCompraId(idCompra);

    for (DetalleCompraInventario detalle : detalles) {
        Inventario inventario = detalle.getInventario();
        int cantidadDetalle = detalle.getCantidad();
        int actual = inventario.getCantidadDisponible() != null ? inventario.getCantidadDisponible() : 0;

        inventario.setCantidadDisponible(Math.max(0, actual - cantidadDetalle));
        inventarioRepository.guardar(inventario);
    }

    detalleCompraRepository.eliminarPorCompraId(idCompra);
    compraProveedorRepository.eliminar(idCompra);
    }

}
