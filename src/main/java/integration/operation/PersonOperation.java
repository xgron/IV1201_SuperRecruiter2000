package integration.operation;

import integration.entity.Person;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class PersonOperation {
    /**
     * This method creates an entry to the "person"-table in the DB.
     * @param  person  is of the entity object "Person" and is the object that will be created in the DB.
     * @param  factory  is the programs SessionFactory.
     */
    public static void createPerson(Person person, SessionFactory factory){
        try {
            Session session = factory.getCurrentSession();
            session.beginTransaction();

            session.save(person);

            session.getTransaction().commit();
        } catch (javax.persistence.PersistenceException pe){
            System.out.println("The user could not be created. Username or ssn already in use." );

        } finally {
        }
    }

    /**
     * This method searches in the DB for a "Person"-entry with a specific SSN.
     * @param  userID  is the users ID in the database. This is what will be searched for.
     * * @param  factory  is the programs SessionFactory.
     * @return  Returns the found object. If no object is found, null will be returned.
     */
    public static Person readPerson(String userID, SessionFactory factory){
        try {
            Session session = factory.getCurrentSession();
            session.beginTransaction();

            Person foundPerson = session.get(Person.class, userID);

            session.getTransaction().commit();

            return foundPerson;

        } finally {
        }
    }
}
