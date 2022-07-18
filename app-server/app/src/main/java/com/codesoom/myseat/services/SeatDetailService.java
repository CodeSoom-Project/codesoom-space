package com.codesoom.myseat.services;

import com.codesoom.myseat.domain.SeatReservation;
import com.codesoom.myseat.domain.SeatReservationRepository;
import org.springframework.stereotype.Service;

/**
 * 좌석 상세 조회 서비스
 */
@Service
public class SeatDetailService {
    private final SeatReservationRepository repository;

    public SeatDetailService(SeatReservationRepository repository) {
        this.repository = repository;
    }

    public SeatReservation seatDetail(int seatNumber) {
        // TODO: 해당 좌석의 당일 예약 정보를 반환해야 한다.
        return null;
    }
}
