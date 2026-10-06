package com.immosync.immosyncapp.services;

import com.immosync.immosyncapp.entities.Categorie;
import com.immosync.immosyncapp.entities.Prestataire;
import com.immosync.immosyncapp.repositories.PrestataireRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PrestataireService {

    private final PrestataireRepository prestataireRepository;

    public PrestataireService( PrestataireRepository prestataireRepository) {
        this.prestataireRepository = prestataireRepository;
    }

    public List<Prestataire> getAllPrestataires() {
        return prestataireRepository.findAll();
    }
    public List<Prestataire> getPrestatairesByCategorie(Categorie categorie) {
        return prestataireRepository.findByCategorie(categorie);
    }
}

