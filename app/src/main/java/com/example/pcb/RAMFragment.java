package com.example.pcb;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

public class RAMFragment extends Fragment {

    private RecyclerView rvRAM;
    public RAMFragment() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static RAMFragment newInstance() {
        RAMFragment fragment = new RAMFragment();
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
        View view = inflater.inflate(R.layout.fragment_r_a_m, container, false);
        ArrayList<RAM> RAMlist = new ArrayList<>();
        for(int i=1; i<MainMenuActivity.RAMlist.size(); i++){
            RAMlist.add(MainMenuActivity.RAMlist.get(i));
        }
        rvRAM = view.findViewById(R.id.rvRAM);
        showRecyclerCardView(view, RAMlist);
        Log.d("RAMFragment ", RAMlist.get(0).getName());

        return view;
    }

    private void showRecyclerCardView(View view, ArrayList<RAM> RAMlist){
        rvRAM.setLayoutManager(new LinearLayoutManager(view.getContext()));
        RAMCardViewAdapter ramCardViewAdapter = new RAMCardViewAdapter(RAMlist);
        rvRAM.setAdapter(ramCardViewAdapter);
    }
}