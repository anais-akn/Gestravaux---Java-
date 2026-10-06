package com.immosync.immosyncapp.services;

import com.immosync.immosyncapp.dto.MoisStatsDTO;
import com.immosync.immosyncapp.dto.PrestaStatsDTO;
import com.immosync.immosyncapp.dto.VilleStatsDTO;
import com.immosync.immosyncapp.entities.*;
import com.immosync.immosyncapp.repositories.ChantierRepository;
import com.immosync.immosyncapp.repositories.DevisTypePrestationRepository;
import com.immosync.immosyncapp.repositories.DevisTypeRepository;
import com.immosync.immosyncapp.repositories.EntrepreneurRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class ChantierService {

    private final ChantierRepository chantierRepository;
    private final DevisTypeRepository devisTypeRepository;
    private final DevisTypePrestationRepository dtpRepository;
    private final EntrepreneurRepository entrepreneurRepository;
    private final ApiService apiService;

    public ChantierService(ChantierRepository chantierRepository,
                           DevisTypeRepository devisTypeRepository,
                           DevisTypePrestationRepository dtpRepository, EntrepreneurRepository entrepreneurRepository, ApiService apiService) {
        this.chantierRepository = chantierRepository;
        this.devisTypeRepository = devisTypeRepository;
        this.dtpRepository = dtpRepository;
        this.entrepreneurRepository = entrepreneurRepository;
        this.apiService = apiService;
    }

    public DevisType creerChantierComplet(String description, Bien bien, Inspecteur inspecteur, Map<Prestataire, Integer> lignesPrestations) {

        Chantier chantier = new Chantier();
        chantier.setDescription(description);
        chantier.setBien(bien);
        chantier.setInspecteur(inspecteur);
        chantier.setDateCreation(Instant.now());
        chantier.setStatut("OUVERT");
        chantier = chantierRepository.save(chantier);

        DevisType devis = new DevisType();
        devis.setIntitule("Devis Type - " + bien.getAdresse());
        devis.setDateCreation(Instant.now());
        devis.setChantier(chantier);
        devis = devisTypeRepository.save(devis);

        for (Map.Entry<Prestataire, Integer> entry : lignesPrestations.entrySet()) {
            DevisTypePrestation dtp = new DevisTypePrestation();
            dtp.setDevisType(devis);
            dtp.setPrestataire(entry.getKey());
            dtp.setQuantite(entry.getValue());
            dtpRepository.save(dtp);
        }

        return devis;
    }
    public List<Entrepreneur> getEntrepreneursPotentiels(Bien bien, List<Prestataire> prestations) {
        if (bien == null || prestations == null || prestations.isEmpty()) return List.of();

        String villeChantier = bien.getVille();

        Set<Integer> categoriesRequisesIds = prestations.stream()
                .map(p -> p.getCategorie().getId())
                .collect(Collectors.toSet());

        List<Entrepreneur> tousLesEntrepreneurs = entrepreneurRepository.findAllWithCategoriesAndDevis();

        return tousLesEntrepreneurs.stream()
                .filter(e -> {
                    Set<Integer> expertiseIds = e.getCategories().stream()
                            .map(Categorie::getId)
                            .collect(Collectors.toSet());
                    return expertiseIds.containsAll(categoriesRequisesIds);
                })
                .filter(e -> {
                    double distance = apiService.getDistanceKm(villeChantier, e.getVille());
                    return distance >= 0 && distance <= 30.0;
                })
                .toList();
    }
    public List<Entrepreneur> getEntrepreneursEligibles(Integer devisTypeId) {
        DevisType devis = devisTypeRepository.findByIdWithPrestations(devisTypeId)
                .orElseThrow(() -> new RuntimeException("Devis Type introuvable"));

        String villeChantier = devis.getChantier().getBien().getVille();
        Set<Integer> categoriesRequisesIds = devis.getDevisTypePrestations().stream()
                .map(dtp -> dtp.getPrestataire().getCategorie().getId())
                .collect(Collectors.toSet());

        List<Entrepreneur> tousLesEntrepreneurs = entrepreneurRepository.findAllWithCategoriesAndDevis();

        return tousLesEntrepreneurs.stream()
                .filter(e -> {
                    Set<Integer> expertiseIds = e.getCategories().stream()
                            .map(Categorie::getId)
                            .collect(Collectors.toSet());
                    return expertiseIds.containsAll(categoriesRequisesIds);
                })
                .filter(e -> {
                    double distance = apiService.getDistanceKm(villeChantier, e.getVille());
                    return distance >= 0 && distance <= 30.0;
                })
                .toList();
    }

    public void lierEntrepreneursAuDevis(Integer devisId, List<Entrepreneur> selectionnes) {
        DevisType devis = devisTypeRepository.findById(devisId)
                .orElseThrow(() -> new RuntimeException("Devis introuvable"));
        for (Entrepreneur ent : selectionnes) {
            Entrepreneur managedEnt = entrepreneurRepository.findByIdWithDevis(ent.getId())
                    .orElseThrow(() -> new RuntimeException("Entrepreneur introuvable"));
            managedEnt.getDevisTypes().add(devis);
            entrepreneurRepository.save(managedEnt);
        }
    }
    public List<VilleStatsDTO> getStatsVilles() {
        return chantierRepository.getChantierStatsByVille();
    }
    public List<MoisStatsDTO> getStatsMensuelles() {
        return chantierRepository.getChantierStatsByMois();
    }
    public List<PrestaStatsDTO> getStatsRevenus() {
        return chantierRepository.getRevenusParCategorie();
    }
}
