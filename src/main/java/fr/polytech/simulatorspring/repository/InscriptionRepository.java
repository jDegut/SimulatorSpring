package fr.polytech.simulatorspring.repository;

import fr.polytech.simulatorspring.domain.Inscription;
import fr.polytech.simulatorspring.domain.Mission;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface InscriptionRepository extends JpaRepository<Inscription, Integer> {
    List<Inscription> findAllByFkUser_Id(int idUser);

    List<Inscription> findAllByFkMission(Mission mission);

    Inscription findByFkUser_IdAndFkMission_Id(int idUser, int idMission);
}

