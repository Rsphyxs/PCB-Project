package com.example.pcb;

import  android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class MainMenuActivity extends AppCompatActivity implements View.OnClickListener, NavigationView.OnNavigationItemSelectedListener {
    private DrawerLayout drawer;
    private TextView txt_nama;
    private TextView txt_email;
    private ImageButton pcb_builder;
    private ImageButton pcb_recommendation;
    private ImageButton butCPU;
    private ImageButton butGPU;
    private ImageButton butMobo;
    private ImageButton butRAM;
    private ImageButton butStorage;
    private ImageButton butPSU;
    private ImageButton butCase;
    private ImageButton butFan;
    private de.hdodenhof.circleimageview.CircleImageView image_user;
    private NavigationView navigationView;
    public static final String EXTRA_EMAIL = "email_user";
    public static List<User> list = new ArrayList<User>();
    public static boolean login;
    public static int imageUser;

    public static List<CPU> CPUlist = new ArrayList<>();
    public static List<Motherboard> Mobolist = new ArrayList<>();
    public static List<GPU> GPUlist = new ArrayList<>();
    public static List<RAM> RAMlist = new ArrayList<>();
    public static List<Storage> Storagelist = new ArrayList<>();
    public static List<PSU> PSUlist = new ArrayList<>();
    public static List<Case> Caselist = new ArrayList<>();
    public static List<Fan> Fanlist = new ArrayList<>();
    public static List<String> cpuName = new ArrayList<String>();
    public static List<String> moboName = new ArrayList<String>();
    public static List<String> gpuName = new ArrayList<String>();
    public static List<String> ramName = new ArrayList<String>();
    public static List<String> storageName = new ArrayList<String>();
    public static List<String> psuName = new ArrayList<String>();
    public static List<String> caseName = new ArrayList<String>();
    public static List<String> fanName = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);

        String dataEmail = getIntent().getStringExtra(EXTRA_EMAIL);

        Log.d("Main Menu: ", "" + dataEmail);

        navigationView = (NavigationView)findViewById(R.id.nav_view);
        View headerView = navigationView.getHeaderView(0);
        txt_nama = (TextView) headerView.findViewById(R.id.header_name);
        txt_email = (TextView) headerView.findViewById(R.id.header_email);
        pcb_builder = findViewById(R.id.pcbuilder);
        pcb_builder.setOnClickListener(this);
        pcb_recommendation = findViewById(R.id.pcrecommendation);
        pcb_recommendation.setOnClickListener(this);
        butCPU = findViewById(R.id.ButProcie);
        butCPU.setOnClickListener(this);
        butGPU = findViewById(R.id.ButGpu);
        butGPU.setOnClickListener(this);
        butMobo = findViewById(R.id.ButMobo);
        butMobo.setOnClickListener(this);
        butRAM = findViewById(R.id.ButRam);
        butRAM.setOnClickListener(this);
        butStorage = findViewById(R.id.ButStorage);
        butStorage.setOnClickListener(this);
        butPSU = findViewById(R.id.ButPsu);
        butPSU.setOnClickListener(this);
        butCase = findViewById(R.id.ButCase);
        butCase.setOnClickListener(this);
        butFan = findViewById(R.id.ButFan);
        butFan.setOnClickListener(this);
        image_user = (de.hdodenhof.circleimageview.CircleImageView) headerView.findViewById(R.id.header_image);

        CPUlist.clear();
        cpuName.clear();
        GPUlist.clear();
        gpuName.clear();
        Mobolist.clear();
        moboName.clear();
        RAMlist.clear();
        ramName.clear();
        Storagelist.clear();
        storageName.clear();
        PSUlist.clear();
        psuName.clear();
        Caselist.clear();
        caseName.clear();
        Fanlist.clear();
        fanName.clear();

        imageUser = R.drawable.zufar;

        if(dataEmail == null){
            txt_nama.setText("Guest");
            txt_email.setText("Guest");
            image_user.setImageResource(R.drawable.logopcb);
            login = false;
        }
        else {
            login = true;
            CRUDapi crudInterface = RetrofitClient.getClient().create(CRUDapi.class);
            Call<List<User>> call = crudInterface.fetchUsername(dataEmail);
            call.enqueue(
                    new Callback<List<User>>() {
                        @Override
                        public void onResponse(Call<List<User>> call, Response<List<User>> response) {
                            list = response.body();
                            txt_nama.setText(list.get(0).getUsername());
                            txt_email.setText(list.get(0).getEmail());
                            image_user.setImageResource(imageUser);
                            Log.d("MainMenu: ", "Password: " + list.get(0).getPassword());
                        }

                        @Override
                        public void onFailure(Call<List<User>> call, Throwable t) {
                            Log.d("ERRORzufar: ", t.getMessage());
                            Toast.makeText(MainMenuActivity.this, "Failed fetch data", Toast.LENGTH_SHORT).show();
                        }
                    }
            );
        }

            Toolbar toolbar = findViewById(R.id.toolbar);
            setSupportActionBar(toolbar);

            drawer = findViewById(R.id.drawer_layout);
            NavigationView navigationView = findViewById(R.id.nav_view);
            navigationView.setNavigationItemSelectedListener(this);

            ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar,
                    R.string.navigation_drawer_open, R.string.navigation_drawer_close);
            drawer.addDrawerListener(toggle);
            toggle.syncState();

        FetchCPU();
        FetchMobo();
        FetchGPU();
        FetchRAM();
        FetchStorage();
        FetchPSU();
        FetchCase();
        FetchFan();
    }

    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    public void addData(){
        setContentView(R.layout.navigation_header);
    }

    public void fetchUsername(String email){

    }

    @Override
    public void onClick(View v) {
        if(v.getId()== R.id.pcbuilder){
            Intent moveIntent = new Intent(MainMenuActivity.this, PCBuildActivity.class);
            startActivity(moveIntent);
        }
        else if(v.getId()== R.id.pcrecommendation){
            Intent moveIntent = new Intent(MainMenuActivity.this, PCRecommendationActivity.class);
            startActivity(moveIntent);
        }
        else if(v.getId()==R.id.ButProcie){
            Intent moveIntent = new Intent(MainMenuActivity.this, ComponentActivity.class);
            moveIntent.putExtra(ComponentActivity.EXTRA_POSITION, 0);
            startActivity(moveIntent);
        }
        else if(v.getId()==R.id.ButGpu){
            Intent moveIntent = new Intent(MainMenuActivity.this, ComponentActivity.class);
            moveIntent.putExtra(ComponentActivity.EXTRA_POSITION, 1);
            startActivity(moveIntent);
        }
        else if(v.getId()==R.id.ButMobo){
            Intent moveIntent = new Intent(MainMenuActivity.this, ComponentActivity.class);
            moveIntent.putExtra(ComponentActivity.EXTRA_POSITION, 2);
            startActivity(moveIntent);
        }
        else if(v.getId()==R.id.ButRam){
            Intent moveIntent = new Intent(MainMenuActivity.this, ComponentActivity.class);
            moveIntent.putExtra(ComponentActivity.EXTRA_POSITION, 3);
            startActivity(moveIntent);
        }
        else if(v.getId()==R.id.ButStorage){
            Intent moveIntent = new Intent(MainMenuActivity.this, ComponentActivity.class);
            moveIntent.putExtra(ComponentActivity.EXTRA_POSITION, 4);
            startActivity(moveIntent);
        }
        else if(v.getId()==R.id.ButPsu){
            Intent moveIntent = new Intent(MainMenuActivity.this, ComponentActivity.class);
            moveIntent.putExtra(ComponentActivity.EXTRA_POSITION, 5);
            startActivity(moveIntent);
        }
        else if(v.getId()==R.id.ButCase){
            Intent moveIntent = new Intent(MainMenuActivity.this, ComponentActivity.class);
            moveIntent.putExtra(ComponentActivity.EXTRA_POSITION, 6);
            startActivity(moveIntent);
        }
        else if(v.getId()==R.id.ButFan){
            Intent moveIntent = new Intent(MainMenuActivity.this, ComponentActivity.class);
            moveIntent.putExtra(ComponentActivity.EXTRA_POSITION, 7);
            startActivity(moveIntent);
        }
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
                    }

                    @Override
                    public void onFailure(Call<List<CPU>> call, Throwable t) {
                        Log.d("ERRORzufar: ", t.getMessage());
                        Toast.makeText(MainMenuActivity.this, "Failed fetch data", Toast.LENGTH_SHORT).show();
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
                    }

                    @Override
                    public void onFailure(Call<List<Motherboard>> call, Throwable t) {
                        Log.d("ERRORzufar: ", t.getMessage());
                        Toast.makeText(MainMenuActivity.this, "Failed fetch data", Toast.LENGTH_SHORT).show();
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
                    }

                    @Override
                    public void onFailure(Call<List<GPU>> call, Throwable t) {
                        Log.d("ERRORzufar: ", t.getMessage());
                        Toast.makeText(MainMenuActivity.this, "Failed fetch data", Toast.LENGTH_SHORT).show();
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
                    }

                    @Override
                    public void onFailure(Call<List<RAM>> call, Throwable t) {
                        Log.d("ERRORzufar: ", t.getMessage());
                        Toast.makeText(MainMenuActivity.this, "Failed fetch data", Toast.LENGTH_SHORT).show();
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
                    }

                    @Override
                    public void onFailure(Call<List<Storage>> call, Throwable t) {
                        Log.d("ERRORzufar: ", t.getMessage());
                        Toast.makeText(MainMenuActivity.this, "Failed fetch data", Toast.LENGTH_SHORT).show();
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
                    }

                    @Override
                    public void onFailure(Call<List<PSU>> call, Throwable t) {
                        Log.d("ERRORzufar: ", t.getMessage());
                        Toast.makeText(MainMenuActivity.this, "Failed fetch data", Toast.LENGTH_SHORT).show();
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
                            line = Caselist.get(i).getName() + " , Rp. " + Caselist.get(i).getPrice();
                            caseName.add(line);
                        }
                    }

                    @Override
                    public void onFailure(Call<List<Case>> call, Throwable t) {
                        Log.d("ERRORzufar: ", t.getMessage());
                        Toast.makeText(MainMenuActivity.this, "Failed fetch data", Toast.LENGTH_SHORT).show();
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
                    }

                    @Override
                    public void onFailure(Call<List<Fan>> call, Throwable t) {
                        Log.d("ERRORzufar: ", t.getMessage());
                        Toast.makeText(MainMenuActivity.this, "Failed fetch data", Toast.LENGTH_SHORT).show();
                    }
                }
        );
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.nav_logout:
                Intent moveIntent = new Intent(MainMenuActivity.this, LoginActivity.class);
                startActivity(moveIntent);
                break;
            case R.id.nav_profile:
                if(login == true){
                    moveIntent = new Intent(MainMenuActivity.this, AccountActivity.class);
                    startActivity(moveIntent);
                }
                else{
                    Toast.makeText(MainMenuActivity.this, "You have to Login", Toast.LENGTH_SHORT).show();
                }
        }
        return true;
    }
}