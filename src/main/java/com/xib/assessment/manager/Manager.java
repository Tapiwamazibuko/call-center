package com.xib.assessment.manager;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.xib.assessment.agent.Agent;
import com.xib.assessment.team.Team;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Manager {

    @Id
    @GeneratedValue
    private Long id;

    private String firstName;

    private String lastName;

    @OneToMany(
            fetch = FetchType.LAZY,
            mappedBy = "manager",
            cascade = CascadeType.ALL
    )
    @JsonManagedReference
    private List<Agent> agents;

    @ManyToMany(mappedBy = "managers")
    @ToString.Exclude
    private List<Team> teams;
}
