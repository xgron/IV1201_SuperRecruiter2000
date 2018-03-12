package standalone;

import net.bytebuddy.utility.RandomString;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
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
            String ip = "192.168.1.185";

            testApplicants(5, ip);
            testApplicants(25, ip);
            testApplicants(50, ip);
            testApplicants(75, ip);
            testApplicants(100, ip);
            testApplicants(150, ip);
            testApplicants(200, ip);

            testRegister(5, ip);
            testRegister(25, ip);
            testRegister(50, ip);
            testRegister(75, ip);
            testRegister(100, ip);
            testRegister(150, ip);
            testRegister(200, ip);
        }catch (Exception e) {
            System.out.println("Something went wrong... " + e.getMessage());
        }
        //System.out.println("StressThread " +  threadName + " exiting.");
    }

    public void testApplicants(int runs, String ip) throws Exception{
        long[] time = new long[runs];

        for(int i = 0; i < runs; i++){
            try{
                long startTime = System.currentTimeMillis();
                long stopTime;
                long elapsedTime;
                getApplicants(ip);
                stopTime = System.currentTimeMillis();
                elapsedTime = stopTime - startTime;
                time[i] = elapsedTime;
            }catch (Exception e){
                throw e;
            }
        }
        System.out.print(threadName + "->fetch: ");
        printArrayInfo(time);
    }

    private void testRegister(int runs, String ip) throws Exception{
        long[] time = new long[runs];

        for(int i = 0; i < runs; i++){
            try{
                RandomString rs = new RandomString();
                Random rnd = new Random();
                String firstname = rs.nextString();
                String surname = rs.nextString();
                String email = rs.nextString()+ "@mail.com";
                String password = rs.nextString();
                String userName = rs.nextString();
                String ssn = Integer.toString(100000 + rnd.nextInt(900000));


                long startTime = System.currentTimeMillis();
                registerUser(ip, firstname, surname, email, password, userName, ssn);
                long stopTime = System.currentTimeMillis();
                long elapsedTime = stopTime - startTime;
                time[i] = elapsedTime;

            }catch (Exception e){
                throw e;
            }
        }
        System.out.print(threadName + "->register: ");
        printArrayInfo(time);
    }

    private static void printArrayInfo(long[] array){
        long max = array[0];
        long min = array[0];
        long tot = 0;
        for(int i = 0; i < array.length; i++){
            long current = array[i];
            tot = tot + current;
            if(min > current)   min = current;
            if(max < current)   max = current;
        }
        long avg = tot / array.length;

        System.out.println("runs#: " + array.length + ", min: " + min + ", max: " + max + ", total: " + tot + ", average: " + avg);
    }

    private static void getApplicants(String ip) throws Exception {

        StringBuilder result = new StringBuilder();
        URL url = new URL("http://" + ip + ":8080/api/users");
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.setRequestProperty ("Authorization", "Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJwdHRoenN5bXVoc2xxc3BnZW95Z3ljaGVuIn0.5zLmmLYZUVYVm1_-5Xn-Hs_8BqgTVuTi_grr90faGzN-Posfhp7odJY1DHgsy5DUXNibVFPlaKuNqUI3rX-ZyQ");

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
