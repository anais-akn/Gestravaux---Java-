package com.immosync.immosyncapp.repositories;

import com.immosync.immosyncapp.entities.DevisTypePrestation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface DevisTypePrestationRepository extends JpaRepository<DevisTypePrestation, Integer> {
}
