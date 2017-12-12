package pl.reaktor.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import pl.reaktor.model.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {
	Role findOneByRole(String role);

}
