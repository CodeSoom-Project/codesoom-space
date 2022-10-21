package com.codesoom.myseat.utils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/** Date와 관련된 util 함수를 갖는 util 클래스 */
public class DateUtil {

    private static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    // Util 클래스의 인스턴스화를 막기 위해 private 생성자를 사용합니다.
    private DateUtil() {
    }

    /**
     * 주어진 날짜가 오늘보다 과거인지 확인합니다.
     *
     * @param date 검증하고자 하는 날짜
     * @return 오늘보다 과거라면 true, 아니라면 false
     */
    public static boolean isBeforeDate(String date) {
        return toLocalDate(date).isBefore(LocalDate.now());
    }

    /**
     * 주어진 날짜의 요일이 주말인지 확인합니다.
     *
     * @param date 검증하고자 하는 날짜
     * @return 토요일 혹은 일요일이라면 true, 아니라면 false
     */
    public static boolean isWeekend(String date) {
        return toLocalDate(date).getDayOfWeek().getValue() > 5;
    }

    /**
     * 문자열로 주어진 날짜를 LocalDate 형식으로 변환합니다.
     *
     * @param dateStr 날짜 문자열
     * @return 변환된 LocalDate 형식
     */
    private static LocalDate toLocalDate(String dateStr) {
        return LocalDate.parse(dateStr, DATE_FORMAT);
    }

}
