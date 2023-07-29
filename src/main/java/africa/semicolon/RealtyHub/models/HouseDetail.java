package africa.semicolon.RealtyHub.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity(name = "houseDetail")
@AllArgsConstructor
@NoArgsConstructor
@Table
public class HouseDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", updatable = false)
    private Long id;
    private String numberOfBedroom;
    private BigDecimal price;

    @ElementCollection
    private List<String> images = new ArrayList<>();

    @Enumerated(EnumType.STRING)
    private HouseStatus houseStatus;

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("HouseDetail{");
        sb.append("id=").append(id);
        sb.append(", numberOfBedroom='").append(numberOfBedroom).append('\'');
        sb.append(", price=").append(price);
        sb.append(", images=").append(images);
        sb.append(", houseStatus=").append(houseStatus);
        sb.append('}');
        return sb.toString();
    }
}
