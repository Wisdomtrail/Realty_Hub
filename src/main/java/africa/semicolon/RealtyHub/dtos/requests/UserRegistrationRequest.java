package africa.semicolon.RealtyHub.dtos.requests;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import static africa.semicolon.RealtyHub.utils.AppUtils.FIELD_MUST_NOT_BE_EMPTY;

@Getter
@Setter
public class UserRegistrationRequest {
    @NotNull(message = FIELD_MUST_NOT_BE_EMPTY)
    @NotEmpty(message = FIELD_MUST_NOT_BE_EMPTY)
    @Email
    @JsonProperty("email")
    private String email;
    @NotNull
    @NotEmpty
    @JsonProperty("password")
    private String password;
}
