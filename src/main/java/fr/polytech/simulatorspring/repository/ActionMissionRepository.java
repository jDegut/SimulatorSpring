package fr.polytech.simulatorspring.repository;

import fr.polytech.simulatorspring.domain.ActionMission;
import fr.polytech.simulatorspring.domain.ActionMissionId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ActionMissionRepository extends JpaRepository<ActionMission, ActionMissionId> {
}