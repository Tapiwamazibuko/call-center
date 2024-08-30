package com.xib.assessment.dto;

import lombok.Data;

@Data
public class AgentDTO {
    String firstName;
    String lastName;
    String idNumber;
    Long teamId;
    Long managerId;
}
