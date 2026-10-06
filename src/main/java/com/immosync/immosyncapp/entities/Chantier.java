package com.immosync.immosyncapp.entities;

import jakarta.persistence.*;

import java.time.Instant;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "chantier")
public class Chantier {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "date_creation", nullable = false)
    private Instant dateCreation;

    @Column(name = "date_validation")
    private Instant dateValidation;

    @Column(name = "statut", nullable = false, length = 50)
    private String statut;

    @Lob
    @Column(name = "description", nullable = false)
    private String description;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "bien_id", nullable = false)
    private Bien bien;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "inspecteur_id", nullable = false)
    private Inspecteur inspecteur;

    @OneToMany(mappedBy = "chantier")
    private Set<DevisType> devisTypes = new LinkedHashSet<>();

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Instant getDateCreation() {
        return dateCreation;
    }

    public void setDateCreation(Instant dateCreation) {
        this.dateCreation = dateCreation;
    }

    public Instant getDateValidation() {
        return dateValidation;
    }

    public void setDateValidation(Instant dateValidation) {
        this.dateValidation = dateValidation;
    }

    public String getStatut() {
        return statut;
    }

    public void setStatut(String statut) {
        this.statut = statut;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Bien getBien() {
        return bien;
    }

    public void setBien(Bien bien) {
        this.bien = bien;
    }

    public Inspecteur getInspecteur() {
        return inspecteur;
    }

    public void setInspecteur(Inspecteur inspecteur) {
        this.inspecteur = inspecteur;
    }

    public Set<DevisType> getDevisTypes() {
        return devisTypes;
    }

    public void setDevisTypes(Set<DevisType> devisTypes) {
        this.devisTypes = devisTypes;
    }

}