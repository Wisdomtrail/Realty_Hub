package africa.semicolon.RealtyHub.services.notification.mail;

import africa.semicolon.RealtyHub.dtos.requests.EmailNotificationRequest;
import africa.semicolon.RealtyHub.dtos.requests.Recipient;
import africa.semicolon.RealtyHub.dtos.requests.Sender;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Set;

import static africa.semicolon.RealtyHub.utils.AppUtils.APP_EMAIL;
import static africa.semicolon.RealtyHub.utils.AppUtils.APP_NAME;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class SendInBlueMailServiceTest {

    @Autowired
    private MailServices mailServices;

    @Test
    public void testThatMailCanSend(){
    Sender sender = new Sender(APP_NAME, APP_EMAIL);

    Recipient recipient = new Recipient("Yinka", " ");
        EmailNotificationRequest emailNotificationRequest =new EmailNotificationRequest();
        emailNotificationRequest.setEmailSender(sender);
        emailNotificationRequest.setRecipients(Set.of(recipient));
        emailNotificationRequest.setContent("<p>Hit the ground running</p>");
        emailNotificationRequest.setSubject("Y'ello");

        var response =mailServices.sendMail(emailNotificationRequest);

        assertThat(response).isNotNull();

    }

}