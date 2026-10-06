package com.immosync.immosyncapp.repositories;


import com.immosync.immosyncapp.entities.Inspecteur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InspecteurRepository extends JpaRepository<Inspecteur, Integer> {
    @Override
    List<Inspecteur> findAll();
}