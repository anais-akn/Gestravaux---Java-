package com.immosync.immosyncapp.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class DevisTypeEntrepreneurId implements Serializable {
    private static final long serialVersionUID = 7536607789424385244L;
    @Column(name = "devis_type_id", nullable = false)
    private Integer devisTypeId;

    @Column(name = "entrepreneur_id", nullable = false)
    private Integer entrepreneurId;

    public Integer getDevisTypeId() {
        return devisTypeId;
    }

    public void setDevisTypeId(Integer devisTypeId) {
        this.devisTypeId = devisTypeId;
    }

    public Integer getEntrepreneurId() {
        return entrepreneurId;
    }

    public void setEntrepreneurId(Integer entrepreneurId) {
        this.entrepreneurId = entrepreneurId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DevisTypeEntrepreneurId entity = (DevisTypeEntrepreneurId) o;
        return Objects.equals(this.devisTypeId, entity.devisTypeId) &&
                Objects.equals(this.entrepreneurId, entity.entrepreneurId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(devisTypeId, entrepreneurId);
    }
}