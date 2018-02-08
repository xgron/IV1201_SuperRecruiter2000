package integration.unused_operations;

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


}
