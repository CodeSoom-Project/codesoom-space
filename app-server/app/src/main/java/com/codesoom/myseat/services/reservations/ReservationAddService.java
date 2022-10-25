package com.codesoom.myseat.services.reservations;

import com.codesoom.myseat.domain.Date;
import com.codesoom.myseat.domain.Plan;
import com.codesoom.myseat.domain.Reservation;
import com.codesoom.myseat.domain.User;
import com.codesoom.myseat.enums.ReservationStatus;
import com.codesoom.myseat.exceptions.AlreadyReservedException;
import com.codesoom.myseat.exceptions.ContentTooLongException;
import com.codesoom.myseat.exceptions.NotReservableDateException;
import com.codesoom.myseat.exceptions.ReservationNotFoundException;
import com.codesoom.myseat.repositories.PlanRepository;
import com.codesoom.myseat.repositories.ReservationRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.InvalidDataAccessResourceUsageException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

/**
 * 좌석 예약 서비스
 */
@Service
@Slf4j
public class ReservationAddService {
    private final PlanRepository planRepo;
    private final ReservationRepository reservationRepo;

    public ReservationAddService(
            PlanRepository planRepo, 
            ReservationRepository reservationRepo
    ) {
        this.planRepo = planRepo;
        this.reservationRepo = reservationRepo;
    }

    /**
     * 생성된 예약 내역을 반환합니다.
     * 
     * @param user 회원
     * @param date 방문 일자
     * @param content 계획 내용
     * @return 생성된 예약
     * @throws AlreadyReservedException 방문 일자에 대한 예약 내역이 이미 존재하면 던집니다.
     * @throws ContentTooLongException 계획의 길이가 너무 길면 던집니다.
     */
    @Transactional
    public Reservation createReservation(
            User user,
            String date,
            String content
    ) {
        Date givenDate = new Date(date);
        if (!givenDate.isReservable()) {
            throw new NotReservableDateException();
        }
        if(isDuplicateReservation(givenDate, user.getId())) {
            throw new AlreadyReservedException();
        }

        Plan plan = Plan.builder()
                    .content(content)
                    .build();
        Reservation reservation = Reservation.builder()
                .date(givenDate)
                .user(user)
                .plan(plan)
                .build();

        try {
            planRepo.save(plan);
        } catch (InvalidDataAccessResourceUsageException e) {
            e.printStackTrace();
            throw new ContentTooLongException();
        }
        reservationRepo.save(reservation);

        return reservation;
    }

    /**
     * 중복된 예약이면 true, 그렇지 않으면 false를 반환합니다.
     * 
     * @param date 방문 일자
     * @param userId 회원 id
     * @return 중복된 예약이면 true, 그렇지 않으면 false
     */
    public boolean isDuplicateReservation(Date date, Long userId) {
        return reservationRepo.existsByDateAndUser_IdAndStatusNot(date, userId, ReservationStatus.CANCELED);
    }

    /**
     * 주어진 id로 조회된 예약을 반환합니다.
     * 
     * @param id 예약 id
     * @return 조회된 예약
     * @throws ReservationNotFoundException 예약 조회에 실패하면 던집니다.
     */
    public Reservation findReservation(Long id) {
        return reservationRepo.findById(id)
                .orElseThrow(() -> new ReservationNotFoundException());
    }
    
}
