package africa.semicolon.RealtyHub.repositories;

import africa.semicolon.RealtyHub.models.BioData;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BioDataRepository extends JpaRepository<BioData, Long> {
    Optional<BioData> findByEmail(String email);
}
