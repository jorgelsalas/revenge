package com.demo.rte.cherryclient.activities.interfaces;

import java.util.ArrayList;
import com.demo.rte.cherryclient.activities.entities.Package;

import org.json.JSONArray;

/**
 * Created by Jorge on 02/08/2015. Listener for service query completion
 */
public interface OnPackageRetrievalCompletedListener {
    void onDataReceivedFailure();
    void onEmptyDataSetReceived();
    void onDataReceived(JSONArray response);
    void onDataReceived(ArrayList<Package> packages);
}
