package model;

import integration.DBPortal;
import org.springframework.security.crypto.bcrypt.BCrypt;
import shared.PersonDTO;

import java.util.Random;

public class User {

    DBPortal portal;
    public User(DBPortal portal){
        this.portal = portal;
    }
    public boolean registerUser(PersonDTO person)
    {
        if(portal.ssnTaken(Integer.parseInt(person.getSsn())))
            return false;
            else if(portal.usernameTaken(person.getUserName()))
                return false;
            else
                {
                    person.setPassword(BCrypt.hashpw(person.getPassword(), BCrypt.gensalt()))   ;
                    person.setUserId(generateUserID());
                    integration.DBPortal.registerUser(person);
                    return true;
                }

    }
    public boolean loginUser(String Username, String Password){
        return false;
    }

    public String generateUserID(){

        Random r = new Random();

        String alphabet = "abcdefghijklmnopqrstuvxyz";
        String userid = "";
        for (int i = 0; i < 25; i++) {
            userid = userid + alphabet.charAt(r.nextInt(alphabet.length()));
        }
        return userid;
    }
}