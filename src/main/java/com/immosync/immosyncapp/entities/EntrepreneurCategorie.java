package com.immosync.immosyncapp.entities;

import jakarta.persistence.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@Table(name = "entrepreneur_categorie")
public class EntrepreneurCategorie {
    @EmbeddedId
    private EntrepreneurCategorieId id;

    @MapsId("entrepreneurId")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "entrepreneur_id", nullable = false)
    private Entrepreneur entrepreneur;

    @MapsId("categorieId")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "categorie_id", nullable = false)
    private Categorie categorie;

    public EntrepreneurCategorieId getId() {
        return id;
    }

    public void setId(EntrepreneurCategorieId id) {
        this.id = id;
    }

    public Entrepreneur getEntrepreneur() {
        return entrepreneur;
    }

    public void setEntrepreneur(Entrepreneur entrepreneur) {
        this.entrepreneur = entrepreneur;
    }

    public Categorie getCategorie() {
        return categorie;
    }

    public void setCategorie(Categorie categorie) {
        this.categorie = categorie;
    }

}