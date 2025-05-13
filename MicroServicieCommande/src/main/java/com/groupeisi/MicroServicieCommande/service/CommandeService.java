package com.groupeisi.MicroServicieCommande.service;

import com.groupeisi.MicroServicieCommande.entity.Commande;
import com.groupeisi.MicroServicieCommande.repository.CommandeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;

@Service
public class CommandeService {

    @Autowired
    private CommandeRepository commandeRepository;

    @Autowired
    private RestTemplate restTemplate;

    public List<Commande> getAllCommandes() {
        return commandeRepository.findAll();
    }

    public Optional<Commande> getCommandeById(Long id) {
        return commandeRepository.findById(id);
    }

    public Commande createCommande(Commande commande) {
        Long userId = commande.getUtilisateurId();
        if (!checkUserExists(userId)) {
            throw new RuntimeException("Utilisateur avec l'ID " + userId + " introuvable.");
        }

        return commandeRepository.save(commande);
    }

    public void deleteCommande(Long id) {
        commandeRepository.deleteById(id);
    }

    public boolean checkUserExists(Long idUser) {
        try {
            ResponseEntity<?> response = restTemplate.getForEntity(
                    "http://localhost:8082/api/utilisateurs/" + idUser,
                    Object.class
            );
            return response.getStatusCode().is2xxSuccessful();
        } catch (Exception e) {
            return false;
        }
    }
}
