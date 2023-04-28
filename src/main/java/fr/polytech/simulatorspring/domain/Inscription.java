package fr.polytech.simulatorspring.domain;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
@Entity
@Table(name = "inscription")
public class Inscription {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "date")
    private LocalDate date;

    @ManyToMany
    @JoinTable(
            name = "inscription__action",
            joinColumns = @JoinColumn(name = "fk_inscription"))
    private List<Action> actions;

}
