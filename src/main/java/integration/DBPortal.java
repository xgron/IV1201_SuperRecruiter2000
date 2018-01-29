package integration;

import integration.entity.*;
import integration.operation.PersonOperation;
import integration.operation.AvailabilityOperation;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import org.hibernate.cfg.Configuration;

import java.sql.Date;
import java.util.Calendar;



public class DBPortal {
    private static SessionFactory factory = new Configuration().configure()
            .addAnnotatedClass(Role.class)
            .addAnnotatedClass(Person.class)
            .addAnnotatedClass(Experience.class)
            .addAnnotatedClass(Competence.class)
            .addAnnotatedClass(Availability.class)
            .buildSessionFactory();

    private static Session session = factory.getCurrentSession();

    public static void registerUser(){

    }
}