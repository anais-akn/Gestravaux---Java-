package com.immosync.immosyncapp.entities;

import jakarta.persistence.*;

import java.time.Instant;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "devis_entrepreneur")
public class DevisEntrepreneur {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "date_debut", nullable = false)
    private Instant dateDebut;

    @Column(name = "duree_estimee_jour", nullable = false)
    private Integer dureeEstimeeJour;

    @Column(name = "statut", nullable = false, length = 50)
    private String statut;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "entrepreneur_id")
    private Entrepreneur entrepreneur;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "chantier_id")
    private Chantier chantier;

    @OneToMany(mappedBy = "devisEntrepeneur")
    private Set<DevisEntrepreneurPrestataire> devisEntrepreneurPrestataires = new LinkedHashSet<>();

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Instant getDateDebut() {
        return dateDebut;
    }

    public void setDateDebut(Instant dateDebut) {
        this.dateDebut = dateDebut;
    }

    public Integer getDureeEstimeeJour() {
        return dureeEstimeeJour;
    }

    public void setDureeEstimeeJour(Integer dureeEstimeeJour) {
        this.dureeEstimeeJour = dureeEstimeeJour;
    }

    public String getStatut() {
        return statut;
    }

    public void setStatut(String statut) {
        this.statut = statut;
    }

    public Entrepreneur getEntrepreneur() {
        return entrepreneur;
    }

    public void setEntrepreneur(Entrepreneur entrepreneur) {
        this.entrepreneur = entrepreneur;
    }

    public Chantier getChantier() {
        return chantier;
    }

    public void setChantier(Chantier chantier) {
        this.chantier = chantier;
    }

    public Set<DevisEntrepreneurPrestataire> getDevisEntrepreneurPrestataires() {
        return devisEntrepreneurPrestataires;
    }

    public void setDevisEntrepreneurPrestataires(Set<DevisEntrepreneurPrestataire> devisEntrepreneurPrestataires) {
        this.devisEntrepreneurPrestataires = devisEntrepreneurPrestataires;
    }

}