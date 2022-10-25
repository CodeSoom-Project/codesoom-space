package com.codesoom.myseat.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

/** 마이페이지 응답 정보 */
@Getter
@Builder
@AllArgsConstructor
public class MyPageResponse {
    
    String name;
    
    String email;
    
}
