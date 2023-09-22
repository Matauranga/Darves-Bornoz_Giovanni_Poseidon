package com.nnk.springboot.controllers;

import com.nnk.springboot.domain.Rating;
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
public class RatingController {

    // todo : regarder @Qualifier
    private final CrudServiceInterface<Rating> ratingService;


    @RequestMapping("/rating/list")
    public String getRatingList(Model model) {

        model.addAttribute("ratings", ratingService.getAll());
        return "rating/list";
    }

    @GetMapping("/rating/add")
    public String addRatingForm(Rating rating) {
        return "rating/add";
    }

    @PostMapping("/rating/validate")
    public String addRating(@Valid Rating rating, BindingResult result) {

        if (!result.hasErrors()) { //TODO a retirer

            ratingService.add(rating);
            return "redirect:/rating/list";
        }

        return "rating/add";
    }

    @GetMapping("/rating/update/{id}")
    public String showUpdateForm(@PathVariable("id") Integer id, Model model) {

        model.addAttribute("rating", ratingService.getById(id));
        return "rating/update";
    }

    @PostMapping("/rating/update/{id}")
    public String updateRating(@PathVariable("id") Integer id, @Valid Rating rating, BindingResult result) {

        if (result.hasErrors()) { //TODO a retirer

            return "rating/update";
        }

        rating.setId(id);
        ratingService.update(rating);

        return "redirect:/rating/list";
    }

    @GetMapping("/rating/delete/{id}")
    public String deleteRating(@PathVariable("id") Integer id) {

        ratingService.deleteById(id);

        return "redirect:/rating/list";
    }
}
