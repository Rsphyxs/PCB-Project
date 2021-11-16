package com.example.pcb;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PCBuildActivity extends AppCompatActivity {
    List<CPU> CPUlist = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pcbuild);

        int[] spinnerId = {R.id.CPUspinner, R.id.Mobospinner, R.id.GPUspinner, R.id.RAMspinner, R.id.Storagespinner, R.id.PSUspinner, R.id.Casespinner, R.id.FANspinner};
        FetchCPU();

    }

    public void Adapter(int spinnerId, List<String> name) {
        Spinner spinner = (Spinner) findViewById(spinnerId);

        ArrayAdapter<String> cpuAdapter = new ArrayAdapter<String>(PCBuildActivity.this, android.R.layout.simple_spinner_item, name) {
            @Override
            public boolean isEnabled(int position) {
                if (position == 0) {
                    return false;
                } else {
                    return true;
                }
            }

            @Override
            public View getDropDownView(int position, View convertView, ViewGroup parent) {
                View view = super.getDropDownView(position, convertView, parent);
                TextView tv = (TextView) view;
                if (position == 0) {
                    tv.setTextColor(Color.GRAY);
                } else {
                    tv.setTextColor(Color.BLACK);
                }
                return view;
            }
        };
        cpuAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(cpuAdapter);

    }

    public void FetchCPU() {
        CRUDapi crudInterface = RetrofitClient.getClient().create(CRUDapi.class);
        Call<List<CPU>> call = crudInterface.fetchCPU();
        Log.d("AMANZUFAR ", "Masuk enqueue");
        call.enqueue(
                new Callback<List<CPU>>() {
                    @Override
                    public void onResponse(Call<List<CPU>> call, Response<List<CPU>> response) {
                        CPUlist = response.body();
                        Log.d("CEK", CPUlist.get(1).toString());
                        Log.d("AMANZUFAR ", "Aman Ngab");
                        List<String> cpuName = new ArrayList<String>();
                        String line = "";
                        cpuName.add(CPUlist.get(0).getName());
                        for(int i=1; i<CPUlist.size(); i++){
                            line = CPUlist.get(i).getName() + " " +  CPUlist.get(i).getCore() + "Cores " + CPUlist.get(i).getClock() + "GHz" + ", Rp. " + CPUlist.get(i).getPrice();
                            cpuName.add(line);
                        }
                        Adapter(R.id.CPUspinner, cpuName);
                    }

                    @Override
                    public void onFailure(Call<List<CPU>> call, Throwable t) {
                        Log.d("ERRORzufar: ", t.getMessage());
                        Toast.makeText(PCBuildActivity.this, "Failed fetch data", Toast.LENGTH_SHORT).show();
                    }
                }
        );
    }
}