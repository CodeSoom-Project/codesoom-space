package com.codesoom.myseat.services;

import com.codesoom.myseat.domain.Seat;
import com.codesoom.myseat.repositories.SeatRepository;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 좌석 목록 조회 서비스
 */
@Service
public class SeatListService {
    private final SeatRepository repository;

    public SeatListService(SeatRepository repository) {
        this.repository = repository;
    }

    public List<Seat> seats() {
        return repository.findAll();
    }
}
