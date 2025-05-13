package com.groupeisi.ProduitMicroservice.controller;

import com.groupeisi.ProduitMicroservice.entity.Produit;
import com.groupeisi.ProduitMicroservice.service.ProduitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/api/produits")
public class ProduitController {

    @Autowired
    private ProduitService produitService;

    // ✅ GET /api/produits (avec HATEOAS)
    @GetMapping
    public CollectionModel<EntityModel<Produit>> getAllProduits() {
        List<EntityModel<Produit>> produits = produitService.getAllProduits().stream()
                .map(produit -> EntityModel.of(produit,
                        linkTo(methodOn(ProduitController.class).getProduit(produit.getId())).withSelfRel()))
                .collect(Collectors.toList());

        return CollectionModel.of(produits,
                linkTo(methodOn(ProduitController.class).getAllProduits()).withSelfRel());
    }

    // ✅ GET /api/produits/{id} (avec HATEOAS)
    @GetMapping("/{id}")
    public EntityModel<Produit> getProduit(@PathVariable Long id) {
        Produit produit = produitService.getProduitById(id)
                .orElseThrow(() -> new RuntimeException("Produit introuvable"));
        return EntityModel.of(produit,
                linkTo(methodOn(ProduitController.class).getProduit(id)).withSelfRel(),
                linkTo(methodOn(ProduitController.class).getAllProduits()).withRel("produits"));
    }

    // ✅ POST /api/produits
    @PostMapping
    public Produit createProduit(@RequestBody Produit produit) {
        return produitService.createProduit(produit);
    }

    // ✅ PUT /api/produits/{id}
    @PutMapping("/{id}")
    public Produit updateProduit(@PathVariable Long id, @RequestBody Produit produit) {
        return produitService.updateProduit(id, produit);
    }

    // ✅ DELETE /api/produits/{id}
    @DeleteMapping("/{id}")
    public void deleteProduit(@PathVariable Long id) {
        produitService.deleteProduit(id);
    }
}
