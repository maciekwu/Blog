package pl.reaktor.model;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

@Entity
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Email(message = "{pl.reaktor.model.user.email.email}")
	@NotEmpty(message = "{pl.reaktor.model.user.notEmpty}") 
	private String email;

	@Length(min = 5, message = "{pl.reaktor.model.user.password.length}")
	private String password;
	
	@NotEmpty(message = "{pl.reaktor.model.user.notEmpty}") 
	private String name;
	
	@NotEmpty(message = "{pl.reaktor.model.user.notEmpty}") 
	private String lastName;

	private boolean active;

	/*
	 * @ManyToMany * @OneToOne * @OneTomany * @ManyToOne
	 */

	@ManyToMany
	@JoinTable(name = "user_role")
	private Set<Role> roles;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}
	
	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}

	public User(String email, String password, String name, String lastName, boolean active, Set<Role> roles) {
		super();
		this.email = email;
		this.password = password;
		this.name = name;
		this.lastName = lastName;
		this.active = active;
		this.roles = roles;
	}

	public User() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", email=" + email + ", password=" + password + ", name=" + name + ", lastName="
				+ lastName + ", active=" + active + ", roles=" + roles + "]";
	}


}
