package com.immosync.immosyncapp.entities;

import jakarta.persistence.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@Table(name = "prestataire_entrepreneur")
public class PrestataireEntrepreneur {
    @EmbeddedId
    private PrestataireEntrepreneurId id;

    @MapsId("prestataireId")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "prestataire_id", nullable = false)
    private Prestataire prestataire;

    @MapsId("entrepreneurId")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "entrepreneur_id", nullable = false)
    private Entrepreneur entrepreneur;

    public PrestataireEntrepreneurId getId() {
        return id;
    }

    public void setId(PrestataireEntrepreneurId id) {
        this.id = id;
    }

    public Prestataire getPrestataire() {
        return prestataire;
    }

    public void setPrestataire(Prestataire prestataire) {
        this.prestataire = prestataire;
    }

    public Entrepreneur getEntrepreneur() {
        return entrepreneur;
    }

    public void setEntrepreneur(Entrepreneur entrepreneur) {
        this.entrepreneur = entrepreneur;
    }

}