package com.groupeisi.ProduitMicroservice.repository;

import com.groupeisi.ProduitMicroservice.entity.Produit;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProduitRepository extends JpaRepository<Produit, Long> {
}
