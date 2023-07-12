package africa.semicolon.RealtyHub.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
@Getter
@Setter
@Entity(name= "houseDetail")
@AllArgsConstructor
@NoArgsConstructor
@Table
public class HouseDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(
            name = "id",
            updatable = false
    )
    private Long id;
    private String numberOfBedroom;
    private BigDecimal price;
    private String imageUpload;
    private HouseStatus houseStatus;

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("HouseDetail{");
        sb.append("id=").append(id);
        sb.append(", numberOfBedroom='").append(numberOfBedroom).append('\'');
        sb.append(", price=").append(price);
        sb.append(", imageUpload='").append(imageUpload).append('\'');
        sb.append(", houseStatus=").append(houseStatus);
        sb.append('}');
        return sb.toString();
    }
}
