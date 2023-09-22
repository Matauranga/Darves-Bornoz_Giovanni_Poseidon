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


    @RequestMapping("/bidList/list")
    public String getBidList(Model model) {

        model.addAttribute("bidLists", bidListService.getAll());
        return "bidList/list";
    }

    @GetMapping("/bidList/add")
    public String addBidForm(BidList bidList) {
        return "bidList/add";
    }

    @PostMapping("/bidList/validate")
    public String addBid(@Valid BidList bid, BindingResult result) {

        if (!result.hasErrors()) { //TODO ; est utile ici ?

            bidListService.add(bid);
            return "redirect:/bidList/list";
        }

        return "bidList/add";
    }

    @GetMapping("/bidList/update/{id}")
    public String showUpdateForm(@PathVariable("id") Integer id, Model model) {

        model.addAttribute("bidList", bidListService.getById(id));
        return "bidList/update";
    }

    @PostMapping("/bidList/update/{id}")
    public String updateBid(@PathVariable("id") Integer id, @Valid BidList bidList, BindingResult result) {

        if (result.hasErrors()) { //TODO a retirer

            return "bidList/update";
        }

        bidList.setBidListId(id);
        this.bidListService.update(bidList);

        return "redirect:/bidList/list";
    }

    @GetMapping("/bidList/delete/{id}")
    public String deleteBid(@PathVariable("id") Integer id) {

        bidListService.deleteById(id);

        return "redirect:/bidList/list";
    }
}
