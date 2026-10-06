package com.immosync.immosyncapp.entities;

import jakarta.persistence.*;

import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "categorie")
public class Categorie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "libelle", nullable = false, length = 100)
    private String libelle;

    @OneToMany(mappedBy = "categorie")
    private Set<Prestataire> prestataires = new LinkedHashSet<>();

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public Set<Prestataire> getPrestataires() {
        return prestataires;
    }

    public void setPrestataires(Set<Prestataire> prestataires) {
        this.prestataires = prestataires;
    }

}