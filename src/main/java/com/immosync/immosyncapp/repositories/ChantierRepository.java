package com.immosync.immosyncapp.repositories;

import com.immosync.immosyncapp.dto.MoisStatsDTO;
import com.immosync.immosyncapp.dto.PrestaStatsDTO;
import com.immosync.immosyncapp.dto.VilleStatsDTO;
import com.immosync.immosyncapp.entities.Chantier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ChantierRepository extends JpaRepository<Chantier, Integer> {
    @Query("SELECT new com.immosync.immosyncapp.dto.VilleStatsDTO(c.bien.ville, COUNT(c.id)) " +
            "FROM Chantier c " +
            "GROUP BY c.bien.ville")
    List<VilleStatsDTO> getChantierStatsByVille();

    @Query("SELECT new com.immosync.immosyncapp.dto.MoisStatsDTO(" +
            "SUBSTR(CAST(c.dateCreation AS string), 1, 7), COUNT(c.id)) " +
            "FROM Chantier c " +
            "GROUP BY SUBSTR(CAST(c.dateCreation AS string), 1, 7) " +
            "ORDER BY SUBSTR(CAST(c.dateCreation AS string), 1, 7) ASC")
    List<MoisStatsDTO> getChantierStatsByMois();

    @Query("SELECT new com.immosync.immosyncapp.dto.PrestaStatsDTO(" +
            "dtp.prestataire.categorie.libelle, SUM(dtp.quantite * dtp.prestataire.prixBase)) " +
            "FROM DevisTypePrestation dtp " +
            "GROUP BY dtp.prestataire.categorie.libelle")
    List<PrestaStatsDTO> getRevenusParCategorie();
}
