package com.immosync.immosyncapp.entities;

import jakarta.persistence.*;

import java.time.Instant;

@Entity
@Table(name = "document")
public class Document {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "type_document", nullable = false, length = 100)
    private String typeDocument;

    @Column(name = "chemin_fichier", nullable = false, length = 500)
    private String cheminFichier;

    @Column(name = "date_upload", nullable = false)
    private Instant dateUpload;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "chantier_id")
    private Chantier chantier;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTypeDocument() {
        return typeDocument;
    }

    public void setTypeDocument(String typeDocument) {
        this.typeDocument = typeDocument;
    }

    public String getCheminFichier() {
        return cheminFichier;
    }

    public void setCheminFichier(String cheminFichier) {
        this.cheminFichier = cheminFichier;
    }

    public Instant getDateUpload() {
        return dateUpload;
    }

    public void setDateUpload(Instant dateUpload) {
        this.dateUpload = dateUpload;
    }

    public Chantier getChantier() {
        return chantier;
    }

    public void setChantier(Chantier chantier) {
        this.chantier = chantier;
    }

}