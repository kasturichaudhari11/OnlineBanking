package com.onlinebanking.icin.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.onlinebanking.icin.service.UserSecurityService;

// https://www.baeldung.com/java-config-spring-security
// https://www.baeldung.com/spring-security-login
	
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private static final String[] ANT_MATCHERS_PERMITTED = {
            "/webjars/**",
            "/css/**",
            "/js/**",
            "/images/**",
            "/",
            "/about/**",
            "/contact/**",
            "/error/**/*",
            "/console/**",
            "/signup"
    };

    @Autowired
    private UserSecurityService userSecurityService;

    
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
            .antMatchers(ANT_MATCHERS_PERMITTED)
            .permitAll()
            .anyRequest()
            .authenticated();

        http.csrf().disable().cors().disable()
            .formLogin().failureUrl("/index?error").defaultSuccessUrl("/homepage").loginPage("/index").permitAll()
            .and()
            .logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout")).logoutSuccessUrl("/index?logout").deleteCookies("remember-me").permitAll()
            .and()
            .rememberMe();
    }


    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {

    	auth.userDetailsService(userSecurityService).passwordEncoder(passwordEncoder());
    }

   //https://www.baeldung.com/spring-security-registration-password-encoding-bcrypt	    
    @Bean
    public BCryptPasswordEncoder passwordEncoder() {

    	return new BCryptPasswordEncoder();
    }
}