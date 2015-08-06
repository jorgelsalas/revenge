package com.demo.rte.cherryclient.activities.asynctasks;

import android.app.Activity;
import android.os.AsyncTask;
import android.support.v4.app.Fragment;
import android.util.Log;

import com.demo.rte.cherryclient.activities.http.HttpHelper;
import com.demo.rte.cherryclient.activities.interfaces.OnPackageRetrievalCompletedListener;

import org.json.JSONArray;

/**
 * Created by Jorge on 05/08/2015. Alternative that receives the endpoint needed to fetch data and returns a JSON
 * object or Array to the caller
 */
public class QueryServiceTask2 extends AsyncTask<String, Integer, String> {

    private String service_name;
    private JSONArray response = null;
    private HttpHelper httpHelper;
    private boolean isNull = false;
    private boolean isEmpty = false;
    private OnPackageRetrievalCompletedListener mCallback;
    private static final String LOG_TAG = QueryServiceTask2.class.getSimpleName();

    public QueryServiceTask2(Activity activity, String service_name)  {
        httpHelper = new HttpHelper();
        this.service_name = service_name;
        setCallback(activity);
    }

    public QueryServiceTask2(Fragment fragment, String service_name)  {
        httpHelper = new HttpHelper();
        this.service_name = service_name;
        setCallback(fragment);
    }

    @Override
    protected String doInBackground(String... params) {
        String result = "";

        try {
            response = httpHelper.getEndpointJSON(service_name);
            if (response == null) {
                isNull = true;
            }
            else if (response.length() == 0){
                isEmpty = true;
            }

        }
        catch (Exception e){
            Log.e(LOG_TAG, "Error while retrieving data from Bluemix: " + e.toString(), e);

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
            mCallback.onDataReceived(response);
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
