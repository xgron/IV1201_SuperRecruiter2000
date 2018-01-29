package integration.operation;

import integration.entity.Role;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class RoleOperation {
    public static void createRole(Role role, SessionFactory factory, Session session){
        try {
            session.beginTransaction();

            session.save(role);

            session.getTransaction().commit();

        } finally {
            factory.close();
        }
    }

    public static Role readRole(String name, SessionFactory factory, Session session){
        try {
            session = factory.getCurrentSession();
            session.beginTransaction();

            Role foundRole = session.get(Role.class, name);

            session.getTransaction().commit();

            return foundRole;
        } finally {
            factory.close();
        }
    }
}
