package com.codesoom.myseat.repositories;

import com.codesoom.myseat.domain.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

/**
 * 좌석 예약 레포지토리
 */
public interface ReservationRepository
        extends JpaRepository<Reservation, Long> {
    /**
     * 
     * @param s must not be {@literal null}.
     * @return
     */
    Reservation save(Reservation s);

    /**
     * 좌석 예약 정보를 삭제한다.
     *
     * @param s must not be {@literal null}. 삭제할 좌석 예약 정보
     */
    void delete(Reservation s);

    List<Reservation> findAll();

    /**
     * 주어진 방문 일자와 회원 id로 예약 내역 검색에 성공하면 true, 그렇지 않으면 false를 반환합니다.
     * 
     * @param date 방문 일자
     * @param id 회원 id
     * @return 주어진 방문 일자와 회원 id로 예약 내역 검색에 성공하면 true, 그렇지 않으면 false
     */
    boolean existsByDateAndUser_Id(String date, Long id);

    /**
     * 주어진 회원 id로 예약 내역을 모두 조회합니다.
     *
     * @param id 회원 id
     * @return 모든 예약 내역
     */
    List<Reservation> findAllByUser_Id(Long id);

    /**
     * 주어진 reservation id와 user id로 예약 내역을 조회합니다.
     *
     * @param id 예약 id
     * @param userId 회원 id
     * @return 예약 내역
     */
    Optional<Reservation> findByIdAndUser_Id(Long id, Long userId);


    /**
     * 주어진 reservation id와 회원 id가 동일하면 true, 그렇지 않으면 false를 반환합니다.
     *
     * @param id 예약 id
     * @param userId 회원 id
     * @return 예약 내역
     */
    boolean existsByIdAndUser_Id(Long id, Long userId);
}
