package com.nnk.springboot.controllers;

import com.nnk.springboot.domain.Rule;
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
public class RuleController {

    private final CrudServiceInterface<Rule> ruleService;

    /**
     * Handler method to handle bid list request
     *
     * @param model attribute to be passed to the front
     * @return the page where we have to redirect
     */
    @RequestMapping("/rule/list")
    public String getRuleList(Model model) {

        model.addAttribute("ruleList", ruleService.getAll());
        return "rule/list";
    }

    /**
     * Handler method to handle bid list request
     *
     * @param rule the future rule to add
     * @return the page where we have to redirect
     */
    @GetMapping("/rule/add")
    public String addRuleForm(Rule rule) {
        return "rule/add";
    }

    /**
     * Handler method to handle bid list request
     *
     * @param rule   the rule to add
     * @param result result of binding process
     * @return the page where we have to redirect
     */
    @PostMapping("/rule/validate")
    public String addRule(@Valid Rule rule, BindingResult result) {

        if (!result.hasErrors()) { //TODO a retirer

            ruleService.add(rule);
            return "redirect:/rule/list";
        }

        return "rule/add";
    }

    /**
     * Handler method to handle bid list request
     *
     * @param id    the id of the rule to update
     * @param model attribute to be passed to the front
     * @return the page where we have to redirect
     */
    @GetMapping("/rule/update/{id}")
    public String showUpdateForm(@PathVariable("id") Integer id, Model model) {

        model.addAttribute("rule", ruleService.getById(id));
        return "rule/update";
    }

    /**
     * Handler method to handle bid list request
     *
     * @param id     the id of the rule to update
     * @param rule   the rule updated
     * @param result result of binding process
     * @return the page where we have to redirect
     */
    @PostMapping("/rule/update/{id}")
    public String updateRule(@PathVariable("id") Integer id, @Valid Rule rule, BindingResult result) {

        rule.setId(id);
        if (result.hasErrors()) { //TODO a retirer

            return "rule/update";
        }

        ruleService.update(rule);
        return "redirect:/rule/list";
    }

    /**
     * Handler method to handle bid list request
     *
     * @param id the id of the rule to delete
     * @return the page where we have to redirect
     */
    @GetMapping("/rule/delete/{id}")
    public String deleteRuleName(@PathVariable("id") Integer id) {

        ruleService.deleteById(id);

        return "redirect:/rule/list";
    }
}
