package controller;

import shared.PersonDTO;

public class TESTER_Controller {
    public static void main(String[] args) {
        HomeController hc = new HomeController();

        PersonDTO pd = new PersonDTO();
        pd.setFirstName("Samwise");
        pd.setSurname("Gamgee");
        pd.setSsn("1234");
        pd.setEmail("sam@bag.end");
        pd.setPassword("lösenord");
        pd.setUserName("sam");

        //hc.registerUser(pd);
    }
}
