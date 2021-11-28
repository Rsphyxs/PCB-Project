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

public class CaseFragment extends Fragment {

    private RecyclerView rvCase;

    public CaseFragment() {
        // Required empty public constructor
    }

    public static CaseFragment newInstance() {
        CaseFragment fragment = new CaseFragment();
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
        View view = inflater.inflate(R.layout.fragment_case, container, false);
        ArrayList<Case> Caselist = new ArrayList<>();
        for(int i=1; i<MainMenuActivity.Caselist.size(); i++){
            Caselist.add(MainMenuActivity.Caselist.get(i));
        }
        rvCase = view.findViewById(R.id.rv_case);
        showRecyclerCardView(view, Caselist);
        Log.d("CPUFragment ", Caselist.get(0).getName());

        return view;
    }

    private void showRecyclerCardView(View view, ArrayList<Case> Caselist){
        rvCase.setLayoutManager(new LinearLayoutManager(view.getContext()));
        CaseCardViewAdapter caseCardViewAdapter = new CaseCardViewAdapter(Caselist);
        rvCase.setAdapter(caseCardViewAdapter);
    }
}