package com.nnk.springboot.controllers;

import com.nnk.springboot.domain.Rating;
import com.nnk.springboot.services.RatingServiceImpl;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
public class RatingController {
    private final RatingServiceImpl ratingServiceImpl;

    public RatingController(RatingServiceImpl ratingServiceImpl) {
        this.ratingServiceImpl = ratingServiceImpl;
    }

    @RequestMapping("/rating/list")
    public String home(Model model) {

        model.addAttribute("ratings", ratingServiceImpl.getAll());

        return "rating/list";
    }

    @GetMapping("/rating/add")
    public String addRatingForm(Rating rating) {
        return "rating/add";
    }

    @PostMapping("/rating/validate")
    public String validate(@Valid Rating rating, BindingResult result, Model model) {

        if (!result.hasErrors()) {

            ratingServiceImpl.add(rating);
            model.addAttribute("ratings", ratingServiceImpl.getAll());

            return "redirect:/rating/list";
        }

        return "rating/add";
    }

    @GetMapping("/rating/update/{id}")
    public String showUpdateForm(@PathVariable("id") Integer id, Model model) {

        model.addAttribute("rating", ratingServiceImpl.getById(id));

        return "rating/update";
    }

    @PostMapping("/rating/update/{id}")
    public String updateRating(@PathVariable("id") Integer id, @Valid Rating rating, BindingResult result, Model model) {

        if (result.hasErrors()) {

            return "rating/update";
        }

        rating.setId(id);
        ratingServiceImpl.update(rating);
        model.addAttribute("ratings", ratingServiceImpl.getAll());

        return "redirect:/rating/list";
    }

    @GetMapping("/rating/delete/{id}")
    public String deleteRating(@PathVariable("id") Integer id, Model model) {

        ratingServiceImpl.deleteById(id);
        model.addAttribute("ratings", ratingServiceImpl.getAll());

        return "redirect:/rating/list";
    }
}
