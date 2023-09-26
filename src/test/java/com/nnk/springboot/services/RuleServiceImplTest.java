package com.nnk.springboot.services;

import com.nnk.springboot.domain.Rule;
import com.nnk.springboot.exceptions.NotFoundException;
import com.nnk.springboot.repositories.RuleRepository;
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
class RuleServiceImplTest {

    @InjectMocks
    RuleServiceImpl ruleService;
    @Mock
    RuleRepository ruleRepository;

    @DisplayName("Try to add Rule")
    @Test
    void add() {
        //Given a Rule to add
        Rule rule = new Rule("Rule Test", "Rule Test", "Json Test", "Template Test", "SQL Str Test", "SQL Part Test");

        //When we try to add the Rule
        when(ruleRepository.save(any())).thenReturn(rule);
        ruleService.add(rule);

        //Then we verify if this have works correctly
        verify(ruleRepository, times(1)).save(any());
    }

    @DisplayName("Try to get Rule by Id")
    @Test
    void getById() {
        //Given an initial Rule
        Rule ruleToFind = new Rule("Rule Test", "Rule Test", "Json Test", "Template Test", "SQL Str Test", "SQL Part Test");
        Integer ruleNameId = ruleToFind.getId();

        //When we try to get the Rule
        when(ruleRepository.findById(any())).thenReturn(Optional.of(ruleToFind));
        Rule response = ruleService.getById(ruleNameId);

        //Then we verify if this have works correctly
        verify(ruleRepository, times(1)).findById(any());
        assertThat(response).isEqualTo(ruleToFind);
    }

    @DisplayName("Try to get a Rule by Id but throw not found exception")
    @Test
    void getEntityByIdNotFound() {
        //Given an initial Rule
        Integer ruleNameId = 0;

        //When we try to get the Rule
        when(ruleRepository.findById(any())).thenReturn(Optional.empty());
        String errMsg = assertThrows(NotFoundException.class, () -> ruleService.getById(ruleNameId)).getMessage();

        //Then we verify if this have works correctly
        verify(ruleRepository, times(1)).findById(any());
        assertThat(errMsg).contains("Entity not found.");
    }

    @DisplayName("Try get all RuleNames")
    @Test
    void getAll() {
        //Given an initial list of RuleNames
        List<Rule> rules = List.of(new Rule(), new Rule());

        //When we try to get all RuleNames
        when(ruleRepository.findAll()).thenReturn(rules);
        List<Rule> response = ruleService.getAll();

        //Then we verify if this have works correctly
        verify(ruleRepository, times(1)).findAll();
        assertThat(response).isNotEmpty().containsAll(rules);

    }

    @DisplayName("Try to delete a Rule")
    @Test
    void deleteById() {
        //Given an initial Rule
        Rule ruleToDelete = new Rule("Rule Test", "Rule Test", "Json Test", "Template Test", "SQL Str Test", "SQL Part Test");

        //When we try to delete the Rule
        ruleService.deleteById(ruleToDelete.getId());

        //Then we verify if this have works correctly
        verify(ruleRepository, times(1)).deleteById(any());
    }

    @DisplayName("Try to update Rule")
    @Test
    void update() {
        //Given an initial Rule and an update
        Rule initialRule = new Rule("Initial Rule Test", "Initial Rule Test", "Initial Json Test", "Initial Template Test", "Initial SQL Str Test", "Initial SQL Part Test");
        Rule updatedRule = new Rule("Updated Rule Test", "Updated Rule Test", "Updated Json Test", "Updated Template Test", "Updated SQL Str Test", "Updated SQL Part Test");
        updatedRule.setId(initialRule.getId());

        //When we try to update the Rule
        when(ruleRepository.findById(any())).thenReturn(Optional.of(initialRule));
        ruleService.update(updatedRule);

        //Then we verify if this have works correctly
        verify(ruleRepository, times(1)).findById(any());
        verify(ruleRepository, times(1)).save(any());
        assertThat(initialRule.getName()).isEqualTo(updatedRule.getName());
        assertThat(initialRule.getSqlPart()).isEqualTo(updatedRule.getSqlPart());
    }

    @DisplayName("Try to update a Rule not found")
    @Test
    void updateEntityNotFound() {
        //Given an initial Rule
        Rule updatedRule = new Rule();

        //When we try to update
        when(ruleRepository.findById(any())).thenReturn(Optional.empty());
        String errMsg = assertThrows(NotFoundException.class, () -> ruleService.update(updatedRule)).getMessage();

        //Then we verify if this have works correctly
        verify(ruleRepository, times(1)).findById(any());
        verify(ruleRepository, times(0)).save(any());
        assertThat(errMsg).contains("Rule not found.");
    }


}