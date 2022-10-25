package com.codesoom.myseat.repositories;

import com.codesoom.myseat.domain.Plan;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlanRepository 
        extends JpaRepository<Plan, Long> {
    
    Plan save(Plan plan);
    
}
