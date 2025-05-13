package com.groupeisi.ProduitMicroservice.entity;

import jakarta.persistence.*;

@Entity
public class Produit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nom_produit;
    private double prix;
    private String description;
    private String image;
    private int quantite;

    // Plus de relation directe avec Categorie
    private Long idCategorie;

    public Produit() {}

    public Produit(String nom, double prix, String description, String image, int quantite, Long idCategorie) {
        this.nom_produit = nom;
        this.prix = prix;
        this.description = description;
        this.image = image;
        this.quantite = quantite;
        this.idCategorie = idCategorie;
    }

    // Getters et Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNom() {
        return nom_produit;
    }

    public void setNom(String nom) {
        this.nom_produit = nom;
    }

    public double getPrix() {
        return prix;
    }

    public void setPrix(double prix) {
        this.prix = prix;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getQuantite() {
        return quantite;
    }

    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }

    public Long getIdCategorie() {
        return idCategorie;
    }

    public void setIdCategorie(Long idCategorie) {
        this.idCategorie = idCategorie;
    }

    @Override
    public String toString() {
        return "Produit{" +
                "id=" + id +
                ", nom='" + nom_produit + '\'' +
                ", prix=" + prix +
                ", description='" + description + '\'' +
                ", image='" + image + '\'' +
                ", quantite=" + quantite +
                ", idCategorie=" + idCategorie +
                '}';
    }
}
