package com.nnk.springboot.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import static org.springframework.boot.autoconfigure.security.servlet.PathRequest.toH2Console;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final LoginSuccessHandler loginSuccessHandler;

    private final UserDetailsService userDetailsService;

    public SecurityConfig(UserDetailsService userDetailsService, LoginSuccessHandler loginSuccessHandler) {
        this.userDetailsService = userDetailsService;
        this.loginSuccessHandler = loginSuccessHandler;
    }

    @Bean
    public static PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests((authorize) -> //TODO a revoir avant de soumettre
                        authorize
                                .requestMatchers(PathRequest.toStaticResources().atCommonLocations()).permitAll()
                                .requestMatchers(new AntPathRequestMatcher("/bidList/**")).hasAnyAuthority("USER")
                                .requestMatchers(new AntPathRequestMatcher("/curvePoint/**")).hasAnyAuthority("USER")
                                .requestMatchers(new AntPathRequestMatcher("/rating/**")).hasAnyAuthority("USER")
                                .requestMatchers(new AntPathRequestMatcher("/ruleName/**")).hasAnyAuthority("USER")
                                .requestMatchers(new AntPathRequestMatcher("/trade/**")).hasAnyAuthority("USER")
                                .requestMatchers(new AntPathRequestMatcher("/user/**")).hasAuthority("ADMIN")
                                .requestMatchers(new AntPathRequestMatcher("/home")).permitAll()
                                //.requestMatchers(new AntPathRequestMatcher("/error")).permitAll()
                                .requestMatchers(toH2Console()).permitAll()
                                .anyRequest().permitAll()

                )
                .formLogin(
                        form -> form
                                .loginPage("/login")
                                .usernameParameter("user")
                                .passwordParameter("password")
                                .loginProcessingUrl("/login")
                                .successHandler(loginSuccessHandler)
                                .permitAll()

                ).logout(
                        logout -> logout
                                .logoutRequestMatcher(new AntPathRequestMatcher("/app-logout"))
                                .permitAll()
                                .logoutSuccessUrl("/login")

                )
                .headers(headers -> headers.frameOptions(HeadersConfigurer.FrameOptionsConfig::disable));

        return http.build();
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .userDetailsService(userDetailsService)
                .passwordEncoder(passwordEncoder());
    }

}




