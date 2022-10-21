package com.codesoom.myseat.domain;

import com.codesoom.myseat.utils.DateUtil;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static org.assertj.core.api.Assertions.assertThat;

class DateTest {

    private static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    @DisplayName("오늘보다 과거이면 true를, 아니라면 false를 반환한다.")
    @Test
    void isBeforeDateTest() {
        //given
        Date before = new Date(LocalDate.now().minusDays(1L).format(DATE_FORMAT));
        Date after = new Date(LocalDate.now().plusDays(1L).format(DATE_FORMAT));
        //when & then
        assertThat(before.isBeforeDate()).isTrue();
        assertThat(after.isBeforeDate()).isFalse();
    }

    @DisplayName("주말이라면 true를, 평일이라면 false를 반환한다.")
    @Test
    void isWeekendTest() {
        //given
        Date saturday = new Date("2022-10-22");
        Date sunday = new Date("2022-10-23");
        Date friday = new Date("2022-10-21");
        //when & then
        assertThat(saturday.isWeekend()).isTrue();
        assertThat(sunday.isWeekend()).isTrue();
        assertThat(friday.isWeekend()).isFalse();
    }

}
