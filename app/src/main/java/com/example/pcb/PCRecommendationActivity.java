package com.example.pcb;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PCRecommendationActivity extends AppCompatActivity {
    private RecyclerView rvRecommendation;
    public List<Rekomendasi> rekomendasiList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pcrecommendation);

        rvRecommendation = findViewById(R.id.rv_recommendation);
        rvRecommendation.setHasFixedSize(true);

        CRUDapi crudInterface = RetrofitClient.getClient().create(CRUDapi.class);
        Call<List<Rekomendasi>> call = crudInterface.fetchRekomendasi();
        Log.d("PCRECOMMENDATION", "Masuk enqueue");
        call.enqueue(
                new Callback<List<Rekomendasi>>() {
                    @Override
                    public void onResponse(Call<List<Rekomendasi>> call, Response<List<Rekomendasi>> response) {
                        rekomendasiList = response.body();
                        rekomendasiList.get(0).setPhoto(R.drawable.low_tier);
                        Log.d("PCRECOMMENDATION", " " + rekomendasiList.get(0).getNama());
                    }

                    @Override
                    public void onFailure(Call<List<Rekomendasi>> call, Throwable t) {
                        Log.d("PCRECOMMENDATION: ", t.getMessage());
                        Toast.makeText(PCRecommendationActivity.this, "Failed fetch data", Toast.LENGTH_SHORT).show();
                    }
                }
        );

        rvRecommendation.setLayoutManager(new LinearLayoutManager(this));
        CardViewAdapter cardViewAdapter = new CardViewAdapter(rekomendasiList);
        rvRecommendation.setAdapter(cardViewAdapter);
    }
}