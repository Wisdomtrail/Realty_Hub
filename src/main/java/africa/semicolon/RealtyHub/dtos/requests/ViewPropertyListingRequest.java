package africa.semicolon.RealtyHub.dtos.requests;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ViewPropertyListingRequest {

    private int page;
    private int pageSize;
}
