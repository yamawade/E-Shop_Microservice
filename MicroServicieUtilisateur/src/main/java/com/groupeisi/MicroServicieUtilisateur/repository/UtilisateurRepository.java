package com.groupeisi.MicroServicieUtilisateur.repository;

import com.groupeisi.MicroServicieUtilisateur.entity.Utilisateurs;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UtilisateurRepository extends JpaRepository<Utilisateurs, Long> {
}
