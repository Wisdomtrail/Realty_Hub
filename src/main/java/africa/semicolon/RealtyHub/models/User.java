package africa.semicolon.RealtyHub.models;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "users")
 public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstName;
    private String lastName;
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private BioData bioData;
    private String profileImage;
    private String phoneNumber;
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    private LocalDateTime timeCreated;

    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    private LocalDateTime lastModifiedDate;
    @OneToMany(
            mappedBy = "user",
            orphanRemoval = true,
            cascade = {CascadeType.PERSIST, CascadeType.REMOVE},
            fetch = FetchType.LAZY
    )
   private List<Property> savedProperties = new ArrayList<>();
    @PrePersist
    public void setTimeCreated(){
        this.timeCreated = LocalDateTime.now();
    }

    public List<Property> getSavedProperties() {
        return savedProperties;
    }

    public void setSavedProperties(List<Property> savedProperties) {
        this.savedProperties = savedProperties;
    }



    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("User{");
        sb.append("id=").append(id);
        sb.append(", firstName='").append(firstName).append('\'');
        sb.append(", lastName='").append(lastName).append('\'');
        sb.append(", emailAddress='").append(bioData.getEmail()).append('\'');
        sb.append(", bioData=").append(bioData);
        sb.append(", profileImage='").append(profileImage).append('\'');
        sb.append(", timeCreated=").append(timeCreated);
        sb.append(", lastModifiedDate=").append(lastModifiedDate);
        sb.append(", savedProperties=").append(savedProperties);
        sb.append('}');
        return sb.toString();
    }
}
