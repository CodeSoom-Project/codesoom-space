package com.codesoom.myseat.services;

import com.codesoom.myseat.domain.SeatRepository;
import com.codesoom.myseat.domain.SeatReservationRepository;
import com.codesoom.myseat.dto.SeatReservationCancelRequest;
import org.springframework.stereotype.Service;

/**
 * 좌석 예약 취소 서비스
 */
@Service
public class SeatReservationCancelService {
    private final SeatRepository seatRepository;
    private final SeatReservationRepository seatReservationRepository;

    public SeatReservationCancelService(SeatRepository seatRepository, SeatReservationRepository seatReservationRepository) {
        this.seatRepository = seatRepository;
        this.seatReservationRepository = seatReservationRepository;
    }

    public void cancelReservation(
            int seatNumber,
            SeatReservationCancelRequest request
    ) {
        // TODO: 해당하는 좌석의 isReserved를 다시 false로 바꿔야 함
        // TODO: 취소 요청을 한 사용자가 당일 예약했던 내역을 삭제해야 함 
    }
}
