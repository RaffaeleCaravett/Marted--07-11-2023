package com.example.blogging.config;
import java.io.IOException;
import java.util.Properties;
import java.util.Date;
import javax.mail.*;
import javax.mail.internet.*;
import com.sun.mail.smtp.*;
import org.springframework.stereotype.Component;

@Component
public class EmailSender {
/*
    public void sendRegistrationEmail(String recipient) throws IOException, MessagingException {

        Properties props = System.getProperties();
        props.put("mail.smtps.host", "smtp.mailgun.org");
        props.put("mail.smtps.auth", "true");

        Session session = Session.getInstance(props, null);
        Message msg = new MimeMessage(session);
        msg.setFrom(new InternetAddress("raffaelecaravetta13@gmail.com"));

        InternetAddress[] addrs = InternetAddress.parse(recipient, false);
        msg.setRecipients(Message.RecipientType.TO, addrs);

        msg.setSubject("Hello");
        msg.setText("Testing some Mailgun awesomness");
        msg.setSentDate(new Date());

        SMTPTransport t =
                (SMTPTransport) session.getTransport("smtps");
        t.connect("smtp.mailgun.org", "postmaster@YOUR_DOMAIN_NAME", "YOUR_SMTP_PASSWORD");
        t.sendMessage(msg, msg.getAllRecipients());

        System.out.println("Response: " + t.getLastServerResponse());

        t.close();
    }*/
}
