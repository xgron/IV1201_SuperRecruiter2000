package integration;

import integration.entity.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import shared.PersonDTO;
import shared.PublicApplicationDTO;

import javax.validation.Validator;
import java.sql.Date;
import java.util.Calendar;
import java.util.List;

import java.util.logging.*;

public class DBPortal {
    private final static Logger LOG = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

    private Factory factoryObj = new Factory();
    private SessionFactory factory = factoryObj.getFactory();
    Session session = factory.getCurrentSession();

    /**
     * This method will create an object of Experience in the database.
     * @param experience is an object of Experience
     */
    public void saveExperience(Experience experience){
        Validator validator = factoryObj.validatorFactory.getValidator();
        validator.validate(experience);
        session = factory.getCurrentSession();
        session.beginTransaction();
        session.save(experience);
        session.getTransaction().commit();
    }

    /**
     * This method will create an object of Person in the database.
     * @param person is a Person object
     */
    public void savePerson(Person person){
        Validator validator = factoryObj.validatorFactory.getValidator();
        validator.validate(person);
        session = factory.getCurrentSession();
        session.beginTransaction();
        session.save(person);
        session.getTransaction().commit();
    }

    /**
     * This method will update a Person object in the DB if changes has been made.
     * @param person is a Person object
     */
    public void updatePerson(Person person){
        Validator validator = factoryObj.validatorFactory.getValidator();
        validator.validate(person);
        session = factory.getCurrentSession();
        session.beginTransaction();
        session.update(person);
        session.getTransaction().commit();
    }

    /**
     * This method creates an entry to the "availability"-table in the DB.
     * @param  availability  is of the entity object "Availability" and is the object that will be created in the DB.
     */
    public void createAvailability(Availability availability){
        Validator validator = factoryObj.validatorFactory.getValidator();
        validator.validate(availability);
        Session session = factory.getCurrentSession();
        session.beginTransaction();
        session.save(availability);
        session.getTransaction().commit();
    }

    /**
     * This method searches for a person in the DB with a specific username
     * @param username is the username that is being searched for.
     * @return A list with persons found. Username will be unique, meaning the list will be at most one entry long.
     */
    public List<Person> getPersonWithUsername(String username){
        session = factory.getCurrentSession();
        session.beginTransaction();
        List<Person> personList = session.createQuery("from Person p where p.username='" + username + "'").getResultList();
        session.getTransaction().commit();
        return personList;
    }

    /**
     * This method will search for all persons having a specific role.
     * @param role is the specific role searched for.
     * @return A list of found persons.
     */
    public List<Person> getPersonWithRole(String role){
        session = factory.getCurrentSession();
        session.beginTransaction();
        List<Person> personList = session.createQuery("from Person p where p.role='" + role + "'").getResultList();
        session.getTransaction().commit();
        return personList;
    }

    /**
     * This method searches for a person in the DB with a specific user ID
     * @param userID is the user ID that is being searched for.
     * @return A list with persons found. User ID will be unique, meaning the list will be at most one entry long.
     */
    public List<Person> getPersonWithUserID(String userID){
        session = factory.getCurrentSession();
        session.beginTransaction();
        List<Person> personList = session.createQuery("from Person p where p.userID='" + userID + "'").getResultList();
        session.getTransaction().commit();
        return personList;
    }

    /**
     * This method searches for a person in the DB with a specific ssn
     * @param ssn is the ssn that is being searched for.
     * @return A list with persons found. Ssn will be unique, meaning the list will be at most one entry long.
     */
    public List<Person> getPersonWithSSN(int ssn){
        session = factory.getCurrentSession();
        session.beginTransaction();
        List<Person> personList = session.createQuery("from Person p where p.ssn='" + ssn + "'").getResultList();
        session.getTransaction().commit();
        return personList;
    }

    /**
     * This method will return a list of competences matching the string. Is used for checking if the competence exist or not.
     * @param competence is the competence being searched for
     * @return
     */
    public List<Competence> getCompetence(String competence){
        session = factory.getCurrentSession();
        session.beginTransaction();
        List<Competence> competenceList = session.createQuery("from Competence c where c.name='" + competence + "'").getResultList();
        session.getTransaction().commit();
        return competenceList;
    }

    /**
     * This method will return all possible competences.
     * @return All possible competences.
     */
    public List<Competence> getAllCompetences(){
        session = factory.getCurrentSession();
        session.beginTransaction();
        List<Competence> competenceList = session.createQuery("from Competence").getResultList();
        session.getTransaction().commit();
        return competenceList;
    }


    /**
     * This method searches in the DB for a "Person"-entry with a specific SSN.
     * @param  userID  is the users ID in the database. This is what will be searched for.
     * @return  Returns the found object. If no object is found, null will be returned.
     */
    public Person readPerson(String userID){
        Session session = factory.getCurrentSession();
        session.beginTransaction();
        Person foundPerson = session.get(Person.class, userID);
        session.getTransaction().commit();
        return foundPerson;
    }
}