package com.immosync.immosyncapp.repositories;

import com.immosync.immosyncapp.entities.Entrepreneur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface EntrepreneurRepository extends JpaRepository<Entrepreneur, Integer> {

    @Query("SELECT DISTINCT e FROM Entrepreneur e LEFT JOIN FETCH e.categories")
    List<Entrepreneur> findAllWithCategories();
    @Query("SELECT DISTINCT e FROM Entrepreneur e " + "LEFT JOIN FETCH e.categories " + "LEFT JOIN FETCH e.devisTypes")
    List<Entrepreneur> findAllWithCategoriesAndDevis();
    @Query("SELECT e FROM Entrepreneur e LEFT JOIN FETCH e.devisTypes WHERE e.id = :id")
    Optional<Entrepreneur> findByIdWithDevis(@Param("id") Integer id);

}

