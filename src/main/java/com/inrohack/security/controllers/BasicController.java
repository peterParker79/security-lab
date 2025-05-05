package com.inrohack.security.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")

public class BasicController {

    @GetMapping("public/users")
    public ResponseEntity<String> publicUsers(){
        return ResponseEntity.ok("Acceso a public. Es de acceso publico");
    }

    @GetMapping("/admin")
    public ResponseEntity<String> admin(){
        return ResponseEntity.ok("Acceso a admin. Es de acceso administrador");
    }




}
