package com.nnk.springboot.controllers;

import com.nnk.springboot.domain.Bid;
import com.nnk.springboot.services.CrudServiceInterface;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
public class BidController {

    private final CrudServiceInterface<Bid> bidService;

    /**
     * Handler method to handle bid list request
     *
     * @param model attribute to be passed to the front
     * @return the page where we have to redirect
     */
    @RequestMapping("/bid/list")
    public String getBidList(Model model) {

        model.addAttribute("bidList", bidService.getAll());
        return "bid/list";
    }

    /**
     * Handler method to handle add bid form
     *
     * @param bid the future bid to add
     * @return the page where we have to redirect
     */
    @GetMapping("/bid/add")
    public String addBidForm(Bid bid) {
        return "bid/add";
    }

    /**
     * Handler method to add bid
     *
     * @param bid    the bid to add
     * @param result result of binding process
     * @return the page where we have to redirect
     */
    @PostMapping("/bid/validate")
    public String addBid(@Valid Bid bid, BindingResult result) {

        if (!result.hasErrors()) {

            bidService.add(bid);
            return "redirect:/bid/list";
        }

        return "bid/add";
    }

    /**
     * Handler method to handle update bid form
     *
     * @param id    the id of bid to update
     * @param model attribute to be passed to the front
     * @return the page where we have to redirect
     */
    @GetMapping("/bid/update/{id}")
    public String showUpdateForm(@PathVariable("id") Integer id, Model model) {

        model.addAttribute("bid", bidService.getById(id));
        return "bid/update";
    }

    /**
     * Handler method to update bid
     *
     * @param id     the id of bid to update
     * @param bid    the bid updated
     * @param result result of binding process
     * @return the page where we have to redirect
     */
    @PostMapping("/bid/update/{id}")
    public String updateBid(@PathVariable("id") Integer id, @Valid Bid bid, BindingResult result) {

        if (result.hasErrors()) {

            return "bid/update";
        }

        bid.setBidListId(id);
        this.bidService.update(bid);

        return "redirect:/bid/list";
    }

    /**
     * Handler method to delete bid
     *
     * @param id the id of bid to delete
     * @return the page where we have to redirect
     */
    @GetMapping("/bid/delete/{id}")
    public String deleteBid(@PathVariable("id") Integer id) {

        bidService.deleteById(id);

        return "redirect:/bid/list";
    }
}
