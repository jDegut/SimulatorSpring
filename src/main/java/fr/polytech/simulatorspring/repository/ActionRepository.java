package fr.polytech.simulatorspring.repository;

import fr.polytech.simulatorspring.domain.Action;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface ActionRepository extends JpaRepository<Action, Integer> {
	Action findByFkAction(Action action);
}