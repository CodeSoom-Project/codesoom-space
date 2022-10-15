package com.codesoom.myseat.repositories;

import com.codesoom.myseat.domain.Retrospective;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RetrospectiveRepository extends JpaRepository<Retrospective, Long> {
}
