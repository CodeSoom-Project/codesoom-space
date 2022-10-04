package com.codesoom.myseat.services;

import com.codesoom.myseat.domain.Seat;
import com.codesoom.myseat.exceptions.SeatNotFoundException;
import com.codesoom.myseat.repositories.SeatRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class SeatService {
    private final SeatRepository seatRepo;

    public SeatService(
            SeatRepository seatRepo
    ) {
        this.seatRepo = seatRepo;
    }

    public Seat findSeat(
            int number
    ) {
        return seatRepo.findByNumber(number)
                .orElseThrow(() -> new SeatNotFoundException());
    }
}
