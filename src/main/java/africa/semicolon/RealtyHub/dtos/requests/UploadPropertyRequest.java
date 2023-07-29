package africa.semicolon.RealtyHub.dtos.requests;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UploadPropertyRequest {

    private Long userId; // Add the user_id field
    private String streetName;
    private String city;
    private String state;
    private String postalCode;
    private String country;
    private HouseDetailDto houseDetailDto;
}
