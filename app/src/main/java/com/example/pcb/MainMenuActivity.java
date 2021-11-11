package com.example.pcb;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

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


public class MainMenuActivity extends AppCompatActivity {
    private DrawerLayout drawer;
    private TextView txt_nama;
    private NavigationView navigationView;
    public static final String EXTRA_EMAIL = "email_user";
    public static List<User> list = new ArrayList<User>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);

        String dataEmail = getIntent().getStringExtra(EXTRA_EMAIL);

        navigationView = (NavigationView)findViewById(R.id.nav_view);
        View headerView = navigationView.getHeaderView(0);
        txt_nama = (TextView) headerView.findViewById(R.id.darvin);


        CRUDapi crudInterface = RetrofitClient.getClient().create(CRUDapi.class);
        Call<List<User>> call = crudInterface.fetchUsername(dataEmail);
        call.enqueue(
                new Callback<List<User>>() {
                         @Override
                         public void onResponse(Call<List<User>> call, Response<List<User>> response) {
                             list = response.body();
                             txt_nama.setText(list.get(0).getUsername());
                         }

                         @Override
                         public void onFailure(Call<List<User>> call, Throwable t) {
                             Log.d("ERRORzufar: ", t.getMessage());
                             Toast.makeText(MainMenuActivity.this, "Failed fetch data", Toast.LENGTH_SHORT).show();
                         }
                     }
        );

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawer = findViewById(R.id.drawer_layout);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

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

    public void addData(){
        setContentView(R.layout.navigation_header);
    }

    public void fetchUsername(String email){

    }
}