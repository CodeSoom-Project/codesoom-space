package com.codesoom.myseat.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.*;

/**
 * 좌석 엔티티
 */
@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Slf4j
public class Seat {
    @Id
    @GeneratedValue
    @Column(name="seat_id")
    private Long id;

    private int number;
    
    @Builder.Default
    private boolean isReserved = false;

    @OneToOne(mappedBy = "seat")
    private SeatReservation seatReservation;

    public void reserve(SeatReservation seatReservation) {
        this.isReserved = true;
        this.seatReservation = seatReservation;
    }

    public void cancelReservation() {
        log.info("예약 취소");
                
        isReserved = false;
        this.seatReservation = null;
    }
}
