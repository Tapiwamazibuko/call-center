package com.xib.assessment.team;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.xib.assessment.agent.Agent;
import com.xib.assessment.manager.Manager;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Team {
    @Id
    @GeneratedValue
    private Long id;
    private String name;

    @OneToMany(
            fetch = FetchType.LAZY,
            mappedBy = "team"
    )
    @JsonManagedReference
    private List<Agent> agents;

    @ManyToMany
    @JoinTable(
            name="managers_teams",
            joinColumns = {
                    @JoinColumn(name = "team_id")
            },
            inverseJoinColumns = {
                    @JoinColumn(name = "manager_id")
            }
    )
    @ToString.Exclude
    private List<Manager> managers;
}
