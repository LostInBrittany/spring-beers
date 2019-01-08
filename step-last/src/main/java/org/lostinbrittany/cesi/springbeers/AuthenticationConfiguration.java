package org.lostinbrittany.cesi.springbeers;

import org.lostinbrittany.cesi.springbeers.services.CustomMongoSecurityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configurers.GlobalAuthenticationConfigurerAdapter;

@Configuration
public class AuthenticationConfiguration extends GlobalAuthenticationConfigurerAdapter {

    @Autowired
    public CustomMongoSecurityService mongoSecurityService;

    @Override
    public void init(AuthenticationManagerBuilder auth) throws Exception {
        auth
           // .userDetailsService(mongoSecurityService).and()
            .inMemoryAuthentication()
            .withUser("user").password("password").roles("USER").and()
            .withUser("admin").password("1234").roles("ADMIN");
    }

}
