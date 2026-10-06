package com.immosync.immosyncapp.services;

import com.immosync.immosyncapp.entities.Utilisateur;
import com.immosync.immosyncapp.repositories.UtilisateurRepository;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UtilisateurService {

    private final UtilisateurRepository userRepository;

    public UtilisateurService(UtilisateurRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Utilisateur existeUser(String loginUser, String pwdUser) {
        return userRepository.findByEmailAndPassword(loginUser, pwdUser);
    }

    public List<Utilisateur> getAllUsers() {
        return userRepository.findAll();
    }

    public Map<String, Object> getDashboardUserStats() {
        long total = userRepository.countAllUsers()-1;
        long nbrProprio = userRepository.countProprios().count();
        long nbrEntr = userRepository.countEntrepreneurs().count();
        long nbrInspec = userRepository.countInspecteurs().count();

        Map<String, Object> stats = new HashMap<>();
        stats.put("total", total);
        stats.put("pctProprio", total > 0 ? (nbrProprio * 100.0 / total) : 0);
        stats.put("pctEntr", total > 0 ? (nbrEntr * 100.0 / total) : 0);
        stats.put("pctInspec", total > 0 ? (nbrInspec * 100.0 / total) : 0);

        return stats;
    }

}
