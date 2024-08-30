package com.xib.assessment.team;

import com.xib.assessment.agent.Agent;
import com.xib.assessment.agent.AgentRepository;
import com.xib.assessment.dto.AgentDTO;
import com.xib.assessment.dto.TeamDTO;
import com.xib.assessment.dto.TeamResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TeamService {
    private final TeamRepository teamRepository;
    private final TeamMapper teamMapper;
    private final AgentRepository agentRepository;

    public List<TeamResponseDTO> findAllTeams() {
        return teamRepository.findAll()
                .stream()
                .map(teamMapper::mapToResponse)
                .collect(Collectors.toList());
    }

    public TeamResponseDTO findTeam(Long id) {
        return teamRepository.findById(id)
                .map(teamMapper::mapToResponse)
                .orElseThrow(NoSuchElementException::new);
    }

    public TeamResponseDTO saveTeam(TeamDTO teamDTO) {
        Team team = teamRepository.save(teamMapper.mapToTeam(teamDTO));

        return teamMapper.mapToResponse(team);
    }

    public TeamResponseDTO assignAgent(AgentDTO agentDTO, Long id) {
        Agent agent = agentRepository.findByIdNumber(agentDTO.getIdNumber());
        Team team = teamRepository.findById(id).get();

        if(team.getManagers().contains(agent.getManager())) {
            agent.setTeam(team);
            List<Agent> teamAgents = team.getAgents();
            teamAgents.add(agent);
            team.setAgents(teamAgents);
            agentRepository.save(agent);
            teamRepository.save(team);
        }

        return teamMapper.mapToResponse(team);
    }

    public List<TeamResponseDTO> findEmptyTeams() {

        return teamRepository.findByAgentsNullAndManagersNull()
                .stream()
                .map(teamMapper::mapToResponse)
                .collect(Collectors.toList());
    }
}
