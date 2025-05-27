package com.groupeisi.MicroServicieUtilisateur.service;

import com.groupeisi.MicroServicieUtilisateur.entity.Utilisateurs;
import com.groupeisi.MicroServicieUtilisateur.repository.UtilisateurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.*;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class UtilisateurDetailService implements UserDetailsService {

    @Autowired
    private UtilisateurRepository utilisateurRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Utilisateurs user = utilisateurRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("Utilisateur non trouvé"));
        return new org.springframework.security.core.userdetails.User(
                user.getEmail(), user.getMotDePasse(), new ArrayList<>()
        );
    }
}
