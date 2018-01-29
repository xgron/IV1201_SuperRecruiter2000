package controller;

import org.springframework.stereotype.Controller;

@Controller
public class HomeController {
    @GetMapping("/hello")
    public String handle(Model model) {
        model.addAttribute("message", "Hello World!");
        return "index";
    }
}
