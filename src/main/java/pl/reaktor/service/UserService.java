package pl.reaktor.service;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pl.reaktor.model.Role;
import pl.reaktor.model.User;
import pl.reaktor.repository.RoleRepository;
import pl.reaktor.repository.UserRepository;

@Service
public class UserService {

	private UserRepository userRepository;
	private RoleRepository roleRepository;

	@Autowired
	public UserService(UserRepository userRepository, RoleRepository roleRepository) {
		this.userRepository = userRepository;
		this.roleRepository = roleRepository;
	}

	public User addUserWithRoleUser(User user) {
		Set<Role> roles = new HashSet<>();
		
		Role role = roleRepository.findOneByRole("User");
		roles.add(role);
		user.setRoles(roles);
		user.setActive(true);
		
		// repository do fizycznego zapisu w bazie danych
				
		return userRepository.save(user);
	}
	public boolean isNewUserExists(String email) {
		return userRepository.findOneByEmail(email) == null;
		
	}
}
