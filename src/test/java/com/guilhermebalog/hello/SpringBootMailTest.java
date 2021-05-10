package com.guilhermebalog.hello;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import static org.junit.Assert.assertEquals;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.subethamail.wiser.Wiser;
import org.subethamail.wiser.WiserMessage;

import javax.mail.MessagingException;
import java.io.IOException;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = HelloApplication.class)
@WebAppConfiguration
public class SpringBootMailTest {
    @Autowired
    private JavaMailSender javaMailSender;

    private Wiser wiser;

    private final String userTo = "user2@localhost";
    private final String userFrom = "user1@localhost";
    private final String subject = "Test subject";
    private final String textMail = "Text subject mail";

    private SimpleMailMessage composeEmailMessage() {
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(userTo);
        mailMessage.setReplyTo(userFrom);
        mailMessage.setFrom(userFrom);
        mailMessage.setSubject(subject);
        mailMessage.setText(textMail);

        return mailMessage;
    }

    private String getMessage(WiserMessage wiserMessage) throws MessagingException, IOException {
        return wiserMessage.getMimeMessage().getContent().toString().trim();
    }

    private String getSubject(WiserMessage wiserMessage) throws MessagingException {
        return wiserMessage.getMimeMessage().getSubject();
    }

    @Before
    public void setUp() throws Exception {
        final int TEST_PORT = 3333;
        wiser = new Wiser(TEST_PORT);
        wiser.start();
    }

    @After
    public void tearDown() throws Exception {
        wiser.stop();
    }

    @Test
    public void givenMail_whenSendReceived_thenCorrect() throws Exception {
        SimpleMailMessage message = composeEmailMessage();
        javaMailSender.send(message);
        List<WiserMessage> messages = wiser.getMessages();

        assertEquals(1, messages.size());
        WiserMessage wiserMessage = messages.get(0);

        assertEquals(userFrom, wiserMessage.getEnvelopeSender());
        assertEquals(userTo, wiserMessage.getEnvelopeReceiver());
        assertEquals(subject, getSubject(wiserMessage));
        assertEquals(textMail, getMessage(wiserMessage));
    }
}
