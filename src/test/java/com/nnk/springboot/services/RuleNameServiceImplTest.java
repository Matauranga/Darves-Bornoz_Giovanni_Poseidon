package com.nnk.springboot.services;

import com.nnk.springboot.domain.RuleName;
import com.nnk.springboot.exceptions.NotFoundException;
import com.nnk.springboot.repositories.RuleNameRepository;
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
class RuleNameServiceImplTest {

    @InjectMocks
    RuleNameServiceImpl ruleNameService;
    @Mock
    RuleNameRepository ruleNameRepository;

    @DisplayName("Try to add RuleName")
    @Test
    void add() {
        //Given a RuleName to add
        RuleName ruleName = new RuleName("RuleName Test", "RuleName Test", "Json Test", "Template Test", "SQL Str Test", "SQL Part Test");

        //When we try to add the RuleName
        when(ruleNameRepository.save(any())).thenReturn(ruleName);
        ruleNameService.add(ruleName);

        //Then we verify if this have works correctly
        verify(ruleNameRepository, times(1)).save(any());
    }

    @DisplayName("Try to get RuleName by Id")
    @Test
    void getById() {
        //Given an initial RuleName
        RuleName ruleNameToFind = new RuleName("RuleName Test", "RuleName Test", "Json Test", "Template Test", "SQL Str Test", "SQL Part Test");
        Integer ruleNameId = ruleNameToFind.getId();

        //When we try to get the RuleName
        when(ruleNameRepository.findById(any())).thenReturn(Optional.of(ruleNameToFind));
        RuleName response = ruleNameService.getById(ruleNameId);

        //Then we verify if this have works correctly
        verify(ruleNameRepository, times(1)).findById(any());
        assertThat(response).isEqualTo(ruleNameToFind);
    }

    @DisplayName("Try to get a RuleName by Id but throw not found exception")
    @Test
    void getEntityByIdNotFound() {
        //Given an initial RuleName
        Integer ruleNameId = 0;

        //When we try to get the RuleName
        when(ruleNameRepository.findById(any())).thenReturn(Optional.empty());
        String errMsg = assertThrows(NotFoundException.class, () -> ruleNameService.getById(ruleNameId)).getMessage();

        //Then we verify if this have works correctly
        verify(ruleNameRepository, times(1)).findById(any());
        assertThat(errMsg).contains("Entity not found.");
    }

    @DisplayName("Try get all RuleNames")
    @Test
    void getAll() {
        //Given an initial list of RuleNames
        List<RuleName> ruleNames = List.of(new RuleName(), new RuleName());

        //When we try to get all RuleNames
        when(ruleNameRepository.findAll()).thenReturn(ruleNames);
        List<RuleName> response = ruleNameService.getAll();

        //Then we verify if this have works correctly
        verify(ruleNameRepository, times(1)).findAll();
        assertThat(response).isNotEmpty().containsAll(ruleNames);

    }

    @DisplayName("Try to delete a RuleName")
    @Test
    void deleteById() {
        //Given an initial RuleName
        RuleName ruleNameToDelete = new RuleName("RuleName Test", "RuleName Test", "Json Test", "Template Test", "SQL Str Test", "SQL Part Test");

        //When we try to delete the RuleName
        ruleNameService.deleteById(ruleNameToDelete.getId());

        //Then we verify if this have works correctly
        verify(ruleNameRepository, times(1)).deleteById(any());
    }

    @DisplayName("Try to update RuleName")
    @Test
    void update() {
        //Given an initial RuleName and an update
        RuleName initialRuleName = new RuleName("Initial RuleName Test", "Initial RuleName Test", "Initial Json Test", "Initial Template Test", "Initial SQL Str Test", "Initial SQL Part Test");
        RuleName updatedRuleName = new RuleName("Updated RuleName Test", "Updated RuleName Test", "Updated Json Test", "Updated Template Test", "Updated SQL Str Test", "Updated SQL Part Test");
        updatedRuleName.setId(initialRuleName.getId());

        //When we try to update the RuleName
        when(ruleNameRepository.findById(any())).thenReturn(Optional.of(initialRuleName));
        ruleNameService.update(updatedRuleName);

        //Then we verify if this have works correctly
        verify(ruleNameRepository, times(1)).findById(any());
        verify(ruleNameRepository, times(1)).save(any());
        assertThat(initialRuleName.getName()).isEqualTo(updatedRuleName.getName());
        assertThat(initialRuleName.getSqlPart()).isEqualTo(updatedRuleName.getSqlPart());
    }

    @DisplayName("Try to update a RuleName not found")
    @Test
    void updateEntityNotFound() {
        //Given an initial RuleName
        RuleName updatedRuleName = new RuleName();

        //When we try to update
        when(ruleNameRepository.findById(any())).thenReturn(Optional.empty());
        String errMsg = assertThrows(NotFoundException.class, () -> ruleNameService.update(updatedRuleName)).getMessage();

        //Then we verify if this have works correctly
        verify(ruleNameRepository, times(1)).findById(any());
        verify(ruleNameRepository, times(0)).save(any());
        assertThat(errMsg).contains("RuleName not found.");
    }


}