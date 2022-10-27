package com.codesoom.myseat.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import java.time.LocalDateTime;

import static javax.persistence.CascadeType.PERSIST;
import static javax.persistence.GenerationType.IDENTITY;


@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EntityListeners(AuditingEntityListener.class)
public class Retrospective {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "retrospective_id")
    private Long id;

    @Column(length = 1000)
    private String content;

    @OneToOne(cascade = PERSIST)
    @JoinColumn(name = "reservation_id")
    private Reservation reservation;

    @CreatedDate
    private LocalDateTime createdDate;

    public void updateContent(String content) {
        this.content = content;
    }

}
