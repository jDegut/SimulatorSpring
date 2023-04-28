package fr.polytech.simulatorspring.domain;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
@Table(name = "mission")
public class Mission {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "wording")
    private String wording;

    @OneToMany
    @JoinTable(
            name = "action__mission",
            joinColumns = @JoinColumn(name = "fk_mission"))
    private List<Action> actions;

}
