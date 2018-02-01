package controller;

import model.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import shared.PersonDTO;

@Controller
public class HomeController {
    public model.Model modelObject = new model.Model();

    @RequestMapping("/RegisterUser")
    public boolean registerUser(PersonDTO person) {
        boolean check = modelObject.user.registerUser(person);
        return check;
    }
}
