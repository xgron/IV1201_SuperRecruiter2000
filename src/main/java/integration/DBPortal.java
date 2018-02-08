package integration;


import integration.entity.*;
import integration.entity.Person;
import integration.unused_operations.PersonOperation;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import shared.PersonDTO;

import java.sql.Date;

import java.util.Calendar;
import java.util.List;


public class DBPortal {
    private Factory factoryObj = new Factory();
    private SessionFactory factory = factoryObj.getFactory();

    private Session session = factory.getCurrentSession();

    /**
     * This method will register a user to the database
     * @param  personDTO    the object containing all user data to be saved to the database
     */
    public void registerUser(PersonDTO personDTO){
        int ssn = Integer.parseInt(personDTO.getSsn());
        String name = personDTO.getFirstName();
        String surname = personDTO.getSurname();
        String email = personDTO.getEmail();
        String password = personDTO.getPassword();
        String role_name = "applicant";
        String username = personDTO.getUserName();
        Boolean hired = null;
        Date registrationdate = new java.sql.Date(Calendar.getInstance().getTime().getTime());
        String userID = personDTO.getUserId();

        Person person = new Person(ssn, name, surname, email, password, role_name, username, hired, registrationdate, userID);

        PersonOperation.createPerson(person, factory);
    }

    //JAVADOC TO DO
    public List<Person> getPersonWithUsername(String username){
        session = factory.getCurrentSession();
        session.beginTransaction();
        List<Person> personList = session.createQuery("from Person p where p.username='" + username + "'").getResultList();
        session.getTransaction().commit();
        return personList;
    }

    //JAVADOC TO DO
    public List<Person> getPersonWithUserID(String userID){
        session = factory.getCurrentSession();
        session.beginTransaction();
        List<Person> personList = session.createQuery("from Person p where p.userID='" + userID + "'").getResultList();
        session.getTransaction().commit();
        return personList;
    }

    //JAVADOC TO DO
    public List<Person> getPersonWithSSN(int ssn){
        session = factory.getCurrentSession();
        session.beginTransaction();
        List<Person> personList = session.createQuery("from Person p where p.ssn='" + ssn + "'").getResultList();
        session.getTransaction().commit();
        return personList;
    }

    //JAVADOC TO DO
    public List<Competence> getCompetence(String competence){
        session = factory.getCurrentSession();
        session.beginTransaction();
        List<Competence> competenceList = session.createQuery("from Competence c where c.name='" + competence + "'").getResultList();
        session.getTransaction().commit();
        return competenceList;
    }

    /**
     * This method creates an entry to the "availability"-table in the DB.
     * @param  availability  is of the entity object "Availability" and is the object that will be created in the DB.
     */
    public void createAvailability(Availability availability){
        try {
            Session session = factory.getCurrentSession();
            session.beginTransaction();
            session.save(availability);
            session.getTransaction().commit();

        } finally {
        }
    }

    /**
     * This method searches in the DB for a "Person"-entry with a specific SSN.
     * @param  userID  is the users ID in the database. This is what will be searched for.
     * @return  Returns the found object. If no object is found, null will be returned.
     */
    public Person readPerson(String userID){
        try {
            Session session = factory.getCurrentSession();
            session.beginTransaction();
            Person foundPerson = session.get(Person.class, userID);
            session.getTransaction().commit();

            return foundPerson;

        } finally {
        }
    }
    /**
     * This method creates an entry to the "experience"-table in the DB.
     * @param  experience  is of the entity object "Experience" and is the object that will be created in the DB.
     */
    public void createExperience(Experience experience){
        try {
            Session session = factory.getCurrentSession();
            session.beginTransaction();
            session.save(experience);
            session.getTransaction().commit();

        } finally {
        }
    }
}