package com.codesoom.myseat.services;

import com.codesoom.myseat.domain.SeatReservation;
import com.codesoom.myseat.repositories.SeatReservationRepository;
import com.codesoom.myseat.exceptions.SeatReservationNotFoundException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * 좌석 상세 조회 서비스
 */
@Service
public class SeatDetailService {
    private final SeatReservationRepository repository;

    public SeatDetailService(SeatReservationRepository repository) {
        this.repository = repository;
    }

    /**
     * 좌석의 당일 예약 정보를 반환한다.
     *
     * @param seatNumber 좌석 번호
     * @return 당일 예약 정보
     * @throws SeatReservationNotFoundException 예약 내역을 찾을 수 없는 경우 예외를 던진다.
     */
    public SeatReservation seatDetail(int seatNumber) {
        return repository.findByDateAndSeatNumber(today(), seatNumber)
                .orElseThrow(() -> new SeatReservationNotFoundException(
                        "당일 예약 내역을 찾을 수 없어서 조회에 실패했습니다."));
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
