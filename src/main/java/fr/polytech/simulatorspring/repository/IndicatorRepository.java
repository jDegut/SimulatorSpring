package fr.polytech.simulatorspring.repository;

import fr.polytech.simulatorspring.domain.Action;
import fr.polytech.simulatorspring.domain.Indicator;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IndicatorRepository extends JpaRepository<Indicator, Integer> {

    List<Indicator> findAllByFkAction(Action action);

    List<Indicator> findAllByFkAction_Id(int idAction);

}