package com.tecser.autopartes.security;

import com.tecser.autopartes.adapter.out.persistence.repository.UsuarioJpaRepository;
import com.tecser.autopartes.adapter.out.persistence.repository.UsuarioRolJpaRepository;
import com.tecser.autopartes.domain.model.Usuario;
import com.tecser.autopartes.domain.model.UsuarioRol;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;
import java.util.List;

/**
 * Genera, lee y valida tokens JWT firmados con HS256.
 * Incluye logs detallados para diagnosticar por qué un token es rechazado.
 */
@Component
public class JwtTokenProvider {

    @Value("${jwt.secret}")
    private String JWT_SECRET;

    private static final long JWT_EXPIRATION_MS = 12 * 60 * 60 * 1000; // 12 horas
    private Key key;

    private final UsuarioJpaRepository usuarioRepository;
    private final UsuarioRolJpaRepository usuarioRolRepository;

    public JwtTokenProvider(UsuarioJpaRepository usuarioRepository,
                            UsuarioRolJpaRepository usuarioRolRepository) {
        this.usuarioRepository = usuarioRepository;
        this.usuarioRolRepository = usuarioRolRepository;
    }

    /**
     * Se ejecuta una vez al iniciar el bean y construye la clave HMAC a partir del secret.
     */
    @PostConstruct
    public void init() {
        this.key = Keys.hmacShaKeyFor(JWT_SECRET.getBytes(StandardCharsets.UTF_8));
        System.out.println("[JwtTokenProvider] 🔐 Clave secreta cargada, tamaño = " + JWT_SECRET.length());
    }

    /**
     * Genera un JWT con subject = correo y un claim "roles" que contiene una lista con el nombre del rol del usuario.
     */
    public String generarToken(String correo) {
        Usuario usuario = usuarioRepository.findByCorreo(correo)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado: " + correo));

        UsuarioRol usuarioRol = usuarioRolRepository.findByUsuarioId(usuario.getId())
                .orElseThrow(() -> new RuntimeException("El usuario no tiene rol asignado"));

        List<String> roles = List.of(usuarioRol.getRol().getNombre());

        String token = Jwts.builder()
                .setSubject(correo)
                .claim("roles", roles)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + JWT_EXPIRATION_MS))
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();

        System.out.println("[JwtTokenProvider] 🪙 Token generado para " + correo + " con roles " + roles);
        return token;
    }

    public String obtenerUsernameDelToken(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }

    public List<String> obtenerRolesDelToken(String token) {
        Claims claims = Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody();

        Object rolesObject = claims.get("roles");
        ObjectMapper mapper = new ObjectMapper();
        return mapper.convertValue(rolesObject, new TypeReference<List<String>>() {});
    }

    /**
     * Valida la firma y expiración del token. En caso de fallo, imprime el motivo concreto.
     */
    public boolean validarToken(String token) {
        try {
            Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token);
            return true;
        } catch (ExpiredJwtException e) {
            System.out.println("[JwtTokenProvider] ❌ Token expirado en " + e.getClaims().getExpiration());
        } catch (UnsupportedJwtException e) {
            System.out.println("[JwtTokenProvider] ❌ Token no soportado → " + e.getMessage());
        } catch (MalformedJwtException e) {
            System.out.println("[JwtTokenProvider] ❌ Token malformado → " + e.getMessage());
        } catch (SignatureException e) {
            System.out.println("[JwtTokenProvider] ❌ Firma inválida: la clave no coincide → " + e.getMessage());
        } catch (IllegalArgumentException e) {
            System.out.println("[JwtTokenProvider] ❌ Token vacío o nulo → " + e.getMessage());
        }
        return false;
    }
}
