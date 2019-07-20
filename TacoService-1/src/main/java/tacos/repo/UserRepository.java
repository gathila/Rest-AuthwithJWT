package tacos.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import tacos.domain.User;


public interface UserRepository extends JpaRepository<User, Long> {

	Optional<User> findByUsername(String username);
}
