package com.nnk.springboot.services;

import com.nnk.springboot.domain.BidList;
import com.nnk.springboot.exceptions.NotFoundException;
import com.nnk.springboot.repositories.BidListRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@SpringBootTest
public class BidListServiceImplTest {

    @InjectMocks
    BidListServiceImpl bidListService;

    @Mock
    BidListRepository bidListRepository;

    @DisplayName("Try to add a bid")
    @Test
    void add() {
        //Given a bid to add
        BidList bid = new BidList("Account Test", "Type Test", 100d);

        //When we add the bid
        when(bidListRepository.save(any())).thenReturn(bid);
        bidListService.add(bid);

        //Then we verify if this have works correctly
        verify(bidListRepository, times(1)).save(any());
    }

    @DisplayName("Try to get a bid by id")
    @Test
    void getById() {
        //Given an initial Bid
        BidList bidToFind = new BidList("Account Test", "Type Test", 100d);
        Integer bidId = bidToFind.getBidListId();

        //When we try to get the bid
        when(bidListRepository.findById(any())).thenReturn(Optional.of(bidToFind));
        BidList response = bidListService.getById(bidId);

        //Then we verify if this have works correctly
        verify(bidListRepository, times(1)).findById(any());
        assertThat(response).isNotNull(); //todo impossible d utilser du empty ou contains
    }

    @DisplayName("Try to get a bid by Id but throw not found exception")
    @Test
    void getEntityByIdNotFound() {
        //Given an initial Bid
        Integer bidId = 0;

        //When we try to get the bid
        when(bidListRepository.findById(any())).thenReturn(Optional.empty());
        assertThrows(NotFoundException.class, () -> bidListService.getById(bidId));

        //Then we verify if this have works correctly
        verify(bidListRepository, times(1)).findById(any());
    }

    @DisplayName("Try get all bid")
    @Test
    void getAll() {
        //Given an initial list of bid
        List<BidList> bidLists = List.of(new BidList(), new BidList());

        //When we try to get all
        when(bidListRepository.findAll()).thenReturn(bidLists);
        List<BidList> response = bidListService.getAll();

        //Then we verify if this have works correctly
        verify(bidListRepository, times(1)).findAll();
        assertThat(response).isNotEmpty().containsAll(bidLists);
    }

    @DisplayName("Try to delete a bid")
    @Test
    void deleteById() {//Todo a voir avec frank
        //Given a
        BidList bidToDelete = new BidList("Account To Delete Test", "Type To Delete Test", 1d);

        //When we
        bidListService.deleteById(bidToDelete.getBidListId());

        //Then we verify if this have works correctly
        verify(bidListRepository, times(1)).deleteById(any());
    }

    @DisplayName("Try to update a bid")
    @Test
    void update() {
        //Given an initial bid and his update
        BidList initialBid = new BidList("Account Initial Test", "Type Initial Test", 1d);
        BidList updatedBid = new BidList("Account Updated Test", "Type Updated Test", 1d);
        updatedBid.setBidListId(initialBid.getBidListId());

        //When we try to update the bid
        when(bidListRepository.findById(any())).thenReturn(Optional.of(initialBid));
        bidListService.update(updatedBid);

        //Then we verify if this have works correctly
        verify(bidListRepository, times(1)).findById(any());
        verify(bidListRepository, times(1)).save(any());
        assertThat(initialBid.getAccount()).isEqualTo(updatedBid.getAccount());
    }

    @DisplayName("Try to update a not found bid")
    @Test
    void updateEntityNotFound() {
        //Given an update bid
        BidList updatedBid = new BidList();

        //When we try to update
        when(bidListRepository.findById(any())).thenReturn(Optional.empty());
        assertThrows(NotFoundException.class, () -> bidListService.update(updatedBid));

        //Then we verify if this have works correctly
        verify(bidListRepository, times(1)).findById(any());
        verify(bidListRepository, times(0)).save(any());
    }

}