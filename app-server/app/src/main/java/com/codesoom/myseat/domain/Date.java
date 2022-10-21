package com.codesoom.myseat.domain;

import lombok.Getter;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Date {

    private static final DateTimeFormatter DATE_FORMAT
            = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    @Getter
    private String date;

    public Date(String date) {
        this.date = date;
    }

    /**
     * 주어진 날짜가 오늘보다 과거인지 확인합니다.
     *
     * @return 오늘보다 과거라면 true, 아니라면 false
     */
    public boolean isBeforeDate() {
        return toLocalDate().isBefore(LocalDate.now());
    }

    /**
     * 주어진 날짜의 요일이 주말인지 확인합니다.
     *
     * @return 토요일 혹은 일요일이라면 true, 아니라면 false
     */
    public boolean isWeekend() {
        return toLocalDate().getDayOfWeek().getValue() > 5;
    }

    /**
     * 문자열로 주어진 날짜를 LocalDate 형식으로 변환합니다.
     *
     * @return 변환된 LocalDate 형식
     */
    private LocalDate toLocalDate() {
        return LocalDate.parse(this.date, DATE_FORMAT);
    }

}
