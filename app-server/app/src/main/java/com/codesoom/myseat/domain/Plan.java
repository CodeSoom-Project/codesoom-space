package com.codesoom.myseat.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.*;

import static javax.persistence.GenerationType.IDENTITY;

/**
 * 계획 엔티티
 */
@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Slf4j
public class Plan {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name="plan_id")
    private Long id;

    @Column(length = 1000)
    private String content;

    public void update(String content) {
        this.content = content;
    }
}
