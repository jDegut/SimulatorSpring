package fr.polytech.simulatorspring.repository;

import fr.polytech.simulatorspring.domain.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UtilisateurRepository extends JpaRepository<Utilisateur, Integer> {
}