package com.xib.assessment.dto;

import lombok.Data;

import java.util.List;

@Data
public class TeamDTO {
    String name;
    List<Long> managerIDs;
}
