package model;

import integration.DBPortal;
import shared.PersonDTO;

import java.util.Random;

public class User {
    /*public static boolean registerUser(PersonDTO person)

    DBPortal portal;
    public User(DBPortal portal){
    this.portal = portal;
    }
    public boolean registerUser(PersonDTO person)
    {
        if(portal.ssnTaken(person.getSsn()))
            return false;
            else if(portal.usernameTaken(person.getUserName()))
                return false;
            else
                {
                    person.setUserId(generateUserID());
                    integration.DBPortal.registerUser(person);
                }
    }

    public String generateUserID(){

        Random r = new Random();

        String alphabet = "abcdefghijklmnopqrstuvxyz";
        String userid = "";
        for (int i = 0; i < 25; i++) {
            userid = userid + alphabet.charAt(r.nextInt(alphabet.length()));
        }
        return userid;
    }*/
}
