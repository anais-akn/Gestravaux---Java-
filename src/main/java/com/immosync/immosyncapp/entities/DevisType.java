package com.immosync.immosyncapp.entities;

import jakarta.persistence.*;

import java.time.Instant;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "devis_type")
public class DevisType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "intitule", nullable = false, length = 200)
    private String intitule;

    @Column(name = "date_creation", nullable = false)
    private Instant dateCreation;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "chantier_id")
    private Chantier chantier;

    @OneToMany(mappedBy = "devisType")
    private Set<DevisTypePrestation> devisTypePrestations = new LinkedHashSet<>();

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getIntitule() {
        return intitule;
    }

    public void setIntitule(String intitule) {
        this.intitule = intitule;
    }

    public Instant getDateCreation() {
        return dateCreation;
    }

    public void setDateCreation(Instant dateCreation) {
        this.dateCreation = dateCreation;
    }

    public Chantier getChantier() {
        return chantier;
    }

    public void setChantier(Chantier chantier) {
        this.chantier = chantier;
    }

    public Set<DevisTypePrestation> getDevisTypePrestations() {
        return devisTypePrestations;
    }

    public void setDevisTypePrestations(Set<DevisTypePrestation> devisTypePrestations) {
        this.devisTypePrestations = devisTypePrestations;
    }

}