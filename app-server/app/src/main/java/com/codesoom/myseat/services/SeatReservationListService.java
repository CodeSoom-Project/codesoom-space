package com.codesoom.myseat.services;

import com.codesoom.myseat.domain.SeatReservation;
import com.codesoom.myseat.domain.SeatReservationRepository;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 좌석 예약 목록 조회 서비스
 */
@Service
public class SeatReservationListService {
    private final SeatReservationRepository repository;

    public SeatReservationListService(SeatReservationRepository repository) {
        this.repository = repository;
    }

    public List<SeatReservation> seatReservations() {
        return null;
    }
}
