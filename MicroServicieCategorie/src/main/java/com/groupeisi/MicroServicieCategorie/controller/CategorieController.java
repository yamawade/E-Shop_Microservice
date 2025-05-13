package com.groupeisi.MicroServicieCategorie.controller;

import com.groupeisi.MicroServicieCategorie.entity.Categorie;
import com.groupeisi.MicroServicieCategorie.service.CategorieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categories")
public class CategorieController {

    @Autowired
    private CategorieService categorieService;

    @GetMapping
    public List<Categorie> getAll() {
        return categorieService.getAllCategories();
    }

    @GetMapping("/{id}")
    public Categorie getById(@PathVariable Long id) {
        return categorieService.getCategorieById(id)
                .orElseThrow(() -> new RuntimeException("Catégorie non trouvée"));
    }

    @PostMapping
    public Categorie create(@RequestBody Categorie categorie) {
        return categorieService.createCategorie(categorie);
    }

    @PutMapping("/{id}")
    public Categorie update(@PathVariable Long id, @RequestBody Categorie categorie) {
        return categorieService.updateCategorie(id, categorie);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        categorieService.deleteCategorie(id);
    }
}

