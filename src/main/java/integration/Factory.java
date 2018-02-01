package integration;

import integration.entity.*;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import javax.inject.Singleton;

@Singleton
public class Factory {
    private static SessionFactory factory = new Configuration().configure()
            .addAnnotatedClass(Role.class)
            .addAnnotatedClass(Person.class)
            .addAnnotatedClass(Experience.class)
            .addAnnotatedClass(Competence.class)
            .addAnnotatedClass(Availability.class)
            .buildSessionFactory();

    public static SessionFactory getFactory() {
        return factory;
    }

    public static void setFactory(SessionFactory factory) {
        Factory.factory = factory;
    }

    public Factory() {
    }
}
