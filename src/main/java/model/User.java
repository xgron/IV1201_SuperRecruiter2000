package model;

import integration.DBPortal;
import integration.entity.*;
import integration.entity.Person;
import org.springframework.beans.BeanUtils;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.transaction.support.TransactionSynchronizationManager;
import shared.LoginDTO;
import shared.PersonDTO;
import shared.PublicApplicationDTO;

import java.sql.Date;
import java.util.Calendar;
import javax.transaction.Transactional;

import java.util.List;
import java.util.Random;
import java.util.logging.ConsoleHandler;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;


public class User {

    private DBPortal portal;
    final static Logger logr = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
    /**
     *  Constructor for the User class.
     *
     * @param   portal  a DBPortal object(Database Portal). The model communicates only with DBPortal in the Integrationlayer.
     */
    User(DBPortal portal){
        this.portal = portal;
        LogManager.getLogManager().reset();
        logr.setLevel(Level.ALL);
        ConsoleHandler ch = new ConsoleHandler();
        logr.addHandler(ch);
    }

    /**
     *  This method is called from the Control layer when a someone is trying to register a new user.
     *
     *  Checks if someone with the username or SSN already exists in the DB, and if several people are trying to register
     *  at the same time with the same parameters (transaction error). If any of this occurs, it throws an exception.
     *
     *  Otherwise, it uses BCrypt to encrypt the password of the person, generates a UserID using
     *  generateUserID(), and then uses all the parameters from the PersonDTO to create a Person object,
     *  which is manageable by the integration layer. It then saves the person to the database, clears the transaction
     *  and returns PersonDTO to the view.
     *
     *
     * @param   personDTO  A PersonDTO(Person Data Transfer Object), which contains all necessary data for a person.
     * @return  personDTO  A PersonDTO(Person Data Transfer Object), now with encrypted password and a UserID.
     */
     public PersonDTO registerUser (PersonDTO personDTO)throws ErrorHandling.RegisterUserException {
        if (!portal.getPersonWithSSN(Integer.parseInt(personDTO.getSsn())).isEmpty()) {
            throw new ErrorHandling.RegisterUserException("SSN already exists");
        } else if (!portal.getPersonWithUsername(personDTO.getUserName()).isEmpty()) {
            throw new ErrorHandling.RegisterUserException("Username already exists");
        } else if (TransactionSynchronizationManager.isActualTransactionActive() && TransactionSynchronizationManager.getResource(personDTO).equals(personDTO))
            throw new ErrorHandling.RegisterUserException("Registration Error! Please try again.");
        else {
            TransactionSynchronizationManager.initSynchronization();
            TransactionSynchronizationManager.bindResource(personDTO, personDTO);
            TransactionSynchronizationManager.setCurrentTransactionName(personDTO.getUserName());
            TransactionSynchronizationManager.setActualTransactionActive(true);

            personDTO.setPassword(BCrypt.hashpw(personDTO.getPassword(), BCrypt.gensalt()));

            // FOR TESTING BELOW IS REPLACED WITH "ABCDEFGHIJKLM"
            personDTO.setUserId(generateUserID());

            //portal.registerUser(person);
            // REPLACED BY:
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

    /**
     *  This method is called from the Control layer when a someone is trying to log in(authentication).
     *
     * It checks if the username exists, and then is the username and password is matching.
     *
     * @param   loginDTO  A LoginDTO(Login Data Transfer Object), which contains a username and password.
     * @return  personDTO  A PersonDTO(Person Data Transfer Object), now with encrypted password and a UserID.
     */
    public PersonDTO authenticateUser(LoginDTO loginDTO) throws ErrorHandling.AuthenticateUserException{
        List<Person> personList = portal.getPersonWithUsername(loginDTO.getUsername());
        if(personList.isEmpty())
            throw new ErrorHandling.AuthenticateUserException("Invalid username!");
        else if(BCrypt.checkpw(loginDTO.getPassword(), personList.get(0).getPassword())) {
                PersonDTO authenticatedUser = new PersonDTO();
                BeanUtils.copyProperties(personList.get(0), authenticatedUser);
            return authenticatedUser;
        }
        else
            throw new ErrorHandling.AuthenticateUserException("Invalid password!");
    }

    private static String generateUserID(){

        Random r = new Random();

        String alphabet = "abcdefghijklmnopqrstuvwxyz";
        String userid = "";
        for (int i = 0; i < 25; i++) {
            userid = userid + alphabet.charAt(r.nextInt(alphabet.length()));
        }
        return userid;
    }

    public String getUserID(String username){
        return portal.getPersonWithUsername(username).get(0).getUserID();
    }
}

