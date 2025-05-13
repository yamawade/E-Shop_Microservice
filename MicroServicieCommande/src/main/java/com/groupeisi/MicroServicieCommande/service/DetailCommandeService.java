package com.groupeisi.MicroServicieCommande.service;

import com.groupeisi.MicroServicieCommande.entity.DetailCommande;
import com.groupeisi.MicroServicieCommande.repository.DetailCommandeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;

@Service
public class DetailCommandeService {

    @Autowired
    private DetailCommandeRepository detailCommandeRepository;

    @Autowired
    private RestTemplate restTemplate;

    public List<DetailCommande> getAllDetails() {
        return detailCommandeRepository.findAll();
    }

    public Optional<DetailCommande> getDetailById(Long id) {
        return detailCommandeRepository.findById(id);
    }

    public DetailCommande createDetail(DetailCommande detail) {
        Long produitId = detail.getProduitId();
        if (!checkProduitExists(produitId)) {
            throw new RuntimeException("Produit avec l'ID " + produitId + " introuvable.");
        }

        return detailCommandeRepository.save(detail);
    }

    public void deleteDetail(Long id) {
        detailCommandeRepository.deleteById(id);
    }

    private boolean checkProduitExists(Long produitId) {
        try {
            ResponseEntity<?> response = restTemplate.getForEntity(
                    "http://localhost:8084/api/produits/" + produitId,
                    Object.class
            );
            return response.getStatusCode().is2xxSuccessful();
        } catch (Exception e) {
            return false;
        }
    }
}
