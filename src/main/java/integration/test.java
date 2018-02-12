package integration;

import integration.entity.*;
import org.hibernate.Session;

import java.sql.Date;
import java.util.Calendar;

public class test {
    public static void main(String[] args) {
        DBPortal portal = new DBPortal();

        Person person = portal.getPersonWithUserID("ABCDEFGHIJKLM").get(0);
        System.out.println(person);
    }
}
