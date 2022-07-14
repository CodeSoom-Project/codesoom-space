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
    private final SeatReservationRepository reservationRepository;

    public SeatReservationService(
            SeatRepository seatRepository,
            SeatReservationRepository reservationRepository
    ) {
        this.seatRepository = seatRepository;
        this.reservationRepository = reservationRepository;
    }

    /**
     * 생성된 좌석 예약 정보를 반환한다.
     *
     * @param seatNumber 예약할 좌석 번호
     * @param request 좌석 예약 요청 정보
     * @return 좌석 예약 정보
     * @throws SeatNotFoundException 좌석을 찾을 수 없는 경우 예외를 던진다.
     * @throws SeatAlreadyReservedException 좌석이 이미 예약된 상태일 경우 예외를 던진다.
     */
    public SeatReservation addReservation(
            int seatNumber,
            SeatReservationRequest request
    ) {
        Seat seat = checkReservationStatus(seatNumber);
        seat.reserve(request.getUserName());

        return reservationRepository.save(
                SeatReservation.builder()
                        .seatNumber(seatNumber)
                        .userName(request.getUserName())
                        .date(today())
                        .checkIn(request.getCheckIn())
                        .checkOut(request.getCheckOut())
                        .build());
    }

    /**
     * 조회된 좌석을 반환한다.
     *
     * @param seatNumber 좌석 번호
     * @return 좌석
     * @throws SeatNotFoundException 좌석을 찾을 수 없는 경우 예외를 던진다.
     */
    private Seat seat(int seatNumber) {
        return seatRepository.findByNumber(seatNumber)
                .orElseThrow(() -> new SeatNotFoundException(
                        "[" + seatNumber + "]번 좌석을 찾을 수 없어서 조회에 실패했습니다."));
    }

    /**
     * 좌석이 이미 예약된 상태인지 확인한다.
     *
     * @param seatNumber 좌석 번호
     * @return 좌석
     * @throws SeatNotFoundException 좌석을 찾을 수 없는 경우 예외를 던진다.
     * @throws SeatAlreadyReservedException 좌석이 이미 예약된 상태일 경우 예외를 던진다.
     */
    private Seat checkReservationStatus(int seatNumber) {
        Seat seat = seat(seatNumber);

        if(!seat.getUserName().isBlank()) {
            throw new SeatAlreadyReservedException(
                    "[" + seatNumber + "]번 좌석은 이미 예약된 좌석이므로 예약에 실패했습니다.");
        }

        return seat;
    }

    /**
     * 오늘 날짜를 반환한다.
     *
     * @return 오늘 날짜
     */
    private String today() {
        LocalDateTime now = LocalDateTime.now();
        return now.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    }
}
