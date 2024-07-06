package in.devsuman.smartcart.repository;

import in.devsuman.smartcart.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    // Used by Security [CustomUserDetailsService] to user fetch data from DB
    Optional<User> findByUsername(String username);
}
