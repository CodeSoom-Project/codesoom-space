package com.codesoom.myseat.domain;

import com.codesoom.myseat.converters.ReservationStatusConverter;
import com.codesoom.myseat.enums.ReservationStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.*;

import static javax.persistence.CascadeType.PERSIST;
import static javax.persistence.GenerationType.IDENTITY;

/**
 * 좌석 예약 엔티티
 */
@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Slf4j
public class Reservation {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name="reservation_id")
    private Long id;

    private String date;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    @OneToOne(mappedBy = "reservation", cascade = PERSIST)
    private Plan plan;

    @Convert(converter = ReservationStatusConverter.class)
    @Column(name = "status")
    @Builder.Default
    private ReservationStatus status = ReservationStatus.RETROSPECTIVE_WAITING;

    @PrePersist
    public void prePersist() {
        if (this.status == null) {
            this.status = ReservationStatus.RETROSPECTIVE_WAITING;
        }
    }

}
