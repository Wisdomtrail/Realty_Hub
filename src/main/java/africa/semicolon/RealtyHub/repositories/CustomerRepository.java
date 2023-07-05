package africa.semicolon.RealtyHub.repositories;

import africa.semicolon.RealtyHub.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<User, String> {
}
