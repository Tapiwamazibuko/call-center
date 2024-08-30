package com.xib.assessment.dto;

import lombok.Data;

import java.util.List;

@Data
public class ManagerDTO  {
    String firstName;
    String lastName;
    List<Long> agentIDs;
}
