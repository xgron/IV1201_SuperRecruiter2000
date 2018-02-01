package integration.operation;

import integration.entity.Experience;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class ExperienceOperation {
    /**
     * This method creates an entry to the "experience"-table in the DB.
     * @param  experience  is of the entity object "Experience" and is the object that will be created in the DB.
     * @param  factory  is the programs SessionFactory.
     */
    public static void createExperience(Experience experience, SessionFactory factory){
        try {
            Session session = factory.getCurrentSession();
            session.beginTransaction();

            session.save(experience);

            session.getTransaction().commit();

        } finally {
        }
    }

    /**
     * This method searches in the DB for an "Experience"-entry with a specific SSN.
     * @param  ssn  is the key that will be searched for.
     * @param  factory  is the programs SessionFactory.
     * @return  Returns the found object. If no object is found, null will be returned.
     */
    public static Experience readExperience(int ssn, String comp, SessionFactory factory){
        try {
            Session session = factory.getCurrentSession();
            session.beginTransaction();

            Experience exp = new Experience(ssn, comp);

            Experience foundRole = session.get(Experience.class, exp);

            session.getTransaction().commit();

            return foundRole;
        } finally {
        }
    }
}
