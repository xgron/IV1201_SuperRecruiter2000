package model;

import integration.DBPortal;
import integration.entity.*;
import integration.entity.Person;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.BeanUtils;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.transaction.support.TransactionSynchronizationManager;
import shared.*;

import java.io.UnsupportedEncodingException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import javax.transaction.Transactional;

import java.util.List;
import java.util.Random;
import java.util.logging.*;



public class User {
    private final static Logger LOG = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

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
    @Transactional
     public PersonDTO registerUser (PersonDTO personDTO)throws ErrorHandling.CommonException {

        try {
             if (!portal.getPersonWithSSN(Integer.parseInt(personDTO.getSsn())).isEmpty()) {
                 throw new ErrorHandling.CommonException(ErrorMessages.EXISTING_SSN_MESSAGE.getErrorMessage());
             } else if (!portal.getPersonWithUsername(personDTO.getUserName()).isEmpty()) {
                 throw new ErrorHandling.CommonException(ErrorMessages.EXISTING_USERNAME_MESSAGE.getErrorMessage());
             }/* else if (TransactionSynchronizationManager.isActualTransactionActive() && TransactionSynchronizationManager.getCurrentTransactionName()==personDTO.getUserName())
                 throw new ErrorHandling.RegisterUserException("Registration Error! Please try again.");*/
             else {
                /* TransactionSynchronizationManager.initSynchronization();
                 TransactionSynchronizationManager.setCurrentTransactionName(personDTO.getUserName());
                 TransactionSynchronizationManager.setActualTransactionActive(true);*/

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
                 LOG.log(Level.INFO, "User " + name + " " + surname + " registered.");
                 //TransactionSynchronizationManager.clear();
                 return personDTO;
             }
         }catch (Exception e) {
             LOG.info("Exception in integration layer: " + e);
         }
        return null;
    }

    /**
     *  This method is called from the Control layer when a someone is trying to log in(authentication).
     *
     * It checks if the username exists, and then is the username and password is matching.
     *
     * @param   loginDTO  A LoginDTO(Login Data Transfer Object), which contains a username and password.
     * @return  personDTO  A PersonDTO(Person Data Transfer Object), now with encrypted password and a UserID.
     */
    public PersonDTO authenticateUser(LoginDTO loginDTO) throws ErrorHandling.CommonException {
        try {
            List<Person> personList = portal.getPersonWithUsername(loginDTO.getUsername());
            if (personList.isEmpty())
                throw new ErrorHandling.CommonException(ErrorMessages.INVALID_USERNAME_MESSAGE.getErrorMessage());
            else if (BCrypt.checkpw(loginDTO.getPassword(), personList.get(0).getPassword())) {
                PersonDTO authenticatedUser = new PersonDTO();

                LOG.log(Level.INFO, "User " + authenticatedUser.getFirstName() + " " + authenticatedUser.getSurname() + " authenticated.");
                return authenticatedUser;
            } else
                throw new ErrorHandling.CommonException(ErrorMessages.INVALID_PASSWORD_MESSAGE.getErrorMessage());
        }catch (Exception e) {
            LOG.info("Exception in integration layer: " + e);
        }
        return null;
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
    /**
     * This method returns the userID of a specific username
     * @param username is the username of the user
     * @return returns the userID of that user
     */
    public String getUserID(String username){
        try{
        return portal.getPersonWithUsername(username).get(0).getUserID();
        }catch (Exception e) {
            LOG.info("Exception in integration layer: " + e);
        }
        return null;
    }

    /**
     *  This method is to get the role of a user
     *
     * @param   userID  current user
     *
     * @return  role    a string with the role
     */
    public String getRole(String userID){
        try {
            return portal.getPersonWithUserID(userID).get(0).getRole().getName();
        }catch (Exception e) {
            LOG.info("Exception in integration layer: " + e);
        }
        return null;
    }
    private String jwtBuilder(String userID){
        try {
            return Jwts.builder()
                    .setSubject("users/" + userID)
                    .setExpiration(new Date(2030119380))
                    .signWith(
                            SignatureAlgorithm.HS256,
                            "secret".getBytes("UTF-8")
                    )
                    .compact();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    return null;
    }
}

