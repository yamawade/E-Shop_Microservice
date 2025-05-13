package com.groupeisi.MicroServicieCommande.entity;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
public class Commande {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String etat_commande;
    private LocalDate dateCommande;

    // On stocke uniquement l'ID de l'utilisateur
    private Long utilisateurId;

    @OneToMany(mappedBy = "commande", cascade = CascadeType.ALL)
    private List<DetailCommande> details;

    // Constructeurs
    public Commande() {
    }

    public Commande(String etat_commande, LocalDate dateCommande, Long utilisateurId) {
        this.etat_commande = etat_commande;
        this.dateCommande = dateCommande;
        this.utilisateurId = utilisateurId;
    }

    // Getters et Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEtat_commande() {
        return etat_commande;
    }

    public void setEtat_commande(String etat_commande) {
        this.etat_commande = etat_commande;
    }

    public LocalDate getDateCommande() {
        return dateCommande;
    }

    public void setDateCommande(LocalDate dateCommande) {
        this.dateCommande = dateCommande;
    }

    public Long getUtilisateurId() {
        return utilisateurId;
    }

    public void setUtilisateurId(Long utilisateurId) {
        this.utilisateurId = utilisateurId;
    }

    public List<DetailCommande> getDetails() {
        return details;
    }

    public void setDetails(List<DetailCommande> details) {
        this.details = details;
    }

    @Override
    public String toString() {
        return "Commande{" +
                "id=" + id +
                ", etat_commande='" + etat_commande + '\'' +
                ", dateCommande=" + dateCommande +
                ", utilisateurId=" + utilisateurId +
                '}';
    }
}
