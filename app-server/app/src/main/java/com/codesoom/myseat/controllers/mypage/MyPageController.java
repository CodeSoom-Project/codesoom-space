package com.codesoom.myseat.controllers.mypage;

import com.codesoom.myseat.domain.User;
import com.codesoom.myseat.dto.MyPageResponse;
import com.codesoom.myseat.exceptions.UserNotFoundException;
import com.codesoom.myseat.security.UserAuthentication;
import com.codesoom.myseat.services.users.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/** 마이페이지 컨트롤러 */
@RestController
@RequestMapping("/mypage")
@CrossOrigin
@Slf4j
public class MyPageController {
    
    private final UserService userService;

    public MyPageController(
            UserService userService
    ) {
        this.userService = userService;
    }

    /**
     * 회원 조회에 성공하면 상태 코드 200과 함께 응답 정보를 반환합니다.
     * 
     * @param principal 인증 정보
     * @return 회원 정보
     * @throws UserNotFoundException 회원 조회에 실패하면 던집니다.
     */
    @PreAuthorize("isAuthenticated()")
    @ResponseStatus(HttpStatus.OK)
    @GetMapping
    public MyPageResponse myPage(
            @AuthenticationPrincipal UserAuthentication principal
    ) {
        User user = userService.findById(principal.getId());

        return toResponse(user);
    }

    private MyPageResponse toResponse(
            User user
    ) {
        return MyPageResponse.builder()
                .name(user.getName())
                .email(user.getEmail())
                .build();
    }
    
}
