package com.codesoom.myseat.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.*;

import static javax.persistence.CascadeType.PERSIST;
import static javax.persistence.GenerationType.IDENTITY;

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
    @GeneratedValue(strategy = IDENTITY)
    @Column(name="plan_id")
    private Long id;

    private String content;

    @OneToOne(cascade = PERSIST)
    @JoinColumn(name = "reservation_id")
    private Reservation reservation;

    @OneToOne(mappedBy = "plan", cascade = PERSIST)
    private Retrospective retrospective;

    /**
     * 예약을 추가합니다.
     * 
     * @param reservation 예약
     */
    public void addReservation(
            Reservation reservation
    ) {
        this.reservation = reservation;
    }
}
