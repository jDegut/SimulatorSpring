package fr.polytech.simulatorspring.domain;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
@Table(name = "action")
public class Action {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "wording")
    private String wording;
    @Column(name = "scoreMinimum")
    private int scoreMinimum;

    @OneToOne
    @JoinColumn(name = "fk_action")
    private Action previousAction;

    @ManyToOne
    @JoinTable(
            name = "action__mission",
            joinColumns = @JoinColumn(name = "fk_action"))
    private Mission mission;

    @ManyToMany
    @JoinTable(
            name = "inscription__action",
            joinColumns = @JoinColumn(name = "fk_action"))
    private List<Inscription> inscriptions;

    @OneToMany
    @JoinColumn(name = "fk_action")
    private List<Indicator> indicators;

}
