package pl.reaktor.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import pl.reaktor.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
	User findOneByEmail(String email);
}
