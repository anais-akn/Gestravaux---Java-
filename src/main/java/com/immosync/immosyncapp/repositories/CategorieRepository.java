package com.immosync.immosyncapp.repositories;

import com.immosync.immosyncapp.entities.Categorie;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CategorieRepository extends JpaRepository<Categorie,Integer> {
    @Override
    List<Categorie> findAll();
}
