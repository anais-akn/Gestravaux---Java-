package com.immosync.immosyncapp.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class PrestataireEntrepreneurId implements Serializable {
    private static final long serialVersionUID = 3363516451197056367L;
    @Column(name = "prestataire_id", nullable = false)
    private Integer prestataireId;

    @Column(name = "entrepreneur_id", nullable = false)
    private Integer entrepreneurId;

    public Integer getPrestataireId() {
        return prestataireId;
    }

    public void setPrestataireId(Integer prestataireId) {
        this.prestataireId = prestataireId;
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
        PrestataireEntrepreneurId entity = (PrestataireEntrepreneurId) o;
        return Objects.equals(this.prestataireId, entity.prestataireId) &&
                Objects.equals(this.entrepreneurId, entity.entrepreneurId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(prestataireId, entrepreneurId);
    }
}