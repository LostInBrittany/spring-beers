# Step 08 - Securing the application

In this step we are using Spring Securty to easily protect the create, delete and edit endpoints from non-authorized usage.

## Set up Spring Security

We are going to add the `spring-boot-starter-security` as a Gradle dependency. `spring-boot-starter-security` installs Spring Security in the application. If Spring Security is on the classpath, then Spring Boot automatically secures all HTTP endpoints with "basic" authentication. But you can further customize the security settings. 

Modify the `build.gradle` file and refresh the Gradle project.

```groovy
dependencies {
	compile 'org.springframework.boot:spring-boot-starter-web'
	compile 'org.springframework.boot:spring-boot-starter-data-mongodb'   
	compile 'org.springframework.boot:spring-boot-starter-security'
	
	// https://mvnrepository.com/artifact/com.fasterxml.jackson.core/jackson-core
	compile group: 'com.fasterxml.jackson.core', name: 'jackson-databind', version: '2.9.8'

}
```

## Adding security configuration

Now we are going to add a security configuration, `WebSecurityConfig.java` that ensures that only authenticated users can access the protected endpoints.

```java
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
	        .antMatchers("/", "/**/*.*", "/beers", "/beer/details").permitAll()
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
```

The `WebSecurityConfig` class is annotated with `@EnableWebSecurity` to enable Spring Security’s web security support and provide the Spring MVC integration. It also extends `WebSecurityConfigurerAdapter` and overrides a couple of its methods to set some specifics of the web security configuration.

The `configure(HttpSecurity)` method defines which URL paths should be secured and which should not. 

As for the `userDetailsService()` method, it sets up an in-memory user store with a single user. That user is given a username of "user", a password of "password", and a role of "USER".


## Create a login page

Now we need to create the login page. We are doing simple, so we create a `login.html` file inside `public`:

```html
<!DOCTYPE html>
<html>
  <head>
    <title>Spring Security Example login form</title>
  </head>

	<body>
    <form action="/login" method="post">
      <div><label> User Name : <input type="text" name="username"/> </label></div>
      <div><label> Password: <input type="password" name="password"/> </label></div>
      <div><input type="submit" value="Sign In"/></div>
    </form>
  </body>
</html>
```

And an `error.html` to manage login errors:

```html
<!DOCTYPE html>
<html>
	<head>
		  <title>Spring Security Example login form</title>
	</head>

	<body>
	  <p>Incorrect user name and/or password, please try again</p>
    <form action="/login" method="post">
      <div><label> User Name : <input type="text" name="username"/> </label></div>
      <div><label> Password: <input type="password" name="password"/> </label></div>
      <div><input type="submit" value="Sign In"/></div>
    </form>
  </body>
</html>
```


And now it should work...
