package com.xib.assessment.agent;

import org.springframework.data.jpa.repository.JpaRepository;

public interface AgentRepository extends JpaRepository<Agent, Long> {
    Agent findByIdNumber(String idNumber);
}
