package model;

import shared.PersonDTO;

public class TESTER_Model {
    public static void main(String[] args) {
        Model model = new Model();
        PersonDTO pd = new PersonDTO();
        pd.setFirstName("Frodo");
        pd.setSurname("Baggins");
        pd.setSsn("000000");
        pd.setEmail("frodo@bag.end");
        pd.setPassword("lösenord");
        pd.setUserName("frodo");
        //System.out.println(model.user.registerUser(pd));
    }
}
