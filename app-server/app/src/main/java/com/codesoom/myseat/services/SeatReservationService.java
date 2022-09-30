package com.codesoom.myseat.services;

import com.codesoom.myseat.domain.Seat;
import com.codesoom.myseat.domain.User;
import com.codesoom.myseat.exceptions.SeatReservationNotFoundException;
import com.codesoom.myseat.repositories.SeatRepository;
import com.codesoom.myseat.domain.SeatReservation;
import com.codesoom.myseat.repositories.SeatReservationRepository;
import com.codesoom.myseat.dto.SeatReservationRequest;
import com.codesoom.myseat.exceptions.SeatAlreadyReservedException;
import com.codesoom.myseat.exceptions.SeatNotFoundException;
import com.codesoom.myseat.exceptions.UserAlreadyReservedSeatTodayException;
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

    /**
     * 생성된 좌석 예약 정보를 반환한다.
     *
     * @param number 예약할 좌석 번호
     * @param request 좌석 예약 요청 정보
     * @return 좌석 예약 정보
     * @throws SeatNotFoundException 좌석을 찾을 수 없는 경우 예외를 던진다.
     * @throws SeatAlreadyReservedException 좌석이 이미 예약된 상태일 경우 예외를 던진다.
     * @throws UserAlreadyReservedSeatTodayException 회원의 당일 예약 내역이 이미 있는 경우 예외를 던진다.
     */
    public SeatReservation addReservation(
            int number,
            SeatReservationRequest request,
            User user
    ) {
        Seat seat = seat(number);
        log.info("seat: " + seat.getNumber());
        
        if(seat.isStatus()) {
            throw new SeatAlreadyReservedException("이미 예약된 좌석입니다.");
        }
        
        if(checkAlreadyReservedToday(user.getEmail()) != null) {
            throw new UserAlreadyReservedSeatTodayException("이미 당일 예약 내역이 있습니다.");
        } else {
            SeatReservation seatReservation = reservationRepo.save(
                    SeatReservation.builder()
                            .date(today())
                            .checkIn(request.getCheckIn())
                            .checkOut(request.getCheckOut())
                            .user(user)
                            .seat(seat)
                            .build());
            seat.reserve(seatReservation);

            seatRepo.save(seat);

            return seatReservation;
        }
    }

    /**
     * 조회된 좌석을 반환한다.
     *
     * @param number 좌석 번호
     * @return 좌석
     * @throws SeatNotFoundException 좌석을 찾을 수 없는 경우 예외를 던진다.
     */
    private Seat seat(
            int number
    ) {
        return seatRepo.findByNumber(number)
                .orElseThrow(() -> new SeatNotFoundException(
                        "[" + number + "]번 좌석을 찾을 수 없어서 조회에 실패했습니다."));
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
     * 회원의 당일 예약 내역을 반환한다.
     *
     * @param email 회원 email
     * @return 회원의 당일 예약 내역
     * @throws SeatReservationNotFoundException 좌석 예약 내역을 찾을 수 없는 경우 예외를 던진다.
     */
    public SeatReservation checkAlreadyReservedToday(
            String email
    ) {
        return reservationRepo.findByDateAndUser_EmailAndCanceledIsFalse(today(), email)
                .orElseThrow(() -> new SeatReservationNotFoundException(
                        "[" + email + "]회원의 당일 예약 내역을 찾을 수 없어서 조회에 실패했습니다."));
    }
}
