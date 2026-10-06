package com.immosync.immosyncapp.entities;

import jakarta.persistence.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@Table(name = "devis_type_entrepreneur")
public class DevisTypeEntrepreneur {
    @EmbeddedId
    private DevisTypeEntrepreneurId id;

    @MapsId("devisTypeId")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "devis_type_id", nullable = false)
    private DevisType devisType;

    @MapsId("entrepreneurId")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "entrepreneur_id", nullable = false)
    private Entrepreneur entrepreneur;

    public DevisTypeEntrepreneurId getId() {
        return id;
    }

    public void setId(DevisTypeEntrepreneurId id) {
        this.id = id;
    }

    public DevisType getDevisType() {
        return devisType;
    }

    public void setDevisType(DevisType devisType) {
        this.devisType = devisType;
    }

    public Entrepreneur getEntrepreneur() {
        return entrepreneur;
    }

    public void setEntrepreneur(Entrepreneur entrepreneur) {
        this.entrepreneur = entrepreneur;
    }

}