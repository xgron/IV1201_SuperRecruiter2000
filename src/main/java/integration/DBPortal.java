package integration;

import integration.entity.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import shared.PersonDTO;

import java.sql.Date;
import java.util.Calendar;
import java.util.List;

public class DBPortal {
    private Factory factoryObj = new Factory();
    private SessionFactory factory = factoryObj.getFactory();
    Session session = factory.getCurrentSession();

    //JAVADOC TO DO
    public void createExperience(Experience experience){
        session = factory.getCurrentSession();
        session.beginTransaction();
        session.save(experience);
        session.getTransaction().commit();
    }

    //JAVADOC TO DO
    public void savePerson(Person person){
        session = factory.getCurrentSession();
        session.beginTransaction();
        session.save(person);
        session.getTransaction().commit();
    }

    //JAVADOC TO DO
    public void updatePerson(Person person){
        session = factory.getCurrentSession();
        session.beginTransaction();
        session.update(person);
        session.getTransaction().commit();
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

    //JAVADOC TO DO
    public void createPerson(Person person){
        Session session = factory.getCurrentSession();
        session.beginTransaction();
        session.save(person);
        session.getTransaction().commit();
    }

    /**
     * This method creates an entry to the "availability"-table in the DB.
     * @param  availability  is of the entity object "Availability" and is the object that will be created in the DB.
     */
    public void createAvailability(Availability availability){
        Session session = factory.getCurrentSession();
        session.beginTransaction();
        session.save(availability);
        session.getTransaction().commit();
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