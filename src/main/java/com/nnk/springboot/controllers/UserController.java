package com.nnk.springboot.controllers;

import com.nnk.springboot.domain.User;
import com.nnk.springboot.services.CrudServiceInterface;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
public class UserController {

    private final CrudServiceInterface<User> userService;

    @RequestMapping("/user/list")
    public String getUserList(Model model) {

        model.addAttribute("users", userService.getAll());
        return "user/list";
    }

    @GetMapping("/user/add")
    public String addUserForm(User user) {
        return "user/add";
    }

    @PostMapping("/user/validate")
    public String addUser(@Valid User user, BindingResult result) {

        if (!result.hasErrors()) { //TODO :  a retirer

            BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
            user.setPassword(encoder.encode(user.getPassword()));
            userService.add(user);

            return "redirect:/user/list";
        }

        return "user/add";
    }

    @GetMapping("/user/update/{id}")
    public String showUpdateForm(@PathVariable("id") Integer id, Model model) {

        User user = userService.getById(id);
        user.setPassword("");
        model.addAttribute("user", user);

        return "user/update";
    }

    @PostMapping("/user/update/{id}")
    public String updateUser(@PathVariable("id") Integer id, @Valid User user,
                             BindingResult result) {

        if (result.hasErrors()) { //TODO :  a retirer
            return "user/update";
        }

        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        user.setPassword(encoder.encode(user.getPassword()));
        user.setId(id);
        userService.add(user);

        return "redirect:/user/list";
    }

    @GetMapping("/user/delete/{id}")
    public String deleteUser(@PathVariable("id") Integer id) {

        userService.deleteById(id);
        return "redirect:/user/list";
    }
}
