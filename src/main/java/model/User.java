package model;

import java.util.Random;

public class User {
    /*public static boolean registerUser(PersonDTO person)
    {
        if(integration.dbPortal.checkIfExists(person.getSSN, "person", "SSN"))
            return false;
            else if(integration.dbPortal.checkIfExists(person.getUsername, "person", "username"))
                return false;
            else
                {
                    person.setID(generateUserID());
                    integration.dbPortal.createUser(person);
                }
    }

    public static String generateUserID(){

        Random r = new Random();

        String alphabet = "abcdefghijklmnopqrstuvxyz";
        String userid = "";
        for (int i = 0; i < 25; i++) {
            userid = userid + alphabet.charAt(r.nextInt(alphabet.length()));
        }
        return userid;
    }*/
}
