package com.codesoom.myseat.domain;

import lombok.Getter;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

public class Date {

    private static final DateTimeFormatter DATE_FORMAT
            = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    @Getter
    private String date;

    public Date(String date) {
        this.date = date;
    }

    /**
     * 예약 가능한 날짜인지 여부를 확인합니다.
     *
     * @return 예약이 가능한 날짜라면 true, 아니라면 false
     */
    public boolean isReservable() {
        return !isBeforeDate() && !isNow() && isWeekend();
    }

    /**
     * 날짜가 오늘보다 과거인지 확인합니다.
     *
     * @return 오늘보다 과거라면 true, 아니라면 false
     */
    private boolean isBeforeDate() {
        return toLocalDate().isBefore(LocalDate.now());
    }

    /**
     * 날짜의 요일이 주말인지 확인합니다.
     *
     * @return 토요일 혹은 일요일이라면 true, 아니라면 false
     */
    private boolean isWeekend() {
        return toLocalDate().getDayOfWeek().getValue() > 5;
    }

    /**
     * 날짜가 오늘 날짜와 동일한지 확인합니다.
     *
     * @return 오늘 날짜와 동일하면 true, 아니라면 false
     */
    private boolean isNow() {
        return toLocalDate().equals(LocalDate.now());
    }

    /**
     * 문자열로 주어진 날짜를 LocalDate 형식으로 변환합니다.
     *
     * @return 변환된 LocalDate 형식
     */
    private LocalDate toLocalDate() {
        return LocalDate.parse(this.date, DATE_FORMAT);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Date date1 = (Date) o;
        return Objects.equals(date, date1.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(date);
    }

}
