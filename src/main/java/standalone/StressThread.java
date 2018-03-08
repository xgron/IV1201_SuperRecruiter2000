package standalone;

import net.bytebuddy.utility.RandomString;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.StandardCharsets;
import java.util.Random;

class StressThread extends java.lang.Thread {

    public String threadName = "";


    StressThread(String name) {
        threadName = name;
        //System.out.println("Creating " +  threadName );
    }

    public void run() {
        //System.out.println("Running " +  threadName );
        try {
            String ip = "130.229.136.157";

            testApplicants(5, ip);
            testRegister(5, ip);
        }catch (Exception e) {
            System.out.println("Something went wrong... " + e.getMessage());
        }
        //System.out.println("StressThread " +  threadName + " exiting.");
    }

    public void testApplicants(int runs, String ip) throws Exception{
        long startTime = System.currentTimeMillis();
        long stopTime;
        long elapsedTime;

        for(int i = 0; i < runs; i++){
            try{
                getApplicants(ip);
            }catch (Exception e){
                throw e;
            }

        }
        stopTime = System.currentTimeMillis();
        elapsedTime = stopTime - startTime;
        System.out.println(threadName + " -> " + elapsedTime + " ms to fetch applications " + runs + " time(s)");
    }

    private void testRegister(int runs, String ip) throws Exception{
        RandomString rs = new RandomString();
        Random rnd = new Random();
        long startTime = System.currentTimeMillis();

        for(int i = 0; i < runs; i++){
            try{
                String firstname = rs.nextString();
                String surname = rs.nextString();
                String email = rs.nextString()+ "@mail.com";
                String password = rs.nextString();
                String userName = rs.nextString();
                String ssn = Integer.toString(100000 + rnd.nextInt(900000));
                registerUser(ip, firstname, surname, email, password, userName, ssn);
            }catch (Exception e){
                throw e;
            }

        }

        long stopTime = System.currentTimeMillis();
        long elapsedTime = stopTime - startTime;
        System.out.println(threadName + " -> " + elapsedTime + " ms to register " + runs + " user(s)");
    }

    private static void getApplicants(String ip) throws Exception {

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
    }

    private static void registerUser(String ip, String firstname, String surname, String email, String password, String userName, String ssn) throws Exception{
        URL url = new URL("http://" + ip + ":8080/api/users");
        URLConnection con = url.openConnection();
        HttpURLConnection http = (HttpURLConnection)con;
        http.setRequestMethod("POST");
        http.setDoOutput(true);

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
                //System.out.println("Registration successful!");
            }else{
                //System.out.println("Failed to register.");
            }

        }
    }
}
