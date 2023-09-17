package com.nnk.springboot.config;

import org.springframework.beans.factory.annotation.Autowired;
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

    private final UserDetailsService userDetailsService;

    public SecurityConfig(UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @Bean
    public static PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests((authorize) ->
                        authorize.requestMatchers(new AntPathRequestMatcher("/bidList/**")).hasAnyAuthority("USER", "ADMIN")
                                .requestMatchers(new AntPathRequestMatcher("/curvePoint/**")).hasAnyAuthority("USER", "ADMIN")
                                .requestMatchers(new AntPathRequestMatcher("/rating/**")).hasAnyAuthority("USER", "ADMIN")
                                .requestMatchers(new AntPathRequestMatcher("/ruleName/**")).hasAnyAuthority("USER", "ADMIN")
                                .requestMatchers(new AntPathRequestMatcher("/trade/**")).hasAnyAuthority("USER", "ADMIN")
                                .requestMatchers(new AntPathRequestMatcher("/user/**")).hasAuthority("ADMIN")
                                .requestMatchers(new AntPathRequestMatcher("/home")).permitAll()
                                .requestMatchers(toH2Console()).permitAll()
                                .anyRequest().authenticated()
                )
                .formLogin(
                        form -> form
                                .loginPage("/login")
                                .usernameParameter("user")
                                .passwordParameter("password")
                                .loginProcessingUrl("/login")
                                .defaultSuccessUrl("/bidList/list", true)
                                .permitAll()

                ).logout(
                        logout -> logout
                                .logoutRequestMatcher(new AntPathRequestMatcher("/app-logout"))
                                .permitAll()
                                .logoutSuccessUrl("/home")
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




