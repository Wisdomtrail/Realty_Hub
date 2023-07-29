package africa.semicolon.RealtyHub.dtos.requests;

import africa.semicolon.RealtyHub.models.HouseDetail;
import africa.semicolon.RealtyHub.models.User;
import lombok.Data;

import java.util.List;

@Data
public class PropertyDto {
    private User user;
    private HouseDetail houseDetail;
    private String city;
    private String country;
    private List<String> images;
    private String state;
}
