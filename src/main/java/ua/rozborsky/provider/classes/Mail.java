package ua.rozborsky.provider.classes;

import org.springframework.stereotype.Component;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

/**
 * Created by roman on 04.04.2017.
 */
@Component
public class Mail {
    private String address;
    private String password;
    private String SUBJECT = "provider";

    public void initAddress(String address, String password) {
        this.address = address;
        this.password = password;
    }

    public void send(String name, String surname, String email) {
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        Session session = Session.getInstance(props,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(address, password);
                    }
                });

        String text = "Hello, " + name + " " + surname + "you have not enough money to pay for TV";
        try {
            Message message = new MimeMessage(session);
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(email));
            message.setSubject(SUBJECT);
            message.setText(text);

            Transport.send(message);
        } catch (MessagingException e) {
            //do nothing
        }
    }
}
