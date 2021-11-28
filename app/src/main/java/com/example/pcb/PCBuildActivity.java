package com.example.pcb;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

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

import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PCBuildActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    private DrawerLayout drawer;
    private TextView txt_nama;
    private TextView txt_email;
    private NavigationView navigationView;
    private de.hdodenhof.circleimageview.CircleImageView image_user;
    private List<User> namaList = MainMenuActivity.list;

    List<CPU> CPUlist = MainMenuActivity.CPUlist;
    List<Motherboard> Mobolist = MainMenuActivity.Mobolist;
    List<GPU> GPUlist = MainMenuActivity.GPUlist;
    List<RAM> RAMlist = MainMenuActivity.RAMlist;
    List<Storage> Storagelist =  MainMenuActivity.Storagelist;
    List<PSU> PSUlist =  MainMenuActivity.PSUlist;
    List<Case> Caselist =  MainMenuActivity.Caselist;
    List<Fan> Fanlist =  MainMenuActivity.Fanlist;
    List<String> cpuName = MainMenuActivity.cpuName;
    List<String> moboName = MainMenuActivity.moboName;
    List<String> gpuName = MainMenuActivity.gpuName;
    List<String> ramName = MainMenuActivity.ramName;
    List<String> storageName = MainMenuActivity.storageName;
    List<String> psuName = MainMenuActivity.psuName;
    List<String> caseName = MainMenuActivity.caseName;
    List<String> fanName = MainMenuActivity.fanName;
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

        setTitle("Build your own PC");

        navigationView = (NavigationView)findViewById(R.id.nav_view);
        View headerView = navigationView.getHeaderView(0);
        txt_nama = (TextView) headerView.findViewById(R.id.header_name);
        txt_email = (TextView) headerView.findViewById(R.id.header_email);
        image_user = (de.hdodenhof.circleimageview.CircleImageView) headerView.findViewById(R.id.header_image);
        image_user = (de.hdodenhof.circleimageview.CircleImageView) headerView.findViewById(R.id.header_image);

        if(MainMenuActivity.login == false){
            txt_nama.setText("Guest");
            txt_email.setText("Guest");
            image_user.setImageResource(R.drawable.logopcb);
        }
        else {
            txt_nama.setText(namaList.get(0).getUsername());
            txt_email.setText(namaList.get(0).getEmail());
            image_user.setImageResource(R.drawable.zufar);
        }

        Adapter(R.id.CPUspinner, cpuName);
        Adapter(R.id.Mobospinner, moboName);
        Adapter(R.id.GPUspinner, gpuName);
        Adapter(R.id.RAMspinner, ramName);
        Adapter(R.id.Storagespinner, storageName);
        Adapter(R.id.PSUspinner, psuName);
        Adapter(R.id.Casespinner, caseName);
        Adapter(R.id.FANspinner, fanName);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawer = findViewById(R.id.drawer_layout);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
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

    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
            txt_nama.setText("Nama");
        } else {
            super.onBackPressed();
        }
    }
}