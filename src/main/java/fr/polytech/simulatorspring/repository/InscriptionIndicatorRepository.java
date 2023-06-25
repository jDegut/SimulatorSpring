package fr.polytech.simulatorspring.repository;

import fr.polytech.simulatorspring.domain.Action;
import fr.polytech.simulatorspring.domain.Indicator;
import fr.polytech.simulatorspring.domain.Inscription;
import fr.polytech.simulatorspring.domain.InscriptionIndicator;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;
import java.util.List;

public interface InscriptionIndicatorRepository extends JpaRepository<InscriptionIndicator, Integer> {
	List<InscriptionIndicator> findAllByFkInscriptionIn(List<Inscription> fkInscription);
	List<InscriptionIndicator> findAllByFkInscription_IdAndFkAction_Id(int inscription, int action);
	InscriptionIndicator findByFkInscription_IdAndFkAction_IdAndFkIndicator_Id(int inscription, int action, int indicator);
	List<InscriptionIndicator> findAllByFkAction(Action fkAction);
	List<InscriptionIndicator> findAllByFkIndicator_Id(int fkIndicator);
}