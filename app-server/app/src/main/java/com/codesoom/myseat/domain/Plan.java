package com.codesoom.myseat.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.*;

import static javax.persistence.CascadeType.PERSIST;

/**
 * 계획 엔티티
 */
@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Slf4j
public class Plan {
    @Id
    @GeneratedValue
    @Column(name="plan_id")
    private Long id;

    private String content;

    @OneToOne(cascade = PERSIST)
    @JoinColumn(name = "seatReservation_id")
    private SeatReservation seatReservation;

    /**
     * 예약을 추가합니다.
     * 
     * @param seatReservation 예약
     */
    public void addReservation(
            SeatReservation seatReservation
    ) {
        this.seatReservation = seatReservation;
    }
}
