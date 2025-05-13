package com.groupeisi.MicroServicieUtilisateur.controller;

import com.groupeisi.MicroServicieUtilisateur.entity.Utilisateurs;
import com.groupeisi.MicroServicieUtilisateur.service.UtilisateurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/utilisateurs")
public class UtilisateurController {

    @Autowired
    private UtilisateurService utilisateurService;

    @GetMapping
    public List<Utilisateurs> getAll() {
        return utilisateurService.getAllUtilisateurs();
    }

    @GetMapping("/{id}")
    public Utilisateurs getById(@PathVariable Long id) {
        return utilisateurService.getUtilisateurById(id)
                .orElseThrow(() -> new RuntimeException("Utilisateur introuvable"));
    }

    @PostMapping
    public Utilisateurs create(@RequestBody Utilisateurs utilisateur) {
        return utilisateurService.createUtilisateur(utilisateur);
    }

    @PutMapping("/{id}")
    public Utilisateurs update(@PathVariable Long id, @RequestBody Utilisateurs utilisateur) {
        return utilisateurService.updateUtilisateur(id, utilisateur);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        utilisateurService.deleteUtilisateur(id);
    }
}

