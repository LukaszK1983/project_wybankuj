package pl.coderslab.projectwybankuj.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@Service
public class EmailService {

    private final static Logger logger = LogManager.getLogger(EmailService.class);
    private final static String MAIL_SENDER = "wybankuj@bbee.pl";
    private final JavaMailSender javaMailSender;

    public EmailService(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    public void send(String to, String title, String content) {
        MimeMessage mail = javaMailSender.createMimeMessage();

        try {
            MimeMessageHelper helper = new MimeMessageHelper(mail, true);
            helper.setTo(to);
            helper.setReplyTo(MAIL_SENDER);
            helper.setFrom(MAIL_SENDER);
            helper.setSubject(title);
            helper.setText(content, true);
            javaMailSender.send(mail);
            logger.info("Wiadomość została wysłana - " + title);
        } catch (MessagingException e) {
            logger.warn("Wiadomość NIE została wysłana!");
        }
    }
}
