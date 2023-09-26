package com.nnk.springboot.services;

import com.nnk.springboot.domain.CurvePoint;
import com.nnk.springboot.exceptions.NotFoundException;
import com.nnk.springboot.repositories.CurvePointRepository;
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
class CurvePointServiceImplTest {

    @InjectMocks
    CurvePointServiceImpl curvePointService;
    @Mock
    CurvePointRepository curvePointRepository;

    @DisplayName("Try to add a CurvePoint")
    @Test
    void add() {
        //Given a curve point to add
        CurvePoint curvePoint = new CurvePoint(1, 90d, 90d);

        //When we add the curve point
        when(curvePointRepository.save(any())).thenReturn(curvePoint);
        curvePointService.add(curvePoint);

        //Then we verify if this have works correctly
        verify(curvePointRepository, times(1)).save(any());
    }

    @DisplayName("Try to get a CurvePoint by Id ")
    @Test
    void getById() {
        //Given an initial curve point
        CurvePoint curvePointToFind = new CurvePoint(2, 91d, 91d);
        Integer curvePointID = curvePointToFind.getId();

        //When we try to get the curve point
        when(curvePointRepository.findById(any())).thenReturn(Optional.of(curvePointToFind));
        CurvePoint response = curvePointService.getById(curvePointID);

        //Then we verify if this have works correctly
        verify(curvePointRepository, times(1)).findById(any());
        assertThat(response).isEqualTo(curvePointToFind);
    }

    @DisplayName("Try to get a CurvePoint by Id but throw not found exception")
    @Test
    void getEntityByIdNotFound() {
        //Given an initial curve point
        Integer curvePointID = 0;

        //When we try to get the curve point
        when(curvePointRepository.findById(any())).thenReturn(Optional.empty());
        String errMsg = assertThrows(NotFoundException.class, () -> curvePointService.getById(curvePointID)).getMessage();

        //Then we verify if this have works correctly
        verify(curvePointRepository, times(1)).findById(any());
        assertThat(errMsg).contains("Entity not found.");
    }

    @DisplayName("Try get all curvePoints")
    @Test
    void getAll() {
        //Given an initial list of curvePoint
        List<CurvePoint> curvePoints = List.of(new CurvePoint(), new CurvePoint());

        //When we try to get all curvePoints
        when(curvePointRepository.findAll()).thenReturn(curvePoints);
        List<CurvePoint> response = curvePointService.getAll();

        //Then we verify if this have works correctly
        verify(curvePointRepository, times(1)).findAll();
        assertThat(response).isNotEmpty().containsAll(curvePoints);
    }

    @DisplayName("Try to delete a curvePoint")
    @Test
    void deleteById() {
        //Given an initial curvePoint
        CurvePoint curvePointToDelete = new CurvePoint();

        //When we try to delete the curvePoint
        curvePointService.deleteById(curvePointToDelete.getId());

        //Then we verify if this have works correctly
        verify(curvePointRepository, times(1)).deleteById(any());
    }

    @DisplayName("Try to update a curvePoint ")
    @Test
    void update() {
        //Given an initial curvePoint and an update
        CurvePoint initialCurvePoint = new CurvePoint(3, 92d, 93d);
        CurvePoint updatedCurvePoint = new CurvePoint(4, 94d, 95d);
        updatedCurvePoint.setId(initialCurvePoint.getId());

        //When we try to update the curvePoint
        when(curvePointRepository.findById(any())).thenReturn(Optional.of(initialCurvePoint));
        curvePointService.update(updatedCurvePoint);

        //Then we verify if this have works correctly
        verify(curvePointRepository, times(1)).findById(any());
        verify(curvePointRepository, times(1)).save(any());
        assertThat(initialCurvePoint.getTerm()).isEqualTo(updatedCurvePoint.getTerm());
        assertThat(initialCurvePoint.getValue()).isEqualTo(updatedCurvePoint.getValue());
    }

    @DisplayName("Try to update a curvePoint not found ")
    @Test
    void updateEntityNotFound() {
        //Given an update curvePoint
        CurvePoint updatedCurvePoint = new CurvePoint();

        //When we try to update
        when(curvePointRepository.findById(any())).thenReturn(Optional.empty());
        String errMsg = assertThrows(NotFoundException.class, () -> curvePointService.update(updatedCurvePoint)).getMessage();

        //Then we verify if this have works correctly
        verify(curvePointRepository, times(1)).findById(any());
        verify(curvePointRepository, times(0)).save(any());
        assertThat(errMsg).contains("CurvePoint not found.");
    }

}