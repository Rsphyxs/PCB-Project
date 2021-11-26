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

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link MoboFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MoboFragment extends Fragment {

    private RecyclerView rvMobo;


    public MoboFragment() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static MoboFragment newInstance() {
        MoboFragment fragment = new MoboFragment();
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
        View view = inflater.inflate(R.layout.fragment_mobo, container, false);
        ArrayList<Motherboard> Mobolist = new ArrayList<>();
        for(int i=1; i<MainMenuActivity.Mobolist.size(); i++){
            Mobolist.add(MainMenuActivity.Mobolist.get(i));
        }
        rvMobo = view.findViewById(R.id.rv_mobo);
        showRecyclerCardView(view, Mobolist);
        Log.d("MoboFragment ", Mobolist.get(0).getName());

        return view;
    }

    private void showRecyclerCardView(View view, ArrayList<Motherboard> Mobolist){
        rvMobo.setLayoutManager(new LinearLayoutManager(view.getContext()));
        MoboCardViewAdapter moboCardViewAdapter = new MoboCardViewAdapter(Mobolist);
        rvMobo.setAdapter(moboCardViewAdapter);
    }
}