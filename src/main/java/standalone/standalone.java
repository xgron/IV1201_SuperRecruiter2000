package standalone;

import java.io.*;
import java.net.*;
import java.nio.charset.StandardCharsets;
import java.util.NoSuchElementException;
import java.util.Scanner;

import org.hibernate.boot.jaxb.SourceType;
import org.json.JSONArray;
import org.json.JSONObject;


public class standalone {
    public static Scanner reader = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("Server ip?");
        String ip = reader.next();
        //reader.close();

        while(1>0){
            Scanner input = new Scanner(System.in);
            System.out.println("Action? (print-applicants, register-user)");
            String select = reader.next();

            if(select.contentEquals("print-applicants" ) || select.contentEquals("print" )){
                try {
                    printApplicants(ip);
                } catch (Exception e) {
                    System.out.println("Failed to run printApplicants.");;
                }
            }else if(select.contentEquals("register-user") || select.contentEquals("register" )){
                try {
                    registerUser(ip);
                } catch (Exception e) {
                    System.out.println("Failed to run registerUser.");
                }
            }else {
                System.out.println("What?");
            }
        }


    }

    private static void printApplicants(String ip) throws Exception {
        StringBuilder result = new StringBuilder();
        URL url = new URL("http://" + ip + ":8080/api/users");
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        String line;
        while ((line = rd.readLine()) != null) {
            result.append(line);
        }
        rd.close();

        String json = result.toString();
        JSONArray applications = new JSONArray(json);
        System.out.println("APPLICATIONS:");
        for (int i = 0; i < applications.length(); i++) {
            JSONObject application = applications.getJSONObject(i);
            JSONArray availabilities = application.getJSONArray("availabilities");
            JSONArray experiences = application.getJSONArray("experience");
            if(availabilities.length() < 1 || experiences.length() < 1){
                continue;
            }

            String name = application.getString("name");
            String surname = application.getString("surname");
            int ssn = application.getInt("ssn");
            String email = application.getString("email");
            String regDate = application.getString("registrationdate");
            String userID = application.getString("userID");
            System.out.println("Application for: " + name + " " + surname +
                                "\n    SSN: " + ssn + ", email: " + email +
                                "\n    date of registration: " + regDate + ", userID: " + userID);

            System.out.println("     Experience:");
            for(int j = 0; j < experiences.length(); j++){
                JSONObject experience = experiences.getJSONObject(j);
                String experienceName = experience.getString("name");
                double years = experience.getDouble("years");
                System.out.println("       " + experienceName + " for " + years + " years");
            }

            System.out.println("     Available:");
            for(int j = 0; j < availabilities.length(); j++){
                JSONObject availability = availabilities.getJSONObject(j);
                String startdate = availability.getString("start");
                String enddate = availability.getString("end");
                System.out.println("       " + startdate + " -> " + enddate);
            }
            System.out.println("");
        }
    }

    public static void registerUser(String ip) throws Exception{
        URL url = new URL("http://" + ip + ":8080/api/users");
        URLConnection con = url.openConnection();
        HttpURLConnection http = (HttpURLConnection)con;
        http.setRequestMethod("POST");
        http.setDoOutput(true);


        //Scanner keyReader = new Scanner(System.in);
        System.out.println("Firstname?");
        String firstname = reader.next();
        System.out.println("Surname?");
        String surname = reader.next();
        System.out.println("Email?");
        String email = reader.next();
        System.out.println("Password?");
        String password = reader.next();
        System.out.println("Username?");
        String userName = reader.next();
        System.out.println("SSN? (6 digits)");
        String ssn = reader.next();
        //keyReader.close();

        String json = "{\n" +
                "\t\"firstName\": \"" + firstname + "\",\n" +
                "\t\"surname\": \"" + surname + "\",\n" +
                "\t\"email\": \"" + email + "\",\n" +
                "\t\"password\": \"" + password + "\",\n" +
                "\t\"userName\": \"" + userName + "\",\n" +
                "\t\"ssn\": \"" + ssn + "\"\n" +
                "}";
        byte[] out = json.getBytes(StandardCharsets.UTF_8);
        int length = out.length;

        http.setFixedLengthStreamingMode(length);
        http.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
        http.connect();
        OutputStream os = http.getOutputStream();
        os.write(out);

        BufferedReader reader = new BufferedReader(new  InputStreamReader(http.getInputStream()));
        if (reader != null) {
            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                //System.out.println("Response: " + line);
                sb.append(line);
            }
            String getResponseString = "";
            getResponseString = sb.toString();
            if(getResponseString.contains("userId")){
                System.out.println("Registration successful!");
            }else{
                System.out.println("Failed to register.");
            }

        }
    }
}
