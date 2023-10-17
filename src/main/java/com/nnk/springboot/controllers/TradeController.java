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

    /**
     * Handler method to handle trade list request
     *
     * @param model attribute to be passed to the front
     * @return the page where we have to redirect
     */
    @RequestMapping("/trade/list")
    public String getTradeList(Model model) {

        model.addAttribute("trades", tradeService.getAll());
        return "trade/list";
    }

    /**
     * Handler method to handle add trade form
     *
     * @param trade the future trade to add
     * @return the page where we have to redirect
     */
    @GetMapping("/trade/add")
    public String addUser(Trade trade) {
        return "trade/add";
    }

    /**
     * Handler method to handle add trade
     *
     * @param trade  the trade to add
     * @param result result of binding process
     * @return the page where we have to redirect
     */
    @PostMapping("/trade/validate")
    public String addTrade(@Valid Trade trade, BindingResult result) {

        if (!result.hasErrors()) { //TODO a retirer

            tradeService.add(trade);
            return "redirect:/trade/list";
        }

        return "trade/add";
    }

    /**
     * Handler method to handle update trade form
     *
     * @param id    the id of trade to update
     * @param model attribute to be passed to the front
     * @return the page where we have to redirect
     */
    @GetMapping("/trade/update/{id}")
    public String showUpdateForm(@PathVariable("id") Integer id, Model model) {

        model.addAttribute("trade", tradeService.getById(id));

        return "trade/update";
    }

    /**
     * Handler method to handle update trade
     *
     * @param id     the id of trade to update
     * @param trade  the trade updated
     * @param result result of binding process
     * @return the page where we have to redirect
     */
    @PostMapping("/trade/update/{id}")
    public String updateTrade(@PathVariable("id") Integer id, @Valid Trade trade, BindingResult result) {

        trade.setTradeId(id);
        if (result.hasErrors()) { //TODO a retirer

            return "trade/update";
        }

        tradeService.update(trade);
        return "redirect:/trade/list";
    }

    /**
     * Handler method to handle delete trade
     *
     * @param id the id of trade to delete
     * @return the page where we have to redirect
     */
    @GetMapping("/trade/delete/{id}")
    public String deleteTrade(@PathVariable("id") Integer id) {

        tradeService.deleteById(id);

        return "redirect:/trade/list";
    }
}
