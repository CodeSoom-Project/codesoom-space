package com.codesoom.myseat.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * 좌석 예약 엔티티
 */
@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SeatReservation {
    @Id
    @GeneratedValue
    private Long id;

    private Long seatId;

    private Long userId;

    private int seatNumber;

    private String date;

    private String checkIn;

    private String checkOut;
}
