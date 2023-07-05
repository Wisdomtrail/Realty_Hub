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
}
