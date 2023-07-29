package africa.semicolon.RealtyHub.dtos.requests;

import africa.semicolon.RealtyHub.models.HouseStatus;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class HouseDetailDto {

    private String numberOfBedroom;
    private BigDecimal price;
    private List<String> images = new ArrayList<>();
    private HouseStatus houseStatus;

}
