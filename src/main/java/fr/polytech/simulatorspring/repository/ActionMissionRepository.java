package fr.polytech.simulatorspring.repository;

import fr.polytech.simulatorspring.domain.Action;
import fr.polytech.simulatorspring.domain.ActionMission;
import fr.polytech.simulatorspring.domain.ActionMissionId;
import fr.polytech.simulatorspring.domain.Mission;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;
import java.util.List;

public interface ActionMissionRepository extends JpaRepository<ActionMission, ActionMissionId> {

	List<ActionMission> findAllByFkMission(Mission fkMission);

	List<ActionMission> findAllByFkMissionAndFkActionIn(Mission fkMission, Collection<Action> fkAction);

}