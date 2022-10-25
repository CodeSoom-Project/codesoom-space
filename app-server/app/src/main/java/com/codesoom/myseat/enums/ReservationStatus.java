package com.codesoom.myseat.enums;

import lombok.Getter;

import java.util.Arrays;

/** 예약 상태 */
public enum ReservationStatus {

    RESERVED(0),    // 예약 완료
    CANCELED(1),    // 예약 취소
    RETROSPECTIVE_WAITING(2),   // 회고작성 전
    RETROSPECTIVE_COMPLETE(3);  // 회고작성 완료

    @Getter
    private int num;

    ReservationStatus(int num) {
        this.num = num;
    }

    public static ReservationStatus ofNum(
            Integer storedData
    ) {
        return Arrays.stream(ReservationStatus.values())
                .filter(it -> it.num == storedData)
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("올바르지 않은 상태입니다."));
    }

}
