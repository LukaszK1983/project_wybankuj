package pl.coderslab.projectwybankuj.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.mail.javamail.JavaMailSender;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.InputStream;

import static org.mockito.Matchers.any;

class EmailServiceTest {

    private JavaMailSender javaMailSenderMock = Mockito.mock(JavaMailSender.class);
    private EmailService emailService = new EmailService(javaMailSenderMock);

    @BeforeEach
    void setUp() throws MessagingException {
        MimeMessage mimeMessage = new MimeMessage(null, InputStream.nullInputStream());
        Mockito.when(javaMailSenderMock.createMimeMessage()).thenReturn(mimeMessage);
        Mockito.doNothing().when(javaMailSenderMock).send(any(MimeMessage.class));
    }

    @Test
    void send() {
        // given
        String to = "luke83@poczta.fm";
        String title = "Testowy tytuł";
        String content = "Testowa zawartość";

        // when
        emailService.send(to, title, content);

        // then
        Mockito.verify(javaMailSenderMock).send(any(MimeMessage.class));
    }
}