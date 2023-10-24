package com.nnk.springboot.config;

import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import java.util.stream.Stream;

import static org.springframework.boot.autoconfigure.security.servlet.PathRequest.toH2Console;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final LoginSuccessHandler loginSuccessHandler;

    public SecurityConfig(LoginSuccessHandler loginSuccessHandler) {
        this.loginSuccessHandler = loginSuccessHandler;
    }

    /**
     * Method define the Password Encoder
     *
     */
    @Bean
    public static PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * Custom security filter
     *
     */
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        AntPathRequestMatcher[] authenticatedPaths = Stream.of("/bid/**", "/curvePoint/**", "/rating/**", "/trade/**", "/rule/**")
                .map(AntPathRequestMatcher::new)
                .toArray(AntPathRequestMatcher[]::new);

        http
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests((authorize) ->
                        authorize
                                .requestMatchers(PathRequest.toStaticResources().atCommonLocations()).permitAll()
                                .requestMatchers(authenticatedPaths).hasAnyAuthority("USER", "ADMIN")
                                .requestMatchers(new AntPathRequestMatcher("/user/**")).hasAuthority("ADMIN")
                                .requestMatchers(new AntPathRequestMatcher("/home")).permitAll()
                                .requestMatchers(toH2Console()).permitAll()
                                .anyRequest().authenticated()

                )
                .formLogin(
                        form -> form
                                .loginPage("/app/login")
                                .usernameParameter("user")
                                .passwordParameter("password")
                                .loginProcessingUrl("/app/login")
                                .successHandler(loginSuccessHandler)
                                .permitAll()

                ).logout(
                        logout -> logout
                                .logoutRequestMatcher(new AntPathRequestMatcher("/app-logout"))
                                .permitAll()
                                .logoutSuccessUrl("/app/login")

                )
                .headers(headers -> headers.frameOptions(HeadersConfigurer.FrameOptionsConfig::disable))
                .sessionManagement(session ->
                        session
                                .sessionFixation()
                                .migrateSession()
                                .maximumSessions(1)
                                .expiredUrl("/app/login")
                );

        return http.build();
    }
}




