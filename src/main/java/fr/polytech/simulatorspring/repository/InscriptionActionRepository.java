package fr.polytech.simulatorspring.repository;

import fr.polytech.simulatorspring.domain.InscriptionAction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InscriptionActionRepository extends JpaRepository<InscriptionAction, Integer> {
}