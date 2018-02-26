package standalone;

import net.bytebuddy.utility.RandomString;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.StandardCharsets;
import java.util.Random;

public class stress {
    public static void main(String[] args) {
        // http://www.shodor.org/interactivate/activities/SimplePlot/
        String ip = "130.229.159.24";

        testApplicants(1000, ip);
    }

    private static void testApplicants(int runs, String ip){
        long startTime = System.currentTimeMillis();
        long stopTime;
        long elapsedTime;

        for(int i = 0; i < runs; i++){
            try{
                getApplicants(ip);
                if((i%10) == 0 ){
                    stopTime = System.currentTimeMillis();
                    elapsedTime = stopTime - startTime;
                    System.out.print(i + "," + elapsedTime + ",\n");
                }
            }catch (Exception e){
                e.printStackTrace();
            }

        }

        stopTime = System.currentTimeMillis();
        elapsedTime = stopTime - startTime;
        //System.out.print(runs + "," + elapsedTime + "\n");
        //System.out.println(elapsedTime + " ms to fetch applications " + runs + " times");
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
        //System.out.println(json);
    }

    private static void testRegister(int runs, String ip){
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
                e.printStackTrace();
            }

        }

        long stopTime = System.currentTimeMillis();
        long elapsedTime = stopTime - startTime;
        System.out.println(elapsedTime + "," + runs + ",\n");
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
