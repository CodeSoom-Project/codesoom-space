package com.codesoom.myseat.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * 좌석 엔티티
 */
@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Seat {
    @Id
    @GeneratedValue
    @Column(name="seat_id")
    private Long id;

    private int number;
    
    @Builder.Default
    private boolean isReserved = false;

    @Builder.Default
    private String userName = "";

    @OneToOne(mappedBy = "seat")
    private SeatReservation seatReservation;

    public void reserve(String userName) {
        this.isReserved = true; 
        this.userName = userName;
    }

    public void cancelReservation() {
        this.userName = "";
    }
}
