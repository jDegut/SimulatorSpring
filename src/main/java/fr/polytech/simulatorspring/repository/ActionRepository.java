package fr.polytech.simulatorspring.repository;

import fr.polytech.simulatorspring.domain.Action;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ActionRepository extends JpaRepository<Action, Integer> {

    Action findByFkAction(Action action);

}