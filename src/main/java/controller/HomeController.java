package controller;


import model.ErrorHandling;
import org.springframework.stereotype.Controller;
import shared.ApplicationDTO;
import shared.LoginDTO;
import shared.PersonDTO;
import shared.PublicApplicationDTO;

import java.util.List;

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
    public PersonDTO registerUser(PersonDTO person) throws ErrorHandling.CommonException {
        return model.user.registerUser(person);
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
    public boolean registerApplication(ApplicationDTO application) throws ErrorHandling.CommonException {
        return model.application.registerApplication(application);
    }

    public boolean evaluateApplication(String applicantID, boolean evaluation, String recruiterID) throws ErrorHandling.CommonException{
        return model.application.evaluateApplication(applicantID, evaluation, recruiterID);
    }

    //JAVADOC TO DO
    public String getUserID(String username){
        return model.user.getUserID(username);
    }

    //JAVADOC TO DO
    public String getRole(String userID) { return model.user.getRole(userID); }

    //JAVADOC TO DO
    public List<PublicApplicationDTO> getApplicants() {
        return model.application.getApplicants();
    }


    public PersonDTO AuthenticateUser(LoginDTO loginDTO) throws ErrorHandling.CommonException{
        PersonDTO authenticatedUser = model.user.authenticateUser(loginDTO);
        return authenticatedUser;
    }
}
