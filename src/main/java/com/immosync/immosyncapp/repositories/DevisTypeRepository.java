package com.immosync.immosyncapp.repositories;

import com.immosync.immosyncapp.entities.DevisType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DevisTypeRepository extends JpaRepository<DevisType, Integer> {
    @Query("SELECT dt FROM DevisType dt " +
            "JOIN FETCH dt.chantier c " +
            "JOIN FETCH c.bien b " +
            "LEFT JOIN FETCH dt.devisTypePrestations dtp " +
            "LEFT JOIN FETCH dtp.prestataire p " +
            "LEFT JOIN FETCH p.categorie " +
            "WHERE dt.id = :id")
    Optional<DevisType> findByIdWithPrestations(@Param("id") Integer id);
}
