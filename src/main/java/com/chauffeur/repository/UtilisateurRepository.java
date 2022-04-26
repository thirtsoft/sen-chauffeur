package com.chauffeur.repository;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import com.chauffeur.models.Annonce;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.chauffeur.models.Utilisateur;

@Repository
public interface UtilisateurRepository extends JpaRepository<Utilisateur, Long> {
	
	Optional<Utilisateur> findByUsername(String username);

    Optional<Utilisateur> findByEmail(String email);

    Boolean existsByUsername(String username);

    Boolean existsByEmail(String email);

    @Query("select count(p) from Utilisateur p")
    BigDecimal countNumberOfRecruteurs();
    
    List<Utilisateur> findByOrderByIdDesc();

    @Query("select u from Utilisateur u where month(u.dateInscription) = month(current_date) order by id Desc")
    List<Utilisateur> findUtilisateursByOrderByIdDesc();
    

}
