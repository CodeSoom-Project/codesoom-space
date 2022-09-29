package com.codesoom.myseat.services;

import com.codesoom.myseat.domain.Seat;
import com.codesoom.myseat.domain.User;
import com.codesoom.myseat.repositories.SeatRepository;
import com.codesoom.myseat.domain.SeatReservation;
import com.codesoom.myseat.repositories.SeatReservationRepository;
import com.codesoom.myseat.exceptions.SeatNotFoundException;
import com.codesoom.myseat.exceptions.SeatReservationNotFoundException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * 좌석 예약 취소 서비스
 */
@Service
public class SeatReservationCancelService {
    private final SeatRepository seatRepo;
    private final SeatReservationRepository reservationRepo;

    public SeatReservationCancelService(
            SeatRepository seatRepo, 
            SeatReservationRepository reservationRepo
    ) {
        this.seatRepo = seatRepo;
        this.reservationRepo = reservationRepo;
    }

    /**
     * 예약을 취소한다.
     * 
     * @param seatNumber 예약 취소할 좌석 번호
     * @param user 취소 요청한 사용자 정보
     * @throws SeatNotFoundException 좌석을 찾을 수 없는 경우 예외를 던진다.
     * @throws SeatReservationNotFoundException 좌석 예약 내역을 찾을 수 없는 경우 예외를 던진다.
     */
    public void cancelReservation(
            int seatNumber,
            User user
    ) {
        Seat seat = seat(seatNumber);

        SeatReservation reservation = seatReservation(user.getEmail());

        seat.cancelReservation();
        reservation.cancelReservation();
        user.cancelReserve();
    }

    /**
     * 당일 좌석 예약 내역을 반환한다.
     *
     * @param email 회원 email
     * @return 좌석 예약 내역
     * @throws SeatReservationNotFoundException 좌석 예약 내역을 찾을 수 없는 경우 예외를 던진다.
     */
    private SeatReservation seatReservation(
            String email
    ) {
        return reservationRepo.findByDateAndUser_Email(today(), email)
                .orElseThrow(() -> new SeatReservationNotFoundException(
                        "[" + email + "] 회원의 당일 예약 내역을 찾을 수 없어서 조회에 실패했습니다."));
    }

    /**
     * 조회된 좌석을 반환한다.
     *
     * @param seatNumber 좌석 번호
     * @return 좌석
     * @throws SeatNotFoundException 좌석을 찾을 수 없는 경우 예외를 던진다.
     */
    private Seat seat(int seatNumber) {
        return seatRepo.findByNumber(seatNumber)
                .orElseThrow(() -> new SeatNotFoundException(
                        "[" + seatNumber + "]번 좌석을 찾을 수 없어서 조회에 실패했습니다."));
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
