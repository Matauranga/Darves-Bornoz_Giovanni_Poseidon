package com.nnk.springboot.controllers;

import com.nnk.springboot.domain.RuleName;
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
public class RuleNameController {

    private final CrudServiceInterface<RuleName> ruleNameService;

    @RequestMapping("/ruleName/list")
    public String getRuleNameList(Model model) {

        model.addAttribute("ruleNames", ruleNameService.getAll());
        return "ruleName/list";
    }

    @GetMapping("/ruleName/add")
    public String addRuleNameForm(RuleName bid) {
        return "ruleName/add";
    }

    @PostMapping("/ruleName/validate")
    public String addRuleName(@Valid RuleName ruleName, BindingResult result) {

        if (!result.hasErrors()) { //TODO a retirer

            ruleNameService.add(ruleName);
            return "redirect:/ruleName/list";
        }

        return "ruleName/add";
    }

    @GetMapping("/ruleName/update/{id}")
    public String showUpdateForm(@PathVariable("id") Integer id, Model model) {

        model.addAttribute("ruleName", ruleNameService.getById(id));
        return "ruleName/update";
    }

    @PostMapping("/ruleName/update/{id}")
    public String updateRuleName(@PathVariable("id") Integer id, @Valid RuleName ruleName, BindingResult result) {

        if (result.hasErrors()) {

            return "ruleName/update";
        }

        ruleName.setId(id);
        ruleNameService.update(ruleName);

        return "redirect:/ruleName/list";
    }

    @GetMapping("/ruleName/delete/{id}")
    public String deleteRuleName(@PathVariable("id") Integer id) {

        ruleNameService.deleteById(id);

        return "redirect:/ruleName/list";
    }
}
