package com.immosync.immosyncapp.entities;

import jakarta.persistence.*;

import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "entrepreneur")
public class Entrepreneur {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "nom", nullable = false, length = 200)
    private String nom;

    @Column(name = "siret", nullable = false, length = 20)
    private String siret;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "telephone", nullable = false, length = 50)
    private String telephone;

    @Column(name = "adresse", nullable = false, length = 250)
    private String adresse;

    @Column(name = "ville", nullable = false, length = 100)
    private String ville;

    @Column(name = "code_postal", nullable = false, length = 20)
    private String codePostal;

    @OneToMany(mappedBy = "entrepreneur")
    private Set<DevisEntrepreneur> devisEntrepreneurs = new LinkedHashSet<>();

    @ManyToMany
    @JoinTable(
            name = "devis_type_entrepreneur", // C'est ici que ça bloquait !
            joinColumns = @JoinColumn(name = "entrepreneur_id"),
            inverseJoinColumns = @JoinColumn(name = "devis_type_id")
    )
    private Set<DevisType> devisTypes = new LinkedHashSet<>();

    @ManyToMany
    @JoinTable(
            name = "entrepreneur_categorie",
            joinColumns = @JoinColumn(name = "entrepreneur_id"),
            inverseJoinColumns = @JoinColumn(name = "categorie_id")
    )
    private Set<Categorie> categories = new LinkedHashSet<>();

    @ManyToMany
    private Set<Prestataire> prestataires = new LinkedHashSet<>();

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getSiret() {
        return siret;
    }

    public void setSiret(String siret) {
        this.siret = siret;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getVille() {
        return ville;
    }

    public void setVille(String ville) {
        this.ville = ville;
    }

    public String getCodePostal() {
        return codePostal;
    }

    public void setCodePostal(String codePostal) {
        this.codePostal = codePostal;
    }

    public Set<DevisEntrepreneur> getDevisEntrepreneurs() {
        return devisEntrepreneurs;
    }

    public void setDevisEntrepreneurs(Set<DevisEntrepreneur> devisEntrepreneurs) {
        this.devisEntrepreneurs = devisEntrepreneurs;
    }

    public Set<DevisType> getDevisTypes() {
        return devisTypes;
    }

    public void setDevisTypes(Set<DevisType> devisTypes) {
        this.devisTypes = devisTypes;
    }

    public Set<Categorie> getCategories() {
        return categories;
    }

    public void setCategories(Set<Categorie> categories) {
        this.categories = categories;
    }

    public Set<Prestataire> getPrestataires() {
        return prestataires;
    }

    public void setPrestataires(Set<Prestataire> prestataires) {
        this.prestataires = prestataires;
    }

}