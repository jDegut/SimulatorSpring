package fr.polytech.simulatorspring.repository;

import fr.polytech.simulatorspring.domain.Inscription;
import fr.polytech.simulatorspring.domain.InscriptionAction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;
import java.util.List;

public interface InscriptionActionRepository extends JpaRepository<InscriptionAction, Integer> {

    List<InscriptionAction> findAllByFkInscriptionIn(Collection<Inscription> fkInscription);

}