package com.example.timetrackerapp.controller;

import com.example.timetrackerapp.model.Employee;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.ui.Model;

@Controller
public class IndexController {

    /*
    это отсылка с стартовой странице index.html,
    а не просто к локал хост http://localhost:8080 где ничего нет.
     */
    @GetMapping()
        public String showIndexPage() {
        return "index";
    }

    @GetMapping("/login")
    public String showLoginPage(@ModelAttribute Employee employee, Model model) {
        return "login";
    }

    @GetMapping("/welcome")
    public String redirectUserOrAdmin(Authentication authentication){
        if (authentication.getAuthorities().iterator().next().getAuthority().equals("ROLE_ADMIN")){
            return "redirect:/admin/journal";
        }
        return "redirect:/user/journal";
    }
}
