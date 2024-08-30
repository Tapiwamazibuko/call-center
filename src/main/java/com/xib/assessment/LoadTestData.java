package com.xib.assessment;

import com.xib.assessment.agent.Agent;
import com.xib.assessment.agent.AgentRepository;
import com.xib.assessment.manager.Manager;
import com.xib.assessment.manager.ManagerRepository;
import com.xib.assessment.team.Team;
import com.xib.assessment.team.TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Component
public class LoadTestData {
    @Autowired
    AgentRepository agentRepository;

    @Autowired
    TeamRepository teamRepository;

    @Autowired
    ManagerRepository managerRepository;

    @PostConstruct
    @Transactional
    public void execute() {
        List<Manager> managerList = new ArrayList<>();
        managerList.add(createManager("Disney", "Studios"));
        managerList.add(createManager("Warner", "Bros"));

        Team team1 = createTeam("Marvel", managerList);
        Team team2 = createTeam("DC", managerList);

        createAgent("Bruce", "Banner", "1011125190081", team1);
        createAgent("Tony", "Stark", "6912115191083", team1);
        createAgent("Peter", "Parker", "7801115190084", team1);
        createAgent("Bruce", "Wayne", "6511185190085", team2);
        createAgent("Clark", "Kent", "5905115190086",team2);
    }

    private Team createTeam(String name, List<Manager> managers) {
        Team t = new Team();
        t.setName(name);
        t.setManagers(managers);
        return teamRepository.save(t);
    }

    private Agent createAgent(String firstName, String lastName, String idNumber, Team team) {
        Agent a = new Agent();
        a.setFirstName(firstName);
        a.setLastName(lastName);
        a.setIdNumber(idNumber);
        a.setTeam(team);
        return agentRepository.save(a);
    }

    private Manager createManager(String firstName, String lastName) {
        Manager m = Manager.builder()
                .firstName(firstName)
                .lastName(lastName)
                .build();
        return managerRepository.save(m);
    }
}
