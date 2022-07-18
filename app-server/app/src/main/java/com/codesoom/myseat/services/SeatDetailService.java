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
        return null;
    }
}
