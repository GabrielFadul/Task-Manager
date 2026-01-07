package com.gabrielFadul.taskManager.infra.configSecurity;

import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {
    private final AuthenticationManager authenticationManager;

    public AuthenticationController(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody LoginRequest data) {
        // Cria um "crachá" provisório com e-mail e senha
        var usernamePassword = new UsernamePasswordAuthenticationToken(data.email(), data.password());
        // O Security vai usar o seu UserDetailsService (passo 1) para validar se a senha bate
        var auth = this.authenticationManager.authenticate(usernamePassword);

        return ResponseEntity.ok().build(); // Se chegou aqui, logou!
    }
}
