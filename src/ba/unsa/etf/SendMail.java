package ba.unsa.etf;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.util.Properties;

public class SendMail implements Runnable{

    private Session newSession = null;
    private MimeMessage mimeMessage = null;
    private String recipient;
    private String date;

    public SendMail(String email, String appointment) {
        recipient = email;
        date = appointment;
    }

    @Override
    public void run() {
        Properties properties = System.getProperties();
        properties.put("mail.smtp.port", "587");
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        newSession = Session.getDefaultInstance(properties,null);

        String emailSubject = "LoveFurEver Pet Shelter Approval";
        String emailBody = "Dear friend, you have been approved to adopt the pet that you have liked. Please visit us on the selected date " + date + " to meet and hopefully adopt your companion. Greetings";
        mimeMessage = new MimeMessage(newSession);

        try {
            mimeMessage.addRecipient(Message.RecipientType.TO, new InternetAddress(recipient));
            mimeMessage.setSubject(emailSubject);
            MimeBodyPart bodyPart = new MimeBodyPart();
            bodyPart.setContent(emailBody,"text/html");
            MimeMultipart multiPart = new MimeMultipart();
            multiPart.addBodyPart(bodyPart);
            mimeMessage.setContent(multiPart);

            String fromUser = "lovefureverps@gmail.com";  //sender email id
            String fromUserPassword = "lovefurever";  //password authenticated by gmail smtp server
            String emailHost = "smtp.gmail.com";
            Transport transport = newSession.getTransport("smtp");
            transport.connect(emailHost, fromUser, fromUserPassword);
            transport.sendMessage(mimeMessage, mimeMessage.getAllRecipients());
            transport.close();
        } catch (MessagingException e) {
            e.printStackTrace();
        }

        System.out.println("Email successfully sent");
    }
}
