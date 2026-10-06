package com.immosync.immosyncapp.repositories;

import com.immosync.immosyncapp.entities.Categorie;
import com.immosync.immosyncapp.entities.Prestataire;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PrestataireRepository extends JpaRepository<Prestataire, Integer> {
    @Override
    List<Prestataire> findAll();

    List<Prestataire> findByCategorie(Categorie categorie);
}
