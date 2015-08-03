package com.demo.rte.cherryclient.activities.http;

import android.util.Log;

import com.demo.rte.cherryclient.activities.constants.HttpConstants;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;



/**
 * Created by Jorge on 02/08/2015. Helper
 */
public class HttpClient {

    private static final String CHARSET = "UTF-8";
    private static final String HTTP_GET = "GET";


    public HttpClient(){

    }


    public JSONArray getEndpointJSON(String path){
        String line;
        JSONObject jo = null;
        JSONArray ja = null;

        try {
            String fullUrl = HttpConstants.ENDPOINT+path;

            URLEncoder.encode(fullUrl, CHARSET);
            Log.i(HttpClient.class.getName(), "URL QUERIED: " + fullUrl);
            URL connection = new URL(fullUrl);
            URLConnection tc = connection.openConnection();
            tc.setRequestProperty("charset", CHARSET);

            ((HttpURLConnection) tc).setRequestMethod(HTTP_GET);
            BufferedReader in = new BufferedReader(new InputStreamReader(tc.getInputStream()));

            while ((line = in.readLine()) != null) {
                ja = new JSONArray(line);
            }

            int responseCode = ((HttpURLConnection) tc).getResponseCode();
            System.out.println("Response code: " + responseCode);
            if (responseCode == 200){
                Log.i("HTTP GET","Succesfully retrieved info");
            }
            else {
                Log.e("HTTP GET","Something went wrong with the http request");
            }
        }
        catch (Exception e){
            Log.e("log_tag", "Error while connecting to Bluemix: "+e.toString(), e);
            Log.e("log_tag", "Error message: "+e.getMessage(), e);
        }

        return ja;
    }

}

