package com.demo.rte.cherryclient.activities.http;

import android.util.Log;

import com.demo.rte.cherryclient.activities.constants.HttpConstants;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;


/**
 * Created by Jorge on 02/08/2015. Helper
 */
public class HttpHelper {

    private static final String CHARSET = "UTF-8";
    private static final String HTTP_GET = "GET";


    public HttpHelper(){

    }


    public JSONArray getEndpointJSON(String path){
        String line;
        JSONObject jo = null;
        JSONArray ja = null;

        try {
            String fullUrl = HttpConstants.ENDPOINT+path;

            URLEncoder.encode(fullUrl, CHARSET);
            Log.i(HttpHelper.class.getName(), "URL QUERIED: " + fullUrl);
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

    //TODO: Implementar como key-value pairs
    //TODO: Better yet use Retrofit
    public void postToEndpoint(String path, String name, String acronym, String status){
        String line;
        JSONObject jo;
        String fullUrl = HttpConstants.ENDPOINT+path;
        /*
        Log.i(HttpHelper.class.getName(), "URL QUERIED: " + fullUrl);
        try {
            URL connection = new URL(fullUrl);
            URLConnection tc = connection.openConnection();
            tc.setRequestProperty("charset", CHARSET);
            tc.setDoOutput(true);
            tc.setDoInput(true);
            tc.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            ((HttpURLConnection) tc).setRequestMethod("POST");
            OutputStreamWriter out = new OutputStreamWriter(tc.getOutputStream());
            out.write("");
            out.flush();
            out.close();

            //Get the response
            // Get the response
            BufferedReader in = new BufferedReader(new InputStreamReader(tc.getInputStream()));
            while ((line = in.readLine()) != null) {
                System.out.println("Response obtenido:\n" + line);
                jo = new JSONObject(line);
            }
            in.close();
        }
        catch (Exception e){
            Log.e("log_tag", "Error while doing POST to Bluemix: "+e.toString(), e);
            Log.e("log_tag", "Error message: "+e.getMessage(), e);
        }
        */
        HttpClient httpClient = new DefaultHttpClient();
        HttpPost httpPost = new HttpPost(fullUrl);
        httpPost.setHeader("Content-Type", "application/x-www-form-urlencoded");
        List<NameValuePair> nameValuePair = new ArrayList<NameValuePair>(3);
        nameValuePair.add(new BasicNameValuePair("name", name));
        nameValuePair.add(new BasicNameValuePair("acronym", acronym));
        nameValuePair.add(new BasicNameValuePair("status", status));

        // Url Encoding the POST parameters
        try {
            httpPost.setEntity(new UrlEncodedFormEntity(nameValuePair, HTTP.UTF_8));
        } catch (UnsupportedEncodingException e) {
            // writing error to Log
            e.printStackTrace();
        }
        try {
            HttpResponse response = httpClient.execute(httpPost);
            System.out.println("url = " + fullUrl);
            System.out.println("Response code to string = " + response.getStatusLine().toString());
            HttpEntity entity = response.getEntity();
            System.out.println("Entity length = " + entity.getContentLength());
            if (entity != null) {
                // EntityUtils to get the reponse content
                String content =  EntityUtils.toString(entity);
                System.out.println("Content de entity utils = \n" + content);
            }
            System.out.println("Headers = " + response.getAllHeaders());

            System.out.println("Locale" + response.getLocale().toString());
            System.out.println("Params = " + response.getParams());

            System.out.println("Protocol" + response.getProtocolVersion().toString());

            // writing response to log
            Log.d("Http Response:", response.toString());
        } catch (ClientProtocolException e) {
            // writing exception to log
            e.printStackTrace();
        } catch (IOException e) {
            // writing exception to log
            e.printStackTrace();

        }
    }

}

