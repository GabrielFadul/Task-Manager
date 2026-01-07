package com.gabrielFadul.taskManager.infra.configSecurity;

import com.gabrielFadul.taskManager.user.model.UserModel;
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
    private final TokenService tokenService;

    public AuthenticationController(AuthenticationManager authenticationManager, TokenService tokenService) {
        this.authenticationManager = authenticationManager;
        this.tokenService = tokenService;
    }

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody LoginRequest data) {
        var usernamePassword = new UsernamePasswordAuthenticationToken(data.email(), data.password());
        var auth = this.authenticationManager.authenticate(usernamePassword);

        // 1. Pegue o usuário que o Spring acabou de autenticar
        var user = (UserModel) auth.getPrincipal();

        // 2. Gere o token usando o serviço que já criamos
        var token = tokenService.generateToken(user);

        // 3. Retorne o token no corpo da resposta
        return ResponseEntity.ok(token);
    }
}
