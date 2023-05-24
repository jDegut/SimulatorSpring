package fr.polytech.simulatorspring.repository;

import fr.polytech.simulatorspring.domain.Inscription;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;

import java.util.List;

public interface InscriptionRepository extends JpaRepository<Inscription, Integer> {

    List<Inscription> findAllByFkUser_Id(int idUser);

}