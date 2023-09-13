package com.nnk.springboot.controllers;

import com.nnk.springboot.domain.BidList;
import com.nnk.springboot.services.BidListServiceImpl;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class BidListController {
    private final BidListServiceImpl bidListServiceImpl;

    public BidListController(BidListServiceImpl bidListServiceImpl) {
        this.bidListServiceImpl = bidListServiceImpl;
    }

    @RequestMapping("/bidList/list")
    public String home(Model model) {

        model.addAttribute("bidLists", bidListServiceImpl.getAll());

        return "bidList/list";
    }

    @GetMapping("/bidList/add")
    public String addBidForm(BidList bid) {
        return "bidList/add";
    }

    @PostMapping("/bidList/validate")
    public String validate(@Valid BidList bid, BindingResult result, Model model) {

        if (!result.hasErrors()) {

            bidListServiceImpl.add(bid);
            model.addAttribute("bidLists", bidListServiceImpl.getAll());

            return "redirect:/bidList/list";
        }

        return "bidList/add";
    }

    @GetMapping("/bidList/update/{id}")
    public String showUpdateForm(@PathVariable("id") Integer id, Model model) {

        model.addAttribute("bidList", bidListServiceImpl.getById(id));

        return "bidList/update";
    }

    @PostMapping("/bidList/update/{id}")
    public String updateBid(@PathVariable("id") Integer id, @Valid BidList bidList, BindingResult result, Model model) {

        if (result.hasErrors()) {

            return "bidList/update";
        }

        bidList.setBidListId(id);
        bidListServiceImpl.update(bidList);
        model.addAttribute("bidLists", bidListServiceImpl.getAll());

        return "redirect:/bidList/list";
    }

    @GetMapping("/bidList/delete/{id}")
    public String deleteBid(@PathVariable("id") Integer id, Model model) {

        bidListServiceImpl.deleteById(id);
        model.addAttribute("bidLists", bidListServiceImpl.getAll());

        return "redirect:/bidList/list";
    }
}
