package fr.polytech.simulatorspring.repository;

import fr.polytech.simulatorspring.domain.InscriptionIndicator;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;
import java.util.List;

public interface InscriptionIndicatorRepository extends JpaRepository<InscriptionIndicator, Integer> {
	List<InscriptionIndicator> findAllByFkInscriptionIn(Collection<Integer> fkInscription);
	List<InscriptionIndicator> findAllByFkInscriptionAndFkAction(int inscription, int action);
	InscriptionIndicator findByFkInscriptionAndFkActionAndFkIndicator(int inscription, int action, int indicator);

}