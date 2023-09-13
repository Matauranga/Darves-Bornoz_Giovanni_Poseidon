package com.nnk.springboot.controllers;

import com.nnk.springboot.domain.Trade;
import com.nnk.springboot.services.TradeServiceImpl;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
public class TradeController {
    private final TradeServiceImpl tradeServiceImpl;

    public TradeController(TradeServiceImpl tradeServiceImpl) {
        this.tradeServiceImpl = tradeServiceImpl;
    }

    @RequestMapping("/trade/list")
    public String home(Model model) {

        model.addAttribute("trades", tradeServiceImpl.getAll());

        return "trade/list";
    }

    @GetMapping("/trade/add")
    public String addUser(Trade bid) {
        return "trade/add";
    }

    @PostMapping("/trade/validate")
    public String validate(@Valid Trade trade, BindingResult result, Model model) {

        if (!result.hasErrors()) {

            tradeServiceImpl.add(trade);
            model.addAttribute("trades", tradeServiceImpl.getAll());

            return "redirect:/trade/list";
        }

        return "trade/add";
    }

    @GetMapping("/trade/update/{id}")
    public String showUpdateForm(@PathVariable("id") Integer id, Model model) {

        model.addAttribute("trade", tradeServiceImpl.getById(id));

        return "trade/update";
    }

    @PostMapping("/trade/update/{id}")
    public String updateTrade(@PathVariable("id") Integer id, @Valid Trade trade, BindingResult result, Model model) {

        if (result.hasErrors()) {

            return "trade/update";
        }

        trade.setTradeId(id);
        tradeServiceImpl.update(trade);
        model.addAttribute("trades", tradeServiceImpl.getAll());

        return "redirect:/trade/list";
    }

    @GetMapping("/trade/delete/{id}")
    public String deleteTrade(@PathVariable("id") Integer id, Model model) {

        tradeServiceImpl.deleteById(id);
        model.addAttribute("trades", tradeServiceImpl.getAll());

        return "redirect:/trade/list";
    }
}
