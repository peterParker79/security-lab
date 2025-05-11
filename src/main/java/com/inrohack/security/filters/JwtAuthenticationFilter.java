package com.inrohack.security.filters;


import com.inrohack.security.services.JwtService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.stream.Collectors;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    @Autowired
    JwtService jwtService;

    @Override

    /*
    Filtro que mira los header de la request
    Comprueba que tenemos un header de 'Aturorization' con Bearer.

    Extrae el token del header.
    Estrae usuario y roles
    Métodos Granted -> de los roles del string roles genera las Autoridades para Spring Sec
    Mete estos roles en el conexto de la app para aplicar la reguridad a los endpoints

     */
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        final String authHeader = request.getHeader("Authorization");

        // Es obligatorio que no sea nulo y que empiece con Bearer
        // Si no lo cumple se pasa al siguiente filtro...
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            //response.setStatus(HttpServletResponse.SC_UNAUTHORIZED); //added to return 401
           // response.getWriter().write("Missing token");
            filterChain.doFilter(request, response);
            return;// para que no ejecute el resto del código
        }

        // Se debe quitar el prefijo Bearer
        String token = authHeader.replace("Bearer ", "");

        // Validate the token
        if (!jwtService.validateToken(token)) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED); //added
            response.getWriter().write("Invalid token"); //added
            //filterChain.doFilter(request, response);
            return;
        }

        // If the token is valid, extract the username and roles
        String username = jwtService.extractUsername(token);
        String rolesString = jwtService.extractRoles(token);

        // Convert the roles string to a list of Spring Security authorities
        Collection<GrantedAuthority> authorities = extractAuthorities(rolesString);

        // Create an Authentication object with user information
        UsernamePasswordAuthenticationToken authentication =
                new UsernamePasswordAuthenticationToken(username, null, authorities);

        // Set Authentication in the security context
        SecurityContextHolder.getContext().setAuthentication(authentication);

        // Continue with the rest of the filters
        filterChain.doFilter(request, response);
    }

    private Collection<GrantedAuthority> extractAuthorities(String rolesString) {
        // Process the roles string. Example: "[ROLE_ADMIN, ROLE_USER]"
        if (rolesString == null || rolesString.isEmpty()) {
            return Collections.emptyList();
        }

        // Remove brackets and split by commas
        String roles = rolesString.replace("[", "").replace("]", "");
        String[] roleArray = roles.split(",");

        return Arrays.stream(roleArray)
                .map(String::trim) // Remove spaces
                .map(SimpleGrantedAuthority::new) // We'll have roles in the correct format for Spring Security to recognize them
                .collect(Collectors.toList());
    }
}
