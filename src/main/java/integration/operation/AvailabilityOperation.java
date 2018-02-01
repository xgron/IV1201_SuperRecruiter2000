package integration.operation;

import integration.entity.Availability;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class AvailabilityOperation {
    /**
     * This method creates an entry to the "availability"-table in the DB.
     * @param  availability  is of the entity object "Availability" and is the object that will be created in the DB.
     * @param  factory  is the programs SessionFactory.
     */
    public static void createAvailability(Availability availability, SessionFactory factory){
        try {
            Session session = factory.getCurrentSession();
            session.beginTransaction();

            session.save(availability);

            session.getTransaction().commit();

        } finally {
        }
    }

    /**
     * This method searches in the DB for an "Availability"-entry with a specific SSN.
     * @param  ssn  is the key that will be searched for.
     * @param  factory  is the programs SessionFactory.
     * @return  Returns the found object. If no object is found, null will be returned.
     */
    public static Availability readAvailability(int ssn, java.sql.Date startDate, SessionFactory factory){
        try {
            Session session = factory.getCurrentSession();
            session.beginTransaction();

            Availability av = new Availability(ssn, startDate);

            Availability foundRole = session.get(Availability.class, av);

            session.getTransaction().commit();

            return foundRole;
        } finally {
        }
    }
}
