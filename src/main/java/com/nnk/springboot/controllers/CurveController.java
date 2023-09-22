package com.nnk.springboot.controllers;

import com.nnk.springboot.domain.CurvePoint;
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
public class CurveController {

    private final CrudServiceInterface<CurvePoint> curvePointService;

    /**
     * Handler method to handle curve point list request
     *
     * @param model attribute to be passed to the front
     * @return the page where we have to redirect
     */
    @RequestMapping("/curvePoint/list")
    public String getCurvePointList(Model model) {

        model.addAttribute("curvePoints", curvePointService.getAll());
        return "curvePoint/list";
    }

    /**
     * Handler method to handle add curve point form
     *
     * @param curvePoint the future curve point to add
     * @return the page where we have to redirect
     */
    @GetMapping("/curvePoint/add")
    public String addCurvePointForm(CurvePoint curvePoint) {
        return "curvePoint/add";
    }

    /**
     * Handler method to add curve point
     *
     * @param curvePoint the curve point to add
     * @param result     result of binding process
     * @return the page where we have to redirect
     */
    @PostMapping("/curvePoint/validate")
    public String addCurvePoint(@Valid CurvePoint curvePoint, BindingResult result) {

        if (!result.hasErrors()) { //TODO a retirer

            curvePointService.add(curvePoint);
            return "redirect:/curvePoint/list";
        }

        return "curvePoint/add";
    }

    /**
     * Handler method to handle update curve point form
     *
     * @param id    the id of curve point to update
     * @param model attribute to be passed to the front
     * @return the page where we have to redirect
     */
    @GetMapping("/curvePoint/update/{id}")
    public String showUpdateForm(@PathVariable("id") Integer id, Model model) {

        model.addAttribute("curvePoint", curvePointService.getById(id));
        return "curvePoint/update";
    }

    /**
     * Handler method to update curve point
     *
     * @param id         the id of curve point to update
     * @param curvePoint the curve point updated
     * @param result     result of binding process
     * @return the page where we have to redirect
     */
    @PostMapping("/curvePoint/update/{id}")
    public String updateCurvePoint(@PathVariable("id") Integer id, @Valid CurvePoint curvePoint, BindingResult result) {

        if (result.hasErrors()) { //TODO a retirer

            return "curvePoint/update";
        }

        curvePoint.setCurveId(id);
        curvePointService.update(curvePoint);

        return "redirect:/curvePoint/list";
    }

    /**
     * Handler method to delete curve point
     *
     * @param id the id of curve point to delete
     * @return the page where we have to redirect
     */
    @GetMapping("/curvePoint/delete/{id}")
    public String deleteCurvePoint(@PathVariable("id") Integer id) {

        curvePointService.deleteById(id);

        return "redirect:/curvePoint/list";
    }
}
