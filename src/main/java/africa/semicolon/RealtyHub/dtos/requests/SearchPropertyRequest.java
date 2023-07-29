package africa.semicolon.RealtyHub.dtos.requests;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Setter
@Getter
public class SearchPropertyRequest {

    private BigDecimal price;
    private String state;
}
