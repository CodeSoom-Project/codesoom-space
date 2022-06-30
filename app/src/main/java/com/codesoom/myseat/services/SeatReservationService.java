package com.codesoom.myseat.services;

import com.codesoom.myseat.domain.SeatRepository;
import com.codesoom.myseat.domain.SeatReservation;
import com.codesoom.myseat.domain.SeatReservationRepository;
import com.codesoom.myseat.dto.SeatReservationRequest;
import org.springframework.stereotype.Service;

/**
 * 좌석 예약 생성, 조회, 수정, 삭제 서비스
 */
@Service
public class SeatReservationService {
    private final SeatRepository seatRepository;
    private final SeatReservationRepository seatReservationRepository;

    public SeatReservationService(
            SeatRepository seatRepository,
            SeatReservationRepository seatReservationRepository
    ) {
        this.seatRepository = seatRepository;
        this.seatReservationRepository = seatReservationRepository;
    }

    // TODO: 좌석 예약 생성
    public SeatReservation addReservation(
            Long id,
            SeatReservationRequest seatReservationRequest
    ) {
        // TODO
        //  - 좌석을 조회해서 현재 사용중인지 확인
        //  - 예약 정보 저장
        return null;
    }

    // TODO: 좌석 예약 조회

    // TODO: 좌석 예약 수정

    // TODO: 좌석 예약 삭제
}
