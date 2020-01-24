package pl.coderslab.projectwybankuj.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pl.coderslab.projectwybankuj.entity.User;
import pl.coderslab.projectwybankuj.repository.UserRepository;

import javax.validation.Valid;

@Controller
@RequestMapping("/user")
public class UserController {

    private final UserRepository userRepository;

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping
    public String allUsers(Model model) {
        model.addAttribute("users", userRepository.findAll());
        return "allusers";
    }

    @GetMapping("/add")
    public String addInitForm(Model model) {
        model.addAttribute("user", new User());
        return "adduser";
    }

    @PostMapping("/add")
    public String addPostForm(@Valid User user, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "adduser";
        }
        userRepository.save(user);
        return "redirect:/user";
    }

    @GetMapping("/edit")
    public String editInitForm(@RequestParam Long id, Model model) {
        model.addAttribute("user", userRepository.findById(id));
        return "edituser";
    }

    @PostMapping("/edit")
    public String editPostForm(@Valid User user, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "edituser";
        }
        userRepository.save(user);
        return "redirect:/user";
    }

    @GetMapping("/confirm")
    public String confirmDelete(@RequestParam Long id, Model model) {
        model.addAttribute("user", userRepository.findById(id));
        return "confirmuser";
    }

    @GetMapping("/delete")
    public String deleteUser(@RequestParam Long id) {
        userRepository.deleteById(id);
        return "redirect:/user";
    }
}
