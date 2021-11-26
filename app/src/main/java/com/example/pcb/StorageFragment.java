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

public class StorageFragment extends Fragment {

    private RecyclerView rvStorage;

    public StorageFragment() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static StorageFragment newInstance() {
        StorageFragment fragment = new StorageFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
//            mParam1 = getArguments().getString(ARG_PARAM1);
//            mParam2 = getArguments().getString(ARG_PARAM2);
        }

    }

//    @Override
//    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
//        super.onViewCreated(view, savedInstanceState);
//
//        rvCPU = view.findViewById(R.id.rv_cpu);
//        rvCPU.setLayoutManager(new LinearLayoutManager(this));
//    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_storage, container, false);
        ArrayList<Storage> Storagelist = new ArrayList<>();
        for(int i=1; i<MainMenuActivity.Storagelist.size(); i++){
            Storagelist.add(MainMenuActivity.Storagelist.get(i));
        }
        rvStorage = view.findViewById(R.id.rv_storage);
        showRecyclerCardView(view, Storagelist);
        Log.d("CPUFragment ", Storagelist.get(0).getName());

        return view;
    }

    private void showRecyclerCardView(View view, ArrayList<Storage> Storagelist){
        rvStorage.setLayoutManager(new LinearLayoutManager(view.getContext()));
        StorageCardViewAdapter storageCardViewAdapter = new StorageCardViewAdapter(Storagelist);
        rvStorage.setAdapter(storageCardViewAdapter);
    }
}
