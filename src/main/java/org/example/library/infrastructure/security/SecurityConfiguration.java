package org.example.library.infrastructure.security;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Slf4j
@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

    @Autowired
    private LibraryUserDetailsService libraryUserDetailsService;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


    @Bean
    public AuthenticationManager authManager() {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(libraryUserDetailsService);
        authenticationProvider.setPasswordEncoder(passwordEncoder());

        return new ProviderManager(authenticationProvider);
    }


    @Bean
    public DaoAuthenticationProvider authenticationProvider(
    ) {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(libraryUserDetailsService);
        provider.setPasswordEncoder(passwordEncoder());
        System.out.println("provider: " + provider);
        log.debug("provider {}", provider);
        return provider;
    }

    @Bean
    @ConditionalOnProperty(value = "spring.security.enabled", havingValue = "true", matchIfMissing = true)
    public SecurityFilterChain securityEnabled(HttpSecurity http) throws Exception {
        http.authenticationProvider(authenticationProvider());
        http.authenticationManager(authManager());
        http.csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers("/", "/user/register","/error").permitAll()
                        .requestMatchers("/user/**", "/loan", "/reservation", "/book", "tasks/**").hasAnyAuthority("USER", "ADMIN").anyRequest().authenticated()
                )
                .formLogin(formLogin ->
                        formLogin
                                .defaultSuccessUrl("/user/home"))
                .logout(logout -> logout
                        .logoutSuccessUrl("/")
                        .invalidateHttpSession(true)
                        .deleteCookies("JSESSIONID")
                        .permitAll());


        return http.build();
    }

    @Bean
    @ConditionalOnProperty(value = "spring.security.enabled", havingValue = "false")
    SecurityFilterChain securityDisabled(HttpSecurity http) throws Exception {
        http.csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(authorize -> authorize
                        .anyRequest()
                        .permitAll());

        return http.build();
    }
}
