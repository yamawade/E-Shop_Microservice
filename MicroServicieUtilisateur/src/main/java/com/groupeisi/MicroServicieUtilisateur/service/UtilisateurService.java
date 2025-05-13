package com.groupeisi.MicroServicieUtilisateur.service;

import com.groupeisi.MicroServicieUtilisateur.entity.Utilisateurs;
import com.groupeisi.MicroServicieUtilisateur.repository.UtilisateurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class UtilisateurService {

    @Autowired
    private UtilisateurRepository utilisateurRepository;

    public List<Utilisateurs> getAllUtilisateurs() {
        return utilisateurRepository.findAll();
    }

    public Optional<Utilisateurs> getUtilisateurById(Long id) {
        return utilisateurRepository.findById(id);
    }

    public Utilisateurs createUtilisateur(Utilisateurs utilisateur) {
        return utilisateurRepository.save(utilisateur);
    }

    public Utilisateurs updateUtilisateur(Long id, Utilisateurs updatedUser) {
        Utilisateurs utilisateur = utilisateurRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Utilisateur introuvable avec l'ID : " + id));

        utilisateur.setNom(updatedUser.getNom());
        utilisateur.setEmail(updatedUser.getEmail());
        utilisateur.setMotDePasse(updatedUser.getMotDePasse());

        return utilisateurRepository.save(utilisateur);
    }

    public void deleteUtilisateur(Long id) {
        utilisateurRepository.deleteById(id);
    }
}

