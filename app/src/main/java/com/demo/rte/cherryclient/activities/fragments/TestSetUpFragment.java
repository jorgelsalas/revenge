package com.demo.rte.cherryclient.activities.fragments;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.AdapterView.OnItemSelectedListener;
import com.demo.rte.cherryclient.R;
import com.demo.rte.cherryclient.activities.MainActivity;
import com.demo.rte.cherryclient.activities.asynctasks.QueryServiceTask;
import com.demo.rte.cherryclient.activities.entities.*;
import com.demo.rte.cherryclient.activities.entities.Package;
import com.demo.rte.cherryclient.activities.interfaces.OnPackageRetrievalCompletedListener;

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link OnFragmentInteractionListener3} interface
 * to handle interaction events.
 * Use the {@link TestSetUpFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class TestSetUpFragment extends Fragment implements OnPackageRetrievalCompletedListener, OnItemSelectedListener {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private Spinner spinner;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener3 mListener;

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment TestSetUpFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static TestSetUpFragment newInstance(String param1, String param2) {
        TestSetUpFragment fragment = new TestSetUpFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    public TestSetUpFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_test_set_up, container, false);
        spinner = (Spinner) v.findViewById(R.id.package_spinner);
        spinner.setOnItemSelectedListener(this);
        new QueryServiceTask(this).execute("");
        return v;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction3();
        }
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mListener = (OnFragmentInteractionListener3) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnFragmentInteractionListener");
        }
        ((MainActivity) activity).onSectionAttached(0);
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void onDataReceivedFailure() {

    }

    @Override
    public void onEmptyDataSetReceived() {

    }

    @Override
    public void onDataReceived(JSONArray response) {

    }

    @Override
    public void onDataReceived(ArrayList<Package> packages) {
        List<String> packageNames = new ArrayList<>();

        for (Package p : packages){
            packageNames.add(p.getName() + " (" + p.getAcronym() + ")");
        }

        ArrayAdapter<String> packagesAdapter = new ArrayAdapter<> (getActivity(), android.R.layout.simple_spinner_item, packageNames);
        // Drop down layout style - list view with radio button
        packagesAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // attaching data adapter to spinner
        spinner.setAdapter(packagesAdapter);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        //For the spinner
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        //for the spinner
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener3 {
        // TODO: Update argument type and name
        public void onFragmentInteraction3();
    }

}
