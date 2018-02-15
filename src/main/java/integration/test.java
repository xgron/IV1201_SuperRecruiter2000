package integration;

import integration.entity.*;
import org.hibernate.Session;
import org.hibernate.boot.model.source.spi.PluralAttributeElementSourceManyToAny;

import java.sql.Date;
import java.util.Calendar;

public class test {
    public static void main(String[] args) {
        DBPortal portal = new DBPortal();

        System.out.println(portal.getAllCompetences());
    }
}
