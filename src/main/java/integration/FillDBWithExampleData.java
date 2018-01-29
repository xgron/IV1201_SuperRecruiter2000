package integration;

import com.github.vatbub.randomusers.Generator;
import com.github.vatbub.randomusers.result.RandomUser;
import integration.entity.Person;

import java.sql.Date;
import java.util.Calendar;
import java.util.List;

public class FillDBWithExampleData {
    public static void main(String[] args) {
        person(10);
    }

    public static void person(int amount){
        //List<String> nationalities = new List<String>;
        //nationalities.add()

        for(int i = 0; i < amount; i++) {

            //RandomUser ru = Generator.generateRandomUser(new RandomUser.RandomUserSpec().setNationalities(nationalities));


            int ssn = (int)(Math.random()*10000);
            //String name = ru.getName().toString();

            //System.out.println(name.split(" ")[1]);


            /*
            String surname = ru.get;
            String email;
            String password;
            String role_name;
            String username;
            Boolean hired = null;
            Date registrationdate = new java.sql.Date(Calendar.getInstance().getTime().getTime());

            System.out.println((ssn + ", " + name + ", " + surname + ", " + email + ", " + password + ", " + role_name + ", " + username + ", " + hired + ", " + registrationdate));
*/
            //Person person = new Person(ssn, name, surname, email, password, role_name, username, hired, registrationdate);
        }

    }
}
