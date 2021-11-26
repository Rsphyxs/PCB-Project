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
 * Use the {@link GPUFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class GPUFragment extends Fragment {

    private RecyclerView rvGPU;

    public GPUFragment() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static GPUFragment newInstance() {
        GPUFragment fragment = new GPUFragment();
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
        View view = inflater.inflate(R.layout.fragment_g_p_u, container, false);
        ArrayList<GPU> GPUlist = new ArrayList<>();
        for(int i=1; i<MainMenuActivity.GPUlist.size(); i++){
            GPUlist.add(MainMenuActivity.GPUlist.get(i));
        }
        rvGPU = view.findViewById(R.id.rv_gpu);
        showRecyclerCardView(view, GPUlist);
        Log.d("GPUFragment ", GPUlist.get(0).getName());

        return view;
    }

    private void showRecyclerCardView(View view, ArrayList<GPU> GPUlist){
        rvGPU.setLayoutManager(new LinearLayoutManager(view.getContext()));
        GPUCardViewAdapter gpuCardViewAdapter = new GPUCardViewAdapter(GPUlist);
        rvGPU.setAdapter(gpuCardViewAdapter);
    }
}