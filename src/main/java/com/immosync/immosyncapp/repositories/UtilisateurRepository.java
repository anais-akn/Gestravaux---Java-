package com.immosync.immosyncapp.repositories;

import com.immosync.immosyncapp.dto.UserStatDTO;
import com.immosync.immosyncapp.entities.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface UtilisateurRepository extends JpaRepository<Utilisateur, Integer> {

    Utilisateur findByEmailAndPassword(String loginUser, String pwdUser);

    @Override
    List<Utilisateur> findAll();

    @Override
    Optional<Utilisateur> findById(Integer id);


    @Query("SELECT COUNT(u) FROM Utilisateur u")
    Long countAllUsers();

    @Query("SELECT new com.immosync.immosyncapp.dto.UserStatDTO('PROPRIO', COUNT(u)) FROM Utilisateur u WHERE u.roles LIKE '%ROLE_USER%'")
    UserStatDTO countProprios();

    @Query("SELECT new com.immosync.immosyncapp.dto.UserStatDTO('ENTREPRENEUR', COUNT(u)) FROM Utilisateur u WHERE u.roles LIKE '%ROLE_ENTREPRENEUR%'")
    UserStatDTO countEntrepreneurs();

    @Query("SELECT new com.immosync.immosyncapp.dto.UserStatDTO('INSPECTEUR', COUNT(u)) FROM Utilisateur u WHERE u.roles LIKE '%ROLE_INSPECTEUR%'")
    UserStatDTO countInspecteurs();

}
