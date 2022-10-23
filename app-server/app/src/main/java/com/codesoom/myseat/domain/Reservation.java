package com.codesoom.myseat.domain;

import com.codesoom.myseat.converters.ReservationStatusConverter;
import com.codesoom.myseat.dto.ReservationRequest;
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

    @OneToOne
    @JoinColumn(name = "plan_id")
    private Plan plan;

    @Convert(converter = ReservationStatusConverter.class)
    @Column(name = "status")
    @Builder.Default
    private ReservationStatus status = ReservationStatus.RETROSPECTIVE_WAITING;

    @OneToOne(mappedBy = "reservation", cascade = PERSIST)
    private Retrospective retrospective;

    @PrePersist
    public void prePersist() {
        if (this.status == null) {
            this.status = ReservationStatus.RETROSPECTIVE_WAITING;
        }
    }

    public boolean isOwnReservation(Long userId) {
        return this.user.getId().equals(userId);
    }

    public void update(ReservationRequest updateData) {
        this.date = updateData.getDate();
        this.plan.update(updateData.getContent());
    }

    /**
     * 회고가 null이 아니면 true, 그렇지 않으면 false를 반환합니다.
     * 
     * @return 회고가 null이면 true, 그렇지 않으면 false
     */
    public boolean haveRetrospective() {
        return this.retrospective != null;
    }

    public void cancel() {
        this.status = ReservationStatus.CANCELED;
    }

    public void completeRetrospective() {
        this.status = ReservationStatus.RETROSPECTIVE_COMPLETE;
    }

    /**
     * 예약의 날짜와 주어진 날짜가 다른지 확인합니다.
     *
     * @param date 비교하려는 날짜
     * @return 원래 날짜와 주어진 날짜가 다르면 true, 같으면 false
     */
    public boolean isDifferentDate(String date) {
        return !this.date.equals(date);
    }

    /**
     * 취소된 예약이면 true, 그렇지 않으면 false를 반환합니다.
     * 
     * @return 취소된 예약이면 true, 그렇지 않으면 false
     */
    public boolean isCanceled() {
        return this.status == ReservationStatus.CANCELED;
    }

}
