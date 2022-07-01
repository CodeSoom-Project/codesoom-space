package com.codesoom.myseat.services;

import com.codesoom.myseat.domain.Seat;
import com.codesoom.myseat.domain.SeatRepository;
import com.codesoom.myseat.domain.SeatReservation;
import com.codesoom.myseat.domain.SeatReservationRepository;
import com.codesoom.myseat.dto.SeatReservationRequest;
import com.codesoom.myseat.exceptions.SeatAlreadyReservedException;
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
        checkReservationStatus(number);

        return seatReservationRepository.save(
                SeatReservation.builder()
                        .seatNumber(number)
                        .userName(seatReservationRequest.getUserName())
                        .date(getDate())
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

    /**
     * 좌석이 이미 예약된 상태인지 확인한다.
     *
     * @param number 좌석 번호
     * @throws SeatNotFoundException 좌석을 찾을 수 없는 경우 예외를 던진다.
     * @throws SeatAlreadyReservedException 좌석이 이미 예약된 상태일 경우 예외를 던진다.
     */
    private void checkReservationStatus(int number) {
        Seat seat = findSeat(number);

        if(seat.isReserved()) {
            throw new SeatAlreadyReservedException(
                    "[" + number + "]번 좌석은 이미 예약된 좌석이므로 예약에 실패했습니다.");
        }
    }

    /**
     * 오늘 날짜를 반환한다.
     *
     * @return 오늘 날짜
     */
    private String getDate() {
        LocalDateTime now = LocalDateTime.now();
        return now.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    }
}
