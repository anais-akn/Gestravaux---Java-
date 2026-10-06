package com.immosync.immosyncapp.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "devis_type_prestation")
public class DevisTypePrestation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "quantite", nullable = false)
    private Integer quantite;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "devis_type_id", nullable = false)
    private DevisType devisType;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "prestataire_id", nullable = false)
    private Prestataire prestataire;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getQuantite() {
        return quantite;
    }

    public void setQuantite(Integer quantite) {
        this.quantite = quantite;
    }

    public DevisType getDevisType() {
        return devisType;
    }

    public void setDevisType(DevisType devisType) {
        this.devisType = devisType;
    }

    public Prestataire getPrestataire() {
        return prestataire;
    }

    public void setPrestataire(Prestataire prestataire) {
        this.prestataire = prestataire;
    }

}