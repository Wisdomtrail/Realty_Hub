package africa.semicolon.RealtyHub.services.notification.mail;

import africa.semicolon.RealtyHub.dtos.requests.EmailNotificationRequest;
import africa.semicolon.RealtyHub.dtos.response.SendMailResponse;

public interface MailServices {
    SendMailResponse sendMail(EmailNotificationRequest emailNotificationRequest);
}
