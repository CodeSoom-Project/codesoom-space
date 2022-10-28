package com.codesoom.myseat.domain;

import com.codesoom.myseat.converters.ReservationStatusConverter;
import com.codesoom.myseat.dto.ReservationRequest;
import com.codesoom.myseat.enums.ReservationStatus;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;

import java.time.LocalDateTime;

import static javax.persistence.CascadeType.PERSIST;
import static javax.persistence.GenerationType.IDENTITY;

/**
 * 좌석 예약 엔티티
 */
@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EntityListeners(AuditingEntityListener.class)
@Slf4j
public class Reservation {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name="reservation_id")
    private Long id;

    @Embedded
    private Date date;

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

    @CreatedDate
    private LocalDateTime createdDate;

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
        this.date = new Date(updateData.getDate());
        this.plan.update(updateData.getContent());
    }

    /**
     * 회고내용을 수정합니다.
     *
     * @return 회고 내용 수정
     */
    public void updateRetrospective(String content) {
        this.retrospective.updateContent(content);
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
    public boolean isDifferentDate(Date date) {
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

    /**
     * 회고를 등록합니다.
     *
     * @param reservation 예약 정보
     * @param content 회고 내용
     * @return 회고 내용 등록
     */
    public Retrospective addRetrospective(final Reservation reservation,
                                          final String content) {
        return Retrospective.builder()
                .reservation(reservation)
                .content(content)
                .build();
    }

}
