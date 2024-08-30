package com.xib.assessment.manager;

import com.xib.assessment.agent.Agent;
import com.xib.assessment.agent.AgentRepository;
import com.xib.assessment.dto.ManagerDTO;
import com.xib.assessment.dto.ManagerResponseDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class ManagerMapper {
    private final AgentRepository agentRepository;

    public ManagerResponseDTO mapToResponse(Manager manger) {
        try {
            return ManagerResponseDTO.builder()
                    .firstName(manger.getFirstName())
                    .lastName(manger.getLastName())
                    .build();
        } catch(Exception e) {
            log.error("");
        }
        return null;
    }

    public Manager mapToManager(ManagerDTO managerDTO) {

        return Manager.builder()
                .firstName(managerDTO.getFirstName())
                .lastName(managerDTO.getLastName())
                .build();
    }
}
