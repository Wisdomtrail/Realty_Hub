package africa.semicolon.RealtyHub.dtos.requests;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import static africa.semicolon.RealtyHub.utils.AppUtils.NOT_BLANK;
import static africa.semicolon.RealtyHub.utils.AppUtils.NOT_NULL;


@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class InitRequest {
    @NotNull(message = NOT_NULL)
    @NotBlank(message = NOT_BLANK)
    private String email;
}
