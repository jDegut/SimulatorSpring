package fr.polytech.simulatorspring.domain;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "indicator")
public class Indicator {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "wording")
    private String wording;
    @Column(name = "valueIfCheck")
    private int valueIfCheck;
    @Column(name = "valueIfUnCheck")
    private int valueIfUncheck;

    @ManyToOne
    private Action action;
}
