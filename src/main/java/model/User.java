package model;

import shared.PersonDTO;

import java.util.Random;

public class User {
    public boolean registerUser(PersonDTO person)
    {
        if(integration.DBPortal.checkIfSSN(person.getSsn()))
            return false;
            else if(integration.DBPortal.checkIfUsername(person.getUserName()))
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
    }
}
