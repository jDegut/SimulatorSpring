package fr.polytech.simulatorspring.repository;

import fr.polytech.simulatorspring.domain.Indicator;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IndicatorRepository extends JpaRepository<Indicator, Integer> {
}