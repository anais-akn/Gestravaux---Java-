package com.immosync.immosyncapp.entities;

import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
@Table(name = "devis_entrepreneur_prestataire")
public class DevisEntrepreneurPrestataire {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "prix_unitaire", nullable = false, precision = 10, scale = 2)
    private BigDecimal prixUnitaire;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "devis_entrepeneur_id")
    private DevisEntrepreneur devisEntrepeneur;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "prestation_id")
    private Prestataire prestation;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public BigDecimal getPrixUnitaire() {
        return prixUnitaire;
    }

    public void setPrixUnitaire(BigDecimal prixUnitaire) {
        this.prixUnitaire = prixUnitaire;
    }

    public DevisEntrepreneur getDevisEntrepeneur() {
        return devisEntrepeneur;
    }

    public void setDevisEntrepeneur(DevisEntrepreneur devisEntrepeneur) {
        this.devisEntrepeneur = devisEntrepeneur;
    }

    public Prestataire getPrestation() {
        return prestation;
    }

    public void setPrestation(Prestataire prestation) {
        this.prestation = prestation;
    }

}