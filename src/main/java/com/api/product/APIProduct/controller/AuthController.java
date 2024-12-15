package com.api.product.APIProduct.controller;

import com.api.product.APIProduct.security.JwtTokenUtil;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")

public class AuthController {

    private final JwtTokenUtil jwtTokenUtil;

    public AuthController(JwtTokenUtil jwtTokenUtil) {
        this.jwtTokenUtil = jwtTokenUtil;
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestParam String username, @RequestParam String role) {
        // Aquí puedes validar el usuario y su contraseña contra la base de datos
        // Para simplificar, asumimos que las credenciales son válidas
        String token = jwtTokenUtil.generateToken(username, role);
        return ResponseEntity.ok(token);
    }
}
