package com.codesoom.myseat.services.verification.email;

import com.codesoom.myseat.helpers.EmailHelper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.IOException;

/** 이메일 전송 서비스 */
@Slf4j
@Service
public class SendEmailService {

    private final EmailHelper helper;

    public SendEmailService(EmailHelper helper) {
        this.helper = helper;
    }

    /**
     * 이메일을 전송합니다.
     *
     * @param sendTo  수신인
     * @param subject 제목
     * @param content 내용
     * @throws IOException
     */
    public void sendEmail(String sendTo, String subject, String content)
            throws IOException {
        helper.send(sendTo, subject, content);
    }

}
