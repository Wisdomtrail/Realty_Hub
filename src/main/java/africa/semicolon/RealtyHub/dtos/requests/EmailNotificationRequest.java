package africa.semicolon.RealtyHub.dtos.requests;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.Set;

import static africa.semicolon.RealtyHub.utils.AppUtils.*;

@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EmailNotificationRequest {
    @JsonProperty(SENDER)
    private Sender emailSender;
    @JsonProperty(TO)
    private Set<Recipient> recipients;

    @JsonProperty(SUBJECT)
    private String subject;
    @JsonProperty(HTML_CONTENT_VALUE)
    private String content;
}
