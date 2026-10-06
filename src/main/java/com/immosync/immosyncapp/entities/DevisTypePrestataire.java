package com.immosync.immosyncapp.entities;

import jakarta.persistence.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@Table(name = "devis_type_prestataire")
public class DevisTypePrestataire {
    @EmbeddedId
    private DevisTypePrestataireId id;

    @MapsId("devisTypeId")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "devis_type_id", nullable = false)
    private DevisType devisType;

    @MapsId("prestataireId")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "prestataire_id", nullable = false)
    private Prestataire prestataire;

    public DevisTypePrestataireId getId() {
        return id;
    }

    public void setId(DevisTypePrestataireId id) {
        this.id = id;
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