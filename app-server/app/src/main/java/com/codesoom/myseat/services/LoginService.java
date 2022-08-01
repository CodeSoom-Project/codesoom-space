package com.codesoom.myseat.services;

import com.codesoom.myseat.domain.User;
import com.codesoom.myseat.domain.UserRepository;
import com.codesoom.myseat.dto.LoginRequest;
import com.codesoom.myseat.exceptions.LoginFailureException;
import com.codesoom.myseat.exceptions.UserNotFoundException;
import com.codesoom.myseat.utils.TokenProvider;
import org.springframework.stereotype.Service;

/**
 * 로그인 서비스
 */
@Service
public class LoginService {
    private final UserRepository repository;
    private final TokenProvider tokenProvider;

    public LoginService(UserRepository repository, TokenProvider tokenProvider) {
        this.repository = repository;
        this.tokenProvider = tokenProvider;
    }

    /**
     * 로그인 후 토큰을 반환한다.
     * @param request 로그인 요청 정보
     * @return 토큰
     * @throws LoginFailureException 비밀번호 인증에 실패한 경우 예외를 던진다.
     * @throws UserNotFoundException 회원을 찾을 수 없는 경우 예외를 던진다.
     */
    public String login(LoginRequest request) {
        // TODO: 인증
        //  - 로그인 dto의 비밀번호가 회원 정보와 일치하는지 확인
        //  - 일치하면 유효한 토큰 반환
        //  - 일치하지 않으면 예외 던짐
        User user = user(request.getEmail());
        
        if(!user.authenticate(request.getPassword())) { // 비밀번호 인증 실패하면
            throw new LoginFailureException(
                    "입력한 비밀번호 [" + request.getPassword() + "]가 회원 DB에 저장된 것과 일치하지 않습니다.");
        }
        
        return tokenProvider.token(request.getEmail()); // 토큰 반환
    }

    /**
     * 이메일로 조회된 회원을 반환한다.
     * 
     * @param email 이메일
     * @return 회원 정보
     * @throws UserNotFoundException 회원을 찾을 수 없는 경우 예외를 던진다.
     */
    private User user(String email) {
        return repository.findByEmail(email)
                .orElseThrow(() -> new UserNotFoundException(
                        "[" + email + "]이 일치하는 회원을 찾을 수 없어서 조회에 실패했습니다."));
    }
}
