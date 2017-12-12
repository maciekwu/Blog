package pl.reaktor.security;

import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private DataSource dataSource;

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.jdbcAuthentication().usersByUsernameQuery("select email, password, active from user where email = ?")
				.authoritiesByUsernameQuery(
						"select u.email, r.name from user u\r\n" + "join user_role ur on ur.user_id = u.id\r\n"
								+ "join role r on r.id = ur.roles_id \r\n" + "where u.email = ?")
				.dataSource(dataSource);

	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests().antMatchers("/admin/*").hasAuthority("admin").anyRequest().permitAll().and()
				.formLogin().loginPage("/login").failureUrl("/login?error=true").defaultSuccessUrl("/")
				.usernameParameter("email").passwordParameter("password").and().logout().logoutUrl("/logout")
				.logoutSuccessUrl("/");

	}

}
