package com.example.pcb;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link PSUFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class PSUFragment extends Fragment {

    private RecyclerView rvPSU;

    public PSUFragment() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static PSUFragment newInstance() {
        PSUFragment fragment = new PSUFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_p_s_u, container, false);
        ArrayList<PSU> PSUlist = new ArrayList<>();
        for(int i=1; i<MainMenuActivity.PSUlist.size(); i++){
            PSUlist.add(MainMenuActivity.PSUlist.get(i));
        }
        rvPSU = view.findViewById(R.id.rvPSU);
        showRecyclerCardView(view, PSUlist);
        Log.d("PSUFragment ", PSUlist.get(0).getName());

        return view;
    }

    private void showRecyclerCardView(View view, ArrayList<PSU> PSUlist){
        rvPSU.setLayoutManager(new LinearLayoutManager(view.getContext()));
        PSUCardViewAdapter psuCardViewAdapter = new PSUCardViewAdapter(PSUlist);
        rvPSU.setAdapter(psuCardViewAdapter);
    }
}