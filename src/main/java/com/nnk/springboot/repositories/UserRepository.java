package com.nnk.springboot.repositories;

import com.nnk.springboot.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer>, JpaSpecificationExecutor<User> {

    /**
     * Method that find a user by username
     *
     * @param username the username
     * @return the user
     */
    Optional<User> findUserByUsername(String username);


}
