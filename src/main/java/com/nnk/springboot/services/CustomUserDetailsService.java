package com.nnk.springboot.services;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashSet;

@Service
public class CustomUserDetailsService implements UserDetailsService {
/*
    @Autowired
    private PersonRepository userRepository;

    /**
     * @param email the username identifying the user whose data is required.
     * @return a fully populated user record
     */
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

       /* Person person = userRepository.findByEmail(email);

        if (person == null) {
            throw new UsernameNotFoundException("Invalid username or password.");
        }

        return new User
                (
                        person.getEmail(),
                        person.getPassword(),
                        getAuthorities()
                );*/
        return null;
    }

    /**
     * @return role given at the user (there is no role implement for the moment)
     */
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return new HashSet<>();
    }

}