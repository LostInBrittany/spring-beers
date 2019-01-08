package org.lostinbrittany.cesi.springbeers;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
		.csrf().disable()
        .authorizeRequests()
	    	.antMatchers("/create", "/delete/", "/delete/*", "/edit").authenticated()
	        .antMatchers("/", "/**/*.*", "/beer/list", "/beer/details").permitAll()
	        .anyRequest().authenticated()
        .and()
        .formLogin()
	        .loginPage("/login.html")
	        .defaultSuccessUrl("/index.html", true)
	        .failureUrl("/error.html")
	        .loginProcessingUrl("/login")
	        .permitAll()
        .and()
		.logout()
				.permitAll();
    }
    @Bean
    @Override
    public UserDetailsService userDetailsService() {
        UserDetails user =
             User.withDefaultPasswordEncoder()
                .username("user")
                .password("password")
                .roles("USER")
                .build();

        return new InMemoryUserDetailsManager(user);
    }
}