package com.tecser.autopartes.security;

import com.tecser.autopartes.adapter.out.persistence.repository.UsuarioJpaRepository;
import com.tecser.autopartes.adapter.out.persistence.repository.RolJpaRepository;
import com.tecser.autopartes.adapter.out.persistence.repository.UsuarioRolJpaRepository;
import com.tecser.autopartes.application.dto.UsuarioConRolDto;
import com.tecser.autopartes.domain.model.Usuario;
import com.tecser.autopartes.domain.model.UsuarioRol;
import com.tecser.autopartes.domain.model.Rol;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = {
    "http://localhost:4200",
    "http://192.168.2.30:4200"
})
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider jwtTokenProvider;
    private final UsuarioJpaRepository usuarioRepository;
    private final RolJpaRepository rolRepository;
    private final UsuarioRolJpaRepository usuarioRolRepository;
    private final PasswordEncoder passwordEncoder;

    public AuthController(
        AuthenticationManager authenticationManager,
        JwtTokenProvider jwtTokenProvider,
        UsuarioJpaRepository usuarioRepository,
        RolJpaRepository rolRepository,
        UsuarioRolJpaRepository usuarioRolRepository,
        PasswordEncoder passwordEncoder
    ) {
        this.authenticationManager = authenticationManager;
        this.jwtTokenProvider = jwtTokenProvider;
        this.usuarioRepository = usuarioRepository;
        this.rolRepository = rolRepository;
        this.usuarioRolRepository = usuarioRolRepository;
        this.passwordEncoder = passwordEncoder;
    }

    // 🔐 LOGIN
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AuthRequest request) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getCorreo(), request.getContrasena())
            );

            String correo = authentication.getName();
            String token = jwtTokenProvider.generarToken(correo);

            Usuario usuario = usuarioRepository.findByCorreo(correo)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

            UsuarioRol usuarioRol = usuarioRolRepository.findByUsuarioId(usuario.getId())
                .orElseThrow(() -> new RuntimeException("Rol no asignado al usuario"));

            Long rolId = usuarioRol.getRol().getId();

            return ResponseEntity.ok(new AuthResponse(token, rolId));
        } catch (AuthenticationException ex) {
            return ResponseEntity.status(401).body("Credenciales inválidas");
        }
    }

    // ✅ REGISTRO DE USUARIO
    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterRequest request) {
        if (usuarioRepository.findByCorreo(request.getCorreo()).isPresent()) {
            return ResponseEntity.badRequest().body("El correo ya está registrado");
        }

        if (request.getRolId() == null) {
            return ResponseEntity.badRequest().body("Debes indicar rolId");
        }

        Rol rol = rolRepository.findById(request.getRolId())
            .orElseThrow(() -> new RuntimeException("Rol no encontrado con ID: " + request.getRolId()));

        Usuario nuevo = new Usuario();
        nuevo.setCorreo(request.getCorreo());
        nuevo.setNombreCompleto(request.getNombreCompleto());
        nuevo.setContrasena(passwordEncoder.encode(request.getContrasena()));

        usuarioRepository.save(nuevo);
        usuarioRolRepository.save(new UsuarioRol(nuevo, rol));

        return ResponseEntity.ok("Usuario registrado con rol: " + rol.getNombre());
    }

    // 🔄 ACTUALIZAR USUARIO (solo SUPER_ADMIN)
    @PreAuthorize("hasRole('SUPER_ADMIN')")
    @PutMapping("/usuarios/{id}")
    public ResponseEntity<?> actualizarUsuario(@PathVariable Long id, @RequestBody RegisterRequest request) {
        Optional<Usuario> optionalUsuario = usuarioRepository.findById(id);
        if (optionalUsuario.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        Usuario usuario = optionalUsuario.get();
        usuario.setCorreo(request.getCorreo());
        usuario.setNombreCompleto(request.getNombreCompleto());

        if (request.getContrasena() != null && !request.getContrasena().isBlank()) {
            usuario.setContrasena(passwordEncoder.encode(request.getContrasena()));
        }

        usuarioRepository.save(usuario);

        Rol nuevoRol = rolRepository.findById(request.getRolId())
            .orElseThrow(() -> new RuntimeException("Rol no encontrado con ID: " + request.getRolId()));

        UsuarioRol usuarioRol = usuarioRolRepository.findByUsuarioId(usuario.getId())
            .orElseThrow(() -> new RuntimeException("Rol no asignado al usuario"));

        usuarioRol.setRol(nuevoRol);
        usuarioRolRepository.save(usuarioRol);

        return ResponseEntity.ok("✅ Usuario actualizado correctamente");
    }

    // 🗑️ ELIMINAR USUARIO (solo SUPER_ADMIN)
    @PreAuthorize("hasRole('SUPER_ADMIN')")
    @DeleteMapping("/usuarios/{id}")
    public ResponseEntity<String> eliminarUsuario(@PathVariable Long id) {
        if (!usuarioRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }

        usuarioRolRepository.deleteByUsuarioId(id);
        usuarioRepository.deleteById(id);

        return ResponseEntity.ok("🗑️ Usuario eliminado correctamente");
    }

    // 📋 LISTAR TODOS LOS USUARIOS Y SUS ROLES (solo SUPER_ADMIN)
    @PreAuthorize("hasRole('SUPER_ADMIN')")
    @GetMapping("/usuarios")
    public ResponseEntity<?> listarUsuarios() {
        List<Usuario> usuarios = usuarioRepository.findAll();

        List<UsuarioConRolDto> respuesta = usuarios.stream().map(usuario -> {
            UsuarioRol usuarioRol = usuarioRolRepository.findByUsuarioId(usuario.getId()).orElse(null);
            String rolNombre = (usuarioRol != null) ? usuarioRol.getRol().getNombre() : "Sin rol asignado";

            return new UsuarioConRolDto(
                    usuario.getId(),
                    usuario.getCorreo(),
                    usuario.getNombreCompleto(),
                    rolNombre
            );
        }).toList();

        return ResponseEntity.ok(respuesta);
    }
} 