package fr.polytech.simulatorspring.repository;

import fr.polytech.simulatorspring.domain.Mission;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MissionRepository extends JpaRepository<Mission, Integer> {
}