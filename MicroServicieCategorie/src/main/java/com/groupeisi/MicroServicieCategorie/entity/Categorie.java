package com.groupeisi.MicroServicieCategorie.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;


@Entity
public class Categorie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nom_categorie;


    public Categorie() {}

    public Categorie(String nom) {
        this.nom_categorie = nom;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNom() {
        return nom_categorie;
    }

    public void setNom(String nom) {
        this.nom_categorie = nom;
    }

    @Override
    public String toString() {
        return "Categorie{" +
                "id=" + id +
                ", nom='" + nom_categorie + '\'' +
                '}';
    }
}
