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

    /**
     * Handler method to handle user list request
     *
     * @param model attribute to be passed to the front
     * @return the page where we have to redirect
     */
    @RequestMapping("/user/list")
    public String getUserList(Model model) {

        model.addAttribute("users", userService.getAll());
        return "user/list";
    }

    /**
     * Handler method to handle add user form
     *
     * @param user the future user to add
     * @return the page where we have to redirect
     */
    @GetMapping("/user/add")
    public String addUserForm(User user) {
        return "user/add";
    }

    /**
     * Handler method to handle add user
     *
     * @param user   the user to add
     * @param result result of binding process
     * @return the page where we have to redirect
     */
    @PostMapping("/user/validate")
    public String addUser(@Valid User user, BindingResult result) {

        if (!result.hasErrors()) {

            BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
            user.setPassword(encoder.encode(user.getPassword()));
            userService.add(user);

            return "redirect:/user/list";
        }

        return "user/add";
    }

    /**
     * Handler method to handle update user form
     *
     * @param id    the id of the user to update
     * @param model attribute to be passed to the front
     * @return the page where we have to redirect
     */
    @GetMapping("/user/update/{id}")
    public String showUpdateForm(@PathVariable("id") Integer id, Model model) {

        User user = userService.getById(id);
        user.setPassword("");
        model.addAttribute("user", user);

        return "user/update";
    }

    /**
     * Handler method to handle update user
     *
     * @param id     the id of the user to update
     * @param user   the user updated
     * @param result result of binding process
     * @return the page where we have to redirect
     */
    @PostMapping("/user/update/{id}")
    public String updateUser(@PathVariable("id") Integer id, @Valid User user,
                             BindingResult result) {

        user.setId(id);
        if (result.hasErrors()) {
            return "user/update";
        }

        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        user.setPassword(encoder.encode(user.getPassword()));
        userService.add(user);

        return "redirect:/user/list";
    }

    /**
     * Handler method to handle bid list request
     *
     * @param id the id of the user to delete
     * @return the page where we have to redirect
     */
    @GetMapping("/user/delete/{id}")
    public String deleteUser(@PathVariable("id") Integer id) {

        userService.deleteById(id);
        return "redirect:/user/list";
    }
}
