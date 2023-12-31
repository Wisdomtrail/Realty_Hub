package africa.semicolon.RealtyHub.dtos.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserResponse {
    private Long id;
    private String name;
    private String email;

    private String profileImage;
}
