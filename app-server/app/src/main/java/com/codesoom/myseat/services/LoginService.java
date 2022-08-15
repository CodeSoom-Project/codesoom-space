package com.codesoom.myseat.services;

import com.codesoom.myseat.domain.Token;
import com.codesoom.myseat.domain.User;
import com.codesoom.myseat.repositories.TokenRepository;
import com.codesoom.myseat.repositories.UserRepository;
import com.codesoom.myseat.dto.LoginRequest;
import com.codesoom.myseat.exceptions.LoginFailureException;
import com.codesoom.myseat.exceptions.UserNotFoundException;
import com.codesoom.myseat.utils.TokenProvider;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * 로그인 서비스
 */
@Service
public class LoginService {
    private final UserRepository userRepository;
    private final TokenProvider tokenProvider;
    private final TokenRepository tokenRepository;
    private final PasswordEncoder encoder;

    public LoginService(
            UserRepository userRepository,
            TokenProvider tokenProvider,
            TokenRepository tokenRepository,
            PasswordEncoder encoder
    ) {
        this.userRepository = userRepository;
        this.tokenProvider = tokenProvider;
        this.tokenRepository = tokenRepository;
        this.encoder = encoder;
    }

    /**
     * 로그인 후 토큰을 반환한다.
     * 
     * @param request 로그인 요청 정보
     * @return 토큰
     * @throws LoginFailureException 비밀번호 인증에 실패한 경우 예외를 던진다.
     * @throws UserNotFoundException 회원을 찾을 수 없는 경우 예외를 던진다.
     */
    public Token login(LoginRequest request) {
        User user = user(request.getEmail());
        if(!user.authenticate(request.getPassword(), encoder)) {
            throw new LoginFailureException(
                    "입력한 비밀번호 [" + request.getPassword() + "]가 회원 DB에 저장된 것과 일치하지 않습니다.");
        }

        Token token = Token.builder()
                .token(tokenProvider.token(request.getEmail()))
                .user(user)
                .build();

        user.updateToken(token);

        return tokenRepository.save(token);
    }

    /**
     * 이메일로 조회된 회원을 반환한다.
     * 
     * @param email 이메일
     * @return 회원 정보
     * @throws UserNotFoundException 회원을 찾을 수 없는 경우 예외를 던진다.
     */
    private User user(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new UserNotFoundException(
                        "[" + email + "]이 일치하는 회원을 찾을 수 없어서 조회에 실패했습니다."));
    }
}
