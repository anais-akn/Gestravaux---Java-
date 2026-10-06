package com.immosync.immosyncapp.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class DevisTypePrestataireId implements Serializable {
    private static final long serialVersionUID = -6647287810976015177L;
    @Column(name = "devis_type_id", nullable = false)
    private Integer devisTypeId;

    @Column(name = "prestataire_id", nullable = false)
    private Integer prestataireId;

    public Integer getDevisTypeId() {
        return devisTypeId;
    }

    public void setDevisTypeId(Integer devisTypeId) {
        this.devisTypeId = devisTypeId;
    }

    public Integer getPrestataireId() {
        return prestataireId;
    }

    public void setPrestataireId(Integer prestataireId) {
        this.prestataireId = prestataireId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DevisTypePrestataireId entity = (DevisTypePrestataireId) o;
        return Objects.equals(this.devisTypeId, entity.devisTypeId) &&
                Objects.equals(this.prestataireId, entity.prestataireId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(devisTypeId, prestataireId);
    }
}