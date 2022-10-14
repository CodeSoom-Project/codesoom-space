package com.codesoom.myseat.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import static javax.persistence.CascadeType.PERSIST;
import static javax.persistence.GenerationType.IDENTITY;


@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Retrospective {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "retrospective_id")
    Long id;

    String content;

    @OneToOne(cascade = PERSIST)
    @JoinColumn(name = "reservation_id")
    private Reservation reservation;

}
