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

    private final CrudServiceInterface<Rating> ratingService;

    /**
     * Handler method to handle rating list request
     *
     * @param model attribute to be passed to the front
     * @return the page where we have to redirect
     */
    @RequestMapping("/rating/list")
    public String getRatingList(Model model) {

        model.addAttribute("ratings", ratingService.getAll());
        return "rating/list";
    }

    /**
     * Handler method to add rating form
     *
     * @param rating the future rating to add
     * @return the page where we have to redirect
     */
    @GetMapping("/rating/add")
    public String addRatingForm(Rating rating) {
        return "rating/add";
    }

    /**
     * Handler method to add rating
     *
     * @param rating the rating to add
     * @param result result of binding process
     * @return the page where we have to redirect
     */
    @PostMapping("/rating/validate")
    public String addRating(@Valid Rating rating, BindingResult result) {

        if (result.hasErrors()) {
            return "rating/add";
        }

        ratingService.add(rating);
        return "redirect:/rating/list";
    }

    /**
     * Handler method to update rating form
     *
     * @param id    the id of rating to update
     * @param model attribute to be passed to the front
     * @return the page where we have to redirect
     */
    @GetMapping("/rating/update/{id}")
    public String showUpdateForm(@PathVariable("id") Integer id, Model model) {

        model.addAttribute("rating", ratingService.getById(id));
        return "rating/update";
    }

    /**
     * Handler method to update rating
     *
     * @param id     the id of rating to update
     * @param rating the rating updated
     * @param result result of binding process
     * @return the page where we have to redirect
     */
    @PostMapping("/rating/update/{id}")
    public String updateRating(@PathVariable("id") Integer id, @Valid Rating rating, BindingResult result) {

        rating.setId(id);

        if (result.hasErrors()) {
            return "rating/update";
        }

        ratingService.update(rating);
        return "redirect:/rating/list";
    }

    /**
     * Handler method to delete rating
     *
     * @param id the id of rating to delete
     * @return the page where we have to redirect
     */
    @GetMapping("/rating/delete/{id}")
    public String deleteRating(@PathVariable("id") Integer id) {

        ratingService.deleteById(id);

        return "redirect:/rating/list";
    }
}
