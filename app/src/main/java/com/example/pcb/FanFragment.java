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

public class FanFragment extends Fragment {

    private RecyclerView rvFan;

    public FanFragment() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static FanFragment newInstance() {
        FanFragment fragment = new FanFragment();
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
        View view = inflater.inflate(R.layout.fragment_fan, container, false);
        ArrayList<Fan> Fanlist = new ArrayList<>();
        for(int i=1; i<MainMenuActivity.Fanlist.size(); i++){
            Fanlist.add(MainMenuActivity.Fanlist.get(i));
        }
        rvFan = view.findViewById(R.id.rvFan);
        showRecyclerCardView(view, Fanlist);
        Log.d("FanFragment ", Fanlist.get(0).getName());

        return view;
    }

    private void showRecyclerCardView(View view, ArrayList<Fan> Fanlist){
        rvFan.setLayoutManager(new LinearLayoutManager(view.getContext()));
        FanCardViewAdapter fanCardViewAdapter = new FanCardViewAdapter(Fanlist);
        rvFan.setAdapter(fanCardViewAdapter);
    }
}
