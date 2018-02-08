package integration;

import integration.entity.Experience;
import integration.entity.Person;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class DBPortal {
    private Factory factoryObj = new Factory();
    private SessionFactory factory = factoryObj.getFactory();

    public void createExperience(Experience experience){
        try {
            Session session = factory.getCurrentSession();
            session.beginTransaction();
            session.save(experience);
            session.getTransaction().commit();

        } finally {
        }
    }

    public void createPerson(Person person){
        try {
            Session session = factory.getCurrentSession();
            session.beginTransaction();
            session.save(person);
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
}