package com.xib.assessment.team;

import com.xib.assessment.dto.AgentDTO;
import com.xib.assessment.dto.ErrorResponse;
import com.xib.assessment.dto.TeamDTO;
import com.xib.assessment.dto.TeamResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequiredArgsConstructor
public class TeamController {
    private final TeamService teamService;

    @GetMapping("/teams")
    public ResponseEntity<List<TeamResponseDTO>> findAllTeams() {
        return new ResponseEntity<>(teamService.findAllTeams(), HttpStatus.OK);
    }

    @GetMapping("/team/{id}")
    public ResponseEntity<?> findTeam(@PathVariable("id") Long id) {
        try {
            return new ResponseEntity<>(teamService.findTeam(id), HttpStatus.OK);
        } catch(NoSuchElementException ex) {
            return new ResponseEntity<>(ErrorResponse.builder()
                    .message(String.format("No team with id %d found", id))
                    .build(), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/team")
    public ResponseEntity<?> createTeam(@RequestBody TeamDTO teamDTO) {
        try {
            return new ResponseEntity<>(teamService.saveTeam(teamDTO), HttpStatus.CREATED);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(ErrorResponse.builder()
                    .message(String.format("Invalid manager id"))
                    .build(), HttpStatus.NOT_FOUND);
        }

    }

    @PutMapping("/team/{id}/agent")
    public ResponseEntity<?> assignAgent(@PathVariable Long id, @RequestBody AgentDTO agentDTO) {
        try {
            return new ResponseEntity<>(teamService.assignAgent(agentDTO, id), HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(ErrorResponse.builder()
                    .message(String.format("No team with id %d found", id))
                    .build(), HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/teams/empty")
    public ResponseEntity<?> findEmptyTeams() {
        return new ResponseEntity<>(teamService.findEmptyTeams(), HttpStatus.OK);
    }

    @ExceptionHandler
    public ResponseEntity<?> handle(Exception e) {
        return new ResponseEntity<>(ErrorResponse.builder()
                .message(e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
