package integration;


import integration.entity.*;
import integration.entity.Person;
import integration.operation.PersonOperation;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import org.hibernate.cfg.Configuration;
import shared.PersonDTO;

import java.sql.Date;
import java.util.Calendar;
import java.util.List;


public class DBPortal {
    private Factory factoryObj = new Factory();
    private SessionFactory factory = factoryObj.getFactory();

    private Session session = factory.getCurrentSession();

    /**
     * This method will register a user to the database
     * @param  PersonDTO    the object containing all user data to be saved to the database
     */
    public void registerUser(PersonDTO personDTO){
        int ssn = Integer.parseInt(personDTO.getSsn());
        String name = personDTO.getFirstName();
        String surname = personDTO.getSurname();
        String email = personDTO.getEmail();
        String password = personDTO.getPassword();
        String role_name = "applicant";
        String username = personDTO.getUserName();
        Boolean hired = null;
        Date registrationdate = new java.sql.Date(Calendar.getInstance().getTime().getTime());
        String userID = personDTO.getUserId();

        Person person = new Person(ssn, name, surname, email, password, role_name, username, hired, registrationdate, userID);

        PersonOperation.createPerson(person, factory);
    }

    /**
     * This method checks if the username is taken or not. Will return true if the username is taken, and false if it is free.
     * @param  username     the username that is checked
     * @return      the answer to "is username XXX taken?"
     */
    public Boolean usernameTaken(String username){
        try{
            session = factory.getCurrentSession();
            session.beginTransaction();
            List<Person> personList = session.createQuery("from Person p where p.username='" + username + "'").getResultList();
            int c = 0;
            for(Person tp : personList){
                c++;
            }
            session.getTransaction().commit();
            if(c == 0){
                System.out.println("* username: " + username + " is not taken.");
                return false;
            }
            else{
                System.out.println("* username: " + username + " is taken.");
                return true;
            }
        }finally {
        }

    }

    /**
     * This method checks if the SSN is taken or not. Will return true if the SSN is taken, and false if it is free.
     * @param  ssn     the SSN that is checked
     * @return      the answer to "is SSN XXX taken?"
     */
    public Boolean ssnTaken(int ssn){
        try{
            session = factory.getCurrentSession();
            session.beginTransaction();
            List<Person> personList = session.createQuery("from Person p where p.ssn='" + ssn + "'").getResultList();
            int c = 0;
            for(Person tp : personList){
                c++;
            }
            session.getTransaction().commit();
            if(c == 0){
                System.out.println("* SSN: " + ssn + " is not taken.");
                return false;
            }
            else{
                System.out.println("* SSN: " + ssn + " is taken.");
                return true;
            }
        }finally {
        }
    }
}