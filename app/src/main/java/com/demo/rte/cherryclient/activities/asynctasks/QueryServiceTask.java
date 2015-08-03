package com.demo.rte.cherryclient.activities.asynctasks;

import android.app.Activity;
import android.os.AsyncTask;
import android.support.v4.app.Fragment;
import android.util.Log;

import com.demo.rte.cherryclient.activities.constants.HttpConstants;
import com.demo.rte.cherryclient.activities.entities.Package;
import com.demo.rte.cherryclient.activities.http.HttpClient;
import com.demo.rte.cherryclient.activities.interfaces.OnPackageRetrievalCompletedListener;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by Jorge on 02/08/2015. Background thread helper
 */

public class QueryServiceTask extends AsyncTask<String, Integer, String> {

    private ArrayList<Package> packages = new ArrayList<>();
    //private JSONObject ja = null;
    private JSONArray ja = null;
    //private JSONArray ja = null;
    private HttpClient httpHelper;
    private boolean isNull = false;
    private boolean isEmpty = false;
    private OnPackageRetrievalCompletedListener mCallback;
    private static final String LOG_TAG = QueryServiceTask.class.getSimpleName();

    public QueryServiceTask(Activity activity)  {
        httpHelper = new HttpClient();
        setCallback(activity);
    }

    public QueryServiceTask(Fragment fragment)  {
        httpHelper = new HttpClient();
        setCallback(fragment);
    }

    @Override
    protected String doInBackground(String... params) {
        String result = "";

        try {
            ja = httpHelper.getEndpointJSON(HttpConstants.PACKAGE_PATH);
            if (ja == null) {
                isNull = true;
            }
            else {

                JSONArray responsePackages = ja;

                for (int i = 0; i < responsePackages.length(); i++) {

                    Package pack = new Package("","");

                    JSONObject joVenue = (JSONObject) responsePackages.get(i);
                    pack.setName(joVenue.getString(HttpConstants.NAME_TAG));
                    pack.setAcronym(joVenue.getString(HttpConstants.ACRONYM_TAG));
                    //pack.setResult_number(i + 1);
                    packages.add(pack);
                }
                if (packages.size() == 0){
                    isEmpty = true;
                }
            }

        }
        catch (Exception e){
            Log.e(LOG_TAG, "Error while retrieving Foursquare data: " + e.toString(), e);

        }
        return result;
    }

    @Override
    protected void onPostExecute(String result) {
        if (isNull){
            mCallback.onDataReceivedFailure();
        }
        else if(isEmpty){
            mCallback.onEmptyDataSetReceived();
        }
        else{
            mCallback.onDataReceived(packages);
        }
    }

    public void setCallback(Activity activity){
        try {
            mCallback = (OnPackageRetrievalCompletedListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString() + " must implement "
                    + OnPackageRetrievalCompletedListener.class.getName());
        }
    }

    public void setCallback(Fragment fragment){
        try {
            mCallback = (OnPackageRetrievalCompletedListener) fragment;
        } catch (ClassCastException e) {
            throw new ClassCastException(fragment.toString() + " must implement "
                    + OnPackageRetrievalCompletedListener.class.getName());
        }
    }

}