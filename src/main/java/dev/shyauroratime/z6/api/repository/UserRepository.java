package dev.shyauroratime.z6.api.repository;

import dev.shyauroratime.z6.api.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository  extends JpaRepository<User, Long> {
    Optional<User> findByUserAccount(String userAccount);
}
