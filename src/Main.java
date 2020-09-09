/*
 * This project reads and parses the json file found on the official Austrian webpage for COVID
 * It serves as the basis for an Android App
 * Written by Lukas Becker(2020)
 */

import com.google.gson.*;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.OkHttpClient;

import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

public class Main {

    //official set of Data
    static final String dataUrl = "https://corona-ampel.gv.at/sites/corona-ampel.gv.at/files/assets/Warnstufen_Corona_Ampel_aktuell.json";
    String[] warningsData = new String[18]; // All the Data(regions+codes)
    String[] regions = new String[18];//only regions(in JSON: name)
    String[] codes = new String[18];//only codes(inJSON: warnstufe)

    public static void main(String[] args) {
        //Main function, necessary in Java console Apps
        Main m = new Main(); //constructor for Main Class
        m.parseJson(); //call parseJson
    }

    void parseJson() {
        //This method parses the json we receive from the official server

        Warnstufen[] wArr; // Warnstufen Array (contains name+warnstufen)

        String json = getJson(); //call getJson and save the return to a new String
        Gson gson = new Gson(); //initialize gson(to parse JSON)


        MyPojo parsed = gson.fromJson(json, MyPojo.class); // parse the JSON to a new instance of MyPojo

        wArr = parsed.getWarnstufen(); //get the array TODO: set the refresh interval here!

        for (int i = 0; i < 18; i++) { // Loop through the arrays
            warningsData[i] = String.valueOf(wArr[i]); //cast the wArr to a new String Array
            //Split the warningsData into the two parts we need (region and code)
            // We receive the following format: warnstufe---name(=region)
            codes[i] = warningsData[i].substring(0, warningsData[i].indexOf("---"));
            regions[i] = warningsData[i].substring(warningsData[i].lastIndexOf("---") + 3);

            //Debugging, print the results
            System.out.print(regions[i] + ": ");
            System.out.println(codes[i]);
        }
    }

    public static String getJson() {

        // read the json Data from the official website
        Request req = new Request.Builder().url(dataUrl).build(); //build a new request
        OkHttpClient client = new OkHttpClient(); //initialize a new http Client
        try (Response response = client.newCall(req).execute()) { //look for the response we get from the request
            assert response.body() != null;
            return response.body().string();

        } catch (IOException e) {
            e.printStackTrace();
            Timer timer = new Timer();
            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    getJson();
                }
            }, 30 * 1000);

            return null;
        }
    }


}
