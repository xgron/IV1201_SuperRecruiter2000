package model;

import integration.DBPortal;
import org.springframework.security.crypto.bcrypt.BCrypt;
import shared.PersonDTO;
import java.util.Random;

public class User {

    DBPortal portal;
    /**
     *  Constructor for the User class.
     *
     * @param   portal  a DBPortal object(Database Portal). The model communicates only with DBPortal in the Integrationlayer.
     */
    public User(DBPortal portal){
        this.portal = portal;
    }

    /**
     *  This method is called from the Control layer when a someone is trying to register a new user.
     *
     *  Calls ssnTaken and usernameTaken from DBPortal in the integration layer to check if the
     *  Social Security Number or the Username already exists. If one of them exists(if ssnTaken or usernameTaken
     *  returns TRUE), it returns null, which indicates that the registration failed.
     *
     *  If none of them exists, it uses BCrypt to encrypt the password of the person, generates a UserID using
     *  generateUserID(), and calls registerUser from DBPortal, and returns the PersonDTO back to the Controller.
     *
     *
     * @param   person  A PersonDTO(Person Data Transfer Object), which contains all necessary data for a person.
     * @return  person  A PersonDTO(Person Data Transfer Object), now with encrypted password and a UserID.
     */
    public PersonDTO registerUser (PersonDTO person)throws ErrorHandling.RegisterUserExeption
    {
        if(portal.ssnTaken(Integer.parseInt(person.getSsn()))) {
            throw new ErrorHandling.RegisterUserExeption("SSN already exists");
        }else if(portal.usernameTaken(person.getUserName())) {
            throw new ErrorHandling.RegisterUserExeption("Username already exists");
        }else
            {
                person.setPassword(BCrypt.hashpw(person.getPassword(), BCrypt.gensalt()))   ;
                person.setUserId(generateUserID());
                portal.registerUser(person);
                return person;
            }

    }
    /*public boolean loginUser(String Username, String Password){
        return false;
    }*/

    private String generateUserID(){

        Random r = new Random();

        String alphabet = "abcdefghijklmnopqrstuvwxyz";
        String userid = "";
        for (int i = 0; i < 25; i++) {
            userid = userid + alphabet.charAt(r.nextInt(alphabet.length()));
        }
        return userid;
    }
}