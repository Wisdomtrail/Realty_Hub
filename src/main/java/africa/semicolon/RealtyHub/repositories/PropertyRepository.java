package africa.semicolon.RealtyHub.repositories;

import africa.semicolon.RealtyHub.models.Property;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PropertyRepository extends JpaRepository<Property, Long> {
}
