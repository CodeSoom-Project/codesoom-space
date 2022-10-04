package com.codesoom.myseat.services;

import com.codesoom.myseat.domain.Seat;
import com.codesoom.myseat.repositories.SeatRepository;
import org.springframework.stereotype.Service;

/**
 * 좌석 추가 서비스
 */
@Service
public class SeatAddService {
    private final SeatRepository seatRepo;

    public SeatAddService(
            SeatRepository seatRepo
    ) {
        this.seatRepo = seatRepo;
    }

    /**
     * 생성된 좌석을 반환한다.
     * 
     * @param number 생성할 좌석의 번호
     * @return 좌석
     */
    public Seat createSeat(
            int number
    ) {
        return seatRepo.save(Seat.builder()
                        .number(number)
                        .build()
        );
    }
}
