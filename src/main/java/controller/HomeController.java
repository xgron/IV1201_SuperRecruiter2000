package controller;


import model.ErrorHandling;
import org.springframework.stereotype.Controller;
import shared.ApplicationDTO;
import shared.PersonDTO;

@Controller
public class HomeController {
    public model.Model model = new model.Model();

    /**
     *  This method is called from the View layer when someone is trying to register a new user.
     *
     *  Calls the registerUser method from the Model object modelObject from the Model layer.
     *  Returns a PersonDTO object.
     *
     * @param   person  A PersonDTO(Person Data Transfer Object), which contains all necessary data for a person.
     * @return  user    The Registered User.
     */
    public PersonDTO registerUser(PersonDTO person) throws ErrorHandling.RegisterUserException {

        PersonDTO user = model.user.registerUser(person);
        return user;
    }
    /**
     *  This method is called from the View layer when a user is trying to register their job application.
     *
     *  Calls the registerApplication method from the Model layer.
     *  Returns a boolean. true if the application was valid and was successfully stored in the database. false otherwise.
     *
     * @param   application  An ApplicationDTO(Person Data Transfer Object), which contains all necessary data for an application.
     * @return  check    boolean, false if error.
     */
    public boolean registerApplication(ApplicationDTO application) throws ErrorHandling.RegisterApplicationException {
            boolean check = model.application.registerApplication(application);
        return check;
    }
    public PersonDTO AuthenticateUser(LoginDTO loginDTO) throws ErrorHandling.AuthenticateUserException{
        PersonDTO authenticatedUser = model.user.authenticateUser(loginDTO);
        return authenticatedUser;
    }
}
