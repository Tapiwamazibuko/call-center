package com.xib.assessment.team;

import com.xib.assessment.dto.TeamDTO;
import com.xib.assessment.dto.TeamResponseDTO;
import com.xib.assessment.manager.Manager;
import com.xib.assessment.manager.ManagerRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Slf4j
@Service
@RequiredArgsConstructor
public class TeamMapper {
    private final ManagerRepository managerRepository;
    public TeamResponseDTO mapToResponse(Team team) {
        try {
            return TeamResponseDTO.builder()
                    .name(team.getName())
                    .build();

        } catch(Exception e) {
            log.error("");
        }
        return null;
    }

    public Team mapToTeam(TeamDTO teamDTO) {
        List<Manager> managers = new ArrayList<>();

        if(teamDTO.getManagerIDs() != null) {
            addManagers(managers, teamDTO.getManagerIDs());
        }

        return Team.builder()
                .managers(managers)
                .name(teamDTO.getName())
                .build();
    }

    private void addManagers(List<Manager> managers, List<Long> ids) {
        for (Long id : ids) {
            if (managers.size() < 2) {
                managers.add(managerRepository.findById(id).get());
            } else {
                break;
            }
        }
    }
}
