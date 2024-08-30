package com.xib.assessment.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AgentResponseDTO {
    String firstName;
    String lastName;

    TeamResponseDTO team;

    ManagerResponseDTO manager;
}
