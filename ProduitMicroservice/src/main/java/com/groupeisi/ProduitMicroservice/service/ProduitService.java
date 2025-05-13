package com.groupeisi.ProduitMicroservice.service;

import com.groupeisi.ProduitMicroservice.entity.Produit;
import com.groupeisi.ProduitMicroservice.repository.ProduitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;

@Service
public class ProduitService {

    @Autowired
    private ProduitRepository produitRepository;

    @Autowired
    private RestTemplate restTemplate;

    public List<Produit> getAllProduits() {
        return produitRepository.findAll();
    }

    public Optional<Produit> getProduitById(Long id) {
        return produitRepository.findById(id);
    }

    public Produit createProduit(Produit produit) {
        if (!checkCategorieExists(produit.getIdCategorie())) {
            throw new RuntimeException("La catégorie avec l'ID " + produit.getIdCategorie() + " n'existe pas.");
        }
        return produitRepository.save(produit);
    }

    public Produit updateProduit(Long id, Produit produitDetails) {
        Produit produit = produitRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Produit introuvable"));

        produit.setNom(produitDetails.getNom());
        produit.setPrix(produitDetails.getPrix());
        produit.setQuantite(produitDetails.getQuantite());
        produit.setDescription(produitDetails.getDescription());
        produit.setImage(produitDetails.getImage());

        if (!checkCategorieExists(produitDetails.getIdCategorie())) {
            throw new RuntimeException("La catégorie avec l'ID " + produitDetails.getIdCategorie() + " n'existe pas.");
        }

        produit.setIdCategorie(produitDetails.getIdCategorie());

        return produitRepository.save(produit);
    }

    public void deleteProduit(Long id) {
        produitRepository.deleteById(id);
    }

    public boolean checkCategorieExists(Long idCategorie) {
        try {
            ResponseEntity<?> response = restTemplate.getForEntity(
                    "http://localhost:8083/api/categories/" + idCategorie, // adapte cette URL selon ton service
                    Object.class
            );
            return response.getStatusCode().is2xxSuccessful();
        } catch (Exception e) {
            return false;
        }
    }
}
