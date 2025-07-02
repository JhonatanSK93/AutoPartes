package com.tecser.autopartes.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtTokenProvider tokenProvider;

    public JwtAuthenticationFilter(JwtTokenProvider tokenProvider) {
        this.tokenProvider = tokenProvider;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {

        System.out.println("[JWT Filter] Método: " + request.getMethod() + " → URL: " + request.getRequestURI());
        System.out.println("[JWT Filter] Authorization header: " + request.getHeader("Authorization"));

        String header = request.getHeader("Authorization");
        String token = null;
        String username = null;

        if (header != null && header.startsWith("Bearer ")) {
            token = header.substring(7);
            username = tokenProvider.obtenerUsernameDelToken(token);
            System.out.println("[JWT Filter] Token: " + token);
            System.out.println("[JWT Filter] Usuario extraído: " + username);
            System.out.println("[JWT Filter] Token válido ✅");
        } else {
            System.out.println("[JWT Filter] Token no válido ❌");
        }

        Authentication actual = SecurityContextHolder.getContext().getAuthentication();

        if (username != null && (actual == null || actual instanceof AnonymousAuthenticationToken)) {
            if (tokenProvider.validarToken(token)) {

                List<String> roles = tokenProvider.obtenerRolesDelToken(token);
                var authorities = roles.stream()
                        .map(role -> new SimpleGrantedAuthority("ROLE_" + role))
                        .toList();

                var authentication = new UsernamePasswordAuthenticationToken(username, null, authorities);
                authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authentication);

                System.out.println("[JWT Filter] Usuario autenticado con roles: " + authorities);
            }
        }

        filterChain.doFilter(request, response);
    }
}
