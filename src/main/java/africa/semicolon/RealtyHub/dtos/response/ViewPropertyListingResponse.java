package africa.semicolon.RealtyHub.dtos.response;

import africa.semicolon.RealtyHub.dtos.requests.PropertyDto;
import lombok.Data;

import java.util.List;

@Data
public class ViewPropertyListingResponse {

    private List<PropertyDto> propertyDtoList;

    public void addPropertyDto(PropertyDto propertyDto) {
        propertyDtoList.add(propertyDto);
    }
}
