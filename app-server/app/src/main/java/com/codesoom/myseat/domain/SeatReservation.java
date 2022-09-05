package com.codesoom.myseat.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

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
    @Column(name="seatReservation_id")
    private Long id;

    private String date;

    private String checkIn;

    private String checkOut;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @OneToOne
    @JoinColumn(name = "seat_id")
    private Seat seat;
    
    public void cancelReservation() {
        this.user = null;
    }
}
