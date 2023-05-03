package fr.polytech.simulatorspring.repository;

import fr.polytech.simulatorspring.domain.Inscription;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InscriptionRepository extends JpaRepository<Inscription, Integer> {
}