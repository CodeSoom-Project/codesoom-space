package com.codesoom.myseat.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

/** 회고 상세 조회 응답 정보 */
@Getter
@Builder
@AllArgsConstructor
public class RetrospectiveResponse {
    Long id;
    
    String content;
}
