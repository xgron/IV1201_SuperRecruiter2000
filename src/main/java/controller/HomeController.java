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
     * @return  check    boolean, It throws an exception if error
     */
    public boolean registerApplication(ApplicationDTO application) throws ErrorHandling.CommonException {
        return model.application.registerApplication(application);
    }

    /**
     *  This method is called from the View layer when a recruiter changes status of an application to hired or declined.
     *
     * @param   applicantID  userID of the owner of the application
     * @param   recruiterID  userID of the recruiter
     * @param   evaluation  true if hired, false if declined
     *
     * @return  userID    the UserID.
     */
    public boolean evaluateApplication(String applicantID, boolean evaluation, String recruiterID) throws ErrorHandling.CommonException{
        return model.application.evaluateApplication(applicantID, evaluation, recruiterID);
    }

    /**
     *  This method is called from the View layer to get the userID connected to the given username.
     *
     * @param   username  A Username
     * @return  userID    the UserID.
     */
    public String getUserID(String username){
        return model.user.getUserID(username);
    }

    /**
     *  This method is called from the View layer to get the role connected to the given userID.
     *
     * @param   userID  A Username
     * @return  Role    String
     */
    public String getRole(String userID) { return model.user.getRole(userID); }

    /**
     *  This method is called from the View layer to get all the applications.
     *
     * @return  PublicApplicationDTO List (Contains all the info of both the application and applicant)
     */
    public List<PublicApplicationDTO> getApplicants() {
        return model.application.getApplicants();
    }

    /**
     *  This method is called from the View layer to get all the applications.
     *
     * @return  PublicApplicationDTO (Contains all the info of both the application and applicant)
     */
    public PublicApplicationDTO getApplicant(String userID) { return model.application.getApplicant(userID); }

    /**
     *  This method is called from the View layer when user is attempting to log in.
     *
     * @param   loginDTO  contains username and password
     *
     * @return  authenticatedUser    the user
     */
    public PersonDTO AuthenticateUser(LoginDTO loginDTO) throws ErrorHandling.CommonException{
        PersonDTO authenticatedUser = model.user.authenticateUser(loginDTO);
        return authenticatedUser;
    }

    public List<String> getPossibleCompetences(){
        return model.application.getPossibleCompetences();
    }
}
