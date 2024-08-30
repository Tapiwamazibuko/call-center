package com.xib.assessment.agent;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.xib.assessment.team.Team;
import com.xib.assessment.manager.Manager;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Agent {
    @Id
    @GeneratedValue
    private Long id;
    private String firstName;
    private String lastName;
    private String idNumber;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(
            name = "team_id"
    )
    @JsonBackReference
    private Team team;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(
            name = "manager_id"
    )
    @JsonBackReference
    private Manager manager;
}
