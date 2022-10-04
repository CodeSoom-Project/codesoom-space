package com.codesoom.myseat.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.*;

/**
 * 좌석 예약 엔티티
 */
@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Slf4j
public class SeatReservation {
    @Id
    @GeneratedValue
    @Column(name="seatReservation_id")
    private Long id;

    private String date;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;
}
