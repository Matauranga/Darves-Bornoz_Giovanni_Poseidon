package com.nnk.springboot.controllers;

import com.nnk.springboot.domain.BidList;
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
public class BidListController {

    private final CrudServiceInterface<BidList> bidListService;

    /**
     * Handler method to handle bid list request
     *
     * @param model attribute to be passed to the front
     * @return the page where we have to redirect
     */
    @RequestMapping("/bidList/list")
    public String getBidList(Model model) {

        model.addAttribute("bidLists", bidListService.getAll());
        return "bidList/list";
    }

    /**
     * Handler method to handle add bid form
     *
     * @param bidList the future bid to add
     * @return the page where we have to redirect
     */
    @GetMapping("/bidList/add")
    public String addBidForm(BidList bidList) {
        return "bidList/add";
    }

    /**
     * Handler method to add bid
     *
     * @param bid    the bid to add
     * @param result result of binding process
     * @return the page where we have to redirect
     */
    @PostMapping("/bidList/validate")
    public String addBid(@Valid BidList bid, BindingResult result) {

        if (!result.hasErrors()) { //TODO ; est utile ici ?

            bidListService.add(bid);
            return "redirect:/bidList/list";
        }

        return "bidList/add";
    }

    /**
     * Handler method to handle update bid form
     *
     * @param id    the id of bid to update
     * @param model attribute to be passed to the front
     * @return the page where we have to redirect
     */
    @GetMapping("/bidList/update/{id}")
    public String showUpdateForm(@PathVariable("id") Integer id, Model model) {

        model.addAttribute("bidList", bidListService.getById(id));
        return "bidList/update";
    }

    /**
     * Handler method to update bid
     *
     * @param id      the id of bid to update
     * @param bidList the bid updated
     * @param result  result of binding process
     * @return the page where we have to redirect
     */
    @PostMapping("/bidList/update/{id}")
    public String updateBid(@PathVariable("id") Integer id, @Valid BidList bidList, BindingResult result) {

        if (result.hasErrors()) { //TODO a retirer

            return "bidList/update";
        }

        bidList.setBidListId(id);
        this.bidListService.update(bidList);

        return "redirect:/bidList/list";
    }

    /**
     * Handler method to delete bid
     *
     * @param id the id of bid to delete
     * @return the page where we have to redirect
     */
    @GetMapping("/bidList/delete/{id}")
    public String deleteBid(@PathVariable("id") Integer id) {

        bidListService.deleteById(id);

        return "redirect:/bidList/list";
    }
}
