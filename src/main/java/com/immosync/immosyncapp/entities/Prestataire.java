package com.immosync.immosyncapp.entities;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "prestataire")
public class Prestataire {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "libelle", nullable = false, length = 200)
    private String libelle;

    @Lob
    @Column(name = "description", nullable = false)
    private String description;

    @Column(name = "prix_base", nullable = false, precision = 10, scale = 2)
    private BigDecimal prixBase;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "categorie_id")
    private Categorie categorie;

    @OneToMany(mappedBy = "prestataire")
    private Set<DevisTypePrestation> devisTypePrestations = new LinkedHashSet<>();

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getPrixBase() {
        return prixBase;
    }

    public void setPrixBase(BigDecimal prixBase) {
        this.prixBase = prixBase;
    }

    public Categorie getCategorie() {
        return categorie;
    }

    public void setCategorie(Categorie categorie) {
        this.categorie = categorie;
    }

    public Set<DevisTypePrestation> getDevisTypePrestations() {
        return devisTypePrestations;
    }

    public void setDevisTypePrestations(Set<DevisTypePrestation> devisTypePrestations) {
        this.devisTypePrestations = devisTypePrestations;
    }

}