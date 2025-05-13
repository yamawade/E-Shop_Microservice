package com.groupeisi.MicroServicieCommande.entity;

import jakarta.persistence.*;

@Entity
public class DetailCommande {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int nbr_produit;
    private double prix;

    private Long produitId;

    @ManyToOne
    @JoinColumn(name = "commande_id")
    private Commande commande;

    public DetailCommande() {}

    public DetailCommande(int nbr_produit, double prix, Long produitId, Commande commande) {
        this.nbr_produit = nbr_produit;
        this.prix = prix;
        this.produitId = produitId;
        this.commande = commande;
    }

    // Getters et setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getNbr_produit() {
        return nbr_produit;
    }

    public void setNbr_produit(int nbr_produit) {
        this.nbr_produit = nbr_produit;
    }

    public double getPrix() {
        return prix;
    }

    public void setPrix(double prix) {
        this.prix = prix;
    }

    public Long getProduitId() {
        return produitId;
    }

    public void setProduitId(Long produitId) {
        this.produitId = produitId;
    }

    public Commande getCommande() {
        return commande;
    }

    public void setCommande(Commande commande) {
        this.commande = commande;
    }
}
