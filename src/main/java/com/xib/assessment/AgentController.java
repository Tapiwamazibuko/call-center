package com.xib.assessment;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AgentController {

    @GetMapping("agent/{id}")
    public Agent findAgent(@PathVariable("id") Long id) {
        Agent a = new Agent();
        a.setId(id);
        return a;
    }

}
