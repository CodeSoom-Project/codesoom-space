package com.codesoom.myseat.services;

import com.codesoom.myseat.domain.SeatReservation;
import com.codesoom.myseat.repositories.SeatReservationRepository;
import com.codesoom.myseat.exceptions.SeatReservationNotFoundException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

/**
 * 좌석 예약 목록 조회 서비스
 */
@Service
public class SeatReservationListService {
    private final SeatReservationRepository reservationRepo;

    public SeatReservationListService(
            SeatReservationRepository reservationRepo
    ) {
        this.reservationRepo = reservationRepo;
    }

    /**
     * 당일 좌석 예약 목록을 반환한다.
     *
     * @return 당일 좌석 예약 목록
     * @throws SeatReservationNotFoundException 당일 예약 내역을 찾을 수 없는 경우 예외를 던진다.
     */
    public List<SeatReservation> seatReservations() {
        return seatReservations(today());
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

    /**
     * 당일 좌석 예약 목록을 반환한다.
     *
     * @param today 오늘 날짜
     * @return 당일 좌석 예약 목록
     * @throws SeatReservationNotFoundException 당일 예약 내역을 찾을 수 없는 경우 예외를 던진다.
     */
    private List<SeatReservation> seatReservations(
            String today
    ) {
        return reservationRepo.findAllByDate(today)
                .orElseThrow(() -> new SeatReservationNotFoundException(
                        "당일 예약 내역을 찾을 수 없어서 조회에 실패했습니다."));
    }
}
