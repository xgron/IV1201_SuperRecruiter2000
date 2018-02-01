package integration.operation;

import integration.entity.Competence;
import org.hibernate.Session;
import org.hibernate.SessionFactory;


public class CompetenceOperation {
    /**
     * This method creates an entry to the "competence"-table in the DB.
     * @param  name  is the name of the new competence.
     * @param  factory  is the programs SessionFactory.
     */
    public static void createCompetence(String name, SessionFactory factory) {
        try {
            Session session = factory.getCurrentSession();
            session.beginTransaction();

            Competence comp = new Competence(name);

            session.save(comp);

            session.getTransaction().commit();

        } finally {
        }
    }

    /**
     * This method searches in the DB for a "competence"-entry with a specific SSN.
     * @param    name   is the name of the competence that will be searched for.
     * @param  factory  is the programs SessionFactory.
     * @return  Returns the found object. If no object is found, null will be returned.
     */
    public static Competence readCompetence(String name, SessionFactory factory){
        try {
            Session session = factory.getCurrentSession();
            session.beginTransaction();

            Competence foundComp = session.get(Competence.class, name);

            session.getTransaction().commit();

            return foundComp;
        } finally {
        }
    }
}
