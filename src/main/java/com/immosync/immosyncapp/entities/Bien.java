package com.immosync.immosyncapp.entities;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "bien")
public class Bien {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "adresse", nullable = false)
    private String adresse;

    @Column(name = "ville", nullable = false, length = 100)
    private String ville;

    @Column(name = "code_postal", nullable = false, length = 20)
    private String codePostal;

    @Column(name = "surface", nullable = false, precision = 10, scale = 2)
    private BigDecimal surface;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "utilisateur_id", nullable = false)
    private Utilisateur utilisateur;

    @OneToMany(mappedBy = "bien")
    private Set<Chantier> chantiers = new LinkedHashSet<>();

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public BigDecimal getSurface() {
        return surface;
    }

    public void setSurface(BigDecimal surface) {
        this.surface = surface;
    }

    public Utilisateur getUtilisateur() {
        return utilisateur;
    }

    public void setUtilisateur(Utilisateur utilisateur) {
        this.utilisateur = utilisateur;
    }

    public Set<Chantier> getChantiers() {
        return chantiers;
    }

    public void setChantiers(Set<Chantier> chantiers) {
        this.chantiers = chantiers;
    }

}