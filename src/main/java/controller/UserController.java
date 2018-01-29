package controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class UserController {

    @RequestMapping("/RegisterUser")
    public boolean registerUser(PersonDTO person) {
        boolean check = model.User.registerUser(person);
        return check;
    }
}
