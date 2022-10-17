package com.codesoom.myseat.services.seats;

import com.codesoom.myseat.domain.Seat;
import com.codesoom.myseat.exceptions.SeatListNotFoundException;
import com.codesoom.myseat.repositories.SeatRepository;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 좌석 목록 조회 서비스
 */
@Service
public class SeatListService {
    private final SeatRepository seatRepo;

    public SeatListService(
            SeatRepository seatRepo
    ) {
        this.seatRepo = seatRepo;
    }

    public List<Seat> seats() {
        try {
            return seatRepo.findAll();
        } catch (NullPointerException e) {
            throw new SeatListNotFoundException();
        }
    }
}
