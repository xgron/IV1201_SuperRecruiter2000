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
    public PersonDTO registerUser (PersonDTO personDTO)throws ErrorHandling.RegisterUserExeption
    {
        if(ssnTaken(Integer.parseInt(personDTO.getSsn()))) {
            throw new ErrorHandling.RegisterUserExeption("SSN already exists");
        }else if(usernameTaken(personDTO.getUserName())) {
            throw new ErrorHandling.RegisterUserExeption("Username already exists");
        }else
            {
                personDTO.setPassword(BCrypt.hashpw(personDTO.getPassword(), BCrypt.gensalt()))   ;

                // FOR TESTING BELOW IS REPLACED WITH "ABCDEFGHIJKLM"
                //personDTO.setUserId(generateUserID());
                personDTO.setUserId("ABCDEFGHIJKLM");

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
                //portal.createPerson(person);

                portal.savePerson(person);
                return personDTO;
    @Transactional
    public PersonDTO registerUser (PersonDTO person)throws ErrorHandling.RegisterUserException
    {

        if(ssnTaken(Integer.parseInt(person.getSsn()))) {
            TransactionSynchronizationManager.clear();
            throw new ErrorHandling.RegisterUserException("SSN already exists");
        }else if(usernameTaken(person.getUserName())) {
            TransactionSynchronizationManager.clear();
            throw new ErrorHandling.RegisterUserException("Username already exists");
        }else
            {
                if(TransactionSynchronizationManager.isActualTransactionActive())
                    if (TransactionSynchronizationManager.getCurrentTransactionName().equals(person.getUserId()))
                        throw new ErrorHandling.RegisterUserException("Username already exists");

                TransactionSynchronizationManager.initSynchronization();
                TransactionSynchronizationManager.setCurrentTransactionName(person.getUserId());
                TransactionSynchronizationManager.setActualTransactionActive(true);

                person.setPassword(BCrypt.hashpw(person.getPassword(), BCrypt.gensalt()))   ;
                person.setUserId(generateUserID());
                portal.registerUser(person);
                TransactionSynchronizationManager.clear();
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

    /**
     * This method checks if the username is taken or not. Will return true if the username is taken, and false if it is free.
     * @param  username     the username that is checked
     * @return      the answer to "is username XXX taken?"
     */
    private Boolean usernameTaken(String username){
        try{
            List<Person> personList = portal.getPersonWithUsername(username);
            return !personList.isEmpty();
        } catch(Exception e){

        }
        return null;
    }

    /**
     * This method checks if the SSN is taken or not. Will return true if the SSN is taken, and false if it is free.
     * @param  ssn     the SSN that is checked
     * @return      the answer to "is SSN XXX taken?"
     */
    private Boolean ssnTaken(int ssn){
        try {
            List<Person> personList = portal.getPersonWithSSN(ssn);
            return !personList.isEmpty();
        }catch (Exception e){

        }
        return null;
    }
}