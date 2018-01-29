package integration.operation;

import integration.entity.Competence;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class CompetenceOperation {
    public static void createCompetence(String name, SessionFactory factory, Session session) {
        try {
            session.beginTransaction();

            Competence comp = new Competence(name);

            session.save(comp);

            session.getTransaction().commit();

        } finally {
            factory.close();
        }
    }

    public static Competence readCompetence(String name, SessionFactory factory, Session session){
        try {
            session = factory.getCurrentSession();
            session.beginTransaction();

            Competence foundComp = session.get(Competence.class, name);

            session.getTransaction().commit();

            return foundComp;
        } finally {
            factory.close();
        }
    }
}
