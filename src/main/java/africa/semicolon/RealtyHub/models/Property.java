package africa.semicolon.RealtyHub.models;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@RequiredArgsConstructor
@Entity(name = "property")
@Table
public class Property {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(
            name = "id",
            updatable = false
    )
    private Long id;
    private String streetName;
    private String city;
    private String state;
    private String postalCode;
    private String Country;
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    private LocalDateTime dateUploaded;
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private HouseDetail houseDetail;

    @ManyToOne
    @MapsId("userId")
    @JoinColumn(name = "user_id",   foreignKey = @ForeignKey(name = "user_id_fk")   )
    private RealtyHubUser user;

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Property{");
        sb.append("id=").append(id);
        sb.append(", streetName='").append(streetName).append('\'');
        sb.append(", city='").append(city).append('\'');
        sb.append(", state='").append(state).append('\'');
        sb.append(", postalCode='").append(postalCode).append('\'');
        sb.append(", Country='").append(Country).append('\'');
        sb.append(", dateUploaded=").append(dateUploaded);
        sb.append(", houseDetail=").append(houseDetail);
        sb.append(", user=").append(user);
        sb.append('}');
        return sb.toString();
    }
}
