package com.groupeisi.MicroServicieCommande.repository;

import com.groupeisi.MicroServicieCommande.entity.Commande;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommandeRepository extends JpaRepository<Commande, Long>{
}
