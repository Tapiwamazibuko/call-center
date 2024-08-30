package com.xib.assessment.agent;

import com.xib.assessment.dto.AgentDTO;
import com.xib.assessment.dto.AgentResponseDTO;
import com.xib.assessment.manager.ManagerService;
import com.xib.assessment.team.Team;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AgentService {
    private final AgentRepository agentRepository;
    private final ManagerService managerService;
    private final AgentMapper agentMapper;

    public List<AgentResponseDTO> findAllAgents(Integer page, Integer size) {
        PageRequest pageable = PageRequest.of(page, size);

        return agentRepository.findAll(pageable)
                .stream()
                .map(agentMapper::mapToResponse)
                .collect(Collectors.toList());
    }

    public AgentResponseDTO findAgent(Long id) {
        return agentRepository.findById(id)
                .map(agentMapper::mapToResponse)
                .orElseThrow(NoSuchElementException::new);
    }

    public AgentResponseDTO saveAgent(AgentDTO agentDTO) {
        List<Team> managerTeams = managerService.getManagerTeams(agentDTO.getManagerId());

        boolean hasTeam = managerTeams.stream()
                .anyMatch(teams -> Objects.equals(teams.getId(), agentDTO.getTeamId()));

        if(hasTeam || agentDTO.getTeamId() == null) {
            Agent agent =  agentRepository.save(agentMapper.mapToAgent(agentDTO));

            return agentMapper.mapToResponse(agent);
        }

        throw new IllegalArgumentException("Invaild team");
    }
}
