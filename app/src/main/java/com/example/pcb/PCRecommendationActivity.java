package com.example.pcb;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PCRecommendationActivity extends AppCompatActivity {
    private RecyclerView rvRecommendation;
    public ArrayList<Rekomendasi> rekomendasiList = new ArrayList<>();
    private int[] photo = {R.drawable.lowtier, R.drawable.mid_tier, R.drawable.high_tier};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pcrecommendation);

        rvRecommendation = findViewById(R.id.rv_recommendation);

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

    }

    private void showRecyclerCardView(){
        rvRecommendation.setLayoutManager(new LinearLayoutManager(this));
        CardViewAdapter cardViewAdapter = new CardViewAdapter(rekomendasiList);
        rvRecommendation.setAdapter(cardViewAdapter);
    }
}