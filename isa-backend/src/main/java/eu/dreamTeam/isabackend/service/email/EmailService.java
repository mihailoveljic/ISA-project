package eu.dreamTeam.isabackend.service.email;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.transaction.Transactional;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.Base64;

@Component
@Slf4j
public class EmailService {

    @Autowired
    private JavaMailSender emailSender;
    @Transactional
    public void sendMessageWithAttachment(String to, String subject, String text, byte[] attachment) throws Exception {

        MimeMessage message = emailSender.createMimeMessage();

        MimeMessageHelper helper = new MimeMessageHelper(message, true);

        helper.setFrom("svenadev@gmail.com");
        helper.setTo(to);
        helper.setSubject(subject);
        helper.setText(text);

        File file = new File("QR_CODE.png");

        OutputStream os = new FileOutputStream(file);
        os.write(attachment);
        os.close();

        FileSystemResource resource = new FileSystemResource(file);
        helper.addAttachment("QR_CODE.png", file);


        emailSender.send(message);
        log.info("email sent.");
    }
}