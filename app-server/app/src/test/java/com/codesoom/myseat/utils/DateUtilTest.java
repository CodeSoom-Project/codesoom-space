package com.codesoom.myseat.utils;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static org.assertj.core.api.Assertions.assertThat;

class DateUtilTest {

    private static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    @DisplayName("주어진 날짜가 오늘보다 과거이면 true를, 아니라면 false를 반환한다.")
    @Test
    void isBeforeDateTest() {
        //given
        String before = LocalDate.now().minusDays(1L).format(DATE_FORMAT);
        String after = LocalDate.now().plusDays(1L).format(DATE_FORMAT);
        //when & then
        assertThat(DateUtil.isBeforeDate(before)).isTrue();
        assertThat(DateUtil.isBeforeDate(after)).isFalse();
    }

    @DisplayName("주어진 날짜의 요일이 주말이라면 true를, 평일이라면 false를 반환한다.")
    @Test
    void isWeekendTest() {
        //given
        String saturday = "2022-10-22";
        String sunday = "2022-10-23";
        String friday = "2022-10-21";
        //when & then
        assertThat(DateUtil.isWeekend(saturday)).isTrue();
        assertThat(DateUtil.isWeekend(sunday)).isTrue();
        assertThat(DateUtil.isWeekend(friday)).isFalse();
    }
}
