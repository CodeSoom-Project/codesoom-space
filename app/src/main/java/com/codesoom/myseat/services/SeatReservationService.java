package com.codesoom.myseat.services;

import com.codesoom.myseat.domain.Seat;
import com.codesoom.myseat.domain.SeatRepository;
import com.codesoom.myseat.domain.SeatReservation;
import com.codesoom.myseat.domain.SeatReservationRepository;
import com.codesoom.myseat.dto.SeatReservationRequest;
import com.codesoom.myseat.exceptions.SeatAlreadyInUseException;
import com.codesoom.myseat.exceptions.SeatNotFoundException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * 좌석 예약 서비스
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

    /**
     * 생성된 좌석 예약 정보를 반환한다.
     *
     * @param number 예약할 좌석 번호
     * @param seatReservationRequest 좌석 예약 요청 정보
     * @return 좌석 예약 정보
     */
    public SeatReservation addReservation(
            int number,
            SeatReservationRequest seatReservationRequest
    ) {
        Seat seat = findSeat(number);
        if(seat.isReserved() == true) {
            throw new SeatAlreadyInUseException(
                    "[" + number + "]번 좌석이 이미 사용중이어서 예약에 실패했습니다.");
        }

        LocalDateTime now = LocalDateTime.now();
        String date = now.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));

        return seatReservationRepository.save(
                SeatReservation.builder()
                        .seatNumber(number)
                        .userName(seatReservationRequest.getUserName())
                        .date(date)
                        .checkIn(seatReservationRequest.getCheckIn())
                        .checkOut(seatReservationRequest.getCheckOut())
                        .build());
    }

    // TODO: 좌석 예약 조회

    // TODO: 좌석 예약 수정

    // TODO: 좌석 예약 삭제

    /**
     * 조회된 좌석을 반환한다.
     *
     * @param number 좌석 번호
     * @return 좌석
     */
    private Seat findSeat(int number) {
        return seatRepository.findByNumber(number)
                .orElseThrow(() -> new SeatNotFoundException(
                        "[" + number + "]번 좌석을 찾을 수 없어서 조회에 실패했습니다."));
    }
}
