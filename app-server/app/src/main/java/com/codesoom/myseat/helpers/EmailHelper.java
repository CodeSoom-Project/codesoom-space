package com.codesoom.myseat.helpers;

import com.sendgrid.Method;
import com.sendgrid.Request;
import com.sendgrid.Response;
import com.sendgrid.SendGrid;
import com.sendgrid.helpers.mail.Mail;
import com.sendgrid.helpers.mail.objects.Content;
import com.sendgrid.helpers.mail.objects.Email;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;

/** 이메일 전송 헬퍼 */
@Component
public class EmailHelper {

    @Value("${email.api-key}")
    private static String API_KEY;

    @Value("${email.from}")
    private static String FROM;

    /**
     * 인증 이메일을 전송합니다.
     *
     * @throws IOException
     */
    public void send(
            final String sendTo,
            final String subject,
            final String content
    ) throws IOException {
        Mail mail = new Mail(
                new Email(FROM),
                subject,
                new Email(sendTo),
                new Content("text/plain", content)
        );

        SendGrid sg = new SendGrid(API_KEY);
        Request request = new Request();
        try {
            request.setMethod(Method.POST);
            request.setEndpoint("mail/send");
            request.setBody(mail.build());
            Response response = sg.api(request);
            System.out.println(response.getStatusCode());
            System.out.println(response.getBody());
            System.out.println(response.getHeaders());
        } catch (IOException ex) {
            throw ex;
        }
    }

} 
