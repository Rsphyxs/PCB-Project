package com.example.pcb;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.appcompat.app.AppCompatActivity;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class CPUFragment extends Fragment {

    private RecyclerView rvCPU;

    public CPUFragment() {
        // Required empty public constructor
    }

    public static CPUFragment newInstance() {
        CPUFragment fragment = new CPUFragment();
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
        View view = inflater.inflate(R.layout.fragment_c_p_u, container, false);
        ArrayList<CPU> CPUlist = new ArrayList<>();
        for(int i=1; i<MainMenuActivity.CPUlist.size(); i++){
            CPUlist.add(MainMenuActivity.CPUlist.get(i));
        }
        rvCPU = view.findViewById(R.id.rv_cpu);
        showRecyclerCardView(view, CPUlist);
        Log.d("CPUFragment ", CPUlist.get(0).getName());

        return view;
    }

    private void showRecyclerCardView(View view, ArrayList<CPU> CPUlist){
        rvCPU.setLayoutManager(new LinearLayoutManager(view.getContext()));
        CPUCardViewAdapter cpuCardViewAdapter = new CPUCardViewAdapter(CPUlist);
        rvCPU.setAdapter(cpuCardViewAdapter);
    }

}