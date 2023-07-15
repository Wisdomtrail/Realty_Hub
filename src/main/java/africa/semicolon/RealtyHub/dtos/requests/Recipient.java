package africa.semicolon.RealtyHub.dtos.requests;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Recipient {
    private final String name;
    private final String email;
}
