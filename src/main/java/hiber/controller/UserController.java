package hiber.controller;

import hiber.model.User;
import hiber.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(value = "/")
    public String allUsers(Model model) {
        model.addAttribute("usersList", userService.findAll());
        return "/users";
    }

    @GetMapping(value = "/edit/{id}")
    public String editPage(@PathVariable("id") int id, Model model) {
        model.addAttribute("user", userService.findOne(id));
        return "editPage";
    }

    @PatchMapping(value = "/edit/{id}")
    public String editUser(@ModelAttribute("user") User user,
                                 @PathVariable("id") int id) {
        userService.update(id, user);
        return "redirect:/";
    }

    @GetMapping(value = "/add")
    public String addNewUser(Model model) {
        model.addAttribute("user", new User());
        return "addUser";
    }

    @PostMapping(value = "/add")
    public String addUser(@ModelAttribute("user") User user,
                          BindingResult bindingResult) {
        if(bindingResult.hasErrors()){
            return "/add";
        }
        userService.save(user);
        return "redirect:/";
    }

    @DeleteMapping (value="/delete/{id}")
    public String deleteUser(@PathVariable("id") int id) {
        userService.delete(id);
        return "redirect:/";
    }
}
