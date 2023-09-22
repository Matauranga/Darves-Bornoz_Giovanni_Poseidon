package com.nnk.springboot.controllers;

import com.nnk.springboot.domain.Trade;
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
public class TradeController {
    private final CrudServiceInterface<Trade> tradeService;

    @RequestMapping("/trade/list")
    public String getTradeList(Model model) {

        model.addAttribute("trades", tradeService.getAll());
        return "trade/list";
    }

    @GetMapping("/trade/add")
    public String addUser(Trade bid) {
        return "trade/add";
    }

    @PostMapping("/trade/validate")
    public String addTrade(@Valid Trade trade, BindingResult result) {

        if (!result.hasErrors()) { //TODO a retirer

            tradeService.add(trade);
            return "redirect:/trade/list";
        }

        return "trade/add";
    }

    @GetMapping("/trade/update/{id}")
    public String showUpdateForm(@PathVariable("id") Integer id, Model model) {

        model.addAttribute("trade", tradeService.getById(id));

        return "trade/update";
    }

    @PostMapping("/trade/update/{id}")
    public String updateTrade(@PathVariable("id") Integer id, @Valid Trade trade, BindingResult result) {

        if (result.hasErrors()) { //TODO a retirer

            return "trade/update";
        }

        trade.setTradeId(id);
        tradeService.update(trade);

        return "redirect:/trade/list";
    }

    @GetMapping("/trade/delete/{id}")
    public String deleteTrade(@PathVariable("id") Integer id) {

        tradeService.deleteById(id);

        return "redirect:/trade/list";
    }
}
