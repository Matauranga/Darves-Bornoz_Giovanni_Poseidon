package com.nnk.springboot.services;

import com.nnk.springboot.domain.Bid;
import com.nnk.springboot.exceptions.NotFoundException;
import com.nnk.springboot.repositories.BidRepository;
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
public class BidServiceImplTest {

    @InjectMocks
    BidServiceImpl bidService;
    @Mock
    BidRepository bidRepository;

    @DisplayName("Try to add a bid")
    @Test
    void add() {
        //Given a bid to add
        Bid bid = new Bid("Account Test", "Type Test", 100d);

        //When we add the bid
        when(bidRepository.save(any())).thenReturn(bid);
        bidService.add(bid);

        //Then we verify if this have works correctly
        verify(bidRepository, times(1)).save(any());
    }

    @DisplayName("Try to get a bid by Id")
    @Test
    void getById() {
        //Given an initial Bid
        Bid bidToFind = new Bid("Account Test", "Type Test", 100d);
        Integer bidId = bidToFind.getBidListId();

        //When we try to get the bid
        when(bidRepository.findById(any())).thenReturn(Optional.of(bidToFind));
        Bid response = bidService.getById(bidId);

        //Then we verify if this have works correctly
        verify(bidRepository, times(1)).findById(any());
        assertThat(response).isNotNull()
                .isEqualTo(bidToFind);
    }

    @DisplayName("Try to get a bid by Id but throw not found exception")
    @Test
    void getEntityByIdNotFound() {
        //Given an initial Bid
        Integer bidId = 0;

        //When we try to get the bid
        when(bidRepository.findById(any())).thenReturn(Optional.empty());
        String errMsg = assertThrows(NotFoundException.class, () -> bidService.getById(bidId)).getMessage();

        //Then we verify if this have works correctly
        verify(bidRepository, times(1)).findById(any());
        assertThat(errMsg).contains("Entity not found.");
    }

    @DisplayName("Try get all bid")
    @Test
    void getAll() {
        //Given an initial list of bid
        List<Bid> bids = List.of(new Bid(), new Bid());

        //When we try to get all bids
        when(bidRepository.findAll()).thenReturn(bids);
        List<Bid> response = bidService.getAll();

        //Then we verify if this have works correctly
        verify(bidRepository, times(1)).findAll();
        assertThat(response).isNotEmpty().containsAll(bids);
    }

    @DisplayName("Try to delete a bid")
    @Test
    void deleteById() {
        //Given an initial bid
        Bid bidToDelete = new Bid("Account To Delete Test", "Type To Delete Test", 1d);

        //When we try to delete the bid
        bidService.deleteById(bidToDelete.getBidListId());

        //Then we verify if this have works correctly
        verify(bidRepository, times(1)).deleteById(any());
    }

    @DisplayName("Try to update a bid")
    @Test
    void update() {
        //Given an initial bid and an update
        Bid initialBid = new Bid("Account Initial Test", "Type Initial Test", 1d);
        Bid updatedBid = new Bid("Account Updated Test", "Type Updated Test", 1d);
        updatedBid.setBidListId(initialBid.getBidListId());

        //When we try to update the bid
        when(bidRepository.findById(any())).thenReturn(Optional.of(initialBid));
        bidService.update(updatedBid);

        //Then we verify if this have works correctly
        verify(bidRepository, times(1)).findById(any());
        verify(bidRepository, times(1)).save(any());
        assertThat(initialBid.getAccount()).isEqualTo(updatedBid.getAccount());
    }

    @DisplayName("Try to update a not found bid")
    @Test
    void updateEntityNotFound() {
        //Given an update bid
        Bid updatedBid = new Bid();

        //When we try to update
        when(bidRepository.findById(any())).thenReturn(Optional.empty());
        String errMsg = assertThrows(NotFoundException.class, () -> bidService.update(updatedBid)).getMessage();

        //Then we verify if this have works correctly
        verify(bidRepository, times(1)).findById(any());
        verify(bidRepository, times(0)).save(any());
        assertThat(errMsg).contains("Bid not found.");
    }

}