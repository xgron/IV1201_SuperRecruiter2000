package integration.operation;

import integration.entity.Experience;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class ExperienceOperation {
    public static void createExperience(Experience exp, SessionFactory factory, Session session){
        try {
            session.beginTransaction();

            session.save(exp);

            session.getTransaction().commit();

        } finally {
            factory.close();
        }
    }

    public static Experience readExperience(int ssn, String comp, SessionFactory factory, Session session){
        try {
            session = factory.getCurrentSession();
            session.beginTransaction();

            Experience exp = new Experience(ssn, comp);

            Experience foundRole = session.get(Experience.class, exp);

            session.getTransaction().commit();

            return foundRole;
        } finally {
            factory.close();
        }
    }
}
