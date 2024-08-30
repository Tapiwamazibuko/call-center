package com.xib.assessment.agent;

import com.xib.assessment.dto.AgentDTO;
import com.xib.assessment.dto.AgentResponseDTO;
import com.xib.assessment.manager.Manager;
import com.xib.assessment.manager.ManagerMapper;
import com.xib.assessment.manager.ManagerRepository;
import com.xib.assessment.team.Team;
import com.xib.assessment.team.TeamMapper;
import com.xib.assessment.team.TeamRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AgentMapper {
    private final TeamMapper teamMapper;
    private final TeamRepository teamRepository;
    private final ManagerMapper managerMapper;
    private final ManagerRepository managerRepository;

    public AgentResponseDTO mapToResponse(Agent agent) {

        return AgentResponseDTO.builder()
                .firstName(agent.getFirstName())
                .lastName(agent.getLastName())
                .team(teamMapper.mapToResponse(agent.getTeam()))
                .manager(managerMapper.mapToResponse(agent.getManager()))
                .build();
    }

    public Agent mapToAgent(AgentDTO agentDTO) {
        Team team = agentDTO.getTeamId() != null ? teamRepository.findById(agentDTO.getTeamId()).get() : null;
        Manager manager = agentDTO.getManagerId() != null ? managerRepository.findById(agentDTO.getManagerId()).get() : null;

        return Agent.builder()
                .team(team)
                .firstName(agentDTO.getFirstName())
                .lastName(agentDTO.getLastName())
                .idNumber(agentDTO.getIdNumber())
                .manager(manager)
                .build();
    }
}
