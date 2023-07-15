package africa.semicolon.RealtyHub.repositories;

import africa.semicolon.RealtyHub.models.RealtyHubUser;
import org.springframework.data.jpa.repository.JpaRepository;


import java.util.Optional;

public interface UserRepository extends JpaRepository<RealtyHubUser, Long> {
    Optional<RealtyHubUser> findById(Long id);

}
