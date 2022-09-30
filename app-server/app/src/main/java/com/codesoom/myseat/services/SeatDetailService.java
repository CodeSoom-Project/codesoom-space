package com.codesoom.myseat.services;

import com.codesoom.myseat.domain.SeatReservation;
import com.codesoom.myseat.domain.User;
import com.codesoom.myseat.dto.SeatDetailResponse;
import com.codesoom.myseat.repositories.SeatReservationRepository;
import com.codesoom.myseat.exceptions.SeatReservationNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * 좌석 상세 조회 서비스
 */
@Service
@Slf4j
public class SeatDetailService {
    private final SeatReservationRepository reservationRepo;

    public SeatDetailService(
            SeatReservationRepository reservationRepo
    ) {
        this.reservationRepo = reservationRepo;
    }

    /**
     * 좌석의 당일 예약 정보를 반환한다.
     *
     * @param number 좌석 번호
     * @return 당일 예약 정보
     * @throws SeatReservationNotFoundException 예약 내역을 찾을 수 없는 경우 예외를 던진다.
     */
    public SeatDetailResponse seatDetail(
            int number, 
            User user
    ) {
        log.info("number: " + number);

        SeatReservation s = seatReservation(number);

        return SeatDetailResponse.builder()
                .number(number)
                .date(s.getDate())
                .checkIn(s.getCheckIn())
                .checkOut(s.getCheckOut())
                .name(s.getUser().getName())
                .build();
    }

    /**
     * 좌석의 당일 예약 내역을 반환한다.
     * 
     * @param number 좌석 번호
     * @return 예약 내역
     */
    private SeatReservation seatReservation(
            int number
    ) {
        return reservationRepo.findByDateAndSeatNumber(today(), number)
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
