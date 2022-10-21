package com.codesoom.myseat.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static org.assertj.core.api.Assertions.assertThat;

class DateTest {

    private static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd");

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
        LocalDate friday = LocalDate.parse("2022-10-21", DATE_FORMAT);
        Date ableDate = new Date(friday.plusDays(1L).format(DATE_FORMAT));
        Date notAbleDate = new Date(friday.minusDays(1L).format(DATE_FORMAT));

        assertThat(ableDate.isReservable()).isTrue();
        assertThat(notAbleDate.isReservable()).isFalse();
    }

}
