package pl.reaktor.controller;

import java.util.Collection;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

import pl.reaktor.model.User;
import pl.reaktor.service.UserService;

@Controller
public class LoginController {
	private UserService userService;

	public LoginController(@Autowired UserService userService) {
		this.userService = userService;
	}

	@GetMapping("/")
	public String index(Model model) {

		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (auth != null && auth.isAuthenticated() && !"anonymousUser".equals(auth.getName())) {
			model.addAttribute("isLogin", true);
			model.addAttribute("userEmail", auth.getName());

			Collection<? extends GrantedAuthority> authorities = auth.getAuthorities();
			for (GrantedAuthority grantedAuthority : authorities) {
				 if("admin".equalsIgnoreCase(grantedAuthority.getAuthority())) {
					 model.addAttribute("isAdmin", true);
				 }else {
					 model.addAttribute("isAdmin", false);
				 }

			}
		}

		return "index";
	}

	@GetMapping("/login")
	public String login() {
		return "login";
	}

	@GetMapping("/registration")
	public String registration(Model model) {
		model.addAttribute("user", new User());
		return "registration";
	}

	@PostMapping("/registration")
	// odbieramy dane wpisane z formularza
	public String registration(@Valid @ModelAttribute User user, BindingResult result, Model model) {
		if (result.hasErrors()) {
			return "registration";
		}

		if (!userService.isNewUserExists(user.getEmail())) {
			result.rejectValue("email", "error.user.exist", "User already exists");
			return "registration";
		}

		// zapis do bazy danych
		User savedUser = userService.addUserWithRoleUser(user);
		model.addAttribute("successMessage", "User has been registrated successfully");
		model.addAttribute("user", new User());
		return "registration";

	}

	@GetMapping("/about")
	public String about() {
		return "about";
	}

	@GetMapping("/logout")
	public String logout() {
		return "logout";
	}

}
