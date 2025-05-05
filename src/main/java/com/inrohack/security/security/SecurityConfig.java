package com.inrohack.security.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration


public class SecurityConfig {

    @Bean
    // Se definen la cadena de filtros de seguridad
    // HttpSecurity -> objeto que permite configurar la seguridad HTTP
    public SecurityFilterChain filterChain(HttpSecurity http)    throws Exception {
        http
                // aqui indicamos que rutas tienen acceso sin autenticación y con auth.
                .authorizeHttpRequests( authz ->authz
                        .requestMatchers("/api/public/**").permitAll()
                        .anyRequest().authenticated()
                )
                // El tipo de autenticación: básica http
                .httpBasic(Customizer.withDefaults());

        //aquí devolvemos la configuración completa.
        return http.build();
    }

}
