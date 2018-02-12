package model;

import integration.DBPortal;
import integration.entity.*;
import integration.entity.Person;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.transaction.support.TransactionSynchronizationManager;
import shared.PersonDTO;
import java.sql.Date;
import java.util.Calendar;
import javax.transaction.Transactional;

import java.util.List;
import java.util.Random;


public class User {

    private DBPortal portal;
    /**
     *  Constructor for the User class.
     *
     * @param   portal  a DBPortal object(Database Portal). The model communicates only with DBPortal in the Integrationlayer.
     */
    User(DBPortal portal){
        this.portal = portal;
    }

    /**
     *  This method is called from the Control layer when a someone is trying to register a new user.
     *
     *  Calls ssnTaken and usernameTaken from DBPortal in the integration layer to check if the
     *  Social Security Number or the Username already exists. If one of them exists(if ssnTaken or usernameTaken
     *  returns TRUE), it returns a exception, which describes that the registration failed.
     *
     *  If none of them exists, it uses BCrypt to encrypt the password of the person, generates a UserID using
     *  generateUserID(), and calls registerUser from DBPortal, and returns the PersonDTO back to the Controller.
     *
     *
     * @param   personDTO  A PersonDTO(Person Data Transfer Object), which contains all necessary data for a person.
     * @return  personDTO  A PersonDTO(Person Data Transfer Object), now with encrypted password and a UserID.
     */
    public PersonDTO registerUser (PersonDTO personDTO)throws ErrorHandling.RegisterUserException {
        if (portal.getPersonWithSSN(Integer.parseInt(personDTO.getSsn())).isEmpty()) {
            throw new ErrorHandling.RegisterUserException("SSN already exists");
        } else if (portal.getPersonWithUsername(personDTO.getUserName()).isEmpty()) {
            throw new ErrorHandling.RegisterUserException("Username already exists");
        } else if (TransactionSynchronizationManager.isActualTransactionActive() && TransactionSynchronizationManager.getResource(personDTO).equals(personDTO))
            throw new ErrorHandling.RegisterUserException("Username already exists");
        else {

            TransactionSynchronizationManager.initSynchronization();
            TransactionSynchronizationManager.bindResource(personDTO, personDTO);
            TransactionSynchronizationManager.setCurrentTransactionName(personDTO.getUserName());
            TransactionSynchronizationManager.setActualTransactionActive(true);

            personDTO.setPassword(BCrypt.hashpw(personDTO.getPassword(), BCrypt.gensalt()));
            personDTO.setUserId(generateUserID());

            int ssn = Integer.parseInt(personDTO.getSsn());
            String name = personDTO.getFirstName();
            String surname = personDTO.getSurname();
            String email = personDTO.getEmail();
            String password = personDTO.getPassword();
            String username = personDTO.getUserName();
            Boolean hired = null;
            Date registrationdate = new java.sql.Date(Calendar.getInstance().getTime().getTime());
            String userID = personDTO.getUserId();
            Role role = new Role("applicant");

            Person person = new Person(
                    userID,
                    name,
                    surname,
                    ssn,
                    email,
                    password,
                    username,
                    hired,
                    registrationdate,
                    null,
                    role
            );

            portal.savePerson(person);
            TransactionSynchronizationManager.clear();
            return personDTO;
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
