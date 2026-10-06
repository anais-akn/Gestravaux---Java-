package com.immosync.immosyncapp.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class EntrepreneurCategorieId implements Serializable {
    private static final long serialVersionUID = -6461081971260162546L;
    @Column(name = "entrepreneur_id", nullable = false)
    private Integer entrepreneurId;

    @Column(name = "categorie_id", nullable = false)
    private Integer categorieId;

    public Integer getEntrepreneurId() {
        return entrepreneurId;
    }

    public void setEntrepreneurId(Integer entrepreneurId) {
        this.entrepreneurId = entrepreneurId;
    }

    public Integer getCategorieId() {
        return categorieId;
    }

    public void setCategorieId(Integer categorieId) {
        this.categorieId = categorieId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EntrepreneurCategorieId entity = (EntrepreneurCategorieId) o;
        return Objects.equals(this.entrepreneurId, entity.entrepreneurId) &&
                Objects.equals(this.categorieId, entity.categorieId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(entrepreneurId, categorieId);
    }
}