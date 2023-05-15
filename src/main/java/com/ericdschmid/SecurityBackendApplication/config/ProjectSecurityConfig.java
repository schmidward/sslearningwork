package com.ericdschmid.SecurityBackendApplication.config;

import com.ericdschmid.SecurityBackendApplication.filter.*;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.security.web.csrf.CsrfTokenRequestAttributeHandler;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;

import javax.sql.DataSource;
import java.util.Arrays;
import java.util.Collections;

@Configuration
public class  ProjectSecurityConfig {

    @Bean
    SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
        CsrfTokenRequestAttributeHandler requestHandler = new CsrfTokenRequestAttributeHandler();
        requestHandler.setCsrfRequestAttributeName("_csrf");

        // Invoking something like "/myAccount/**" means any kind of page with the root /myAccount will be secured


        //The STATELESS says "I'm going to take care of everything by myself. Don't make JSESSIONID or whatever
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
                .cors().configurationSource(new CorsConfigurationSource() {
                    @Override
                    public CorsConfiguration getCorsConfiguration(HttpServletRequest request) {
                        CorsConfiguration configuration = new CorsConfiguration();
                        configuration.setAllowedOrigins(Collections.singletonList("http://localhost:4200"));
                        configuration.setAllowedMethods(Collections.singletonList("*"));
                        configuration.setAllowCredentials(true);
                        configuration.setAllowedHeaders(Collections.singletonList("*"));
                        configuration.setExposedHeaders(Arrays.asList("Authorization")); //A header that needs to be passed to the frontend
                        configuration.setMaxAge(3600L);
                        return configuration;
                    }
                })
                .and().csrf((csrf) -> csrf.csrfTokenRequestHandler(requestHandler).ignoringRequestMatchers("/contact", "/register")
                    .csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse()))
                    .addFilterAfter(new CsrfCookieFilter(), BasicAuthenticationFilter.class)
                    // Putting in the filter and then the second parameter is what comes after it... like where you want to inject it in the chain
                    //THE MAJORITY OF PROJECTS USE ADD FILTER BEFORE OR AFTER
                    .addFilterBefore(new RequestValidationBeforeFilter(), BasicAuthenticationFilter.class)
                    .addFilterAt(new AuthoritiesLoggingAtFilter(), BasicAuthenticationFilter.class)
                    .addFilterAfter(new AuthoritiesLoggingAfterFilter(), BasicAuthenticationFilter.class)
                    .addFilterAfter(new JWTTokenGeneratorFilter(), BasicAuthenticationFilter.class)
                    .addFilterBefore(new JWTTokenValidatorFilter(), BasicAuthenticationFilter.class) // This says do this filter before BasicAuthenticationFilter
                .authorizeHttpRequests()
                    .requestMatchers("/myAccount").hasRole("USER")
                    .requestMatchers("/myBalance").hasAnyRole("USER", "ADMIN")
                    .requestMatchers("/myLoans").hasRole("USER")
                    .requestMatchers("/myCards").hasRole("USER")
                    .requestMatchers("/user").authenticated()
                    .requestMatchers("/notices", "/contact", "/register").permitAll()
                .and().formLogin()
                .and().httpBasic();
        return http.build();
    }

    //This method is MANDATORY
    //NoOpPasswordEncoder is saying this is still plain text passwords
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
