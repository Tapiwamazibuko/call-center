package com.xib.assessment.manager;

import com.xib.assessment.dto.ErrorResponse;
import com.xib.assessment.dto.ManagerDTO;
import com.xib.assessment.dto.ManagerResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ManagerController {
    private final ManagerService managerService;

    @PostMapping("/manager")
    public ResponseEntity<?> saveManager(@RequestBody ManagerDTO manager) {
        return new ResponseEntity<>(managerService.saveManager(manager), HttpStatus.CREATED);
    }

    @ExceptionHandler
    public ResponseEntity<?> handle(Exception e) {
        return new ResponseEntity<>(ErrorResponse.builder()
                .message(e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
