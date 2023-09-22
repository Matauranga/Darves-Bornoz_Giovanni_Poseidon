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

    @RequestMapping("/curvePoint/list")
    public String getCurvePointList(Model model) {

        model.addAttribute("curvePoints", curvePointService.getAll());
        return "curvePoint/list";
    }

    @GetMapping("/curvePoint/add")
    public String addCurvePointForm(CurvePoint curvePoint) {
        return "curvePoint/add";
    }

    @PostMapping("/curvePoint/validate")
    public String addCurvePoint(@Valid CurvePoint curvePoint, BindingResult result) {

        if (!result.hasErrors()) { //TODO a retirer

            curvePointService.add(curvePoint);
            return "redirect:/curvePoint/list";
        }

        return "curvePoint/add";
    }

    @GetMapping("/curvePoint/update/{id}")
    public String showUpdateForm(@PathVariable("id") Integer id, Model model) {

        model.addAttribute("curvePoint", curvePointService.getById(id));
        return "curvePoint/update";
    }

    @PostMapping("/curvePoint/update/{id}")
    public String updateCurvePoint(@PathVariable("id") Integer id, @Valid CurvePoint curvePoint, BindingResult result) {

        if (result.hasErrors()) { //TODO a retirer

            return "curvePoint/update";
        }

        curvePoint.setCurveId(id);
        curvePointService.update(curvePoint);

        return "redirect:/curvePoint/list";
    }

    @GetMapping("/curvePoint/delete/{id}")
    public String deleteCurvePoint(@PathVariable("id") Integer id) {

        curvePointService.deleteById(id);

        return "redirect:/curvePoint/list";
    }
}
