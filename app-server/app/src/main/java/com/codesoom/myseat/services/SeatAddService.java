package com.codesoom.myseat.services;

import com.codesoom.myseat.domain.Seat;
import com.codesoom.myseat.repositories.SeatRepository;
import com.codesoom.myseat.dto.SeatAddRequest;
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
     * 추가된 좌석 정보를 반환한다.
     * 
     * @param request 추가할 좌석 정보
     * @return 좌석 정보
     */
    public Seat addSeat(SeatAddRequest request) {
        return seatRepo.save(
                Seat.builder()
                        .number(request.getNumber())
                        .build()
        );
    }
}
