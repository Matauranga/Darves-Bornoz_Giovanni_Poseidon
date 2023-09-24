package com.nnk.springboot.services;

import com.nnk.springboot.domain.Trade;
import com.nnk.springboot.exceptions.NotFoundException;
import com.nnk.springboot.repositories.TradeRepository;
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
class TradeServiceImplTest {

    @InjectMocks
    TradeServiceImpl tradeService;
    @Mock
    TradeRepository tradeRepository;

    @DisplayName("Try to add a Trade")
    @Test
    void add() {
        //Given a Trade to add
        Trade trade = new Trade("Add Test Account", "Add Test Type", 1.0);

        //When we try to add the Trade
        when(tradeRepository.save(any())).thenReturn(trade);
        tradeService.add(trade);

        //Then we verify if this have works correctly
        verify(tradeRepository, times(1)).save(any());
    }

    @DisplayName("Try to get a Trade by Id")
    @Test
    void getById() {
        //Given an initial Trade
        Trade tradeToFind = new Trade("Add Test Account", "Add Test Type", 1.0);
        Integer tradeId = tradeToFind.getTradeId();

        //When we tradeRepository to get the Trade
        when(tradeRepository.findById(any())).thenReturn(Optional.of(tradeToFind));
        Trade response = tradeService.getById(tradeId);

        //Then we verify if this have works correctly
        verify(tradeRepository, times(1)).findById(any());
        assertThat(response).isEqualTo(tradeToFind);
    }

    @DisplayName("Try to get a Trade by Id but throw not found exception")
    @Test
    void getEntityByIdNotFound() {
        //Given an initial Trade
        Integer tradeId = 0;

        //When we try to get the Trade
        when(tradeRepository.findById(any())).thenReturn(Optional.empty());
        String errMsg = assertThrows(NotFoundException.class, () -> tradeService.getById(tradeId)).getMessage();

        //Then we verify if this have works correctly
        verify(tradeRepository, times(1)).findById(any());
        assertThat(errMsg).contains("Entity not found.");
    }

    @DisplayName("Try get all Trades")
    @Test
    void getAll() {
        //Given an initial list of Trades
        List<Trade> trades = List.of(new Trade(), new Trade());

        //When we try to get all Trades
        when(tradeRepository.findAll()).thenReturn(trades);
        List<Trade> response = tradeService.getAll();

        //Then we verify if this have works correctly
        verify(tradeRepository, times(1)).findAll();
        assertThat(response).isNotEmpty().containsAll(trades);
    }

    @DisplayName("Try to delete a Trade")
    @Test
    void deleteById() {
        //Given an initial Trade
        Trade tradeToDelete = new Trade("Add Test Account", "Add Test Type", 1.0);

        //When we try to delete the Trade
        tradeService.deleteById(tradeToDelete.getTradeId());

        //Then we verify if this have works correctly
        verify(tradeRepository, times(1)).deleteById(any());
    }

    @DisplayName("Try to update Trade")
    @Test
    void update() {
        //Given an initial Trade and an update
        Trade initialTrade = new Trade("Add Initial Test Account", "Add Initial Test Type", 1.0);
        Trade updatedTrade = new Trade("Add Updated Test Account", "Add Updated Test Type", 10.0);
        updatedTrade.setTradeId(initialTrade.getTradeId());

        //When we try to update the Trade
        when(tradeRepository.findById(any())).thenReturn(Optional.of(initialTrade));
        tradeService.update(updatedTrade);

        //Then we verify if this have works correctly
        verify(tradeRepository, times(1)).findById(any());
        verify(tradeRepository, times(1)).save(any());
        assertThat(initialTrade.getAccount()).isEqualTo(updatedTrade.getAccount());
        assertThat(initialTrade.getBuyQuantity()).isEqualTo(updatedTrade.getBuyQuantity());
    }

    @DisplayName("Try to update a Trade not found")
    @Test
    void updateEntityNotFound() {
        //Given an initial Trade
        Trade updatedTrade = new Trade();

        //When we try to update
        when(tradeRepository.findById(any())).thenReturn(Optional.empty());
        String errMsg = assertThrows(NotFoundException.class, () -> tradeService.update(updatedTrade)).getMessage();

        //Then we verify if this have works correctly
        verify(tradeRepository, times(1)).findById(any());
        verify(tradeRepository, times(0)).save(any());
        assertThat(errMsg).contains("Trade not found.");
    }


}