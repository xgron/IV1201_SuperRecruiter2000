package integration.operation;

import integration.entity.Person;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class PersonOperation {
    public static void createPerson(Person person, SessionFactory factory, Session session){
        try {
            session.beginTransaction();

            session.save(person);

            session.getTransaction().commit();
        } catch (javax.persistence.PersistenceException pe){
            System.out.println("The user could not be created. Username or ssn already in use." );

        } finally {
            factory.close();
        }
    }

    public static Person readPerson(int ssn, SessionFactory factory, Session session){
        try {
            session = factory.getCurrentSession();
            session.beginTransaction();

            Person foundPerson = session.get(Person.class, ssn);

            session.getTransaction().commit();

            return foundPerson;

        } finally {
            factory.close();
        }
    }
}
