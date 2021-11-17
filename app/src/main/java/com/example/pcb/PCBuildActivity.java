package com.example.pcb;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PCBuildActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    List<CPU> CPUlist = new ArrayList<>();
    List<Motherboard> Mobolist = new ArrayList<>();
    List<GPU> GPUlist = new ArrayList<>();
    List<RAM> RAMlist = new ArrayList<>();
    List<Storage> Storagelist = new ArrayList<>();
    List<PSU> PSUlist = new ArrayList<>();
    List<Case> Caselist = new ArrayList<>();
    List<Fan> Fanlist = new ArrayList<>();
    List<String> cpuName = new ArrayList<String>();
    List<String> moboName = new ArrayList<String>();
    List<String> gpuName = new ArrayList<String>();
    List<String> ramName = new ArrayList<String>();
    List<String> storageName = new ArrayList<String>();
    List<String> psuName = new ArrayList<String>();
    List<String> caseName = new ArrayList<String>();
    List<String> fanName = new ArrayList<String>();
    long cpuPrice = 0;
    long moboPrice = 0;
    long gpuPrice = 0;
    long ramPrice = 0;
    long storagePrice = 0;
    long psuPrice = 0;
    long casePrice = 0;
    long fanPrice = 0;
    long finalPrice;

    private TextView textPrice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pcbuild);

        int[] spinnerId = {R.id.CPUspinner, R.id.Mobospinner, R.id.GPUspinner, R.id.RAMspinner, R.id.Storagespinner, R.id.PSUspinner, R.id.Casespinner, R.id.FANspinner};
        FetchCPU();
        FetchMobo();
        FetchGPU();
        FetchRAM();
        FetchStorage();
        FetchPSU();
        FetchCase();
        FetchFan();

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
        spinner.setOnItemSelectedListener(this);
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
                        Log.d("AMANZUFAR ", "Aman Ngab");
                        String line = "";
                        cpuName.add(CPUlist.get(0).getName());
                        for(int i=1; i<CPUlist.size(); i++){
                            line = CPUlist.get(i).getName() + " " +  CPUlist.get(i).getCore() + " Cores " + CPUlist.get(i).getClock() + "GHz" + ", Rp. " + CPUlist.get(i).getPrice();
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

    public void FetchMobo() {
        CRUDapi crudInterface = RetrofitClient.getClient().create(CRUDapi.class);
        Call<List<Motherboard>> call = crudInterface.fetchMobo();
        Log.d("AMANZUFAR ", "Masuk enqueue");
        call.enqueue(
                new Callback<List<Motherboard>>() {
                    @Override
                    public void onResponse(Call<List<Motherboard>> call, Response<List<Motherboard>> response) {
                        Mobolist = response.body();
                        Log.d("AMANZUFAR ", "Aman Ngab");
                        String line = "";
                        moboName.add(Mobolist.get(0).getName());
                        for(int i=1; i<Mobolist.size(); i++){
                            line = Mobolist.get(i).getName() + " " +  Mobolist.get(i).getChipset() + " " + Mobolist.get(i).getForm_factor() + ", Rp. " + Mobolist.get(i).getPrice();
                            moboName.add(line);
                        }
                        Adapter(R.id.Mobospinner, moboName);
                    }

                    @Override
                    public void onFailure(Call<List<Motherboard>> call, Throwable t) {
                        Log.d("ERRORzufar: ", t.getMessage());
                        Toast.makeText(PCBuildActivity.this, "Failed fetch data", Toast.LENGTH_SHORT).show();
                    }
                }
        );
    }

    public void FetchGPU() {
        CRUDapi crudInterface = RetrofitClient.getClient().create(CRUDapi.class);
        Call<List<GPU>> call = crudInterface.fetchGPU();
        Log.d("AMANZUFAR ", "Masuk enqueue");
        call.enqueue(
                new Callback<List<GPU>>() {
                    @Override
                    public void onResponse(Call<List<GPU>> call, Response<List<GPU>> response) {
                        GPUlist = response.body();
                        Log.d("AMANZUFAR ", "Aman Ngab");
                        String line = "";
                        gpuName.add(GPUlist.get(0).getName());
                        for(int i=1; i<GPUlist.size(); i++){
                            line = GPUlist.get(i).getName() + " " +  GPUlist.get(i).getchip() + " " + GPUlist.get(i).getMemory() + ", Rp. " + GPUlist.get(i).getPrice();
                            gpuName.add(line);
                        }
                        Adapter(R.id.GPUspinner, gpuName);
                    }

                    @Override
                    public void onFailure(Call<List<GPU>> call, Throwable t) {
                        Log.d("ERRORzufar: ", t.getMessage());
                        Toast.makeText(PCBuildActivity.this, "Failed fetch data", Toast.LENGTH_SHORT).show();
                    }
                }
        );
    }

    public void FetchRAM() {
        CRUDapi crudInterface = RetrofitClient.getClient().create(CRUDapi.class);
        Call<List<RAM>> call = crudInterface.fetchRAM();
        Log.d("AMANZUFAR ", "Masuk enqueue");
        call.enqueue(
                new Callback<List<RAM>>() {
                    @Override
                    public void onResponse(Call<List<RAM>> call, Response<List<RAM>> response) {
                        RAMlist = response.body();
                        Log.d("AMANZUFAR ", "Aman Ngab");
                        String line = "";
                        ramName.add(RAMlist.get(0).getName());
                        for(int i=1; i<RAMlist.size(); i++){
                            line = RAMlist.get(i).getName() + " " +  RAMlist.get(i).getSpeed() + "MHz " + RAMlist.get(i).getModules() + ", Rp. " + RAMlist.get(i).getPrice();
                            ramName.add(line);
                        }
                        Adapter(R.id.RAMspinner, ramName);
                    }

                    @Override
                    public void onFailure(Call<List<RAM>> call, Throwable t) {
                        Log.d("ERRORzufar: ", t.getMessage());
                        Toast.makeText(PCBuildActivity.this, "Failed fetch data", Toast.LENGTH_SHORT).show();
                    }
                }
        );
    }

    public void FetchStorage() {
        CRUDapi crudInterface = RetrofitClient.getClient().create(CRUDapi.class);
        Call<List<Storage>> call = crudInterface.fetchStorage();
        Log.d("AMANZUFAR ", "Masuk enqueue");
        call.enqueue(
                new Callback<List<Storage>>() {
                    @Override
                    public void onResponse(Call<List<Storage>> call, Response<List<Storage>> response) {
                        Storagelist = response.body();
                        Log.d("AMANZUFAR ", "Aman Ngab");
                        String line = "";
                        storageName.add(Storagelist.get(0).getName());
                        for(int i=1; i<Storagelist.size(); i++){
                            line = Storagelist.get(i).getTipe() + " " +  Storagelist.get(i).getName() + " " + Storagelist.get(i).getSize() + "GB , Rp. " + Storagelist.get(i).getPrice();
                            storageName.add(line);
                        }
                        Adapter(R.id.Storagespinner, storageName);
                    }

                    @Override
                    public void onFailure(Call<List<Storage>> call, Throwable t) {
                        Log.d("ERRORzufar: ", t.getMessage());
                        Toast.makeText(PCBuildActivity.this, "Failed fetch data", Toast.LENGTH_SHORT).show();
                    }
                }
        );
    }

    public void FetchPSU() {
        CRUDapi crudInterface = RetrofitClient.getClient().create(CRUDapi.class);
        Call<List<PSU>> call = crudInterface.fetchPSU();
        Log.d("AMANZUFAR ", "Masuk enqueue");
        call.enqueue(
                new Callback<List<PSU>>() {
                    @Override
                    public void onResponse(Call<List<PSU>> call, Response<List<PSU>> response) {
                        PSUlist = response.body();
                        Log.d("AMANZUFAR ", "Aman Ngab");
                        String line = "";
                        psuName.add(PSUlist.get(0).getName());
                        for(int i=1; i<PSUlist.size(); i++){
                            line = PSUlist.get(i).getName() + " " +  PSUlist.get(i).getwatt() + " Watt " + PSUlist.get(i).getForm_factor() + ", Rp. " + PSUlist.get(i).getPrice();
                            psuName.add(line);
                        }
                        Adapter(R.id.PSUspinner, psuName);
                    }

                    @Override
                    public void onFailure(Call<List<PSU>> call, Throwable t) {
                        Log.d("ERRORzufar: ", t.getMessage());
                        Toast.makeText(PCBuildActivity.this, "Failed fetch data", Toast.LENGTH_SHORT).show();
                    }
                }
        );
    }

    public void FetchCase() {
        CRUDapi crudInterface = RetrofitClient.getClient().create(CRUDapi.class);
        Call<List<Case>> call = crudInterface.fetchCase();
        Log.d("AMANZUFAR ", "Masuk enqueue");
        call.enqueue(
                new Callback<List<Case>>() {
                    @Override
                    public void onResponse(Call<List<Case>> call, Response<List<Case>> response) {
                        Caselist = response.body();
                        Log.d("AMANZUFAR ", "Aman Ngab");
                        String line = "";
                        String line2 = "";
                        caseName.add(Caselist.get(0).getName());
                        for(int i=1; i<Caselist.size(); i++){
//                            String[] tempList = Caselist.get(i).getFf_tipe();
//                            for(int j=0; j<Caselist.get(i).getFf_tipe().length; j++){
//                                line2 += tempList[j];
//                            }
                            line = Caselist.get(i).getName() + " , Rp. " + Caselist.get(i).getPrice();
                            caseName.add(line);
                        }
                        Adapter(R.id.Casespinner, caseName);
                    }

                    @Override
                    public void onFailure(Call<List<Case>> call, Throwable t) {
                        Log.d("ERRORzufar: ", t.getMessage());
                        Toast.makeText(PCBuildActivity.this, "Failed fetch data", Toast.LENGTH_SHORT).show();
                    }
                }
        );
    }

    public void FetchFan() {
        CRUDapi crudInterface = RetrofitClient.getClient().create(CRUDapi.class);
        Call<List<Fan>> call = crudInterface.fetchFan();
        Log.d("AMANZUFAR ", "Masuk enqueue");
        call.enqueue(
                new Callback<List<Fan>>() {
                    @Override
                    public void onResponse(Call<List<Fan>> call, Response<List<Fan>> response) {
                        Fanlist = response.body();
                        Log.d("AMANZUFAR ", "Aman Ngab");
                        String line = "";
                        fanName.add(Fanlist.get(0).getName());
                        for(int i=1; i<Fanlist.size(); i++){
                            line = Fanlist.get(i).getName() + " , Rp. " + Fanlist.get(i).getPrice();
                            fanName.add(line);
                        }
                        Adapter(R.id.FANspinner, fanName);
                    }

                    @Override
                    public void onFailure(Call<List<Fan>> call, Throwable t) {
                        Log.d("ERRORzufar: ", t.getMessage());
                        Toast.makeText(PCBuildActivity.this, "Failed fetch data", Toast.LENGTH_SHORT).show();
                    }
                }
        );
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String text = parent.getItemAtPosition(position).toString();
        for(int i = 0; i < cpuName.size(); i++){
            if(text.equals(cpuName.get(i))){
                cpuPrice = CPUlist.get(i).getPrice();
            }
            else if(text.equals(moboName.get(i))){
                moboPrice = Mobolist.get(i).getPrice();
            }
            else if(text.equals(gpuName.get(i))){
                gpuPrice = GPUlist.get(i).getPrice();
            }
            else if(text.equals(ramName.get(i))){
                ramPrice = RAMlist.get(i).getPrice();
            }
            else if(text.equals(storageName.get(i))){
                storagePrice = Storagelist.get(i).getPrice();
            }
            else if(text.equals(psuName.get(i))){
                psuPrice = PSUlist.get(i).getPrice();
            }
            else if(text.equals(caseName.get(i))){
                casePrice = Caselist.get(i).getPrice();
            }
            else if(text.equals(fanName.get(i))){
                fanPrice = Fanlist.get(i).getPrice();
            }
        }
        finalPrice = moboPrice + cpuPrice + gpuPrice + ramPrice + storagePrice + psuPrice + casePrice + fanPrice;
        textPrice = findViewById(R.id.finalPrice);
        textPrice.setText("Rp. " + finalPrice);
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}