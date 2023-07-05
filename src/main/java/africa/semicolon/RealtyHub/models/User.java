package africa.semicolon.RealtyHub.models;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity(name = "customer")
@AllArgsConstructor
@RequiredArgsConstructor
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(
            name = "id",
            updatable = false
    )
    private Long id;
    private String firstName;
    private String lastName;
    private String emailAddress;
   private List<Property> savedProperties = new ArrayList<>();

    public List<Property> getSavedProperties() {
        return savedProperties;
    }

    public void setSavedProperties(List<Property> savedProperties) {
        this.savedProperties = savedProperties;
    }
}
