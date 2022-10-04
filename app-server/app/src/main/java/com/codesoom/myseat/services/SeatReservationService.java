package com.codesoom.myseat.services;

import com.codesoom.myseat.domain.Seat;
import com.codesoom.myseat.domain.User;
import com.codesoom.myseat.repositories.SeatRepository;
import com.codesoom.myseat.domain.SeatReservation;
import com.codesoom.myseat.repositories.SeatReservationRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * 좌석 예약 서비스
 */
@Service
@Slf4j
public class SeatReservationService {
    private final SeatRepository seatRepo;
    private final SeatReservationRepository reservationRepo;

    public SeatReservationService(
            SeatRepository seatRepo,
            SeatReservationRepository reservationRepo
    ) {
        this.seatRepo = seatRepo;
        this.reservationRepo = reservationRepo;
    }

    public SeatReservation createReservation(
            User user,
            Seat seat
    ) {
        seat.reserve(user);
        seatRepo.save(seat);

        return reservationRepo.save(
                SeatReservation.builder()
                        .date(today())
                        .user(user)
                        .build()
        );
    }

    /**
     * 오늘 날짜를 반환한다.
     *
     * @return 오늘 날짜
     */
    private String today() {
        return LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    }
}
