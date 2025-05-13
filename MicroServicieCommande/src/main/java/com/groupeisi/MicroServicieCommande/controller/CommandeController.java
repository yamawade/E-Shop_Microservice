package com.groupeisi.MicroServicieCommande.controller;


import com.groupeisi.MicroServicieCommande.entity.Commande;
import com.groupeisi.MicroServicieCommande.service.CommandeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/commandes")
public class CommandeController {

    @Autowired
    private CommandeService commandeService;

    @GetMapping
    public List<Commande> getAll() {
        return commandeService.getAllCommandes();
    }

    @GetMapping("/{id}")
    public Commande getById(@PathVariable Long id) {
        return commandeService.getCommandeById(id)
                .orElseThrow(() -> new RuntimeException("Commande introuvable"));
    }

    @PostMapping
    public Commande create(@RequestBody Commande commande) {
        return commandeService.createCommande(commande);
    }

//    @PutMapping("/{id}")
//    public Commande update(@PathVariable Long id, @RequestBody Commande commande) {
//        return commandeService.updateCommande(id, commande);
//    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        commandeService.deleteCommande(id);
    }
}
