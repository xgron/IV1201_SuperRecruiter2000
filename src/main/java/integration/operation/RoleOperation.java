package integration.operation;

import integration.entity.Role;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class RoleOperation {
    /**
     * This method creates an entry to the "role"-table in the DB.
     * @param  role  is of the entity object "Role" and is the object that will be created in the DB.
     * @param  factory  is the programs SessionFactory.
     */
    public static void createRole(Role role, SessionFactory factory){
        try {
            Session session = factory.getCurrentSession();
            session.beginTransaction();

            session.save(role);

            session.getTransaction().commit();

        } finally {
        }
    }

    /**
     * This method searches in the DB for an "Role"-entry with a specific SSN.
     * @param  name  is the name that will be searched for in the DB.
     * @param  factory  is the programs SessionFactory.
     * @return  Returns the found object. If no object is found, null will be returned.
     */
    public static Role readRole(String name, SessionFactory factory){
        try {
            Session session = factory.getCurrentSession();
            session.beginTransaction();

            Role foundRole = session.get(Role.class, name);

            session.getTransaction().commit();

            return foundRole;
        } finally {
        }
    }
}
