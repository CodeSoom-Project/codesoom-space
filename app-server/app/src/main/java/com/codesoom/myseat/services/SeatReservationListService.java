package com.codesoom.myseat.services;

import com.codesoom.myseat.domain.SeatReservation;
import com.codesoom.myseat.domain.SeatReservationRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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
        return repository.findAllByDate(today());
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
