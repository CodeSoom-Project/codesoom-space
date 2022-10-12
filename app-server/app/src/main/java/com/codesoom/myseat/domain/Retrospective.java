package com.codesoom.myseat.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import static javax.persistence.CascadeType.PERSIST;


@Entity
@Getter
@NoArgsConstructor
public class Retrospective {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    String retrospective;

    @OneToOne(cascade = PERSIST)
    @JoinColumn(name = "plan_id")
    private Plan plan;

    @Builder
    public Retrospective(String retrospective, Plan plan) {
        this.retrospective = retrospective;
        this.plan = plan;
    }

    public void addPlan(
            Plan plan
    ) {
        this.plan = plan;
    }
}
