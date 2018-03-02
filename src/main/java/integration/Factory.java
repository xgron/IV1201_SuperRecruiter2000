package integration;

import integration.entity.*;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;


import javax.inject.Singleton;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

@Singleton
public class Factory {
    private static SessionFactory factory = new Configuration().configure()
            .addAnnotatedClass(Person.class)
            .addAnnotatedClass(Role.class)
            .addAnnotatedClass(Experience.class)
            .addAnnotatedClass(Competence.class)
            .addAnnotatedClass(Availability.class)
            .buildSessionFactory();

    public static SessionFactory getFactory() {
        return factory;
    }

    public static ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();

    public static void setFactory(SessionFactory factory) {
        Factory.factory = factory;
    }

    public Factory() {
    }
}
