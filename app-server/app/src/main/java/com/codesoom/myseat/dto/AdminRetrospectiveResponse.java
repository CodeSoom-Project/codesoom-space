package com.codesoom.myseat.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

/** 관리자 회고 상세 조회 응답 정보 */
@Getter
@Builder
@AllArgsConstructor
public class AdminRetrospectiveResponse {

    private Long id;

    private String content;

    @JsonFormat(shape = JsonFormat.Shape.STRING,
            pattern = "yyyy-MM-dd'T'HH:mm:ss", timezone = "Asia/Seoul")
    private LocalDateTime createdDate;
}
