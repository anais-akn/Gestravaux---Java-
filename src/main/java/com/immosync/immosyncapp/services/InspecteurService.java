package com.immosync.immosyncapp.services;

import com.immosync.immosyncapp.entities.Inspecteur;

import com.immosync.immosyncapp.repositories.InspecteurRepository;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InspecteurService {
    private final InspecteurRepository inspecteurRepository;

    public InspecteurService(InspecteurRepository inspecteurRepository) {
        this.inspecteurRepository = inspecteurRepository;
    }

    public List<Inspecteur> getAllInspecteurs() {
        return inspecteurRepository.findAll();
    }

}
