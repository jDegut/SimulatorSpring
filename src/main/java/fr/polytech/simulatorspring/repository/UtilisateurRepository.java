package fr.polytech.simulatorspring.repository;

import fr.polytech.simulatorspring.domain.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UtilisateurRepository extends JpaRepository<Utilisateur, Integer> {

	Optional<Utilisateur> findByNomUtil(String nomUtil);

	boolean existsByNomUtil(String nomUtil);

	boolean existsByEmail(String email);

}