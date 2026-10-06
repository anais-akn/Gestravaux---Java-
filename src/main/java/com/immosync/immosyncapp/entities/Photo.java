package com.immosync.immosyncapp.entities;

import jakarta.persistence.*;

import java.time.Instant;

@Entity
@Table(name = "photo")
public class Photo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "chemin_fichier", nullable = false, length = 500)
    private String cheminFichier;

    @Column(name = "description", nullable = false, length = 500)
    private String description;

    @Column(name = "date_prise", nullable = false)
    private Instant datePrise;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "chantier_id", nullable = false)
    private Chantier chantier;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCheminFichier() {
        return cheminFichier;
    }

    public void setCheminFichier(String cheminFichier) {
        this.cheminFichier = cheminFichier;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Instant getDatePrise() {
        return datePrise;
    }

    public void setDatePrise(Instant datePrise) {
        this.datePrise = datePrise;
    }

    public Chantier getChantier() {
        return chantier;
    }

    public void setChantier(Chantier chantier) {
        this.chantier = chantier;
    }

}