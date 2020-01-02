package pl.coderslab.projectwybankuj.service;

import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.util.logging.Level;
import java.util.logging.Logger;

@Service
public class EmailService {

    private final static String MAIL_SENDER = "wybankuj@bbee.pl";
    private final JavaMailSender javaMailSender;

    public EmailService(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    public void send(String to, String title, String content) throws MessagingException {
        MimeMessage mail = javaMailSender.createMimeMessage();
        Logger logger = Logger.getLogger(getClass().getName());

        MimeMessageHelper helper = new MimeMessageHelper(mail, true);
        helper.setTo(to);
        helper.setReplyTo(MAIL_SENDER);
        helper.setFrom(MAIL_SENDER);
        helper.setSubject(title);
        helper.setText(content, true);
        logger.log(Level.INFO, "Wiadomość została wysłana - " + title);
        logger.log(Level.SEVERE, "Wiadomość NIE została wysłana!");

        javaMailSender.send(mail);
    }
}
