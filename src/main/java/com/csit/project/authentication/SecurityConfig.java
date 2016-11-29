package com.csit.project.authentication;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private DataSource dataSource;

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {

		JdbcUserDetailsManager userDetailsService = new JdbcUserDetailsManager();
		userDetailsService.setDataSource(dataSource);
		PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

		auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder);
		auth.jdbcAuthentication().dataSource(dataSource);

		/*
		 * if(userDetailsService.userExists("user")){ List<GrantedAuthority>
		 * authorities = new ArrayList<GrantedAuthority>(); authorities.add(new
		 * SimpleGrantedAuthority("ADMIN")); User userDetails = new User(); }
		 * 
		 */

		// auth.inMemoryAuthentication().withUser("emon").password("emon").roles("ADMIN");
		// auth.jdbcAuthentication().dataSource(dataSource);
		auth.getDefaultUserDetailsService();
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		http.authorizeRequests()
			.antMatchers("/signup")
			.permitAll()
			.anyRequest()
			.authenticated();

		http.formLogin()
			.failureUrl("/?error")
			.defaultSuccessUrl("/home")
			.loginPage("/")
			.permitAll()
			.and()
			.logout()
			.logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
			.logoutSuccessUrl("/")
			.permitAll();
	}
}
