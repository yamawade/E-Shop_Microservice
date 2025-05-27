package com.groupeisi.MicroServicieUtilisateur.controller;

import com.groupeisi.MicroServicieUtilisateur.entity.Utilisateurs;
import com.groupeisi.MicroServicieUtilisateur.repository.UtilisateurRepository;
import com.groupeisi.MicroServicieUtilisateur.security.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.*;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
public class AuthController {

    @Autowired
    private UtilisateurRepository utilisateurRepository;

    @Autowired
    private AuthenticationManager authManager;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/register")
    public Utilisateurs register(@RequestBody Utilisateurs utilisateur) {
        utilisateur.setMotDePasse(passwordEncoder.encode(utilisateur.getMotDePasse()));
        return utilisateurRepository.save(utilisateur);
    }

    @PostMapping("/auth/token")
    public Map<String, String> login(@RequestBody Map<String, String> body) {
        String email = body.get("email");
        String password = body.get("password");

        authManager.authenticate(new UsernamePasswordAuthenticationToken(email, password));
        String token = jwtUtil.generateToken(email);
        return Map.of("token", token);
    }

    @GetMapping("/auth/me")
    public Utilisateurs getCurrentUser(@RequestHeader("Authorization") String authHeader) {
        String email = jwtUtil.getUsernameFromToken(authHeader.substring(7));
        return utilisateurRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Utilisateur non trouvé"));
    }
}
