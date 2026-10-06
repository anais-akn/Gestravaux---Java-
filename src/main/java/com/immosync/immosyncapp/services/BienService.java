package com.immosync.immosyncapp.services;

import com.immosync.immosyncapp.entities.Bien;
import com.immosync.immosyncapp.entities.Utilisateur;
import com.immosync.immosyncapp.repositories.BienRepository;
import com.immosync.immosyncapp.repositories.UtilisateurRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class BienService {

    private final BienRepository bienRepository;
    private final UtilisateurRepository utilisateurRepository;

    public BienService(BienRepository bienRepository, UtilisateurRepository utilisateurRepository) {
        this.bienRepository = bienRepository;
        this.utilisateurRepository = utilisateurRepository;
    }

    public List<Bien> getAllBiens() {
        return bienRepository.findAll();
    }


    public Bien creerBien(String adresse, String ville, String cp, BigDecimal surface, Utilisateur proprietaire) {
        Bien nouveauBien = new Bien();
        nouveauBien.setAdresse(adresse);
        nouveauBien.setVille(ville);
        nouveauBien.setCodePostal(cp);
        nouveauBien.setSurface(surface);
        nouveauBien.setUtilisateur(proprietaire);

        return bienRepository.save(nouveauBien);
    }
    public Bien modifierBien(Integer id, String adresse, String ville, String cp, BigDecimal surface,Integer idProprietaire) {
        Bien bienExistant = bienRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Bien introuvable avec l'ID : " + id));

        bienExistant.setAdresse(adresse);
        bienExistant.setVille(ville);
        bienExistant.setCodePostal(cp);
        bienExistant.setSurface(surface);
        Utilisateur nouveauProprio = utilisateurRepository.findById(idProprietaire)
                .orElseThrow(() -> new RuntimeException("Utilisateur non trouvé"));
        bienExistant.setUtilisateur(nouveauProprio);

        return bienRepository.save(bienExistant);
    }
    public List<Bien> getAllBiensAvecProprios() {
        return bienRepository.findAllWithUtilisateurs();
    }
}
