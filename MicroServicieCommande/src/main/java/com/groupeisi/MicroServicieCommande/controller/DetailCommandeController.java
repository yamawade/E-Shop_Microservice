package com.groupeisi.MicroServicieCommande.controller;

import com.groupeisi.MicroServicieCommande.entity.DetailCommande;
import com.groupeisi.MicroServicieCommande.service.DetailCommandeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/detail-commandes")
public class DetailCommandeController {

    @Autowired
    private DetailCommandeService detailCommandeService;

    @GetMapping
    public List<DetailCommande> getAll() {
        return detailCommandeService.getAllDetails();
    }

    @GetMapping("/{id}")
    public DetailCommande getById(@PathVariable Long id) {
        return detailCommandeService.getDetailById(id)
                .orElseThrow(() -> new RuntimeException("Détail commande introuvable"));
    }

    @PostMapping
    public DetailCommande create(@RequestBody DetailCommande detail) {
        return detailCommandeService.createDetail(detail);
    }

//    @PutMapping("/{id}")
//    public DetailCommande update(@PathVariable Long id, @RequestBody DetailCommande detail) {
//        return detailCommandeService.updateDetail(id, detail);
//    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        detailCommandeService.deleteDetail(id);
    }
}

