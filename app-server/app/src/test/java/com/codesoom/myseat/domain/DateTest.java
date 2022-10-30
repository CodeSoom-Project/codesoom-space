package com.codesoom.myseat.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static org.assertj.core.api.Assertions.assertThat;

class DateTest {

    private static final DateTimeFormatter DATE_FORMAT
            = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    @DisplayName("Date의 date 문자열이 동일하면 equals의 결과가 true이다.")
    @Test
    void equalsTest() {
        Date date1 = new Date("2022-10-22");
        Date date2 = new Date("2022-10-22");

        assertThat(date1).isEqualTo(date2);
    }

    @DisplayName("예약 가능한 날짜라면 true, 아니라면 false를 반환한다.")
    @Test
    void isReservableTest() {
        long plusDays = 0;
        LocalDate now = LocalDate.now();
        if (now.getDayOfWeek().getValue() < 6) {
            plusDays = 6 - now.getDayOfWeek().getValue();
        }
        LocalDate saturday = now.plusDays(plusDays);
        Date ableDate = new Date(saturday.format(DATE_FORMAT));
        Date notAbleDate = new Date(now.format(DATE_FORMAT));

        assertThat(ableDate.isReservable()).isTrue();
        assertThat(notAbleDate.isReservable()).isFalse();
    }

}
