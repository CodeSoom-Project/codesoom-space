package com.codesoom.myseat.dto;

import com.codesoom.myseat.domain.User;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * 예약 정보에 포함되는 사용자 정보
 */
@Getter
@NoArgsConstructor
public class UserResponse {

    private Long id;
    private String name;

    public UserResponse(User user) {
        this.id = user.getId();
        this.name = user.getName();
    }

}
