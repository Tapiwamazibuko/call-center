package com.xib.assessment.agent;

import com.xib.assessment.dto.AgentDTO;
import com.xib.assessment.dto.ErrorResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.NoSuchElementException;

@RestController
@RequiredArgsConstructor
public class AgentController {
    private final AgentService agentService;

    @GetMapping("/agent/{id}")
    public ResponseEntity<?> findAgent(@PathVariable("id") Long id) {
        try {
            return new ResponseEntity<>(agentService.findAgent(id), HttpStatus.OK);
        } catch(NoSuchElementException ex) {
            return new ResponseEntity<>(ErrorResponse.builder()
                    .message(String.format("No agent with id %d found", id))
                    .build(), HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/agents")
    public ResponseEntity<?> findAllAgents(@RequestParam Integer page, @RequestParam Integer size) {
        return new ResponseEntity<>(agentService.findAllAgents(page, size), HttpStatus.OK);

    }

    @PostMapping("/agent")
    public ResponseEntity<?>  createAgent(@RequestBody AgentDTO agent) {
        try {
            return new ResponseEntity<>(agentService.saveAgent(agent), HttpStatus.CREATED);
        } catch(IllegalArgumentException e) {
            return new ResponseEntity<>(ErrorResponse.builder()
                    .message(e.getMessage())
                    .build(), HttpStatus.BAD_REQUEST);
        }
    }

    @ExceptionHandler
    public ResponseEntity<?> handle(Exception e) {
        return new ResponseEntity<>(ErrorResponse.builder()
                .message(e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
