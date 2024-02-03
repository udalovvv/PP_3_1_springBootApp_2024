package com.example.springboot.controllers;

import com.example.springboot.entity.User;
import com.example.springboot.services.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping("/")
public class UserController {

    private static final String REDIRECT_URL = "redirect:/";
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("")
    public String index(Model userModel) {
        userModel.addAttribute("users", userService.findAll());
        userModel.addAttribute("newUser", new User());
        return "pages/index";
    }

    @GetMapping("/add")
    public String add(Model userModel) {
        userModel.addAttribute("newUser", new User());
        return "pages/add";
    }

    @PostMapping("/add")
    public String add(@ModelAttribute("newUser") @Valid User newUser,
                      BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {return "pages/add";}
        userService.save(newUser);
        return REDIRECT_URL;
    }

    @GetMapping("/edit")
    public String edit(@RequestParam("id") long id, Model userModel) {
        userModel.addAttribute("user", userService.findById(id));
        return "pages/edit";
    }

    @PostMapping("/edit")
    public String edit(@RequestParam("id") long id,
                       @ModelAttribute("user") @Valid User user,
                       BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {return "pages/edit";}
        userService.updateUser(id, user);
        return REDIRECT_URL;
    }

    @GetMapping("/delete")
    public String delete(@RequestParam("id") long id) {
        userService.deleteUserById(id);
        return REDIRECT_URL;
    }

    @GetMapping("/show")
    public String show(@RequestParam("id") long id, Model userModel) {
        userModel.addAttribute("user", userService.findById(id));
        return "pages/show";
    }



    @PostMapping("/editmodal")
    public String editWithModal(@ModelAttribute("user") User user) {
        userService.updateUser(user.getId(), user);
        return REDIRECT_URL;
    }
}
