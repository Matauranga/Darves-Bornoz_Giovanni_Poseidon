package com.nnk.springboot.services;

import com.nnk.springboot.repositories.UserRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Collections;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    public CustomUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * Method to returned user details
     *
     * @param username the username of the user who wishes to log in
     * @return a fully populated user record
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        return userRepository.findUserByUsername(username)
                .map(user -> new User
                        (
                                user.getUsername(),
                                user.getPassword(),
                                getAuthorities(username)
                        ))
                .orElseThrow(() -> new UsernameNotFoundException("Invalid username or password."));
    }

    /**
     * Method to get the authority of a user
     *
     * @return role given at the user (there is no role implement for the moment)
     */
    public Collection<? extends GrantedAuthority> getAuthorities(String username) {

        final String role = userRepository.findUserByUsername(username)
                .map(user -> user.getRole())
                .orElseThrow(() -> new UsernameNotFoundException("Invalid username or password."));

        var authority = new SimpleGrantedAuthority(role);

        return Collections.singleton(authority);
    }

}