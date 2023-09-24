package com.nnk.springboot.services;

import com.nnk.springboot.domain.User;
import com.nnk.springboot.exceptions.NotFoundException;
import com.nnk.springboot.repositories.UserRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@SpringBootTest
class UserServiceImplTest {

    @InjectMocks
    UserServiceImpl userService;
    @Mock
    UserRepository userRepository;

    @DisplayName("Try to add a User ")
    @Test
    void add() {
        //Given a User
        User user = new User("UserNameTest", "TEST", "FullNameTest", "USER");

        //When we try to add the User
        when(userRepository.save(any())).thenReturn(user);
        userService.add(user);

        //Then we verify if this have works correctly
        verify(userRepository, times(1)).save(any());
    }

    @DisplayName("Try to get an User by Id")
    @Test
    void getById() {
        //Given an initial User
        User userToFind = new User("UserNameTest", "TEST", "FullNameTest", "USER");
        Integer userId = userToFind.getId();

        //When we try to get the User
        when(userRepository.findById(any())).thenReturn(Optional.of(userToFind));
        User response = userService.getById(userId);

        //Then we verify if this have works correctly
        verify(userRepository, times(1)).findById(any());
        assertThat(response).isEqualTo(userToFind);
    }

    @DisplayName("Try to get a User by Id but throw not found exception")
    @Test
    void getEntityByIdNotFound() {
        //Given an initial User
        Integer userId = 0;

        //When we try to get the User
        when(userRepository.findById(any())).thenReturn(Optional.empty());
        String errMsg = assertThrows(NotFoundException.class, () -> userService.getById(userId)).getMessage();

        //Then we verify if this have works correctly
        verify(userRepository, times(1)).findById(any());
        assertThat(errMsg).contains("Entity not found.");
    }

    @DisplayName("Try get all Users")
    @Test
    void getAll() {
        //Given an initial list of Users
        List<User> users = List.of(new User(), new User());

        //When we try to get all Users
        when(userRepository.findAll()).thenReturn(users);
        List<User> response = userService.getAll();

        //Then we verify if this have works correctly
        verify(userRepository, times(1)).findAll();
        assertThat(response).isNotEmpty().containsAll(users);
    }

    @DisplayName("Try to delete a User")
    @Test
    void deleteById() {
        //Given an initial User
        User userToDelete = new User("UserNameTest", "TEST", "FullNameTest", "USER");

        //When we try to delete the User
        userService.deleteById(userToDelete.getId());

        //Then we verify if this have works correctly
        verify(userRepository, times(1)).deleteById(any());
    }

    @DisplayName("Try to update User")
    @Test
    void update() {
        //Given an initial User and an update
        User initialUser = new User("Initial UserNameTest", "TEST", "Initial FullNameTest", "USER");
        User updatedUser = new User("Updated UserNameTest", "TEST", "Updated FullNameTest", "ADMIN");
        updatedUser.setId(initialUser.getId());

        //When we try to update User
        when(userRepository.findById(any())).thenReturn(Optional.of(initialUser));
        userService.update(updatedUser);

        //Then we verify if this have works correctly
        verify(userRepository, times(1)).findById(any());
        verify(userRepository, times(1)).save(any());
        assertThat(initialUser.getUsername()).isEqualTo(updatedUser.getUsername());
        assertThat(initialUser.getRole()).isEqualTo(updatedUser.getRole());
    }

    @DisplayName("Try to update a User not found ")
    @Test
    void updateEntityNotFound() {
        //Given an initial User
        User updatedUser = new User();

        //When we try to update
        when(userRepository.findById(any())).thenReturn(Optional.empty());
        String errMsg = assertThrows(NotFoundException.class, () -> userService.update(updatedUser)).getMessage();

        //Then we verify if this have works correctly
        verify(userRepository, times(1)).findById(any());
        verify(userRepository, times(0)).save(any());
        assertThat(errMsg).contains("User not found.");
    }

}