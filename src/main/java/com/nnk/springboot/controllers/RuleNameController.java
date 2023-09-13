package com.nnk.springboot.controllers;

import com.nnk.springboot.domain.RuleName;
import com.nnk.springboot.services.RuleNameServiceImpl;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
public class RuleNameController {
    private final RuleNameServiceImpl ruleNameServiceimpl;

    public RuleNameController(RuleNameServiceImpl ruleNameServiceimpl) {
        this.ruleNameServiceimpl = ruleNameServiceimpl;
    }

    @RequestMapping("/ruleName/list")
    public String home(Model model) {

        model.addAttribute("ruleNames", ruleNameServiceimpl.getAll());

        return "ruleName/list";
    }

    @GetMapping("/ruleName/add")
    public String addRuleForm(RuleName bid) {
        return "ruleName/add";
    }

    @PostMapping("/ruleName/validate")
    public String validate(@Valid RuleName ruleName, BindingResult result, Model model) {

        if (!result.hasErrors()) {

            ruleNameServiceimpl.add(ruleName);
            model.addAttribute("ruleNames", ruleNameServiceimpl.getAll());

            return "redirect:/ruleName/list";
        }

        return "ruleName/add";
    }

    @GetMapping("/ruleName/update/{id}")
    public String showUpdateForm(@PathVariable("id") Integer id, Model model) {

        model.addAttribute("ruleName", ruleNameServiceimpl.getById(id));

        return "ruleName/update";
    }

    @PostMapping("/ruleName/update/{id}")
    public String updateRuleName(@PathVariable("id") Integer id, @Valid RuleName ruleName, BindingResult result, Model model) {

        if (result.hasErrors()) {

            return "ruleName/update";
        }

        ruleName.setId(id);
        ruleNameServiceimpl.update(ruleName);
        model.addAttribute("ruleNames", ruleNameServiceimpl.getAll());

        return "redirect:/ruleName/list";
    }

    @GetMapping("/ruleName/delete/{id}")
    public String deleteRuleName(@PathVariable("id") Integer id, Model model) {

        ruleNameServiceimpl.deleteById(id);
        model.addAttribute("ruleNames", ruleNameServiceimpl.getAll());

        return "redirect:/ruleName/list";
    }
}
