package com.immosync.immosyncapp.services;

import com.immosync.immosyncapp.entities.Categorie;
import com.immosync.immosyncapp.repositories.CategorieRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategorieService {
    private final CategorieRepository categorieRepository;

    public CategorieService( CategorieRepository categorieRepository) {
        this.categorieRepository = categorieRepository;
    }

    public List<Categorie> getAllCategories() {
        return categorieRepository.findAll();
    }

}
