package com.groupeisi.MicroServicieUtilisateur.repository;

import com.groupeisi.MicroServicieUtilisateur.entity.Utilisateurs;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UtilisateurRepository extends JpaRepository<Utilisateurs, Long> {
    Optional<Utilisateurs> findByEmail(String email);
}
