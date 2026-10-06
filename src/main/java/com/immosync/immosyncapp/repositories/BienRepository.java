package com.immosync.immosyncapp.repositories;

import com.immosync.immosyncapp.entities.Bien;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BienRepository extends JpaRepository<Bien, Integer> {

    @Override
    List<Bien> findAll();

    @Query("SELECT b FROM Bien b JOIN FETCH b.utilisateur")
    List<Bien> findAllWithUtilisateurs();

}
