package integration.operation;

import integration.entity.Availability;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class AvailabilityOperation {
    public static void createAvailability(Availability av, SessionFactory factory, Session session){
        try {
            session.beginTransaction();

            session.save(av);

            session.getTransaction().commit();

        } finally {
            factory.close();
        }
    }

    public static Availability readAvailability(int ssn, java.sql.Date startDate, SessionFactory factory, Session session){
        try {
            session = factory.getCurrentSession();
            session.beginTransaction();

            Availability av = new Availability(ssn, startDate);

            Availability foundRole = session.get(Availability.class, av);

            session.getTransaction().commit();

            return foundRole;
        } finally {
            factory.close();
        }
    }
}
