package com.codesoom.myseat.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.*;

import static javax.persistence.GenerationType.IDENTITY;

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
    @GeneratedValue(strategy = IDENTITY)
    @Column(name="seat_id")
    private Long id;

    private int number;

    @Builder.Default
    private boolean status = false;

    public boolean getStatus() {
        return this.status;
    }
}
