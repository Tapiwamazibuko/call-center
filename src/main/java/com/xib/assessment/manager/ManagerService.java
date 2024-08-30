package com.xib.assessment.manager;

import com.xib.assessment.agent.Agent;
import com.xib.assessment.agent.AgentRepository;
import com.xib.assessment.dto.ManagerDTO;
import com.xib.assessment.dto.ManagerResponseDTO;
import com.xib.assessment.team.Team;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.persistence.NamedStoredProcedureQuery;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class ManagerService {
    private final ManagerRepository managerRepository;
    private final ManagerMapper managerMapper;
    private final AgentRepository agentRepository;

    public ManagerResponseDTO saveManager(ManagerDTO managerDTO) {
        Manager manager =  managerRepository.save(managerMapper.mapToManager(managerDTO));

        for(Long id: managerDTO.getAgentIDs()) {
            try {
                Agent agent = agentRepository.findById(id).get();
                agent.setManager(manager);
                agentRepository.save(agent);
            } catch(Exception e) {
                log.error("Could not find Agent with id: {}", id);
            }
        }

        return managerMapper.mapToResponse(manager);
    }

    public List<Team> findTeams(Long managerId) {
        return  managerRepository.findById(managerId)
                .get().getTeams();
    }

    public List<Team> getManagerTeams(Long managerId) {
        Manager manager = managerRepository.findById(managerId).get();

        return manager.getTeams();
    }
}
