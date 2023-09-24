package com.nnk.springboot.services;

import com.nnk.springboot.domain.Rating;
import com.nnk.springboot.exceptions.NotFoundException;
import com.nnk.springboot.repositories.RatingRepository;
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
class RatingServiceImplTest {

    @InjectMocks
    RatingServiceImpl ratingService;
    @Mock
    RatingRepository ratingRepository;

    @DisplayName("Try to add a rating")
    @Test
    void add() {
        //Given a rating to add
        Rating rating = new Rating("Moodys Test", "SandP Test", "Fitch Test", 100);

        //When we try to add the rating
        when(ratingRepository.save(any())).thenReturn(rating);
        ratingService.add(rating);

        //Then we verify if this have works correctly
        verify(ratingRepository, times(1)).save(any());
    }

    @DisplayName("Try to get a rating by Id ")
    @Test
    void getById() {
        //Given an initial rating
        Rating ratingToFind = new Rating("Moodys Test", "SandP Test", "Fitch Test", 100);
        Integer ratingId = ratingToFind.getId();

        //When we try to get the rating
        when(ratingRepository.findById(any())).thenReturn(Optional.of(ratingToFind));
        Rating response = ratingService.getById(ratingId);

        //Then we verify if this have works correctly
        verify(ratingRepository, times(1)).findById(any());
        assertThat(response).isEqualTo(ratingToFind);
    }

    @DisplayName("Try to get a rating by Id but throw not found exception")
    @Test
    void getEntityByIdNotFound() {
        //Given an initial rating
        Integer ratingId = 0;

        //When we try to get the rating
        when(ratingRepository.findById(any())).thenReturn(Optional.empty());
        String errMsg = assertThrows(NotFoundException.class, () -> ratingService.getById(ratingId)).getMessage();

        //Then we verify if this have works correctly
        verify(ratingRepository, times(1)).findById(any());
        assertThat(errMsg).contains("Entity not found.");
    }

    @DisplayName("Try get all ratings")
    @Test
    void getAll() {
        //Given an initial list of ratings
        List<Rating> ratings = List.of(new Rating(), new Rating());

        //When we try to get all ratings
        when(ratingRepository.findAll()).thenReturn(ratings);
        List<Rating> response = ratingService.getAll();

        //Then we verify if this have works correctly
        verify(ratingRepository, times(1)).findAll();
        assertThat(response).isNotEmpty().containsAll(ratings);
    }

    @DisplayName("Try to delete a rating")
    @Test
    void deleteById() {
        //Given an initial rating
        Rating ratingToDelete = new Rating("Moodys Test", "SandP Test", "Fitch Test", 200);

        //When we try to delete the rating
        ratingService.deleteById(ratingToDelete.getId());

        //Then we verify if this have works correctly
        verify(ratingRepository, times(1)).deleteById(any());

    }

    @DisplayName("Try to update a rating")
    @Test
    void update() {
        //Given an initial rating and an update
        Rating initialRating = new Rating("Moodys Initial Test", "SandP Initial Test", "Fitch Initial Test", 300);
        Rating updatedRating = new Rating("Moodys Updated Test", "SandP Updated Test", "Fitch Updated Test", 400);
        updatedRating.setId(initialRating.getId());

        //When we try to update rating
        when(ratingRepository.findById(any())).thenReturn(Optional.of(initialRating));
        ratingService.update(updatedRating);

        //Then we verify if this have works correctly
        verify(ratingRepository, times(1)).findById(any());
        verify(ratingRepository, times(1)).save(any());
        assertThat(initialRating.getMoodysRating()).isEqualTo(updatedRating.getMoodysRating());
        assertThat(initialRating.getOrderNumber()).isEqualTo(updatedRating.getOrderNumber());
    }

    @DisplayName("Try to update a rating not found ")
    @Test
    void updateEntityNotFound() {
        //Given an initial rating
        Rating updatedRating = new Rating();

        //When we try to update
        when(ratingRepository.findById(any())).thenReturn(Optional.empty());
        String errMsg = assertThrows(NotFoundException.class, () -> ratingService.update(updatedRating)).getMessage();

        //Then we verify if this have works correctly
        verify(ratingRepository, times(1)).findById(any());
        verify(ratingRepository, times(0)).save(any());
        assertThat(errMsg).contains("Rating not found.");
    }


}