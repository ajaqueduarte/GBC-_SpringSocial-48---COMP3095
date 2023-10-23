package ca.gbc.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ca.gbc.user.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
}
