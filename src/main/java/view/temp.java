package view;

import controller.HomeController;
import model.ErrorHandling;

public class temp {
    public static void main(String[] args) {
        HomeController hc = new HomeController();

        System.out.println("User: " + hc.getUserID("thehulk"));
        System.out.println("Recruiter: " + hc.getUserID("recruiter"));

        try {
            hc.evaluateApplication(hc.getUserID("thehulk"), true, hc.getUserID("recruiter"));
        } catch (ErrorHandling.CommonException e) {
            e.printStackTrace();
        }
    }
}
