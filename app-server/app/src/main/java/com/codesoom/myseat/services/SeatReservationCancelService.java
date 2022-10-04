package com.codesoom.myseat.services;

import com.codesoom.myseat.domain.Seat;
import com.codesoom.myseat.domain.User;
import com.codesoom.myseat.domain.SeatReservation;
import com.codesoom.myseat.repositories.SeatReservationRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * 좌석 예약 취소 서비스
 */
@Service
@Slf4j
public class SeatReservationCancelService {
    private static final SimpleDateFormat dateFormat
            = new SimpleDateFormat("HH:mm:ss");

    private final SeatReservationRepository reservationRepo;

    public SeatReservationCancelService(
            SeatReservationRepository reservationRepo
    ) {
        this.reservationRepo = reservationRepo;
    }

    /**
     * 예약을 취소한다.
     * 
     * @param user
     * @param seat
     */
    public void cancelReservation(
            User user,
            Seat seat
    ) {
        SeatReservation reservation = user.getSeatReservation();
        user.cancelReservation();
        reservationRepo.delete(reservation);
        seat.cancelReservation();
    }

    /**
     * 매일 23시에 모든 예약을 초기화한다.
     */
    @Scheduled(cron = "0 0 23 * * *")
    public void reset() {
        log.info("현재 시간: {}", dateFormat.format(new Date()));
        
        List<SeatReservation> reservations = reservationRepo.findAll();
        for(SeatReservation s : reservations) {
            s.getUser().cancelReservation();
            Seat seat = s.getUser().getSeat();
            
            reservationRepo.delete(s);
            seat.cancelReservation();
        }
    }
}
