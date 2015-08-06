package com.demo.rte.cherryclient.activities.asynctasks;

import android.app.Activity;
import android.os.AsyncTask;
import android.support.v4.app.Fragment;
import android.util.Log;

import com.demo.rte.cherryclient.activities.constants.HttpConstants;
import com.demo.rte.cherryclient.activities.entities.*;
import com.demo.rte.cherryclient.activities.entities.Package;
import com.demo.rte.cherryclient.activities.http.HttpHelper;
import com.demo.rte.cherryclient.activities.interfaces.OnPackageRetrievalCompletedListener;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * To POST
 */
public class PostServiceTask extends AsyncTask<String, Integer, String> {

    private ArrayList<Package> packages = new ArrayList<>();
    //private JSONObject ja = null;
    private JSONArray ja = null;
    private HttpHelper httpHelper;
    private boolean isNull = false;
    private boolean isEmpty = false;
    private String name, acronym, status;
    private OnPackageRetrievalCompletedListener mCallback;
    private static final String LOG_TAG = QueryServiceTask.class.getSimpleName();

    public PostServiceTask(Activity activity, String name, String acronym, String status)  {
        httpHelper = new HttpHelper();
        this.name = name;
        this.acronym = acronym;
        this.status = status;
        setCallback(activity);
    }

    public PostServiceTask(Fragment fragment, String name, String acronym, String status)  {
        httpHelper = new HttpHelper();
        this.name = name;
        this.acronym = acronym;
        this.status = status;
        setCallback(fragment);
    }

    @Override
    protected String doInBackground(String... params) {
        String result = "";

        try {
            httpHelper.postToEndpoint(HttpConstants.PACKAGE_TESTS_PATH, name, acronym, status);
        }
        catch (Exception e){
            Log.e(LOG_TAG, "Error while POSTING data from Bluemix server: " + e.toString(), e);

        }
        return result;
    }

    @Override
    protected void onPostExecute(String result) {

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