package com.example.pcb;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PCRecommendationActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private DrawerLayout drawer;
    private RecyclerView rvRecommendation;
    private TextView txt_nama;
    private TextView txt_email;
    private NavigationView navigationView;
    private de.hdodenhof.circleimageview.CircleImageView image_user;
    public ArrayList<Rekomendasi> rekomendasiList = new ArrayList<>();
    public static List<User> namaList = MainMenuActivity.list;
    private int[] photo = {R.drawable.lowtier, R.drawable.mid_tier, R.drawable.high_tier};

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pcrecommendation);
        setTitle("Choose our recommendation");

        rvRecommendation = findViewById(R.id.rv_recommendation);
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
            if(MainMenuActivity.list.get(0).getUsername().equals("zufar")){
                image_user.setImageResource(R.drawable.zufar);
            }
            else if(MainMenuActivity.list.get(0).getUsername().equals("ilhum")){
                image_user.setImageResource(R.drawable.ilhum);
            }
            else if(MainMenuActivity.list.get(0).getUsername().equals("fred")){
                image_user.setImageResource(R.drawable.fred);
            }
            else{
                image_user.setImageResource(R.drawable.logopcb);
            }
        }

        CRUDapi crudInterface = RetrofitClient.getClient().create(CRUDapi.class);
        Call<ArrayList<Rekomendasi>> call = crudInterface.fetchRekomendasi();
        Log.d("PCRECOMMENDATION", "Masuk enqueue");
        call.enqueue(
                new Callback<ArrayList<Rekomendasi>>() {
                    @Override
                    public void onResponse(Call<ArrayList<Rekomendasi>> call, Response<ArrayList<Rekomendasi>> response) {
                        rekomendasiList.addAll(response.body());
                        for(int i = 0; i <rekomendasiList.size(); i++){
                            rekomendasiList.get(i).setPhoto(photo[i]);
                            Log.d("PCRECOMMENDATION", " " + rekomendasiList.get(i).getNama());
                        }
                        showRecyclerCardView();
                    }

                    @Override
                    public void onFailure(Call<ArrayList<Rekomendasi>> call, Throwable t) {
                        Log.d("PCRECOMMENDATION: ", t.getMessage());
                        Toast.makeText(PCRecommendationActivity.this, "Failed fetch data", Toast.LENGTH_SHORT).show();
                    }
                }
        );

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

    }

    private void showRecyclerCardView(){
        rvRecommendation.setLayoutManager(new LinearLayoutManager(this));
        CardViewAdapter cardViewAdapter = new CardViewAdapter(rekomendasiList);
        rvRecommendation.setAdapter(cardViewAdapter);
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

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.nav_logout:
                Intent moveIntent = new Intent(PCRecommendationActivity.this, LoginActivity.class);
                startActivity(moveIntent);
                break;
            case R.id.nav_profile:
                if(MainMenuActivity.login == true){
                    moveIntent = new Intent(PCRecommendationActivity.this, AccountActivity.class);
                    startActivity(moveIntent);
                }
                else{
                    Toast.makeText(PCRecommendationActivity.this, "You have to Login", Toast.LENGTH_SHORT).show();
                }
        }
        return true;
    }
}