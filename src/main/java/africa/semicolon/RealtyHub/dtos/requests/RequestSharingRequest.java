package africa.semicolon.RealtyHub.dtos.requests;

import africa.semicolon.RealtyHub.models.User;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class RequestSharingRequest {

    private User RequestingUser;
    private User requestedUser;
}
